package br.gov.pe.sismepe.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.gov.pe.sismepe.services.exceptions.AuthorizationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String nome, String login) {
		return Jwts.builder().claim("nome", nome).setSubject(login)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Claims claims = getClaims(token);
			if (claims != null) {
				String username = claims.getSubject();
				Date expirationDate = claims.getExpiration();
				Date now = new Date(System.currentTimeMillis());
				if (username != null && expirationDate != null && now.before(expirationDate)) {
					return true;
				}
			}
		} catch(ExpiredJwtException e) {
			throw new AuthorizationException("Token expirado");
			
		}
		return false;
	}

	private Claims getClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	public String getUsername(String token) {
		String retorno = null;
		Claims claims = getClaims(token);
		if (claims != null) {
			retorno = claims.getSubject();
		}
		return retorno;
	}
}