package br.gov.pe.sismepe.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.gov.pe.sismepe.domain.AssistenciaSocial;
import br.gov.pe.sismepe.domain.Atendimento;
import br.gov.pe.sismepe.domain.ItAgendaCentral;
import br.gov.pe.sismepe.domain.enums.SituacaoAtendimento;
import br.gov.pe.sismepe.domain.enums.SituacaoItAgendaCentral;
import br.gov.pe.sismepe.dto.ItAgendaCentralDTO;
import br.gov.pe.sismepe.repositories.AssistenciaSocialRepository;
import br.gov.pe.sismepe.repositories.ItAgendaCentralRepository;
import br.gov.pe.sismepe.resources.exceptions.FieldMessage;

public class ItAgendaCentralUpdateValidator implements ConstraintValidator<ItAgendaCentralUpdate, ItAgendaCentralDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ItAgendaCentralRepository repo;
	
	@Autowired
	private AssistenciaSocialRepository assistenciaSocialRepo;

	@Override
	public void initialize(ItAgendaCentralUpdate ann) {
	}

	@Override
	public boolean isValid(ItAgendaCentralDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();
		Optional<ItAgendaCentral> itAgendaCentralAtual = repo.findById(uriId);
		
		if(objDto.getSituacaoAgendamento()!=null) {
			switch (objDto.getSituacaoAgendamento()) {
			case "A":
				if (itAgendaCentralAtual.get().getSituacao().equals(SituacaoItAgendaCentral.LIVRE)) {
					List<ItAgendaCentral> listaItAgendaCentral = repo.findByIdNotAndPaciente_idAndHoraInicial(uriId,
							objDto.getIdPaciente(), itAgendaCentralAtual.get().getHoraInicial());
					if (!listaItAgendaCentral.isEmpty())
						list.add(new FieldMessage("idPaciente", "Paciente com consulta para o mesmo dia e mesmo horário"));
				} else {
					list.add(new FieldMessage("idPaciente", "Só é permitido agendar itens disponíveis!"));
				}
				break;
			case "C":
				if (!itAgendaCentralAtual.get().getSituacao().equals(SituacaoItAgendaCentral.AGENDADO)) {
					list.add(new FieldMessage("idPaciente", "Só é permitido cancelar itens agendados!"));
				}
				break;
			default:
				list.add(new FieldMessage("idPaciente", "Situação do item não identificado!"));
				break;
			}
		}else if(objDto.getSituacaoAtendimento()!= null) {
			switch (objDto.getSituacaoAtendimento()) {
			case 1:
				if (!itAgendaCentralAtual.get().getSituacao().equals(SituacaoItAgendaCentral.AGENDADO)) {
					list.add(new FieldMessage("idPaciente", "Só é permitido abrir o atendimento de itens agendados!"));
				}
				
				if(itAgendaCentralAtual.get().getAtendimento() != null) {
					if (!itAgendaCentralAtual.get().getAtendimento().getSituacao().equals(SituacaoAtendimento.EM_ATENDIMENTO)) {
						list.add(new FieldMessage("idPaciente", "Só é permitido abrir o atendimento de itens agendados!"));
					}
					List<AssistenciaSocial> listAss = assistenciaSocialRepo.findByAtendimentoAndAtivo(new Atendimento(itAgendaCentralAtual.get().getAtendimento().getId()),"S");
					if (!listAss.isEmpty()) {
						list.add(new FieldMessage("idPaciente", "Há Assistencia Social criada nesse atendimento, exclua antes de modificar o atendimento!"));
					}
				}
				
				break;
			case 3:
				if (itAgendaCentralAtual.get().getAtendimento()!=null && !itAgendaCentralAtual.get().getAtendimento().getSituacao().equals(SituacaoAtendimento.ABERTO)) {
					list.add(new FieldMessage("idPaciente", "Só é permitido cancelar o atendimento de itens abertos!"));
				}
				break;
			default:
				list.add(new FieldMessage("idPaciente", "Situação do item não identificado!"));
				break;
			}
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}