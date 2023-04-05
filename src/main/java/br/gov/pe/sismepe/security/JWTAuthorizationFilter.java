package br.gov.pe.sismepe.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if(header != null ) {
			
			if(header.startsWith("Bearer ")) {
				header = header.substring(7);
			}
			
			UsernamePasswordAuthenticationToken auth = getAuthentication2(header);
			if(auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
	}

	@SuppressWarnings("unused")
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if(jwtUtil.isTokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
		}
		return null;
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication2(String token) {
		if(jwtUtil.isTokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserSS user = (UserSS)userDetailsService.loadUserByUsername(username);
			user.setSenha("");
			//UserSS u = new UserSS();
			//u.setLogin(username);
			//UserDetails user = u;
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
		}
		return null;
	}
	
}