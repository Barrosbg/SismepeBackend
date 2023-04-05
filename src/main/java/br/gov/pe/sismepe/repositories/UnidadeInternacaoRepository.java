package br.gov.pe.sismepe.repositories;

import java.util.List;

import br.gov.pe.sismepe.domain.UnidadeInternacao;
import br.gov.pe.sismepe.filtro.FiltroUnidadeInternacao;

public interface UnidadeInternacaoRepository extends GenericRepository<UnidadeInternacao, Integer> {
	public List<UnidadeInternacao> consultarPorFiltro(FiltroUnidadeInternacao filtro);

}