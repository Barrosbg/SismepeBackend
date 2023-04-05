package br.gov.pe.sismepe.dto.covid;

import java.io.Serializable;

public class PacienteDTO implements Serializable {
	
	private static final long serialVersionUID = 8274837680866447279L;
	private String nome;
	private String matricula;
	private String sexo;
	private Integer idade;
	private String unidadeInternacao;
	private String tipo;
	private String categoria;
	private String corporacao;
	private String situacao;
	private String suspeitaCovid;
	private String turno;
	
	public PacienteDTO() {
		
	}

	public PacienteDTO(String nome, String matricula, String sexo, Integer idade, String unidadeInternacao,
			String tipo, String categoria, String corporacao, String situacao, String suspeitaCovid, String turno) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.sexo = sexo;
		this.idade = idade;
		this.unidadeInternacao = unidadeInternacao;
		this.tipo = tipo;
		this.categoria = categoria;
		this.corporacao = corporacao;
		this.situacao = situacao;
		this.suspeitaCovid = suspeitaCovid;
		this.turno = turno;
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

	public String getUnidadeInternacao() {
		return unidadeInternacao;
	}

	public void setUnidadeInternacao(String unidadeInternacao) {
		this.unidadeInternacao = unidadeInternacao;
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

	public String getSuspeitaCovid() {
		return suspeitaCovid;
	}

	public void setSuspeitaCovid(String suspeitaCovid) {
		this.suspeitaCovid = suspeitaCovid;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
}