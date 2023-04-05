package br.gov.pe.sismepe.repositories;

import java.util.List;

import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.GrupoMenuDTO;
import br.gov.pe.sismepe.dto.MenuDTO;

public interface GrupoMenuRepository {
	
	public List<GrupoMenuDTO> list(Usuario usuario);
	
	
	public MenuDTO childrens(MenuDTO menu, Usuario usuario);

}
