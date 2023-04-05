package br.gov.pe.sismepe.domain;

import java.io.Serializable;

public class BiometriaImpressaoDigitalId implements Serializable {

	private static final long serialVersionUID = 4519702077881066614L;

	private Integer cdIdentificadorDedo;
	private String dsBiometriaHash;
	
	public Integer getCdIdentificadorDedo() {
		return cdIdentificadorDedo;
	}
	public void setCdIdentificadorDedo(Integer cdIdentificadorDedo) {
		this.cdIdentificadorDedo = cdIdentificadorDedo;
	}
	public String getDsBiometriaHash() {
		return dsBiometriaHash;
	}
	public void setDsBiometriaHash(String dsBiometriaHash) {
		this.dsBiometriaHash = dsBiometriaHash;
	}
}
