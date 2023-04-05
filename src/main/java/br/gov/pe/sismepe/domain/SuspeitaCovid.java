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

@Entity
@Table(name = "SUSPEITA_COVID19")
public class SuspeitaCovid implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cd_suspeita_covid19")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cd_pessoa")
	private Pessoa pessoa;
	
	@Column(name = "sn_ativo", nullable = false, length = 1)
	private String ativo;
	
	@Column(name = "sn_exame_positivo", nullable = false, length = 1)
	private String positivo;
	
	@Column(name = "dt_coleta_exame")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataColetaExame;
	
	@Column(name = "dt_resultado_exame")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataResultadoExame;
	
	@Column(name = "dt_obito")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataObito;
	
	@ManyToOne
	@JoinColumn(name = "cd_atendimento")
	private Atendimento atendimento;
	
	public SuspeitaCovid() {
		
	}
	
	public SuspeitaCovid(Integer id) {
		this.id = id;
	}
	
	public SuspeitaCovid(Integer id, String nome) {
		this.id = id;
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		this.setPessoa(pessoa);
	}

	public SuspeitaCovid(Integer id, Pessoa pessoa, String ativo, String positivo, Date dataColetaExame,
			Date dataResultadoExame) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.ativo = ativo;
		this.positivo = positivo;
		this.dataColetaExame = dataColetaExame;
		this.dataResultadoExame = dataResultadoExame;
	}
	
	public SuspeitaCovid(Integer id, String ativo, String nome) {
		super();
		this.id = id;
		this.ativo = ativo;
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		this.setPessoa(pessoa);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getPositivo() {
		return positivo;
	}

	public void setPositivo(String positivo) {
		this.positivo = positivo;
	}

	public Date getDataColetaExame() {
		return dataColetaExame;
	}

	public void setDataColetaExame(Date dataColetaExame) {
		this.dataColetaExame = dataColetaExame;
	}

	public Date getDataResultadoExame() {
		return dataResultadoExame;
	}

	public void setDataResultadoExame(Date dataResultadoExame) {
		this.dataResultadoExame = dataResultadoExame;
	}

	public Date getDataObito() {
		return dataObito;
	}

	public void setDataObito(Date dataObito) {
		this.dataObito = dataObito;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
}