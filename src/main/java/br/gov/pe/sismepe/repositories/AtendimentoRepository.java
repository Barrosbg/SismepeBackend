package br.gov.pe.sismepe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pe.sismepe.domain.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

}