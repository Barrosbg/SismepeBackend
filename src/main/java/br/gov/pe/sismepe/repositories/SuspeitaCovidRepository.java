package br.gov.pe.sismepe.repositories;

import java.util.List;

import br.gov.pe.sismepe.domain.Atendimento;
import br.gov.pe.sismepe.domain.SuspeitaCovid;
import br.gov.pe.sismepe.dto.covid.SuspeitaCovidDTO;
import br.gov.pe.sismepe.filtro.FiltroSuspeitaCovid;

public interface SuspeitaCovidRepository extends GenericRepository<SuspeitaCovid, Integer> {

	public SuspeitaCovid buscarPorCodigoPessoa(Integer codigoPessoa);
	public SuspeitaCovid buscarPorAtendimento(Atendimento atendimento);
	public List<SuspeitaCovid> consultarPorFiltro(FiltroSuspeitaCovid filtro);
	public List<SuspeitaCovidDTO> consultarDTOPorFiltro(FiltroSuspeitaCovid filtro);
	public List<SuspeitaCovidDTO> consultarDTOPorFiltroInternacao(FiltroSuspeitaCovid filtro);
	public List<SuspeitaCovidDTO> consultarDTOPorFiltroUrgencia(FiltroSuspeitaCovid filtro);
}