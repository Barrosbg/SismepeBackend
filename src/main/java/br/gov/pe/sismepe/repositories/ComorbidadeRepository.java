package br.gov.pe.sismepe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pe.sismepe.domain.Comorbidade;

public interface ComorbidadeRepository extends JpaRepository<Comorbidade, Integer> {

}
