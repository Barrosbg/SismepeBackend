package br.gov.pe.sismepe.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PRESTADOR")
public class Prestador implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_PRESTADOR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NM_MNEMONICO")
	private String nome;
	
	@JoinColumn(name = "CD_PESSOA")
	@OneToOne
	@JsonIgnoreProperties(value = {"usuarioCadastro", "endereco"})
	private Pessoa pessoa;
	
	@JsonIgnore
	@JoinColumn(name = "CD_CONSELHO")
	@ManyToOne
	private Conselho conselho;

	@Column(name = "NR_CONSELHO")
	private String numeroConselho;
	
	@Column(name = "SN_CORPO_CLINICO")
	private String corpoClinico;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "PRESTADOR_ESPECIALIDADE",
			joinColumns = @JoinColumn(name = "CD_PRESTADOR"),
			inverseJoinColumns = @JoinColumn(name = "CD_ESPECIALIDADE")
	)
	private List<Especialidade> especialidades = new ArrayList<>();
	
	public Prestador() {
	}
	
	public Prestador(Long id, String nome, Conselho conselho, String numeroConselho, Pessoa pessoa, String corpoClinico, String ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.conselho = conselho;
		this.numeroConselho = numeroConselho;
		this.pessoa = pessoa;
		this.corpoClinico = corpoClinico;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getNumeroConselho() {
		return numeroConselho;
	}

	public void setNumeroConselho(String numeroConselho) {
		this.numeroConselho = numeroConselho;
	}

	public String getCorpoClinico() {
		return corpoClinico;
	}

	public void setCorpoClinico(String corpoClinico) {
		this.corpoClinico = corpoClinico;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	public Conselho getConselho() {
		return conselho;
	}

	public void setConselho(Conselho conselho) {
		this.conselho = conselho;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestador other = (Prestador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
