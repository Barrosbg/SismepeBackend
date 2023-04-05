package br.gov.pe.sismepe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pe.sismepe.domain.tecnico;

public interface TecnicoRepository extends JpaRepository<tecnico, Integer> {

}
