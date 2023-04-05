package br.gov.pe.sismepe.dto;

import br.gov.pe.sismepe.domain.PessoaTitular;

public class PessoaTitularDTO {
	
	private Long pessoaId;
	private Integer usuarioId;
	private String nome;
	private Integer matricula;
	private Integer digito;
	private Integer corporacao;
	private Long omeId;
	private String ome;
	
	public PessoaTitularDTO(PessoaTitular pessoa) {
		this.pessoaId = pessoa.getId();
		this.nome = pessoa.getNome();
		this.matricula = pessoa.getMatricula();
		this.digito = pessoa.getDigito();
		this.corporacao = pessoa.getCorporacao();
		this.omeId = pessoa.getOme() != null ? pessoa.getOme().getId() : null;
		this.ome = pessoa.getOme() != null ? pessoa.getOme().getAbreviacao() : null;
	}
	
	public PessoaTitularDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public Integer getDigito() {
		return digito;
	}
	public void setDigito(Integer digito) {
		this.digito = digito;
	}
	public Integer getCorporacao() {
		return corporacao;
	}
	public void setCorporacao(Integer corporacao) {
		this.corporacao = corporacao;
	}
	public Long getOmeId() {
		return omeId;
	}
	public void setOmeId(Long omeId) {
		this.omeId = omeId;
	}
	public String getOme() {
		return ome;
	}
	public void setOme(String ome) {
		this.ome = ome;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
	

}
