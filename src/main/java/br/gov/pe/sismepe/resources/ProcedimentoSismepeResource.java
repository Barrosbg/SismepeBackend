package br.gov.pe.sismepe.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.ProcedimentoSismepe;
import br.gov.pe.sismepe.services.ProcedimentoSismepeService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/procedimento-sismepe")
public class ProcedimentoSismepeResource {
	
	@Autowired
	private ProcedimentoSismepeService procedimentoSismepeService;
	
	@GetMapping
	public ResponseEntity<Page<ProcedimentoSismepe>> find(
			@RequestParam(value = "descricao", defaultValue = "") String descricao,
			@RequestParam(value = "especificacao", defaultValue = "") String especificacao,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "descricao") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String directions
			){
		
		Page<ProcedimentoSismepe> list = procedimentoSismepeService.find(descricao, especificacao, page, linesPerPage, orderBy, directions);
		return ResponseEntity.ok(list);
	}

}
