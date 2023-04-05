package br.gov.pe.sismepe.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.AutorizacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.Cidade;
import br.gov.pe.sismepe.domain.ItSolicitacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.PessoaDependente;
import br.gov.pe.sismepe.domain.PessoaTitular;
import br.gov.pe.sismepe.domain.Prestador;
import br.gov.pe.sismepe.domain.RegistroProcedimentoInterno;
import br.gov.pe.sismepe.domain.SolicitacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.domain.enums.NaturezaSolicitacaoProcedimentoEnum;
import br.gov.pe.sismepe.dto.AutorizacaoProcedimentoExternoDTO;
import br.gov.pe.sismepe.dto.SolicitacaoProcedimentoExternoAprovacaoDTO;
import br.gov.pe.sismepe.report.service.GuiaSolicitacaoProcedimentoExternoReportService;
import br.gov.pe.sismepe.repositories.AutorizacaoProcedimentoExternoRepository;
import br.gov.pe.sismepe.repositories.CidadeRepository;
import br.gov.pe.sismepe.repositories.ItSolicitacaoProcedimentoExternoRepository;
import br.gov.pe.sismepe.repositories.PrestadorRepository;
import br.gov.pe.sismepe.repositories.RegistroProcedimentoInternoRepository;
import br.gov.pe.sismepe.repositories.SolicitacaoProcedimentoExternoRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.exceptions.NegocioException;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import br.gov.pe.sismepe.util.Utils;

@Service
public class SolicitacaoProcedimentoExternoService {

	@Autowired
	private SolicitacaoProcedimentoExternoRepository solicitacaoProcedimentoExternoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PrestadorRepository prestadorRepository;

	@Autowired
	private RegistroProcedimentoInternoRepository registroProcedimentoInternoRepository;

	@Autowired
	private AutorizacaoProcedimentoExternoRepository autorizacaoProcedimentoInternoRepository;

