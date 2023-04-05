package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="lancamento_equipamento")
public class LancamentoEquipamento implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name="cd_lancamento")
	private Lancamento cd_lancamento;
	
	
	@ManyToOne
	@JoinColumn(name="cd_equipamento")
	private Equipamento cd_equipamento;

	
	

	public LancamentoEquipamento() {
		super();
	}




	public LancamentoEquipamento(Integer id, Lancamento cd_lancamento,
			Equipamento cd_equipamento) {
		super();
		this.id = id;
		this.cd_lancamento = cd_lancamento;
		this.cd_equipamento = cd_equipamento;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public Lancamento getCd_lancamento() {
		return cd_lancamento;
	}




	public void setCd_lancamento(Lancamento cd_lancamento) {
		this.cd_lancamento = cd_lancamento;
	}




	public Equipamento getCd_equipamento() {
		return cd_equipamento;
	}




	public void setCd_equipamento(Equipamento cd_equipamento) {
		this.cd_equipamento = cd_equipamento;
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
		LancamentoEquipamento other = (LancamentoEquipamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
