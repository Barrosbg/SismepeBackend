package br.gov.pe.sismepe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.Biometria;
import br.gov.pe.sismepe.dto.BiometriaDTO;

@Repository
public interface BiometriaRepository extends JpaRepository<Biometria, Integer> {
	@Transactional(readOnly = true)
	@Query("select b from Biometria b where b.cdPessoa = :cd_pessoa")
	BiometriaDTO getBiometriaByPessoa(@Param("cd_pessoa") Integer cd_pessoa);
	
	@Transactional(readOnly = true)
	@Query("select b from Biometria b where b.cdPessoa = :cd_pessoa")
	Biometria getBiometria(@Param("cd_pessoa") Integer cd_pessoa);
}
