package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.AssistenciaSocial;

public class AssistenciaSocialDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long atendimentoId;
	
	private Long prestadorId;
	
	private String situacaoClinica;
	
	private String providenciaTomada;
	
	private String statusSituacao;
	
	private String outrasInformacoes;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataInclusao;
	
	private String ativo;
	
	public AssistenciaSocialDTO() {
	}
	
	public AssistenciaSocialDTO(AssistenciaSocial obj) {
		this.id = obj.getId();
		this.dataInclusao = obj.getDataInclusao();
		this.atendimentoId = obj.getAtendimento().getId();
		this.prestadorId = obj.getAtendimento().getPrestador().getId();
		this.ativo = obj.getAtivo();
		this.statusSituacao = (obj.getStatusSituacao().equals("A")) ? "Andamento" : "Finalizado";
		this.situacaoClinica = obj.getSituacaoClinica();
		this.providenciaTomada = obj.getProvidenciaTomada();
		this.outrasInformacoes = obj.getOutrasInformacoes();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAtendimentoId() {
		return atendimentoId;
	}

	public void setAtendimentoId(Long atendimentoId) {
		this.atendimentoId = atendimentoId;
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

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	public Long getPrestadorId() {
		return prestadorId;
	}

	public void setPrestadorId(Long prestadorId) {
		this.prestadorId = prestadorId;
	}
	
}
