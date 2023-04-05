package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import br.gov.pe.sismepe.domain.enums.TipoDemandaOrdemJudicial;

@Entity
@Table(name = "PESSOA_DEPENDENTE")
@JsonTypeName("PESSOA_DEPENDENTE")
public class PessoaDependente extends Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "NR_MATRICULA")
	private Integer matricula;
	
	@Column(name = "NR_SEQUENCIAL")
	private Integer sequencial;
	
	@Column(name = "CD_CORPORACAO")
	private Integer corporacao;
	
	@Column(name = "TP_TABELA")
	private Integer tipoTabela;
	
	@Column(name = "DT_CANCELAMENTO")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCancelamento;
	
	@Column(name = "DT_BLOQUEIO")
	private Date dataBloqueio;
	
	@Column(name = "SN_DIREITO_ORDEM_JUDICIAL")
	private String temDireitoOrdemJudicial;
	
	@Column(name = "DS_DIREITO_ORDEM_JUDICIAL")
	private String descricaoDireitoOrdemJudicial;
	
	@Column(name = "DS_OBSERVACAO")
	private String observacao;
	
	@Column(name = "SN_UNIVERSITARIO")
	private String universitario;
	
	@Column(name = "SN_INVALIDO")
	private String invalido;
	
	@Column(name = "CD_TIPO_DEMANDA_JUDICIAL")
	private TipoDemandaOrdemJudicial tipoDemanda;
	
	@ManyToOne
	@JoinColumn(name="CD_PARENTESCO")
	private Parentesco parentesco;
	
	@Column(name = "DT_VALIDADE_PLANO")
	private Date dataValidadePlano;
	
	@Column(name = "DT_CONCESSAO")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConcessao;
	
	@Column(name = "DT_ULTIMA_ATUALIZACAO")
	private Date ultimaAtualizacao;

	public PessoaDependente() {
		
	}
	
	public PessoaDependente(Integer matricula, Integer sequencial, Integer corporacao, Integer tipoTabela,
			Date dataCancelamento, Date dataBloqueio) {
		super();
		this.matricula = matricula;
		this.sequencial = sequencial;
		this.corporacao = corporacao;
		this.tipoTabela = tipoTabela;
		this.dataCancelamento = dataCancelamento;
		this.dataBloqueio = dataBloqueio;
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

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Date getDataBloqueio() {
		return dataBloqueio;
	}

	public void setDataBloqueio(Date dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}

	public String getTemDireitoOrdemJudicial() {
		return temDireitoOrdemJudicial;
	}

	public void setTemDireitoOrdemJudicial(String temDireitoOrdemJudicial) {
		this.temDireitoOrdemJudicial = temDireitoOrdemJudicial;
	}

	public String getDescricaoDireitoOrdemJudicial() {
		return descricaoDireitoOrdemJudicial;
	}

	public void setDescricaoDireitoOrdemJudicial(String descricaoDireitoOrdemJudicial) {
		this.descricaoDireitoOrdemJudicial = descricaoDireitoOrdemJudicial;
	}

	public String getUniversitario() {
		return universitario;
	}

	public void setUniversitario(String universitario) {
		this.universitario = universitario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoDemandaOrdemJudicial getTipoDemanda() {
		return tipoDemanda;
	}

	public void setTipoDemanda(TipoDemandaOrdemJudicial tipoDemanda) {
		this.tipoDemanda = tipoDemanda;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public String getInvalido() {
		return invalido;
	}

	public void setInvalido(String invalido) {
		this.invalido = invalido;
	}

	public Date getDataValidadePlano() {
		return dataValidadePlano;
	}

	public void setDataValidadePlano(Date dataValidadePlano) {
		this.dataValidadePlano = dataValidadePlano;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public Date getDataConcessao() {
		return dataConcessao;
	}

	public void setDataConcessao(Date dataConcessao) {
		this.dataConcessao = dataConcessao;
	}
	
	
	
}