package br.gov.pe.sismepe.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.AgendaCentral;
import br.gov.pe.sismepe.domain.AgendaCentralAmb;
import br.gov.pe.sismepe.domain.Atendimento;
import br.gov.pe.sismepe.domain.ItAgendaCentral;
import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.domain.enums.SituacaoAtendimento;
import br.gov.pe.sismepe.domain.enums.SituacaoItAgendaCentral;
import br.gov.pe.sismepe.domain.enums.TipoAtendimentoEnum;
import br.gov.pe.sismepe.dto.EstatisticaAtendimentoDTO;
import br.gov.pe.sismepe.dto.ItAgendaCentralDTO;
import br.gov.pe.sismepe.dto.ItAgendaCentralDisponivelDTO;
import br.gov.pe.sismepe.repositories.AgendaCentralRepository;
import br.gov.pe.sismepe.repositories.AtendimentoRepository;
import br.gov.pe.sismepe.repositories.EstatisticaAtendimentoRepository;
import br.gov.pe.sismepe.repositories.ItAgendaCentralRepository;
import br.gov.pe.sismepe.repositories.PacienteRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import br.gov.pe.sismepe.util.Utils;

@Service
public class ItAgendaCentralService {

	@Autowired
	ItAgendaCentralRepository repo;

	@Autowired
	AgendaCentralRepository agendaCentralRepo;

	@Autowired
	PacienteRepository pacienteRepo;

	@Autowired
	UsuarioRepository usuarioRepo;

	@Autowired
	AtendimentoRepository atendimentoRepo;
	
	@Autowired
	EstatisticaAtendimentoRepository estatisticaAtendRepo;
		
