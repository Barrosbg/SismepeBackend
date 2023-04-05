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
import br.gov.pe.sismepe.filtro.FiltroMotivoAlta;
import br.gov.pe.sismepe.services.MotivoAltaService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value="/motivo-alta")
public class MotivoAltaResource {
	
	@Autowired
	private MotivoAltaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<MotivoAlta> find(@PathVariable Integer id) {
		MotivoAlta obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<MotivoAlta>> findAll() {
		List<MotivoAlta> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/filtro", method=RequestMethod.POST)
	public ResponseEntity<List<MotivoAlta>> consultarPorFiltroPost(@RequestBody FiltroMotivoAlta filtro) {
		List<MotivoAlta> lista = null;// service.consultarPorFiltro(filtro);
		return ResponseEntity.ok().body(lista);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody MotivoAlta entity) {
		service.save(entity);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody MotivoAlta entity, @PathVariable Integer id) {
		service.update(entity);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<MotivoAlta> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}