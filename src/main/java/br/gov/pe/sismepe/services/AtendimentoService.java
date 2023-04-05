package br.gov.pe.sismepe.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Atendimento;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.domain.enums.SituacaoAtendimento;
import br.gov.pe.sismepe.dto.AtendimentoDTO;
import br.gov.pe.sismepe.repositories.AtendimentoRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.exceptions.DataIntegrityException;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendimentoRepository repo;
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	public  Atendimento find(Long id) {
		Optional<Atendimento> obj =  repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Atendimento não encontrato! ID: " + id));
	}
	
	public void insert(Atendimento obj) {
		obj.setId(null);
		repo.save(obj);
	}
	
	public void update(Atendimento obj) {
		Optional<Atendimento> atendimento = repo.findById(obj.getId());
		UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuarioLogado = usuarioRepo.findByLogin(userSS.getLogin());
		
		switch (obj.getSituacao()) {
		case EM_ATENDIMENTO:
			if(atendimento.get().getSituacao().equals(SituacaoAtendimento.FINALIZADO)) {
				atendimento.get().setDataFinalizacao(null);
				atendimento.get().setUsuarioFinalizacao(null);
			}
			break;
		case FINALIZADO:
			atendimento.get().setDataFinalizacao(new Date());
			atendimento.get().setUsuarioFinalizacao(usuarioLogado);
			break;
		default:
			break;
		}
		atendimento.get().setSituacao(obj.getSituacao());
		repo.save(atendimento.get());
	}
	
	public void delete(Long id) {
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma entidade vinculada.");
		}
	}
	
	public List<Atendimento> findAll() {
		return repo.findAll();
	}
	
	public List<Atendimento> listarTodos() {
		return repo.findAll();
	}

	public Atendimento fromDTO(AtendimentoDTO objDTO) {
		SituacaoAtendimento situacao = (objDTO.getSituacao() == null) ? null
				: SituacaoAtendimento.toEnum(objDTO.getSituacao());
		return new Atendimento(situacao);
	}
}