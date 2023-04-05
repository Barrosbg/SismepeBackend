package br.gov.pe.sismepe.repositories;

import java.util.ArrayList;
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

import br.gov.pe.sismepe.domain.Prestador;
import br.gov.pe.sismepe.repositories.customRepository.PrestadorRespositoryCustom;

@Repository
public class PrestadorRepositoryImpl implements PrestadorRespositoryCustom {
    @PersistenceContext
    private EntityManager em;

    // findByNomeContainingAndNomeNotLikeAndEspecialidades_idOrderByNomeAsc

    public Page<Prestador> findPrestador(String nome, Long especialidade, String numeroConselho, Pageable pageable) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Prestador> criteria = builder.createQuery(Prestador.class);
        Root<Prestador> prestadorRoot = criteria.from(Prestador.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (nome != null) {
            predicates.add(builder.like(builder.lower(prestadorRoot.get("nome")), "%" + nome.toLowerCase() + "%"));
        }

        if (numeroConselho != null && !numeroConselho.equalsIgnoreCase("")) {
            predicates.add(builder.like(prestadorRoot.get("numeroConselho"), numeroConselho));
        }

//        Join<Prestador, Especialidade> especialidades = prestadorRoot.join("especialidades");
//        prestadorRoot.join("especialidades");

//        if (especialidade != null) {
//            predicates.add(builder.equal(especialidades.get("id"), especialidade));
//        }

        criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        criteria.orderBy(QueryUtils.toOrders(pageable.getSort(), prestadorRoot, builder));

        List<Prestador> result = this.em.createQuery(criteria).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();

        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Prestador> prestadorRootCount = countQuery.from(Prestador.class);
        countQuery.select(builder.count(prestadorRootCount)).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        Predicate[] pre = predicates.toArray(new Predicate[predicates.size()]);

        Long count = null;

        try {
            count = this.em.createQuery(countQuery).getSingleResult();
        } catch(Exception e) {
            count = 0L;
        }

        Page<Prestador> resultado = new PageImpl<Prestador>(result, pageable, count);

        return resultado;
    }
    
    public Page<Prestador> findByParameters(String nome, Long especialidade, String numeroConselho, Pageable pageable) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Prestador> criteria = builder.createQuery(Prestador.class);
        	        
        Root<Prestador> prestadorRoot = criteria.from(Prestador.class);
        List<Predicate> predicates = new ArrayList<>();

        if (nome != null) {
            predicates.add(builder.like(builder.lower(prestadorRoot.get("nome")), "%" + nome.toLowerCase() + "%"));
        }

        if (numeroConselho != null && !numeroConselho.equalsIgnoreCase("")) {
            predicates.add(builder.like(prestadorRoot.get("numeroConselho"), numeroConselho));
        }
        
        
        criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        
        criteria.orderBy(QueryUtils.toOrders(pageable.getSort(), prestadorRoot, builder));

        TypedQuery<Prestador> criteriaQuery = this.em.createQuery(criteria);
        
        List<Prestador> result = criteriaQuery.setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        
     // Count
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Prestador> solsCount = countQuery.from(Prestador.class);
        countQuery.select(builder.count(solsCount))
                .where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        Long count = this.em.createQuery(countQuery).getSingleResult();

        Page<Prestador> pagePrest = new PageImpl<>(result, pageable, count);

        return pagePrest;
 }
}
