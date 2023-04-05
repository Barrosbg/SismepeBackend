package br.gov.pe.sismepe.repositories;

import br.gov.pe.sismepe.domain.Conselho;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConselhoRepository extends JpaRepository<Conselho, Long> {
    Conselho findBySiglaAndUf(String sigla, String uf);

    Page<Conselho> findBySiglaOrUfOrNumero(String sigla, String uf, String numero, Pageable pageable);
    Page<Conselho> findByNumeroContaining(String numero, Pageable pageable);
}
