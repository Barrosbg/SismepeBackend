package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.ExameTransfusional;
import br.gov.pe.sismepe.domain.Perfil;
import br.gov.pe.sismepe.domain.PerfilUsuario;
import br.gov.pe.sismepe.domain.PerfilUsuarioId;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.repositories.PerfilUsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.PerfilService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/perfil")
public class PerfilResource {

	@Autowired
	private PerfilService service;
	
	@Autowired
	private PerfilUsuarioRepository repoPerfilUser;

	@GetMapping
	ResponseEntity<List<Perfil>> list() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Perfil> perfis = service.listByUser(((UserSS) principal));
		return ResponseEntity.ok().body(perfis);
	}
	
	@GetMapping(value = "/todos")
	public ResponseEntity<List<Perfil>> findAllPerfis(){
		    List<Perfil> perfil = service.list();
		    return ResponseEntity.ok().body(perfil);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<Object>> buscar(@PathVariable Integer id){
		    List<Object> lista = new ArrayList<Object>();
           	List<PerfilUsuario> user =  repoPerfilUser.findByUsuario_id(id);
           	user.stream().forEach(usuario -> lista.add(usuario.getId()));
           	return ResponseEntity.ok().body(lista);
	}
	
	@PostMapping(value = "/adicionar")
	public ResponseEntity<PerfilUsuario> savePerfil (@Valid @RequestBody PerfilUsuario perfil){
		System.out.println(perfil);
		repoPerfilUser.savePerfilUSer(perfil.getPerfil().getId(), perfil.getUsuario().getId());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(perfil.getPerfil().getId(), perfil.getUsuario().getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
