package br.gov.pe.sismepe.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.TestePCRCovid;
import br.gov.pe.sismepe.dto.covid.TestePCRCovidDTO;
import br.gov.pe.sismepe.filtro.FiltroTestePCRCovid;
import br.gov.pe.sismepe.util.Utils;

@Repository
public class TestePCRCovidRepositoryImpl extends AbstractDAO<TestePCRCovid, Integer, FiltroTestePCRCovid>
		implements TestePCRCovidRepository {

	public List<TestePCRCovid> consultarPorFiltro(FiltroTestePCRCovid filtro) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT s FROM SuspeitaCovid s JOIN FETCH s.pessoa WHERE s.ativo = 'S' ");

		if (Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			sb.append(" AND s.pessoa.nome LIKE :nome ");
		}

		sb.append(" ORDER BY s.pessoa.nome");

		Query query = getEntityManager().createQuery(sb.toString());

		if (Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			query.setParameter("nome", "%" + filtro.getNome() + "%");
		}

		return query.getResultList();
	}

	@Override
	public List<TestePCRCovidDTO> consultarDTOPorFiltro(FiltroTestePCRCovid filtro) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * ");
		sb.append("FROM ( ");
		
		sb.append("select t.`CD_TESTE_PCR`, ");
		sb.append("p.`NM_PESSOA`, ");
		sb.append("CAST(CONCAT(pt.NR_MATRICULA, '-', pt.NR_DIGITO) as char) as MATRICULA, ");
		sb.append("p.`TP_SEXO` as SEXO, ");
		sb.append("'TITULAR' AS TIPO, ");
		sb.append("p.`NR_CPF`, ");
		sb.append("p.`DT_NASCIMENTO`, ");
		sb.append("(case "); 
		sb.append("WHEN po.TP_CATEGORIA = 'O' THEN 'MIL' ");  
		sb.append("WHEN po.TP_CATEGORIA = 'P' THEN 'MIL' ");  
		sb.append("WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 THEN 'PENS' ");  
		sb.append("WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 THEN 'FC' ");  
		sb.append("ELSE 'NÃO INFORMADO' ");  
		sb.append("END) as categoria, ");

		sb.append("(CASE "); 
		sb.append("    WHEN pt.CD_CORPORACAO = 6 then 'PM' "); 
		sb.append("    WHEN pt.CD_CORPORACAO = 40 then 'CBM' "); 
		sb.append("    ELSE 'NÃO INFORMADO' "); 
		sb.append("END "); 
		sb.append(") as corporacao, "); 
		sb.append("(CASE "); 
		sb.append("    WHEN pt.TP_TABELA = 1 then 'ATIVO' "); 
		sb.append("    WHEN pt.TP_TABELA = 2 then 'INATIVO' "); 
		sb.append("    ELSE 'NÃO INFORMADO' "); 
		sb.append("END "); 
		sb.append(") as situacao, ");

		sb.append("(CASE "); 
		sb.append("	WHEN t.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO' "); 
		sb.append("    WHEN t.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO' "); 
		sb.append("    ELSE 'AGUARDANDO' "); 
		sb.append("END) as STATUS, ");

		sb.append("t.`DT_COLETA_EXAME`, ");
		sb.append("t.`DT_RESULTADO_EXAME` ");

		sb.append("from `teste_pcr_covid` t, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO, ");
		sb.append("pessoa p ");
		sb.append("where t.`CD_PESSOA` = p.`CD_PESSOA` ");
		sb.append("and pt.`CD_PESSOA` = p.`CD_PESSOA` ");
		sb.append("and t.sn_ativo = 'S' ");

		sb.append("union ");

		sb.append("select t.`CD_TESTE_PCR`, ");
		sb.append("p.`NM_PESSOA`, ");
		sb.append("CAST(CONCAT(pt.NR_MATRICULA, '-', pt.NR_DIGITO, '/', pd.NR_SEQUENCIAL) as char) as MATRICULA, ");
		sb.append("p.`TP_SEXO` as SEXO, ");
		sb.append("'DEPENDENTE' AS TIPO, ");
		sb.append("p.`NR_CPF`, ");
		sb.append("p.`DT_NASCIMENTO`, ");
		sb.append("(case "); 
		sb.append("WHEN po.TP_CATEGORIA = 'O' THEN 'MIL' ");  
		sb.append("WHEN po.TP_CATEGORIA = 'P' THEN 'MIL' ");  
		sb.append("WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO = 101 THEN 'PENS' ");  
		sb.append("WHEN po.TP_CATEGORIA = 'C' AND po.CD_POSTO <> 101 THEN 'FC' ");  
		sb.append("ELSE 'NÃO INFORMADO' ");  
		sb.append("END) as categoria, ");

		sb.append("(CASE "); 
		sb.append("    WHEN pt.CD_CORPORACAO = 6 then 'PM' "); 
		sb.append("    WHEN pt.CD_CORPORACAO = 40 then 'CBM' "); 
		sb.append("    ELSE 'NÃO INFORMADO' "); 
		sb.append("END "); 
		sb.append(") as corporacao, "); 
		sb.append("(CASE "); 
		sb.append("    WHEN pt.TP_TABELA = 1 then 'ATIVO' "); 
		sb.append("    WHEN pt.TP_TABELA = 2 then 'INATIVO' "); 
		sb.append("    ELSE 'NÃO INFORMADO' "); 
		sb.append("END "); 
		sb.append(") as situacao, ");

		sb.append("(CASE "); 
		sb.append("	WHEN t.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO' "); 
		sb.append("    WHEN t.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO' "); 
		sb.append("    ELSE 'AGUARDANDO'  ");
		sb.append("END) as STATUS, ");

		sb.append("t.`DT_COLETA_EXAME`, ");
		sb.append("t.`DT_RESULTADO_EXAME` ");

		sb.append("from `teste_pcr_covid` t, ");
		sb.append("`pessoa_dependente` pd, ");
		sb.append("pessoa_titular pt left outer join posto po on po.CD_POSTO = pt.CD_POSTO, ");
		sb.append("pessoa p ");
		sb.append("where t.`CD_PESSOA` = p.`CD_PESSOA` ");
		sb.append("and pd.`CD_PESSOA` = p.`CD_PESSOA` ");
		sb.append("AND pd.NR_MATRICULA = pt.NR_MATRICULA ");
		sb.append("and t.sn_ativo = 'S' ");

		sb.append("union ");

		sb.append("select t.`CD_TESTE_PCR`, t.`NM_NOME`, ");
		sb.append("'' as MATRICULA, ");
		sb.append("t.tp_sexo as SEXO, ");
		sb.append("'OUTROS' AS TIPO, ");
		sb.append("t.`NR_CPF`, ");
		sb.append("t.`DT_NASCIMENTO` as Nascimento, ");
		sb.append("'' as categoria, ");
		sb.append("'' as corporacao, ");
		sb.append("'' as situacao, ");
		sb.append("(CASE "); 
		sb.append("	WHEN t.SN_EXAME_POSITIVO = 'S' THEN 'POSITIVO' "); 
		sb.append("    WHEN t.SN_EXAME_POSITIVO = 'N' THEN 'NEGATIVO' "); 
		sb.append("    ELSE 'AGUARDANDO' "); 
		sb.append("END) as STATUS, ");

		sb.append("t.`DT_COLETA_EXAME`, ");
		sb.append("t.`DT_RESULTADO_EXAME` "); 

		sb.append("from `teste_pcr_covid` t ");
		sb.append("where t.`CD_PESSOA` is null ");
		sb.append("and t.sn_ativo = 'S' ");

		sb.append(")r ");
		sb.append("WHERE 1=1 ");

		if (Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			sb.append("AND NM_PESSOA LIKE :nome ");
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getSexo())) {
			sb.append("AND SEXO = :sexo ");
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getTipo())) {
			sb.append("AND TIPO = :tipo ");
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getCategoria())) {
			sb.append("AND CATEGORIA = :categoria ");
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getCorporacao())) {
			sb.append("AND CORPORACAO = :corporacao ");
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getSituacao())) {
			sb.append("AND SITUACAO = :situacao ");
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getStatus())) {
			sb.append("AND status = :status ");
		}

		if (filtro.getMatricula() != null) {
			sb.append("AND MATRICULA LIKE :matricula ");
		}
		
		if(filtro.getDataInicio() != null) {
			sb.append("AND DT_COLETA_EXAME >= :dataInicio ");
		}
		
		if(filtro.getDataFim() != null) {
			sb.append("AND DT_COLETA_EXAME <= :dataFim ");
		}

		sb.append("ORDER BY nm_pessoa ");

		Query query = getEntityManager().createNativeQuery(sb.toString());

		if (Utils.isStringNaoNulaNaoVazia(filtro.getNome())) {
			query.setParameter("nome", "%" + filtro.getNome() + "%");
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getSexo())) {
			query.setParameter("sexo", filtro.getSexo());
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getTipo())) {
			query.setParameter("tipo", filtro.getTipo());
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getCategoria())) {
			query.setParameter("categoria", filtro.getCategoria());
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getCorporacao())) {
			query.setParameter("corporacao", filtro.getCorporacao());
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getSituacao())) {
			query.setParameter("situacao", filtro.getSituacao());
		}

		if (Utils.isStringNaoNulaNaoVazia(filtro.getStatus())) {
			query.setParameter("status", filtro.getStatus());
		}

		if (filtro.getMatricula() != null) {
			query.setParameter("matricula", "%" + filtro.getMatricula() + "%");
		}
		
		if(filtro.getDataInicio() != null) {
			query.setParameter("dataInicio", filtro.getDataInicio());
		}
		
		if(filtro.getDataFim() != null) {
			query.setParameter("dataFim", filtro.getDataFim());
		}

		final List<Object[]> list = query.getResultList();

		List<TestePCRCovidDTO> retorno = new ArrayList<TestePCRCovidDTO>();

		for (final Object[] obj : list) {
			int i = 0;
			TestePCRCovidDTO dto = new TestePCRCovidDTO();
			dto.setId((Integer) obj[i++]);
			dto.setNome((String) obj[i++]);
			dto.setMatricula((String) obj[i++]);
			dto.setSexo((String) obj[i++]);
			dto.setTipo((String) obj[i++]);
			dto.setCpf((String) obj[i++]);
			dto.setDataNascimento((Date) obj[i++]);
			dto.setCategoria((String) obj[i++]);
			dto.setCorporacao((String) obj[i++]);
			dto.setSituacao((String) obj[i++]);
			dto.setStatus((String) obj[i++]);
			dto.setDataColetaExame((Date) obj[i++]);
			dto.setDataResultadoExame((Date) obj[i++]);
			retorno.add(dto);
		}

		return retorno;
	}

}