package br.gov.pe.sismepe.domain.enums;

public enum TipoMotivoAltaEnum {

	NORMAL("Normal", "N"),
	OBITO("Óbito", "O"),
	TRANSFERENCIA("Transferência", "T"),
	ADMINISTRATIVO("Administrativo", "A");
	
	private String descricao;
	private String sigla;

	private TipoMotivoAltaEnum(String descricao, String sigla) {
			this.descricao = descricao;
			this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getSigla() {
		return sigla;
	}

	public static TipoMotivoAltaEnum toEnum(String sigla) {
		TipoMotivoAltaEnum retorno = null;
		if (sigla != null) {
			for (TipoMotivoAltaEnum tipo : TipoMotivoAltaEnum.values()) {
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