package br.gov.pe.sismepe.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable{
	
	private static final long serialVersionUID = -9175755623145103553L;
	private String nome;
	private String login;
	private String senha;
	
	public CredenciaisDTO() {
		
	}

	public CredenciaisDTO(String nome, String login, String senha) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}