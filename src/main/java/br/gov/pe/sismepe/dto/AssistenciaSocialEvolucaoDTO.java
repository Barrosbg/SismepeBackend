package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.AssistenciaSocialEvolucao;

public class AssistenciaSocialEvolucaoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long assistenciaSocialId; 
	
	private Long atendimentoId;
	
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataInclusao;
	
	private String situacao;

	public AssistenciaSocialEvolucaoDTO() {
	}

	public AssistenciaSocialEvolucaoDTO(AssistenciaSocialEvolucao obj) {
		super();
		this.id = obj.getId();
		this.assistenciaSocialId = obj.getAssistenciaSocial().getId();
		this.atendimentoId = obj.getAtendimento().getId();
		this.descricao = obj.getDescricao();
		this.dataInclusao = obj.getDataInclusao();
		this.situacao = obj.getSituacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssistenciaSocialId() {
		return assistenciaSocialId;
	}

	public void setAssistenciaSocialId(Long assistenciaSocialId) {
		this.assistenciaSocialId = assistenciaSocialId;
	}

	public Long getAtendimentoId() {
		return atendimentoId;
	}

	public void setAtendimentoId(Long atendimentoId) {
		this.atendimentoId = atendimentoId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}
