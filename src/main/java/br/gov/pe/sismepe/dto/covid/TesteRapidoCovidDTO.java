package br.gov.pe.sismepe.dto.covid;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TesteRapidoCovidDTO implements Serializable {
	
	private static final long serialVersionUID = 8274837680866447279L;
	private Integer id;
	private Integer codigoPessoa;
	private String nome;
	private String matricula;
	private String cpf;
	private String sexo;
	private Integer idade;
	private String tipo;
	private String categoria;
	private String corporacao;
	private String situacao;
	private String igg;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataColetaExame;
	
	public TesteRapidoCovidDTO() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCorporacao() {
		return corporacao;
	}

	public void setCorporacao(String corporacao) {
		this.corporacao = corporacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getDataColetaExame() {
		return dataColetaExame;
	}

	public void setDataColetaExame(Date dataColetaExame) {
		this.dataColetaExame = dataColetaExame;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIgg() {
		return igg;
	}

	public void setIgg(String igg) {
		this.igg = igg;
	}
}