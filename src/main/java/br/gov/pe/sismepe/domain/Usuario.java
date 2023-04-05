package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cd_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "cd_pessoa")
	@JsonIgnoreProperties(value = {"usuarioCadastro","endereco"})
	private Pessoa pessoa;
	
	@Column(name = "nm_login", nullable = false)
	private String login;
	
	@Column(name = "ds_email", nullable = false)
	private String email;
	
	@Column(name = "ds_senha", nullable = false)
	@JsonIgnore
	private String senha;
	
	@Column(name = "sn_ativo", nullable = false)
	private String ativo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinTable(name = "PERFIL_USUARIO",
		joinColumns = @JoinColumn(name = "CD_USUARIO"),
		inverseJoinColumns = @JoinColumn(name = "CD_PERFIL")
	)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Perfil> perfis = new ArrayList<>();
	
	@Column(name = "tp_nivel_acesso")
	private String nivelAcesso;
	
//	@ElementCollection(fetch = FetchType.EAGER)
//	@CollectionTable (name = "PERFIS")
//	private Set<Integer> perfis = new HashSet<Integer>();
	
	public Usuario() {
		
	}
	
	public Usuario(Integer id) {
		super();
		this.id = id;
	}

	public Usuario(Integer id, Pessoa pessoa, String login, String senha, String ativo) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
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
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", pessoa=" + pessoa + ", login=" + login + ", senha=" + senha + ", ativo=" + ativo
				+ ", perfis=" + perfis + ", nivelAcesso=" + nivelAcesso + "]";
	}

//	public Set<Perfil> getPerfis() {
//		return perfis.stream().map(perfil -> Perfil.toEnum(perfil)).collect(Collectors.toSet());
//	}
//	
//	public void addPerfil(Perfil perfil) {
//		this.perfis.add(perfil.getCodigo());
//	}
//
//	public void setPerfis(Set<Integer> perfis) {
//		this.perfis = perfis;
//	}

}