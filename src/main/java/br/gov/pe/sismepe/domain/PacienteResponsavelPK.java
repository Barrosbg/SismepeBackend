package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PacienteResponsavelPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "CD_PACIENTE")
	private Long paciente;
	
	@Column(name = "CD_PESSOA_RESPONSAVEL")
	private Long responsavel;
	
	public PacienteResponsavelPK() {
		super();
	}

	public PacienteResponsavelPK(Long paciente, Long responsavel) {
		super();
		this.paciente = paciente;
		this.responsavel = responsavel;
	}

	public Long getPaciente() {
		return paciente;
	}

	public void setPaciente(Long paciente) {
		this.paciente = paciente;
	}

	public Long getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Long responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
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
		PacienteResponsavelPK other = (PacienteResponsavelPK) obj;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		return true;
	}
		
}