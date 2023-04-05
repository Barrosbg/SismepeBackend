package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="pesquisa_expiracao")
public class PesquisaExpiracao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CD_PESQUISA")
	private Long id;
	
	@Column(name="CD_PESSOA")
	private Long cdPessoa;
	
	@Column(name="CD_ATENDIMENTO")
	private Long cdAtendimento;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="DT_CADASTRO")
	private Date dataCadastro = new Date();
	
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name="DT_EXPIRACAO")
	private LocalDate dataExpiracao;
	
    @Column(name="PESQUISA_EXP")
	private String pesquisaExpirada;
    
    
    public PesquisaExpiracao () {
    	
    }
    

	public PesquisaExpiracao( Long cdPessoa, Long cdAtendimento, Date dataCadastro, LocalDate dataExpiracao,
			String pesquisaExpirada) {
		super();
		
		this.cdPessoa = cdPessoa;
		this.cdAtendimento = cdAtendimento;
		this.dataCadastro = dataCadastro;
		this.dataExpiracao = dataExpiracao;
		this.pesquisaExpirada = pesquisaExpirada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCdPessoa() {
		return cdPessoa;
	}

	public void setCdPessoa(Long cdPessoa) {
		this.cdPessoa = cdPessoa;
	}

	public Long getCdAtendimento() {
		return cdAtendimento;
	}

	public void setCdAtendimento(Long cdAtendimento) {
		this.cdAtendimento = cdAtendimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDate mesSeguinte) {
		this.dataExpiracao = mesSeguinte;
	}

	public String getPesquisaExpirada() {
		return pesquisaExpirada;
	}

	public void setPesquisaExpirada(String pesquisaExpirada) {
		this.pesquisaExpirada = pesquisaExpirada;
	}
    
 
	

}
