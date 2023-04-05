package br.gov.pe.sismepe.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.pe.sismepe.domain.Ome;
import br.gov.pe.sismepe.domain.Perfil;
import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.Prestador;

public class UsuarioDTO {
	
	private Integer id;
	private Pessoa pessoa;
	private Prestador prestador;
	private String login;
	private String email;
	private String ativo;
	private List<Perfil> perfis = new ArrayList<>();
	private String nivelAcesso;
	private Ome ome;
	
	
	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	
	public Prestador getPrestador() {
		return prestador;
	}


	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAtivo() {
		return ativo;
	}


	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}


	public List<Perfil> getPerfis() {
		return perfis;
	}


	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}


	public String getNivelAcesso() {
		return nivelAcesso;
	}


	public void setNivelAcesso(String nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}


	public Ome getOme() {
		return ome;
	}


	public void setOme(Ome ome) {
		this.ome = ome;
	}
	
	
	

}
