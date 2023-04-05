package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comorbidade_pessoa")
public class PessoaComorbidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
    @Id
	@Column(name="CD_COMORBIDADE")
	private Comorbidade codigo;
	

	@Column(name="CD_PESSOA")
	private Pessoa pessoa;

	

	public PessoaComorbidade() {
		super();
	}


	public PessoaComorbidade(Comorbidade codigo, Pessoa pessoa) {
		super();
		this.codigo = codigo;
		this.pessoa = pessoa;
	}


	public Comorbidade getCodigo() {
		return codigo;
	}


	public void setCodigo(Comorbidade codigo) {
		this.codigo = codigo;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	@Override
	public String toString() {
		return "PessoaComorbidade [codigo=" + codigo + ", pessoa=" + pessoa + "]";
	}
	
	
	
	
}
