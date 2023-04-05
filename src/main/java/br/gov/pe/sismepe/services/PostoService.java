package br.gov.pe.sismepe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Posto;
import br.gov.pe.sismepe.repositories.PostoRepository;
import br.gov.pe.sismepe.services.exceptions.DataIntegrityException;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class PostoService {
	
	@Autowired
	private PostoRepository repo;
	
	public Posto find(Long id) {
		Optional<Posto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! ID: " + id + "Tipo: " + Posto.class.getName())); 
	}
	
	public Posto insert(Posto posto) {
		posto.setId(null);
		return repo.save(posto);
	}
	
	public Posto update(Posto posto) {
		Posto newObj = find(posto.getId());
		updateData(newObj, posto);
		return repo.save(newObj);
	}
	
	private void updateData(Posto newObj, Posto posto) {
		newObj.setNome(posto.getNome());
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma posto que possui produtos.");
		}
	}
	
	public List<Posto> findAll() {
		return repo.findAll();
	}
	
	public Page<Posto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
}