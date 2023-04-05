package br.gov.pe.sismepe.repositories;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.OperativaPessoaTesteCovid;
import br.gov.pe.sismepe.filtro.FiltroOperativaPessoaTesteCovid;
import br.gov.pe.sismepe.util.Utils;

@Repository
public class OperativaPessoaTesteCovidRepositoryImpl extends AbstractDAO<OperativaPessoaTesteCovid, Integer, FiltroOperativaPessoaTesteCovid>
		implements OperativaPessoaTesteCovidRepository {

	public List<OperativaPessoaTesteCovid> consultarPorFiltro(FiltroOperativaPessoaTesteCovid filtro) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT s FROM OperativaPessoaTesteCovid s WHERE s.ativo = 'S' ");

		if (Utils.isStringNaoNulaNaoVazia(filtro.getDescricao())) {
			sb.append(" AND s.descricao LIKE :descricao");
		}

		sb.append(" ORDER BY s.descricao ");

		Query query = getEntityManager().createQuery(sb.toString());

		if (Utils.isStringNaoNulaNaoVazia(filtro.getDescricao())) {
			query.setParameter("descricao", "%" + filtro.getDescricao() + "%");
		}

		return query.getResultList();
	}
}