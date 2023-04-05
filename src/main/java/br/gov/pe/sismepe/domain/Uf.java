package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "uf_j")
public class Uf implements Serializable {
	private static final long serialVersionUID = -8841394975352343912L;

	@Id
	@Column(name = "CD_UF", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "NM_NOME")
	private String uf;
	
	@Column(name = "NM_SIGLA")
	private String sigla;
	
	@Column(name = "BL_ATIVO")
	private Integer ativo;
	
	@JsonBackReference
	@OneToMany(mappedBy = "uf")
	private Set<Cidade> cidades;

	public Uf() {
	}

	public Uf(Long id, String uf, String sigla, Integer ativo) {
		super();
		this.id = id;
		this.uf = uf;
		this.ativo = ativo;
		this.sigla = sigla;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Set<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(Set<Cidade> cidades) {
		this.cidades = cidades;
	}
	
}
