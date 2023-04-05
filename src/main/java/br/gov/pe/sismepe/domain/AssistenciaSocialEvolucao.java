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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ASSISTENCIA_SOCIAL_EVOLUCAO")
public class AssistenciaSocialEvolucao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CD_ASSISTENCIA_SOCIAL_EVOLUCAO")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="CD_ASSISTENCIA_SOCIAL", nullable=false)
	@JsonIgnoreProperties(value = {"evolucoes"})
	private AssistenciaSocial assistenciaSocial; 
	
	@ManyToOne
    @JoinColumn(name="CD_ATENDIMENTO", nullable=false)
	private Atendimento atendimento;
	
	@Column(name = "DS_EVOLUCAO")
	private String descricao;
	
	@Column(name = "DT_INCL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInclusao;
	
	@Column(name = "SN_ATIVO")
	private String situacao;
	
	public AssistenciaSocialEvolucao() {}

	public AssistenciaSocialEvolucao(Long id, AssistenciaSocial assistenciaSocial, Atendimento atendimento,
			String descricao, Date dataInclusao, String situacao) {
		super();
		this.id = id;
		this.assistenciaSocial = assistenciaSocial;
		this.atendimento = atendimento;
		this.descricao = descricao;
		this.dataInclusao = dataInclusao;
		this.situacao = situacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AssistenciaSocial getAssistenciaSocial() {
		return assistenciaSocial;
	}

	public void setAssistenciaSocial(AssistenciaSocial assistenciaSocial) {
		this.assistenciaSocial = assistenciaSocial;
	}

		public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSituacao() {
		return situacao;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}


	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}


	public Date getDataInclusao() {
		return dataInclusao;
	}


	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
	
}
