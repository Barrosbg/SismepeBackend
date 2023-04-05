package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "PESSOA_TITULAR")
@JsonTypeName("PESSOA_TITULAR")
public class PessoaTitular extends Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Column(name = "NR_MATRICULA")
	private Integer matricula;
	
	@Column(name = "NR_DIGITO")
	private Integer digito;
	
	@Column(name = "CD_CORPORACAO")
	private Integer corporacao;
	
	@Column(name = "TP_TABELA")
	private Integer tipoTabela;
	
	@Column(name = "DT_CANCELAMENTO")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCancelamento;
	
	@Column(name = "DT_CONCESSAO")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConcessao;

	@Column(name = "DT_ALT")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataAlteracao;
	
	@Column(name = "DS_ENDERECO_FOTO")
	private String foto;
	
	@ManyToOne
	@JoinColumn(name = "CD_OME")
	private Ome ome;
	
	@ManyToOne
	@JoinColumn(name = "CD_POSTO", nullable = true)
	private Posto posto;

	public PessoaTitular() {
		
	}
	
	public PessoaTitular(Integer matricula, Integer digito, Integer corporacao, Integer tipoTabela, Date cancelamento) {
		super();
		this.matricula = matricula;
		this.digito = digito;
		this.corporacao = corporacao;
		this.tipoTabela = tipoTabela;
		this.dataCancelamento = cancelamento;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Integer getDigito() {
		return digito;
	}

	public void setDigito(Integer digito) {
		this.digito = digito;
	}

	public Integer getCorporacao() {
		return corporacao;
	}

	public void setCorporacao(Integer corporacao) {
		this.corporacao = corporacao;
	}

	public Integer getTipoTabela() {
		return tipoTabela;
	}

	public void setTipoTabela(Integer tipoTabela) {
		this.tipoTabela = tipoTabela;
	}

	public Date getCancelamento() {
		return dataCancelamento;
	}

	public void setCancelamento(Date cancelamento) {
		this.dataCancelamento = cancelamento;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Ome getOme() {
		return ome;
	}

	public void setOme(Ome ome) {
		this.ome = ome;
	}

	public Posto getPosto() {
		return posto;
	}

	public void setPosto(Posto posto) {
		this.posto = posto;
	}
	
	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Date getDataConcessao() {
		return dataConcessao;
	}

	public void setDataConcessao(Date dataConcessao) {
		this.dataConcessao = dataConcessao;
	}
	
	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public String toString() {
		return "PessoaTitular [matricula=" + matricula + ", digito=" + digito + ", corporacao=" + corporacao
				+ ", tipoTabela=" + tipoTabela + ", dataCancelamento=" + dataCancelamento + ", foto=" + foto + ", ome="
				+ ome + ", posto=" + posto + "]";
	}
	
	
	
}