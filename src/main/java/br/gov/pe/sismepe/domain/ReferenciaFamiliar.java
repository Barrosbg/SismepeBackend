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
@Table(name = "referencia_familiar")
public class ReferenciaFamiliar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_REFERENCIA_FAMILIAR")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "CD_PARENTESCO")
	private Parentesco parentesco;

	@ManyToOne
	@JoinColumn(name = "CD_USUARIO_CADASTRO")
	@JsonIgnoreProperties(value = {"perfis"})
	private Usuario usuarioCadastro;

	@ManyToOne
	@JoinColumn(name = "CD_PACIENTE")
	private Paciente paciente;

	@Column(name = "NM_REFERENCIA")
	private String nome;

	@Column(name = "DT_NASCIMENTO")
	private Date dataNascimento;

	@Column(name = "DS_GRAU_INST")
	private String grauInstrucao;

	@Column(name = "DS_OCUPACAO")
	private String ocupacao;

	@Column(name = "VL_RENDA")
	private double renda;

	@Column(name = "DT_CADASTRO")
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dataCadastro;

	@Column(name = "SN_ATIVO")
	private String situacao;

	public ReferenciaFamiliar() {
	}

	public ReferenciaFamiliar(Parentesco parentesco, Usuario usuarioCadastro, Paciente paciente, String nome,
			Date dataNascimento, String grauInstrucao, String ocupacao, double renda, Date dataCadastro, String situacao) {
		super();
		this.parentesco = parentesco;
		this.usuarioCadastro = usuarioCadastro;
		this.paciente = paciente;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.grauInstrucao = grauInstrucao;
		this.ocupacao = ocupacao;
		this.renda = renda;
		this.dataCadastro = dataCadastro;
		this.situacao = situacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauInstrucao(String grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public double getRenda() {
		return renda;
	}

	public void setRenda(double renda) {
		this.renda = renda;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ReferenciaFamiliar [id=" + id + ", parentesco=" + parentesco + ", usuarioCadastro=" + usuarioCadastro
				+ ", paciente=" + paciente + ", nome=" + nome + ", dataNascimento=" + dataNascimento
				+ ", grauInstrucao=" + grauInstrucao + ", ocupacao=" + ocupacao + ", renda=" + renda + ", dataCadastro="
				+ dataCadastro + ", situacao=" + situacao + "]";
	}

}
