package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "habitacao")
public class Habitacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CD_HABITACAO")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CD_PACIENTE")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "CD_USUARIO_CADASTRO")
	@JsonIgnoreProperties(value = {"perfis"})
	private Usuario usuarioCadastro;

	@Column(name = "NR_SALAS")
	private Integer numeroSalas;
	
	@Column(name = "NR_COZINHAS")
	private Integer numeroCozinhas;
	
	@Column(name = "NR_QUARTOS")
	private Integer numeroQuartos;
	
	@Column(name = "NR_BANHEIROS")
	private Integer numeroBanheiros;
	
	@Column(name = "VL_HABITACAO")
	private double valor;
	
	@Column(name = "DT_CADASTRO")
	private Date dataCadastro;
	
	@Column(name = "SN_ATIVO")
	private String situacao;
	
	public Habitacao() {}

	public Habitacao(Paciente paciente, Usuario usuarioCadastro, Integer numeroSalas, Integer numeroCozinhas,
			Integer numeroQuartos, Integer numeroBanheiros, double valor, Date dataCadastro, 
			String situacao) {
		super();
		this.paciente = paciente;
		this.usuarioCadastro = usuarioCadastro;
		this.numeroSalas = numeroSalas;
		this.numeroCozinhas = numeroCozinhas;
		this.numeroQuartos = numeroQuartos;
		this.numeroBanheiros = numeroBanheiros;
		this.valor = valor;
		this.dataCadastro = dataCadastro;
		this.situacao = situacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
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
