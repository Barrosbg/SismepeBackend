package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SETOR_PESSOA_TESTE_COVID")
public class SetorPessoaTesteCovid implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cd_setor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ds_setor", nullable = false, length = 300)
	private String descricao;
	
	@Column(name = "sn_ativo", nullable = false, length = 1)
	private String ativo;
	
	public SetorPessoaTesteCovid() {
		
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
}