	public ItAgendaCentral find(Long id) {
		Optional<ItAgendaCentral> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("ItAgendaCentral não encontrato! ID: " + id));
	}

	public Page<ItAgendaCentral> findAllByFiltro(Long especialidadeId, Long prestadorId, Long pacienteId,
			String dataAgendamento, String tipoAgenda, String situacaoAgendamento, String situacaoAtendimento,
			Integer page, Integer linesPerPage, String orderBy, String directions) throws ParseException {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		
		
		List<AgendaCentral> listaAgendaCentral = new ArrayList<AgendaCentral>();
		if (especialidadeId != null || prestadorId != null) {
			List<AgendaCentralAmb> lista = especialidadeId != null
					? prestadorId != null
							? agendaCentralRepo.findAmbsByEspecialidade_idAndPrestador_id(especialidadeId,
									prestadorId)
							: agendaCentralRepo.findAmbsByEspecialidade_id(especialidadeId)
					: agendaCentralRepo.findAmbsByAndPrestador_id(prestadorId);
			if(lista.isEmpty()) {
				List<ItAgendaCentral> listaVazia = new ArrayList<ItAgendaCentral>();
				Page<ItAgendaCentral> pageRetorno = new PageImpl<ItAgendaCentral>(listaVazia,pageRequest,listaVazia.size());
				return pageRetorno;
			}
			listaAgendaCentral = lista.stream().map(obj -> new AgendaCentral(obj.getId()))
					.collect(Collectors.toList());			
		}
		
		//Não tem situação atendimento
		if (situacaoAtendimento.equals("")) {
			//Tem situação de agendamento
			if (!situacaoAgendamento.equals("")) {
				String[] items = situacaoAgendamento.split(",");
				List<String> situacaoList = new ArrayList<String>();
				for (String item : items) {
					situacaoList.add(item);
				}

				//Tem data
				if (Utils.isStringNaoNulaNaoVazia(dataAgendamento)) {
					Utils util = new Utils();
					Date data = util.getDate(dataAgendamento);
					
					//Tem prestador ou especialidade
					if(!listaAgendaCentral.isEmpty()) {
						return pacienteId != null ? repo
								.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimentoIsNull(
										"S", situacaoList, "S", "N", pacienteId, listaAgendaCentral, data, pageRequest)
								: repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimentoIsNull(
										"S", situacaoList, "S", "N", listaAgendaCentral, data, pageRequest);
					}
					//Não tem prestador nem especialidade
					return pacienteId != null ? repo
							.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_dataAgendamentoAndAtendimentoIsNull(
									"S", situacaoList, "S", "N", pacienteId, data, pageRequest)
							: repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_dataAgendamentoAndAtendimentoIsNull(
									"S", situacaoList, "S", "N", data, pageRequest);
				} 
				
				//Não tem data
				//Tem prestador ou especialidade
				if(!listaAgendaCentral.isEmpty()) {
					return pacienteId != null ? repo
							.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAtendimentoIsNull(
									"S", situacaoList, "S", "N", pacienteId, listaAgendaCentral, pageRequest)
							: repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAtendimentoIsNull(
									"S", situacaoList, "S", "N", listaAgendaCentral, pageRequest);
				}
				//Não tem prestador nem especialidade
				return pacienteId != null ? repo
						.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_tipoAndAtendimentoIsNull(
								"S", situacaoList, "S", "N", pacienteId, tipoAgenda, pageRequest)
						: repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_tipoAndAtendimentoIsNull(
								"S", situacaoList, "S", "N", tipoAgenda, pageRequest);
			}
			
			//Não tem situação de agendamento
			//Tem data
			if (Utils.isStringNaoNulaNaoVazia(dataAgendamento)) {
				Utils util = new Utils();
				Date data = util.getDate(dataAgendamento);
				
				//Tem prestador ou especialidade
				if(!listaAgendaCentral.isEmpty()) {
					return pacienteId != null ? repo
							.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAgendaCentral_dataAgendamento(
									"S", "S", "N", pacienteId, listaAgendaCentral, data, pageRequest)
							: repo.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAgendaCentral_dataAgendamento(
									"S", "S", "N", listaAgendaCentral, data, pageRequest);
				}
				//Não tem prestador nem especialidade
				return pacienteId != null ? repo
						.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_dataAgendamento(
								"S", "S", "N", pacienteId, data, pageRequest)
						: repo.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_dataAgendamento(
								"S", "S", "N", data, pageRequest);
			} 
			
			//Não tem data
			//Tem prestador ou especialidade
			if(!listaAgendaCentral.isEmpty()) {
				return pacienteId != null ? repo
						.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralIn(
								"S", "S", "N", pacienteId, listaAgendaCentral, pageRequest)
						: repo.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralIn(
								"S", "S", "N", listaAgendaCentral, pageRequest);
			}
			//Não tem prestador nem especialidade
			return pacienteId != null ? repo
					.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_tipo(
							"S", "S", "N", pacienteId, tipoAgenda, pageRequest)
					: repo.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_tipo(
							"S", "S", "N", tipoAgenda, pageRequest);
		}
			
		//Tem situação atendimento
		String[] itemsSitAtend = situacaoAtendimento.split(",");
		List<Integer> situacaoAtendList = new ArrayList<Integer>();
		for (String item : itemsSitAtend) {
			situacaoAtendList.add(Integer.parseInt(item));
		}
		
		//Não tem situação de agendamento
		if (situacaoAgendamento.equals("")) {
			//Tem prestador ou especialidade
			if(!listaAgendaCentral.isEmpty()) {
				//Tem data
				if (Utils.isStringNaoNulaNaoVazia(dataAgendamento)) {
					Utils util = new Utils();
					Date data = util.getDate(dataAgendamento);
					return pacienteId != null ? repo
							.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(
									"S", "S", "N", pacienteId, listaAgendaCentral, data, situacaoAtendList, pageRequest)
							: repo.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(
									"S", "S", "N", listaAgendaCentral, data, situacaoAtendList, pageRequest);
				}
				//Não tem data
				return pacienteId != null ? repo
						.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAtendimento_situacaoIn(
								"S", "S", "N", pacienteId, listaAgendaCentral, situacaoAtendList, pageRequest)
						: repo.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAtendimento_situacaoIn(
								"S", "S", "N", listaAgendaCentral, situacaoAtendList, pageRequest);
			}
			
			//Não tem prestador nem especialidade
			//Tem data
			if (Utils.isStringNaoNulaNaoVazia(dataAgendamento)) {
				Utils util = new Utils();
				Date data = util.getDate(dataAgendamento);
				return pacienteId != null ? repo
						.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(
								"S", "S", "N", pacienteId, data, situacaoAtendList, pageRequest)
						: repo.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(
								"S", "S", "N", data, situacaoAtendList, pageRequest);
			}
			//Não tem data
			return pacienteId != null ? repo
					.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAtendimento_situacaoIn(
							"S", "S", "N", pacienteId, situacaoAtendList, pageRequest)
					: repo.findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAtendimento_situacaoIn(
							"S", "S", "N", situacaoAtendList, pageRequest);
		}
		
		//Tem situação de agendamento
		String[] items = situacaoAgendamento.split(",");
		List<String> situacaoList = new ArrayList<String>();
		for (String item : items) {
			situacaoList.add(item);
		}
		List<ItAgendaCentral> lista;
		//Tem prestador ou especialidade
		if(!listaAgendaCentral.isEmpty()) {
			//Tem data
			if (Utils.isStringNaoNulaNaoVazia(dataAgendamento)) {
				Utils util = new Utils();
				Date data = util.getDate(dataAgendamento);
				lista = pacienteId != null ? 
						repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(
								"S", situacaoList, "S", "N", pacienteId, listaAgendaCentral, data, situacaoAtendList)
						: repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(
								"S", situacaoList, "S", "N", listaAgendaCentral, data, situacaoAtendList);
			}else {
				//Não tem data
				lista = pacienteId != null ? 
						repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAtendimento_situacaoIn(
								"S", situacaoList, "S", "N", pacienteId, listaAgendaCentral, situacaoAtendList)
						: repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAtendimento_situacaoIn(
								"S", situacaoList, "S", "N", listaAgendaCentral, situacaoAtendList);
			}
		}else {
			//Não tem prestador nem especialidade
			//Tem data
			if (Utils.isStringNaoNulaNaoVazia(dataAgendamento)) {
				Utils util = new Utils();
				Date data = util.getDate(dataAgendamento);
				lista = pacienteId != null ? 
						repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(
								"S", situacaoList, "S", "N", pacienteId, data, situacaoAtendList)
						: repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(
								"S", situacaoList, "S", "N", data, situacaoAtendList);
			}else {
				//Não tem data
				lista = pacienteId != null ? 
						repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAtendimento_situacaoIn(
								"S", situacaoList, "S", "N", pacienteId, situacaoAtendList)
						: repo.findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAtendimento_situacaoIn(
								"S", situacaoList, "S", "N", situacaoAtendList);
			}
		}
		
		return new PageImpl<ItAgendaCentral>(lista,pageRequest,lista.size());
	
	}

	public Page<ItAgendaCentralDisponivelDTO> buscarItensLivres(Long especialidadeId, Long prestadorId, Integer page,
			Integer linesPerPage, String orderBy, String directions) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);

		List<AgendaCentralAmb> lista = agendaCentralRepo.findAmbsByEspecialidade_idAndPrestador_id(especialidadeId,
				prestadorId);
		List<AgendaCentral> listaAgendaCentral = lista.stream().map(obj -> new AgendaCentral(obj.getId()))
				.collect(Collectors.toList());
		Date hoje = new Date();		
		Date yesterday = new Date(hoje.getTime() - (1000*60*60*24));
		Page<ItAgendaCentral> listaItens = repo
				.findByAtivoAndSituacaoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAgendaCentral_dataAgendamentoGreaterThanEqual(
						"S", "L", "S", "N", listaAgendaCentral, yesterday, pageRequest);
		Page<ItAgendaCentralDisponivelDTO> listaItensDisponiveis = listaItens.map(obj -> {
			if (lista.contains(obj.getAgendaCentral())) {
				lista.remove(obj.getAgendaCentral());
				return new ItAgendaCentralDisponivelDTO(obj);
			}
			return null;
		});

		List<ItAgendaCentralDisponivelDTO> retorno = listaItensDisponiveis.stream().filter(obj -> (obj != null))
				.collect(Collectors.toList());
		Page<ItAgendaCentralDisponivelDTO> pageRetorno = new PageImpl<ItAgendaCentralDisponivelDTO>(retorno,
				pageRequest, retorno.size());

		return pageRetorno;
	}

	@Transactional
	public ItAgendaCentral update(ItAgendaCentral obj, Integer situacaoAtendimento) {
		Integer ORIGEM_ATEND_SERVICO_SOCIAL = 69;
		Optional<ItAgendaCentral> oldObj = repo.findById(obj.getId());

		UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuarioLogado = usuarioRepo.findByLogin(userSS.getLogin());
		Date horaAlteracao = new Date();

		if (situacaoAtendimento == null) {
			switch (obj.getSituacao()) {
			case CANCELADO:
				ItAgendaCentral novoItem = new ItAgendaCentral(oldObj.get());
				novoItem.setPaciente(null);
				novoItem.setSituacao(SituacaoItAgendaCentral.LIVRE);
				novoItem.setUsuarioAlt(usuarioLogado);
				novoItem.setDataInc(horaAlteracao);
				novoItem.setDataAlt(horaAlteracao);
				repo.save(novoItem);
				oldObj.get().setDataCancelamento(horaAlteracao);
				oldObj.get().setUsuarioCanc(usuarioLogado);
				break;
			case AGENDADO:
				oldObj.get().setPaciente(obj.getPaciente());
				oldObj.get().setUsuarioAgend(usuarioLogado);
				oldObj.get().setDataAgendamento(horaAlteracao);
				break;
			default:
				break;
			}
			oldObj.get().setSituacao(obj.getSituacao());
		} else {
			switch (SituacaoAtendimento.toEnum(situacaoAtendimento)) {
			case ABERTO:
				AgendaCentralAmb agenda = (AgendaCentralAmb) oldObj.get().getAgendaCentral();
				Atendimento atendimento = new Atendimento(TipoAtendimentoEnum.AMBULATORIAL, oldObj.get().getPaciente(),
						SituacaoAtendimento.toEnum(situacaoAtendimento), ORIGEM_ATEND_SERVICO_SOCIAL,
						agenda.getPrestador(), agenda.getEspecialidade(), usuarioLogado);
				atendimento = atendimentoRepo.save(atendimento);
				oldObj.get().setAtendimento(atendimento);
				break;
			case CANCELADO:
				Optional<Atendimento> oldAtendimento = atendimentoRepo.findById(oldObj.get().getAtendimento().getId());
				oldAtendimento.get().setSituacao(SituacaoAtendimento.toEnum(situacaoAtendimento));
				atendimentoRepo.save(oldAtendimento.get());
				oldObj.get().setAtendimento(null);
				break;
			default:
				break;
			}
		}
		oldObj.get().setDataAlt(horaAlteracao);
		oldObj.get().setUsuarioAlt(usuarioLogado);
		return repo.save(oldObj.get());
	}

	@Transactional
	public ItAgendaCentral insert(ItAgendaCentral obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public ItAgendaCentral fromDTO(ItAgendaCentralDTO objDTO) {
		SituacaoItAgendaCentral situacao = (objDTO.getSituacaoAgendamento() == null) ? null
				: SituacaoItAgendaCentral.toEnum(objDTO.getSituacaoAgendamento());
		return new ItAgendaCentral(objDTO.getId(), situacao, new Paciente(objDTO.getIdPaciente()));
	}
	
	public List<EstatisticaAtendimentoDTO> pesquisarEstatisticasDeAtendimento(Long prestadorId, Long pacienteId, String periodo) {
		String periodoInicial = "";
		String periodoFinal = "";
		
		switch (periodo) {
			case "semanal":
				periodoInicial = Utils.firstDayOfWeek() + " 00:00:00";
				periodoFinal = Utils.lastDayOfWeek() + " 23:59:59";
				break;	
			case "mensal":
				periodoInicial = Utils.firstDayOfMonth() + " 00:00:00";
				periodoFinal = Utils.lastDayOfMonth() + " 23:59:59";
				break;	
			case "anual":
				periodoInicial = Utils.firstDayOfYear() + " 00:00:00";
				periodoFinal = Utils.lastDayOfYear() + " 23:59:59";
				break;	
			default:
				break;
		}
		
		
		if(prestadorId != null) {
			return estatisticaAtendRepo.pesquisarEstatisticasDeAtendimentoPrestador(prestadorId, periodoInicial, periodoFinal);
		}

		if(pacienteId != null) {
			return estatisticaAtendRepo.pesquisarEstatisticasDeAtendimentoPaciente(pacienteId, periodoInicial, periodoFinal);
		}
		
		return null;
	}
	
}
