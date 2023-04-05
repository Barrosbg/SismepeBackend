package br.gov.pe.sismepe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pe.sismepe.domain.Habitacao;

public interface HabitacaoRepository extends JpaRepository<Habitacao, Long>{

	Habitacao findByPaciente_idAndSituacao(Long pacienteId, String ativo);
	
}
