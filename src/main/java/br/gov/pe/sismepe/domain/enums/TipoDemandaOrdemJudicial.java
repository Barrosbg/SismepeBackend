package br.gov.pe.sismepe.domain.enums;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public enum TipoDemandaOrdemJudicial {
	EXCOMPANHEIRO(1, "Ex-companheiro (a)"),
	FILHO_MAIOR_NAO_UNIVERSITARIO(2, "Filho maior não universitário"),
	FILHO_MENOR(3, "Filho menor"),
	ALIMENTADO(4, "Alimentado");
	
	private String descricao;
	private Integer id;
	
	private TipoDemandaOrdemJudicial(Integer id, String descricao) {
		this.descricao = descricao;
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getId() {
		return id;
	}
	
	public static TipoDemandaOrdemJudicial fromInteger(Integer index) {
		return Stream.of(TipoDemandaOrdemJudicial.values()).filter((t) -> t.getId() == index).findFirst().orElseThrow(IllegalArgumentException::new);
	}
}
