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

import br.gov.pe.sismepe.domain.SetorPessoaTesteCovid;
import br.gov.pe.sismepe.filtro.FiltroSetorPessoaTesteCovid;
import br.gov.pe.sismepe.services.SetorPessoaTesteCovidService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value="/setor-pessoa-teste")
public class SetorPessoaTesteCovidResource {
	
	@Autowired
	private SetorPessoaTesteCovidService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<SetorPessoaTesteCovid> find(@PathVariable Integer id) {
		SetorPessoaTesteCovid obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SetorPessoaTesteCovid>> findAll() {
		List<SetorPessoaTesteCovid> lista = service.listarTodos();
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/filtro", method=RequestMethod.POST)
	public ResponseEntity<List<SetorPessoaTesteCovid>> consultarPorFiltroPost(@RequestBody FiltroSetorPessoaTesteCovid filtro) {
		List<SetorPessoaTesteCovid> lista = service.consultarPorFiltro(filtro);
		return ResponseEntity.ok().body(lista);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody SetorPessoaTesteCovid entity) {
		service.insert(entity);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody SetorPessoaTesteCovid entity, @PathVariable Integer id) {
		service.update(entity);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<SetorPessoaTesteCovid> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}