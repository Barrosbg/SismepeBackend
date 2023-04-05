package br.gov.pe.sismepe.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.GrupoMenuDTO;
import br.gov.pe.sismepe.dto.MenuDTO;

@Repository
@Transactional(readOnly = true)
public class GrupoMenuRepositoryImpl implements GrupoMenuRepository {

	@Autowired
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<GrupoMenuDTO> list(Usuario usuario) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT gm.CD_GRUPO_MENU, gm.DS_GRUPO_MENU, gm.NR_ORDEM ");
		sb.append("FROM grupo_menu gm ");
		sb.append("WHERE gm.SN_ATIVO = 'S' ");
				
		if(!usuario.getNivelAcesso().equals("A")) {
			sb.append("AND (gm.CD_GRUPO_MENU IN ( ");
			sb.append("SELECT m.CD_GRUPO_MENU FROM menu m ");
			sb.append("WHERE m.CD_FUNCIONALIDADE IN ( ");
			sb.append("SELECT pf.CD_FUNCIONALIDADE ");
			sb.append("FROM PERFIL_FUNCIONALIDADE pf ");
			sb.append("WHERE pf.CD_PERFIL IN ( ");
			sb.append("SELECT pu.CD_PERFIL FROM PERFIL_USUARIO pu ");
			sb.append("WHERE PU.CD_USUARIO = :id )))) ");
		}
		
		sb.append("ORDER BY gm.NR_ORDEM;");
		
		Query query = this.entityManager.createNativeQuery(sb.toString());
		if(!usuario.getNivelAcesso().equals("A"))  query.setParameter("id", usuario.getId());

		final List<Object[]> result = query.getResultList();
		List<GrupoMenuDTO> grupos = new ArrayList<GrupoMenuDTO>();
		
		for (final Object[] obj : result) {
			GrupoMenuDTO grupo = new GrupoMenuDTO();
			grupo.setId(((Integer) obj[0]).intValue());
			grupo.setTitle((String)obj[1]);
			grupo.setOrder(((Integer) obj[2]).intValue());
			
			sb = new StringBuilder();
			sb.append("SELECT CD_MENU, DS_MENU, CD_MENU_PAI, DS_ID_FUNCIONALIDADE, IF(TOT_FILHO > 0,'S','N') SN_TEM_FILHO, CD_SISTEMA ");			  
			sb.append("FROM (SELECT M.CD_MENU, M.CD_SISTEMA, M.DS_MENU, M.CD_MENU_PAI, F.DS_ID_FUNCIONALIDADE, ");			      
			sb.append("(SELECT COUNT(*) FROM MENU M2 ");			      
			sb.append("WHERE CD_MENU_PAI = M.CD_MENU) TOT_FILHO, NR_ORDEM ");									  
			sb.append("FROM MENU M LEFT JOIN FUNCIONALIDADE F ON F.CD_FUNCIONALIDADE = M.CD_FUNCIONALIDADE ");									 
			sb.append("WHERE M.CD_GRUPO_MENU = :idGrupo AND M.CD_MENU_PAI IS NULL AND M.SN_ATIVO = 'S' ");
			
			if(!usuario.getNivelAcesso().equals("A")) {
				sb.append("AND ( M.CD_FUNCIONALIDADE IS NULL ");
				sb.append("OR M.CD_FUNCIONALIDADE IN ");
				sb.append("( SELECT PF.CD_FUNCIONALIDADE FROM PERFIL_FUNCIONALIDADE PF ");
				sb.append("WHERE PF.CD_PERFIL IN (SELECT PU.CD_PERFIL FROM PERFIL_USUARIO PU ");
				sb.append("WHERE PU.CD_USUARIO = :id ))) ");
			}
			
			sb.append(") T_MENU	ORDER BY NR_ORDEM");
						
			Query queryMenu = this.entityManager.createNativeQuery(sb.toString());
			queryMenu.setParameter("idGrupo", grupo.getId());
			if(!usuario.getNivelAcesso().equals("A"))  queryMenu.setParameter("id", usuario.getId());

			final List<Object[]> resultMenu = queryMenu.getResultList();
			for (final Object[] objMenu : resultMenu) {
				MenuDTO menu = new MenuDTO();
				menu.setId(((Integer) objMenu[0]).intValue());
				menu.setTitle((String)objMenu[1]);
				menu.setPath((String)objMenu[3]);
				menu.setLeaf(((String) objMenu[4]).equals("S") ? false : true);
				menu.setCdSistema(((Integer) objMenu[5]).intValue());
				menu.setGrupo(grupo.getId());
				menu.setMenuPai(objMenu[2] != null ? ((Integer) objMenu[2]).intValue() : 0);
				if(!menu.isLeaf()) {
					this.childrens(menu, usuario);
					
				}
				
				if((menu.getChildren().size() > 0) || (menu.getCdSistema() == 2 && menu.isLeaf())) {
					grupo.getMenus().add(menu);
				}
				
			}
			
			grupos.add(grupo);
		}
		
