package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.gov.pe.sismepe.domain.PacienteResponsavel;
import br.gov.pe.sismepe.dto.PacienteResponsavelDTO;
import br.gov.pe.sismepe.services.PacienteResponsavelService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/paciente-responsavel")
public class PacienteResponsavelResource {

	@Autowired
	private PacienteResponsavelService service;
	
	/**
	 * Pesquisar um paciente pelo Id
	 */
	@GetMapping(value = "/{pacienteId}/{pessoaId}")
	public ResponseEntity<?> find(@PathVariable Long pacienteId, @PathVariable Long pessoaId){
		PacienteResponsavel obj = service.find(pacienteId, pessoaId);
		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Pesquisar todos os responsáveis pelos pacientes
	 */
	@GetMapping
	public ResponseEntity<List<PacienteResponsavelDTO>> findAll(){
		List<PacienteResponsavel> list = service.findAll();
		List<PacienteResponsavelDTO> listDTO = list.stream().map(obj -> new PacienteResponsavelDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/paciente")
	public ResponseEntity<List<PacienteResponsavelDTO>> findAll(
			@RequestParam(value = "id", defaultValue = "") Long pacienteId){
		List<PacienteResponsavel> list = service.findByPaciente(pacienteId);
		List<PacienteResponsavelDTO> listDTO = list.stream().map(obj -> new PacienteResponsavelDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PutMapping(value = "/{pacienteId}/{pessoaId}")
	public ResponseEntity<Void> update(@RequestBody PacienteResponsavelDTO entity, @PathVariable Long pacienteId, @PathVariable Long pessoaId) {
		entity.setPacienteId(pacienteId);
		entity.setResponsavelId(pessoaId);
		service.update(entity);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody PacienteResponsavelDTO objDTO){
		PacienteResponsavel obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
