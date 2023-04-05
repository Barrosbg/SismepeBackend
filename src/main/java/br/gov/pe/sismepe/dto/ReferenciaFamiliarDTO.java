package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.ReferenciaFamiliar;

public class ReferenciaFamiliarDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long parentescoId;

	private Integer usuarioCadastroId;

	private Long pacienteId;

	private String nome;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	private String grauInstrucao;

	private String ocupacao;

	private double renda;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCadastro;

	private String situacao;

	public ReferenciaFamiliarDTO() {
	}
	
	public ReferenciaFamiliarDTO(Long id, Long parentescoId, Integer usuarioCadastroId, Long pacienteId, String nome,
			Date dataNascimento, String grauInstrucao, String ocupacao, double renda, Date dataCadastro,
			String situacao) {
		super();
		this.id = id;
		this.parentescoId = parentescoId;
		this.usuarioCadastroId = usuarioCadastroId;
		this.pacienteId = pacienteId;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.grauInstrucao = grauInstrucao;
		this.ocupacao = ocupacao;
		this.renda = renda;
		this.dataCadastro = dataCadastro;
		this.situacao = situacao;
	}
	
	public ReferenciaFamiliarDTO(ReferenciaFamiliar obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.pacienteId = obj.getPaciente().getId();
		this.parentescoId = obj.getParentesco().getId();
		this.dataCadastro = obj.getDataCadastro();
		this.dataNascimento = obj.getDataNascimento();
		this.grauInstrucao = obj.getGrauInstrucao();
		this.ocupacao = obj.getOcupacao();
		this.renda = obj.getRenda();
		this.situacao = obj.getSituacao();
		this.usuarioCadastroId = obj.getUsuarioCadastro().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentescoId() {
		return parentescoId;
	}

	public void setParentescoId(Long parentescoId) {
		this.parentescoId = parentescoId;
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
	
}
