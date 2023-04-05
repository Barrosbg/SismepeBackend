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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "autorizacao_procedimento_externo_validade")
public class ValidadeAutorizacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_VALIDADE")
	private Long id;

	@JsonIgnoreProperties({ "itSolicitacao", "empresa", "usuarioAutorizacao", "situacao", "quantidade", "validades",
			"dataCadastro", "dataAlteracao", "quantidadeFormatada" })
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "CD_AUT_PROCEDIMENTO_EXTERNO")
	private AutorizacaoProcedimentoExterno autorizacaoProcedimentoExterno;

	@JsonIgnoreProperties({ "pessoa", "login", "email", "ativo", "nivelAcesso" })
	@OneToOne
	@JoinColumn(name = "CD_USUARIO")
	private Usuario usuarioCadastro;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "DT_VALIDADE")
	private Date dataValidade;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "DT_CADASTRO")
	private Date dataCadastro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AutorizacaoProcedimentoExterno getAutorizacaoProcedimentoExterno() {
		return autorizacaoProcedimentoExterno;
	}

	public void setAutorizacaoProcedimentoExterno(AutorizacaoProcedimentoExterno autorizacaoProcedimentoExterno) {
		this.autorizacaoProcedimentoExterno = autorizacaoProcedimentoExterno;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "ValidadeAutorizacao [id=" + id + ", autorizacaoProcedimentoExterno=" + autorizacaoProcedimentoExterno
				+ ", usuarioCadastro=" + usuarioCadastro + ", dataValidade=" + dataValidade + ", dataCadastro="
				+ dataCadastro + "]";
	}

}
