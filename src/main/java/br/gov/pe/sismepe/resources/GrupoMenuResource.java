package br.gov.pe.sismepe.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.dto.GrupoMenuDTO;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.GrupoMenuService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/grupo-menu")
public class GrupoMenuResource {
	
	@Autowired
	private GrupoMenuService service;

	@GetMapping
	ResponseEntity<List<GrupoMenuDTO>> list() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<GrupoMenuDTO> list = service.list(((UserSS) principal));
		return ResponseEntity.ok().body(list);
	}

}
