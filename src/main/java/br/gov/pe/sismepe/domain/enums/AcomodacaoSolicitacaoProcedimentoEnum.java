package br.gov.pe.sismepe.domain.enums;

public enum AcomodacaoSolicitacaoProcedimentoEnum {
	
	AMBULATORIAL("AMBULATORIAL"),
	APARTAMENTO("APARTAMENTO"),
	ENFERMARIA("ENFERMARIA");
	
	private String tipo;

	AcomodacaoSolicitacaoProcedimentoEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	

}
