package br.gov.pe.sismepe.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EstatisticaAtendimentoDTO {
	
	@JsonFormat(pattern = "dd/MM")
	private Date data;
	private int agendados;
	private int realizados;
	
	public EstatisticaAtendimentoDTO() {}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getAgendados() {
		return agendados;
	}

	public void setAgendados(int agendados) {
		this.agendados = agendados;
	}

	public int getRealizados() {
		return realizados;
	}

	public void setRealizados(int realizados) {
		this.realizados = realizados;
	}

	
	
	

}
