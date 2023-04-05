package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import br.gov.pe.sismepe.domain.AutorizacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.ValidadeAutorizacao;
import br.gov.pe.sismepe.dto.AutorizacaoProcedimentoExternoDTO;
import br.gov.pe.sismepe.services.AutorizacaoProcedimentoExternoService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/aut-procedimento-externo")
public class AutorizacaoProcedimentoExternoResource {
	
	@Autowired
	private AutorizacaoProcedimentoExternoService autorizacaoProcedimentoExternoService;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('GESTOR_EXAME_INTERN') or hasAuthority('OPERA_EXAME_INTERN')")
	public ResponseEntity<Page<AutorizacaoProcedimentoExterno>> find(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String directions
			){
		
		Page<AutorizacaoProcedimentoExterno> list = autorizacaoProcedimentoExternoService.find(page, linesPerPage, orderBy, directions);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/solicitacao/{id}")
	@PreAuthorize("hasAuthority('GESTOR_EXAME_INTERN') or hasAuthority('OPERA_EXAME_INTERN')")
	public ResponseEntity<List<AutorizacaoProcedimentoExterno>> findByIdSolicitacao(@PathVariable Long id){
		
		List<AutorizacaoProcedimentoExterno> list = autorizacaoProcedimentoExternoService.findByIdSolicitacao(id);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('GESTOR_EXAME_INTERN') or hasAuthority('OPERA_EXAME_INTERN')")
	public ResponseEntity<Void> save(@RequestBody @Valid AutorizacaoProcedimentoExternoDTO autorizacao){
		AutorizacaoProcedimentoExterno aut = autorizacaoProcedimentoExternoService.save(autorizacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aut.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value = "/{id}/revalidar")
	@PreAuthorize("hasAuthority('GESTOR_EXAME_INTERN') or hasAuthority('OPERA_EXAME_INTERN')")
	public ResponseEntity<Void> revalidar(@PathVariable Long id, @RequestBody ValidadeAutorizacao validadeAutorizacao){
		AutorizacaoProcedimentoExterno aut = autorizacaoProcedimentoExternoService.revalidar(id, validadeAutorizacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aut.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('GESTOR_EXAME_INTERN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody @Valid AutorizacaoProcedimentoExternoDTO autorizacao, @PathVariable Long id){
		autorizacaoProcedimentoExternoService.update(id, autorizacao);
	}
	
}
