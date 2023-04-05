package br.gov.pe.sismepe.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.ProcedimentoSismepe;

@Repository
public interface ProcedimentoSismepeRepository extends JpaRepository<ProcedimentoSismepe, Long>{
	
	Page<ProcedimentoSismepe> findByDescricaoContainingAndValorIsNotNull(String descricao, Pageable pageable);

	Page<ProcedimentoSismepe> findByEspecificacaoContainingAndValorIsNotNull(String especificacao, Pageable pageable);
}
