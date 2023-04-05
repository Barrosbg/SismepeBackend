package br.gov.pe.sismepe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.ExameTransfusional;

@Repository
public interface ExameTransfusionalRepository extends JpaRepository<ExameTransfusional, Long> {

	List<ExameTransfusional> findExameTransfusionalByPessoa_IdAndAtivo(Long id,String ativo);
	
	List<ExameTransfusional> findByAtivo(String ativo);
	
	Optional<ExameTransfusional> findByIdAndAtivo(Long id, String ativo);
	
	ExameTransfusional findExameTransfusionalById(Long id);
	
	
	

	
	
}
