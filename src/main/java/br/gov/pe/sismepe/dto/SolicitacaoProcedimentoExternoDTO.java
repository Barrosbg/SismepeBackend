package br.gov.pe.sismepe.dto;

public class SolicitacaoProcedimentoExternoDTO {
	private long idPaciente;

	public SolicitacaoProcedimentoExternoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public SolicitacaoProcedimentoExternoDTO(long idPaciente) {
		super();
		this.idPaciente = idPaciente;
	}




	public long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(long idPaciente) {
		this.idPaciente = idPaciente;
	}

	

}
