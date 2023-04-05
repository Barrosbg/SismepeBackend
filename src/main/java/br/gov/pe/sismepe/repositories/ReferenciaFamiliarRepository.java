package br.gov.pe.sismepe.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pe.sismepe.domain.ReferenciaFamiliar;

public interface ReferenciaFamiliarRepository extends JpaRepository<ReferenciaFamiliar, Long> {
	
	Page<ReferenciaFamiliar> findByPaciente_idAndSituacao(Long pacienteId, String situacao, Pageable pageable);

}
