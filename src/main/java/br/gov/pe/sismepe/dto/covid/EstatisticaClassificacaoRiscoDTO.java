package br.gov.pe.sismepe.dto.covid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EstatisticaClassificacaoRiscoDTO implements Serializable {
	
	private static final long serialVersionUID = 8274837680866447279L;
	private String descricao;
	private String cor;
	private List<Integer> quantitavivos;
	
	public EstatisticaClassificacaoRiscoDTO() {
		this.quantitavivos = new ArrayList<Integer>();
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

	public List<Integer> getQuantitavivos() {
		return quantitavivos;
	}

	public void setQuantitavivos(List<Integer> quantitavivos) {
		this.quantitavivos = quantitavivos;
	}
}