package br.gov.pe.sismepe.repositories;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.List;
import java.util.function.LongSupplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.filtro.FiltroGenerico;

public abstract class AbstractDAO <T, PK extends Serializable, F extends FiltroGenerico<T>> {
	
	@SuppressWarnings("unchecked")
    private final Class<T> entityClass = (Class<T>) 
        ( (ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    
    @PersistenceContext
    private EntityManager entityManager;
 
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
    @Transactional
    public void save(T entity) {
        entityManager.persist(entity);
    }
    
    @Transactional
    public void update(T entity) {            
    	entityManager.merge(entity);
    }
    
    @Transactional
    public void delete(PK id) {            
    	entityManager.remove(entityManager.getReference(entityClass, id));
    }
    
    public T findById(PK id) {            
    	return entityManager.find(entityClass, id);
    }
    
    public List<T> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }    
    
    public List<T> createQuery(String jpql, Object... params) {
    	TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
    	
    	for(int i = 0; i < params.length; i++) {
    		query.setParameter((i+1), params[i]);
    	}
    	
    	return query.getResultList();
    }
    
    public List<T> consultar(F filtro) {

		String countQueryHQL = filtro.getCountQuery();
		Query countQuery = null;
		if (filtro.isConsultaSQL()) {
			countQuery = this.entityManager.createNativeQuery(countQueryHQL);
		} else {
			countQuery = this.entityManager.createQuery(countQueryHQL);
		}
		countQuery.setMaxResults(1);
		filtro.setParametros(countQuery);
		Long totalResultados = null;
		if (filtro.isConsultaSQL()) {
			totalResultados = ((BigInteger) countQuery.getSingleResult()).longValue();
		} else {
			totalResultados = (Long) countQuery.getSingleResult();
		}
		filtro.setTotalResultados(totalResultados);
		String selectQueryHQL = filtro.getSelectQuery();
		Query createQuery = null;
		if (filtro.isConsultaSQL()) {
			createQuery = this.entityManager.createNativeQuery(selectQueryHQL);
		} else {
			createQuery = this.entityManager.createQuery(selectQueryHQL);
		}

		filtro.setParametros(createQuery);

		createQuery.setFirstResult(filtro.getStart());
		createQuery.setMaxResults(filtro.getLength());
		List<T> resultList = null;
		if (filtro.isConsultaSQL()) {
			List<Object[]> retorno = createQuery.getResultList();
			resultList = filtro.montarObjeto(retorno);
		} else {
			resultList = createQuery.getResultList();
		}
		return resultList;
	}
    
    public String getCountQuery(String query) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT count(*) ");
		sb.append(query);
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public List<T> listar(F filtro) {

		String selectQueryHQL = filtro.getSelectQuery();
		Query createQuery = null;
		if (filtro.isConsultaSQL()) {
			createQuery = this.entityManager.createNativeQuery(selectQueryHQL);
		} else {
			createQuery = this.entityManager.createQuery(selectQueryHQL);
		}

		filtro.setParametros(createQuery);

		List<T> resultList = null;
		if (filtro.isConsultaSQL()) {
			List<Object[]> retorno = createQuery.getResultList();
			resultList = filtro.montarObjeto(retorno);
		} else {
			resultList = createQuery.getResultList();
		}
		return resultList;
	}
	
	public Page<T> consultar(F filtro, Pageable pageable) {
		List<T> resultList = this.consultar(filtro);
		LongSupplier sup = () -> filtro.getTotalResultados();
		return PageableExecutionUtils.getPage(resultList, pageable, sup);
	}
	
	public boolean existeEntidade(F filtro) {
		boolean retorno = false;

		String queryString = filtro.getSQLExisteEntidade();
		Query query = this.entityManager.createNativeQuery(queryString);
		
		Long totalResultados = ((BigInteger) query.getSingleResult()).longValue();
		if(totalResultados > 0) {
			retorno = true;
		}
		return retorno;
	}
}