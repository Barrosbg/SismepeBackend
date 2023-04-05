package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.BiometriaImpressaoDigital;
import br.gov.pe.sismepe.dto.BiometriaImpressaoDigitalDTO;

@Repository
public interface BiometriaImpressaoDigitalRepository extends JpaRepository<BiometriaImpressaoDigital, Integer> {
//	@Transactional(readOnly = true)
//	@Query("select bid from BiometriaImpressaoDigital bid where bid.cdBiometria = :cd_biometria and bid.cdIdentificadorDedo = :cd_identificador_dedo")
//	BiometriaImpressaoDigitalDTO getBiometriaImpressaoDigitalDTO(@Param("cd_biometria") Integer cdBiometria, @Param("cd_identificador_dedo") Integer cdIdentificadorDedo);
//	
//	@Transactional(readOnly = true)
//	@Query("select bid from BiometriaImpressaoDigital bid where bid.cdIdentificadorDedo = :cd_identificador_dedo and bid.dsBiometriaHash = :ds_biometria_hash")
//	BiometriaImpressaoDigital getBiometriaImpressaoDigital(@Param("cd_identificador_dedo") Integer cdIdentificadorDedo, @Param("ds_biometria_hash") String dsBiometriaHash);
//	
//	@Transactional(readOnly = true)
//	@Query("select bid from BiometriaImpressaoDigital bid where bid.cdBiometria = :cd_biometria")
//	List<BiometriaImpressaoDigital> getBiometriaImpressoesDigitais(@Param("cd_biometria") Integer cdBiometria);
}
