package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private int grupo;
	private int menuPai;
	private int cdSistema;
	private String title;
	private String path;
	private boolean leaf;
	private List<MenuDTO> children;
	
	
	public MenuDTO() {}

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	
	

	public int getGrupo() {
		return grupo;
	}



	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}


	public int getMenuPai() {
		return menuPai;
	}



	public void setMenuPai(int menuPai) {
		this.menuPai = menuPai;
	}



	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public boolean isLeaf() {
		return leaf;
	}


	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}


	public List<MenuDTO> getChildren() {
		if(children == null) {
			children = new ArrayList<MenuDTO>();
		}
		return children;
	}


	public void setChildren(List<MenuDTO> children) {
		this.children = children;
	}



	public int getCdSistema() {
		return cdSistema;
	}



	public void setCdSistema(int cdSistema) {
		this.cdSistema = cdSistema;
	}	
	
	
	
}
