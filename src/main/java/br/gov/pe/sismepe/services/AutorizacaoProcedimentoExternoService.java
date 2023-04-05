package br.gov.pe.sismepe.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.AutorizacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.ItSolicitacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.SolicitacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.domain.ValidadeAutorizacao;
import br.gov.pe.sismepe.dto.AutorizacaoProcedimentoExternoDTO;
import br.gov.pe.sismepe.repositories.AutorizacaoProcedimentoExternoRepository;
import br.gov.pe.sismepe.repositories.ItSolicitacaoProcedimentoExternoRepository;
import br.gov.pe.sismepe.repositories.SolicitacaoProcedimentoExternoRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.repositories.ValidadeAutorizacaoRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.exceptions.NegocioException;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import br.gov.pe.sismepe.util.Utils;

@Service
public class AutorizacaoProcedimentoExternoService {

	@Autowired
	private AutorizacaoProcedimentoExternoRepository autorizacaoProcedimentoInternoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SolicitacaoProcedimentoExternoRepository solicitacaoProcedimentoExternoRepository;
	
	@Autowired
	private ItSolicitacaoProcedimentoExternoRepository itsolicitacaoProcedimentoExternoRepository;
	
	@Autowired
	private ValidadeAutorizacaoRepository validadeAutorizacaoRepository;

	public Page<AutorizacaoProcedimentoExterno> find(Integer page, Integer linesPerPage, String orderBy, String directions) {
				
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		return autorizacaoProcedimentoInternoRepository.findAll(pageRequest);
	}

	@Transactional
	public AutorizacaoProcedimentoExterno save(@Valid AutorizacaoProcedimentoExternoDTO autorizacao) {
		
		ItSolicitacaoProcedimentoExterno it = itsolicitacaoProcedimentoExternoRepository
				.findById(autorizacao.getItSolicitacao().getId())
				.orElseThrow(() -> new NegocioException("Item de solicitação não foi encontrado para o id informado!"));
						
			
		
		Optional<AutorizacaoProcedimentoExterno> aut = autorizacaoProcedimentoInternoRepository.findByItSolicitacao(it);
		
		if(aut.isPresent()) {
			throw new NegocioException("Já existe uma autorização cadastrada para o item informado!");
		}		
		
//		atualiza a situação do it para autorizado
		it.setSituacao("A");
		it = itsolicitacaoProcedimentoExternoRepository.save(it);
		
		SolicitacaoProcedimentoExterno solicitacao = it.getSolicitacaoProcedimentoExterno();

//		altera a sitacao da solicitacao para autorizada
		
		String situacao = "A";		

//		verifica existem outros itens na mesma solicitação
		if(solicitacao.getItSolicitacoes().size() > 1) {
			for (ItSolicitacaoProcedimentoExterno item: solicitacao.getItSolicitacoes()) {
//				verificar se todos estão autorizados
//				caso contrario, atualizar situação da solicitação para parcialmente autorizada
				if(item.getSituacao() != "A") {
					situacao = "P";
				}
			}
		}
		
		
		solicitacao.setSituacao(situacao);
		solicitacao.setObservacao(autorizacao.getObservacao());
		solicitacao.setObservacaoRestrita(autorizacao.getObservacaoRestrita());		
		solicitacao.setDataValidade(Utils.datePlusDays(new Date(), 30));
		solicitacao.setDataAlteracao(new Date());
		solicitacaoProcedimentoExternoRepository.save(solicitacao);		
		
//		pega o usuário logado
		UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioRepository.findByLogin(user.getLogin());
		
//		cria uma autorizacao
		AutorizacaoProcedimentoExterno auto = new AutorizacaoProcedimentoExterno();
		auto.setEmpresa(autorizacao.getEmpresa());
		auto.setItSolicitacao(it);
		auto.setUsuarioAutorizacao(usuario);
		auto.setDataCadastro(new Date());
		auto.setQuantidade(autorizacao.getQuantidadeAutorizada());
		auto.setSituacao("A");
		return autorizacaoProcedimentoInternoRepository.save(auto);
		
	}

