package br.gov.pe.sismepe.repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.pe.sismepe.domain.CadastroDeCarros;
import br.gov.pe.sismepe.domain.RegistroEntradaVeiculos;

public interface RegistroEntradaVeiculosRepository extends JpaRepository<RegistroEntradaVeiculos, Long> { 
	
List<RegistroEntradaVeiculos> findBySituacaoEntrada(String situacaoEntrada );
List<RegistroEntradaVeiculos> findByDataEntrada(String situacaoEntrada );
RegistroEntradaVeiculos findRegistroEntradaVeiculosByCadastroCarrosAndSituacaoEntrada( CadastroDeCarros cadastroCarros , String situacaoEntrada );
     


@Query( "SELECT count(*) FROM RegistroEntradaVeiculos where ST_ENTRADA=:SituacaoEntrada ")
long countRegistroEntradaVeiculos(@Param("SituacaoEntrada") String SituacaoEntrada  );


@Query( "SELECT count(*) FROM RegistroEntradaVeiculos where DT_ENTRADA >= :dataInicio and  DT_ENTRADA <=  :dataFim")
long countTotalVeiculosEstacionados(@Param("dataInicio") Date dataInicio,@Param("dataFim") Date dataFim );
}
