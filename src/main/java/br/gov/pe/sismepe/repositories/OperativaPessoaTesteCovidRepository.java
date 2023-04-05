package br.gov.pe.sismepe.repositories;

import java.util.List;

import br.gov.pe.sismepe.domain.OperativaPessoaTesteCovid;
import br.gov.pe.sismepe.filtro.FiltroOperativaPessoaTesteCovid;

public interface OperativaPessoaTesteCovidRepository extends GenericRepository<OperativaPessoaTesteCovid, Integer> {
	public List<OperativaPessoaTesteCovid> consultarPorFiltro(FiltroOperativaPessoaTesteCovid filtro);


}