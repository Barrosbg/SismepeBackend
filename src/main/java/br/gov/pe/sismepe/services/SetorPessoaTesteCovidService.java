package br.gov.pe.sismepe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.SetorPessoaTesteCovid;
import br.gov.pe.sismepe.filtro.FiltroSetorPessoaTesteCovid;
import br.gov.pe.sismepe.repositories.SetorPessoaTesteCovidRepository;
import br.gov.pe.sismepe.services.exceptions.DataIntegrityException;

@Service
public class SetorPessoaTesteCovidService {
	
	@Autowired
	private SetorPessoaTesteCovidRepository repo;
	
	public  SetorPessoaTesteCovid find(Integer id) {
		return repo.findById(id);
	}
	
	public void insert( SetorPessoaTesteCovid entity) {
		entity.setId(null);
		repo.save(entity);
	}
	
	public void update( SetorPessoaTesteCovid entity) {
		repo.update(entity);
	}
	
	public void delete(Integer id) {
		try {
			repo.delete(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma entidade vinculada.");
		}
	}
	
	public List< SetorPessoaTesteCovid> findAll() {
		return repo.findAll();
	}
	
	public List< SetorPessoaTesteCovid> listarTodos() {
		return repo.findAll();
	}
	
	public List<SetorPessoaTesteCovid> consultarPorFiltro(FiltroSetorPessoaTesteCovid filtro) {
		return repo.consultarPorFiltro(filtro);
	}
}