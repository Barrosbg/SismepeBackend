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

import br.gov.pe.sismepe.domain.MotivoAlta;
import br.gov.pe.sismepe.domain.UnidadeInternacao;
import br.gov.pe.sismepe.filtro.FiltroUnidadeInternacao;
import br.gov.pe.sismepe.services.UnidadeInternacaoService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value="/unidade-internacao")
public class UnidadeInternacaoResource {
	
	@Autowired
	private UnidadeInternacaoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UnidadeInternacao> find(@PathVariable Integer id) {
		UnidadeInternacao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UnidadeInternacao>> findAll() {
		List<UnidadeInternacao> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/filtro", method=RequestMethod.POST)
	public ResponseEntity<List<UnidadeInternacao>> consultarPorFiltroPost(@RequestBody FiltroUnidadeInternacao filtro) {
		List<UnidadeInternacao> lista = service.consultarPorFiltro(filtro);
		return ResponseEntity.ok().body(lista);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UnidadeInternacao entity) {
		service.save(entity);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UnidadeInternacao entity, @PathVariable Integer id) {
		service.update(entity);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<MotivoAlta> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}