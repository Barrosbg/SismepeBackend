package br.gov.pe.sismepe.dto.covid.boletim;

public class SituacaoLeitoDTO {
	
	public static final String LEITOS_OCUPADOS = "Ocupados";
	public static final String LEITOS_LIVRES = "Livres";
	
	private String tipo;
	private Integer numLeitos;
	
	public SituacaoLeitoDTO() {	}

	/**
	 * Construtor para conjunto de gráficos "Unidades de Internação COVID-19"
	 * @param tipo
	 * @param numLeitos
	 */
	public SituacaoLeitoDTO(String tipo, Integer numLeitos) {
		this.tipo = tipo;
		this.numLeitos = numLeitos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getNumLeitos() {
		return numLeitos;
	}

	public void setNumLeitos(Integer numLeitos) {
		this.numLeitos = numLeitos;
	}	


}
