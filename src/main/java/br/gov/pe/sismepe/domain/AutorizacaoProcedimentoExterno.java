package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "autorizacao_procedimento_externo")
public class AutorizacaoProcedimentoExterno implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "CD_AUT_PROCEDIMENTO_INTER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "CD_IT_SOLICITACAO")
	private ItSolicitacaoProcedimentoExterno itSolicitacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "CD_EMPRESA")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name = "CD_USUARIO_AUTORIZACAO")
	private Usuario usuarioAutorizacao;
	
	@Column(name = "DS_SITUACAO")
	private String situacao;

	@Column(name = "NR_PROCEDIMENTOS_AUT")
	private int quantidade;
	
	@OneToMany(mappedBy="autorizacaoProcedimentoExterno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ValidadeAutorizacao> validades;
	
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

	public ItSolicitacaoProcedimentoExterno getItSolicitacao() {
		return itSolicitacao;
	}

	public void setItSolicitacao(ItSolicitacaoProcedimentoExterno itSolicitacao) {
		this.itSolicitacao = itSolicitacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuarioAutorizacao() {
		return usuarioAutorizacao;
	}

	public void setUsuarioAutorizacao(Usuario usuarioAutorizacao) {
		this.usuarioAutorizacao = usuarioAutorizacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
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

	public List<ValidadeAutorizacao> getValidades() {
		return validades;
	}

	public void setValidades(List<ValidadeAutorizacao> validades) {
		this.validades = validades;
	}
	
	
	
}
