package br.gov.pe.sismepe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Cidade;
import br.gov.pe.sismepe.domain.Uf;
import br.gov.pe.sismepe.repositories.CidadeRepository;

@Service
public class CidadeService {
	@Autowired
	CidadeRepository repo;
	
	@Autowired
	UfService ufService;
	
	public Page<Cidade> findByUf(String uf, Integer page, Integer linesPerPage, String orderBy, String direction) {
		Uf u = ufService.findBySigla(uf);
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		
		return repo.findByUf(u, pageRequest);
	}
	
	public Page<Cidade> findByUfId(Long id, Integer page, Integer linesPerPage, String orderBy, String direction) {
		Uf u = ufService.findById(id);
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		
		return repo.findByUf(u, pageRequest);
	}
	
	public Page<Cidade> filtro(String uf, Long ufId, Integer page, Integer linesPerPage, String orderBy, String direction) {
		if (uf != null && !uf.equalsIgnoreCase("")) {
			return findByUf(uf, page, linesPerPage, orderBy, direction);
		}
		else if (ufId != null) {
			return findByUfId(ufId, page, linesPerPage, orderBy, direction);
		}
		
		return findByUf("AAA", page, linesPerPage, orderBy, direction);
	}
}
