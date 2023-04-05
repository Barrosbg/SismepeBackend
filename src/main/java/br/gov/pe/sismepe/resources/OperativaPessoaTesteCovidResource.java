package br.gov.pe.sismepe.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.OperativaPessoaTesteCovid;
import br.gov.pe.sismepe.filtro.FiltroOperativaPessoaTesteCovid;
import br.gov.pe.sismepe.services.OperativaPessoaTesteCovidService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value="/operativa-pessoa-teste")
public class OperativaPessoaTesteCovidResource {
	
	@Autowired
	private OperativaPessoaTesteCovidService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<OperativaPessoaTesteCovid> find(@PathVariable Integer id) {
		OperativaPessoaTesteCovid obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<OperativaPessoaTesteCovid>> findAll() {
		List<OperativaPessoaTesteCovid> lista = service.listarTodos();
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/filtro", method=RequestMethod.POST)
	public ResponseEntity<List<OperativaPessoaTesteCovid>> consultarPorFiltroPost(@RequestBody FiltroOperativaPessoaTesteCovid filtro) {
		List<OperativaPessoaTesteCovid> lista = service.consultarPorFiltro(filtro);
		return ResponseEntity.ok().body(lista);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody OperativaPessoaTesteCovid entity) {
		service.insert(entity);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody OperativaPessoaTesteCovid entity, @PathVariable Integer id) {
		service.update(entity);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<OperativaPessoaTesteCovid> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}