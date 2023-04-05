package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.pe.sismepe.domain.enums.TipoMotivoAltaEnum;

@Entity
@Table(name = "MOTIVO_ALTA")
public class MotivoAlta implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_MOTIVO_ALTA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "DS_MOTIVO_ALTA")
	private String descricao;
	
	@Column(name = "TP_MOTIVO_ALTA")
	private String tipo;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	public MotivoAlta() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public void setTipo(TipoMotivoAltaEnum tipo) {
		this.tipo = tipo.getSigla();
	}
	
	public TipoMotivoAltaEnum getTipo() {
		return TipoMotivoAltaEnum.toEnum(tipo);
	}
}