	public List<AutorizacaoProcedimentoExterno> findByIdSolicitacao(Long id) {
		SolicitacaoProcedimentoExterno sol = solicitacaoProcedimentoExternoRepository.findById(id)
				.orElseThrow(() -> new NegocioException("Solicitação não foi encontrada para o id informado!"));		
		
		return autorizacaoProcedimentoInternoRepository.findByItSolicitacao_solicitacaoProcedimentoExterno(sol);
	}

	public void update(Long id, @Valid AutorizacaoProcedimentoExternoDTO autorizacao) {
		AutorizacaoProcedimentoExterno aut = autorizacaoProcedimentoInternoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Autorização não encontrada para o ID informado!"));
		
//		salva o item de solicitação		
		ItSolicitacaoProcedimentoExterno it = itsolicitacaoProcedimentoExternoRepository.findById(autorizacao.getItSolicitacao().getId())
				.orElseThrow(() -> new ObjectNotFoundException("Item de solicitação não encontrado para o ID informado!"));
		it.setSituacao(autorizacao.getItSolicitacao().getSituacao());
		it = itsolicitacaoProcedimentoExternoRepository.save(it);
		
		SolicitacaoProcedimentoExterno sol = it.getSolicitacaoProcedimentoExterno();
		
		sol.setObservacao(autorizacao.getObservacao());
		sol.setObservacaoRestrita(autorizacao.getObservacaoRestrita());
		
//		altera a sitacao da solicitacao para autorizada		
		String situacao = "A";		

//		verifica se existem outros itens na mesma solicitação
		if(sol.getItSolicitacoes().size() > 1) {
			for (ItSolicitacaoProcedimentoExterno item: sol.getItSolicitacoes()) {
//				verificar se todos estão autorizados
//				caso contrario, atualizar situação da solicitação para parcialmente autorizada
				if(item.getSituacao() != "A") {
					situacao = "P";
				}
			}
		}
		
		sol.setSituacao(situacao);
		
////		atualiza a solicitacao
		solicitacaoProcedimentoExternoRepository.save(sol);
				
		aut = autorizacaoProcedimentoInternoRepository.findById(id)
					.orElseThrow(() -> new ObjectNotFoundException("Autorização não encontrada para o ID informado!"));
		
		aut.setDataAlteracao(new Date());
		aut.setEmpresa(autorizacao.getEmpresa());
		aut.setSituacao(autorizacao.getSituacao());
		
//		pega o usuário logado
//		UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Usuario usuario = usuarioRepository.findByLogin(user.getLogin());		
//		if(aut.getUsuarioAutorizacao() != usuario) {
//			
//			aut.setUsuarioAutorizacao(usuario);		
//		}
		
		autorizacaoProcedimentoInternoRepository.save(aut);
		
	}

	public AutorizacaoProcedimentoExterno revalidar(Long id, ValidadeAutorizacao validadeAutorizacao) {
		AutorizacaoProcedimentoExterno aut = autorizacaoProcedimentoInternoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Autorização não encontrada!"));
		
			
				
		boolean dataValidadeInvalida = aut.getValidades().stream().anyMatch((val) 
				-> (validadeAutorizacao.getDataValidade().getTime() - val.getDataValidade().getTime()) <= 0);
		
				
		if(dataValidadeInvalida) {
			throw new NegocioException("A data de validade não pode ser menor que a atual!");
		}
		
//		pega o usuário logado
		UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioRepository.findByLogin(user.getLogin());
		
		validadeAutorizacao.setUsuarioCadastro(usuario);
		validadeAutorizacao.setDataCadastro(new Date());
		validadeAutorizacao.setAutorizacaoProcedimentoExterno(aut);

		validadeAutorizacaoRepository.save(validadeAutorizacao);
		
		return autorizacaoProcedimentoInternoRepository.findById(id).orElse(aut);
	}
}
