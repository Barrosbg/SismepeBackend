package br.gov.pe.sismepe.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.AssistenciaSocial;
import br.gov.pe.sismepe.domain.Atendimento;
import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.dto.AssistenciaSocialDTO;
import br.gov.pe.sismepe.repositories.AssistenciaSocialRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class AssistenciaSocialService {
	
	@Autowired
	private AssistenciaSocialRepository repo;
	
	public List<AssistenciaSocial> all(){
		return repo.findAll();
	}
	
	public AssistenciaSocial insert(AssistenciaSocial obj) {
		obj.setId(null);
		obj.setDataInclusao(new Date());
		obj.setAtivo("S");
		return repo.save(obj);
	}
	
	public AssistenciaSocial findById(Long id) {
		Optional<AssistenciaSocial> obj =  repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("AssistenciaSocial não encontrato! ID: " + id));
	}

	public Page<AssistenciaSocialDTO> allByPaciente(Long pacienteId, Integer page,
			Integer linesPerPage, String orderBy, String directions) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
				
		Page<AssistenciaSocial> list = repo.findByAtendimento_pacienteAndAtivo(new Paciente(pacienteId), "S", pageRequest);
		Page<AssistenciaSocialDTO> listDTO = list.map(obj -> new AssistenciaSocialDTO(obj));
		return listDTO;
	}

	public AssistenciaSocial fromDTO(AssistenciaSocialDTO objDTO) {		
		return new AssistenciaSocial(null, new Atendimento(objDTO.getAtendimentoId()), objDTO.getSituacaoClinica(), 
				objDTO.getProvidenciaTomada(), objDTO.getStatusSituacao(), objDTO.getOutrasInformacoes(), 
				objDTO.getDataInclusao(), objDTO.getAtivo()); 
	}

	@Transactional
	public void update(AssistenciaSocial obj, AssistenciaSocialDTO objDTO) {
		Optional<AssistenciaSocial> oldObj = repo.findById(obj.getId());
		oldObj.get().setSituacaoClinica(objDTO.getSituacaoClinica());
		oldObj.get().setProvidenciaTomada(obj.getProvidenciaTomada());
		oldObj.get().setOutrasInformacoes(obj.getOutrasInformacoes());
		oldObj.get().setAtivo(obj.getAtivo());
		oldObj.get().setStatusSituacao(obj.getStatusSituacao());
		repo.save(oldObj.get());
	}
	
	

}
