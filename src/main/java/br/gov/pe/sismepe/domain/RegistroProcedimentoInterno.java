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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "realizacao_procedimento_intern")
public class RegistroProcedimentoInterno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CD_REALIZACAO_PROCEDIMENTO_INTERN")
	private Long id;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "CD_AUTORIZACAO_PROCEDIMENTO_INTERN")
	private AutorizacaoProcedimentoExterno autorizacao;
	
	@ManyToOne
	@JoinColumn(name = "CD_USUARIO_CADASTRO")
	private Usuario usuarioCadastro;
	
	@Column(name = "DS_OBSERVACAO")
	private String observação;
	
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

	public AutorizacaoProcedimentoExterno getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(AutorizacaoProcedimentoExterno autorizacao) {
		this.autorizacao = autorizacao;
	}

	public String getObservação() {
		return observação;
	}

	public void setObservação(String observação) {
		this.observação = observação;
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

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	@Override
	public String toString() {
		return "RegistroProcedimentoInterno [id=" + id + ", autorizacao=" + autorizacao + ", usuarioCadastro="
				+ usuarioCadastro + ", observação=" + observação + ", dataCadastro=" + dataCadastro + ", dataAlteracao="
				+ dataAlteracao + "]";
	}
	
	
	
}
