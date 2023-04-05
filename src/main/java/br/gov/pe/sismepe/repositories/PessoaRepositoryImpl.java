package br.gov.pe.sismepe.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.PessoaTitular;
import br.gov.pe.sismepe.dto.PessoaTitularDTO;

@Repository
public class PessoaRepositoryImpl {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<PessoaTitular> pessoaTitularPorMatriculaOmeCorporacao(Integer matricula, Integer corporacao, Long ome){
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();		
		CriteriaQuery<PessoaTitular> criteria = builder.createQuery(PessoaTitular.class);
		Root<PessoaTitular> root = criteria.from(PessoaTitular.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
						
		if(matricula != null) {
			predicates.add(builder.equal(root.get("matricula"), matricula));
		}
		
		if(corporacao != null) {
			predicates.add(builder.equal(root.get("corporacao"), corporacao));
		}
		
		if(ome != null) {
			predicates.add(builder.equal(root.get("ome"), ome));
		}
				 
		
		criteria.where(predicates.toArray(new Predicate[0]));		
		TypedQuery<PessoaTitular> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	public Page<PessoaTitularDTO> pessoaTitularPorMatriculaOmeCorporacaoPerfil(Integer matricula, Integer corporacao, Long ome, Integer perfil, Pageable pageable){
		
		int pageNumber = pageable.getPageNumber();
	    int pageSize = pageable.getPageSize();
	    	
		StringBuilder sb = new StringBuilder();
		sb.append("select p.cd_pessoa as id, p.nm_pessoa as nome, pt.nr_matricula as matricula, pt.nr_digito as digito, pt.cd_corporacao as corporacao, pt.cd_ome as omeId, o.ds_ome as ome, u.cd_usuario as usuarioId ");

		sb.append("from pessoa as p ");
		sb.append("inner join pessoa_titular as pt on pt.cd_pessoa = p.cd_pessoa ");
		sb.append("inner join ome as o on o.cd_ome = pt.cd_ome ");
		sb.append("inner join usuario u on u.cd_pessoa = p.cd_pessoa ");
		
		if(perfil != null) {
			sb.append("inner join perfil_usuario pu on pu.cd_usuario = u.cd_usuario ");
		}
		
		
		sb.append("where 1 = 1 ");
		
		
		if(matricula != null) {
			sb.append("and pt.nr_matricula = " + matricula);
		}
		
		if(corporacao != null) {
			sb.append(" and pt.cd_corporacao = " + corporacao);
		}
		
		if(ome != null) {
			sb.append(" and pt.cd_ome = " + ome);
		}
		
		if(perfil != null) {
			sb.append(" and pu.cd_perfil = " + perfil);
		}
		
		
//		sb.append(" order by " + pageable.getSort().toString().replaceAll(":", "") + " ");

		Query query = manager.createNativeQuery(sb.toString());
		
		int count = query.getResultList().size();
		
		query.setFirstResult((pageNumber) * pageSize);
	    query.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		final List<Object[]> result = query.getResultList();

		List<PessoaTitularDTO> list = new ArrayList<PessoaTitularDTO>();

		for (final Object[] obj : result) {
			PessoaTitularDTO pessoa = new PessoaTitularDTO();
			int i = 0;
			pessoa.setPessoaId(((Integer)obj[i++]).longValue());
			pessoa.setNome((String) obj[i++]);
			pessoa.setMatricula((Integer) obj[i++]);
			pessoa.setDigito((Integer) obj[i++]);
			pessoa.setCorporacao((Integer) obj[i++]);
			pessoa.setOmeId(((Integer) obj[i++]).longValue());
			pessoa.setOme((String) obj[i++]);
			pessoa.setUsuarioId(((Integer) obj[i++]));
			list.add(pessoa);
		}

		
		return new PageImpl<PessoaTitularDTO>(list, pageable, count);
	}

}
