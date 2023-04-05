package br.gov.pe.sismepe.repositories;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.SetorPessoaTesteCovid;
import br.gov.pe.sismepe.filtro.FiltroSetorPessoaTesteCovid;
import br.gov.pe.sismepe.util.Utils;

@Repository
public class SetorPessoaTesteCovidRepositoryImpl extends AbstractDAO<SetorPessoaTesteCovid, Integer, FiltroSetorPessoaTesteCovid>
		implements SetorPessoaTesteCovidRepository {

	public List<SetorPessoaTesteCovid> consultarPorFiltro(FiltroSetorPessoaTesteCovid filtro) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT s FROM SetorPessoaTesteCovid s WHERE s.ativo = 'S' ");

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