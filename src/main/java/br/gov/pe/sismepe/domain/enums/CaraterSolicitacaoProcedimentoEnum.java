package br.gov.pe.sismepe.domain.enums;

public enum CaraterSolicitacaoProcedimentoEnum {
	
	ELETIVO("ELETIVO"), 
	URGENCIA("URGENCIA");
	
	private String tipo;
	
	CaraterSolicitacaoProcedimentoEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

}
