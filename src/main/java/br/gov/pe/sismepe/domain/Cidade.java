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
@Table(name = "cidade")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 365031256864508306L;
	
	@Id
	@Column(name = "CD_CIDADE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "DS_CIDADE")
	private String cidade;

	@ManyToOne
	@JoinColumn(name="CD_UF")
	private Uf uf;
	
	@Column(name = "SN_ATIVO")
	private String ativo;

	public Cidade() {
	}

	public Cidade(Long id, String cidae, Uf uf, String ativo) {
		super();
		this.id = id;
		this.cidade = cidae;
		this.uf = uf;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidae) {
		this.cidade = cidae;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
}
