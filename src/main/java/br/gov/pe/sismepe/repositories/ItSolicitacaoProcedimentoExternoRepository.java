package br.gov.pe.sismepe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.ItSolicitacaoProcedimentoExterno;

@Repository
public interface ItSolicitacaoProcedimentoExternoRepository extends JpaRepository<ItSolicitacaoProcedimentoExterno, Long>{
	
}
