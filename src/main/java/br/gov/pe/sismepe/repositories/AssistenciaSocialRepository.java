package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pe.sismepe.domain.AssistenciaSocial;
import br.gov.pe.sismepe.domain.Atendimento;
import br.gov.pe.sismepe.domain.Paciente;

public interface AssistenciaSocialRepository extends JpaRepository<AssistenciaSocial, Long> {

	Page<AssistenciaSocial> findByAtendimento_pacienteAndAtivo(Paciente paciente, String ativo, Pageable pageable);
	
	List<AssistenciaSocial> findByAtendimentoAndAtivo(Atendimento atendimento, String ativo);
}
