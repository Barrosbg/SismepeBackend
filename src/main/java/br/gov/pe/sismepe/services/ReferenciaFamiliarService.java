package br.gov.pe.sismepe.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.domain.Parentesco;
import br.gov.pe.sismepe.domain.ReferenciaFamiliar;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.ReferenciaFamiliarDTO;
import br.gov.pe.sismepe.repositories.ReferenciaFamiliarRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class ReferenciaFamiliarService {
	
	@Autowired
	private ReferenciaFamiliarRepository repo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public List<ReferenciaFamiliar> all(){
		return repo.findAll();
	}
	
	public ReferenciaFamiliar insert(ReferenciaFamiliar referenciaFamiliar) {
		UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		referenciaFamiliar.setDataCadastro(new Date());
		referenciaFamiliar.setSituacao("S");
		referenciaFamiliar.setUsuarioCadastro(usuarioRepo.findByLogin(userSS.getLogin()));
		return repo.save(referenciaFamiliar);
	}
	
	public ReferenciaFamiliar fromDTO(ReferenciaFamiliarDTO objDTO) {		
		return new ReferenciaFamiliar(new Parentesco(objDTO.getParentescoId()), new Usuario(objDTO.getUsuarioCadastroId()), 
				new Paciente(objDTO.getPacienteId()), objDTO.getNome(), objDTO.getDataNascimento(), objDTO.getGrauInstrucao(), 
				objDTO.getOcupacao(), objDTO.getRenda(), objDTO.getDataCadastro(), objDTO.getSituacao()); 
	}

	public ReferenciaFamiliar findById(Long id) {
		Optional<ReferenciaFamiliar> obj =  repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("ReferenciaFamiliar não encontrato! ID: " + id));
	}

	public Page<ReferenciaFamiliar> findAllByPaciente(Long pacienteId, Integer page, Integer linesPerPage,
			String orderBy, String directions) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);

		Page<ReferenciaFamiliar> lista = repo.findByPaciente_idAndSituacao(pacienteId, "S", pageRequest);
		return lista;
	}

	public void update(ReferenciaFamiliarDTO entity) {
		Optional<ReferenciaFamiliar> obj = repo.findById(entity.getId());
		if(entity.getSituacao()!=null) obj.get().setSituacao(entity.getSituacao());		
		if(entity.getDataNascimento()!=null) obj.get().setDataNascimento(entity.getDataNascimento());
		if(entity.getGrauInstrucao()!=null) obj.get().setGrauInstrucao(entity.getGrauInstrucao());
		if(entity.getNome()!=null) obj.get().setNome(entity.getNome());
		if(entity.getOcupacao()!=null) obj.get().setOcupacao(entity.getOcupacao());
		if(entity.getParentescoId()!=null) obj.get().setParentesco(new Parentesco(entity.getParentescoId()));
		if(entity.getRenda()!=0) obj.get().setRenda(entity.getRenda());
		repo.save(obj.get());		
	}

}
