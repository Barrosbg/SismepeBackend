package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="perfil_usuario" ,schema = "10.3.2.201")
public class PerfilUsuario implements Serializable {

	private static final long serialVersionUID = 1L;



	@EmbeddedId
	private PerfilUsuarioId  id =  new PerfilUsuarioId();
	
 
	@ManyToOne(optional = false)
	@JoinColumn(name = "cd_perfil", nullable = false, insertable = false, updatable = false )
	private Perfil perfil;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cd_usuario", nullable = false, insertable = false, updatable = false)
	private Usuario usuario;
	
	public PerfilUsuario() {
		super();
	}
	
	

	public PerfilUsuario(  Perfil perfil, Usuario usuario) {
		super();
		this.id = new PerfilUsuarioId();
		this.id.setCd_perfil(perfil.getId());
		this.id.setCd_usuario(usuario.getId());
		this.perfil = perfil;
		this.usuario = usuario;
	}



	public PerfilUsuarioId getId() {
		return id;
	}

	public void setId(PerfilUsuarioId id) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PerfilUsuario other = (PerfilUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

    
	@Override
	public String toString() {
		return "PerfilUsuario [id=" + id + ", perfil=" + perfil + ", usuario=" + usuario + "]";
	}
	
}
