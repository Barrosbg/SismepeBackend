package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.gov.pe.sismepe.domain.enums.AcomodacaoSolicitacaoProcedimentoEnum;
import br.gov.pe.sismepe.domain.enums.CaraterSolicitacaoProcedimentoEnum;
import br.gov.pe.sismepe.domain.enums.ClassificacaoSolicitacaoProcedimentoEnum;
import br.gov.pe.sismepe.domain.enums.NaturezaSolicitacaoProcedimentoEnum;
import br.gov.pe.sismepe.domain.enums.RegimeSolicitacaoProcedimentoEnum;

@Entity
@Table(name="solicitacao_procedimento_externo")
public class SolicitacaoProcedimentoExterno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CD_SOLICITACAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CD_PRESTADOR_SOLICITANTE")
	@JsonIgnoreProperties(value = {"especialidades"})
	private Prestador prestadorSolicitante;
	
	@ManyToOne
	@JoinColumn(name = "CD_PACIENTE")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "CD_OME")
	private Ome ome;
	
	@ManyToOne
	@JoinColumn(name = "CD_CID")
	private Cid cid;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "solicitacaoProcedimentoExterno", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	List<ItSolicitacaoProcedimentoExterno> itSolicitacoes = new ArrayList<ItSolicitacaoProcedimentoExterno>();
	
	@ManyToOne(fetch = FetchType.LAZY, optional=true)
	@JoinColumn(name="CD_SOLICITACAO_PAI")
	private SolicitacaoProcedimentoExterno solicitacaoPai;
		
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "TP_CARATER")
	private CaraterSolicitacaoProcedimentoEnum carater;

	@Enumerated(EnumType.STRING)
	@Column(name = "TP_CLASSIFICACAO")
	private ClassificacaoSolicitacaoProcedimentoEnum classificacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "TP_NATUREZA")
	private NaturezaSolicitacaoProcedimentoEnum natureza;

	@Enumerated(EnumType.STRING)
	@Column(name = "TP_REGIME_INTERN")
	private RegimeSolicitacaoProcedimentoEnum regimeInternacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "TP_ACOMODACAO")
	private AcomodacaoSolicitacaoProcedimentoEnum acomodacao;	
	
	@Column(name = "DS_SITUACAO")
	private String situacao;
	
	@Column(name = "DS_JUSTIFICATIVA")
	private String justificativa;
	
	@Column(name = "DS_OBSERVACAO")
	private String observacao;
	
	@Column(name = "DS_OBSERVACAO_RESTRITA")
	private String observacaoRestrita;
	
	@Column(name = "DT_CADASTRO")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataCadastro;
	
	@Column(name = "DT_VALIDADE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataValidade;
	
	@Column(name = "DT_ALTERACAO")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataAlteracao;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFormatedId() {
		int zeros = 12 - id.toString().length();
		return String.format("%0"+ zeros +"d", id);		
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

	public Prestador getPrestadorSolicitante() {
		return prestadorSolicitante;
	}

	public void setPrestadorSolicitante(Prestador prestadorSolicitante) {
		this.prestadorSolicitante = prestadorSolicitante;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getSituacao() {
		return situacao;
	}
	
	public Ome getOme() {
		return ome;
	}

	public void setOme(Ome ome) {
		this.ome = ome;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<ItSolicitacaoProcedimentoExterno> getItSolicitacoes() {
		return itSolicitacoes;
	}

	public void setItSolicitacoes(List<ItSolicitacaoProcedimentoExterno> itSolicitacoes) {
		this.itSolicitacoes = itSolicitacoes;
	}
	
	
	public CaraterSolicitacaoProcedimentoEnum getCarater() {
		return carater;
	}

	public void setCarater(CaraterSolicitacaoProcedimentoEnum carater) {
		this.carater = carater;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	
	public Cid getCid() {
		return cid;
	}

	public void setCid(Cid cid) {
		this.cid = cid;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacaoRestrita() {
		return observacaoRestrita;
	}

	public void setObservacaoRestrita(String observacaoRestrita) {
		this.observacaoRestrita = observacaoRestrita;
	}
	
	public ClassificacaoSolicitacaoProcedimentoEnum getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(ClassificacaoSolicitacaoProcedimentoEnum classificacao) {
		this.classificacao = classificacao;
	}

	public NaturezaSolicitacaoProcedimentoEnum getNatureza() {
		return natureza;
	}

	public void setNatureza(NaturezaSolicitacaoProcedimentoEnum natureza) {
		this.natureza = natureza;
	}

	public RegimeSolicitacaoProcedimentoEnum getRegimeInternacao() {
		return regimeInternacao;
	}

	public void setRegimeInternacao(RegimeSolicitacaoProcedimentoEnum regimeInternacao) {
		this.regimeInternacao = regimeInternacao;
	}

	public AcomodacaoSolicitacaoProcedimentoEnum getAcomodacao() {
		return acomodacao;
	}

	public void setAcomodacao(AcomodacaoSolicitacaoProcedimentoEnum acomodacao) {
		this.acomodacao = acomodacao;
	}

	public SolicitacaoProcedimentoExterno getSolicitacaoPai() {
		return solicitacaoPai;
	}

	public void setSolicitacaoPai(SolicitacaoProcedimentoExterno solicitacaoPai) {
		this.solicitacaoPai = solicitacaoPai;
	}
	
	
}
