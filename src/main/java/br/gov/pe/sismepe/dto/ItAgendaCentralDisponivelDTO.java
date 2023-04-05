package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.ItAgendaCentral;

public class ItAgendaCentralDisponivelDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataAgendamento;
	
	@JsonFormat(pattern = "HH:mm:ss")
	private Date horaInicial;
	
	public ItAgendaCentralDisponivelDTO() {
	}
	
	public ItAgendaCentralDisponivelDTO(ItAgendaCentral obj) {
		this.id = obj.getId();
		this.dataAgendamento = obj.getAgendaCentral().getDataAgendamento();
		this.horaInicial = obj.getAgendaCentral().getHoraInicial();		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Date getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}
	
}
