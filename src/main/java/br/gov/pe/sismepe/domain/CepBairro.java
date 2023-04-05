package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cepbairro")
public class CepBairro  implements Serializable{

	@Id
	@Column(name="CHAVEBAI")
	private Long id;
	
	@Column(name="EXTENSOBAI")
	private String descBairro;
	
	@Column(name="CHVLOCBAI")
	private Long chaveLocalBai;
	
//	@ManyToOne
//    @JoinColumn(name = "CHAVEBAI", updatable = false, insertable = false)
//	private List<CepLogradouro> logradouro;
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = true)
//	@JoinColumn(name ="CHVLOCBAI")
//	private CepLogradouro chaveLocal;
	
	
	public CepBairro() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescBairro() {
		return descBairro;
	}

	public void setDescBairro(String descBairro) {
		this.descBairro = descBairro;
	}

	public Long getChaveLocalBai() {
		return chaveLocalBai;
	}

	public void setChaveLocalBai(Long chaveLocalBai) {
		this.chaveLocalBai = chaveLocalBai;
	}

	
	
//	
//	public List<CepLogradouro>  getBairros() {
//		return logradouro;
//	}
//
//	public void setBairros(List<CepLogradouro> bairros) {
//		this.logradouro = logradouro;
//	}

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
		CepBairro other = (CepBairro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CepBairro [id=" + id + ", descBairro=" + descBairro + ", chaveLocalBai=" + chaveLocalBai + "]";
	}
	
	
	
}
