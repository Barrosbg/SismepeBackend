package br.gov.pe.sismepe.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.SolicitacaoProcedimentoExterno;
import br.gov.pe.sismepe.util.Utils;

@Repository
public class SolicitacaoProcedimentoExternoRepositoryImpl {

	 @PersistenceContext
	 private EntityManager em;
	 
	 public Page<SolicitacaoProcedimentoExterno> findByParameters(Long prestadorId, Long pacienteId, String dataCadastro, Pageable pageable) {
	        CriteriaBuilder builder = this.em.getCriteriaBuilder();
	        CriteriaQuery<SolicitacaoProcedimentoExterno> criteria = builder.createQuery(SolicitacaoProcedimentoExterno.class);
	        	        
	        Root<SolicitacaoProcedimentoExterno> sols = criteria.from(SolicitacaoProcedimentoExterno.class);
	        List<Predicate> predicates = new ArrayList<>();

	        predicates.add(builder.equal(sols.get("ativo"), "S"));
	        
	        if(prestadorId != null) {
	        	predicates.add(builder.equal(sols.get("prestadorSolicitante").get("id"), prestadorId));
	        }
	        
	        if(pacienteId != null) {
	        	predicates.add(builder.equal(sols.get("paciente").get("id"), pacienteId));
	        }
	        
	        if(dataCadastro != null && !dataCadastro.equals("")) {
	        	Date dateIni = Utils.stringToDate(dataCadastro + " 00:00:00");
	        	Date dateFim = Utils.stringToDate(dataCadastro + " 23:59:59");
	        	predicates.add(builder.between(sols.get("dataCadastro"), dateIni, dateFim));
	        }
	        
	        
	        criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
	        
	        criteria.orderBy(QueryUtils.toOrders(pageable.getSort(), sols, builder));

	        TypedQuery<SolicitacaoProcedimentoExterno> criteriaQuery = this.em.createQuery(criteria);
	        
	        List<SolicitacaoProcedimentoExterno> result = criteriaQuery.setFirstResult((int) pageable.getOffset())
	                .setMaxResults(pageable.getPageSize())
	                .getResultList();
	        
	     // Count
	        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
	        Root<SolicitacaoProcedimentoExterno> solsCount = countQuery.from(SolicitacaoProcedimentoExterno.class);
	        countQuery.select(builder.count(solsCount))
	                .where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

	        Long count = this.em.createQuery(countQuery).getSingleResult();

	        Page<SolicitacaoProcedimentoExterno> pagesSols = new PageImpl<>(result, pageable, count);

	        return pagesSols;
	 }

}
