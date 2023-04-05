package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PACIENTE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Paciente implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_PACIENTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "CD_PESSOA")
	@JsonIgnoreProperties(value = {"usuarioCadastro"})
	private Pessoa pessoa;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	public Paciente() {
		
	}
	
	public Paciente(Long id) {
		super();
		this.id = id;
	}
	
	public Paciente(Long id, Pessoa pessoa, String ativo) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", pessoa=" + pessoa + ", ativo=" + ativo + "]";
	}
	
	
	
}