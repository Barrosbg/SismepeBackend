package br.gov.pe.sismepe.repositories;

import br.gov.pe.sismepe.domain.Ome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OmeRepository extends JpaRepository<Ome, Long> {
    Page<Ome> findByDescricaoContaining(String descricao, Pageable pagable);
    Page<Ome> findByAbreviacaoContaining(String abreviacao, Pageable pagable);
}
