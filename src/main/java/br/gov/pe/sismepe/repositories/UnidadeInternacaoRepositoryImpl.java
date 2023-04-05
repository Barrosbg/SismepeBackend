package br.gov.pe.sismepe.repositories;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.UnidadeInternacao;
import br.gov.pe.sismepe.filtro.FiltroUnidadeInternacao;
import br.gov.pe.sismepe.util.Utils;

@Repository
public class UnidadeInternacaoRepositoryImpl extends AbstractDAO<UnidadeInternacao, Integer, FiltroUnidadeInternacao>
		implements UnidadeInternacaoRepository {
	
	public List<UnidadeInternacao> consultarPorFiltro(FiltroUnidadeInternacao filtro) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT s FROM UnidadeInternacao s WHERE s.ativo = 'S' ");
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCovid())) {
			sb.append(" AND s.covid = :covid ");
		}
		
		sb.append(" ORDER BY s.descricao");
		
		
		Query query = getEntityManager().createQuery(sb.toString());
		
		if(Utils.isStringNaoNulaNaoVazia(filtro.getCovid())) {
			query.setParameter("covid", filtro.getCovid());
		}
		
		return query.getResultList();
	}

	
}