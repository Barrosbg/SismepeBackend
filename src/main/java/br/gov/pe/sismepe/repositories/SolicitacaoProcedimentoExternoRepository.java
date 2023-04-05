package br.gov.pe.sismepe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.Prestador;
import br.gov.pe.sismepe.domain.SolicitacaoProcedimentoExterno;

@Repository
public interface SolicitacaoProcedimentoExternoRepository extends JpaRepository<SolicitacaoProcedimentoExterno, Long>{
	
	@Transactional(readOnly = true)
	Optional<SolicitacaoProcedimentoExterno> findByIdAndPrestadorSolicitante(Long id, Prestador prestadorSolicitante);
	
	@Transactional(readOnly = true)
	Page<SolicitacaoProcedimentoExterno> findByPrestadorSolicitante(Prestador prestadorSolicitante, Pageable pageable);

	Page<SolicitacaoProcedimentoExterno> findByAtivo(String ativo, Pageable pageable);
	
	Page<SolicitacaoProcedimentoExterno> findByParameters(Long prestadorId, Long pacienteId, String dataCadastro, Pageable pageable);

	List<SolicitacaoProcedimentoExterno> findBySolicitacaoPai_Id(Long solicitacaoPai_Id);
}
