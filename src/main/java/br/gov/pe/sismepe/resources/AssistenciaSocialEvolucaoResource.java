package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import br.gov.pe.sismepe.domain.AssistenciaSocialEvolucao;
import br.gov.pe.sismepe.dto.AssistenciaSocialEvolucaoDTO;
import br.gov.pe.sismepe.services.AssistenciaSocialEvolucaoService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/assistencia-social-evolucao")
public class AssistenciaSocialEvolucaoResource {
	
	@Autowired
	private AssistenciaSocialEvolucaoService service; 

	@GetMapping
	public ResponseEntity<List<AssistenciaSocialEvolucaoDTO>> list() {
		List<AssistenciaSocialEvolucao> list = service.all();
		List<AssistenciaSocialEvolucaoDTO> listDTO = list.stream().map(obj -> new AssistenciaSocialEvolucaoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AssistenciaSocialEvolucao> find(@PathVariable Long id){
		AssistenciaSocialEvolucao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/ass")
	public ResponseEntity<Page<AssistenciaSocialEvolucaoDTO>> listByAssistenciaSocial(
			@RequestParam(value = "assId") Long assistenciaSocialId,
			@RequestParam(value = "ativo", defaultValue = "S") String ativo,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String directions) {
		Page<AssistenciaSocialEvolucaoDTO> list = service.allByAssistenciaSocial(assistenciaSocialId, ativo, page, linesPerPage, orderBy, directions);
		return ResponseEntity.ok().body(list);		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody AssistenciaSocialEvolucaoDTO entity, @PathVariable Long id) {
		entity.setId(id);
		service.update(entity);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<AssistenciaSocialEvolucao> save(@RequestBody AssistenciaSocialEvolucaoDTO assistenciaSocialEvolucaoDTO){
		AssistenciaSocialEvolucao obj = service.fromDTO(assistenciaSocialEvolucaoDTO);
		obj.setId(null);		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
}