	@Autowired
	private ItSolicitacaoProcedimentoExternoRepository itSolicitacaoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	public Page<SolicitacaoProcedimentoExterno> find(Long prestadorId, Long pacienteId, String dataCadastro,
			Integer page, Integer linesPerPage, String orderBy, String directions) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		
		return solicitacaoProcedimentoExternoRepository.findByParameters(prestadorId, pacienteId, dataCadastro,
				pageRequest);

	}

	public SolicitacaoProcedimentoExterno findById(Long id) throws ObjectNotFoundException {
		return solicitacaoProcedimentoExternoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Solicitação não encontrada!"));
	}
	
	public List<SolicitacaoProcedimentoExterno> findBySolicitacaoPai_Id(Long solPai_Id) throws ObjectNotFoundException {
		return solicitacaoProcedimentoExternoRepository.findBySolicitacaoPai_Id(solPai_Id);
	}

	public SolicitacaoProcedimentoExterno findByIdAndPrestador(Long id) throws ObjectNotFoundException {

		// pega o usuário logado
		UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioRepository.findByLogin(user.getLogin());

		// verificar se usuario é um prestador
		Prestador prestador = prestadorRepository.findByPessoa(usuario.getPessoa());

		if (prestador != null) {
			return solicitacaoProcedimentoExternoRepository.findByIdAndPrestadorSolicitante(id, prestador)
					.orElseThrow(() -> new ObjectNotFoundException("Solicitação não encontrada!"));

		} else {
			throw new ObjectNotFoundException("Solicitação não encontrada!");
		}

	}

	public Page<SolicitacaoProcedimentoExterno> findByPrestador(Long pacienteId, String dataCadastro, Integer page,
			Integer linesPerPage, String orderBy, String directions) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);

		// pega o usuário logado
		UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioRepository.findByLogin(user.getLogin());

		// verificar se usuario é um prestador
		Prestador prestador = prestadorRepository.findByPessoa(usuario.getPessoa());

		if (prestador != null) {
			return solicitacaoProcedimentoExternoRepository.findByParameters(prestador.getId(), pacienteId,
					dataCadastro, pageRequest);
//				return solicitacaoProcedimentoExternoRepository.findByPrestadorSolicitante(prestador, pageRequest);
		} else {
			return new PageImpl<SolicitacaoProcedimentoExterno>(new ArrayList<SolicitacaoProcedimentoExterno>(),
					pageRequest, 0);
		}
	}

	@Transactional
	public SolicitacaoProcedimentoExterno savarSolicitacao(SolicitacaoProcedimentoExterno solicitacao) {

//			//	pega o usuário logado
//			UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			Usuario usuario = usuarioRepository.findByLogin(user.getLogin());		
//
//			//	verificar se usuario é um prestador
//			Prestador prestador = prestadorRepository.findByPessoa(usuario.getPessoa());
//
//			if(prestador != null) {

		if (solicitacao.getItSolicitacoes().size() > 0) {
//					solicitacao.setPrestadorSolicitante(prestador);	
			solicitacao.setDataCadastro(new Date());
			solicitacao.setSituacao("S");
			solicitacao.setAtivo("S");

			for (ItSolicitacaoProcedimentoExterno it : solicitacao.getItSolicitacoes()) {
				it.setDataCadastro(new Date());
				it.setDataCadastro(new Date());
				it.setSolicitacaoProcedimentoExterno(solicitacao);
				it.setSituacao("S");
			}

			return solicitacaoProcedimentoExternoRepository.save(solicitacao);

		} else {
			throw new NegocioException("Lista de procedimentos vazia!");
		}
//			} else {
//				throw new NegocioException("O usuário precisa ser um prestador para solicitar exame!");
//			}

	}

	public void updateSolicitacao(Long id, SolicitacaoProcedimentoExterno solicitacao) {
		SolicitacaoProcedimentoExterno sol = solicitacaoProcedimentoExternoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Solicitação não encontrada para o ID informado!"));

		sol.setSituacao(solicitacao.getSituacao());
		sol.setJustificativa(sol.getJustificativa());
		sol.setDataAlteracao(new Date());
		sol.setAtivo(solicitacao.getAtivo());
		solicitacaoProcedimentoExternoRepository.save(sol);
	}

	public void gerarPdfGuia(Long id, HttpServletResponse response) throws IOException, Exception {

		SolicitacaoProcedimentoExterno sol = solicitacaoProcedimentoExternoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Solicitação não encontrada. ID " + id));

		if (sol.getNatureza().equals(NaturezaSolicitacaoProcedimentoEnum.EXAME)) {
			pdfGuiaExame(sol, response);
		} else {
			pdfGuiaInternacao(sol, response);
		}

	}

	public void pdfGuiaExame(SolicitacaoProcedimentoExterno sol, HttpServletResponse response)
			throws IOException, Exception {
		String carteirinha = "";
		if (sol.getPaciente().getPessoa().getClass().getSimpleName().equals("PessoaTitular")) {
			PessoaTitular pessoa = (PessoaTitular) sol.getPaciente().getPessoa();
			carteirinha = pessoa.getMatricula().toString() + " - Titular";
		} else {
			PessoaDependente pessoa = (PessoaDependente) sol.getPaciente().getPessoa();
			carteirinha = pessoa.getMatricula().toString() + " - Dependente";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");

		List<AutorizacaoProcedimentoExterno> auths = autorizacaoProcedimentoInternoRepository
				.findByItSolicitacao_solicitacaoProcedimentoExterno(sol);

		List<RegistroProcedimentoInterno> registros = registroProcedimentoInternoRepository.findByAutorizacaoIn(auths);

		String contratante = "";
		String contratanteCnpj = "";
		String dataAutorizacao = "";

		if (auths.size() > 0) {
			contratante = auths.get(0).getEmpresa().getRazaoSocial();
			contratanteCnpj = auths.get(0).getEmpresa().getCnpj();
			dataAutorizacao = sdfDate.format(auths.get(0).getDataCadastro());
		}

		GuiaSolicitacaoProcedimentoExternoReportService rs = new GuiaSolicitacaoProcedimentoExternoReportService();

		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("TITULO", "GUIA DE SERVIÇO PROFISSIONAL / SERVIÇO AUXILIAR DE DIAGNÓSTICO E TERAPIA - SP/SADT");
		parameters.put("numeroGuia", sol.getFormatedId());
		parameters.put("dataAutorizacao", dataAutorizacao);
		parameters.put("dataValidade",
				sol.getDataValidade() == null ? "" : Utils.ptOnlyDateToString(sol.getDataValidade()));
		parameters.put("numeroGuiaOperadora", "");
		parameters.put("numeroCarteira", carteirinha);
		parameters.put("nomeBeneficiario", sol.getPaciente().getPessoa().getNome());
		parameters.put("nomePrestadorSolicitante", sol.getPrestadorSolicitante().getPessoa().getNome());
		parameters.put("conselho", sol.getPrestadorSolicitante().getConselho().getId().toString());
		parameters.put("numeroConselho", sol.getPrestadorSolicitante().getNumeroConselho());
		parameters.put("uf", sol.getPrestadorSolicitante().getConselho().getUf());
		parameters.put("dataSolicitacao", sdf.format(sol.getDataCadastro()));
		parameters.put("indicacaoClinica", sol.getCid() == null ? "" : sol.getCid().getDescricao());
		parameters.put("nomeContratante", contratante);
		parameters.put("cnpjContratante", Utils.formatarCNPJ(contratanteCnpj));

		for (int i = 0; i < sol.getItSolicitacoes().size(); i++) {
			parameters.put("procedimentoId" + (i + 1),
					sol.getItSolicitacoes().get(i).getProcedimento().getEspecificacao());
			parameters.put("procedimentoNome" + (i + 1),
					sol.getItSolicitacoes().get(i).getProcedimento().getDescricao().trim());
			parameters.put("qtdSol" + (i + 1), sol.getItSolicitacoes().get(i).getQuantidadeFormatada());

			AutorizacaoProcedimentoExterno autorizacao = autorizacaoProcedimentoInternoRepository
					.findByItSolicitacao(sol.getItSolicitacoes().get(i)).orElse(null);
			if (autorizacao != null) {
				parameters.put("procedimentoAutorizadoId" + (i + 1),
						autorizacao.getItSolicitacao().getProcedimento().getEspecificacao());
				parameters.put("procedimentoAutorizadoNome" + (i + 1),
						autorizacao.getItSolicitacao().getProcedimento().getDescricao().trim());
				parameters.put("qtdAut" + (i + 1), autorizacao.getQuantidadeFormatada());
			} else {
				parameters.put("procedimentoAutorizadoId" + (i + 1), "");
				parameters.put("procedimentoAutorizadoNome" + (i + 1), "");
				parameters.put("qtdAut" + (i + 1), "");
			}

		}

		for (int i = sol.getItSolicitacoes().size(); i < 5; i++) {
			parameters.put("procedimentoId" + (i + 1), "|__|__|__|__|__|__|__|__|");
			parameters.put("procedimentoNome" + (i + 1),
					"_______________________________________________________________________________________________________________________________________________");
			parameters.put("qtdSol" + (i + 1), "|__|__|__|");

			parameters.put("procedimentoAutorizadoId" + (i + 1), "|__|__|__|__|__|__|__|__|");
			parameters.put("procedimentoAutorizadoNome" + (i + 1),
					"__________________________________________________");
			parameters.put("qtdAut" + (i + 1), "|__|__|__|");
		}

		for (int i = 0; i < registros.size(); i++) {
			parameters.put("procedimentoRealizadoId" + (i + 1),
					registros.get(i).getAutorizacao().getItSolicitacao().getProcedimento().getEspecificacao());
			parameters.put("procedimentoRealizadoNome" + (i + 1),
					registros.get(i).getAutorizacao().getItSolicitacao().getProcedimento().getDescricao().trim());
			parameters.put("dtProcedimentoRealizado" + (i + 1),
					Utils.ptOnlyDateToString(registros.get(i).getDataCadastro()));
			parameters.put("horaProcedimentoRealizado" + (i + 1),
					Utils.ptTimeToString(registros.get(i).getDataCadastro()));
			parameters.put("qtdRealizado" + (i + 1), "1");
		}

		for (int i = registros.size(); i < 5; i++) {
			parameters.put("procedimentoRealizadoId" + (i + 1), "|__|__|__|__|__|__|__|__|");
			parameters.put("procedimentoRealizadoNome" + (i + 1), "__________________________________________________");
			parameters.put("dtProcedimentoRealizado" + (i + 1), "__/__/__");
			parameters.put("horaProcedimentoRealizado" + (i + 1), "___:___");
			parameters.put("qtdRealizado" + (i + 1), "|__|__|__|");
		}

		String cidade = "";

		if (auths.size() > 0 && (auths.get(0).getEmpresa().getEndereco() != null)
				&& (auths.get(0).getEmpresa().getEndereco().getCidade() != null)) {
			Optional<Cidade> op = cidadeRepository.findById(auths.get(0).getEmpresa().getEndereco().getCidade());
			if (op.isPresent()) {
				cidade = op.get().getCidade() != null ? op.get().getCidade().toUpperCase() : "";
			}
		}

		parameters.put("observacao", sol.getObservacao() == null ? "" : sol.getObservacao());
		parameters.put("justificativa", sol.getJustificativa() == null ? "" : sol.getJustificativa());
		parameters.put("caraterAtendimento", sol.getCarater().getTipo());
		parameters.put("logradouroContratante",
				auths.size() > 0
						? auths.get(0).getEmpresa().getEndereco() != null
								? auths.get(0).getEmpresa().getEndereco().getDescricaoLogradouro().toString()
								: ""
						: "");
		parameters.put("cidadeContratante", cidade);
		parameters.put("ufContratante",
				auths.size() > 0
						? (auths.get(0).getEmpresa().getEndereco() != null)
								&& (auths.get(0).getEmpresa().getEndereco().getUf()) != null
										? auths.get(0).getEmpresa().getEndereco().getUf().toString()
										: ""
						: "");
		parameters
				.put("telefoneContratante",
						auths.size() > 0
								? auths.get(0).getEmpresa().getTelefone() != null ? Utils.adicionarFormatacaoTelefone(
										auths.get(0).getEmpresa().getTelefone().toString()) : ""
								: "");

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline; filename=guia-exame-interno.pdf;");

		rs.toOutputStream("/jasper/relGuiaExameInterno.jrxml", new ArrayList<SolicitacaoProcedimentoExterno>(),
				parameters, response.getOutputStream());

	}

	public void pdfGuiaInternacao(SolicitacaoProcedimentoExterno sol, HttpServletResponse response)
			throws IOException, Exception {

		String carteirinha = "";
		if (sol.getPaciente().getPessoa().getClass().getSimpleName().equals("PessoaTitular")) {
			PessoaTitular pessoa = (PessoaTitular) sol.getPaciente().getPessoa();
			carteirinha = pessoa.getMatricula().toString() + " - Titular";
		} else {
			PessoaDependente pessoa = (PessoaDependente) sol.getPaciente().getPessoa();
			carteirinha = pessoa.getMatricula().toString() + " - Dependente";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");

		List<AutorizacaoProcedimentoExterno> auths = autorizacaoProcedimentoInternoRepository
				.findByItSolicitacao_solicitacaoProcedimentoExterno(sol);

		String contratante = "";
		String contratanteCnpj = "";
		String dataAutorizacao = "";
		int diariasAutorizadas = 0;
		int diariasSolicitadas = 0;

		if (auths.size() > 0) {
			contratante = auths.get(0).getEmpresa().getRazaoSocial();
			contratanteCnpj = auths.get(0).getEmpresa().getCnpj();
			dataAutorizacao = sdfDate.format(auths.get(0).getDataCadastro());
		}

		GuiaSolicitacaoProcedimentoExternoReportService rs = new GuiaSolicitacaoProcedimentoExternoReportService();

		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("TITULO", "GUIA DE SOLICITAÇÃO DE INTERNAÇÃO");
		parameters.put("numeroGuia", sol.getFormatedId());
		parameters.put("dataAutorizacao", dataAutorizacao);
		parameters.put("dataValidade",
				sol.getDataValidade() == null ? "" : Utils.ptOnlyDateToString(sol.getDataValidade()));
		parameters.put("numeroGuiaOperadora", "");
		parameters.put("numeroCarteira", carteirinha);
		parameters.put("nomeBeneficiario", sol.getPaciente().getPessoa().getNome());
		parameters.put("nomePrestadorSolicitante", sol.getPrestadorSolicitante().getPessoa().getNome());
		parameters.put("conselho", sol.getPrestadorSolicitante().getConselho().getSigla());
		parameters.put("numeroConselho", sol.getPrestadorSolicitante().getNumeroConselho());
		parameters.put("uf", sol.getPrestadorSolicitante().getConselho().getUf());
		parameters.put("dataSolicitacao", sdf.format(sol.getDataCadastro()));
		parameters.put("indicacaoClinica", sol.getCid() == null ? "" : sol.getCid().getDescricao());
		parameters.put("nomeContratante", contratante);
		parameters.put("cnpjContratante", Utils.formatarCNPJ(contratanteCnpj));

		for (int i = 0; i < sol.getItSolicitacoes().size(); i++) {
			parameters.put("procedimentoId" + (i + 1),
					sol.getItSolicitacoes().get(i).getProcedimento().getEspecificacao());
			parameters.put("procedimentoNome" + (i + 1),
					sol.getItSolicitacoes().get(i).getProcedimento().getDescricao().trim());
			parameters.put("qtdSol" + (i + 1), sol.getItSolicitacoes().get(i).getQuantidadeFormatada());
			
			diariasSolicitadas += sol.getItSolicitacoes().get(i).getQuantidade();

			AutorizacaoProcedimentoExterno autorizacao = autorizacaoProcedimentoInternoRepository
					.findByItSolicitacao(sol.getItSolicitacoes().get(i)).orElse(null);
			if (autorizacao != null) {
				parameters.put("procedimentoAutorizadoId" + (i + 1),
						autorizacao.getItSolicitacao().getProcedimento().getEspecificacao());
				parameters.put("procedimentoAutorizadoNome" + (i + 1),
						autorizacao.getItSolicitacao().getProcedimento().getDescricao().trim());
				parameters.put("qtdAut" + (i + 1), autorizacao.getQuantidadeFormatada());
				diariasAutorizadas += autorizacao.getQuantidade();
			} else {
				parameters.put("procedimentoAutorizadoId" + (i + 1), "");
				parameters.put("procedimentoAutorizadoNome" + (i + 1), "");
				parameters.put("qtdAut" + (i + 1), "");
			}

		}

		for (int i = sol.getItSolicitacoes().size(); i < 5; i++) {
			parameters.put("procedimentoId" + (i + 1), "");
			parameters.put("procedimentoNome" + (i + 1), "");
			parameters.put("qtdSol" + (i + 1), "");

			parameters.put("procedimentoAutorizadoId" + (i + 1), "");
			parameters.put("procedimentoAutorizadoNome" + (i + 1), "");
			parameters.put("qtdAut" + (i + 1), "");
		}

		parameters.put("observacao", sol.getObservacao() == null ? "" : sol.getObservacao());
		parameters.put("justificativa", sol.getJustificativa() == null ? "" : sol.getJustificativa());
		parameters.put("caraterAtendimento", "| " + sol.getCarater().getTipo().charAt(0) + " |");
		parameters.put("tipoInternacao", "| " + sol.getNatureza().ordinal() + " |");
		parameters.put("tipoAcomodacao", "| " + (sol.getAcomodacao().ordinal() + 1) + " |");
		parameters.put("dsAcomodacao", sol.getAcomodacao().getTipo());
		parameters.put("regimeInternacao", "| " + (sol.getRegimeInternacao().ordinal() + 1) + " |");
		parameters.put("qtdDiariasSol", "| " + Utils.formatZerosLeft(3, diariasSolicitadas) + " |");
		parameters.put("qtdDiariasAut", "| " + Utils.formatZerosLeft(3, diariasAutorizadas) + " |");
		parameters.put("cdCidPrincipal", sol.getCid().getId());

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline; filename=guia-procedimento-externo.pdf;");

		rs.toOutputStream("/jasper/relGuiaInternacao.jrxml", new ArrayList<SolicitacaoProcedimentoExterno>(),
				parameters, response.getOutputStream());

	}

	@Transactional
	public SolicitacaoProcedimentoExterno savarSolicitacaoAprovacao(
			@Valid SolicitacaoProcedimentoExternoAprovacaoDTO solicitacao) {

//		quantidade mínima de procedimentos por solicitação
		if (solicitacao.getItRealizados().size() == 0) {
			throw new NegocioException("É necessário inserir ao menos um procedimento por solicitação!");
		}

//		limite de procedimentos por solicitação
		if (solicitacao.getItRealizados().size() > 5) {
			throw new NegocioException("Só podem ser cadastrados, no máximo, 5 procedimentos por solicitação!");
		}

		// pega o usuário logado
		UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioRepository.findByLogin(user.getLogin());

		SolicitacaoProcedimentoExterno sol = new SolicitacaoProcedimentoExterno();
		sol.setPaciente(solicitacao.getPaciente());
		sol.setPrestadorSolicitante(solicitacao.getPrestadorSolicitante());
		sol.setCarater(solicitacao.getCarater());
		sol.setClassificacao(solicitacao.getClassificacao());
		sol.setNatureza(solicitacao.getNatureza());
		sol.setRegimeInternacao(solicitacao.getRegimeInterno());
		sol.setAcomodacao(solicitacao.getAcomodacao());
		sol.setOme(solicitacao.getOme());
		sol.setCid(solicitacao.getCid());
		sol.setJustificativa(solicitacao.getJustificativa());
		sol.setObservacao(solicitacao.getObservacao());
		sol.setObservacaoRestrita(solicitacao.getObservacaoRestrita());
		sol.setDataCadastro(new Date());
		sol.setAtivo("S");
		
		if (solicitacao.getSolicitacaoPai() != null) {
			SolicitacaoProcedimentoExterno solPai = solicitacaoProcedimentoExternoRepository.findById(solicitacao.getSolicitacaoPai().getId()).orElseThrow(() -> new NegocioException("Solicitação pai não existe"));
			
			if (solPai.getSolicitacaoPai() != null) {
				throw new NegocioException("Solicitação pai não pode ser filha");
			}
			
			sol.setSolicitacaoPai(solPai);
		}

		boolean algumProcedimentoRecusado = solicitacao.getItRealizados().stream()
				.anyMatch(item -> item.getEmpresa() == null && item.getItSolicitacao().getSituacao().equals("R"));

		boolean algumProcedimentoAutorizado = solicitacao.getItRealizados().stream()
				.anyMatch(item -> item.getEmpresa() != null);

		boolean algumProcedimentoEmEspera = solicitacao.getItRealizados().stream()
				.anyMatch(item -> item.getEmpresa() == null && item.getItSolicitacao().getSituacao().equals("E"));

		sol.setSituacao(algumProcedimentoAutorizado && (algumProcedimentoRecusado || algumProcedimentoEmEspera) ? "P"
				: algumProcedimentoAutorizado ? "A"
						: (algumProcedimentoRecusado && algumProcedimentoEmEspera) ? "S"
								: algumProcedimentoRecusado ? "R" : "S");
		if (algumProcedimentoAutorizado) {
			sol.setDataValidade(Utils.datePlusDays(new Date(), 30));
		}
//			cadastra a solicitação
		sol = solicitacaoProcedimentoExternoRepository.save(sol);

		for (AutorizacaoProcedimentoExternoDTO it : solicitacao.getItRealizados()) {
			ItSolicitacaoProcedimentoExterno itSol = new ItSolicitacaoProcedimentoExterno();
			itSol.setDataCadastro(new Date());
			itSol.setQuantidade(it.getItSolicitacao().getQuantidade());
			itSol.setProcedimento(it.getItSolicitacao().getProcedimento());
			itSol.setSolicitacaoProcedimentoExterno(sol);
			itSol.setSituacao(
					it.getEmpresa() != null ? "A" : it.getItSolicitacao().getSituacao().equals("R") ? "R" : "E");
			itSol.setSolicitacaoProcedimentoExterno(sol);

//				cadastra o item de solicitação
			itSol = itSolicitacaoRepository.save(itSol);

//				se existir empresa para o item, cadastra a autorização
			if (it.getEmpresa() != null) {
				AutorizacaoProcedimentoExterno aut = new AutorizacaoProcedimentoExterno();
				aut.setEmpresa(it.getEmpresa());
				aut.setItSolicitacao(itSol);
				aut.setQuantidade(it.getQuantidadeAutorizada());
				aut.setDataCadastro(new Date());
				aut.setUsuarioAutorizacao(usuario);
				aut.setSituacao("A");
				autorizacaoProcedimentoInternoRepository.save(aut);
			}

		}

		return sol;

	}

}
