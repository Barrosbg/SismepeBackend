package br.gov.pe.sismepe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.OperativaPessoaTesteCovid;
import br.gov.pe.sismepe.filtro.FiltroOperativaPessoaTesteCovid;
import br.gov.pe.sismepe.repositories.OperativaPessoaTesteCovidRepository;
import br.gov.pe.sismepe.services.exceptions.DataIntegrityException;

@Service
public class OperativaPessoaTesteCovidService {
	
	@Autowired
	private OperativaPessoaTesteCovidRepository repo;
	
	public  OperativaPessoaTesteCovid find(Integer id) {
		return repo.findById(id);
	}
	
	public void insert(OperativaPessoaTesteCovid entity) {
		entity.setId(null);
		repo.save(entity);
	}
	
	public void update( OperativaPessoaTesteCovid entity) {
		repo.update(entity);
	}
	
	public void delete(Integer id) {
		try {
			repo.delete(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma entidade vinculada.");
		}
	}
	
	public List< OperativaPessoaTesteCovid> findAll() {
		return repo.findAll();
	}
	
	public List< OperativaPessoaTesteCovid> listarTodos() {
		return repo.findAll();
	}
	
	public List<OperativaPessoaTesteCovid> consultarPorFiltro(FiltroOperativaPessoaTesteCovid filtro) {
		return repo.consultarPorFiltro(filtro);
	}
}