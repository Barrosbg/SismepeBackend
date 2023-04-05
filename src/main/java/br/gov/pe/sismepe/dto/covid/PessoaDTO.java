package br.gov.pe.sismepe.dto.covid;

import java.io.Serializable;

public class PessoaDTO implements Serializable {
	
	private static final long serialVersionUID = 8274837680866447279L;
	private Integer codigoPessoa;
	private String nome;
	private Integer matricula;
	private String sexo;
	private Integer idade;
	private String tipo;
	
	public PessoaDTO() {
		
	}
	
	public PessoaDTO(Integer codigoPessoa, String nome, String tipo) {
		super();
		this.codigoPessoa = codigoPessoa;
		this.nome = nome;
		this.tipo = tipo;
	}

	public PessoaDTO(Integer codigoPessoa, String nome, Integer matricula, String sexo, Integer idade, String tipo) {
		super();
		this.codigoPessoa = codigoPessoa;
		this.nome = nome;
		this.matricula = matricula;
		this.sexo = sexo;
		this.idade = idade;
		this.tipo = tipo;
	}

	public Integer getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(Integer codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}