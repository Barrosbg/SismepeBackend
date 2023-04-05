package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.AssistenciaSocial;
import br.gov.pe.sismepe.dto.AssistenciaSocialDTO;
import br.gov.pe.sismepe.services.AssistenciaSocialService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/assistencia-social")
public class AssistenciaSocialResource {
	
	@Autowired
	private AssistenciaSocialService service; 

	@GetMapping
	public ResponseEntity<List<AssistenciaSocial>> list() {
		List<AssistenciaSocial> list = service.all();		
		return ResponseEntity.ok().body(list);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AssistenciaSocial> find(@PathVariable Long id){
		AssistenciaSocial obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/paciente")
	public ResponseEntity<Page<AssistenciaSocialDTO>> listByPaciente(
			@RequestParam(value = "id") Long pacienteId,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String directions) {
		Page<AssistenciaSocialDTO> list = service.allByPaciente(pacienteId, page, linesPerPage, orderBy, directions);
		return ResponseEntity.ok().body(list);		
	}
	
	@PostMapping
	public ResponseEntity<AssistenciaSocial> save(@RequestBody AssistenciaSocialDTO assistenciaSocialDTO){
		AssistenciaSocial obj = service.fromDTO(assistenciaSocialDTO);
		obj.setId(null);		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@Valid @RequestBody AssistenciaSocialDTO objDTO, @PathVariable Long id) {
		AssistenciaSocial obj = service.fromDTO(objDTO);
		obj.setId(id);
		service.update(obj,objDTO);
		return ResponseEntity.noContent().build();
	}
	

}
