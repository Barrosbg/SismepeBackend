package br.gov.pe.sismepe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.PesquisaExpiracao;

@Repository
public interface PesquisaExpiracaoRepository extends JpaRepository<PesquisaExpiracao, Long> {

	PesquisaExpiracao findBycdAtendimento(Long cdAtendimento);
	
	
}
