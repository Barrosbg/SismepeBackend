package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="it_solicitacao_procedimento_externo")
public class ItSolicitacaoProcedimentoExterno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CD_IT_SOLICITACAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CD_SOLICITACAO")
	private SolicitacaoProcedimentoExterno solicitacaoProcedimentoExterno;
	
	@ManyToOne
	@JoinColumn(name = "CD_PROCEDIMENTO_SISMEPE")
	private ProcedimentoSismepe procedimento;
	
	@Column(name = "DS_SITUACAO")
	private String situacao;
	
	@Column(name = "NR_PROCEDIMENTOS_SOL")
	private int quantidade;
	
	@Column(name = "DT_CADASTRO")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataCadastro;
	
	@Column(name = "DT_ALTERACAO")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataAlteracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProcedimentoSismepe getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(ProcedimentoSismepe procedimento) {
		this.procedimento = procedimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public SolicitacaoProcedimentoExterno getSolicitacaoProcedimentoExterno() {
		return solicitacaoProcedimentoExterno;
	}

	public void setSolicitacaoProcedimentoExterno(SolicitacaoProcedimentoExterno solicitacaoProcedimentoExterno) {
		this.solicitacaoProcedimentoExterno = solicitacaoProcedimentoExterno;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getQuantidadeFormatada() {
		if(quantidade > 100) {
			return Integer.toString(quantidade);
		} else if (quantidade >= 10) {
			return "0" + quantidade;
		} else {
			return "00" + quantidade;
		}
	}
	
}
