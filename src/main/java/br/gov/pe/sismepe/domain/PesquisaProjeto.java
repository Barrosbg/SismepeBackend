package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="pesquisa_projeto")
public class PesquisaProjeto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CD_PESQUISA_PROJETO")
	private Long id;
	
	@Column(name="DS_PESQUISA_PROJETO")
	private String descricaoProjeto;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="DT_CADASTRO")
	private Date dataCadastro = new Date();
	
	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="DT_EXPIRACAO")
	private Date dataExpiracao;
	
    @Column(name="PESQUISA_EXP" )
	private String pesquisaExpirada;
    
    @OneToMany
    private List<PesquisaDeSatisfacao> pesquisaSatisfacao;
    
	public PesquisaProjeto() {
		super();
	}

	public PesquisaProjeto(Long id, String descricaoProjeto, Date dataCadastro, Date dataExpiracao, 
			String pesquisaExpirada, List<PesquisaDeSatisfacao> pesquisaSatisfacao) {
		super();
		this.id = id;
		this.descricaoProjeto = descricaoProjeto;
		this.dataCadastro = dataCadastro;
		this.dataExpiracao = dataExpiracao;
		this.pesquisaExpirada = pesquisaExpirada;	
		this.pesquisaSatisfacao = pesquisaSatisfacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoProjeto() {
		return descricaoProjeto;
	}

	public void setDescricaoProjeto(String descricaoProjeto) {
		this.descricaoProjeto = descricaoProjeto;
	}

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

	public List<PesquisaDeSatisfacao> getPesquisaSatisfacao() {
		return pesquisaSatisfacao;
	}

	public void setPesquisaSatisfacao(List<PesquisaDeSatisfacao> pesquisaSatisfacao) {
		this.pesquisaSatisfacao = pesquisaSatisfacao;
	}
	
}