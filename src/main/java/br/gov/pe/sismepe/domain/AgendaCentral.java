package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Table(name = "AGENDA_CENTRAL")
@Inheritance(strategy=InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public class AgendaCentral implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CD_AGENDA_CENTRAL")
	private Long id;
	
	@Column(name = "TP_AGENDA_CENTRAL")
	private String tipo;
	
	@Column(name = "CD_UNIDADE_ATENDIMENTO")
	private Long CdUnidadeAtendimento;
	
	@Column(name = "DT_AGENDA_CENTRAL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataAgendamento;
	
	@Column(name = "HR_INICIAL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date horaInicial;
	
	@Column(name = "HR_FINAL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date horaFinal;
	
	@Column(name = "SN_PERMITE_ENCAIXE")
	private String permiteEncaixe;
	
	@Column(name = "SN_PERMITE_AGEND_RECEP")
	private String permiteAgendamentoRecepcao;
	
	@Column(name = "SN_PERMITE_AGEND_VOLTA")
	private String permiteAgendamentoVolta;
	
	@Column(name = "SN_BLOQUEADO")
	private String bloqueado;
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	@Column(name = "DT_ALT")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataAlteracao;
	
	@Column(name = "DT_INCL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInclusao;
	
	
	
	@Column(name = "CD_USUARIO_ALTERACAO")
	private Long CdUsuarioAlteracao;
	
	
	public AgendaCentral() {
	}

	public AgendaCentral(Long id) {
		super();
		this.id = id;
	}

	public AgendaCentral(Long id, String tipo, Date dataAgendamento, Date horaInicial, String permiteEncaixe,
			String bloqueado, String ativo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.dataAgendamento = dataAgendamento;
		this.horaInicial = horaInicial;
		this.permiteEncaixe = permiteEncaixe;
		this.bloqueado = bloqueado;
		this.ativo = ativo;
	}
	
	

	public AgendaCentral(Long id, String tipo, Long cdUnidadeAtendimento, Date dataAgendamento, Date horaInicial,
			Date horaFinal, String permiteEncaixe, String permiteAgendamentoRecepcao, String permiteAgendamentoVolta,
			String bloqueado, String ativo, Date dataAlteracao, Date dataInclusao, Long cdUsuarioAlteracao) {
		super();
		this.id = id;
		this.tipo = tipo;
		CdUnidadeAtendimento = cdUnidadeAtendimento;
		this.dataAgendamento = dataAgendamento;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.permiteEncaixe = permiteEncaixe;
		this.permiteAgendamentoRecepcao = permiteAgendamentoRecepcao;
		this.permiteAgendamentoVolta = permiteAgendamentoVolta;
		this.bloqueado = bloqueado;
		this.ativo = ativo;
		this.dataAlteracao = dataAlteracao;
		this.dataInclusao = dataInclusao;
		CdUsuarioAlteracao = cdUsuarioAlteracao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getCdUnidadeAtendimento() {
		return CdUnidadeAtendimento;
	}

	public void setCdUnidadeAtendimento(Long cdUnidadeAtendimento) {
		CdUnidadeAtendimento = cdUnidadeAtendimento;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
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

	public String getPermiteEncaixe() {
		return permiteEncaixe;
	}

	public void setPermiteEncaixe(String permiteEncaixe) {
		this.permiteEncaixe = permiteEncaixe;
	}

	public String getPermiteAgendamentoRecepcao() {
		return permiteAgendamentoRecepcao;
	}

	public void setPermiteAgendamentoRecepcao(String permiteAgendamentoRecepcao) {
		this.permiteAgendamentoRecepcao = permiteAgendamentoRecepcao;
	}

	public String getPermiteAgendamentoVolta() {
		return permiteAgendamentoVolta;
	}

	public void setPermiteAgendamentoVolta(String permiteAgendamentoVolta) {
		this.permiteAgendamentoVolta = permiteAgendamentoVolta;
	}

	public String getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Long getCdUsuarioAlteracao() {
		return CdUsuarioAlteracao;
	}

	public void setCdUsuarioAlteracao(Long cdUsuarioAlteracao) {
		CdUsuarioAlteracao = cdUsuarioAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CdUnidadeAtendimento == null) ? 0 : CdUnidadeAtendimento.hashCode());
		result = prime * result + ((CdUsuarioAlteracao == null) ? 0 : CdUsuarioAlteracao.hashCode());
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((bloqueado == null) ? 0 : bloqueado.hashCode());
		result = prime * result + ((dataAgendamento == null) ? 0 : dataAgendamento.hashCode());
		result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((horaFinal == null) ? 0 : horaFinal.hashCode());
		result = prime * result + ((horaInicial == null) ? 0 : horaInicial.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((permiteAgendamentoRecepcao == null) ? 0 : permiteAgendamentoRecepcao.hashCode());
		result = prime * result + ((permiteAgendamentoVolta == null) ? 0 : permiteAgendamentoVolta.hashCode());
		result = prime * result + ((permiteEncaixe == null) ? 0 : permiteEncaixe.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		AgendaCentral other = (AgendaCentral) obj;
		if (CdUnidadeAtendimento == null) {
			if (other.CdUnidadeAtendimento != null)
				return false;
		} else if (!CdUnidadeAtendimento.equals(other.CdUnidadeAtendimento))
			return false;
		if (CdUsuarioAlteracao == null) {
			if (other.CdUsuarioAlteracao != null)
				return false;
		} else if (!CdUsuarioAlteracao.equals(other.CdUsuarioAlteracao))
			return false;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (bloqueado == null) {
			if (other.bloqueado != null)
				return false;
		} else if (!bloqueado.equals(other.bloqueado))
			return false;
		if (dataAgendamento == null) {
			if (other.dataAgendamento != null)
				return false;
		} else if (!dataAgendamento.equals(other.dataAgendamento))
			return false;
		if (dataAlteracao == null) {
			if (other.dataAlteracao != null)
				return false;
		} else if (!dataAlteracao.equals(other.dataAlteracao))
			return false;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (horaFinal == null) {
			if (other.horaFinal != null)
				return false;
		} else if (!horaFinal.equals(other.horaFinal))
			return false;
		if (horaInicial == null) {
			if (other.horaInicial != null)
				return false;
		} else if (!horaInicial.equals(other.horaInicial))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (permiteAgendamentoRecepcao == null) {
			if (other.permiteAgendamentoRecepcao != null)
				return false;
		} else if (!permiteAgendamentoRecepcao.equals(other.permiteAgendamentoRecepcao))
			return false;
		if (permiteAgendamentoVolta == null) {
			if (other.permiteAgendamentoVolta != null)
				return false;
		} else if (!permiteAgendamentoVolta.equals(other.permiteAgendamentoVolta))
			return false;
		if (permiteEncaixe == null) {
			if (other.permiteEncaixe != null)
				return false;
		} else if (!permiteEncaixe.equals(other.permiteEncaixe))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	
	
	
}
