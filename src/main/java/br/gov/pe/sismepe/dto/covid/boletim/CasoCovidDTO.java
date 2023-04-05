package br.gov.pe.sismepe.dto.covid.boletim;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CasoCovidDTO {
	
	private Integer numCasos;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	private Integer numSemana;
	
	private Integer faixaEtariaInicio;
	private Integer faixaEtariaFim;


	public CasoCovidDTO() {}
	
	/**
	 * Construtor para os gráficos "Casos acumulados de COVID-19" e "Óbitos acumulados"
	 * @param data
	 * @param numCasos
	 */
	public CasoCovidDTO(Date data, Integer numCasos) {
		this.data = data;
		this.numCasos = numCasos;
	}

	/**
	 * Construtor para o gráfico "Casos confirmados por semana epidemiológica"
	 * @param numSemana
	 * @param numCasos
	 */
	public CasoCovidDTO(Integer numSemana, Integer numCasos) {
		this.numSemana = numSemana;
		this.numCasos = numCasos;
	}
	
	/**
	 * Construtor para "Gráfico por faixa etária"
	 * @param faixaEtariaInicio
	 * @param faixaEtariaFim
	 * @param numCasos
	 */
	public CasoCovidDTO(Integer faixaEtariaInicio, Integer faixaEtariaFim, Integer numCasos) {
		this.faixaEtariaInicio = faixaEtariaInicio;
		this.faixaEtariaFim = faixaEtariaFim;
		this.numCasos = numCasos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getNumCasos() {
		return numCasos;
	}

	public void setNumCasos(Integer numCasos) {
		this.numCasos = numCasos;
	}	
	
	public Integer getNumSemana() {
		return numSemana;
	}

	public void setNumSemana(Integer numSemana) {
		this.numSemana = numSemana;
	}
	
	public Integer getFaixaEtariaInicio() {
		return faixaEtariaInicio;
	}

	public void setFaixaEtariaInicio(Integer faixaEtariaInicio) {
		this.faixaEtariaInicio = faixaEtariaInicio;
	}

	public Integer getFaixaEtariaFim() {
		return faixaEtariaFim;
	}

	public void setFaixaEtariaFim(Integer faixaEtariaFim) {
		this.faixaEtariaFim = faixaEtariaFim;
	}

}
