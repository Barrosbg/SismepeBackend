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
@Table(name = "subgrupo_procedimento_sismepe")
public class SubgrupoProcedimentoSismepe implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_SUBGRUPO_PROCEDIMENTO_SISMEPE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CD_GRUPO_PROCEDIMENTO_SISMEPE")
	private GrupoProcedimentoSismepe grupo;
	
	@Column(name = "DS_SUBGRUPO_PROCEDIMENTO_SISMEPE")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GrupoProcedimentoSismepe getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoProcedimentoSismepe grupo) {
		this.grupo = grupo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "SubgrupoProcedimentoSismepe [id=" + id + ", grupo=" + grupo + ", descricao=" + descricao + "]";
	}
	
	
	
}