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

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.enums.SituacaoItAgendaCentral;

@Entity
@Table(name = "it_agenda_central")
public class ItAgendaCentral implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CD_IT_AGENDA_CENTRAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CD_AGENDA_CENTRAL")
	private AgendaCentral agendaCentral;
	
	@Column(name = "HR_INICIAL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date horaInicial;
	
	@Column(name = "HR_FINAL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date horaFinal;
	
	@Column(name = "SN_ENCAIXE")
	private String encaixe;
	
	@Column(name = "TP_SITUACAO")
	private String situacao;
	
	@OneToOne
	@JoinColumn(name = "CD_PACIENTE")
	private Paciente paciente;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	@OneToOne
	@JoinColumn(name = "CD_ATENDIMENTO")
	private Atendimento atendimento;
	
	@Column(name = "DT_AGENDAMENTO")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataAgendamento;
	
	@Column(name = "DT_CANCELAMENTO")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataCancelamento;
	
	@Column(name = "DT_ALT")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataAlt;
	
	@Column(name = "DT_INCL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInc;
	
	@OneToOne
	@JoinColumn(name = "CD_USUARIO_AGENDAMENTO")
	private Usuario usuarioAgend;
	
	@OneToOne
	@JoinColumn(name = "CD_USUARIO_ALTERACAO")
	private Usuario usuarioAlt;
	
	@OneToOne
	@JoinColumn(name = "CD_USUARIO_CANCELAMENTO")
	private Usuario usuarioCanc;
	
	public ItAgendaCentral() {
	}
	
	public ItAgendaCentral(Long id, SituacaoItAgendaCentral situacao, Paciente paciente) {
		super();
		this.id = id;
		this.situacao = (situacao==null) ? null : situacao.getCodigo();
		this.paciente = paciente;
	}

	public ItAgendaCentral(Long id, AgendaCentral agendaCentral, Date horaInicial, Date horaFinal, String encaixe,
			SituacaoItAgendaCentral situacao, Paciente paciente, String ativo, Atendimento atendimento, Date dataAgendamento,
			Date dataCancelamento, Date dataAlt, Date dataInc, Usuario usuarioAgend, Usuario usuarioAlt,
			Usuario usuarioCanc) {
		super();
		this.id = id;
		this.agendaCentral = agendaCentral;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.encaixe = encaixe;
		this.situacao = (situacao==null) ? null : situacao.getCodigo();
		this.paciente = paciente;
		this.ativo = ativo;
		this.atendimento = atendimento;
		this.dataAgendamento = dataAgendamento;
		this.dataCancelamento = dataCancelamento;
		this.dataAlt = dataAlt;
		this.dataInc = dataInc;
		this.usuarioAgend = usuarioAgend;
		this.usuarioAlt = usuarioAlt;
		this.usuarioCanc = usuarioCanc;
	}

	/**
	 * Copiar uma agenda sem o id
	 * @param itAgendaCentral
	 */
	public ItAgendaCentral(ItAgendaCentral itAgendaCentral) {
		super();
		this.agendaCentral = itAgendaCentral.agendaCentral;
		this.horaInicial = itAgendaCentral.horaInicial;
		this.horaFinal = itAgendaCentral.horaFinal;
		this.encaixe = itAgendaCentral.encaixe;
		this.dataAgendamento = itAgendaCentral.dataAgendamento;
		this.situacao = itAgendaCentral.situacao;
		this.paciente = itAgendaCentral.paciente;
		this.ativo = itAgendaCentral.ativo;
		this.atendimento = itAgendaCentral.atendimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AgendaCentral getAgendaCentral() {
		return agendaCentral;
	}

	public void setAgendaCentral(AgendaCentral agendaCentral) {
		this.agendaCentral = agendaCentral;
	}

	public Date getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}

	public Date getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getEncaixe() {
		return encaixe;
	}

	public void setEncaixe(String encaixe) {
		this.encaixe = encaixe;
	}
	
	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	public SituacaoItAgendaCentral getSituacao() {
		return SituacaoItAgendaCentral.toEnum(situacao);
	}

	public void setSituacao(SituacaoItAgendaCentral situacao) {
		this.situacao = situacao.getCodigo();
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	
	public Date getDataAlt() {
		return dataAlt;
	}

	public void setDataAlt(Date dataAlt) {
		this.dataAlt = dataAlt;
	}

	public Date getDataInc() {
		return dataInc;
	}

	public void setDataInc(Date dataInc) {
		this.dataInc = dataInc;
	}
	
	public Usuario getUsuarioAlt() {
		return usuarioAlt;
	}

	public void setUsuarioAlt(Usuario usuarioAlt) {
		this.usuarioAlt = usuarioAlt;
	}
	
	public Usuario getUsuarioAgend() {
		return usuarioAgend;
	}

	public void setUsuarioAgend(Usuario usuarioAgend) {
		this.usuarioAgend = usuarioAgend;
	}

	public Usuario getUsuarioCanc() {
		return usuarioCanc;
	}

	public void setUsuarioCanc(Usuario usuarioCanc) {
		this.usuarioCanc = usuarioCanc;
	}
	
	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItAgendaCentral other = (ItAgendaCentral) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
