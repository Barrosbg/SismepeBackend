package br.gov.pe.sismepe.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.Cidade;
import br.gov.pe.sismepe.domain.Uf;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	Page<Cidade> findByUf(Uf uf, Pageable pageable);
}
