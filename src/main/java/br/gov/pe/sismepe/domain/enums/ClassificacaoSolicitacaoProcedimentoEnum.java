package br.gov.pe.sismepe.domain.enums;

public enum ClassificacaoSolicitacaoProcedimentoEnum {
	
	AMBULATORIO("AMBULATORIO"), 
	INTERNACAO("INTERNACAO"), 
	ONCOLOGIA("ONCOLOGIA"), 
	URGENCIA("URGENCIA");

	private String tipo;

	ClassificacaoSolicitacaoProcedimentoEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	

}
