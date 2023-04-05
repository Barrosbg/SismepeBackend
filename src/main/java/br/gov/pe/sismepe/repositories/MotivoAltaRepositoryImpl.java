package br.gov.pe.sismepe.repositories;

import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.MotivoAlta;
import br.gov.pe.sismepe.filtro.FiltroMotivoAlta;

@Repository
public class MotivoAltaRepositoryImpl extends AbstractDAO<MotivoAlta, Integer, FiltroMotivoAlta>
		implements MotivoAltaRepository {

	
}