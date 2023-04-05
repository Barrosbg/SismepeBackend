package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "procedimento_sismepe")
public class ProcedimentoSismepe implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_PROCEDIMENTO_SISMEPE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CD_ESPECIFICACAO")
	private String especificacao;
	
	@Column(name = "CD_REFERENCIA")
	private String referencia;
	
	@ManyToOne
	@JoinColumn(name = "CD_GRUPO_PROCEDIMENTO_SISMEPE")
	private GrupoProcedimentoSismepe grupo;
//
//	@ManyToOne
//	@JoinColumn(name = "CD_SUBGRUPO_PROCEDIMENTO_SISMEPE")
//	private SubgrupoProcedimentoSismepe subgrupo;
	
	@Column(name = "DS_PROCEDIMENTO_SISMEPE")
	private String descricao;
	
	@Column(name = "VL_PROCEDIMENTO")
	private double valor;
	
	@Column(name = "TP_SEXO")
	private String sexo;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	@Column(name = "SN_AUTORIZA_AUTOMATICAMENTE")
	private String autorizaAutomanticamente;
	
	@Column(name = "DOC_ORIGEM")
	private String docOrigem;
	
	@Column(name = "OBS")
	private String observacao;
	
	@Column(name = "TP_ESPECIALIDADE")
	private String especialidade;
			
	@Column(name = "NR_PORTE_ANESTESICO")
	private String numeroPorteAnestesico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getAutorizaAutomanticamente() {
		return autorizaAutomanticamente;
	}

	public void setAutorizaAutomanticamente(String autorizaAutomanticamente) {
		this.autorizaAutomanticamente = autorizaAutomanticamente;
	}

	public String getDocOrigem() {
		return docOrigem;
	}

	public void setDocOrigem(String docOrigem) {
		this.docOrigem = docOrigem;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	
	public String getNumeroPorteAnestesico() {
		return numeroPorteAnestesico;
	}

	public void setNumeroPorteAnestesico(String numeroPorteAnestesico) {
		this.numeroPorteAnestesico = numeroPorteAnestesico;
	}
	
	public GrupoProcedimentoSismepe getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoProcedimentoSismepe grupo) {
		this.grupo = grupo;
	}
//
//	public SubgrupoProcedimentoSismepe getSubgrupo() {
//		return subgrupo;
//	}
//
//	public void setSubgrupo(SubgrupoProcedimentoSismepe subgrupo) {
//		this.subgrupo = subgrupo;
//	}

	@Override
	public String toString() {
		return "ProcedimentoSismepe [id=" + id + ", especificacao=" + especificacao + ", referencia=" + referencia
				+ ", grupo=" + grupo + ", descricao=" + descricao + ", sexo=" + sexo + ", ativo=" + ativo
				+ ", autorizaAutomanticamente=" + autorizaAutomanticamente + ", docOrigem=" + docOrigem
				+ ", observacao=" + observacao + ", especialidade=" + especialidade + ", numeroPorteAnestesico="
				+ numeroPorteAnestesico + "]";
	}

	
	
}
