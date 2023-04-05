package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.PacienteResponsavel;

public class PacienteResponsavelDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long pacienteId;
	
	private Long responsavelId;
	
	private String responsavelNome;
	
	private String responsavelCpf;
	
	private Long parentescoId;
	
	private String parentescoDescricao;
	
	private String telefone;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataInc;
	
	private String ativo;

	public PacienteResponsavelDTO() {
		super();
	}

	public PacienteResponsavelDTO(PacienteResponsavel obj) {
		super();
		this.pacienteId = obj.getPaciente().getId();
		this.responsavelId = obj.getResponsavel().getId();
		this.responsavelNome = obj.getResponsavel().getNome();
		this.responsavelCpf = obj.getResponsavel().getCpf();
		this.parentescoId = obj.getParentesco().getId();
		this.parentescoDescricao = obj.getParentesco().getDescricao();
		this.telefone = obj.getTelefone();
		this.dataInc = obj.getDataInc();
		this.ativo = obj.getAtivo();
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Long getResponsavelId() {
		return responsavelId;
	}

	public void setResponsavelId(Long responsavelId) {
		this.responsavelId = responsavelId;
	}

	public Long getParentescoId() {
		return parentescoId;
	}

	public void setParentescoId(Long parentescoId) {
		this.parentescoId = parentescoId;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataInc() {
		return dataInc;
	}

	public void setDataInc(Date dataInc) {
		this.dataInc = dataInc;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getResponsavelNome() {
		return responsavelNome;
	}

	public void setResponsavelNome(String responsavelNome) {
		this.responsavelNome = responsavelNome;
	}

	public String getResponsavelCpf() {
		return responsavelCpf;
	}

	public void setResponsavelCpf(String responsavelCpf) {
		this.responsavelCpf = responsavelCpf;
	}

	public String getParentescoDescricao() {
		return parentescoDescricao;
	}

	public void setParentescoDescricao(String parentescoDescricao) {
		this.parentescoDescricao = parentescoDescricao;
	}

}
