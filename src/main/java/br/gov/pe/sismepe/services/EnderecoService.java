package br.gov.pe.sismepe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Endereco;
import br.gov.pe.sismepe.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository repo;
	
	public Endereco alterar(Endereco endereco) {
		return repo.save(endereco);
	}
}
