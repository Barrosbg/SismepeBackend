package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.CepLogradouro;

@Repository
public interface CepLogradouroRepository extends JpaRepository<CepLogradouro, Long>{
	@Transactional(readOnly = true)
	List<CepLogradouro> findBycepLog(Long cep);
	
	
	
}
