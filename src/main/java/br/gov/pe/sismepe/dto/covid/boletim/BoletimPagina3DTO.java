package br.gov.pe.sismepe.dto.covid.boletim;

import java.util.ArrayList;
import java.util.List;

public class BoletimPagina3DTO {
	
	private List<SituacaoLeitoDTO> situacaoGequar = new ArrayList<SituacaoLeitoDTO>();
	private List<SituacaoLeitoDTO> situacaoGeter = new ArrayList<SituacaoLeitoDTO>();
	private List<SituacaoLeitoDTO> situacaoSpaCovid19 = new ArrayList<SituacaoLeitoDTO>();
	private List<SituacaoLeitoDTO> situacaoUti = new ArrayList<SituacaoLeitoDTO>();
	
	private Integer pmTitularesAtivos;
	private Integer pmTitularesInativos;
	private Integer pmDependentesAtivos;
	private Integer pmDependentesInativos;
	private Integer cbmTitularesAtivos;
	private Integer cbmTitularesInativos;
	private Integer cbmDependentesAtivos;
	private Integer cbmDependentesInativos;

	private List<CasoCovidDTO> grupoFaixaEtaria = new ArrayList<CasoCovidDTO>();
	
	
	private Integer getNumInternados(List<SituacaoLeitoDTO> situacao) {
		for (SituacaoLeitoDTO sit: situacao) {
			if (sit.getTipo().equals(SituacaoLeitoDTO.LEITOS_OCUPADOS)) {
				return sit.getNumLeitos();
			}
		}
		return 0;
	}
	
	private Integer getTotalLeitos(List<SituacaoLeitoDTO> situacao) {
		return situacao.stream().reduce(0, (partialResult, sit) -> partialResult + sit.getNumLeitos(), Integer::sum);
	}

	public BoletimPagina3DTO() { }

	public Integer getInternadosGequar() {
		return this.getNumInternados(situacaoGequar);
	}

	public Integer getTotalLeitosGequar() { 
		return this.getTotalLeitos(situacaoGequar);
	}


	public Integer getInternadosGeter() {
		return this.getNumInternados(situacaoGeter);
	}

	public Integer getTotalLeitosGeter() {
		return this.getTotalLeitos(situacaoGeter);
	}


	public Integer getInternadosSpaCovid19() {
		return this.getNumInternados(situacaoSpaCovid19);
	}

	public Integer getTotalLeitosSpaCovid19() {
		return this.getTotalLeitos(situacaoSpaCovid19);
	}

	public Integer getInternadosUti() {
		return this.getNumInternados(situacaoUti);
	}

	public Integer getTotalLeitosUti() {
		return this.getTotalLeitos(situacaoUti);
	}


	public Integer getPmTitularesAtivos() {
		return pmTitularesAtivos;
	}

	public void setPmTitularesAtivos(Integer pmTitularesAtivos) {
		this.pmTitularesAtivos = pmTitularesAtivos;
	}

	public Integer getPmTitularesInativos() {
		return pmTitularesInativos;
	}

	public void setPmTitularesInativos(Integer pmTitularesInativos) {
		this.pmTitularesInativos = pmTitularesInativos;
	}

	public Integer getPmDependentesAtivos() {
		return pmDependentesAtivos;
	}

	public void setPmDependentesAtivos(Integer pmDependentesAtivos) {
		this.pmDependentesAtivos = pmDependentesAtivos;
	}

	public Integer getPmDependentesInativos() {
		return pmDependentesInativos;
	}

	public void setPmDependentesInativos(Integer pmDependentesInativos) {
		this.pmDependentesInativos = pmDependentesInativos;
	}

	public Integer getCbmTitularesAtivos() {
		return cbmTitularesAtivos;
	}

	public void setCbmTitularesAtivos(Integer cbmTitularesAtivos) {
		this.cbmTitularesAtivos = cbmTitularesAtivos;
	}

	public Integer getCbmTitularesInativos() {
		return cbmTitularesInativos;
	}

	public void setCbmTitularesInativos(Integer cbmTitularesInativos) {
		this.cbmTitularesInativos = cbmTitularesInativos;
	}

	public Integer getCbmDependentesAtivos() {
		return cbmDependentesAtivos;
	}

	public void setCbmDependentesAtivos(Integer cbmDependentesAtivos) {
		this.cbmDependentesAtivos = cbmDependentesAtivos;
	}

	public Integer getCbmDependentesInativos() {
		return cbmDependentesInativos;
	}

	public void setCbmDependentesInativos(Integer cbmDependentesInativos) {
		this.cbmDependentesInativos = cbmDependentesInativos;
	}

	public List<CasoCovidDTO> getGrupoFaixaEtaria() {
		return grupoFaixaEtaria;
	}

	public void setGrupoFaixaEtaria(List<CasoCovidDTO> grupoFaixaEtaria) {
		this.grupoFaixaEtaria = grupoFaixaEtaria;
	}

	public List<SituacaoLeitoDTO> getSituacaoGequar() {
		return situacaoGequar;
	}

	public void setSituacaoGequar(List<SituacaoLeitoDTO> situacaoGequar) {
		this.situacaoGequar = situacaoGequar;
	}

	public List<SituacaoLeitoDTO> getSituacaoGeter() {
		return situacaoGeter;
	}

	public void setSituacaoGeter(List<SituacaoLeitoDTO> situacaoGeter) {
		this.situacaoGeter = situacaoGeter;
	}

	public List<SituacaoLeitoDTO> getSituacaoSpaCovid19() {
		return situacaoSpaCovid19;
	}

	public void setSituacaoSpaCovid19(List<SituacaoLeitoDTO> situacaoSpaCovid19) {
		this.situacaoSpaCovid19 = situacaoSpaCovid19;
	}

	public List<SituacaoLeitoDTO> getSituacaoUti() {
		return situacaoUti;
	}

	public void setSituacaoUti(List<SituacaoLeitoDTO> situacaoUti) {
		this.situacaoUti = situacaoUti;
	}

	
	
}
