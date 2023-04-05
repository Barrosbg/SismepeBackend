package br.gov.pe.sismepe.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pe.sismepe.domain.AssistenciaSocial;
import br.gov.pe.sismepe.domain.AssistenciaSocialEvolucao;

public interface AssistenciaSocialEvolucaoRepository extends JpaRepository<AssistenciaSocialEvolucao, Long> {

	Page<AssistenciaSocialEvolucao> findByAssistenciaSocialAndSituacao(AssistenciaSocial assistenciaSocial, 
			String situacao, Pageable pageable);
}
