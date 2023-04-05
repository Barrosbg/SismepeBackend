package br.gov.pe.sismepe.domain.enums;

public enum NaturezaSolicitacaoProcedimentoEnum {
	
	EXAME("EXAME"), 
	INTERNACAO_CLINICA("INTERNACAO_CLINICA"), 
	INTERNACAO_CIRURGICA("INTERNACAO_CIRURGICA"),
	INTERNACAO_OBSTETRICA("INTERNACAO_OBSTETRICA"),
	INTERNACAO_PEDIATRICA("INTERNACAO_PEDIATRICA"),
	INTERNACAO_PSIQUIATRICA("INTERNACAO_PSIQUIATRICA");
	
	private String tipo;

	private NaturezaSolicitacaoProcedimentoEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}
