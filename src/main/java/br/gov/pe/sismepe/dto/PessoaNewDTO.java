package br.gov.pe.sismepe.dto;

import java.io.Serializable;

import br.gov.pe.sismepe.domain.Pessoa;

public class PessoaNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String nome;
	
	private String cpf;
	
	public PessoaNewDTO() {
	}
	
	public PessoaNewDTO(Pessoa obj) {
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
