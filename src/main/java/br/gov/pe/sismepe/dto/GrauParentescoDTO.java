package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.sql.Date;

public class GrauParentescoDTO implements Serializable {
	private static final long serialVersionUID = 614874531423002661L;
	
	private Long cdPessoa;
	private Integer matricula;
	private Integer sequencial;
	private Long grauParentesco;
	private Long beneficiarioDireto;
	private String cep;
	private String complemento;
	private String cpf;
	private Date dataNascimento;
	private Date dataValidadePlano;
	private String email;
	private String genero;
	private String logradouro;
	private Long numero;
	private String numeroProcesso;
	private String numeroSEI;
	private String numeroTelefone;
	private String numeroWhatsapp;
	private Integer tipoDemanda;
	private Long uf;
	private Long cidade;
	private boolean universitario;
	private boolean invalido;
	private String nomeMae;
	
	public GrauParentescoDTO(Long cdPessoa, Integer matricula, Integer sequencial, Long grauParentesco,
			Long beneficiarioDireto, String cep, String complemento, String cpf, Date dataNascimento,
			Date dataValidadePlano, String email, String genero, String logradouro, Long numero,
			String numeroProcesso, String numeroSEI, String numeroTelefone, String numeroWhatsapp,
			Integer tipoDemanda) {
		super();
		this.cdPessoa = cdPessoa;
		this.matricula = matricula;
		this.sequencial = sequencial;
		this.grauParentesco = grauParentesco;
		this.beneficiarioDireto = beneficiarioDireto;
		this.cep = cep;
		this.complemento = complemento;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.dataValidadePlano = dataValidadePlano;
		this.email = email;
		this.genero = genero;
		this.logradouro = logradouro;
		this.numero = numero;
		this.numeroProcesso = numeroProcesso;
		this.numeroSEI = numeroSEI;
		this.numeroTelefone = numeroTelefone;
		this.numeroWhatsapp = numeroWhatsapp;
		this.tipoDemanda = tipoDemanda;
	}
	
	public GrauParentescoDTO() {
	}
	
	public Long getCdPessoa() {
		return cdPessoa;
	}

	public void setCdPessoa(Long cdPessoa) {
		this.cdPessoa = cdPessoa;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}
	
	public Long getGrauParentesco() {
		return grauParentesco;
	}
	public void setGrauParentesco(Long grauParentesco) {
		this.grauParentesco = grauParentesco;
	}
	public Long getBeneficiarioDireto() {
		return beneficiarioDireto;
	}
	public void setBeneficiarioDireto(Long beneficiarioDireto) {
		this.beneficiarioDireto = beneficiarioDireto;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
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
	public Date getDataValidadePlano() {
		return dataValidadePlano;
	}
	public void setDataValidadePlano(Date dataValidadePlano) {
		this.dataValidadePlano = dataValidadePlano;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getNumeroProcesso() {
		return numeroProcesso;
	}
	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}
	public String getNumeroSEI() {
		return numeroSEI;
	}
	public void setNumeroSEI(String numeroSEI) {
		this.numeroSEI = numeroSEI;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
	public String getNumeroWhatsapp() {
		return numeroWhatsapp;
	}
	public void setNumeroWhatsapp(String numeroWhatsapp) {
		this.numeroWhatsapp = numeroWhatsapp;
	}
	public Integer getTipoDemanda() {
		return tipoDemanda;
	}
	public void setTipoDemanda(Integer tipoDemanda) {
		this.tipoDemanda = tipoDemanda;
	}

	public Long getUf() {
		return uf;
	}

	public void setUf(Long uf) {
		this.uf = uf;
	}

	public Long getCidade() {
		return cidade;
	}

	public void setCidade(Long cidade) {
		this.cidade = cidade;
	}

	public boolean isUniversitario() {
		return universitario;
	}

	public void setUniversitario(boolean universitario) {
		this.universitario = universitario;
	}

	public boolean isInvalido() {
		return invalido;
	}

	public void setInvalido(boolean invalido) {
		this.invalido = invalido;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	@Override
	public String toString() {
		return "GrauParentescoDTO [cdPessoa=" + cdPessoa + ", matricula=" + matricula + ", sequencial=" + sequencial
				+ ", grauParentesco=" + grauParentesco + ", beneficiarioDireto=" + beneficiarioDireto + ", cep=" + cep
				+ ", complemento=" + complemento + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento
				+ ", dataValidadePlano=" + dataValidadePlano + ", email=" + email + ", genero=" + genero
				+ ", logradouro=" + logradouro + ", numero=" + numero + ", numeroProcesso=" + numeroProcesso
				+ ", numeroSEI=" + numeroSEI + ", numeroTelefone=" + numeroTelefone + ", numeroWhatsapp="
				+ numeroWhatsapp + ", tipoDemanda=" + tipoDemanda + ", uf=" + uf + ", cidade=" + cidade
				+ ", universitario=" + universitario + ", invalido=" + invalido + "]";
	}

}
