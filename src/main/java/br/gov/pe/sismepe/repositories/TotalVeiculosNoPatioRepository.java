package br.gov.pe.sismepe.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.TotalVeiculosNoPatio;

@Repository
public interface TotalVeiculosNoPatioRepository extends JpaRepository<TotalVeiculosNoPatio, Long> {

	public TotalVeiculosNoPatio findByDataEntradaBetween(Date dataInicio, Date dataFim);
	
}
