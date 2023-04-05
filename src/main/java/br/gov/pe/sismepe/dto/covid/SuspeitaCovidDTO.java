package br.gov.pe.sismepe.dto.covid;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SuspeitaCovidDTO implements Serializable {
	
	private static final long serialVersionUID = 8274837680866447279L;
	private Integer id;
	private Integer codigoPessoa;
	private String nome;
	private String matricula;
	private String sexo;
	private Integer idade;
	private String tipo;
	private String categoria;
	private String corporacao;
	private String situacao;
	private String status;
	private Integer codigoMotivoAlta;
	private String descricaoMotivoAlta;
	private String tipoMotivoAlta;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataColetaExame;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataResultadoExame;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataAlta;
	
	public SuspeitaCovidDTO() {
		
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDataColetaExame() {
		return dataColetaExame;
	}

	public void setDataColetaExame(Date dataColetaExame) {
		this.dataColetaExame = dataColetaExame;
	}

	public Date getDataResultadoExame() {
		return dataResultadoExame;
	}

	public void setDataResultadoExame(Date dataResultadoExame) {
		this.dataResultadoExame = dataResultadoExame;
	}

	public Date getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}

	public Integer getCodigoMotivoAlta() {
		return codigoMotivoAlta;
	}

	public void setCodigoMotivoAlta(Integer codigoMotivoAlta) {
		this.codigoMotivoAlta = codigoMotivoAlta;
	}

	public String getDescricaoMotivoAlta() {
		return descricaoMotivoAlta;
	}

	public void setDescricaoMotivoAlta(String descricaoMotivoAlta) {
		this.descricaoMotivoAlta = descricaoMotivoAlta;
	}

	public String getTipoMotivoAlta() {
		return tipoMotivoAlta;
	}

	public void setTipoMotivoAlta(String tipoMotivoAlta) {
		this.tipoMotivoAlta = tipoMotivoAlta;
	}
}