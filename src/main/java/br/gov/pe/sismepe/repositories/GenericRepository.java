package br.gov.pe.sismepe.repositories;

import java.util.List;

public interface GenericRepository <T, ID> {
	
	public void save(T entity);
    
    public void update(T entity);
    
    public void delete(ID id);
    
    public T findById(ID id);
    
    public List<T> findAll();
}