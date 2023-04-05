package br.gov.pe.sismepe.dto.covid.boletim;

import java.util.ArrayList;
import java.util.List;

public class BoletimPagina2DTO {
	
	private List<CasoCovidDTO> obitosAcumulados = new ArrayList<CasoCovidDTO>();
	private Integer atendidos24h;
	private Integer suspeitos24h;
	private Integer internadosCMH;
	private Integer internadosGequar;
	private Integer totalLeitosGequar;
	private Integer internadosGeter;
	private Integer totalLeitosGeter;
	private Integer internadosSpaCovid19;
	private Integer totalLeitosSpaCovid19;
	private Integer internadosUti;
	private Integer totalLeitosUti;
	
	public BoletimPagina2DTO() {}

	public List<CasoCovidDTO> getObitosAcumulados() {
		return obitosAcumulados;
	}

	public void setObitosAcumulados(List<CasoCovidDTO> obitosAcumulados) {
		this.obitosAcumulados = obitosAcumulados;
	}

	public Integer getAtendidos24h() {
		return atendidos24h;
	}

	public void setAtendidos24h(Integer atendidos24h) {
		this.atendidos24h = atendidos24h;
	}

	public Integer getSuspeitos24h() {
		return suspeitos24h;
	}

	public void setSuspeitos24h(Integer suspeitos24h) {
		this.suspeitos24h = suspeitos24h;
	}

	public Integer getInternadosCMH() {
		return internadosCMH;
	}

	public void setInternadosCMH(Integer internadosCMH) {
		this.internadosCMH = internadosCMH;
	}

	public Integer getInternadosGequar() {
		return internadosGequar;
	}

	public void setInternadosGequar(Integer internadosGequar) {
		this.internadosGequar = internadosGequar;
	}

	public Integer getTotalLeitosGequar() {
		return totalLeitosGequar;
	}

	public void setTotalLeitosGequar(Integer totalLeitosGequar) {
		this.totalLeitosGequar = totalLeitosGequar;
	}

	public Integer getInternadosGeter() {
		return internadosGeter;
	}

	public void setInternadosGeter(Integer internadosGeter) {
		this.internadosGeter = internadosGeter;
	}

	public Integer getTotalLeitosGeter() {
		return totalLeitosGeter;
	}

	public void setTotalLeitosGeter(Integer totalLeitosGeter) {
		this.totalLeitosGeter = totalLeitosGeter;
	}

	public Integer getInternadosSpaCovid19() {
		return internadosSpaCovid19;
	}

	public void setInternadosSpaCovid19(Integer internadosSpaCovid19) {
		this.internadosSpaCovid19 = internadosSpaCovid19;
	}

	public Integer getTotalLeitosSpaCovid19() {
		return totalLeitosSpaCovid19;
	}

	public void setTotalLeitosSpaCovid19(Integer totalLeitosSpaCovid19) {
		this.totalLeitosSpaCovid19 = totalLeitosSpaCovid19;
	}

	public Integer getInternadosUti() {
		return internadosUti;
	}

	public void setInternadosUti(Integer internadosUti) {
		this.internadosUti = internadosUti;
	}

	public Integer getTotalLeitosUti() {
		return totalLeitosUti;
	}

	public void setTotalLeitosUti(Integer totalLeitosUti) {
		this.totalLeitosUti = totalLeitosUti;
	}

}
