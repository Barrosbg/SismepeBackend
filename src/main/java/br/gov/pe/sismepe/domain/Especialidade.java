package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ESPECIALIDADE")
public class Especialidade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CD_ESPECIALIDADE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DS_ESPECIALIDADE")
	private String descricao;
	
	@Column(name = "TP_ESPECIALIDADE")
	private String tipo;
	
	@Column(name = "DS_OBSERVACAO")
	private String observacao;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "especialidades")
	private List<Prestador> prestadores = new ArrayList<>();
	
	public Especialidade() {
	}

	public Especialidade(Long id, String descricao, String tipo, String observacao, String ativo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.tipo = tipo;
		this.observacao = observacao;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public List<Prestador> getPrestadores() {
		return prestadores;
	}

	public void setPrestadores(List<Prestador> prestadores) {
		this.prestadores = prestadores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
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
		Especialidade other = (Especialidade) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		return true;
	}
	
}
