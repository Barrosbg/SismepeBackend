package br.gov.pe.sismepe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import br.gov.pe.sismepe.domain.Perfil;
import br.gov.pe.sismepe.repositories.PerfilRepository;
import br.gov.pe.sismepe.security.UserSS;

public class UserService {
	
	@Autowired
	private PerfilRepository repo;
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception e) {
			return null;
		}
	}
	
	public List<Perfil> listPerfis(Integer id) {
		return repo.listByUser(id);	
	}

}
