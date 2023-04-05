package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENDERECO")
public class Endereco implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CD_ENDERECO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DS_LOGRADOURO")
	private String logradouro;
	
	@Column(name = "NM_BAIRRO")
	private String bairro;
	
	@Column(name = "CD_UF")
	private String uf;
	
	@Column(name = "NR_CEP")
	private String cep;
	
	@Column(name = "NR_ENDERECO")
	private Long numero;
	
	@Column(name = "DS_COMPLEMENTO")
	private String complemento;
	
	@Column(name = "CD_CIDADE")
	private Long cidade;
	
	@Column(name = "DS_CIDADE")
	private String nomeCidade;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	public Endereco() {
	}

	public Endereco(Long id, String descricao, String tipo, String observacao, String cep, String ativo) {
		super();
		this.id = id;
		this.logradouro = descricao;
		this.bairro = tipo;
		this.uf = observacao;
		this.cep = cep;
		this.ativo = ativo;
	}

	
	public String getDescricaoLogradouro() {
		String descricao = "";
		
		if(logradouro != null && !logradouro.equals("")) {
			descricao += logradouro;
		}
		
		if(bairro != null && !bairro.equals("")) {
			descricao += descricao.length() > 0 ? ", " + bairro : bairro;
		}
		
		if(numero != null && !numero.equals("")) {
			descricao += descricao.length() > 0 ? ", " + numero : numero;
		}
		
		return descricao;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return logradouro;
	}

	public void setDescricao(String descricao) {
		this.logradouro = descricao;
	}

	public String getTipo() {
		return bairro;
	}

	public void setTipo(String tipo) {
		this.bairro = tipo;
	}

	public String getObservacao() {
		return uf;
	}

	public void setObservacao(String observacao) {
		this.uf = observacao;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getCidade() {
		return cidade;
	}

	public void setCidade(Long cidade) {
		this.cidade = cidade;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
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
		Endereco other = (Endereco) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", bairro=" + bairro + ", uf=" + uf + ", cep="
				+ cep + ", ativo=" + ativo + "]";
	}
	
	
	
}
