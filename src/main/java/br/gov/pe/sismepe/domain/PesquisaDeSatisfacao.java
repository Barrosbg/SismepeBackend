package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.dto.PesquisaDeSatisfacaoDTO;


@Entity
@Table(name="pesquisa_satisfacao")
public class PesquisaDeSatisfacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CD_PESQUISA")
	private Long id;
	
//	@JoinColumn(name="CD_PESSOA")
//	@ManyToOne
//	private Pessoa pessoa;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="DT_CADASTRO")
	private Date dataCadastro = new Date();
	
	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="DT_EXPIRACAO")
	private Date dataExpiracao;	
	
    @Column(name="PESQUISA_EXP")
	private String pesquisaExpirada;
    
    @Column(name="CODIGO_ATENDIMENTO")
	private Long codigoAtendimento;

	@JoinColumn(name="CD_PESQUISA_PROJETO" )
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	private PesquisaProjeto pesquisaProjeto;	
	
	public PesquisaDeSatisfacao() {
		super();
	}

	public PesquisaDeSatisfacao(Long id, Date dataCadastro, Date dataExpiracao, String pesquisaExpirada,
			PesquisaPerguntaResposta idPesquisa, PesquisaProjeto pesquisaProjeto, Long codigoAtendimento) {
		super();
		this.id = id;
		this.dataCadastro = dataCadastro;
		this.dataExpiracao = dataExpiracao;
		this.pesquisaExpirada = pesquisaExpirada;
		this.pesquisaProjeto = pesquisaProjeto;	
		this.codigoAtendimento = codigoAtendimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Pessoa getPessoa() {
//		return pessoa;
//	}
//
//	public void setPessoa(Pessoa pessoa) {
//		this.pessoa = pessoa;
//	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public String getPesquisaExpirada() {
		return pesquisaExpirada;
	}

	public void setPesquisaExpirada(String pesquisaExpirada) {
		this.pesquisaExpirada = pesquisaExpirada;
	}
	
	public PesquisaProjeto getPesquisaProjeto() {
		return pesquisaProjeto;
	}
	
	public void setPesquisaProjeto(PesquisaProjeto pesquisaProjeto) {
		this.pesquisaProjeto = pesquisaProjeto;
	}
	
	public Long getCodigoAtendimento() {
		return codigoAtendimento;
	}
	
	public void setCodigoAtendimento(Long codigoAtendimento) {
		this.codigoAtendimento = codigoAtendimento;
	}
	
	
}
