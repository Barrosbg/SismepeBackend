package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ATENDIMENTO_INT")
public class AtendimentoInternacao extends Atendimento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "CD_MOTIVO_ALTA")
	private MotivoAlta motivoAlta;
	
	
//	  `CD_LEITO` int(11) NOT NULL COMMENT 'Código de identificação do leito onde o paciente será internado. Apenas para atendimentos do tipo "I" (internação).',
//	  `CD_PRESTADOR_RESP_ATEND` int(11) DEFAULT NULL COMMENT 'Prestador responsavel pelo paciente.',
//	  `CD_MOTIVO_ALTA` int(11) DEFAULT NULL COMMENT 'Motivo da alta.',
//	  `CD_PRESTADOR_ALTA` int(11) DEFAULT NULL COMMENT 'Prestador que está realizando a alta. ',
//	  `OBS_ALTA` text COMMENT 'Observações da alta.',
//	  `CD_PEP_SOLICITACAO_INTERNACAO` int(11) DEFAULT NULL COMMENT 
	
	public AtendimentoInternacao() {
		
	}

	public MotivoAlta getMotivoAlta() {
		return motivoAlta;
	}

	public void setMotivoAlta(MotivoAlta motivoAlta) {
		this.motivoAlta = motivoAlta;
	}
}