package br.gov.pe.sismepe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.CepLogradouro;
import br.gov.pe.sismepe.repositories.CepLogradouroRepository;

@Service
public class CepLogradouroService  {

	@Autowired
	private CepLogradouroRepository cepRepo;
	
	public List<CepLogradouro> buscarCep(Long cep){
		List<CepLogradouro> log = cepRepo.findBycepLog(cep);
		
		return log;
	}
	
}
