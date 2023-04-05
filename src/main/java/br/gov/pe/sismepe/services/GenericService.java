package br.gov.pe.sismepe.services;

import java.io.Serializable;
import java.util.List;

import br.gov.pe.sismepe.filtro.FiltroGenerico;
import br.gov.pe.sismepe.services.exceptions.NegocioException;

public interface GenericService<T, ID extends Serializable, F extends FiltroGenerico<T>> {
	
	void save(T departamento) throws NegocioException;

	void update(T departamento) throws NegocioException;

	void delete(ID id) throws NegocioException ;

	T findById(ID id);

	List<T> findAll();
	
	public List<T> consultar(F filtro);
}