package br.gov.pe.sismepe.domain.enums;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoDemandaOrdemJudicialConverter implements AttributeConverter<TipoDemandaOrdemJudicial, Integer> {

	@Override
	public Integer convertToDatabaseColumn(TipoDemandaOrdemJudicial td) {
		if (td == null) return null;
		
		return td.getId();
	}

	@Override
	public TipoDemandaOrdemJudicial convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		}
		
		return Stream.of(TipoDemandaOrdemJudicial.values()).filter((t) -> t.getId() == dbData).findFirst().orElseThrow(IllegalArgumentException::new);
	}
}
