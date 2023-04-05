package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UNIDADE_ATENDIMENTO")
public class UnidadeAtendimento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_UNIDADE_ATENDIMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "DS_UNIDADE_ATENDIMENTO")
	private String descricao;
	
	@OneToOne
	@JoinColumn(name = "CD_ENDERECO")
	private Endereco endereco;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	public UnidadeAtendimento() {
	}
	
	public UnidadeAtendimento(Integer id, String descricao, Endereco endereco, String ativo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.endereco = endereco;
		this.ativo = ativo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
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
		UnidadeAtendimento other = (UnidadeAtendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}