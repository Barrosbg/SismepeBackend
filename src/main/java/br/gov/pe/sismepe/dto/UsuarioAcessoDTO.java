package br.gov.pe.sismepe.dto;

import br.gov.pe.sismepe.services.validation.UsuarioAcessoUpdate;

@UsuarioAcessoUpdate
public class UsuarioAcessoDTO {
	
	private String senhaAtual;
	private String novaSenha;
	private String email;
	private String login;
	private String cpf;
	private String token;
	
	public UsuarioAcessoDTO() {
		// TODO Auto-generated constructor stub
	}
	
		
	public String getSenhaAtual() {
		return senhaAtual;
	}
	
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	
	public String getNovaSenha() {
		return novaSenha;
	}
	
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	@Override
	public String toString() {
		return "UsuarioAcessoDTO [senhaAtual=" + senhaAtual + ", novaSenha=" + novaSenha + ", email=" + email
				+ ", login=" + login + ", cpf=" + cpf + ", token=" + token + "]";
	}
	

}
