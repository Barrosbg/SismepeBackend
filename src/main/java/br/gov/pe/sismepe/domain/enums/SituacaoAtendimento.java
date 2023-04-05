package br.gov.pe.sismepe.domain.enums;

public enum SituacaoAtendimento {

	ABERTO(1, "Aberto"),
	EM_ATENDIMENTO(2, "Em atendimento"),
	CANCELADO(3, "Cancelado"),
	FINALIZADO(4, "Finalizado"),
	REAVALIAR(5, "Reavaliar"),
	EVASAO(6, "Evasão");
	
	private Integer codigo;
	private String descricao;

	private SituacaoAtendimento(Integer codigo, String descricao) {
		this.codigo = codigo;	
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static SituacaoAtendimento toEnum(Integer codigo) {
		SituacaoAtendimento retorno = null;
		if (codigo != null) {
			for (SituacaoAtendimento tipo : SituacaoAtendimento.values()) {
				if (tipo.getCodigo().equals(codigo)) {
					retorno = tipo;
				}
			}
		}

		if (retorno == null) {
			throw new IllegalArgumentException("Código inválido: " + codigo);
		}
		return retorno;
	}

	public Integer getCodigo() {
		return codigo;
	}
}