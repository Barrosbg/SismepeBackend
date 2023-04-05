package br.gov.pe.sismepe.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Habitacao;
import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.HabitacaoDTO;
import br.gov.pe.sismepe.repositories.HabitacaoRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class HabitacaoService {

	@Autowired
	private HabitacaoRepository repo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public List<Habitacao> all(){
		return repo.findAll();
	}
	
	public Habitacao findById(Long id) {
		Optional<Habitacao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Habitacao não encontrato! ID: " + id));	
	
	}
	
	public Habitacao insert(Habitacao habitacao) {
		UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuarioLogado = usuarioRepo.findByLogin(userSS.getLogin());
		Habitacao oldHabitacao = repo.findByPaciente_idAndSituacao(habitacao.getPaciente().getId(), "S");
		if(oldHabitacao==null) {
			habitacao.setUsuarioCadastro(usuarioLogado);
			habitacao.setDataCadastro(new Date());
			habitacao.setId(null);
			habitacao.setSituacao("S");
			return repo.save(habitacao);
		}else {
			oldHabitacao.setUsuarioCadastro(usuarioLogado);
			oldHabitacao.setDataCadastro(new Date());
			atualizarHabitacao(oldHabitacao, habitacao);
			return repo.save(oldHabitacao);
		}		
	}

	private void atualizarHabitacao(Habitacao oldHabitacao, Habitacao habitacao) {
		if(habitacao.getNumeroBanheiros()!=null) oldHabitacao.setNumeroBanheiros(habitacao.getNumeroBanheiros());		
		if(habitacao.getNumeroCozinhas()!=null) oldHabitacao.setNumeroCozinhas(habitacao.getNumeroCozinhas());
		if(habitacao.getNumeroQuartos()!=null) oldHabitacao.setNumeroQuartos(habitacao.getNumeroQuartos());
		if(habitacao.getNumeroSalas()!=null) oldHabitacao.setNumeroSalas(habitacao.getNumeroSalas());
		oldHabitacao.setSituacao(habitacao.getSituacao());
		oldHabitacao.setValor(habitacao.getValor());
	}

	public Habitacao fromDTO(HabitacaoDTO habitacaoDTO) {
		return new Habitacao(new Paciente(habitacaoDTO.getPacienteId()), new Usuario(habitacaoDTO.getUsuarioCadastroId()), 
				habitacaoDTO.getNumeroSalas(), habitacaoDTO.getNumeroCozinhas(), habitacaoDTO.getNumeroQuartos(), 
				habitacaoDTO.getNumeroBanheiros(), habitacaoDTO.getValor(), habitacaoDTO.getDataCadastro(), 
				habitacaoDTO.getSituacao());
	}

	public Habitacao findAllByPaciente(Long pacienteId) {
		return repo.findByPaciente_idAndSituacao(pacienteId,"S");		
	}

	public void update(Habitacao obj) {
		UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuarioLogado = usuarioRepo.findByLogin(userSS.getLogin());
		Optional<Habitacao> oldObj = repo.findById(obj.getId());
		atualizarHabitacao(oldObj.get(), obj);
		oldObj.get().setUsuarioCadastro(usuarioLogado);
		oldObj.get().setDataCadastro(new Date());
		repo.save(oldObj.get());
	}
}
