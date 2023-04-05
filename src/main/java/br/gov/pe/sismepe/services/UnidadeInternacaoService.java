package br.gov.pe.sismepe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.UnidadeInternacao;
import br.gov.pe.sismepe.filtro.FiltroUnidadeInternacao;
import br.gov.pe.sismepe.repositories.UnidadeInternacaoRepository;
import br.gov.pe.sismepe.services.exceptions.NegocioException;

@Service
public class UnidadeInternacaoService extends AbstractService<UnidadeInternacao, Integer, FiltroUnidadeInternacao> {
	
	@Autowired
	private UnidadeInternacaoRepository repo;
	
	public List<UnidadeInternacao> consultarPorFiltro(FiltroUnidadeInternacao filtro) {
		return repo.consultarPorFiltro(filtro);
	}
	
	@Override
	protected void validarUnicidadeEntidade(UnidadeInternacao entity) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validarInclusaoEntidade(UnidadeInternacao entity) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validarAlteracaoEntidade(UnidadeInternacao entity) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validarExclusaoEntidade(Integer id) throws NegocioException {
		// TODO Auto-generated method stub
		
	}
}