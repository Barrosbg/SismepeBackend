package br.gov.pe.sismepe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.Uf;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {
	Uf findBySigla(String sigla);
}
