package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PesquisaExpiracaoDTO implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private int cdPessoa;
	private int cdAtendimento;
	private Date dataCadastro = new Date();
	private Date dataExpiracao;
	private String pesquisaExpirada;
	
	
	public PesquisaExpiracaoDTO(Long id, int cdPessoa, int cdAtendimento, Date dataCadastro, Date dataExpiracao,
			String pesquisaExpirada) {
		super();
		this.id = id;
		this.cdPessoa = cdPessoa;
		this.cdAtendimento = cdAtendimento;
		this.dataCadastro = dataCadastro;
		this.dataExpiracao = dataExpiracao;
		this.pesquisaExpirada = pesquisaExpirada;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getCdPessoa() {
		return cdPessoa;
	}


	public void setCdPessoa(int cdPessoa) {
		this.cdPessoa = cdPessoa;
	}


	public int getCdAtendimento() {
		return cdAtendimento;
	}


	public void setCdAtendimento(int cdAtendimento) {
		this.cdAtendimento = cdAtendimento;
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public Date getDataExpiracao() {
		return dataExpiracao;
	}


	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}


	public String getPesquisaExpirada() {
		return pesquisaExpirada;
	}


	public void setPesquisaExpirada(String pesquisaExpirada) {
		this.pesquisaExpirada = pesquisaExpirada;
	}
	
	
	
	
}
