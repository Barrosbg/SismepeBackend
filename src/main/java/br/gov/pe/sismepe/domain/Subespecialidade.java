package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBESPECIALIDADE")
public class Subespecialidade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CD_SUBESPECIALIDADE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DS_SUBESPECIALIDADE")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "CD_ESPECIALIDADE")
	private Especialidade especialidade;

	@Column(name = "SN_ATIVO")
	private String ativo;
	
	public Subespecialidade() {
	}

	public Subespecialidade(Long id, String descricao, Especialidade especialidade, String ativo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.especialidade = especialidade;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
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
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
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
		Subespecialidade other = (Subespecialidade) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		return true;
	}
	
}
