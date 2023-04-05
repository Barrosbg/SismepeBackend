package br.gov.pe.sismepe.repositories;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.Atendimento;
import br.gov.pe.sismepe.domain.SuspeitaCovid;
import br.gov.pe.sismepe.dto.covid.SuspeitaCovidDTO;
import br.gov.pe.sismepe.filtro.FiltroSuspeitaCovid;
import br.gov.pe.sismepe.util.Utils;

@Repository
public class SuspeitaCovidRepositoryImpl extends AbstractDAO<SuspeitaCovid, Integer, FiltroSuspeitaCovid> implements SuspeitaCovidRepository {

	@Override
	public SuspeitaCovid buscarPorCodigoPessoa(Integer codigoPessoa) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT s FROM SuspeitaCovid s JOIN FETCH s.pessoa WHERE s.ativo = 'S' AND s.pessoa.id = :codigoPessoa");
		Query query = getEntityManager().createQuery(sb.toString());
		query.setParameter("codigoPessoa", codigoPessoa);
		
		SuspeitaCovid retorno = null;
		List<SuspeitaCovid> lista = query.getResultList();
		if(Utils.isListNaoNulaNaoVazia(lista)) {
			retorno = lista.get(0);
		}
		
		return retorno;
	}
	
	public SuspeitaCovid buscarPorAtendimento(Atendimento atendimento) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT s FROM SuspeitaCovid s JOIN FETCH s.pessoa WHERE s.ativo = 'S' AND s.atendimento.id = :codigoAtendimento");
		Query query = getEntityManager().createQuery(sb.toString());
		query.setParameter("codigoAtendimento", atendimento.getId());
		
		SuspeitaCovid retorno = null;
		List<SuspeitaCovid> lista = query.getResultList();
		if(Utils.isListNaoNulaNaoVazia(lista)) {
			retorno = lista.get(0);
		}
		
		return retorno;
	}
	
	@Override
	public List<SuspeitaCovid> findAll() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT s FROM SuspeitaCovid s JOIN FETCH s.pessoa WHERE s.ativo = 'S' ORDER BY s.pessoa.nome");
		Query query = getEntityManager().createQuery(sb.toString());
		return query.getResultList();
	}
	
	public List<SuspeitaCovid> consultarPorFiltro(FiltroSuspeitaCovid filtro) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT s FROM SuspeitaCovid s JOIN FETCH s.pessoa WHERE s.ativo = 'S' ");
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			sb.append(" AND s.pessoa.nome LIKE :nome ");
		}
		
		sb.append(" ORDER BY s.pessoa.nome");
		
		
		Query query = getEntityManager().createQuery(sb.toString());
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			query.setParameter("nome", "%" + filtro.getNome() + "%");
		}
		
		return query.getResultList();
	}
	
	public List<SuspeitaCovidDTO> consultarDTOPorFiltro(FiltroSuspeitaCovid filtro) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * ");
		sb.append("FROM ( ");
		sb.append(" ");
		sb.append("/* ");
		sb.append("Titulares Internação ");
		sb.append("*/ ");
		sb.append("SELECT sc.CD_SUSPEITA_COVID19, ");
		sb.append("CAST(CONCAT(pt.NR_MATRICULA, '-', pt.NR_DIGITO) as char) as MATRICULA, ");
		sb.append("p.cd_pessoa, ");
		sb.append("P.NM_PESSOA AS NM_PACIENTE, ");
		sb.append("P.TP_SEXO, ");
		sb.append("FLOOR((DATEDIFF(a.DT_ABERTURA,p.dt_nascimento) / 365)) AS DS_IDADE, ");
		sb.append("'TITULAR' as tipo, ");
		sb.append(" ");
		sb.append("(CASE   ");
		sb.append("	WHEN po.TP_CATEGORIA = 'O' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'P' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 THEN 'PENS' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 THEN 'FC' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as categoria, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("	WHEN pt.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("	WHEN pt.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as corporacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN pt.TP_TABELA = 1 then 'ATIVO'   ");
		sb.append("WHEN pt.TP_TABELA = 2 then 'INATIVO'   ");
		sb.append("ELSE 'NÃO INFORMADO'   ");
		sb.append("END   ");
		sb.append(") as situacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO'   ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO'   ");
		sb.append("WHEN sc.DT_COLETA_EXAME IS NOT NULL THEN 'AGUARDANDO'   ");
		sb.append("WHEN sc.CD_SUSPEITA_COVID19 is NOT NULL THEN 'SUSPEITO'   ");
		sb.append("END) as STATUS_COVID, ");
		sb.append(" ");
		sb.append("sc.DT_COLETA_EXAME, ");
		sb.append("sc.DT_RESULTADO_EXAME, ");
		sb.append(" ");
		sb.append("a.DT_FINALIZACAO as DT_ALTA, ");
		sb.append(" ");
		sb.append("ma.CD_MOTIVO_ALTA, ");
		sb.append("ma.DS_MOTIVO_ALTA, ");
		sb.append("ma.TP_MOTIVO_ALTA ");
		sb.append(" ");
		sb.append("FROM suspeita_covid19 sc, ");
		sb.append("atendimento a, ");
		sb.append("atendimento_int ai left outer join motivo_alta ma on ai.CD_MOTIVO_ALTA = ma.CD_MOTIVO_ALTA, ");
		sb.append("paciente pac, ");
		sb.append("pessoa p, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO ");
		sb.append("where sc.CD_ATENDIMENTO = a.CD_ATENDIMENTO ");
		sb.append("and a.CD_ATENDIMENTO = ai.CD_ATENDIMENTO ");
		sb.append("and a.DT_CANCELAMENTO is null ");
		sb.append("and sc.SN_ATIVO = 'S' ");
		sb.append("and a.CD_PACIENTE = pac.CD_PACIENTE ");
		sb.append("and pac.CD_PESSOA = p.CD_PESSOA ");
		sb.append("and p.CD_PESSOA = pt.CD_PESSOA ");
		sb.append(" ");
		sb.append("UNION ");
		sb.append(" ");
		sb.append("/* ");
		sb.append("Dependentes Internação ");
		sb.append("*/ ");
		sb.append("SELECT sc.CD_SUSPEITA_COVID19, ");
		sb.append("CAST(CONCAT(pt.NR_MATRICULA, '-', pt.NR_DIGITO, '/', pd.NR_SEQUENCIAL) as char) as MATRICULA, ");
		sb.append("p.cd_pessoa, ");
		sb.append("P.NM_PESSOA AS NM_PACIENTE, ");
		sb.append("P.TP_SEXO, ");
		sb.append("FLOOR((DATEDIFF(a.DT_ABERTURA,p.dt_nascimento) / 365)) AS DS_IDADE, ");
		sb.append("'DEPENDENTE' as tipo, ");
		sb.append(" ");
		sb.append("(CASE   ");
		sb.append("	WHEN po.TP_CATEGORIA = 'O' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'P' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 THEN 'PENS' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 THEN 'FC' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as categoria, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("	WHEN pt.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("	WHEN pt.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as corporacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN pt.TP_TABELA = 1 then 'ATIVO'   ");
		sb.append("WHEN pt.TP_TABELA = 2 then 'INATIVO'   ");
		sb.append("ELSE 'NÃO INFORMADO'   ");
		sb.append("END   ");
		sb.append(") as situacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO'   ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO'   ");
		sb.append("WHEN sc.DT_COLETA_EXAME IS NOT NULL THEN 'AGUARDANDO'   ");
		sb.append("WHEN sc.CD_SUSPEITA_COVID19 is NOT NULL THEN 'SUSPEITO'   ");
		sb.append("END) as STATUS_COVID, ");
		sb.append(" ");
		sb.append("sc.DT_COLETA_EXAME, ");
		sb.append("sc.DT_RESULTADO_EXAME, ");
		sb.append(" ");
		sb.append("a.DT_FINALIZACAO as DT_ALTA, ");
		sb.append(" ");
		sb.append("ma.CD_MOTIVO_ALTA, ");
		sb.append("ma.DS_MOTIVO_ALTA, ");
		sb.append("ma.TP_MOTIVO_ALTA ");
		sb.append(" ");
		sb.append("FROM suspeita_covid19 sc, ");
		sb.append("atendimento a, ");
		sb.append("atendimento_int ai left outer join motivo_alta ma on ai.CD_MOTIVO_ALTA = ma.CD_MOTIVO_ALTA, ");
		sb.append("paciente pac, ");
		sb.append("pessoa p, ");
		sb.append("pessoa_dependente pd, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO ");
		sb.append("where sc.CD_ATENDIMENTO = a.CD_ATENDIMENTO ");
		sb.append("and a.CD_ATENDIMENTO = ai.CD_ATENDIMENTO ");
		sb.append("and a.DT_CANCELAMENTO is null ");
		sb.append("and sc.SN_ATIVO = 'S' ");
		sb.append("and a.CD_PACIENTE = pac.CD_PACIENTE ");
		sb.append("and pac.CD_PESSOA = p.CD_PESSOA ");
		sb.append("and p.CD_PESSOA = pd.CD_PESSOA ");
		sb.append("and pd.NR_MATRICULA = pt.NR_MATRICULA ");
		sb.append("and pd.CD_CORPORACAO = pt.CD_CORPORACAO ");
		sb.append(" ");
		sb.append("UNION ");
		sb.append(" ");
		sb.append("/* ");
		sb.append("Titulares Urgência ");
		sb.append("*/ ");
		sb.append("SELECT sc.CD_SUSPEITA_COVID19, ");
		sb.append("CAST(CONCAT(pt.NR_MATRICULA, '-', pt.NR_DIGITO) as char) as MATRICULA, ");
		sb.append("p.cd_pessoa, ");
		sb.append("P.NM_PESSOA AS NM_PACIENTE, ");
		sb.append("P.TP_SEXO, ");
		sb.append("FLOOR((DATEDIFF(a.DT_ABERTURA,p.dt_nascimento) / 365)) AS DS_IDADE, ");
		sb.append("'TITULAR' as tipo, ");
		sb.append(" ");
		sb.append("(CASE   ");
		sb.append("	WHEN po.TP_CATEGORIA = 'O' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'P' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 THEN 'PENS' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 THEN 'FC' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as categoria, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("	WHEN pt.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("	WHEN pt.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as corporacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN pt.TP_TABELA = 1 then 'ATIVO'   ");
		sb.append("WHEN pt.TP_TABELA = 2 then 'INATIVO'   ");
		sb.append("ELSE 'NÃO INFORMADO'   ");
		sb.append("END   ");
		sb.append(") as situacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO'   ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO'   ");
		sb.append("WHEN sc.DT_COLETA_EXAME IS NOT NULL THEN 'AGUARDANDO'   ");
		sb.append("WHEN sc.CD_SUSPEITA_COVID19 is NOT NULL THEN 'SUSPEITO'   ");
		sb.append("END) as STATUS_COVID, ");
		sb.append(" ");
		sb.append("sc.DT_COLETA_EXAME, ");
		sb.append("sc.DT_RESULTADO_EXAME, ");
		sb.append(" ");
		sb.append("a.DT_FINALIZACAO as DT_ALTA, ");
		sb.append(" ");
		sb.append("ma.CD_MOTIVO_ALTA, ");
		sb.append("ma.DS_MOTIVO_ALTA, ");
		sb.append("ma.TP_MOTIVO_ALTA ");
		sb.append(" ");
		sb.append("FROM suspeita_covid19 sc, ");
		sb.append("atendimento a, ");
		sb.append("atendimento_urg au left outer join motivo_alta ma on au.CD_MOTIVO_ALTA = ma.CD_MOTIVO_ALTA, ");
		sb.append("paciente pac, ");
		sb.append("pessoa p, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO ");
		sb.append("where sc.CD_ATENDIMENTO = a.CD_ATENDIMENTO ");
		sb.append("and a.CD_ATENDIMENTO = au.CD_ATENDIMENTO ");
		sb.append("and a.DT_CANCELAMENTO is null ");
		sb.append("and sc.SN_ATIVO = 'S' ");
		sb.append("and a.CD_PACIENTE = pac.CD_PACIENTE ");
		sb.append("and pac.CD_PESSOA = p.CD_PESSOA ");
		sb.append("and p.CD_PESSOA = pt.CD_PESSOA ");
		sb.append(" ");
		sb.append("UNION ");
		sb.append(" ");
		sb.append("/* ");
		sb.append("Dependentes Urgência ");
		sb.append("*/ ");
		sb.append("SELECT sc.CD_SUSPEITA_COVID19, ");
		sb.append("CAST(CONCAT(pt.NR_MATRICULA, '-', pt.NR_DIGITO, '/', pd.NR_SEQUENCIAL) as char) as MATRICULA, ");
		sb.append("p.cd_pessoa, ");
		sb.append("P.NM_PESSOA AS NM_PACIENTE, ");
		sb.append("P.TP_SEXO, ");
		sb.append("FLOOR((DATEDIFF(a.DT_ABERTURA,p.dt_nascimento) / 365)) AS DS_IDADE, ");
		sb.append("'DEPENDENTE' as tipo, ");
		sb.append(" ");
		sb.append("(CASE   ");
		sb.append("	WHEN po.TP_CATEGORIA = 'O' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'P' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 THEN 'PENS' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 THEN 'FC' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as categoria, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("	WHEN pt.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("	WHEN pt.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as corporacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN pt.TP_TABELA = 1 then 'ATIVO'   ");
		sb.append("WHEN pt.TP_TABELA = 2 then 'INATIVO'   ");
		sb.append("ELSE 'NÃO INFORMADO'   ");
		sb.append("END   ");
		sb.append(") as situacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO'   ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO'   ");
		sb.append("WHEN sc.DT_COLETA_EXAME IS NOT NULL THEN 'AGUARDANDO'   ");
		sb.append("WHEN sc.CD_SUSPEITA_COVID19 is NOT NULL THEN 'SUSPEITO'   ");
		sb.append("END) as STATUS_COVID, ");
		sb.append(" ");
		sb.append("sc.DT_COLETA_EXAME, ");
		sb.append("sc.DT_RESULTADO_EXAME, ");
		sb.append(" ");
		sb.append("a.DT_FINALIZACAO as DT_ALTA, ");
		sb.append(" ");
		sb.append("ma.CD_MOTIVO_ALTA, ");
		sb.append("ma.DS_MOTIVO_ALTA, ");
		sb.append("ma.TP_MOTIVO_ALTA ");
		sb.append(" ");
		sb.append("FROM suspeita_covid19 sc, ");
		sb.append("atendimento a, ");
		sb.append("atendimento_urg au left outer join motivo_alta ma on au.CD_MOTIVO_ALTA = ma.CD_MOTIVO_ALTA, ");
		sb.append("paciente pac, ");
		sb.append("pessoa p, ");
		sb.append("pessoa_dependente pd, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO ");
		sb.append("where sc.CD_ATENDIMENTO = a.CD_ATENDIMENTO ");
		sb.append("and a.CD_ATENDIMENTO = au.CD_ATENDIMENTO ");
		sb.append("and a.DT_CANCELAMENTO is null ");
		sb.append("and sc.SN_ATIVO = 'S' ");
		sb.append("and a.CD_PACIENTE = pac.CD_PACIENTE ");
		sb.append("and pac.CD_PESSOA = p.CD_PESSOA ");
		sb.append("and p.CD_PESSOA = pd.CD_PESSOA ");
		sb.append("and pd.NR_MATRICULA = pt.NR_MATRICULA ");
		sb.append("and pd.CD_CORPORACAO = pt.CD_CORPORACAO ");
		sb.append(" ");
		sb.append(") r ");

		sb.append("WHERE 1=1 ");
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			sb.append("AND NM_PACIENTE LIKE :nome ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSexo())) {
			sb.append("AND TP_SEXO = :sexo ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getTipo())) {
			sb.append("AND TIPO = :tipo ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCategoria())) {
			sb.append("AND CATEGORIA = :categoria ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCorporacao())) {
			sb.append("AND CORPORACAO = :corporacao ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSituacao())) {
			sb.append("AND SITUACAO = :situacao ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getStatus())) {
			sb.append("AND STATUS_COVID = :status ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getObito())) {
			if(filtro.getObito().equals("S")) {
				sb.append("AND TP_MOTIVO_ALTA = 'O' ");
				
			} else if(filtro.getObito().equals("N")) {
				sb.append("AND TP_MOTIVO_ALTA <> 'O' ");
			}
		}
		
		if(filtro.getMatricula() != null) {
			sb.append("AND MATRICULA LIKE :matricula ");
		}
				
		sb.append("ORDER BY nm_paciente ");
		
		
		Query query = getEntityManager().createNativeQuery(sb.toString());
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			query.setParameter("nome", "%" + filtro.getNome() + "%");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSexo())) {
			query.setParameter("sexo", filtro.getSexo());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getTipo())) {
			query.setParameter("tipo", filtro.getTipo());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCategoria())) {
			query.setParameter("categoria", filtro.getCategoria());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCorporacao())) {
			query.setParameter("corporacao", filtro.getCorporacao());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSituacao())) {
			query.setParameter("situacao", filtro.getSituacao());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getStatus())) {
			query.setParameter("status", filtro.getStatus());
		}
		
		if(filtro.getMatricula() != null) {
			query.setParameter("matricula", "%" + filtro.getMatricula() + "%");
		}
		
		final List<Object[]> list = query.getResultList();
		
		List<SuspeitaCovidDTO> retorno = new ArrayList<SuspeitaCovidDTO>();
		
		for (final Object[] obj : list) {
			int i = 0;
			SuspeitaCovidDTO dto = new SuspeitaCovidDTO();
			dto.setId((Integer) obj[i++]);
			dto.setMatricula((String) obj[i++]);
			dto.setCodigoPessoa((Integer) obj[i++]);
			dto.setNome((String) obj[i++]);
			dto.setSexo((String) obj[i++]);
			dto.setIdade(((BigInteger) obj[i++]).intValue());
			dto.setTipo((String) obj[i++]);
			dto.setCategoria((String) obj[i++]);
			dto.setCorporacao((String) obj[i++]);
			dto.setSituacao((String) obj[i++]);
			dto.setStatus((String) obj[i++]);
			dto.setDataColetaExame((Date) obj[i++]);
			dto.setDataResultadoExame((Date) obj[i++]);
			dto.setDataAlta((Date) obj[i++]);
			dto.setCodigoMotivoAlta((Integer) obj[i++]);
			dto.setDescricaoMotivoAlta((String) obj[i++]);
			dto.setTipoMotivoAlta((String) obj[i++]);
			retorno.add(dto);
		}
		
		return retorno;
	}
	
	public List<SuspeitaCovidDTO> consultarDTOPorFiltroInternacao(FiltroSuspeitaCovid filtro) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * ");
		sb.append("FROM ( ");
		sb.append(" ");
		sb.append("/* ");
		sb.append("Titulares Internação ");
		sb.append("*/ ");
		sb.append("SELECT sc.CD_SUSPEITA_COVID19, ");
		sb.append("CAST(CONCAT(pt.NR_MATRICULA, '-', pt.NR_DIGITO) as char) as MATRICULA, ");
		sb.append("p.cd_pessoa, ");
		sb.append("P.NM_PESSOA AS NM_PACIENTE, ");
		sb.append("P.TP_SEXO, ");
		sb.append("FLOOR((DATEDIFF(a.DT_ABERTURA,p.dt_nascimento) / 365)) AS DS_IDADE, ");
		sb.append("'TITULAR' as tipo, ");
		sb.append(" ");
		sb.append("(CASE   ");
		sb.append("	WHEN po.TP_CATEGORIA = 'O' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'P' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 THEN 'PENS' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 THEN 'FC' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as categoria, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("	WHEN pt.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("	WHEN pt.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as corporacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN pt.TP_TABELA = 1 then 'ATIVO'   ");
		sb.append("WHEN pt.TP_TABELA = 2 then 'INATIVO'   ");
		sb.append("ELSE 'NÃO INFORMADO'   ");
		sb.append("END   ");
		sb.append(") as situacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO'   ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO'   ");
		sb.append("WHEN sc.DT_COLETA_EXAME IS NOT NULL THEN 'AGUARDANDO'   ");
		sb.append("WHEN sc.CD_SUSPEITA_COVID19 is NOT NULL THEN 'SUSPEITO'   ");
		sb.append("END) as STATUS_COVID, ");
		sb.append(" ");
		sb.append("sc.DT_COLETA_EXAME, ");
		sb.append("sc.DT_RESULTADO_EXAME, ");
		sb.append(" ");
		sb.append("a.DT_FINALIZACAO as DT_ALTA, ");
		sb.append(" ");
		sb.append("ma.CD_MOTIVO_ALTA, ");
		sb.append("ma.DS_MOTIVO_ALTA, ");
		sb.append("ma.TP_MOTIVO_ALTA ");
		sb.append(" ");
		sb.append("FROM suspeita_covid19 sc, ");
		sb.append("atendimento a, ");
		sb.append("atendimento_int ai left outer join motivo_alta ma on ai.CD_MOTIVO_ALTA = ma.CD_MOTIVO_ALTA, ");
		sb.append("paciente pac, ");
		sb.append("pessoa p, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO ");
		sb.append("where sc.CD_ATENDIMENTO = a.CD_ATENDIMENTO ");
		sb.append("and a.CD_ATENDIMENTO = ai.CD_ATENDIMENTO ");
		sb.append("and a.DT_CANCELAMENTO is null ");
		sb.append("and sc.SN_ATIVO = 'S' ");
		sb.append("and a.CD_PACIENTE = pac.CD_PACIENTE ");
		sb.append("and pac.CD_PESSOA = p.CD_PESSOA ");
		sb.append("and p.CD_PESSOA = pt.CD_PESSOA ");
		sb.append(" ");
		sb.append("UNION ");
		sb.append(" ");
		sb.append("/* ");
		sb.append("Dependentes Internação ");
		sb.append("*/ ");
		sb.append("SELECT sc.CD_SUSPEITA_COVID19, ");
		sb.append("CAST(CONCAT(pt.NR_MATRICULA, '-', pt.NR_DIGITO, '/', pd.NR_SEQUENCIAL) as char) as MATRICULA, ");
		sb.append("p.cd_pessoa, ");
		sb.append("P.NM_PESSOA AS NM_PACIENTE, ");
		sb.append("P.TP_SEXO, ");
		sb.append("FLOOR((DATEDIFF(a.DT_ABERTURA,p.dt_nascimento) / 365)) AS DS_IDADE, ");
		sb.append("'DEPENDENTE' as tipo, ");
		sb.append(" ");
		sb.append("(CASE   ");
		sb.append("	WHEN po.TP_CATEGORIA = 'O' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'P' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 THEN 'PENS' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 THEN 'FC' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as categoria, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("	WHEN pt.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("	WHEN pt.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as corporacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN pt.TP_TABELA = 1 then 'ATIVO'   ");
		sb.append("WHEN pt.TP_TABELA = 2 then 'INATIVO'   ");
		sb.append("ELSE 'NÃO INFORMADO'   ");
		sb.append("END   ");
		sb.append(") as situacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO'   ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO'   ");
		sb.append("WHEN sc.DT_COLETA_EXAME IS NOT NULL THEN 'AGUARDANDO'   ");
		sb.append("WHEN sc.CD_SUSPEITA_COVID19 is NOT NULL THEN 'SUSPEITO'   ");
		sb.append("END) as STATUS_COVID, ");
		sb.append(" ");
		sb.append("sc.DT_COLETA_EXAME, ");
		sb.append("sc.DT_RESULTADO_EXAME, ");
		sb.append(" ");
		sb.append("a.DT_FINALIZACAO as DT_ALTA, ");
		sb.append(" ");
		sb.append("ma.CD_MOTIVO_ALTA, ");
		sb.append("ma.DS_MOTIVO_ALTA, ");
		sb.append("ma.TP_MOTIVO_ALTA ");
		sb.append(" ");
		sb.append("FROM suspeita_covid19 sc, ");
		sb.append("atendimento a, ");
		sb.append("atendimento_int ai left outer join motivo_alta ma on ai.CD_MOTIVO_ALTA = ma.CD_MOTIVO_ALTA, ");
		sb.append("paciente pac, ");
		sb.append("pessoa p, ");
		sb.append("pessoa_dependente pd, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO ");
		sb.append("where sc.CD_ATENDIMENTO = a.CD_ATENDIMENTO ");
		sb.append("and a.CD_ATENDIMENTO = ai.CD_ATENDIMENTO ");
		sb.append("and a.DT_CANCELAMENTO is null ");
		sb.append("and sc.SN_ATIVO = 'S' ");
		sb.append("and a.CD_PACIENTE = pac.CD_PACIENTE ");
		sb.append("and pac.CD_PESSOA = p.CD_PESSOA ");
		sb.append("and p.CD_PESSOA = pd.CD_PESSOA ");
		sb.append("and pd.NR_MATRICULA = pt.NR_MATRICULA ");
		sb.append("and pd.CD_CORPORACAO = pt.CD_CORPORACAO ");
		sb.append(") r ");

		sb.append("WHERE 1=1 ");
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			sb.append("AND NM_PACIENTE LIKE :nome ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSexo())) {
			sb.append("AND TP_SEXO = :sexo ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getTipo())) {
			sb.append("AND TIPO = :tipo ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCategoria())) {
			sb.append("AND CATEGORIA = :categoria ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCorporacao())) {
			sb.append("AND CORPORACAO = :corporacao ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSituacao())) {
			sb.append("AND SITUACAO = :situacao ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getStatus())) {
			sb.append("AND STATUS_COVID = :status ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getObito())) {
			if(filtro.getObito().equals("S")) {
				sb.append("AND TP_MOTIVO_ALTA = 'O' ");
				
			} else if(filtro.getObito().equals("N")) {
				sb.append("AND TP_MOTIVO_ALTA <> 'O' ");
			}
		}
		
		if(filtro.getMatricula() != null) {
			sb.append("AND MATRICULA LIKE :matricula ");
		}
				
		sb.append("ORDER BY nm_paciente ");
		
		
		Query query = getEntityManager().createNativeQuery(sb.toString());
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			query.setParameter("nome", "%" + filtro.getNome() + "%");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSexo())) {
			query.setParameter("sexo", filtro.getSexo());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getTipo())) {
			query.setParameter("tipo", filtro.getTipo());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCategoria())) {
			query.setParameter("categoria", filtro.getCategoria());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCorporacao())) {
			query.setParameter("corporacao", filtro.getCorporacao());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSituacao())) {
			query.setParameter("situacao", filtro.getSituacao());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getStatus())) {
			query.setParameter("status", filtro.getStatus());
		}
		
		if(filtro.getMatricula() != null) {
			query.setParameter("matricula", "%" + filtro.getMatricula() + "%");
		}
		
		final List<Object[]> list = query.getResultList();
		
		List<SuspeitaCovidDTO> retorno = new ArrayList<SuspeitaCovidDTO>();
		
		for (final Object[] obj : list) {
			int i = 0;
			SuspeitaCovidDTO dto = new SuspeitaCovidDTO();
			dto.setId((Integer) obj[i++]);
			dto.setMatricula((String) obj[i++]);
			dto.setCodigoPessoa((Integer) obj[i++]);
			dto.setNome((String) obj[i++]);
			dto.setSexo((String) obj[i++]);
			dto.setIdade(((BigInteger) obj[i++]).intValue());
			dto.setTipo((String) obj[i++]);
			dto.setCategoria((String) obj[i++]);
			dto.setCorporacao((String) obj[i++]);
			dto.setSituacao((String) obj[i++]);
			dto.setStatus((String) obj[i++]);
			dto.setDataColetaExame((Date) obj[i++]);
			dto.setDataResultadoExame((Date) obj[i++]);
			dto.setDataAlta((Date) obj[i++]);
			dto.setCodigoMotivoAlta((Integer) obj[i++]);
			dto.setDescricaoMotivoAlta((String) obj[i++]);
			dto.setTipoMotivoAlta((String) obj[i++]);
			retorno.add(dto);
		}
		
		return retorno;
	}
	
	public List<SuspeitaCovidDTO> consultarDTOPorFiltroUrgencia(FiltroSuspeitaCovid filtro) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * ");
		sb.append("FROM ( ");
		sb.append("/* ");
		sb.append("Titulares Urgência ");
		sb.append("*/ ");
		sb.append("SELECT sc.CD_SUSPEITA_COVID19, ");
		sb.append("CAST(CONCAT(pt.NR_MATRICULA, '-', pt.NR_DIGITO) as char) as MATRICULA, ");
		sb.append("p.cd_pessoa, ");
		sb.append("P.NM_PESSOA AS NM_PACIENTE, ");
		sb.append("P.TP_SEXO, ");
		sb.append("FLOOR((DATEDIFF(a.DT_ABERTURA,p.dt_nascimento) / 365)) AS DS_IDADE, ");
		sb.append("'TITULAR' as tipo, ");
		sb.append(" ");
		sb.append("(CASE   ");
		sb.append("	WHEN po.TP_CATEGORIA = 'O' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'P' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 THEN 'PENS' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 THEN 'FC' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as categoria, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("	WHEN pt.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("	WHEN pt.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as corporacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN pt.TP_TABELA = 1 then 'ATIVO'   ");
		sb.append("WHEN pt.TP_TABELA = 2 then 'INATIVO'   ");
		sb.append("ELSE 'NÃO INFORMADO'   ");
		sb.append("END   ");
		sb.append(") as situacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO'   ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO'   ");
		sb.append("WHEN sc.DT_COLETA_EXAME IS NOT NULL THEN 'AGUARDANDO'   ");
		sb.append("WHEN sc.CD_SUSPEITA_COVID19 is NOT NULL THEN 'SUSPEITO'   ");
		sb.append("END) as STATUS_COVID, ");
		sb.append(" ");
		sb.append("sc.DT_COLETA_EXAME, ");
		sb.append("sc.DT_RESULTADO_EXAME, ");
		sb.append(" ");
		sb.append("a.DT_FINALIZACAO as DT_ALTA, ");
		sb.append(" ");
		sb.append("ma.CD_MOTIVO_ALTA, ");
		sb.append("ma.DS_MOTIVO_ALTA, ");
		sb.append("ma.TP_MOTIVO_ALTA ");
		sb.append(" ");
		sb.append("FROM suspeita_covid19 sc, ");
		sb.append("atendimento a, ");
		sb.append("atendimento_urg au left outer join motivo_alta ma on au.CD_MOTIVO_ALTA = ma.CD_MOTIVO_ALTA, ");
		sb.append("paciente pac, ");
		sb.append("pessoa p, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO ");
		sb.append("where sc.CD_ATENDIMENTO = a.CD_ATENDIMENTO ");
		sb.append("and a.CD_ATENDIMENTO = au.CD_ATENDIMENTO ");
		sb.append("and a.DT_CANCELAMENTO is null ");
		sb.append("and sc.SN_ATIVO = 'S' ");
		sb.append("and a.CD_PACIENTE = pac.CD_PACIENTE ");
		sb.append("and pac.CD_PESSOA = p.CD_PESSOA ");
		sb.append("and p.CD_PESSOA = pt.CD_PESSOA ");
		sb.append(" ");
		sb.append("UNION ");
		sb.append(" ");
		sb.append("/* ");
		sb.append("Dependentes Urgência ");
		sb.append("*/ ");
		sb.append("SELECT sc.CD_SUSPEITA_COVID19, ");
		sb.append("CAST(CONCAT(pt.NR_MATRICULA, '-', pt.NR_DIGITO, '/', pd.NR_SEQUENCIAL) as char) as MATRICULA, ");
		sb.append("p.cd_pessoa, ");
		sb.append("P.NM_PESSOA AS NM_PACIENTE, ");
		sb.append("P.TP_SEXO, ");
		sb.append("FLOOR((DATEDIFF(a.DT_ABERTURA,p.dt_nascimento) / 365)) AS DS_IDADE, ");
		sb.append("'DEPENDENTE' as tipo, ");
		sb.append(" ");
		sb.append("(CASE   ");
		sb.append("	WHEN po.TP_CATEGORIA = 'O' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'P' THEN 'MIL' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 THEN 'PENS' ");
		sb.append("	WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 THEN 'FC' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as categoria, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("	WHEN pt.CD_CORPORACAO = 6 then 'PM' ");
		sb.append("	WHEN pt.CD_CORPORACAO = 40 then 'CBM' ");
		sb.append("	ELSE 'NÃO INFORMADO' ");
		sb.append("END) as corporacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN pt.TP_TABELA = 1 then 'ATIVO'   ");
		sb.append("WHEN pt.TP_TABELA = 2 then 'INATIVO'   ");
		sb.append("ELSE 'NÃO INFORMADO'   ");
		sb.append("END   ");
		sb.append(") as situacao, ");
		sb.append(" ");
		sb.append("(CASE ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO'   ");
		sb.append("WHEN sc.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO'   ");
		sb.append("WHEN sc.DT_COLETA_EXAME IS NOT NULL THEN 'AGUARDANDO'   ");
		sb.append("WHEN sc.CD_SUSPEITA_COVID19 is NOT NULL THEN 'SUSPEITO'   ");
		sb.append("END) as STATUS_COVID, ");
		sb.append(" ");
		sb.append("sc.DT_COLETA_EXAME, ");
		sb.append("sc.DT_RESULTADO_EXAME, ");
		sb.append(" ");
		sb.append("a.DT_FINALIZACAO as DT_ALTA, ");
		sb.append(" ");
		sb.append("ma.CD_MOTIVO_ALTA, ");
		sb.append("ma.DS_MOTIVO_ALTA, ");
		sb.append("ma.TP_MOTIVO_ALTA ");
		sb.append(" ");
		sb.append("FROM suspeita_covid19 sc, ");
		sb.append("atendimento a, ");
		sb.append("atendimento_urg au left outer join motivo_alta ma on au.CD_MOTIVO_ALTA = ma.CD_MOTIVO_ALTA, ");
		sb.append("paciente pac, ");
		sb.append("pessoa p, ");
		sb.append("pessoa_dependente pd, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO ");
		sb.append("where sc.CD_ATENDIMENTO = a.CD_ATENDIMENTO ");
		sb.append("and a.CD_ATENDIMENTO = au.CD_ATENDIMENTO ");
		sb.append("and a.DT_CANCELAMENTO is null ");
		sb.append("and sc.SN_ATIVO = 'S' ");
		sb.append("and a.CD_PACIENTE = pac.CD_PACIENTE ");
		sb.append("and pac.CD_PESSOA = p.CD_PESSOA ");
		sb.append("and p.CD_PESSOA = pd.CD_PESSOA ");
		sb.append("and pd.NR_MATRICULA = pt.NR_MATRICULA ");
		sb.append("and pd.CD_CORPORACAO = pt.CD_CORPORACAO ");
		sb.append(" ");
		sb.append(") r ");

		sb.append("WHERE 1=1 ");
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			sb.append("AND NM_PACIENTE LIKE :nome ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSexo())) {
			sb.append("AND TP_SEXO = :sexo ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getTipo())) {
			sb.append("AND TIPO = :tipo ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCategoria())) {
			sb.append("AND CATEGORIA = :categoria ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCorporacao())) {
			sb.append("AND CORPORACAO = :corporacao ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSituacao())) {
			sb.append("AND SITUACAO = :situacao ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getStatus())) {
			sb.append("AND STATUS_COVID = :status ");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getObito())) {
			if(filtro.getObito().equals("S")) {
				sb.append("AND TP_MOTIVO_ALTA = 'O' ");
				
			} else if(filtro.getObito().equals("N")) {
				sb.append("AND TP_MOTIVO_ALTA <> 'O' ");
			}
		}
		
		if(filtro.getMatricula() != null) {
			sb.append("AND MATRICULA LIKE :matricula ");
		}
				
		sb.append("ORDER BY nm_paciente ");
		
		
		Query query = getEntityManager().createNativeQuery(sb.toString());
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			query.setParameter("nome", "%" + filtro.getNome() + "%");
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSexo())) {
			query.setParameter("sexo", filtro.getSexo());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getTipo())) {
			query.setParameter("tipo", filtro.getTipo());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCategoria())) {
			query.setParameter("categoria", filtro.getCategoria());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCorporacao())) {
			query.setParameter("corporacao", filtro.getCorporacao());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getSituacao())) {
			query.setParameter("situacao", filtro.getSituacao());
		}
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getStatus())) {
			query.setParameter("status", filtro.getStatus());
		}
		
		if(filtro.getMatricula() != null) {
			query.setParameter("matricula", "%" + filtro.getMatricula() + "%");
		}
		
		final List<Object[]> list = query.getResultList();
		
		List<SuspeitaCovidDTO> retorno = new ArrayList<SuspeitaCovidDTO>();
		
		for (final Object[] obj : list) {
			int i = 0;
			SuspeitaCovidDTO dto = new SuspeitaCovidDTO();
			dto.setId((Integer) obj[i++]);
			dto.setMatricula((String) obj[i++]);
			dto.setCodigoPessoa((Integer) obj[i++]);
			dto.setNome((String) obj[i++]);
			dto.setSexo((String) obj[i++]);
			dto.setIdade(((BigInteger) obj[i++]).intValue());
			dto.setTipo((String) obj[i++]);
			dto.setCategoria((String) obj[i++]);
			dto.setCorporacao((String) obj[i++]);
			dto.setSituacao((String) obj[i++]);
			dto.setStatus((String) obj[i++]);
			dto.setDataColetaExame((Date) obj[i++]);
			dto.setDataResultadoExame((Date) obj[i++]);
			dto.setDataAlta((Date) obj[i++]);
			dto.setCodigoMotivoAlta((Integer) obj[i++]);
			dto.setDescricaoMotivoAlta((String) obj[i++]);
			dto.setTipoMotivoAlta((String) obj[i++]);
			retorno.add(dto);
		}
		
		return retorno;
	}
	
}