package br.gov.pe.sismepe.dto.covid.boletim;

public class ExpedienteDTO {
	
	private String nomePessoa;
	private String infoComplementarPessoa;
	private String cargoPessoa;
	
	public ExpedienteDTO() { }
	
	public ExpedienteDTO(String nomePessoa, String infoComplementarPessoa, String cargoPessoa) {
		this.nomePessoa = nomePessoa;
		this.infoComplementarPessoa = infoComplementarPessoa;
		this.cargoPessoa = cargoPessoa;
	}
	
	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getInfoComplementarPessoa() {
		return infoComplementarPessoa;
	}

	public void setInfoComplementarPessoa(String infoComplementarPessoa) {
		this.infoComplementarPessoa = infoComplementarPessoa;
	}

	public String getCargoPessoa() {
		return cargoPessoa;
	}

	public void setCargoPessoa(String cargoPessoa) {
		this.cargoPessoa = cargoPessoa;
	}	

}
