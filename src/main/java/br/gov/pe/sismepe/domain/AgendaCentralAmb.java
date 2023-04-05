package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "AGENDA_CENTRAL_AMB")
@JsonTypeName("AGENDA_CENTRAL_AMB") 
public class AgendaCentralAmb extends AgendaCentral implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@OrderBy("id DESC")
	@JoinColumn(name = "CD_PRESTADOR")
	private Prestador prestador;
	
	@ManyToOne
	@JoinColumn(name = "CD_ESPECIALIDADE")
	private Especialidade especialidade;
	
	@ManyToOne
	@JoinColumn(name = "CD_SUBESPECIALIDADE")
	private Subespecialidade subespecialidade;
	
	public AgendaCentralAmb() {
	}

	public AgendaCentralAmb(Prestador prestador, Especialidade especialidade, Subespecialidade subespecialidade) {
		super();
		this.prestador = prestador;
		this.especialidade = especialidade;
		this.subespecialidade = subespecialidade;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Subespecialidade getSubespecialidade() {
		return subespecialidade;
	}

	public void setSubespecialidade(Subespecialidade subespecialidade) {
		this.subespecialidade = subespecialidade;
	}
		
}
