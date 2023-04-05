package br.gov.pe.sismepe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pe.sismepe.domain.FichaInspecaoJms;

public interface FichaInspecaoJmsRepository extends JpaRepository<FichaInspecaoJms, Integer> {
	
	Optional<FichaInspecaoJms> findByIdAndAtivo(Integer id, String ativo);
	
	List<FichaInspecaoJms> findByAtivo(String ativo);
	
	Optional<FichaInspecaoJms> findByPessoaTitular_idAndAtivo(Long id, String ativo);

	List<FichaInspecaoJms> findFichasByPessoaTitular_idAndAtivo(Long id, String ativo);
}
