package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupo_procedimento_sismepe")
public class GrupoProcedimentoSismepe implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_GRUPO_PROCEDIMENTO_SISMEPE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DS_GRUPO_PROCEDIMENTO_SISMEPE")
	private String descricao;

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

	@Override
	public String toString() {
		return "GrupoProcedimentoSismepe [id=" + id + ", descricao=" + descricao + "]";
	}

	
	
}
