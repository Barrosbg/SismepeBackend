package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "escala_central")
public class EscalaCentral implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CD_ESCALA_CENTRAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "TP_ESCALA_CENTRAL")
	private String tipo;
	
	@Column(name = "CD_UNIDADE_ATENDIMENTO")
	private Integer unidadeAtendimento;
	
	@Column(name = "NR_DIA_SEMANA")
	private Integer NRdiaSemana;
	
	@Column(name = "NR_MAX_AGENDAMENTO")
	private Integer NRmaxAgendamento;
	
	@Column(name = "HR_INICIAL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date hrInicial;
	
	@Column(name = "HR_FINAL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date hrFinal;
	
	@Column(name = "HR_TEMPO_MEDIO")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date hrTempoMedio;
	
	
	@Column(name = "SN_PERMITE_ENCAIXE")
	private String permiteEncaixe;
	
	@Column(name = "SN_AGENDAMENTO_RECEPCAO")
	private String permiteAgendamentoRecepcao;
	
	@Column(name = "SN_BLOQUEAR_AGENDAMENTO_WEB")
	private String bloquearAgendamentoWeb;
	
	@Column(name = "SN_AGENDAMENTO_VOLTA")
	private String snAgendamentoVolta;
	
	
	@Column(name = "SN_ATIVO")
	private String ativo;
	
	@Column(name = "DT_ALT")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataAlteracao;
	
	@Column(name = "DT_INCL")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInclusao;
	
	
	@Column(name = "CD_USUARIO_ALTERACAO")
	private Integer cdUsuarioAlteracao;
	
	  @OneToOne(cascade=CascadeType.ALL)
      @JoinTable(name="escala_central_amb",
                joinColumns={@JoinColumn(name="cd_escala_central",
                 referencedColumnName="cd_escala_central")},
                inverseJoinColumns={@JoinColumn(name="cd_escala_central",
                 referencedColumnName="cd_escala_central")})
      private Perfil perfil;
	
	
	public EscalaCentral() {
	}

	public EscalaCentral(Integer id, String tipo, Integer unidadeAtendimento) {
		super();
		this.id = id;
		this.tipo = tipo;
	}
	
	
	
	

	public EscalaCentral(Integer id, String tipo, Integer unidadeAtendimento, Integer nRdiaSemana,
			Integer nRmaxAgendamento, Date hrInicial, Date hrFinal, Date hrTempoMedio, String permiteEncaixe,
			String permiteAgendamentoRecepcao, String bloquearAgendamentoWeb, String snAgendamentoVolta, String ativo,
			Date dataAlteracao, Date dataInclusao, Integer cdUsuarioAlteracao) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.unidadeAtendimento = unidadeAtendimento;
		NRdiaSemana = nRdiaSemana;
		NRmaxAgendamento = nRmaxAgendamento;
		this.hrInicial = hrInicial;
		this.hrFinal = hrFinal;
		this.hrTempoMedio = hrTempoMedio;
		this.permiteEncaixe = permiteEncaixe;
		this.permiteAgendamentoRecepcao = permiteAgendamentoRecepcao;
		this.bloquearAgendamentoWeb = bloquearAgendamentoWeb;
		this.snAgendamentoVolta = snAgendamentoVolta;
		this.ativo = ativo;
		this.dataAlteracao = dataAlteracao;
		this.dataInclusao = dataInclusao;
		this.cdUsuarioAlteracao = cdUsuarioAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento(Integer unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

	public Integer getNRdiaSemana() {
		return NRdiaSemana;
	}

	public void setNRdiaSemana(Integer nRdiaSemana) {
		NRdiaSemana = nRdiaSemana;
	}

	public Integer getNRmaxAgendamento() {
		return NRmaxAgendamento;
	}

	public void setNRmaxAgendamento(Integer nRmaxAgendamento) {
		NRmaxAgendamento = nRmaxAgendamento;
	}

	public Date getHrInicial() {
		return hrInicial;
	}

	public void setHrInicial(Date hrInicial) {
		this.hrInicial = hrInicial;
	}

	public Date getHrFinal() {
		return hrFinal;
	}

	public void setHrFinal(Date hrFinal) {
		this.hrFinal = hrFinal;
	}

	public Date getHrTempoMedio() {
		return hrTempoMedio;
	}

	public void setHrTempoMedio(Date hrTempoMedio) {
		this.hrTempoMedio = hrTempoMedio;
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

	public String getBloquearAgendamentoWeb() {
		return bloquearAgendamentoWeb;
	}

	public void setBloquearAgendamentoWeb(String bloquearAgendamentoWeb) {
		this.bloquearAgendamentoWeb = bloquearAgendamentoWeb;
	}

	public String getSnAgendamentoVolta() {
		return snAgendamentoVolta;
	}

	public void setSnAgendamentoVolta(String snAgendamentoVolta) {
		this.snAgendamentoVolta = snAgendamentoVolta;
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

	public Integer getCdUsuarioAlteracao() {
		return cdUsuarioAlteracao;
	}

	public void setCdUsuarioAlteracao(Integer cdUsuarioAlteracao) {
		this.cdUsuarioAlteracao = cdUsuarioAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NRdiaSemana == null) ? 0 : NRdiaSemana.hashCode());
		result = prime * result + ((NRmaxAgendamento == null) ? 0 : NRmaxAgendamento.hashCode());
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((bloquearAgendamentoWeb == null) ? 0 : bloquearAgendamentoWeb.hashCode());
		result = prime * result + ((cdUsuarioAlteracao == null) ? 0 : cdUsuarioAlteracao.hashCode());
		result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((hrFinal == null) ? 0 : hrFinal.hashCode());
		result = prime * result + ((hrInicial == null) ? 0 : hrInicial.hashCode());
		result = prime * result + ((hrTempoMedio == null) ? 0 : hrTempoMedio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((permiteAgendamentoRecepcao == null) ? 0 : permiteAgendamentoRecepcao.hashCode());
		result = prime * result + ((permiteEncaixe == null) ? 0 : permiteEncaixe.hashCode());
		result = prime * result + ((snAgendamentoVolta == null) ? 0 : snAgendamentoVolta.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((unidadeAtendimento == null) ? 0 : unidadeAtendimento.hashCode());
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
		EscalaCentral other = (EscalaCentral) obj;
		if (NRdiaSemana == null) {
			if (other.NRdiaSemana != null)
				return false;
		} else if (!NRdiaSemana.equals(other.NRdiaSemana))
			return false;
		if (NRmaxAgendamento == null) {
			if (other.NRmaxAgendamento != null)
				return false;
		} else if (!NRmaxAgendamento.equals(other.NRmaxAgendamento))
			return false;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (bloquearAgendamentoWeb == null) {
			if (other.bloquearAgendamentoWeb != null)
				return false;
		} else if (!bloquearAgendamentoWeb.equals(other.bloquearAgendamentoWeb))
			return false;
		if (cdUsuarioAlteracao == null) {
			if (other.cdUsuarioAlteracao != null)
				return false;
		} else if (!cdUsuarioAlteracao.equals(other.cdUsuarioAlteracao))
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
		if (hrFinal == null) {
			if (other.hrFinal != null)
				return false;
		} else if (!hrFinal.equals(other.hrFinal))
			return false;
		if (hrInicial == null) {
			if (other.hrInicial != null)
				return false;
		} else if (!hrInicial.equals(other.hrInicial))
			return false;
		if (hrTempoMedio == null) {
			if (other.hrTempoMedio != null)
				return false;
		} else if (!hrTempoMedio.equals(other.hrTempoMedio))
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
		if (permiteEncaixe == null) {
			if (other.permiteEncaixe != null)
				return false;
		} else if (!permiteEncaixe.equals(other.permiteEncaixe))
			return false;
		if (snAgendamentoVolta == null) {
			if (other.snAgendamentoVolta != null)
				return false;
		} else if (!snAgendamentoVolta.equals(other.snAgendamentoVolta))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (unidadeAtendimento == null) {
			if (other.unidadeAtendimento != null)
				return false;
		} else if (!unidadeAtendimento.equals(other.unidadeAtendimento))
			return false;
		return true;
	}
	
	

	
	
	
}
