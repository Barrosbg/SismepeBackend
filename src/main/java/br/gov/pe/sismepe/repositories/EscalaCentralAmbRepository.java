package br.gov.pe.sismepe.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.EscalaCentral;
import br.gov.pe.sismepe.domain.EscalaCentralAmb;

public interface EscalaCentralAmbRepository extends JpaRepository<EscalaCentralAmb, Long>{

	
	@Transactional(readOnly = true)
	Optional<EscalaCentralAmb> findById(Long id);
	
	
	Page<EscalaCentralAmb> findAll(Pageable pageable);
}
