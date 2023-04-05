package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UNIDADE_INTERNACAO")
public class UnidadeInternacao implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_UNIDADE_INTERNACAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "DS_UNIDADE_INTERNACAO")
	private String descricao;
	
	@Column(name = "CD_CATEGORIA")
	private String categortia;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	@Column(name = "NR_COR_HEX")
	private String cor;
	
	@Column(name = "SN_COVID")
	private String covid;
	
	@Column(name = "CD_SETOR")
	private String codigoSetor;
	
	public UnidadeInternacao() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getCategortia() {
		return categortia;
	}

	public void setCategortia(String categortia) {
		this.categortia = categortia;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getCovid() {
		return covid;
	}

	public void setCovid(String covid) {
		this.covid = covid;
	}

	public String getCodigoSetor() {
		return codigoSetor;
	}

	public void setCodigoSetor(String codigoSetor) {
		this.codigoSetor = codigoSetor;
	}
}