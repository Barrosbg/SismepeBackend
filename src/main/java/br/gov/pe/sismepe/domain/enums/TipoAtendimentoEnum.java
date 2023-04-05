package br.gov.pe.sismepe.domain.enums;

public enum TipoAtendimentoEnum {

	AMBULATORIAL("Ambulatorial", "A"),
	URGENCIA("Urgência", "U"),
	INTERNACAO("Internação", "I"),
	EXTERNO("Externo", "E"),
	LABORATORIAL("Laboratorial", "L"),
	JMS("JMS", "J");
	

	private String descricao;
	private String sigla;

	private TipoAtendimentoEnum(String descricao, String sigla) {
			this.descricao = descricao;
			this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getSigla() {
		return sigla;
	}

	public static TipoAtendimentoEnum toEnum(String sigla) {
		TipoAtendimentoEnum retorno = null;
		if (sigla != null) {
			for (TipoAtendimentoEnum tipo : TipoAtendimentoEnum.values()) {
				if (tipo.getSigla().equals(sigla)) {
					retorno = tipo;
				}
			}
		}

		if (retorno == null) {
			throw new IllegalArgumentException("Sigla inválida: " + sigla);
		}

		return retorno;
	}
}