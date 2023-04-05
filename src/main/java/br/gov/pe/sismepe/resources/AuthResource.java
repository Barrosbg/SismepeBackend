package br.gov.pe.sismepe.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.security.JWTUtil;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getNome(), user.getUsername());
		response.setHeader("Authorization", "Bearer " + token);
		response.setHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/validateToken", method = RequestMethod.POST)
	public ResponseEntity<Boolean> validate(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		boolean retorno = false;
		if(user != null) {
			retorno = true;
		}
		return ResponseEntity.ok().body(retorno);
	}
}