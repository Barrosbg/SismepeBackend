package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "parentesco")
public class Parentesco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CD_PARENTESCO")
	private Long id;
	
	@Column(name = "DS_PARENTESCO")
	private String descricao;
	
	@Column(name = "SN_ATIVO")
	private String situacao;
	
	public Parentesco() {}
	
	public Parentesco(Long id) {
		super();
		this.id = id;
	}

	public Parentesco(String descricao, String situacao) {
		this.descricao = descricao;
		this.situacao = situacao;
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

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Parentesco [id=" + id + ", descricao=" + descricao + ", situacao=" + situacao + "]";
	}
	
	
	
	
	
}
