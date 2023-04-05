package br.gov.pe.sismepe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Uf;
import br.gov.pe.sismepe.repositories.UfRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class UfService {
	@Autowired
	UfRepository repo;
	
	public List<Uf> findAll() {
		return repo.findAll();
	}
	
	public Uf findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("UF não encontrado: " + id));
	}
	
	public Uf findBySigla(String uf) {
		return repo.findBySigla(uf);
	}
}
