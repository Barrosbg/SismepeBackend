package br.gov.pe.sismepe.dto.covid;

import java.io.Serializable;

public class EstatisticaUnidadeInternacaoDTO implements Serializable {
	
	private static final long serialVersionUID = 8274837680866447279L;
	private String nome;
	private String cor;
	private Integer ativos;
	private Integer ocupados;
	private Integer manutencao;
	private Integer vagos;
	private Integer total;
	private Double taxaOcupacao;
	
	
	public EstatisticaUnidadeInternacaoDTO() {
		
	}

	public EstatisticaUnidadeInternacaoDTO(String nome, String cor, Integer ativos, Integer ocupados,
			Integer manutencao, Integer vagos, Integer total, Double taxaOcupacao) {
		super();
		this.nome = nome;
		this.cor = cor;
		this.ativos = ativos;
		this.ocupados = ocupados;
		this.manutencao = manutencao;
		this.vagos = vagos;
		this.total = total;
		this.taxaOcupacao = taxaOcupacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Integer getAtivos() {
		return ativos;
	}

	public void setAtivos(Integer ativos) {
		this.ativos = ativos;
	}

	public Integer getOcupados() {
		return ocupados;
	}

	public void setOcupados(Integer ocupados) {
		this.ocupados = ocupados;
	}

	public Integer getManutencao() {
		return manutencao;
	}

	public void setManutencao(Integer manutencao) {
		this.manutencao = manutencao;
	}

	public Integer getVagos() {
		return vagos;
	}

	public void setVagos(Integer vagos) {
		this.vagos = vagos;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Double getTaxaOcupacao() {
		return taxaOcupacao;
	}

	public void setTaxaOcupacao(Double taxaOcupacao) {
		this.taxaOcupacao = taxaOcupacao;
	}
}