package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.RegistroProcedimentoInterno;
import br.gov.pe.sismepe.services.RegistroProcedimentoExternoService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/registro-procedimento-interno")
public class RegistroProcedimentoInternoResource {
	
	@Autowired
	private RegistroProcedimentoExternoService registroProcedimentoInternoService;
	

	@GetMapping
	@PreAuthorize("hasAuthority('CADASTRA_EXAME_INTERN')")
	public ResponseEntity<Page<RegistroProcedimentoInterno>> find(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String directions
			){
		
		Page<RegistroProcedimentoInterno> list = registroProcedimentoInternoService.find(page, linesPerPage, orderBy, directions);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/solicitacao/{id}")
	@PreAuthorize("hasAuthority('CADASTRA_EXAME_INTERN')")
	public ResponseEntity<List<RegistroProcedimentoInterno>> findByIdSolicitacao(@PathVariable Long id){
		
		List<RegistroProcedimentoInterno> list = registroProcedimentoInternoService.findByIdSolicitacao(id);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('CADASTRA_EXAME_INTERN')")
	public ResponseEntity<Void> save(@RequestBody @Valid RegistroProcedimentoInterno registro){
		registro = registroProcedimentoInternoService.save(registro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	


}
