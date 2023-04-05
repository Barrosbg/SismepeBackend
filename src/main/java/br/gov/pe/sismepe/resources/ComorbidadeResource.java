package br.gov.pe.sismepe.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.Comorbidade;
import br.gov.pe.sismepe.repositories.ComorbidadeRepository;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/comorbidades")
public class ComorbidadeResource {
	
	@Autowired
	private ComorbidadeRepository comorbidadeRepo;
		
	@GetMapping
	ResponseEntity<List<Comorbidade>> findAll(){
		List<Comorbidade> comorbidades = comorbidadeRepo.findAll();
		return ResponseEntity.ok(comorbidades);
	}
	
	
}
