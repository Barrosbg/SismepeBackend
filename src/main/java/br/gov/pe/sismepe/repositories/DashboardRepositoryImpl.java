package br.gov.pe.sismepe.repositories;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.covid.AdmissaoInternacaoDTO;
import br.gov.pe.sismepe.dto.covid.AltaInternacaoDTO;
import br.gov.pe.sismepe.dto.covid.CasosCovidDTO;
import br.gov.pe.sismepe.dto.covid.DadoGraficoDTO;
import br.gov.pe.sismepe.dto.covid.EstatisticaCasosDTO;
import br.gov.pe.sismepe.dto.covid.EstatisticaClassificacaoRiscoDTO;
import br.gov.pe.sismepe.dto.covid.EstatisticaTestesDTO;
import br.gov.pe.sismepe.dto.covid.EstatisticaUnidadeInternacaoDTO;
import br.gov.pe.sismepe.dto.covid.GraficoDTO;
import br.gov.pe.sismepe.dto.covid.ObitosCovidDTO;
import br.gov.pe.sismepe.dto.covid.PacienteDTO;
import br.gov.pe.sismepe.dto.covid.PessoaDTO;
import br.gov.pe.sismepe.util.Utils;

@Repository
@Transactional(readOnly = true)
public class DashboardRepositoryImpl implements DashboardRepository {

	@Autowired
	protected EntityManager entityManager;

	public Usuario consultarUsuarioParaLogin(String login) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u.cd_usuario, u.nm_login, u.ds_senha, u.sn_ativo, p.nm_pessoa ");
		sb.append("FROM usuario u, ");
		sb.append("pessoa p ");
		sb.append("WHERE u.cd_pessoa = p.cd_pessoa ");
		sb.append("AND u.nm_login = :login ");
		sb.append("AND u.sn_ativo = 'S' ");
		sb.append("AND EXISTS (  ");
		sb.append("				SELECT 1 FROM  ");
		sb.append("				perfil_usuario pu  ");
		sb.append("				WHERE pu.cd_usuario = u.cd_usuario  ");
//		sb.append("				AND pu.cd_perfil = 99  ");
		sb.append(")  ");

		Query query = this.entityManager.createNativeQuery(sb.toString());
		query.setParameter("login", login);

		final List<Object[]> result = query.getResultList();

		Usuario retorno = null;

