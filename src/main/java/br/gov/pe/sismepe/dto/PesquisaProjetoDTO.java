package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import br.gov.pe.sismepe.domain.PesquisaProjeto;

public class PesquisaProjetoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricaoProjeto;
	private Date dataCadastro;
	private Date dataExpiracao;
	private String pesquisaExpirada;

	public PesquisaProjeto toModel() {
		PesquisaProjeto pp = new PesquisaProjeto();
		pp.setId(this.id);	
		pp.setDescricaoProjeto(this.descricaoProjeto);
		pp.setDataCadastro(this.dataCadastro);
		pp.setDataExpiracao(this.dataExpiracao);
		pp.setPesquisaExpirada(this.pesquisaExpirada);	

		return pp;
	}

	public PesquisaProjetoDTO() {

	}
	
	public PesquisaProjetoDTO(Long id, String descricaoProjeto, Date dataCadastro, 
			Date dataExpiracao, String pesquisaExpirada) {
		super();
		this.id = id;
		this.descricaoProjeto = descricaoProjeto;
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

	public String getDescricaoProjeto() {
		return descricaoProjeto;
	}

	public void setDescricaoProjeto(String descricaoProjeto) {
		this.descricaoProjeto = descricaoProjeto;
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
