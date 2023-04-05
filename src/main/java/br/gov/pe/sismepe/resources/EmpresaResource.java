package br.gov.pe.sismepe.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.Empresa;
import br.gov.pe.sismepe.services.EmpresaService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/empresa")
public class EmpresaResource {
	
	@Autowired
	private EmpresaService empresaService;
		
	@GetMapping
	public ResponseEntity<Page<Empresa>> find(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "cnpj", required = false) String cnpj,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String directions
			){
		
		Page<Empresa> list = empresaService.find(nome, cnpj, page, linesPerPage, orderBy, directions);
		return ResponseEntity.ok(list);
	}
	

}
