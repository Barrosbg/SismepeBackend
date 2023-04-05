package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.List;

public class BiometriaDTO implements Serializable {
	private static final long serialVersionUID = 3950881401489010593L;
	
	private Integer cdPessoa;
	private List<BiometriaImpressaoDigitalDTO> digitais;
	
	public BiometriaDTO() {
		
	}
	
	public BiometriaDTO(Integer cdPessoa, List<BiometriaImpressaoDigitalDTO> digitais) {
		super();
		this.cdPessoa = cdPessoa;
		this.digitais = digitais;
	}
	public Integer getCdPessoa() {
		return this.cdPessoa;
	}
	public void setCdPessoa(Integer cdPessoa) {
		this.cdPessoa = cdPessoa;
	}
	public List<BiometriaImpressaoDigitalDTO> getDigitais() {
		return this.digitais;
	}
	public void setDigitais(List<BiometriaImpressaoDigitalDTO> digitais) {
		this.digitais = digitais;
	}
	
}
