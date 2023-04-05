package br.gov.pe.sismepe.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.Cidade;
import br.gov.pe.sismepe.domain.EscalaCentral;
import br.gov.pe.sismepe.domain.Uf;

public interface EscalaCentralRepository extends JpaRepository<EscalaCentral, Integer> {

	@Transactional(readOnly = true)
	Optional<EscalaCentral> findById(Integer id);
	
	@Transactional(readOnly = true)
	public List<EscalaCentral> findByHrInicialBetween(Date inicio, Date fim);
	
    Page<EscalaCentral> findAll(Pageable pageable);
	
//	@Query("SELECT t FROM EscalaCentral t WHERE t.hrInicial BETWEEN :inicio and  :termino")
//	public List<EscalaCentral> findByData(String inicio, String termino);
//	
}
