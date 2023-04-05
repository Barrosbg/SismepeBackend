package br.gov.pe.sismepe.dto.covid.boletim;

import java.util.ArrayList;
import java.util.List;

public class BoletimPagina4DTO {
	
	private List<ExpedienteDTO> expediente = new ArrayList<ExpedienteDTO>();

	public BoletimPagina4DTO() { }
	
	public List<ExpedienteDTO> getExpediente() {
		return expediente;
	}

	public void setExpediente(List<ExpedienteDTO> expediente) {
		this.expediente = expediente;
	}
	
}
