package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.Habitacao;

public class HabitacaoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Integer usuarioCadastroId;

	private Long pacienteId;

	private Integer numeroSalas;
	
	private Integer numeroCozinhas;
	
	private Integer numeroQuartos;
	
	private Integer numeroBanheiros;
	
	private double valor;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCadastro;
	
	private String situacao;
	
	public HabitacaoDTO() {
	}

	public HabitacaoDTO(Habitacao obj) {
		super();
		this.id = obj.getId();
		this.usuarioCadastroId = obj.getUsuarioCadastro().getId();
		this.pacienteId = obj.getPaciente().getId();
		this.numeroSalas = obj.getNumeroSalas();
		this.numeroCozinhas = obj.getNumeroCozinhas();
		this.numeroQuartos = obj.getNumeroQuartos();
		this.numeroBanheiros = obj.getNumeroBanheiros();
		this.valor = obj.getValor();
		this.dataCadastro = obj.getDataCadastro();
		this.situacao = obj.getSituacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUsuarioCadastroId() {
		return usuarioCadastroId;
	}

	public void setUsuarioCadastroId(Integer usuarioCadastroId) {
		this.usuarioCadastroId = usuarioCadastroId;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Integer getNumeroSalas() {
		return numeroSalas;
	}

	public void setNumeroSalas(Integer numeroSalas) {
		this.numeroSalas = numeroSalas;
	}

	public Integer getNumeroCozinhas() {
		return numeroCozinhas;
	}

	public void setNumeroCozinhas(Integer numeroCozinhas) {
		this.numeroCozinhas = numeroCozinhas;
	}

	public Integer getNumeroQuartos() {
		return numeroQuartos;
	}

	public void setNumeroQuartos(Integer numeroQuartos) {
		this.numeroQuartos = numeroQuartos;
	}

	public Integer getNumeroBanheiros() {
		return numeroBanheiros;
	}

	public void setNumeroBanheiros(Integer numeroBanheiros) {
		this.numeroBanheiros = numeroBanheiros;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}
