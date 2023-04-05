package br.gov.pe.sismepe.filtro;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Query;

import br.gov.pe.sismepe.domain.SuspeitaCovid;

public class FiltroSuspeitaCovid extends FiltroGenerico<SuspeitaCovid> implements Serializable {

	private static final long serialVersionUID = -921888663288461181L;
	private Long id;
	private Integer codigoPessoa;
	private String nome;
	private String positivo;
	private Date dataColetaExame;
	private Date dataResultadoExame;
	private Date dataObito;
	private Integer matricula;
	private String sexo;
	private Integer idade;
	private String tipo;
	private String categoria;
	private String corporacao;
	private String situacao;
	private String suspeitaCovid;
	private String turno;
	private String status;
	private String obito;
	private String tipoConsulta;

	
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

	public Integer getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(Integer codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public String getPositivo() {
		return positivo;
	}

	public void setPositivo(String positivo) {
		this.positivo = positivo;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDataObito() {
		return dataObito;
	}

	public void setDataObito(Date dataObito) {
		this.dataObito = dataObito;
	}

	public String getObito() {
		return obito;
	}

	public void setObito(String obito) {
		this.obito = obito;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	@Override
	public String getSelectQuery() {
		return null;
	}

	@Override
	public String getSQL() {
		return null;
	}

	@Override
	public String getSQLExisteEntidade() {
		return null;
	}

	@Override
	public void setParametros(Query query) {
		
	}
}