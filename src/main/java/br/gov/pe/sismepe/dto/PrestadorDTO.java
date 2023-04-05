package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.List;

import br.gov.pe.sismepe.domain.Conselho;
import br.gov.pe.sismepe.domain.Especialidade;
import br.gov.pe.sismepe.domain.Prestador;

public class PrestadorDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private List<Especialidade> especialidades;
	private Conselho conselho;
	private String numeroConselho;
	private String corpoClinico;
	private String ativo;

	public PrestadorDTO() {
	}
	
	public PrestadorDTO(Prestador obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.especialidades = obj.getEspecialidades();
		this.conselho = obj.getConselho();
		this.numeroConselho = obj.getNumeroConselho();
		this.corpoClinico = obj.getCorpoClinico();
		this.ativo = obj.getAtivo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Conselho getConselho() {
		return conselho;
	}

	public void setConselho(Conselho conselho) {
		this.conselho = conselho;
	}

	public String getNumeroConselho() {
		return numeroConselho;
	}

	public void setNumeroConselho(String numeroConselho) {
		this.numeroConselho = numeroConselho;
	}

	public String getCorpoClinico() {
		return corpoClinico;
	}

	public void setCorpoClinico(String corpoClinico) {
		this.corpoClinico = corpoClinico;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
}
