package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GrupoMenuDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private int order;
	private List<MenuDTO> menus;
	
	public GrupoMenuDTO() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public List<MenuDTO> getMenus() {
		if(menus == null) {
			menus = new ArrayList<MenuDTO>();
		}
		return menus;
	}

	public void setMenus(List<MenuDTO> menus) {
		this.menus = menus;
	}
	
	
	
}
