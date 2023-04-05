package br.gov.pe.sismepe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pe.sismepe.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
