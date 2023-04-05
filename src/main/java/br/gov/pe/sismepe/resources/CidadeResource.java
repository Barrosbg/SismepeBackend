package br.gov.pe.sismepe.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.Cidade;
import br.gov.pe.sismepe.services.CidadeService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/cidade")
public class CidadeResource {
	@Autowired
	CidadeService service;
	
	@GetMapping("/filtro")
	public ResponseEntity<Page<Cidade>> filtro(
			@RequestParam(required = false) String uf,
			@RequestParam(required = false) Long ufId,
			@RequestParam(required = false) String cidade,
			@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "853") Integer linesPerPage,
            @RequestParam(defaultValue = "cidade") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction) {
		Page<Cidade> cidades = service.filtro(uf, ufId, page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.ok(cidades);
	}
}
