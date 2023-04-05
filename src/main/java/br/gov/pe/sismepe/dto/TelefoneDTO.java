package br.gov.pe.sismepe.dto;

import java.io.Serializable;

public class TelefoneDTO implements Serializable {
	private Long cdPessoa;
	private String telefone;
	
	public TelefoneDTO() {
	
	}
	
	public TelefoneDTO(Long cdPessoa, String telefone) {
		this.cdPessoa = cdPessoa;
		this.telefone = telefone;
	}

	public Long getCdPessoa() {
		return cdPessoa;
	}
	public void setCdPessoa(Long cdPessoa) {
		this.cdPessoa = cdPessoa;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
