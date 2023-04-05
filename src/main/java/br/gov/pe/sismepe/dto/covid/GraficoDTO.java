package br.gov.pe.sismepe.dto.covid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GraficoDTO implements Serializable {
	
	private static final long serialVersionUID = -4139300693474020480L;
	private String descricao;
	private List<DadoGraficoDTO> dataset = new ArrayList<>();
	private List<String> labels = new ArrayList<String>();
	
	public GraficoDTO() {
		
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<DadoGraficoDTO> getDataset() {
		return dataset;
	}
	public void setDataset(List<DadoGraficoDTO> dataset) {
		this.dataset = dataset;
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
}