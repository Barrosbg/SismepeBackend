package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.gov.pe.sismepe.domain.Habitacao;
import br.gov.pe.sismepe.dto.HabitacaoDTO;
import br.gov.pe.sismepe.services.HabitacaoService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping(value = "/habitacao")
public class HabitacaoResource {
	
	
	@Autowired
	private HabitacaoService service; 

	@GetMapping
	public ResponseEntity<List<Habitacao>> list() {
		List<Habitacao> list = service.all();		
		return ResponseEntity.ok().body(list);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Habitacao> find(@PathVariable Long id){
		Habitacao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/paciente")
	public ResponseEntity<Habitacao> findAllByPaciente(
		@RequestParam(value = "pac", defaultValue = "") Long pacienteId){
		Habitacao obj = service.findAllByPaciente(pacienteId);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Habitacao> save(@RequestBody HabitacaoDTO habitacaoDTO){
		Habitacao obj = service.fromDTO(habitacaoDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody HabitacaoDTO objDTO, @PathVariable Long id) {
		Habitacao obj = service.fromDTO(objDTO);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
}
