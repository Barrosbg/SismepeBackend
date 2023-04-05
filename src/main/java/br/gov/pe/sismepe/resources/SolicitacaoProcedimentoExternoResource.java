package br.gov.pe.sismepe.resources;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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

import br.gov.pe.sismepe.domain.SolicitacaoProcedimentoExterno;
import br.gov.pe.sismepe.dto.SolicitacaoProcedimentoExternoAprovacaoDTO;
import br.gov.pe.sismepe.services.SolicitacaoProcedimentoExternoService;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/solicitacao-procedimento-externo")
public class SolicitacaoProcedimentoExternoResource {
	
	@Autowired
	private SolicitacaoProcedimentoExternoService solicitacaoProcedimentoExternoService;
		
	
	@PreAuthorize("hasAuthority('GESTOR_EXAME_INTERN') or hasAuthority('OPERA_EXAME_INTERN')")
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSolicitacao(@RequestBody SolicitacaoProcedimentoExterno solicitacao, @PathVariable Long id){
		solicitacaoProcedimentoExternoService.updateSolicitacao(id, solicitacao);
	}
		
	@PreAuthorize("hasAuthority('SOLICITA_EXAME_INTERN')")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Void> save(@RequestBody @Valid SolicitacaoProcedimentoExterno solicitacao){
		SolicitacaoProcedimentoExterno sol = solicitacaoProcedimentoExternoService.savarSolicitacao(solicitacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sol.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAuthority('GESTOR_EXAME_INTERN') or hasAuthority('OPERA_EXAME_INTERN')")
	@PostMapping(value = "/autorizacao")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Void> save(@RequestBody @Valid SolicitacaoProcedimentoExternoAprovacaoDTO solicitacao){
		SolicitacaoProcedimentoExterno sol = solicitacaoProcedimentoExternoService.savarSolicitacaoAprovacao(solicitacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sol.getId()).toUri();
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("access-control-expose-headers", "Location");
		return ResponseEntity.created(uri)
				.headers(responseHeaders)
				.build();
	}
	
	@PreAuthorize("hasAuthority('GESTOR_EXAME_INTERN') or hasAuthority('OPERA_EXAME_INTERN')")
	@GetMapping
	public ResponseEntity<Page<SolicitacaoProcedimentoExterno>> find(
			@RequestParam(value = "prestador", required = false) Long prestadorId,
			@RequestParam(value = "paciente", required = false) Long pacienteId,
			@RequestParam(value = "dataCadastro", required = false) String dataCadastro, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String directions
			){
		
		Page<SolicitacaoProcedimentoExterno> list = solicitacaoProcedimentoExternoService.find(prestadorId, pacienteId, dataCadastro, page, linesPerPage, orderBy, directions);
		return ResponseEntity.ok(list);
	}
	
	@PreAuthorize("hasAuthority('SOLICITA_EXAME_INTERN')")
	@GetMapping(value = "/prestador")
	public ResponseEntity<Page<SolicitacaoProcedimentoExterno>> findByPrestador(
			@RequestParam(value = "paciente", required = false) Long pacienteId,
			@RequestParam(value = "dataCadastro", required = false) String dataCadastro, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String directions
			){
		
		Page<SolicitacaoProcedimentoExterno> list = solicitacaoProcedimentoExternoService.findByPrestador(pacienteId, dataCadastro, page, linesPerPage, orderBy, directions);
		return ResponseEntity.ok(list);
	}
	
	@PreAuthorize("hasAuthority('SOLICITA_EXAME_INTERN')")
	@GetMapping(value = "/prestador/{id}")
	public ResponseEntity<SolicitacaoProcedimentoExterno> findByIdAndPrestador(@PathVariable Long id) throws ObjectNotFoundException{
		SolicitacaoProcedimentoExterno sol = solicitacaoProcedimentoExternoService.findByIdAndPrestador(id);
		return ResponseEntity.ok(sol);
	}
	
	@PreAuthorize("hasAuthority('GESTOR_EXAME_INTERN') or hasAuthority('OPERA_EXAME_INTERN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<SolicitacaoProcedimentoExterno> findById(@PathVariable Long id) throws ObjectNotFoundException{
		SolicitacaoProcedimentoExterno sol = solicitacaoProcedimentoExternoService.findById(id);
		return ResponseEntity.ok(sol);
	}
	
	@GetMapping(value = "/{id}/pdf")
	@PreAuthorize("hasAuthority('GESTOR_EXAME_INTERN') or hasAuthority('SOLICITA_EXAME_INTERN') or hasAuthority('OPERA_EXAME_INTERN')")
    public void guiaPorIdPdf(@PathVariable Long id, HttpServletResponse response) throws IOException, Exception {
        solicitacaoProcedimentoExternoService.gerarPdfGuia(id, response);        
    }
	
	
	
}
