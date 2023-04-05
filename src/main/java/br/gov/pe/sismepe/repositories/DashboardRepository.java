package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.covid.AdmissaoInternacaoDTO;
import br.gov.pe.sismepe.dto.covid.AltaInternacaoDTO;
import br.gov.pe.sismepe.dto.covid.CasosCovidDTO;
import br.gov.pe.sismepe.dto.covid.EstatisticaCasosDTO;
import br.gov.pe.sismepe.dto.covid.EstatisticaClassificacaoRiscoDTO;
import br.gov.pe.sismepe.dto.covid.EstatisticaTestesDTO;
import br.gov.pe.sismepe.dto.covid.EstatisticaUnidadeInternacaoDTO;
import br.gov.pe.sismepe.dto.covid.GraficoDTO;
import br.gov.pe.sismepe.dto.covid.ObitosCovidDTO;
import br.gov.pe.sismepe.dto.covid.PacienteDTO;
import br.gov.pe.sismepe.dto.covid.PessoaDTO;

@Repository
public interface DashboardRepository {
	
	public List<EstatisticaClassificacaoRiscoDTO> consultarEstatisticaClassificacaoCovid(Integer ano, Integer mes);
	public List<PacienteDTO> consultarClassificacaoCovidPorPlantao(String data, String nome, String sexo, String tipo,
			String categoria, String corporacao, String situacao, String turno);
	public List<AdmissaoInternacaoDTO> consultarAdmissoesInternacaoCovid(Integer ano, Integer mes);
	public List<AltaInternacaoDTO> consultarAltasInternacaoCovid(Integer ano, Integer mes);
	public List<EstatisticaUnidadeInternacaoDTO> consultarEstatisticaUnidadesIternacaoCovid();
	public List<PacienteDTO> consultarPacientesInternadosCovid(String nome, String sexo, String unidade, String tipo,
			String categoria, String corporacao, String situacao, String status);
	
	public Usuario consultarUsuarioParaLogin(String login);
	public List<PessoaDTO> consultarPessoa(Integer matricula);
	public EstatisticaCasosDTO consultarEstatisticaCasos();
	public ObitosCovidDTO consultarObitosAcumuladosCovid();
	public CasosCovidDTO consultarCasosAcumuladosCovid();
	public CasosCovidDTO consultarCasosPorSemana();
	public PessoaDTO consultarPessoaPorCPF(String cpf);
	public GraficoDTO consultarCasosEObitosPorSemana();
	public GraficoDTO consultarClassificacaoRiscoPorDia();
	public EstatisticaTestesDTO consultarEstatisticaTestePCR();
	public EstatisticaTestesDTO consultarEstatisticaTesteRapido();
	public CasosCovidDTO consultarCasosPCRPorSemana();
}