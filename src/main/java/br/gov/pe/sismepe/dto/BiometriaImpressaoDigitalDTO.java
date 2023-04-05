package br.gov.pe.sismepe.dto;

import java.io.Serializable;

public class BiometriaImpressaoDigitalDTO implements Serializable {
	private static final long serialVersionUID = -8875289690104362296L;

	private String index;
	private String hash;
	
	public BiometriaImpressaoDigitalDTO() {
		
	}
	
	public BiometriaImpressaoDigitalDTO(String index, String hash) {
		super();
		this.index = index;
		this.hash = hash;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
}
