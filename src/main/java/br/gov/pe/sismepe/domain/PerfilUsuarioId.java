package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class PerfilUsuarioId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="cd_perfil")
	private Integer cd_perfil;
	
	@Column(name="cd_usuario")
	private Integer cd_usuario;
	
	
	public PerfilUsuarioId() {
		super();
	}


	public PerfilUsuarioId(Integer cd_perfil, Integer cd_usuario) {
		super();
		this.cd_perfil = cd_perfil;
		this.cd_usuario = cd_usuario;
	}


	public Integer getCd_perfil() {
		return cd_perfil;
	}




	public void setCd_perfil(Integer cd_perfil) {
		this.cd_perfil = cd_perfil;
	}




	public Integer getCd_usuario() {
		return cd_usuario;
	}




	public void setCd_usuario(Integer cd_usuario) {
		this.cd_usuario = cd_usuario;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cd_perfil == null) ? 0 : cd_perfil.hashCode());
		result = prime * result + ((cd_usuario == null) ? 0 : cd_usuario.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilUsuarioId other = (PerfilUsuarioId) obj;
		if (cd_perfil == null) {
			if (other.cd_perfil != null)
				return false;
		} else if (!cd_perfil.equals(other.cd_perfil))
			return false;
		if (cd_usuario == null) {
			if (other.cd_usuario != null)
				return false;
		} else if (!cd_usuario.equals(other.cd_usuario))
			return false;
		return true;
	}






	
}
