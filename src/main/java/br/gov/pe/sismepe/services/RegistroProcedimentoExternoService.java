package br.gov.pe.sismepe.services;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.AutorizacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.RegistroProcedimentoInterno;
import br.gov.pe.sismepe.domain.SolicitacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.repositories.AutorizacaoProcedimentoExternoRepository;
import br.gov.pe.sismepe.repositories.RegistroProcedimentoInternoRepository;
import br.gov.pe.sismepe.repositories.SolicitacaoProcedimentoExternoRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.exceptions.NegocioException;

@Service
public class RegistroProcedimentoExternoService {

	
	@Autowired
	private RegistroProcedimentoInternoRepository registroProcedimentoInternoRepository;
	
	@Autowired
	private AutorizacaoProcedimentoExternoRepository autorizacaoProcedimentoInternoRepository;
	
	@Autowired
	private SolicitacaoProcedimentoExternoRepository solicitacaoProcedimentoExternoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Page<RegistroProcedimentoInterno> find(Integer page, Integer linesPerPage, String orderBy,
			String directions) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
//		pega o usuário logado
		UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioRepository.findByLogin(user.getLogin());			
		
		return registroProcedimentoInternoRepository.findByUsuarioCadastro(usuario, pageRequest);
	}


	public @Valid RegistroProcedimentoInterno save(@Valid RegistroProcedimentoInterno registro) {

		AutorizacaoProcedimentoExterno auth = autorizacaoProcedimentoInternoRepository.findById(registro.getAutorizacao().getId())
				.orElseThrow(() -> new NegocioException("Autorização não foi encontrada para o id informado!"));
		
		auth.setDataAlteracao(new Date());
		auth.setSituacao("R");
		autorizacaoProcedimentoInternoRepository.save(auth);
		
//		pega o usuário logado
		UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioRepository.findByLogin(user.getLogin());			
		registro.setUsuarioCadastro(usuario);
		registro.setDataCadastro(new Date());
		return registroProcedimentoInternoRepository.save(registro);
	}


	public List<RegistroProcedimentoInterno> findByIdSolicitacao(Long id) {
		SolicitacaoProcedimentoExterno sol = solicitacaoProcedimentoExternoRepository.findById(id)
				.orElseThrow(()-> new NegocioException("Nenhuma solicitação encontrada para o ID informado!"));
		
		List<AutorizacaoProcedimentoExterno> auths = autorizacaoProcedimentoInternoRepository.findByItSolicitacao_solicitacaoProcedimentoExterno(sol);
		
		return registroProcedimentoInternoRepository.findByAutorizacaoIn(auths);
	}
	
	
	
}
