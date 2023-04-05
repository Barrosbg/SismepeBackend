package br.gov.pe.sismepe.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.dto.DataTablesFilter;
import br.gov.pe.sismepe.filtro.FiltroGenerico;
import br.gov.pe.sismepe.repositories.AbstractDAO;
import br.gov.pe.sismepe.services.exceptions.NegocioException;

@Service
@Transactional (readOnly = false)
public abstract class AbstractService<T, ID extends Serializable, F extends FiltroGenerico<T>> implements GenericService<T, ID, F> {
	
	@Autowired
	private AbstractDAO<T, ID, F> dao;
	
	protected abstract void validarUnicidadeEntidade(T entity) throws NegocioException;
	protected abstract void validarInclusaoEntidade(T entity) throws NegocioException;
	protected abstract void validarAlteracaoEntidade(T entity) throws NegocioException;
	protected abstract void validarExclusaoEntidade(ID id) throws NegocioException;
	
	@Transactional
	public void save(T entity) throws NegocioException {
		validarInclusao(entity);
		dao.save(entity);
	}
	
	private void validarInclusao(T entity) throws NegocioException {
		validarInclusaoEntidade(entity);
		validarUnicidadeEntidade(entity);
	}
	
	private void validarAlteracao(T entity) throws NegocioException {
		validarAlteracaoEntidade(entity);
		validarUnicidadeEntidade(entity);
	}
	
	private void validarExclusao(ID id) throws NegocioException {
		validarExclusaoEntidade(id);
	}
	
	@Transactional
	public void update(T entity) throws NegocioException {
		validarAlteracao(entity);
		dao.update(entity);
	}

	@Transactional
	public void delete(ID id) throws NegocioException {
		validarExclusao(id);
		dao.delete(id);
	}

	@Transactional(readOnly = true)
	public T findById(ID id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		return dao.findAll();
	}
	
	public List<T> consultar(F filtro) {
		return dao.consultar(filtro);
	}
	
	public Page<T> consultar(DataTablesFilter dataTablesFilter, F filtro){
		PageRequest pageRequest = PageRequest.of(dataTablesFilter.getPagina(), dataTablesFilter.getLength(), Sort.Direction.ASC, "nome");
		Page<T> resultadoPesquisa = this.dao.consultar(filtro, pageRequest);
		return resultadoPesquisa;
	}
	public AbstractDAO<T, ID, F> getDao() {
		return dao;
	}
}