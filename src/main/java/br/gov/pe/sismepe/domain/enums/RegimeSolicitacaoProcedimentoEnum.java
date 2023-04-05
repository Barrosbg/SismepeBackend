package br.gov.pe.sismepe.domain.enums;

public enum RegimeSolicitacaoProcedimentoEnum {
	
	HOSPITALAR("HOSPITALAR"), 
	HOSPITALAR_DIA("HOSPITALAR_DIA"), 
	DOMICILIAR("DOMICILIARomiciliar");
	
	private String tipo;

	RegimeSolicitacaoProcedimentoEnum(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
		

}
