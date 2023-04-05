package br.gov.pe.sismepe.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.Uf;
import br.gov.pe.sismepe.services.UfService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/uf")
public class UfResource {
	@Autowired
	UfService service;
	
	@GetMapping
	public ResponseEntity<List<Uf>> findAll() {
		List<Uf> ufs = service.findAll();
		return ResponseEntity.ok(ufs);
	}
	
	@GetMapping(value = "/{uf}")
	public ResponseEntity<Uf> findBySigla(@PathVariable String uf) {
		return ResponseEntity.ok(service.findBySigla(uf));
	}
}
