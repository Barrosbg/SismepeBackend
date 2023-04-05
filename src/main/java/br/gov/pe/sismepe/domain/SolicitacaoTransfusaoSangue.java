package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="sts_solicitacao")
public class SolicitacaoTransfusaoSangue implements Serializable {

//	private static final long serialVersionUID = 1L;
//
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="CD_SOLICITACAO")
//	private int id;
//	
//	@Column(name="CD_PESSOA")
//	private Pessoa pessoa;
//	
//	
//	@Column(name="peso")
//	private double peso;
//	
//	@Column(name="CD_HOSPITAL")
//	private Ome hospital;
//	
//	@Column(name="HOSPITAL_FONE")
//	private String fone;
//	
//	@Column(name="LOCAL_INTERNACAO")
//	private String internaca;
//	
//	@Column(name="CD_REGISTRO_PACIENTE")
//	private Paciente paciente;
//	
//	
//	@Column(name="DIAGNOSTICO_INDICACAO")
//	private String diag_indicacao;
//	
//	@Column(name="ANTE_TRANSFUSIONAIS")
//	private String ante_transfusionais;
//	
//	@Column(name="ANTE_GESTACIONAIS")
//	private String ante_gestacionais;
//	
//	@Column(name="ANTE_GESTACIONAIS_ABORTO")
//	private  String ante_gestacionais_aborto;
//	
//	@Column(name="REACAO_TRANSFUSIONAL")
//	private String reacao_transfusional;
//	
//	@Column(name="REACAO_TRANSFUSIONAL_TIPO")
//	private String reacao_transfusional_tipo;
//	
//	@Column(name="REACAO_TRANSFUSIONAL_DATA")
//	private Date reacao_transfusional_data;
//	
//	@Column(name="USA_SANGUE_FENOTIPADO")
//	private String usa_sangue_fenotipado;
//	
//	@Column(name="USA_SANGUE_FENOTIPADO_TIPO")
//	private String usa_sangue_fenotipado_tipo;
//	
//	@Column(name="CD_PRESTADOR")
//	private Prestador prestador;
//	
//	@Column(name="DT_CADASTRO")
//	private Date dt_cadastro;
//	@Column(name="CD_USUARIO_CADASTRO")
//	private Usuario usuario_cadastro;
//	
//	@Column(name="TP_TRANSFUSAO")
//	private String tipo_transfusao;
//	
//	@Column(name="TP_TRANSFUSAO_DATA")
//	private Date tipo_transfusao_data;
//	
//	
//
//	public SolicitacaoTransfusaoSangue() {
//		super();
//	}
//
//
//
//	public SolicitacaoTransfusaoSangue(int id, Pessoa pessoa, double peso, Ome hospital, String fone, String internaca,
//			Paciente paciente, String diag_indicacao, String ante_transfusionais, String ante_gestacionais,
//			String ante_gestacionais_aborto, String reacao_transfusional, String reacao_transfusional_tipo,
//			Date reacao_transfusional_data, String usa_sangue_fenotipado, String usa_sangue_fenotipado_tipo,
//			Prestador prestador, Date dt_cadastro, Usuario usuario_cadastro, String tipo_transfusao,
//			Date tipo_transfusao_data) {
//		super();
//		this.id = id;
//		this.pessoa = pessoa;
//		this.peso = peso;
//		this.hospital = hospital;
//		this.fone = fone;
//		this.internaca = internaca;
//		this.paciente = paciente;
//		this.diag_indicacao = diag_indicacao;
//		this.ante_transfusionais = ante_transfusionais;
//		this.ante_gestacionais = ante_gestacionais;
//		this.ante_gestacionais_aborto = ante_gestacionais_aborto;
//		this.reacao_transfusional = reacao_transfusional;
//		this.reacao_transfusional_tipo = reacao_transfusional_tipo;
//		this.reacao_transfusional_data = reacao_transfusional_data;
//		this.usa_sangue_fenotipado = usa_sangue_fenotipado;
//		this.usa_sangue_fenotipado_tipo = usa_sangue_fenotipado_tipo;
//		this.prestador = prestador;
//		this.dt_cadastro = dt_cadastro;
//		this.usuario_cadastro = usuario_cadastro;
//		this.tipo_transfusao = tipo_transfusao;
//		this.tipo_transfusao_data = tipo_transfusao_data;
//	}
//
//
//
//	public int getId() {
//		return id;
//	}
//
//
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//
//
//	public Pessoa getPessoa() {
//		return pessoa;
//	}
//
//
//
//	public void setPessoa(Pessoa pessoa) {
//		this.pessoa = pessoa;
//	}
//
//
//
//	public double getPeso() {
//		return peso;
//	}
//
//
//
//	public void setPeso(double peso) {
//		this.peso = peso;
//	}
//
//
//
//	public Ome getHospital() {
//		return hospital;
//	}
//
//
//
//	public void setHospital(Ome hospital) {
//		this.hospital = hospital;
//	}
//
//
//
//	public String getFone() {
//		return fone;
//	}
//
//
//
//	public void setFone(String fone) {
//		this.fone = fone;
//	}
//
//
//
//	public String getInternaca() {
//		return internaca;
//	}
//
//
//
//	public void setInternaca(String internaca) {
//		this.internaca = internaca;
//	}
//
//
//
//	public Paciente getPaciente() {
//		return paciente;
//	}
//
//
//
//	public void setPaciente(Paciente paciente) {
//		this.paciente = paciente;
//	}
//
//
//
//	public String getDiag_indicacao() {
//		return diag_indicacao;
//	}
//
//
//
//	public void setDiag_indicacao(String diag_indicacao) {
//		this.diag_indicacao = diag_indicacao;
//	}
//
//
//
//	public String getAnte_transfusionais() {
//		return ante_transfusionais;
//	}
//
//
//
//	public void setAnte_transfusionais(String ante_transfusionais) {
//		this.ante_transfusionais = ante_transfusionais;
//	}
//
//
//
//	public String getAnte_gestacionais() {
//		return ante_gestacionais;
//	}
//
//
//
//	public void setAnte_gestacionais(String ante_gestacionais) {
//		this.ante_gestacionais = ante_gestacionais;
//	}
//
//
//
//	public String getAnte_gestacionais_aborto() {
//		return ante_gestacionais_aborto;
//	}
//
//
//
//	public void setAnte_gestacionais_aborto(String ante_gestacionais_aborto) {
//		this.ante_gestacionais_aborto = ante_gestacionais_aborto;
//	}
//
//
//
//	public String getReacao_transfusional() {
//		return reacao_transfusional;
//	}
//
//
//
//	public void setReacao_transfusional(String reacao_transfusional) {
//		this.reacao_transfusional = reacao_transfusional;
//	}
//
//
//
//	public String getReacao_transfusional_tipo() {
//		return reacao_transfusional_tipo;
//	}
//
//
//
//	public void setReacao_transfusional_tipo(String reacao_transfusional_tipo) {
//		this.reacao_transfusional_tipo = reacao_transfusional_tipo;
//	}
//
//
//
//	public Date getReacao_transfusional_data() {
//		return reacao_transfusional_data;
//	}
//
//
//
//	public void setReacao_transfusional_data(Date reacao_transfusional_data) {
//		this.reacao_transfusional_data = reacao_transfusional_data;
//	}
//
//
//
//	public String getUsa_sangue_fenotipado() {
//		return usa_sangue_fenotipado;
//	}
//
//
//
//	public void setUsa_sangue_fenotipado(String usa_sangue_fenotipado) {
//		this.usa_sangue_fenotipado = usa_sangue_fenotipado;
//	}
//
//
//
//	public String getUsa_sangue_fenotipado_tipo() {
//		return usa_sangue_fenotipado_tipo;
//	}
//
//
//
//	public void setUsa_sangue_fenotipado_tipo(String usa_sangue_fenotipado_tipo) {
//		this.usa_sangue_fenotipado_tipo = usa_sangue_fenotipado_tipo;
//	}
//
//
//
//	public Prestador getPrestador() {
//		return prestador;
//	}
//
//
//
//	public void setPrestador(Prestador prestador) {
//		this.prestador = prestador;
//	}
//
//
//
//	public Date getDt_cadastro() {
//		return dt_cadastro;
//	}
//
//
//
//	public void setDt_cadastro(Date dt_cadastro) {
//		this.dt_cadastro = dt_cadastro;
//	}
//
//
//
//	public Usuario getUsuario_cadastro() {
//		return usuario_cadastro;
//	}
//
//
//
//	public void setUsuario_cadastro(Usuario usuario_cadastro) {
//		this.usuario_cadastro = usuario_cadastro;
//	}
//
//
//
//	public String getTipo_transfusao() {
//		return tipo_transfusao;
//	}
//
//
//
//	public void setTipo_transfusao(String tipo_transfusao) {
//		this.tipo_transfusao = tipo_transfusao;
//	}
//
//
//
//	public Date getTipo_transfusao_data() {
//		return tipo_transfusao_data;
//	}
//
//
//
//	public void setTipo_transfusao_data(Date tipo_transfusao_data) {
//		this.tipo_transfusao_data = tipo_transfusao_data;
//	}
//
//
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + id;
//		return result;
//	}
//
//
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		SolicitacaoTransfusaoSangue other = (SolicitacaoTransfusaoSangue) obj;
//		if (id != other.id)
//			return false;
//		return true;
//	}
//	
//	
//	
	
}
