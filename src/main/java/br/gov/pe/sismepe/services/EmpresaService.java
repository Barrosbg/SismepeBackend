package br.gov.pe.sismepe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Empresa;
import br.gov.pe.sismepe.repositories.EmpresaRepository;

@Service
public class EmpresaService {

	
	@Autowired
	private EmpresaRepository empresaRepository;

	public Page<Empresa> find(String nome, String cnpj, Integer page, Integer linesPerPage, String orderBy,
			String directions) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		
		if(cnpj != null) {			
			return empresaRepository.findByCnpjContainingAndAtivo(cnpj, "S", pageRequest);
		} else {	
			return empresaRepository.findByRazaoSocialContainingAndAtivo(nome, "S", pageRequest);
		}
		
	}
	
	
}
