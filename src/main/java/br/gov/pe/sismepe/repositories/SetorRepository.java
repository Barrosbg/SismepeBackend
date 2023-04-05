package br.gov.pe.sismepe.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer> {
	@Transactional(readOnly = true)
	Page<Setor> findSetorByDescricao(String descricao, Pageable   pageRequest);
	
	
}
