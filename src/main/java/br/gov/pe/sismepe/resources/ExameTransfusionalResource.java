package br.gov.pe.sismepe.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.ExameTransfusional;
import br.gov.pe.sismepe.dto.ExameTransfusionalDTO;
import br.gov.pe.sismepe.repositories.ExameTransfusionalRepository;
import br.gov.pe.sismepe.services.ExameTransfusionalService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value="/exameTransfusional")
public class ExameTransfusionalResource {

	@Autowired
	private ExameTransfusionalRepository repo;
	
	@Autowired
	private ExameTransfusionalService service;
	
	@GetMapping
	public ResponseEntity<List<ExameTransfusional>> buscarTodos(
//			@RequestParam(value = "page", defaultValue = "0") Integer page, 
//			@RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage, 
//			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
//			@RequestParam(value = "direction", defaultValue = "DESC") String directions
			){
		
//		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
//		Page<ExameTransfusional> exame = repo.findAll(pageRequest);
//		System.out.println(exame);
		return ResponseEntity.ok().body(repo.findByAtivo("S"));
	}
	
	
	
	@PostMapping
	public ResponseEntity<Object> adicionarExame(@Valid @RequestBody ExameTransfusional exame){
		System.out.println(exame);
		ExameTransfusional ex = repo.save(exame);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ex.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/exibirExame/{id}/pdf")
	public void imprimeExames(@PathVariable Long id, HttpServletResponse response) throws IOException, Exception {
		service.imprimirExame(id, response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarExame(@PathVariable Long id){
		List<ExameTransfusional> ex = service.buscarExame(id);
		return ResponseEntity.ok().body(ex);
	}
	
	
	@GetMapping("/exame/{id}")
	public ResponseEntity<ExameTransfusional> buscarExamePorId(@PathVariable Long id){
		ExameTransfusional ex = service.buscarExamePorId(id);
		return ResponseEntity.ok().body(ex);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/atualizar/{id}")
	public void atualizarExames(@PathVariable Long id, @RequestBody ExameTransfusionalDTO exameTransfucional) {
		   service.atualizarExames(id, exameTransfucional); 
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/exluir/{id}")
	public void deletarExame(@PathVariable Long id){
		 service.deletarExame(id);
	}
	
	
}
