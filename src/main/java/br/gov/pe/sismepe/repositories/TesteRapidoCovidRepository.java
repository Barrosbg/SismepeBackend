package br.gov.pe.sismepe.repositories;

import java.util.List;

import br.gov.pe.sismepe.domain.TesteRapidoCovid;
import br.gov.pe.sismepe.dto.covid.TesteRapidoCovidDTO;
import br.gov.pe.sismepe.filtro.FiltroTesteRapidoCovid;

public interface TesteRapidoCovidRepository extends GenericRepository<TesteRapidoCovid, Integer> {

	List<TesteRapidoCovidDTO> consultarDTOPorFiltro(FiltroTesteRapidoCovid filtro);

}