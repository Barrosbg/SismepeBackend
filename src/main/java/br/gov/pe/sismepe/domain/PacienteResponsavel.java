package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "PACIENTE_RESPONSAVEL")
public class PacienteResponsavel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PacienteResponsavelPK id = new PacienteResponsavelPK();
	
	@OneToOne
	@JoinColumn(name = "CD_PARENTESCO")
	private Parentesco parentesco;
	
	@ManyToOne
	@JoinColumn(name = "CD_PACIENTE", insertable = false, updatable = false)
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "CD_PESSOA_RESPONSAVEL", insertable = false, updatable = false)
	private Pessoa responsavel;
	
	@Column(name = "NR_TELEFONE")
	private String telefone;
	
	@Column(name = "DT_INCL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInc;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	public PacienteResponsavel() {
	}
	
	public PacienteResponsavel(PacienteResponsavelPK id, Parentesco parentesco, 
			String telefone, Date dataInc, String ativo) {
		super();
		this.id = id;
		this.parentesco = parentesco;
		this.telefone = telefone;
		this.dataInc = dataInc;
		this.ativo = ativo;
	}

	public PacienteResponsavelPK getId() {
		return id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}

	public void setId(PacienteResponsavelPK id) {
		this.id = id;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataInc() {
		return dataInc;
	}

	public void setDataInc(Date dataInc) {
		this.dataInc = dataInc;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacienteResponsavel other = (PacienteResponsavel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}