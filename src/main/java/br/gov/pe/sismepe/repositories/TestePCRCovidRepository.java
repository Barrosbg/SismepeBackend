package br.gov.pe.sismepe.repositories;

import java.util.List;

import br.gov.pe.sismepe.domain.TestePCRCovid;
import br.gov.pe.sismepe.dto.covid.TestePCRCovidDTO;
import br.gov.pe.sismepe.filtro.FiltroTestePCRCovid;

public interface TestePCRCovidRepository extends GenericRepository<TestePCRCovid, Integer> {

	List<TestePCRCovidDTO> consultarDTOPorFiltro(FiltroTestePCRCovid filtro);

}