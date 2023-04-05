package br.gov.pe.sismepe.dto;

import java.io.Serializable;

import br.gov.pe.sismepe.domain.Atendimento;

public class AtendimentoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Integer situacao;
	
	public AtendimentoDTO() {
	}
	
	public AtendimentoDTO(Atendimento obj) {
		this.id = obj.getId();
		this.situacao = obj.getSituacao().getCodigo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}
		
}
