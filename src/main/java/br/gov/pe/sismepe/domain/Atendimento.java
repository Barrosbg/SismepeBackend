package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.gov.pe.sismepe.domain.enums.SituacaoAtendimento;
import br.gov.pe.sismepe.domain.enums.TipoAtendimentoEnum;

@Entity
@Table(name = "ATENDIMENTO")
@Inheritance(strategy = InheritanceType.JOINED)
public class Atendimento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_ATENDIMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TP_ATENDIMENTO")
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name = "CD_PACIENTE")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "CD_PRESTADOR")
	private Prestador prestador;
	
	@ManyToOne
	@JoinColumn(name = "CD_ESPECIALIDADE")
	private Especialidade especialidade;
	
	@Column(name = "CD_SITUACAO_ATENDIMENTO")
	private Integer situacao;
	
	@Column(name = "CD_ORIGEM_ATEND")
	private Integer origem;
	
	@Column(name = "DT_ABERTURA")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataAbertura;
	
	@ManyToOne
	@JsonIgnoreProperties(value = {"perfis","nivelAcesso","ativo"})
	@JoinColumn(name = "CD_USUARIO_ABERTURA")
	private Usuario usuarioAbertura;
	
	@Column(name = "DT_FINALIZACAO")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataFinalizacao;
	
	@ManyToOne
	@JsonIgnoreProperties(value = {"perfis","nivelAcesso","ativo"})
	@JoinColumn(name = "CD_USUARIO_FINALIZACAO")
	private Usuario usuarioFinalizacao;
	
	@Column(name = "DT_CANCELAMENTO")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataCancelamento;
	
	@ManyToOne
	@JsonIgnoreProperties(value = {"perfis","nivelAcesso","ativo"})
	@JoinColumn(name = "CD_USUARIO_CANCELAMENTO")
	private Usuario usuarioCancelamento;
		
	
//	  `CD_ORIGEM_ATEND` int(11) NOT NULL COMMENT 'Código do local de origem do paciente. \r\n\r\nEx.: Recepção SPA, Recepção SPA Infantil etc.',
//	  `CD_ESPECIALIDADE` int(11) DEFAULT NULL COMMENT 'Código de identificação da especialidade em que o paciente será atendido.',
//	  `CD_PRESTADOR` int(11) DEFAULT NULL COMMENT 'Código do prestador (médico) que  atendeu o paciente.',
//	  `DS_OBSERVACAO` text COMMENT 'Observações a respeito do atendimento.',
//	  `NR_SENHA` int(11) 
	  
	
	public Atendimento() {
		
	}
	
	public Atendimento(SituacaoAtendimento situacao) {
		this.situacao = (situacao == null) ? null : situacao.getCodigo();
	}
	
	public Atendimento(TipoAtendimentoEnum tipo, Paciente paciente, SituacaoAtendimento situacao, Integer origem,
			Usuario usuarioAbertura) {
		super();
		this.tipo = (tipo == null) ? null : tipo.getSigla();
		this.paciente = paciente;
		this.situacao = (situacao == null) ? null : situacao.getCodigo();
		this.origem = origem;
		this.dataAbertura = new Date();
		this.usuarioAbertura = usuarioAbertura;
	}

	public Atendimento(TipoAtendimentoEnum tipo, Paciente paciente, SituacaoAtendimento situacao, Integer origem,
			Prestador prestador, Especialidade especialidade, Usuario usuarioAbertura) {
		super();
		this.tipo = (tipo == null) ? null : tipo.getSigla();
		this.paciente = paciente;
		this.situacao = (situacao == null) ? null : situacao.getCodigo();
		this.origem = origem;
		this.prestador = prestador;
		this.especialidade = especialidade;
		this.dataAbertura = new Date();
		this.usuarioAbertura = usuarioAbertura;
	}

	public Atendimento(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTipo(TipoAtendimentoEnum tipo) {
		this.tipo = tipo.getSigla();
	}
	
	public TipoAtendimentoEnum getTipo() {
		return TipoAtendimentoEnum.toEnum(tipo);
	}
	
	public void setSituacao(SituacaoAtendimento situacao) {
		this.situacao = situacao.getCodigo();
	}
	
	public SituacaoAtendimento getSituacao() {
		return SituacaoAtendimento.toEnum(situacao);
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Integer getOrigem() {
		return origem;
	}

	public void setOrigem(Integer origem) {
		this.origem = origem;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuarioAbertura() {
		return usuarioAbertura;
	}

	public void setUsuarioAbertura(Usuario usuarioAbertura) {
		this.usuarioAbertura = usuarioAbertura;
	}
	
	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	
	public Usuario getUsuarioFinalizacao() {
		return usuarioFinalizacao;
	}

	public void setUsuarioFinalizacao(Usuario usuarioFinalizacao) {
		this.usuarioFinalizacao = usuarioFinalizacao;
	}

	public Usuario getUsuarioCancelamento() {
		return usuarioCancelamento;
	}

	public void setUsuarioCancelamento(Usuario usuarioCancelamento) {
		this.usuarioCancelamento = usuarioCancelamento;
	}

	@Override
	public String toString() {
		return "Atendimento [id=" + id + ", tipo=" + tipo + ", paciente=" + paciente + ", prestador=" + prestador
				+ ", especialidade=" + especialidade + ", situacao=" + situacao + ", origem=" + origem
				+ ", dataAbertura=" + dataAbertura + ", usuarioAbertura=" + usuarioAbertura + ", dataFinalizacao="
				+ dataFinalizacao + ", usuarioFinalizacao=" + usuarioFinalizacao + ", dataCancelamento="
				+ dataCancelamento + ", usuarioCancelamento=" + usuarioCancelamento + "]";
	}

	
	
	
	
	
}