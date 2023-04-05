package br.gov.pe.sismepe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Perfil;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.repositories.PerfilRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;

@Service
public class PerfilService {

	@Autowired(required=true)
	private PerfilRepository repo;
	
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public List<Perfil> listByUser(UserSS user){
		Usuario u = usuarioRepo.findByLogin(user.getLogin());
		return repo.listByUser(u.getId());
	}
	

	public List<Perfil> list(){
		return repo.findAll();
	}
}
