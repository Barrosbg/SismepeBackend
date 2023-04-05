package br.gov.pe.sismepe.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pe.sismepe.domain.TransfusionalSolicitacao;

public interface TransfusionalSolicitacaoRepository extends JpaRepository<TransfusionalSolicitacao, Integer>{

	Optional<TransfusionalSolicitacao> findBynemroRegistro(String nemroRegistro);
	
//	Page<TransfusionalSolicitacao> findAll (Integer id, Integer page, Integer linesPerPage, String orderBy, String directions);
	
}
