package br.gov.pe.sismepe.dto.covid.boletim;

import java.util.ArrayList;
import java.util.List;

public class BoletimPagina1DTO {
	
	private Integer suspeitos;
	private Integer positivos;
	private Integer negativos;
	private Integer aguardando;
	private Integer naoTestados;
	private Integer obitos;
	
	private List<CasoCovidDTO> casosAcumulados = new ArrayList<CasoCovidDTO>();
	private List<CasoCovidDTO> casosPorSemana = new ArrayList<CasoCovidDTO>();

	public BoletimPagina1DTO() {}


	public Integer getSuspeitos() {
		return suspeitos;
	}


	public void setSuspeitos(Integer suspeitos) {
		this.suspeitos = suspeitos;
	}


	public Integer getPositivos() {
		return positivos;
	}


	public void setPositivos(Integer positivos) {
		this.positivos = positivos;
	}


	public Integer getNegativos() {
		return negativos;
	}


	public void setNegativos(Integer negativos) {
		this.negativos = negativos;
	}


	public Integer getAguardando() {
		return aguardando;
	}


	public void setAguardando(Integer aguardando) {
		this.aguardando = aguardando;
	}


	public Integer getNaoTestados() {
		return naoTestados;
	}


	public void setNaoTestados(Integer naoTestados) {
		this.naoTestados = naoTestados;
	}


	public Integer getObitos() {
		return obitos;
	}


	public void setObitos(Integer obitos) {
		this.obitos = obitos;
	}


	public List<CasoCovidDTO> getCasosAcumulados() {
		return casosAcumulados;
	}


	public void setCasosAcumulados(List<CasoCovidDTO> casosAcumulados) {
		this.casosAcumulados = casosAcumulados;
	}
	
	public List<CasoCovidDTO> getCasosPorSemana() {
		return casosPorSemana;
	}

	public void setCasosPorSemana(List<CasoCovidDTO> casosPorSemana) {
		this.casosPorSemana = casosPorSemana;
	}
	
}
