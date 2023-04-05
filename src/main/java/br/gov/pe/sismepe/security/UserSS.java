package br.gov.pe.sismepe.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS() {

	}

	public UserSS(Integer id, String nome, String login, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public UserSS(Integer id, String nome, String login, String senha, String nivelAcesso,
			List<br.gov.pe.sismepe.domain.Perfil> perfis) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;

		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		
		if (nivelAcesso.equals("A")) {
			list.add(new SimpleGrantedAuthority("ADMIN"));
		}

		perfis.forEach((perfil) -> {
			if (perfil.getSiglaPermissao() != null && StringUtils.hasText(perfil.getSiglaPermissao())) {
				list.add(new SimpleGrantedAuthority(perfil.getSiglaPermissao().toString()));
			}
		});

		this.authorities = list.stream().map(perfil -> perfil).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "UserSS [id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", authorities="
				+ authorities + "]";
	}

}