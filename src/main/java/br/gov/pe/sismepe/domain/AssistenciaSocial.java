package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assistencia_social")
public class AssistenciaSocial implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CD_ASSISTENCIA_SOCIAL")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "CD_ATENDIMENTO")
	private Atendimento atendimento;
	
	@Column(name = "DS_SITUACAO_CLINICA")
	private String situacaoClinica;
	
	@Column(name = "DS_PROVIDENCIA_TOMADA")
	private String providenciaTomada;
	
	@Column(name = "TP_SITUACAO")
	private String statusSituacao;
	
	@Column(name = "DS_OUTRAS_INFO")
	private String outrasInformacoes;
	
	@Column(name = "DT_INCL")
	private Date dataInclusao;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	@OneToMany(mappedBy="assistenciaSocial")
	private List<AssistenciaSocialEvolucao> evolucoes;
	
	public AssistenciaSocial() {
	}
	
	public AssistenciaSocial(Long id) {
		super();
		this.id = id;
	}

	public AssistenciaSocial(Long id, Atendimento atendimento, String situacaoClinica, String providenciaTomada,
			String statusSituacao, String outrasInformacoes, Date dataInclusao, String ativo) {
		super();
		this.id = id;
		this.atendimento = atendimento;
		this.situacaoClinica = situacaoClinica;
		this.providenciaTomada = providenciaTomada;
		this.statusSituacao = statusSituacao;
		this.outrasInformacoes = outrasInformacoes;
		this.dataInclusao = dataInclusao;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public String getSituacaoClinica() {
		return situacaoClinica;
	}

	public void setSituacaoClinica(String situacaoClinica) {
		this.situacaoClinica = situacaoClinica;
	}

	public String getProvidenciaTomada() {
		return providenciaTomada;
	}

	public void setProvidenciaTomada(String providenciaTomada) {
		this.providenciaTomada = providenciaTomada;
	}

	public String getStatusSituacao() {
		return statusSituacao;
	}

	public void setStatusSituacao(String statusSituacao) {
		this.statusSituacao = statusSituacao;
	}

	public String getOutrasInformacoes() {
		return outrasInformacoes;
	}

	public void setOutrasInformacoes(String outrasInformacoes) {
		this.outrasInformacoes = outrasInformacoes;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public List<AssistenciaSocialEvolucao> getEvolucoes() {
		return evolucoes;
	}

	public void setEvolucoes(List<AssistenciaSocialEvolucao> evolucoes) {
		this.evolucoes = evolucoes;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
		
}
