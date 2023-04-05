package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.ReferenciaFamiliar;
import br.gov.pe.sismepe.dto.ReferenciaFamiliarDTO;
import br.gov.pe.sismepe.services.ReferenciaFamiliarService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping(value = "/referencia-familiar")
public class ReferenciaFamiliarResource {
	
	@Autowired
	private ReferenciaFamiliarService service; 

	@GetMapping
	public ResponseEntity<List<ReferenciaFamiliar>> list() {
		List<ReferenciaFamiliar> list = service.all();		
		return ResponseEntity.ok().body(list);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReferenciaFamiliar> find(@PathVariable Long id){
		ReferenciaFamiliar obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/paciente")
	public ResponseEntity<Page<ReferenciaFamiliar>> findAllByPaciente(
		@RequestParam(value = "pac", defaultValue = "") Long pacienteId,
		@RequestParam(value = "page", defaultValue = "0") Integer page, 
		@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
		@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
		@RequestParam(value = "direction", defaultValue = "ASC") String directions){
		Page<ReferenciaFamiliar> listDTO = service.findAllByPaciente(pacienteId, page, linesPerPage, orderBy, directions);
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody ReferenciaFamiliarDTO entity, @PathVariable Long id) {
		entity.setId(id);
		service.update(entity);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<ReferenciaFamiliar> save(@RequestBody ReferenciaFamiliarDTO referenciaFamiliarDTO){
		ReferenciaFamiliar obj = service.fromDTO(referenciaFamiliarDTO);
		obj.setId(null);		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
