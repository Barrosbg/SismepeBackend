package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "escala_central_amb")
public class EscalaCentralAmb implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name="CD_ESCALA_CENTRAL")
	private long id;
	

//	@NotNull
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "CD_ESCALA_CENTRAL", referencedColumnName = "CD_ESCALA_CENTRAL", nullable = true)
//    @JoinTable(name = "escala_central", joinColumns = {@JoinColumn(name = "CD_ESCALA_CENTRAL") })
	

	@ManyToOne(optional=false)
	@JoinColumn(name="CD_ESCALA_CENTRAL",referencedColumnName="CD_ESCALA_CENTRAL", insertable=false, updatable=false)
	private EscalaCentral escala;
	

	@ManyToOne
	@JoinColumn(name = "CD_ESPECIALIDADE")
	private Especialidade especialidade;
	
	@ManyToOne
	@JoinColumn(name = "CD_PRESTADOR")
	private Prestador prestador;
	
	@ManyToOne
	@JoinColumn(name = "CD_SUBESPECIALIDADE")
	private Subespecialidade subespecialidade;
	
	public EscalaCentralAmb() {
	
	}

	public EscalaCentralAmb( @NotNull EscalaCentral escala, Especialidade especialidade, Prestador prestador,
			Subespecialidade subespecialidade) {
		super();
//		this.id = id;
		this.escala = escala;
		this.especialidade = especialidade;
		this.prestador = prestador;
		this.subespecialidade = subespecialidade;
	}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

	public EscalaCentral getEscala() {
		return escala;
	}

	public void setEscala(EscalaCentral escala) {
		this.escala = escala;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public Subespecialidade getSubespecialidade() {
		return subespecialidade;
	}

	public void setSubespecialidade(Subespecialidade subespecialidade) {
		this.subespecialidade = subespecialidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((escala == null) ? 0 : escala.hashCode());
		result = prime * result + ((especialidade == null) ? 0 : especialidade.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((prestador == null) ? 0 : prestador.hashCode());
		result = prime * result + ((subespecialidade == null) ? 0 : subespecialidade.hashCode());
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
		EscalaCentralAmb other = (EscalaCentralAmb) obj;
		if (escala == null) {
			if (other.escala != null)
				return false;
		} else if (!escala.equals(other.escala))
			return false;
		if (especialidade == null) {
			if (other.especialidade != null)
				return false;
		} else if (!especialidade.equals(other.especialidade))
			return false;
		if (id != other.id)
			return false;
		if (prestador == null) {
			if (other.prestador != null)
				return false;
		} else if (!prestador.equals(other.prestador))
			return false;
		if (subespecialidade == null) {
			if (other.subespecialidade != null)
				return false;
		} else if (!subespecialidade.equals(other.subespecialidade))
			return false;
		return true;
	}

	
}