		grupos.removeIf(grupo -> grupo.getMenus().size() == 0);

		return grupos;
	}

	@Override
	public MenuDTO childrens(MenuDTO m, Usuario usuario) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT CD_MENU, DS_MENU, CD_MENU_PAI, DS_ID_FUNCIONALIDADE, IF(TOT_FILHO > 0,'S','N') SN_TEM_FILHO, CD_SISTEMA ");			  
		sb.append("FROM (SELECT M.CD_MENU, M.CD_SISTEMA, M.DS_MENU, M.CD_MENU_PAI, F.DS_ID_FUNCIONALIDADE, ");			      
		sb.append("(SELECT COUNT(*) FROM MENU M2 ");			      
		sb.append("WHERE CD_MENU_PAI = M.CD_MENU) TOT_FILHO, NR_ORDEM ");									  
		sb.append("FROM MENU M LEFT JOIN FUNCIONALIDADE F ON F.CD_FUNCIONALIDADE = M.CD_FUNCIONALIDADE ");									 
		sb.append("WHERE M.CD_GRUPO_MENU = :idGrupo AND M.CD_MENU_PAI = :pai AND M.SN_ATIVO = 'S' ");
		
		if(!usuario.getNivelAcesso().equals("A")) {
			sb.append("AND ( M.CD_FUNCIONALIDADE IS NULL ");
			sb.append("OR M.CD_FUNCIONALIDADE IN ");
			sb.append("( SELECT PF.CD_FUNCIONALIDADE FROM PERFIL_FUNCIONALIDADE PF ");
			sb.append("WHERE PF.CD_PERFIL IN (SELECT PU.CD_PERFIL FROM PERFIL_USUARIO PU ");
			sb.append("WHERE PU.CD_USUARIO = :id ))) ");
		}
		
		sb.append(") T_MENU	ORDER BY NR_ORDEM");
		
		Query queryMenu = this.entityManager.createNativeQuery(sb.toString());
		queryMenu.setParameter("idGrupo", m.getGrupo());
		queryMenu.setParameter("pai", m.getId());
		if(!usuario.getNivelAcesso().equals("A"))  queryMenu.setParameter("id", usuario.getId());

		final List<Object[]> resultMenu = queryMenu.getResultList();
		for (final Object[] objMenu : resultMenu) {
			MenuDTO menu = new MenuDTO();
			menu.setId(((Integer) objMenu[0]).intValue());
			menu.setTitle((String)objMenu[1]);
			menu.setPath((String)objMenu[3]);
			menu.setLeaf(((String) objMenu[4]).equals("S") ? false : true);
			menu.setCdSistema(((Integer) objMenu[5]).intValue());
			menu.setMenuPai(m.getId());
			menu.setGrupo(m.getGrupo());
			if(!menu.isLeaf()) {
				this.childrens(menu, usuario);
			}
			
			if((menu.getChildren().size() > 0) || (menu.getCdSistema() == 2 && menu.isLeaf())) {
				m.getChildren().add(menu);
			}			
			
		}
		return m;
	}
	

}
