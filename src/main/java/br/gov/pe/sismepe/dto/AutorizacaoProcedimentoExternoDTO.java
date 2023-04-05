package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.Empresa;
import br.gov.pe.sismepe.domain.ItSolicitacaoProcedimentoExterno;

public class AutorizacaoProcedimentoExternoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ItSolicitacaoProcedimentoExterno itSolicitacao;	
	private Empresa empresa;
	private String situacao;	
	private String observacao;
	private String observacaoRestrita;
	private int quantidadeAutorizada;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataCadastro;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataAlteracao;


	public ItSolicitacaoProcedimentoExterno getItSolicitacao() {
		return itSolicitacao;
	}

	public void setItSolicitacao(ItSolicitacaoProcedimentoExterno itSolicitacao) {
		this.itSolicitacao = itSolicitacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacaoRestrita() {
		return observacaoRestrita;
	}

	public void setObservacaoRestrita(String observacaoRestrita) {
		this.observacaoRestrita = observacaoRestrita;
	}

	public int getQuantidadeAutorizada() {
		return quantidadeAutorizada;
	}

	public void setQuantidadeAutorizada(int quantidadeAutorizada) {
		this.quantidadeAutorizada = quantidadeAutorizada;
	}

	@Override
	public String toString() {
		return "AutorizacaoProcedimentoInternoDTO [itSolicitacao=" + itSolicitacao + ", empresa=" + empresa
				+ ", situacao=" + situacao + ", dataCadastro=" + dataCadastro + ", dataAlteracao=" + dataAlteracao
				+ "]";
	}
	
	
	
}
