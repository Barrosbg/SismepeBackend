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

import br.gov.pe.sismepe.domain.Atendimento;
import br.gov.pe.sismepe.dto.AtendimentoDTO;
import br.gov.pe.sismepe.filtro.FiltroAtendimento;
import br.gov.pe.sismepe.services.AtendimentoService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value="/atendimento")
public class AtendimentoResource {
	
	@Autowired(required=true)
	private AtendimentoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Atendimento> find(@PathVariable Long id) {
		Atendimento obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Atendimento>> findAll() {
		List<Atendimento> lista = service.listarTodos();
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/filtro", method=RequestMethod.POST)
	public ResponseEntity<List<Atendimento>> consultarPorFiltroPost(@RequestBody FiltroAtendimento filtro) {
		List<Atendimento> lista = null;// service.consultarPorFiltro(filtro);
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Atendimento entity) {
		service.insert(entity);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody AtendimentoDTO objDTO, @PathVariable Long id) {
		Atendimento obj = service.fromDTO(objDTO);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Atendimento> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}