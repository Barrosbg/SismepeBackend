package br.gov.pe.sismepe.resources;

import java.util.Date;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.gov.pe.sismepe.domain.Perfil;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.UsuarioAcessoDTO;
import br.gov.pe.sismepe.dto.UsuarioDTO;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.services.UsuarioService;
import br.gov.pe.sismepe.util.Utils;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value="/usuario")
public class UsuarioResource {
	
	@Autowired(required=true)
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository repo;

	@GetMapping
	public ResponseEntity<UsuarioDTO> findUsuarioLogado() {
		UsuarioDTO usuario = service.getUsuarioDTOLogado();
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping(value = "/data-atual")
	public ResponseEntity<String> dataAtual() {
		return ResponseEntity.ok(Utils.dateToString(new Date()));
	}
	
	@PostMapping(value = "/alterar-acesso")
	public ResponseEntity<String> alterarSenha(@Valid @RequestBody UsuarioAcessoDTO usuario, 
			@RequestParam(value = "tipo") String tipo) {
		service.alterarAcessoUsuario(usuario, tipo);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/recuperar-senha")
	public ResponseEntity<?> recuperarSenha(@Valid @RequestBody UsuarioAcessoDTO usuario) throws MessagingException {
		service.recuperarSenha(usuario);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/validar-token/{token}")
	@ResponseStatus(HttpStatus.OK)
	void validarTokenResetSenha(@PathVariable String token) {
		service.validarToken(token);
	}
	
	
	@GetMapping(value = "/Buscar-por-id/{id}")
	public ResponseEntity<Optional<Usuario>> buscarUsuarioPorId(@PathVariable Integer id) {
		Optional<Usuario> usuario = repo.findById(id);
		return ResponseEntity.ok(usuario);
	}
	

	@PostMapping(value = "/resetar-senha")
	public ResponseEntity<?> resetarSenha(@RequestBody UsuarioAcessoDTO usuario) {
		service.resetarSenha(usuario);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_CADASTRO_JUNTA')")
	@PutMapping(value = "/{usuarioId}/remover-perfil")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerPerfil(@PathVariable Integer usuarioId, @RequestBody Perfil perfil){
		service.removerPerfilUsuario(usuarioId, perfil);
	}
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_CADASTRO_JUNTA')")
	@PutMapping(value = "/{usuarioId}/adicionar-perfil")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void adicionarPerfil(@PathVariable Integer usuarioId, @RequestBody Perfil perfil){
		service.adicionarPerfilUsuario(usuarioId, perfil);
	}

}
