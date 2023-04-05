package br.gov.pe.sismepe.repositories;

import java.util.List;

import br.gov.pe.sismepe.domain.SetorPessoaTesteCovid;
import br.gov.pe.sismepe.filtro.FiltroSetorPessoaTesteCovid;

public interface SetorPessoaTesteCovidRepository extends GenericRepository<SetorPessoaTesteCovid, Integer> {
	public List<SetorPessoaTesteCovid> consultarPorFiltro(FiltroSetorPessoaTesteCovid filtro);


}