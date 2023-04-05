package br.gov.pe.sismepe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.GrupoMenuDTO;
import br.gov.pe.sismepe.repositories.GrupoMenuRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;

@Service
public class GrupoMenuService {
	
	@Autowired
	private GrupoMenuRepository repo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	public List<GrupoMenuDTO> list(UserSS user){
		Usuario u = usuarioRepo.findByLogin(user.getLogin());
		return repo.list(u);
	}
}
