package br.gov.pe.sismepe.services;

import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.MotivoAlta;
import br.gov.pe.sismepe.filtro.FiltroMotivoAlta;
import br.gov.pe.sismepe.services.exceptions.NegocioException;

@Service
public class MotivoAltaService extends AbstractService<MotivoAlta, Integer, FiltroMotivoAlta> {
	
	@Override
	protected void validarUnicidadeEntidade(MotivoAlta entity) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validarInclusaoEntidade(MotivoAlta entity) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validarAlteracaoEntidade(MotivoAlta entity) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validarExclusaoEntidade(Integer id) throws NegocioException {
		// TODO Auto-generated method stub
		
	}
}