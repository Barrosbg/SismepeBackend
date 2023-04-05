package br.gov.pe.sismepe.resources;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.repositories.CepBairroRepository;

@RestController
@RequestMapping(value="/bairro")
public class CepBairroResource {

	@Autowired
	private CepBairroRepository bairroRepo;
	
	
	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		return ResponseEntity.ok(bairroRepo.findAll());
	}
	
	
}
