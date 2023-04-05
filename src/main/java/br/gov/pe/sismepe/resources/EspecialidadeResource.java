package br.gov.pe.sismepe.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.Especialidade;
import br.gov.pe.sismepe.services.EspecialidadeService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/especialidade")
public class EspecialidadeResource {
	
	@Autowired
	private EspecialidadeService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		Especialidade obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
