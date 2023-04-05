package br.gov.pe.sismepe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.AutorizacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.ItSolicitacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.SolicitacaoProcedimentoExterno;

@Repository
public interface AutorizacaoProcedimentoExternoRepository extends JpaRepository<AutorizacaoProcedimentoExterno, Long>{

	List<AutorizacaoProcedimentoExterno> findByItSolicitacao_solicitacaoProcedimentoExterno(SolicitacaoProcedimentoExterno sol);

	Optional<AutorizacaoProcedimentoExterno> findByItSolicitacao(ItSolicitacaoProcedimentoExterno it);

}
