package br.gov.pe.sismepe.dto.covid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CasosCovidDTO implements Serializable {
	
	private static final long serialVersionUID = -7800833561400141252L;
	private String descricao = "Casos COVID-19";
	private String cor = "#FFA500";
	private List<Integer> quantitativos = new ArrayList<Integer>();
	private List<Integer> acumulados = new ArrayList<Integer>();
	private List<String> labels = new ArrayList<String>();
	
	public CasosCovidDTO() {
		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public List<Integer> getQuantitativos() {
		return quantitativos;
	}

	public void setQuantitativos(List<Integer> quantitativos) {
		this.quantitativos = quantitativos;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<Integer> getAcumulados() {
		return acumulados;
	}

	public void setAcumulados(List<Integer> acumulados) {
		this.acumulados = acumulados;
	}
}