package br.gov.pe.sismepe.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.AssistenciaSocial;
import br.gov.pe.sismepe.domain.AssistenciaSocialEvolucao;
import br.gov.pe.sismepe.domain.Atendimento;
import br.gov.pe.sismepe.dto.AssistenciaSocialEvolucaoDTO;
import br.gov.pe.sismepe.repositories.AssistenciaSocialEvolucaoRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class AssistenciaSocialEvolucaoService {
	
	@Autowired
	private AssistenciaSocialEvolucaoRepository repo;
	
	public List<AssistenciaSocialEvolucao> all(){
		return repo.findAll();
	}
	
	public AssistenciaSocialEvolucao insert(AssistenciaSocialEvolucao obj) {
		if(obj.getId()==null) obj.setDataInclusao(new Date());
		return repo.save(obj);
	}
	
	public AssistenciaSocialEvolucao findById(Long id) {
		Optional<AssistenciaSocialEvolucao> obj =  repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Assistencia social evolução não encontrato! ID: " + id ));
	}

	public Page<AssistenciaSocialEvolucaoDTO> allByAssistenciaSocial(Long assistenciaSocialId, String ativo, Integer page,
			Integer linesPerPage, String orderBy, String directions) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
				
		Page<AssistenciaSocialEvolucao> list = repo.findByAssistenciaSocialAndSituacao(new AssistenciaSocial(assistenciaSocialId), ativo, pageRequest);
		Page<AssistenciaSocialEvolucaoDTO> listDTO = list.map(obj -> new AssistenciaSocialEvolucaoDTO(obj));
		return listDTO;
	}

	public AssistenciaSocialEvolucao fromDTO(AssistenciaSocialEvolucaoDTO objDTO) {		
		return new AssistenciaSocialEvolucao(objDTO.getId(), new AssistenciaSocial(objDTO.getAssistenciaSocialId()), 
				new Atendimento(objDTO.getAtendimentoId()), objDTO.getDescricao(), objDTO.getDataInclusao(), 
				objDTO.getSituacao()); 
	}

	public void update(AssistenciaSocialEvolucaoDTO entity) {
		Optional<AssistenciaSocialEvolucao> obj = repo.findById(entity.getId());
		if(entity.getSituacao()!=null) obj.get().setSituacao(entity.getSituacao());		
		if(entity.getDescricao()!=null) obj.get().setDescricao(entity.getDescricao());
		repo.save(obj.get());		
	}

}