		if (result != null && !result.isEmpty()) {
			retorno = new Usuario();
			Object[] obj = result.get(0);
			retorno.setId(((Integer) obj[0]).intValue());
			retorno.setLogin((String) obj[1]);
			retorno.setSenha((String) obj[2]);
			retorno.setAtivo((String) obj[3]);
			Pessoa pessoa = new Pessoa();
			pessoa.setNome((String) obj[4]);
			retorno.setPessoa(pessoa);
		}
		return retorno;
	}

	public List<PessoaDTO> consultarPessoa(Integer matricula) {
		StringBuilder sb = new StringBuilder();
		sb.append("select cd_pessoa, nm_pessoa, tipo ");

		sb.append("from ( ");

		sb.append("select p.CD_PESSOA, p.NM_PESSOA, 'TITULAR' as tipo ");
		sb.append("from pessoa p, ");
		sb.append("pessoa_titular pt ");
		sb.append("where p.CD_PESSOA = pt.CD_PESSOA ");
		sb.append("and pt.NR_MATRICULA = :matricula ");
		sb.append("and pt.DT_CANCELAMENTO IS NULL ");

		sb.append("union ");

		sb.append("select p.CD_PESSOA, p.NM_PESSOA, 'DEPENDENTE' as tipo ");
		sb.append("from pessoa p, ");
		sb.append("pessoa_dependente pd ");
		sb.append("where p.CD_PESSOA = pd.CD_PESSOA ");
		sb.append("and pd.NR_MATRICULA = :matricula ");
		sb.append("and pd.DT_CANCELAMENTO IS NULL ");
		sb.append("and pd.DT_BLOQUEIO IS NULL ");
		sb.append(") r ");

		sb.append("order by tipo desc, nm_pessoa ");

		Query query = this.entityManager.createNativeQuery(sb.toString());
		query.setParameter("matricula", matricula);

		final List<Object[]> result = query.getResultList();

		List<PessoaDTO> retorno = new ArrayList<PessoaDTO>();

		for (final Object[] obj : result) {
			PessoaDTO pessoa = new PessoaDTO();
			int i = 0;
			pessoa.setCodigoPessoa((Integer) obj[i++]);
			pessoa.setNome((String) obj[i++]);
			pessoa.setTipo((String) obj[i++]);
			retorno.add(pessoa);
		}

		return retorno;
	}

	public PessoaDTO consultarPessoaPorCPF(String cpf) {
		StringBuilder sb = new StringBuilder();

		sb.append("select cd_pessoa, nm_pessoa, tipo ");

		sb.append("from ( ");

		sb.append("select p.CD_PESSOA, p.NM_PESSOA, 'TITULAR' as tipo ");
		sb.append("from pessoa p, ");
		sb.append("pessoa_titular pt ");
		sb.append("where p.CD_PESSOA = pt.CD_PESSOA ");
		sb.append("and p.nr_cpf = :cpf ");

		sb.append("union ");

		sb.append("select p.CD_PESSOA, p.NM_PESSOA, 'DEPENDENTE' as tipo ");
		sb.append("from pessoa p, ");
		sb.append("pessoa_dependente pd ");
		sb.append("where p.CD_PESSOA = pd.CD_PESSOA ");
		sb.append("and p.nr_cpf = :cpf ");

		sb.append(") r ");

		Query query = this.entityManager.createNativeQuery(sb.toString());
		query.setParameter("cpf", cpf);

		final List<Object[]> result = query.getResultList();

		PessoaDTO retorno = null;

		if (Utils.isListNaoNulaNaoVazia(result)) {
			Object[] obj = result.get(0);
			retorno = new PessoaDTO();
			int i = 0;
			retorno.setCodigoPessoa((Integer) obj[i++]);
			retorno.setNome((String) obj[i++]);

		}

		return retorno;
	}

	public List<EstatisticaClassificacaoRiscoDTO> consultarEstatisticaClassificacaoCovid(Integer ano, Integer mes) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DS_TIPO_CLASSIFICACAO_RISCO, NR_COR_HEX, ");
		sb.append(" D_01,D_02,D_03,D_04,D_05,D_06,D_07,D_08,D_09,D_10 ");
		sb.append(" ,D_11,D_12,D_13,D_14,D_15,D_16,D_17,D_18,D_19,D_20 ");
		sb.append(" ,D_21,D_22,D_23,D_24,D_25,D_26,D_27,D_28,D_29,D_30,D_31, ");
		sb.append(" CASE WHEN D_01 IS NULL THEN 0 ELSE D_01 END + ");
		sb.append(" CASE WHEN D_02 IS NULL THEN 0 ELSE D_02 END + ");
		sb.append(" CASE WHEN D_03 IS NULL THEN 0 ELSE D_03 END + ");
		sb.append(" CASE WHEN D_04 IS NULL THEN 0 ELSE D_04 END + ");
		sb.append(" CASE WHEN D_05 IS NULL THEN 0 ELSE D_05 END + ");
		sb.append(" CASE WHEN D_06 IS NULL THEN 0 ELSE D_06 END + ");
		sb.append(" CASE WHEN D_07 IS NULL THEN 0 ELSE D_07 END + ");
		sb.append(" CASE WHEN D_08 IS NULL THEN 0 ELSE D_08 END + ");
		sb.append(" CASE WHEN D_09 IS NULL THEN 0 ELSE D_09 END + ");
		sb.append(" CASE WHEN D_10 IS NULL THEN 0 ELSE D_10 END + ");
		sb.append(" CASE WHEN D_11 IS NULL THEN 0 ELSE D_11 END + ");
		sb.append(" CASE WHEN D_12 IS NULL THEN 0 ELSE D_12 END + ");
		sb.append(" CASE WHEN D_13 IS NULL THEN 0 ELSE D_13 END + ");
		sb.append(" CASE WHEN D_14 IS NULL THEN 0 ELSE D_14 END + ");
		sb.append(" CASE WHEN D_15 IS NULL THEN 0 ELSE D_15 END + ");
		sb.append(" CASE WHEN D_16 IS NULL THEN 0 ELSE D_16 END + ");
		sb.append(" CASE WHEN D_17 IS NULL THEN 0 ELSE D_17 END + ");
		sb.append(" CASE WHEN D_18 IS NULL THEN 0 ELSE D_18 END + ");
		sb.append(" CASE WHEN D_19 IS NULL THEN 0 ELSE D_19 END + ");
		sb.append(" CASE WHEN D_20 IS NULL THEN 0 ELSE D_20 END + ");
		sb.append(" CASE WHEN D_21 IS NULL THEN 0 ELSE D_21 END + ");
		sb.append(" CASE WHEN D_22 IS NULL THEN 0 ELSE D_22 END + ");
		sb.append(" CASE WHEN D_23 IS NULL THEN 0 ELSE D_23 END + ");
		sb.append(" CASE WHEN D_24 IS NULL THEN 0 ELSE D_24 END + ");
		sb.append(" CASE WHEN D_25 IS NULL THEN 0 ELSE D_25 END + ");
		sb.append(" CASE WHEN D_26 IS NULL THEN 0 ELSE D_26 END + ");
		sb.append(" CASE WHEN D_27 IS NULL THEN 0 ELSE D_27 END + ");
		sb.append(" CASE WHEN D_28 IS NULL THEN 0 ELSE D_28 END + ");
		sb.append(" CASE WHEN D_29 IS NULL THEN 0 ELSE D_29 END + ");
		sb.append(" CASE WHEN D_30 IS NULL THEN 0 ELSE D_30 END + ");
		sb.append(" CASE WHEN D_31 IS NULL THEN 0 ELSE D_31 END AS TOTAL ");
		sb.append(" FROM ( ");
		sb.append(" SELECT DS_TIPO_CLASSIFICACAO_RISCO, NR_COR_HEX, ");
		sb.append("  SUM(CASE WHEN DIA =  1 THEN QTDE END) 'D_01' ");
		sb.append(" ,SUM(CASE WHEN DIA =  2 THEN QTDE END) 'D_02' ");
		sb.append(" ,SUM(CASE WHEN DIA =  3 THEN QTDE END) 'D_03' ");
		sb.append(" ,SUM(CASE WHEN DIA =  4 THEN QTDE END) 'D_04' ");
		sb.append(" ,SUM(CASE WHEN DIA =  5 THEN QTDE END) 'D_05' ");
		sb.append(" ,SUM(CASE WHEN DIA =  6 THEN QTDE END) 'D_06' ");
		sb.append(" ,SUM(CASE WHEN DIA =  7 THEN QTDE END) 'D_07' ");
		sb.append(" ,SUM(CASE WHEN DIA =  8 THEN QTDE END) 'D_08' ");
		sb.append(" ,SUM(CASE WHEN DIA =  9 THEN QTDE END) 'D_09' ");
		sb.append(" ,SUM(CASE WHEN DIA = 10 THEN QTDE END) 'D_10' ");
		sb.append(" ,SUM(CASE WHEN DIA = 11 THEN QTDE END) 'D_11' ");
		sb.append(" ,SUM(CASE WHEN DIA = 12 THEN QTDE END) 'D_12' ");
		sb.append(" ,SUM(CASE WHEN DIA = 13 THEN QTDE END) 'D_13' ");
		sb.append(" ,SUM(CASE WHEN DIA = 14 THEN QTDE END) 'D_14' ");
		sb.append(" ,SUM(CASE WHEN DIA = 15 THEN QTDE END) 'D_15' ");
		sb.append(" ,SUM(CASE WHEN DIA = 16 THEN QTDE END) 'D_16' ");
		sb.append(" ,SUM(CASE WHEN DIA = 17 THEN QTDE END) 'D_17' ");
		sb.append(" ,SUM(CASE WHEN DIA = 18 THEN QTDE END) 'D_18' ");
		sb.append(" ,SUM(CASE WHEN DIA = 19 THEN QTDE END) 'D_19' ");
		sb.append(" ,SUM(CASE WHEN DIA = 20 THEN QTDE END) 'D_20' ");
		sb.append(" ,SUM(CASE WHEN DIA = 21 THEN QTDE END) 'D_21' ");
		sb.append(" ,SUM(CASE WHEN DIA = 22 THEN QTDE END) 'D_22' ");
		sb.append(" ,SUM(CASE WHEN DIA = 23 THEN QTDE END) 'D_23' ");
		sb.append(" ,SUM(CASE WHEN DIA = 24 THEN QTDE END) 'D_24' ");
		sb.append(" ,SUM(CASE WHEN DIA = 25 THEN QTDE END) 'D_25' ");
		sb.append(" ,SUM(CASE WHEN DIA = 26 THEN QTDE END) 'D_26' ");
		sb.append(" ,SUM(CASE WHEN DIA = 27 THEN QTDE END) 'D_27' ");
		sb.append(" ,SUM(CASE WHEN DIA = 28 THEN QTDE END) 'D_28' ");
		sb.append(" ,SUM(CASE WHEN DIA = 29 THEN QTDE END) 'D_29' ");
		sb.append(" ,SUM(CASE WHEN DIA = 30 THEN QTDE END) 'D_30' ");
		sb.append(" ,SUM(CASE WHEN DIA = 31 THEN QTDE END) 'D_31' ");
		sb.append(" FROM( ");
		sb.append(
				" SELECT DAY(cr.DT_CLASSIFICACAO_RISCO) DIA, tcr.DS_TIPO_CLASSIFICACAO_RISCO, tcr.NR_COR_HEX, COUNT(*) QTDE  ");
		sb.append(" FROM classificacao_risco cr, tipo_classificacao_risco tcr ");
		sb.append(" 	 WHERE cr.CD_TIPO_CLASSIFICACAO_RISCO = tcr.CD_TIPO_CLASSIFICACAO_RISCO ");
		sb.append(" 	 AND YEAR(cr.DT_CLASSIFICACAO_RISCO) = :ano ");
		sb.append(" 	 AND MONTH(cr.DT_CLASSIFICACAO_RISCO) BETWEEN :mes AND :mes  ");
		sb.append(" GROUP BY DAY(cr.DT_CLASSIFICACAO_RISCO), tcr.DS_TIPO_CLASSIFICACAO_RISCO ");
		sb.append(") atend ");
		sb.append("GROUP BY DS_TIPO_CLASSIFICACAO_RISCO) atend2 ");

		Query query = this.entityManager.createNativeQuery(sb.toString());
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);

		final List<Object[]> list = query.getResultList();

		List<EstatisticaClassificacaoRiscoDTO> retorno = new ArrayList<EstatisticaClassificacaoRiscoDTO>();

		for (final Object[] obj : list) {
			EstatisticaClassificacaoRiscoDTO dto = new EstatisticaClassificacaoRiscoDTO();
			dto.setDescricao((String) obj[0]);
			dto.setCor((String) obj[1]);

			for (int i = 2; i < obj.length - 1; i++) {
				dto.getQuantitavivos().add((obj[i] != null ? ((BigDecimal) obj[i]).intValue() : 0));
			}
			retorno.add(dto);
		}
		return retorno;
	}

	private Date getDate(String data) {
		Date retorno = null;
		try {
			data += " 07:00:00";
			SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			retorno = f1.parse(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	private Date getDatePosterior(String data) {
		Date retorno = null;
		try {
			LocalDateTime t1 = new LocalDateTime(getDate(data));
			t1 = t1.plusDays(1);
			retorno = t1.toDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public List<PacienteDTO> consultarClassificacaoCovidPorPlantao(String data, String nome, String sexo, String tipo,
			String categoria, String corporacao, String situacao, String turno) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * ");
		sb.append("FROM ( ");
		sb.append("select cast(concat(pt.NR_MATRICULA, '-', pt.NR_DIGITO) as char) as MATRICULA, ");
		sb.append("p.NM_PESSOA, ");
		sb.append("p.TP_SEXO, ");

		sb.append("TIMESTAMPDIFF (YEAR, p.DT_NASCIMENTO, CURDATE()) as idade, ");

		sb.append("'TITULAR' AS TIPO, ");
		sb.append("(CASE ");
		sb.append("	WHEN pt.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("    WHEN pt.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("    ELSE 'NÃO INFORMADO' ");
		sb.append("END ");
		sb.append(") as corporacao, ");
		sb.append("(CASE ");
		sb.append("	WHEN pt.TP_TABELA = 1 then 'ATIVO' ");
		sb.append("    WHEN pt.TP_TABELA = 2 then 'INATIVO' ");
		sb.append("    ELSE 'NÃO INFORMADO' ");
		sb.append("END ");
		sb.append(") as situacao, ");

		sb.append("(case ");
		sb.append("	when po.TP_CATEGORIA = 'O' then 'MIL' ");
		sb.append("    when po.TP_CATEGORIA = 'P' then 'MIL' ");
		sb.append("    when po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 then 'PENS' ");
		sb.append("    when po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 then 'FC' ");
		sb.append("	else 'NÃO INFORMADO' ");
		sb.append("    end) as categoria, ");

		sb.append("( ");
		sb.append("case ");
		sb.append("when (hour(cr.DT_CLASSIFICACAO_RISCO) >= 0 and hour(cr.DT_CLASSIFICACAO_RISCO) < 7) then 'N' ");
		sb.append("when (hour(cr.DT_CLASSIFICACAO_RISCO) >= 7 and hour(cr.DT_CLASSIFICACAO_RISCO) < 19) then 'D' ");
		sb.append("when (hour(cr.DT_CLASSIFICACAO_RISCO) >= 19) then 'N' ");

		sb.append("end)as turno ");

		sb.append("from classificacao_risco cr, ");
		sb.append("atendimento a, ");
		sb.append("paciente pac, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO, ");
		sb.append("pessoa p ");
		sb.append("where cr.CD_TIPO_CLASSIFICACAO_RISCO = 5 ");
		sb.append("and cr.CD_ATENDIMENTO = a.CD_ATENDIMENTO ");
		sb.append("and a.CD_PACIENTE = pac.CD_PACIENTE ");
		sb.append("and pac.CD_PESSOA = pt.CD_PESSOA ");
		sb.append("and pt.CD_PESSOA = p.CD_PESSOA ");
		sb.append("and cr.DT_CLASSIFICACAO_RISCO >= :data1 ");
		sb.append("and cr.DT_CLASSIFICACAO_RISCO < :data2 ");

		sb.append("union ");

		sb.append(
				"select cast(concat(pt.NR_MATRICULA, '-', pt.NR_DIGITO, '/', pd.NR_SEQUENCIAL) as char) as MATRICULA, ");
		sb.append("p.NM_PESSOA, ");
		sb.append("p.TP_SEXO, ");
		sb.append("TIMESTAMPDIFF (YEAR, p.DT_NASCIMENTO, CURDATE()) as idade, ");
		sb.append("'DEPENDENTE' AS TIPO, ");
		sb.append("(CASE ");
		sb.append("	WHEN pd.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("    WHEN pd.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("    ELSE 'NÃO INFORMADO' ");
		sb.append("END ");
		sb.append(") as corporacao, ");

		sb.append("(CASE ");
		sb.append("	WHEN pt.TP_TABELA = 1 then 'ATIVO' ");
		sb.append("    WHEN pt.TP_TABELA = 2 then 'INATIVO' ");
		sb.append("    ELSE 'NÃO INFORMADO' ");
		sb.append("END) as situacao, ");

		sb.append("(CASE ");
		sb.append("	when po.TP_CATEGORIA = 'O' then 'MIL' ");
		sb.append("    when po.TP_CATEGORIA = 'P' then 'MIL' ");
		sb.append("    when po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 then 'PENS' ");
		sb.append("    when po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 then 'FC' ");
		sb.append("	else 'NÃO INFORMADO' ");
		sb.append("END) as categoria, ");

		sb.append("( ");
		sb.append("case ");
		sb.append("when (hour(cr.DT_CLASSIFICACAO_RISCO) >= 0 and hour(cr.DT_CLASSIFICACAO_RISCO) < 7) then 'D' ");
		sb.append("when (hour(cr.DT_CLASSIFICACAO_RISCO) >= 7 and hour(cr.DT_CLASSIFICACAO_RISCO) < 19) then 'D' ");
		sb.append("when (hour(cr.DT_CLASSIFICACAO_RISCO) >= 19) then 'N' ");

		sb.append("end)as turno ");

		sb.append("from classificacao_risco cr, ");
		sb.append("atendimento a, ");
		sb.append("paciente pac, ");
		sb.append("pessoa_dependente pd, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO, ");
		sb.append("pessoa p ");
		sb.append("where cr.CD_TIPO_CLASSIFICACAO_RISCO = 5 ");
		sb.append("and cr.CD_ATENDIMENTO = a.CD_ATENDIMENTO ");
		sb.append("and a.CD_PACIENTE = pac.CD_PACIENTE ");
		sb.append("and pac.CD_PESSOA = pd.CD_PESSOA ");
		sb.append("and pd.CD_PESSOA = p.CD_PESSOA ");
		sb.append("and pd.NR_MATRICULA = pt.NR_MATRICULA ");
		sb.append("and pd.CD_CORPORACAO = pt.CD_CORPORACAO ");
		sb.append("and cr.DT_CLASSIFICACAO_RISCO >= :data1 ");
		sb.append("and cr.DT_CLASSIFICACAO_RISCO < :data2 ");
		sb.append(") r ");

		sb.append(" WHERE 1=1 ");

		if (nome != null && !nome.isEmpty()) {
			sb.append("AND NM_PESSOA LIKE :nome ");
		}

		if (sexo != null && !sexo.isEmpty()) {
			sb.append("AND TP_SEXO = :sexo ");
		}

		if (tipo != null && !tipo.isEmpty()) {
			sb.append("AND TIPO = :tipo ");
		}

		if (corporacao != null && !corporacao.isEmpty()) {
			sb.append("AND CORPORACAO = :corporacao ");
		}

		if (situacao != null && !situacao.isEmpty()) {
			sb.append("AND SITUACAO = :situacao ");
		}

		if (categoria != null && !categoria.isEmpty()) {
			sb.append("AND CATEGORIA = :categoria ");
		}

		if (turno != null && !turno.isEmpty()) {
			sb.append("AND TURNO = :turno ");
		}

		sb.append("order by r.turno, r.nm_pessoa ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		Date d1 = getDate(data);

		if (data != null && !data.isEmpty()) {
			query.setParameter("data1", d1);
		}

		Date d2 = getDatePosterior(data);

		if (data != null && !data.isEmpty()) {
			query.setParameter("data2", d2);
		}

		if (nome != null && !nome.isEmpty()) {
			query.setParameter("nome", "%" + nome + "%");
		}

		if (sexo != null && !sexo.isEmpty()) {
			query.setParameter("sexo", sexo);
		}

		if (tipo != null && !tipo.isEmpty()) {
			query.setParameter("tipo", tipo);
		}

		if (categoria != null && !categoria.isEmpty()) {
			query.setParameter("categoria", categoria);
		}

		if (corporacao != null && !corporacao.isEmpty()) {
			query.setParameter("corporacao", corporacao);
		}

		if (situacao != null && !situacao.isEmpty()) {
			query.setParameter("situacao", situacao);
		}

		if (turno != null && !turno.isEmpty()) {
			query.setParameter("turno", turno);
		}

		final List<Object[]> list = query.getResultList();

		List<PacienteDTO> retorno = new ArrayList<PacienteDTO>();

		for (final Object[] obj : list) {
			PacienteDTO dto = new PacienteDTO();
			dto.setMatricula((String) obj[0]);
			dto.setNome((String) obj[1]);
			dto.setSexo((String) obj[2]);
			dto.setIdade(((BigInteger) obj[3]).intValue());
			dto.setTipo((String) obj[4]);
			dto.setCorporacao((String) obj[5]);
			dto.setSituacao((String) obj[6]);
			dto.setCategoria((String) obj[7]);
			dto.setTurno((String) obj[8]);

			retorno.add(dto);
		}
		return retorno;
	}

	public List<AdmissaoInternacaoDTO> consultarAdmissoesInternacaoCovid(Integer ano, Integer mes) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DS_UNIDADE_INTERNACAO, NR_COR_HEX, ");
		sb.append(" D_01,D_02,D_03,D_04,D_05,D_06,D_07,D_08,D_09,D_10 ");
		sb.append(" ,D_11,D_12,D_13,D_14,D_15,D_16,D_17,D_18,D_19,D_20 ");
		sb.append(" ,D_21,D_22,D_23,D_24,D_25,D_26,D_27,D_28,D_29,D_30,D_31, ");
		sb.append(" CASE WHEN D_01 IS NULL THEN 0 ELSE D_01 END + ");
		sb.append(" CASE WHEN D_02 IS NULL THEN 0 ELSE D_02 END + ");
		sb.append(" CASE WHEN D_03 IS NULL THEN 0 ELSE D_03 END + ");
		sb.append(" CASE WHEN D_04 IS NULL THEN 0 ELSE D_04 END + ");
		sb.append(" CASE WHEN D_05 IS NULL THEN 0 ELSE D_05 END + ");
		sb.append(" CASE WHEN D_06 IS NULL THEN 0 ELSE D_06 END + ");
		sb.append(" CASE WHEN D_07 IS NULL THEN 0 ELSE D_07 END + ");
		sb.append(" CASE WHEN D_08 IS NULL THEN 0 ELSE D_08 END + ");
		sb.append(" CASE WHEN D_09 IS NULL THEN 0 ELSE D_09 END + ");
		sb.append(" CASE WHEN D_10 IS NULL THEN 0 ELSE D_10 END + ");
		sb.append(" CASE WHEN D_11 IS NULL THEN 0 ELSE D_11 END + ");
		sb.append(" CASE WHEN D_12 IS NULL THEN 0 ELSE D_12 END + ");
		sb.append(" CASE WHEN D_13 IS NULL THEN 0 ELSE D_13 END + ");
		sb.append(" CASE WHEN D_14 IS NULL THEN 0 ELSE D_14 END + ");
		sb.append(" CASE WHEN D_15 IS NULL THEN 0 ELSE D_15 END + ");
		sb.append(" CASE WHEN D_16 IS NULL THEN 0 ELSE D_16 END + ");
		sb.append(" CASE WHEN D_17 IS NULL THEN 0 ELSE D_17 END + ");
		sb.append(" CASE WHEN D_18 IS NULL THEN 0 ELSE D_18 END + ");
		sb.append(" CASE WHEN D_19 IS NULL THEN 0 ELSE D_19 END + ");
		sb.append(" CASE WHEN D_20 IS NULL THEN 0 ELSE D_20 END + ");
		sb.append(" CASE WHEN D_21 IS NULL THEN 0 ELSE D_21 END + ");
		sb.append(" CASE WHEN D_22 IS NULL THEN 0 ELSE D_22 END + ");
		sb.append(" CASE WHEN D_23 IS NULL THEN 0 ELSE D_23 END + ");
		sb.append(" CASE WHEN D_24 IS NULL THEN 0 ELSE D_24 END + ");
		sb.append(" CASE WHEN D_25 IS NULL THEN 0 ELSE D_25 END + ");
		sb.append(" CASE WHEN D_26 IS NULL THEN 0 ELSE D_26 END + ");
		sb.append(" CASE WHEN D_27 IS NULL THEN 0 ELSE D_27 END + ");
		sb.append(" CASE WHEN D_28 IS NULL THEN 0 ELSE D_28 END + ");
		sb.append(" CASE WHEN D_29 IS NULL THEN 0 ELSE D_29 END + ");
		sb.append(" CASE WHEN D_30 IS NULL THEN 0 ELSE D_30 END + ");
		sb.append(" CASE WHEN D_31 IS NULL THEN 0 ELSE D_31 END AS TOTAL ");
		sb.append(" FROM ( ");
		sb.append(" SELECT DS_UNIDADE_INTERNACAO, NR_COR_HEX, ");
		sb.append("  SUM(CASE WHEN DIA =  1 THEN QTDE END) 'D_01' ");
		sb.append(" ,SUM(CASE WHEN DIA =  2 THEN QTDE END) 'D_02' ");
		sb.append(" ,SUM(CASE WHEN DIA =  3 THEN QTDE END) 'D_03' ");
		sb.append(" ,SUM(CASE WHEN DIA =  4 THEN QTDE END) 'D_04' ");
		sb.append(" ,SUM(CASE WHEN DIA =  5 THEN QTDE END) 'D_05' ");
		sb.append(" ,SUM(CASE WHEN DIA =  6 THEN QTDE END) 'D_06' ");
		sb.append(" ,SUM(CASE WHEN DIA =  7 THEN QTDE END) 'D_07' ");
		sb.append(" ,SUM(CASE WHEN DIA =  8 THEN QTDE END) 'D_08' ");
		sb.append(" ,SUM(CASE WHEN DIA =  9 THEN QTDE END) 'D_09' ");
		sb.append(" ,SUM(CASE WHEN DIA = 10 THEN QTDE END) 'D_10' ");
		sb.append(" ,SUM(CASE WHEN DIA = 11 THEN QTDE END) 'D_11' ");
		sb.append(" ,SUM(CASE WHEN DIA = 12 THEN QTDE END) 'D_12' ");
		sb.append(" ,SUM(CASE WHEN DIA = 13 THEN QTDE END) 'D_13' ");
		sb.append(" ,SUM(CASE WHEN DIA = 14 THEN QTDE END) 'D_14' ");
		sb.append(" ,SUM(CASE WHEN DIA = 15 THEN QTDE END) 'D_15' ");
		sb.append(" ,SUM(CASE WHEN DIA = 16 THEN QTDE END) 'D_16' ");
		sb.append(" ,SUM(CASE WHEN DIA = 17 THEN QTDE END) 'D_17' ");
		sb.append(" ,SUM(CASE WHEN DIA = 18 THEN QTDE END) 'D_18' ");
		sb.append(" ,SUM(CASE WHEN DIA = 19 THEN QTDE END) 'D_19' ");
		sb.append(" ,SUM(CASE WHEN DIA = 20 THEN QTDE END) 'D_20' ");
		sb.append(" ,SUM(CASE WHEN DIA = 21 THEN QTDE END) 'D_21' ");
		sb.append(" ,SUM(CASE WHEN DIA = 22 THEN QTDE END) 'D_22' ");
		sb.append(" ,SUM(CASE WHEN DIA = 23 THEN QTDE END) 'D_23' ");
		sb.append(" ,SUM(CASE WHEN DIA = 24 THEN QTDE END) 'D_24' ");
		sb.append(" ,SUM(CASE WHEN DIA = 25 THEN QTDE END) 'D_25' ");
		sb.append(" ,SUM(CASE WHEN DIA = 26 THEN QTDE END) 'D_26' ");
		sb.append(" ,SUM(CASE WHEN DIA = 27 THEN QTDE END) 'D_27' ");
		sb.append(" ,SUM(CASE WHEN DIA = 28 THEN QTDE END) 'D_28' ");
		sb.append(" ,SUM(CASE WHEN DIA = 29 THEN QTDE END) 'D_29' ");
		sb.append(" ,SUM(CASE WHEN DIA = 30 THEN QTDE END) 'D_30' ");
		sb.append(" ,SUM(CASE WHEN DIA = 31 THEN QTDE END) 'D_31' ");
		sb.append(" FROM( ");
		sb.append(" SELECT ");
		sb.append(" DAY(a.DT_ABERTURA) DIA, ");
		sb.append(" ui.DS_UNIDADE_INTERNACAO, ");
		sb.append(" ui.NR_COR_HEX, ");
		sb.append(" COUNT(*) QTDE ");
		sb.append(" FROM ");
		sb.append(" atendimento a, ");
		sb.append(" atendimento_int ai, ");
		sb.append(" leito l, ");
		sb.append(" unidade_internacao ui ");
		sb.append(" WHERE ");
		sb.append(" a.CD_ATENDIMENTO = ai.CD_ATENDIMENTO ");
		sb.append(" and ai.CD_LEITO = l.CD_LEITO ");
		sb.append(" and l.CD_UNIDADE_INTERNACAO = ui.CD_UNIDADE_INTERNACAO ");
		sb.append(" AND YEAR(a.DT_ABERTURA) = :ano ");
		sb.append(" AND MONTH(a.DT_ABERTURA) BETWEEN :mes AND :mes ");
		sb.append(" and ui.sn_covid = 'S' ");
		sb.append(" GROUP BY ");
		sb.append(" DAY(a.DT_ABERTURA), ");
		sb.append(" ui.DS_UNIDADE_INTERNACAO) atend ");
		sb.append(" GROUP BY ");
		sb.append(" DS_UNIDADE_INTERNACAO) atend2 ");

		Query query = this.entityManager.createNativeQuery(sb.toString());
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);

		final List<Object[]> list = query.getResultList();

		List<AdmissaoInternacaoDTO> retorno = new ArrayList<AdmissaoInternacaoDTO>();

		for (final Object[] obj : list) {
			AdmissaoInternacaoDTO dto = new AdmissaoInternacaoDTO();
			dto.setDescricao((String) obj[0]);
			dto.setCor((String) obj[1]);

			for (int i = 2; i < obj.length - 1; i++) {
				dto.getQuantitavivos().add((obj[i] != null ? ((BigDecimal) obj[i]).intValue() : 0));
			}
			retorno.add(dto);
		}
		return retorno;
	}

	public List<AltaInternacaoDTO> consultarAltasInternacaoCovid(Integer ano, Integer mes) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DS_UNIDADE_INTERNACAO, NR_COR_HEX, ");
		sb.append(" D_01,D_02,D_03,D_04,D_05,D_06,D_07,D_08,D_09,D_10 ");
		sb.append(" ,D_11,D_12,D_13,D_14,D_15,D_16,D_17,D_18,D_19,D_20 ");
		sb.append(" ,D_21,D_22,D_23,D_24,D_25,D_26,D_27,D_28,D_29,D_30,D_31, ");
		sb.append(" CASE WHEN D_01 IS NULL THEN 0 ELSE D_01 END + ");
		sb.append(" CASE WHEN D_02 IS NULL THEN 0 ELSE D_02 END + ");
		sb.append(" CASE WHEN D_03 IS NULL THEN 0 ELSE D_03 END + ");
		sb.append(" CASE WHEN D_04 IS NULL THEN 0 ELSE D_04 END + ");
		sb.append(" CASE WHEN D_05 IS NULL THEN 0 ELSE D_05 END + ");
		sb.append(" CASE WHEN D_06 IS NULL THEN 0 ELSE D_06 END + ");
		sb.append(" CASE WHEN D_07 IS NULL THEN 0 ELSE D_07 END + ");
		sb.append(" CASE WHEN D_08 IS NULL THEN 0 ELSE D_08 END + ");
		sb.append(" CASE WHEN D_09 IS NULL THEN 0 ELSE D_09 END + ");
		sb.append(" CASE WHEN D_10 IS NULL THEN 0 ELSE D_10 END + ");
		sb.append(" CASE WHEN D_11 IS NULL THEN 0 ELSE D_11 END + ");
		sb.append(" CASE WHEN D_12 IS NULL THEN 0 ELSE D_12 END + ");
		sb.append(" CASE WHEN D_13 IS NULL THEN 0 ELSE D_13 END + ");
		sb.append(" CASE WHEN D_14 IS NULL THEN 0 ELSE D_14 END + ");
		sb.append(" CASE WHEN D_15 IS NULL THEN 0 ELSE D_15 END + ");
		sb.append(" CASE WHEN D_16 IS NULL THEN 0 ELSE D_16 END + ");
		sb.append(" CASE WHEN D_17 IS NULL THEN 0 ELSE D_17 END + ");
		sb.append(" CASE WHEN D_18 IS NULL THEN 0 ELSE D_18 END + ");
		sb.append(" CASE WHEN D_19 IS NULL THEN 0 ELSE D_19 END + ");
		sb.append(" CASE WHEN D_20 IS NULL THEN 0 ELSE D_20 END + ");
		sb.append(" CASE WHEN D_21 IS NULL THEN 0 ELSE D_21 END + ");
		sb.append(" CASE WHEN D_22 IS NULL THEN 0 ELSE D_22 END + ");
		sb.append(" CASE WHEN D_23 IS NULL THEN 0 ELSE D_23 END + ");
		sb.append(" CASE WHEN D_24 IS NULL THEN 0 ELSE D_24 END + ");
		sb.append(" CASE WHEN D_25 IS NULL THEN 0 ELSE D_25 END + ");
		sb.append(" CASE WHEN D_26 IS NULL THEN 0 ELSE D_26 END + ");
		sb.append(" CASE WHEN D_27 IS NULL THEN 0 ELSE D_27 END + ");
		sb.append(" CASE WHEN D_28 IS NULL THEN 0 ELSE D_28 END + ");
		sb.append(" CASE WHEN D_29 IS NULL THEN 0 ELSE D_29 END + ");
		sb.append(" CASE WHEN D_30 IS NULL THEN 0 ELSE D_30 END + ");
		sb.append(" CASE WHEN D_31 IS NULL THEN 0 ELSE D_31 END AS TOTAL ");
		sb.append(" FROM ( ");
		sb.append(" SELECT DS_UNIDADE_INTERNACAO, NR_COR_HEX, ");
		sb.append("  SUM(CASE WHEN DIA =  1 THEN QTDE END) 'D_01' ");
		sb.append(" ,SUM(CASE WHEN DIA =  2 THEN QTDE END) 'D_02' ");
		sb.append(" ,SUM(CASE WHEN DIA =  3 THEN QTDE END) 'D_03' ");
		sb.append(" ,SUM(CASE WHEN DIA =  4 THEN QTDE END) 'D_04' ");
		sb.append(" ,SUM(CASE WHEN DIA =  5 THEN QTDE END) 'D_05' ");
		sb.append(" ,SUM(CASE WHEN DIA =  6 THEN QTDE END) 'D_06' ");
		sb.append(" ,SUM(CASE WHEN DIA =  7 THEN QTDE END) 'D_07' ");
		sb.append(" ,SUM(CASE WHEN DIA =  8 THEN QTDE END) 'D_08' ");
		sb.append(" ,SUM(CASE WHEN DIA =  9 THEN QTDE END) 'D_09' ");
		sb.append(" ,SUM(CASE WHEN DIA = 10 THEN QTDE END) 'D_10' ");
		sb.append(" ,SUM(CASE WHEN DIA = 11 THEN QTDE END) 'D_11' ");
		sb.append(" ,SUM(CASE WHEN DIA = 12 THEN QTDE END) 'D_12' ");
		sb.append(" ,SUM(CASE WHEN DIA = 13 THEN QTDE END) 'D_13' ");
		sb.append(" ,SUM(CASE WHEN DIA = 14 THEN QTDE END) 'D_14' ");
		sb.append(" ,SUM(CASE WHEN DIA = 15 THEN QTDE END) 'D_15' ");
		sb.append(" ,SUM(CASE WHEN DIA = 16 THEN QTDE END) 'D_16' ");
		sb.append(" ,SUM(CASE WHEN DIA = 17 THEN QTDE END) 'D_17' ");
		sb.append(" ,SUM(CASE WHEN DIA = 18 THEN QTDE END) 'D_18' ");
		sb.append(" ,SUM(CASE WHEN DIA = 19 THEN QTDE END) 'D_19' ");
		sb.append(" ,SUM(CASE WHEN DIA = 20 THEN QTDE END) 'D_20' ");
		sb.append(" ,SUM(CASE WHEN DIA = 21 THEN QTDE END) 'D_21' ");
		sb.append(" ,SUM(CASE WHEN DIA = 22 THEN QTDE END) 'D_22' ");
		sb.append(" ,SUM(CASE WHEN DIA = 23 THEN QTDE END) 'D_23' ");
		sb.append(" ,SUM(CASE WHEN DIA = 24 THEN QTDE END) 'D_24' ");
		sb.append(" ,SUM(CASE WHEN DIA = 25 THEN QTDE END) 'D_25' ");
		sb.append(" ,SUM(CASE WHEN DIA = 26 THEN QTDE END) 'D_26' ");
		sb.append(" ,SUM(CASE WHEN DIA = 27 THEN QTDE END) 'D_27' ");
		sb.append(" ,SUM(CASE WHEN DIA = 28 THEN QTDE END) 'D_28' ");
		sb.append(" ,SUM(CASE WHEN DIA = 29 THEN QTDE END) 'D_29' ");
		sb.append(" ,SUM(CASE WHEN DIA = 30 THEN QTDE END) 'D_30' ");
		sb.append(" ,SUM(CASE WHEN DIA = 31 THEN QTDE END) 'D_31' ");
		sb.append(" FROM( ");

		sb.append(" SELECT ");
		sb.append("         DAY(a.DT_FINALIZACAO) DIA, ");
		sb.append("         ui.DS_UNIDADE_INTERNACAO, ");
		sb.append("         ui.NR_COR_HEX, ");
		sb.append("         COUNT(*) QTDE ");
		sb.append("     FROM ");
		sb.append("         atendimento a, ");
		sb.append("         atendimento_int ai, ");
		sb.append("         leito l, ");
		sb.append("         unidade_internacao ui ");
		sb.append("     WHERE ");
		sb.append("         a.CD_ATENDIMENTO = ai.CD_ATENDIMENTO ");
		sb.append("         and ai.CD_LEITO = l.CD_LEITO ");
		sb.append("         and l.CD_UNIDADE_INTERNACAO = ui.CD_UNIDADE_INTERNACAO ");
		sb.append("         AND YEAR(a.DT_FINALIZACAO) = :ano ");
		sb.append("         AND MONTH(a.DT_FINALIZACAO) BETWEEN :mes AND :mes ");
		sb.append("         and ui.sn_covid = 'S' ");
		sb.append("     GROUP BY ");
		sb.append("         DAY(a.DT_FINALIZACAO), ");
		sb.append("         ui.DS_UNIDADE_INTERNACAO) atend ");
		sb.append(" GROUP BY ");
		sb.append("     DS_UNIDADE_INTERNACAO) atend2 ");

		Query query = this.entityManager.createNativeQuery(sb.toString());
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);

		final List<Object[]> list = query.getResultList();

		List<AltaInternacaoDTO> retorno = new ArrayList<AltaInternacaoDTO>();

		for (final Object[] obj : list) {
			AltaInternacaoDTO dto = new AltaInternacaoDTO();
			dto.setDescricao((String) obj[0]);
			dto.setCor((String) obj[1]);

			for (int i = 2; i < obj.length - 1; i++) {
				dto.getQuantitavivos().add((obj[i] != null ? ((BigDecimal) obj[i]).intValue() : 0));
			}
			retorno.add(dto);
		}
		return retorno;
	}

	public List<PacienteDTO> consultarPacientesInternadosCovid(String nome, String sexo, String unidade, String tipo,
			String categoria, String corporacao, String situacao, String status) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ( ");
		sb.append("		SELECT ");
		sb.append("		  cast(concat(pt.NR_MATRICULA, '-', pt.NR_DIGITO) as char) as MATRICULA ");
		sb.append("		  ,P.NM_PESSOA AS NM_PACIENTE ");
		sb.append("		  ,P.TP_SEXO ");
		sb.append("		  ,FLOOR((DATEDIFF(CURDATE(),p.dt_nascimento) / 365)) AS DS_IDADE ");
		sb.append("		  ,UI.CD_UNIDADE_INTERNACAO ");
		sb.append("		  ,UI.DS_UNIDADE_INTERNACAO ");
		sb.append("		  ,'TITULAR' as tipo, ");
		sb.append("		  (case ");
		sb.append("			when po.TP_CATEGORIA = 'O' then 'MIL' ");
		sb.append("		    when po.TP_CATEGORIA = 'P' then 'MIL' ");
		sb.append("		    when po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 then 'PENS' ");
		sb.append("		    when po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 then 'FC' ");
		sb.append("			else 'NÃO INFORMADO' ");
		sb.append("		    end) as categoria, ");
		sb.append("		    (CASE ");
		sb.append("			WHEN pt.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("		    WHEN pt.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("		    ELSE 'NÃO INFORMADO' ");
		sb.append("		END ");
		sb.append("		) as corporacao, ");
		sb.append("		(CASE ");
		sb.append("			WHEN pt.TP_TABELA = 1 then 'ATIVO' ");
		sb.append("		    WHEN pt.TP_TABELA = 2 then 'INATIVO' ");
		sb.append("		    ELSE 'NÃO INFORMADO' ");
		sb.append("		END ");
		sb.append("		) as situacao, ");

		sb.append("		(CASE ");
		sb.append("			WHEN sc.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO' ");
		sb.append("         WHEN sc.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO' ");
		sb.append("         WHEN sc.DT_COLETA_EXAME IS NOT NULL THEN 'AGUARDANDO' ");
		sb.append("         WHEN sc.CD_SUSPEITA_COVID19 is NOT NULL THEN 'SUSPEITO' ");
		sb.append("     END) as COVID ");

		sb.append("		FROM ATENDIMENTO_INT I, ");
		sb.append(
				"		     ATENDIMENTO A LEFT OUTER JOIN suspeita_covid19 sc ON a.`CD_ATENDIMENTO` = sc.`CD_ATENDIMENTO` AND sc.SN_ATIVO = 'S', ");
		sb.append("		     PACIENTE PAC, ");
		sb.append("		     LEITO L, ");
		sb.append("		     PESSOA P, ");
		sb.append("		     pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO, ");
		sb.append("		     UNIDADE_INTERNACAO UI ");
		sb.append("		WHERE ");
		sb.append("		     A.CD_SITUACAO_ATENDIMENTO in (1,2,4) ");
		sb.append("			 AND I.CD_PRESTADOR_ALTA is null ");
		sb.append("			 AND A.DT_FINALIZACAO IS NULL ");
		sb.append("			 AND A.DT_ABERTURA >= '2016-09-01' ");
		sb.append("			 AND A.CD_ORIGEM_ATEND = '2' ");
		sb.append("		     AND I.CD_ATENDIMENTO = A.CD_ATENDIMENTO ");
		sb.append("		     AND A.CD_PACIENTE = PAC.CD_PACIENTE ");
		sb.append("		     AND I.CD_LEITO = L.CD_LEITO ");
		sb.append("		     AND P.CD_PESSOA = PAC.CD_PESSOA ");
		sb.append("		     and p.cd_pessoa = pt.CD_PESSOA ");
		sb.append("		     AND UI.CD_UNIDADE_INTERNACAO = L.CD_UNIDADE_INTERNACAO ");
		sb.append("		 	 AND UI.SN_COVID = 'S' ");
		sb.append("		UNION ");

		sb.append("		SELECT ");

		sb.append(
				"		  cast(concat(pt.NR_MATRICULA, '-', pt.NR_DIGITO, '/', pd.NR_SEQUENCIAL) as char) as MATRICULA ");
		sb.append("		  ,P.NM_PESSOA AS NM_PACIENTE ");
		sb.append("		  ,P.TP_SEXO ");
		sb.append("		  ,FLOOR((DATEDIFF(CURDATE(),p.dt_nascimento) / 365)) AS DS_IDADE ");
		sb.append("		  ,UI.CD_UNIDADE_INTERNACAO ");
		sb.append("		  ,UI.DS_UNIDADE_INTERNACAO ");
		sb.append("		  ,'DEPENDENTE' as tipo, ");
		sb.append("		  (case ");
		sb.append("			when po.TP_CATEGORIA = 'O' then 'MIL' ");
		sb.append("		    when po.TP_CATEGORIA = 'P' then 'MIL' ");
		sb.append("		    when po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 then 'PENS' ");
		sb.append("		    when po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 then 'FC' ");
		sb.append("			else 'NÃO INFORMADO' ");
		sb.append("		    end) as categoria, ");
		sb.append("		    (CASE ");
		sb.append("			WHEN pt.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("		    WHEN pt.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("		    ELSE 'NÃO INFORMADO' ");
		sb.append("		END ");
		sb.append("		) as corporacao, ");
		sb.append("		(CASE ");
		sb.append("			WHEN pt.TP_TABELA = 1 then 'ATIVO' ");
		sb.append("		    WHEN pt.TP_TABELA = 2 then 'INATIVO' ");
		sb.append("		    ELSE 'NÃO INFORMADO' ");
		sb.append("		END ");
		sb.append("		) as situacao, ");

		sb.append("		(CASE ");
		sb.append("			WHEN sc.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO' ");
		sb.append("         WHEN sc.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO' ");
		sb.append("         WHEN sc.DT_COLETA_EXAME IS NOT NULL THEN 'AGUARDANDO' ");
		sb.append("         WHEN sc.CD_SUSPEITA_COVID19 is NOT NULL THEN 'SUSPEITO' ");
		sb.append("     END) as COVID ");

		sb.append("		FROM ATENDIMENTO_INT I, ");
		sb.append(
				"		     ATENDIMENTO A LEFT OUTER JOIN suspeita_covid19 sc ON a.`CD_ATENDIMENTO` = sc.`CD_ATENDIMENTO` AND sc.SN_ATIVO = 'S', ");
		sb.append("		     PACIENTE PAC, ");
		sb.append("		     LEITO L, ");
		sb.append("		     PESSOA P, ");
		sb.append("		     pessoa_dependente pd, ");
		sb.append("		     pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO, ");
		sb.append("		     UNIDADE_INTERNACAO UI ");
		sb.append("		WHERE ");
		sb.append("		     A.CD_SITUACAO_ATENDIMENTO in (1,2,4) ");
		sb.append("			 AND I.CD_PRESTADOR_ALTA is null ");
		sb.append("			 AND A.DT_FINALIZACAO IS NULL ");
		sb.append("			 AND A.DT_ABERTURA >= '2016-09-01' ");
		sb.append("			 AND A.CD_ORIGEM_ATEND = '2' ");
		sb.append("		     AND I.CD_ATENDIMENTO = A.CD_ATENDIMENTO ");
		sb.append("		     AND A.CD_PACIENTE = PAC.CD_PACIENTE ");
		sb.append("		     AND I.CD_LEITO = L.CD_LEITO ");
		sb.append("		     AND P.CD_PESSOA = PAC.CD_PESSOA ");
		sb.append("		     and p.cd_pessoa = pd.CD_PESSOA ");
		sb.append("		     and pd.NR_MATRICULA = pt.NR_MATRICULA ");
		sb.append("		     and pd.CD_CORPORACAO = pt.CD_CORPORACAO ");
		sb.append("		     AND UI.CD_UNIDADE_INTERNACAO = L.CD_UNIDADE_INTERNACAO ");
		sb.append("		 	 AND UI.SN_COVID = 'S' ");
		sb.append("		) R ");

		sb.append(" WHERE 1=1 ");

		if (nome != null && !nome.isEmpty()) {
			sb.append("AND NM_PACIENTE LIKE :nome ");
		}

		if (sexo != null && !sexo.isEmpty()) {
			sb.append("AND TP_SEXO = :sexo ");
		}

		if (unidade != null && !unidade.isEmpty()) {
			sb.append("AND CD_UNIDADE_INTERNACAO = :unidade ");
		}

		if (tipo != null && !tipo.isEmpty()) {
			sb.append("AND TIPO = :tipo ");
		}

		if (categoria != null && !categoria.isEmpty()) {
			sb.append("AND CATEGORIA = :categoria ");
		}

		if (corporacao != null && !corporacao.isEmpty()) {
			sb.append("AND CORPORACAO = :corporacao ");
		}

		if (situacao != null && !situacao.isEmpty()) {
			sb.append("AND SITUACAO = :situacao ");
		}

		if (status != null && !status.isEmpty()) {
			sb.append("AND COVID = :status ");
		}

		sb.append("ORDER BY DS_UNIDADE_INTERNACAO, NM_PACIENTE ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		if (nome != null && !nome.isEmpty()) {
			query.setParameter("nome", "%" + nome + "%");
		}

		if (sexo != null && !sexo.isEmpty()) {
			query.setParameter("sexo", sexo);
		}

		if (unidade != null && !unidade.isEmpty()) {
			query.setParameter("unidade", unidade);
		}

		if (tipo != null && !tipo.isEmpty()) {
			query.setParameter("tipo", tipo);
		}

		if (categoria != null && !categoria.isEmpty()) {
			query.setParameter("categoria", categoria);
		}

		if (corporacao != null && !corporacao.isEmpty()) {
			query.setParameter("corporacao", corporacao);
		}

		if (situacao != null && !situacao.isEmpty()) {
			query.setParameter("situacao", situacao);
		}

		if (status != null && !status.isEmpty()) {
			query.setParameter("status", status);
		}

		final List<Object[]> list = query.getResultList();

		List<PacienteDTO> retorno = new ArrayList<PacienteDTO>();

		for (final Object[] obj : list) {
			int i = 0;
			PacienteDTO dto = new PacienteDTO();
			dto.setMatricula((String) obj[i++]);
			dto.setNome((String) obj[i++]);
			dto.setSexo((String) obj[i++]);
			dto.setIdade(((BigInteger) obj[i++]).intValue());
			i++;// Codigo Unidade Internacao
			dto.setUnidadeInternacao((String) obj[i++]);
			dto.setTipo((String) obj[i++]);
			dto.setCategoria((String) obj[i++]);
			dto.setCorporacao((String) obj[i++]);
			dto.setSituacao((String) obj[i++]);
			dto.setSuspeitaCovid((String) obj[i++]);
			retorno.add(dto);
		}
		return retorno;
	}

	public List<EstatisticaUnidadeInternacaoDTO> consultarEstatisticaUnidadesIternacaoCovid() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DS_UNIDADE_INTERNACAO, NR_COR_HEX,  SUM(TOT_ATIVO) TOT_ATIVO, ");
		sb.append("SUM(TOT_OCUPADO) TOT_OCUPADO, ");
		sb.append("SUM(TOT_VAGO) TOT_VAGO, ");
		sb.append("SUM(TOT_MANUTENCAO) TOT_MANUTENCAO, ");
		sb.append("IF(SUM(TOT_ATIVO) > 0,((SUM(TOT_OCUPADO) * 100)/SUM(TOT_ATIVO)),0) TAXA_OCUPACAO, ");
		sb.append("(SUM(TOT_ATIVO) + SUM(TOT_MANUTENCAO)) TOT_GERAL ");
		sb.append("FROM ( ");

		sb.append(
				"SELECT UI.DS_UNIDADE_INTERNACAO, UI.NR_COR_HEX, COUNT(*) TOT_ATIVO, 0 TOT_OCUPADO, 0 TOT_VAGO, 0 TOT_MANUTENCAO ");
		sb.append("    FROM LEITO L, ");
		sb.append("    UNIDADE_INTERNACAO UI ");
		sb.append("    WHERE TP_OCUPACAO IN ('O','V') ");
		sb.append("    AND UI.SN_COVID = 'S' ");
		sb.append("    AND UI.CD_UNIDADE_INTERNACAO = L.CD_UNIDADE_INTERNACAO ");
		sb.append("    AND L.SN_ATIVO = 'S' ");
		sb.append("    GROUP BY UI.DS_UNIDADE_INTERNACAO ");

		sb.append("UNION ");

		sb.append(
				"SELECT UI.DS_UNIDADE_INTERNACAO, UI.NR_COR_HEX, 0 TOT_ATIVO, COUNT(*) TOT_OCUPADO, 0 TOT_VAGO, 0 TOT_MANUTENCAO ");
		sb.append("    FROM LEITO L, ");
		sb.append("    UNIDADE_INTERNACAO UI ");
		sb.append("    WHERE L.TP_OCUPACAO = 'O' ");
		sb.append("    AND UI.SN_COVID = 'S' ");
		sb.append("    AND UI.CD_UNIDADE_INTERNACAO = L.CD_UNIDADE_INTERNACAO ");
		sb.append("    AND L.SN_ATIVO = 'S' ");
		sb.append("    GROUP BY UI.DS_UNIDADE_INTERNACAO ");

		sb.append("UNION ");

		sb.append(
				"SELECT UI.DS_UNIDADE_INTERNACAO, UI.NR_COR_HEX, 0 TOT_ATIVO, 0 TOT_OCUPADO, COUNT(*) TOT_VAGO, 0 TOT_MANUTENCAO ");
		sb.append("    FROM LEITO L, ");
		sb.append("    UNIDADE_INTERNACAO UI ");
		sb.append("    WHERE TP_OCUPACAO = 'V' ");
		sb.append("    AND UI.SN_COVID = 'S' ");
		sb.append("    AND UI.CD_UNIDADE_INTERNACAO = L.CD_UNIDADE_INTERNACAO ");
		sb.append("    AND L.SN_ATIVO = 'S' ");
		sb.append("    GROUP BY UI.DS_UNIDADE_INTERNACAO ");

		sb.append("UNION ");

		sb.append(
				"SELECT UI.DS_UNIDADE_INTERNACAO, UI.NR_COR_HEX, 0 TOT_ATIVO, 0 TOT_OCUPADO, 0 TOT_VAGO, COUNT(*) TOT_MANUTENCAO ");
		sb.append("    FROM LEITO L, ");
		sb.append("    UNIDADE_INTERNACAO UI ");
		sb.append("    WHERE TP_OCUPACAO = 'M' ");
		sb.append("    AND UI.SN_COVID = 'S' ");
		sb.append("    AND UI.CD_UNIDADE_INTERNACAO = L.CD_UNIDADE_INTERNACAO ");
		sb.append("    AND L.SN_ATIVO = 'S' ");
		sb.append("    GROUP BY UI.DS_UNIDADE_INTERNACAO ");
		sb.append(") R ");

		sb.append("GROUP BY DS_UNIDADE_INTERNACAO ");
		sb.append("ORDER BY DS_UNIDADE_INTERNACAO ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		final List<Object[]> list = query.getResultList();

		List<EstatisticaUnidadeInternacaoDTO> retorno = new ArrayList<EstatisticaUnidadeInternacaoDTO>();

		for (final Object[] obj : list) {
			EstatisticaUnidadeInternacaoDTO dto = new EstatisticaUnidadeInternacaoDTO();
			dto.setNome((String) obj[0]);
			dto.setCor((String) obj[1]);
			dto.setAtivos(((BigDecimal) obj[2]).intValue());
			dto.setOcupados(((BigDecimal) obj[3]).intValue());
			dto.setVagos(((BigDecimal) obj[4]).intValue());
			dto.setManutencao(((BigDecimal) obj[5]).intValue());
			dto.setTaxaOcupacao(((BigDecimal) obj[6]).doubleValue());
			dto.setTotal(((BigDecimal) obj[7]).intValue());
			retorno.add(dto);
		}
		return retorno;
	}

	public EstatisticaCasosDTO consultarEstatisticaCasos() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");

		sb.append("SUM(1) as SUSPEITOS, ");

		sb.append("SUM((CASE  ");
		sb.append("	WHEN sc.SN_EXAME_POSITIVO = 'S' THEN 1 ");
		sb.append("    ELSE 0 ");
		sb.append("END)) as POSITIVOS, ");

		sb.append("SUM((CASE ");
		sb.append("	WHEN sc.SN_EXAME_POSITIVO = 'N' THEN 1 ");
		sb.append("    ELSE 0 ");
		sb.append("END)) as NEGATIVOS, ");

		sb.append("SUM((CASE ");
		sb.append("	WHEN sc.DT_COLETA_EXAME IS NOT NULL AND sc.SN_EXAME_POSITIVO IS NULL THEN 1 ");
		sb.append("    ELSE 0 ");
		sb.append("END)) as AGUARDANDO, ");

		sb.append("( ");
		sb.append("		select (r.qtd_int + r.qtd_urg) as total ");
		sb.append("		from ( ");
		sb.append("		select (select count(*) as qtd_int ");
		sb.append("		from suspeita_covid19 sc, ");
		sb.append("		`atendimento` a, ");
		sb.append("		`atendimento_int` ai, ");
		sb.append("		`motivo_alta` ma ");
		sb.append("		WHERE SC.SN_ATIVO = 'S'  ");
		sb.append("		and sc.`CD_ATENDIMENTO` = a.`CD_ATENDIMENTO` ");
		sb.append("		and a.`CD_ATENDIMENTO` = ai.`CD_ATENDIMENTO` ");
		sb.append("		and ai.`CD_MOTIVO_ALTA` = ma.`CD_MOTIVO_ALTA` ");
		sb.append("		and ma.`TP_MOTIVO_ALTA` = 'O' ");
		sb.append("		and sc.`SN_EXAME_POSITIVO` = 'S' ");
		sb.append("		and a.`DT_CANCELAMENTO` is null ");
		sb.append("		) as qtd_int, ");

		sb.append("		(select count(*) as qtd_urg ");
		sb.append("		from suspeita_covid19 sc, ");
		sb.append("		`atendimento` a, ");
		sb.append("		`atendimento_urg` au, ");
		sb.append("		`motivo_alta` ma ");
		sb.append("		WHERE SC.SN_ATIVO = 'S'  ");
		sb.append("		and sc.`CD_ATENDIMENTO` = a.`CD_ATENDIMENTO` ");
		sb.append("		and a.`CD_ATENDIMENTO` = au.`CD_ATENDIMENTO` ");
		sb.append("		and au.`CD_MOTIVO_ALTA` = ma.`CD_MOTIVO_ALTA` ");
		sb.append("		and ma.`TP_MOTIVO_ALTA` = 'O' ");
		sb.append("		and sc.`SN_EXAME_POSITIVO` = 'S' ");
		sb.append("		and a.`DT_CANCELAMENTO` is null ");
		sb.append("		) as qtd_urg ");
		sb.append("		) r ");
		sb.append("		) as OBITOS, ");

		sb.append("SUM((CASE ");
		sb.append("	WHEN sc.DT_COLETA_EXAME IS NULL AND sc.SN_EXAME_POSITIVO IS NULL THEN 1 ");
		sb.append("    ELSE 0 ");
		sb.append("END)) as NAO_TESTADOS, ");

		sb.append("( ");
		sb.append("		select count(*) ");
		sb.append("		FROM ATENDIMENTO_INT I, ");
		sb.append(
				"				     ATENDIMENTO A LEFT OUTER JOIN suspeita_covid19 sc ON a.`CD_ATENDIMENTO` = sc.`CD_ATENDIMENTO` AND sc.SN_ATIVO = 'S',  ");
		sb.append("				     PACIENTE PAC, ");
		sb.append("				     LEITO L, ");
		sb.append("				     UNIDADE_INTERNACAO UI ");
		sb.append("				WHERE ");
		sb.append("				     A.CD_SITUACAO_ATENDIMENTO in (1,2,4) ");
		sb.append("					 AND I.CD_PRESTADOR_ALTA is null ");
		sb.append("					 AND A.DT_FINALIZACAO IS NULL ");
		sb.append("					 AND A.DT_ABERTURA >= '2016-09-01' ");
		sb.append("					 AND A.CD_ORIGEM_ATEND = '2' ");
		sb.append("				     AND I.CD_ATENDIMENTO = A.CD_ATENDIMENTO ");
		sb.append("				     AND A.CD_PACIENTE = PAC.CD_PACIENTE ");
		sb.append("				     AND I.CD_LEITO = L.CD_LEITO ");
		sb.append("				     AND UI.CD_UNIDADE_INTERNACAO = L.CD_UNIDADE_INTERNACAO ");
		sb.append("				 	 AND UI.SN_COVID = 'S' ");
		sb.append(") as INTERNADOS ");

		sb.append("from suspeita_covid19 sc, ");
		sb.append("atendimento a,  ");
		sb.append("atendimento_int ai   ");
		sb.append("WHERE SC.SN_ATIVO = 'S' ");
		sb.append("and sc.CD_ATENDIMENTO = a.CD_ATENDIMENTO ");
		sb.append("and a.CD_ATENDIMENTO = ai.CD_ATENDIMENTO ");
		sb.append("and a.DT_CANCELAMENTO is null ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		final List<Object[]> list = query.getResultList();

		EstatisticaCasosDTO dto = new EstatisticaCasosDTO();

		for (final Object[] obj : list) {
			int i = 0;
			dto.setSuspeitos(((BigDecimal) obj[i++]).intValue());
			dto.setPositivos(((BigDecimal) obj[i++]).intValue());
			dto.setNegativos(((BigDecimal) obj[i++]).intValue());
			dto.setAguardando(((BigDecimal) obj[i++]).intValue());
			dto.setObitos(((BigInteger) obj[i++]).intValue());
			dto.setNaoTestados(((BigDecimal) obj[i++]).intValue());
			dto.setInternados(((BigInteger) obj[i++]).intValue());
		}
		return dto;
	}
	
	public EstatisticaTestesDTO consultarEstatisticaTestePCR() {
		StringBuilder sb = new StringBuilder();
		sb.append("select "); 
		sb.append("(select count(*) as testes ");
		sb.append("from teste_pcr_covid t ");
		sb.append("where t.SN_ATIVO = 'S') as total, ");

		sb.append("(select count(*) as positivos ");
		sb.append("from teste_pcr_covid t ");
		sb.append("where t.SN_ATIVO = 'S' ");
		sb.append("and t.SN_EXAME_POSITIVO = 'S') as positivos, ");

		sb.append("(select count(*) as positivos ");
		sb.append("from teste_pcr_covid t ");
		sb.append("where t.SN_ATIVO = 'S' ");
		sb.append("and t.SN_EXAME_POSITIVO = 'N') as negativos, ");

		sb.append("(select count(*) as aguardando ");
		sb.append("from teste_pcr_covid t ");
		sb.append("where t.SN_ATIVO = 'S' ");
		sb.append("and t.SN_EXAME_POSITIVO is null) as aguardando ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		final List<Object[]> list = query.getResultList();

		EstatisticaTestesDTO dto = new EstatisticaTestesDTO();

		for (final Object[] obj : list) {
			int i = 0;
			dto.setTestes(((BigInteger) obj[i++]).intValue());
			dto.setPositivos(((BigInteger) obj[i++]).intValue());
			dto.setNegativos(((BigInteger) obj[i++]).intValue());
			dto.setAguardando(((BigInteger) obj[i++]).intValue());
		}
		return dto;
	}
	
	public EstatisticaTestesDTO consultarEstatisticaTesteRapido() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("(select count(*) as testes ");
		sb.append("from teste_rapido_covid t ");
		sb.append("where t.SN_ATIVO = 'S') as total, ");

		sb.append("(select count(*) as positivos ");
		sb.append("from teste_rapido_covid t ");
		sb.append("where t.SN_ATIVO = 'S' ");
		sb.append("and t.SN_IGG_POSITIVO = 'S') as positivos, ");

		sb.append("(select count(*) as positivos ");
		sb.append("from teste_rapido_covid t ");
		sb.append("where t.SN_ATIVO = 'S' ");
		sb.append("and t.SN_IGG_POSITIVO = 'N') as negativos, ");

		sb.append("(select count(*) as aguardando ");
		sb.append("from teste_rapido_covid t ");
		sb.append("where t.SN_ATIVO = 'S' ");
		sb.append("and t.SN_IGG_POSITIVO is null) as aguardando ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		final List<Object[]> list = query.getResultList();

		EstatisticaTestesDTO dto = new EstatisticaTestesDTO();

		for (final Object[] obj : list) {
			int i = 0;
			dto.setTestes(((BigInteger) obj[i++]).intValue());
			dto.setPositivos(((BigInteger) obj[i++]).intValue());
			dto.setNegativos(((BigInteger) obj[i++]).intValue());
			dto.setAguardando(((BigInteger) obj[i++]).intValue());
		}
		return dto;
	}

	public ObitosCovidDTO consultarObitosAcumuladosCovid() {
		StringBuilder sb = new StringBuilder();

		sb.append("select date_format(r.dia, '%d/%m/%y') as dia, r.qtd as obitos, sum(r2.qtd) as acumulado ");

		sb.append("from ( ");
		sb.append("SELECT DATE(sc.DT_OBITO) as dia, count(*) as qtd ");
		sb.append("FROM suspeita_covid19 sc ");
		sb.append("WHERE sc.DT_OBITO is not null ");
		sb.append("AND sc.SN_EXAME_POSITIVO = 'S' ");
		sb.append("GROUP by DATE(sc.DT_OBITO) ");
		sb.append("ORDER by DATE(sc.DT_OBITO) ");
		sb.append(") r, ");

		sb.append("( ");
		sb.append("SELECT DATE(sc.DT_OBITO) as dia, count(*) as qtd ");
		sb.append("FROM suspeita_covid19 sc ");
		sb.append("WHERE sc.DT_OBITO is not null ");
		sb.append("AND sc.SN_EXAME_POSITIVO = 'S' ");
		sb.append("GROUP by DATE(sc.DT_OBITO) ");
		sb.append("ORDER by DATE(sc.DT_OBITO) ");
		sb.append(") r2 ");

		sb.append("where r.dia >= r2.dia ");
		sb.append("group by r.dia ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		final List<Object[]> list = query.getResultList();

		ObitosCovidDTO retorno = new ObitosCovidDTO();

		for (final Object[] obj : list) {
			retorno.getLabels().add((String) obj[0]);
			retorno.getQuantitativos().add(((BigInteger) obj[1]).intValue());
			retorno.getAcumulados().add(((BigDecimal) obj[2]).intValue());
		}
		return retorno;
	}

	public CasosCovidDTO consultarCasosAcumuladosCovid() {
		StringBuilder sb = new StringBuilder();
		sb.append("select date_format(r.dia, '%d/%m/%y') as dia, r.qtd as casos, sum(r2.qtd) as acumulado ");
		sb.append("from ( ");
		sb.append("SELECT DATE(sc.DT_COLETA_EXAME) as dia, count(*) as qtd ");
		sb.append("FROM suspeita_covid19 sc  ");
		sb.append("WHERE sc.DT_COLETA_EXAME is not null ");
		sb.append("AND sc.SN_EXAME_POSITIVO = 'S' ");
		sb.append("GROUP by DATE(sc.DT_COLETA_EXAME) ");
		sb.append("ORDER by DATE(sc.DT_COLETA_EXAME) ");
		sb.append(") r, ");

		sb.append("( ");
		sb.append("SELECT DATE(sc.DT_COLETA_EXAME) as dia, count(*) as qtd ");
		sb.append("FROM suspeita_covid19 sc ");
		sb.append("WHERE sc.DT_COLETA_EXAME is not null ");
		sb.append("AND sc.SN_EXAME_POSITIVO = 'S' ");
		sb.append("GROUP by DATE(sc.DT_COLETA_EXAME) ");
		sb.append("ORDER by DATE(sc.DT_COLETA_EXAME) ");
		sb.append(") r2 ");
		sb.append("where r.dia >= r2.dia ");
		sb.append("group by r.dia ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		final List<Object[]> list = query.getResultList();

		CasosCovidDTO retorno = new CasosCovidDTO();

		for (final Object[] obj : list) {
			retorno.getLabels().add((String) obj[0]);
			retorno.getQuantitativos().add(((BigInteger) obj[1]).intValue());
			retorno.getAcumulados().add(((BigDecimal) obj[2]).intValue());
		}
		return retorno;
	}

	public CasosCovidDTO consultarCasosPorSemana() {
		StringBuilder sb = new StringBuilder();
		sb.append("select (r.semana + 1) as semana, r.qtd as casos, sum(r2.qtd) as acumulado ");

		sb.append("from ( ");
		sb.append("SELECT WEEK(sc.DT_COLETA_EXAME) as semana, count(*) as qtd ");
		sb.append("FROM suspeita_covid19 sc ");
		sb.append("WHERE sc.SN_EXAME_POSITIVO = 'S' ");
		sb.append("GROUP by WEEK(sc.DT_COLETA_EXAME) ");

		sb.append(") r, ");

		sb.append("( ");
		sb.append("SELECT WEEK(sc.DT_COLETA_EXAME) as semana, count(*) as qtd ");
		sb.append("FROM suspeita_covid19 sc ");
		sb.append("WHERE sc.SN_EXAME_POSITIVO = 'S' ");
		sb.append("GROUP by WEEK(sc.DT_COLETA_EXAME) ");

		sb.append(") r2 ");

		sb.append("where r.semana >= r2.semana ");
		sb.append("group by r.semana; ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		final List<Object[]> list = query.getResultList();

		CasosCovidDTO retorno = new CasosCovidDTO();

		for (final Object[] obj : list) {
			retorno.getLabels().add(((BigInteger) obj[0]).toString());
			retorno.getQuantitativos().add(((BigInteger) obj[1]).intValue());
			retorno.getAcumulados().add(((BigDecimal) obj[2]).intValue());
		}
		return retorno;
	}

	public ObitosCovidDTO consultarObitosPorSemana() {
		StringBuilder sb = new StringBuilder();
		sb.append("select (r.semana + 1) as semana, r.qtd as obitos, sum(r2.qtd) as acumulado ");
		sb.append("from ( ");
		sb.append("SELECT WEEK(sc.DT_OBITO) as semana, count(*) as qtd ");
		sb.append("FROM suspeita_covid19 sc ");
		sb.append("WHERE sc.DT_OBITO is not null ");
		sb.append("AND sc.SN_EXAME_POSITIVO = 'S' ");
		sb.append("GROUP by WEEK(sc.DT_OBITO) ");
		sb.append("ORDER by WEEK(sc.DT_OBITO) ");
		sb.append(") r, ");

		sb.append("(  ");
		sb.append("SELECT WEEK(sc.DT_OBITO) as semana, count(*) as qtd ");
		sb.append("FROM suspeita_covid19 sc ");
		sb.append("WHERE sc.DT_OBITO is not null ");
		sb.append("AND sc.SN_EXAME_POSITIVO = 'S' ");
		sb.append("GROUP by WEEK(sc.DT_OBITO) ");
		sb.append("ORDER by WEEK(sc.DT_OBITO) ");
		sb.append(") r2 ");

		sb.append("where r.semana >= r2.semana ");
		sb.append("group by r.semana ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		final List<Object[]> list = query.getResultList();

		ObitosCovidDTO retorno = new ObitosCovidDTO();

		for (final Object[] obj : list) {
			retorno.getLabels().add(((BigInteger) obj[0]).toString());
			retorno.getQuantitativos().add(((BigInteger) obj[1]).intValue());
			retorno.getAcumulados().add(((BigDecimal) obj[2]).intValue());
		}
		return retorno;
	}

	public GraficoDTO consultarCasosEObitosPorSemana() {
		StringBuilder sb = new StringBuilder();
		sb.append("select r.semana as semana, sum(r.casos) as casos, sum(r.obitos) as obitos ");
		sb.append("from ( ");

		sb.append("SELECT "); 
		sb.append("(WEEK(a.`DT_FINALIZACAO`) + 1) as semana, ");
		sb.append("0 as casos, "); 
		sb.append("COUNT(WEEK(a.`DT_FINALIZACAO`)) as obitos "); 
		sb.append("FROM suspeita_covid19 sc, ");
		sb.append("`atendimento` a, ");
		sb.append("`atendimento_int` ai, ");
		sb.append("`motivo_alta` ma ");
		sb.append("WHERE sc.SN_EXAME_POSITIVO = 'S' "); 
		sb.append("and sc.`CD_ATENDIMENTO` = a.`CD_ATENDIMENTO` ");
		sb.append("and a.`CD_ATENDIMENTO` = ai.`CD_ATENDIMENTO` ");
		sb.append("and ai.`CD_MOTIVO_ALTA` = ma.`CD_MOTIVO_ALTA` ");
		sb.append("and ma.`TP_MOTIVO_ALTA` = 'O' ");
		sb.append("GROUP by WEEK(a.DT_FINALIZACAO) "); 

		sb.append("UNION "); 

		sb.append("SELECT "); 
		sb.append("(WEEK(sc.DT_COLETA_EXAME) + 1) as semana, "); 
		sb.append("COUNT(WEEK(sc.DT_COLETA_EXAME)) as casos, ");
		sb.append("0 as obitos ");
		sb.append("FROM suspeita_covid19 sc, ");
		sb.append("atendimento a ");
		sb.append("WHERE sc.SN_EXAME_POSITIVO = 'S' "); 
		sb.append("and sc.`CD_ATENDIMENTO` = a.`CD_ATENDIMENTO` ");
		sb.append("and a.`TP_ATENDIMENTO` = 'I' ");
		sb.append("GROUP by WEEK(sc.DT_COLETA_EXAME) "); 
		sb.append(") r ");
		
		sb.append("where r.semana is not null ");

		sb.append("group by r.semana ");
		sb.append("order by r.semana ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		final List<Object[]> list = query.getResultList();

		List<String> labels = new ArrayList<String>();

		GraficoDTO grafico = new GraficoDTO();
		grafico.setDescricao("Casos x Óbitos Por Semana Epidemiológica");

		DadoGraficoDTO casos = new DadoGraficoDTO();
		casos.setCor("FFA500");
		casos.setDescricao("Casos");
		List<Integer> quantitativoCasos = new ArrayList<Integer>();

		DadoGraficoDTO obitos = new DadoGraficoDTO();
		obitos.setCor("CC0000");
		obitos.setDescricao("Óbitos");
		List<Integer> quantitativoObitos = new ArrayList<Integer>();

		for (final Object[] obj : list) {
			labels.add(((BigInteger) obj[0]).toString());
			quantitativoCasos.add(((BigDecimal) obj[1]).intValue());
			quantitativoObitos.add(((BigDecimal) obj[2]).intValue());
		}
		casos.setQuantitativos(quantitativoCasos);
		obitos.setQuantitativos(quantitativoObitos);

		grafico.setLabels(labels);
		grafico.getDataset().add(casos);
		grafico.getDataset().add(obitos);

		return grafico;
	}

	public GraficoDTO consultarClassificacaoRiscoPorDia() {
		StringBuilder sb = new StringBuilder();
		sb.append("select r.dia2, r.emergente, r.urgente, r.pouco_urgente, r.nao_urgente, r.covid ");
		sb.append("from ( ");
		
		sb.append("select DATE(cr.DT_CLASSIFICACAO_RISCO) as dia, ");
		sb.append("date_format(cr.DT_CLASSIFICACAO_RISCO, '%d/%m/%y') as dia2, ");
		
		sb.append("SUM(CASE WHEN cr.CD_TIPO_CLASSIFICACAO_RISCO  =  1 THEN 1 ELSE 0 END) as emergente, ");
		sb.append("SUM(CASE WHEN cr.CD_TIPO_CLASSIFICACAO_RISCO  =  2 THEN 1 ELSE 0 END) as urgente, ");
		sb.append("SUM(CASE WHEN cr.CD_TIPO_CLASSIFICACAO_RISCO  =  3 THEN 1 ELSE 0 END) as pouco_urgente, ");
		sb.append("SUM(CASE WHEN cr.CD_TIPO_CLASSIFICACAO_RISCO  =  4 THEN 1 ELSE 0 END) as nao_urgente, ");
		sb.append("SUM(CASE WHEN cr.CD_TIPO_CLASSIFICACAO_RISCO  =  5 THEN 1 ELSE 0 END) as covid ");
		sb.append("FROM classificacao_risco cr ");
		sb.append("WHERE cr.DT_CLASSIFICACAO_RISCO >= '2020-03-01' ");
		sb.append("GROUP BY DATE(cr.DT_CLASSIFICACAO_RISCO) ");
		sb.append("ORDER BY dia ");
		sb.append(") r ");

		Query query = this.entityManager.createNativeQuery(sb.toString());

		final List<Object[]> list = query.getResultList();

		List<String> labels = new ArrayList<String>();

		GraficoDTO grafico = new GraficoDTO();
		grafico.setDescricao("Classificação de Risco por Dia");

		DadoGraficoDTO emergente = new DadoGraficoDTO();
		emergente.setCor("CC0000");
		emergente.setDescricao("Emergente");

		DadoGraficoDTO urgente = new DadoGraficoDTO();
		urgente.setCor("EDEF00");
		urgente.setDescricao("Urgente");

		DadoGraficoDTO poucoUrgente = new DadoGraficoDTO();
		poucoUrgente.setCor("008000");
		poucoUrgente.setDescricao("Pouco urgente");

		DadoGraficoDTO naoUrgente = new DadoGraficoDTO();
		naoUrgente.setCor("0000FF");
		naoUrgente.setDescricao("Não urgente");

		DadoGraficoDTO covid = new DadoGraficoDTO();
		covid.setCor("FFA500");
		covid.setDescricao("COVID-19");

		for (final Object[] obj : list) {
			labels.add(((String) obj[0]).toString());
			emergente.getQuantitativos().add(((BigDecimal) obj[1]).intValue());
			urgente.getQuantitativos().add(((BigDecimal) obj[2]).intValue());
			poucoUrgente.getQuantitativos().add(((BigDecimal) obj[3]).intValue());
			naoUrgente.getQuantitativos().add(((BigDecimal) obj[4]).intValue());
			covid.getQuantitativos().add(((BigDecimal) obj[5]).intValue());
		}

		grafico.setLabels(labels);
		grafico.getDataset().add(emergente);
		grafico.getDataset().add(urgente);
		grafico.getDataset().add(poucoUrgente);
		grafico.getDataset().add(naoUrgente);
		grafico.getDataset().add(covid);

		return grafico;
	}
	
	public CasosCovidDTO consultarCasosPCRPorSemana() {
		StringBuilder sb = new StringBuilder();
		sb.append("select (r.semana + 1) as semana, r.qtd as casos, sum(r2.qtd) as acumulado "); 

		sb.append("from ( "); 
		sb.append("SELECT WEEK(t.DT_COLETA_EXAME) as semana, count(*) as qtd "); 
		sb.append("FROM `teste_pcr_covid` t ");
		sb.append("WHERE t.SN_EXAME_POSITIVO = 'S' "); 
		sb.append("AND t.SN_ATIVO = 'S' ");
		sb.append("GROUP by WEEK(t.DT_COLETA_EXAME) "); 

		sb.append(") r, "); 

		sb.append("( "); 
		sb.append("SELECT WEEK(t.DT_COLETA_EXAME) as semana, count(*) as qtd "); 
		sb.append("FROM `teste_pcr_covid` t "); 
		sb.append("WHERE t.SN_EXAME_POSITIVO = 'S' ");
		sb.append("AND t.SN_ATIVO = 'S' ");
		sb.append("GROUP by WEEK(t.DT_COLETA_EXAME) "); 

		sb.append(") r2 "); 

		sb.append("where r.semana >= r2.semana "); 
		sb.append("group by r.semana "); 

		Query query = this.entityManager.createNativeQuery(sb.toString());

		final List<Object[]> list = query.getResultList();

		CasosCovidDTO retorno = new CasosCovidDTO();

		for (final Object[] obj : list) {
			retorno.getLabels().add(((BigInteger) obj[0]).toString());
			retorno.getQuantitativos().add(((BigInteger) obj[1]).intValue());
			retorno.getAcumulados().add(((BigDecimal) obj[2]).intValue());
		}
		return retorno;
	}
}