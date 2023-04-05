package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.sql.Date;

public class RecadastramentoTitularDTO implements Serializable {
	private static final long serialVersionUID = 2264323080037304423L;
	
	private Long cdPessoa;
	private Integer matricula;
	private String cep;
	private String complemento;
	private String cpf;
	private Date dataNascimento;
	private Date dataValidadePlano;
	private String email;
	private String genero;
	private String logradouro;
	private Long numero;
	private String numeroTelefone;
	private String numeroWhatsapp;
	private Long uf;
	private Long cidade;
	private String nomeMae;
	
	public RecadastramentoTitularDTO(Long cdPessoa, String cep, String complemento, String cpf, Date dataNascimento,
			String email, String genero, String logradouro, Long numero, String numeroTelefone,
			String numeroWhatsapp, Long uf, Long cidade, String nomeMae) {
		super();
		this.cdPessoa = cdPessoa;
		this.cep = cep;
		this.complemento = complemento;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.genero = genero;
		this.logradouro = logradouro;
		this.numero = numero;
		this.numeroTelefone = numeroTelefone;
		this.numeroWhatsapp = numeroWhatsapp;
		this.uf = uf;
		this.cidade = cidade;
		this.nomeMae = nomeMae;
	}
	
	public RecadastramentoTitularDTO() {
	}
	
	public Long getCdPessoa() {
		return cdPessoa;
	}
	public void setCdPessoa(Long cdPessoa) {
		this.cdPessoa = cdPessoa;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
	public String getNumeroWhatsapp() {
		return numeroWhatsapp;
	}
	public void setNumeroWhatsapp(String numeroWhatsapp) {
		this.numeroWhatsapp = numeroWhatsapp;
	}
	public Long getUf() {
		return uf;
	}
	public void setUf(Long uf) {
		this.uf = uf;
	}
	public Long getCidade() {
		return cidade;
	}
	public void setCidade(Long cidade) {
		this.cidade = cidade;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "RecadastramentoTitularDTO [cdPessoa=" + cdPessoa + ", cep=" + cep + ", complemento=" + complemento
				+ ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", email=" + email + ", genero=" + genero
				+ ", logradouro=" + logradouro + ", numero=" + numero
				+ ", numeroTelefone=" + numeroTelefone + ", numeroWhatsapp=" + numeroWhatsapp + ", uf=" + uf
				+ ", cidade=" + cidade + ", nomeMae=" + nomeMae + "]";
	}
}
