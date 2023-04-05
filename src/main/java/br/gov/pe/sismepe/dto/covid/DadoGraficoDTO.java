package br.gov.pe.sismepe.dto.covid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DadoGraficoDTO implements Serializable {
	
	private static final long serialVersionUID = -4139300693474020480L;
	private String descricao;
	private String cor;
	private List<Integer> quantitativos;
	private List<Integer> acumulados;
	
	public DadoGraficoDTO() {
		this.quantitativos = new ArrayList<Integer>();
		this.acumulados = new ArrayList<Integer>();
	}
	
	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Integer> getQuantitativos() {
		return quantitativos;
	}

	public void setQuantitativos(List<Integer> quantitativos) {
		this.quantitativos = quantitativos;
	}

	public List<Integer> getAcumulados() {
		return acumulados;
	}

	public void setAcumulados(List<Integer> acumulados) {
		this.acumulados = acumulados;
	}
}