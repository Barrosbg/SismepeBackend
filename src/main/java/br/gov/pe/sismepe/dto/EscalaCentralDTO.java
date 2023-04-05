package br.gov.pe.sismepe.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.EscalaCentral;
import br.gov.pe.sismepe.domain.Perfil;

public class EscalaCentralDTO {


	private Integer id;
	

	private String tipo;

	private Integer unidadeAtendimento;
	
	private Integer NRdiaSemana;
	
	
	private Integer NRmaxAgendamento;
	
	
	private Date hrInicial;
	
	
	private Date hrFinal;
	
	
	private Date hrTempoMedio;
	
	
	private String permiteEncaixe;
	
	
	private String permiteAgendamentoRecepcao;
	
	private String bloquearAgendamentoWeb;
	
	
	private String snAgendamentoVolta;
	
	
	private String ativo;
	

	private Date dataAlteracao;
	

	private Date dataInclusao;
	
	private Integer cdUsuarioAlteracao;
	

	
	public EscalaCentralDTO() {
	}

	public EscalaCentralDTO(Integer id, String tipo, Integer unidadeAtendimento) {
		super();
		this.id = id;
		this.tipo = tipo;
	}
	
	
	
	

	public EscalaCentralDTO(Integer id, String tipo, Integer unidadeAtendimento, Integer nRdiaSemana,
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
		EscalaCentralDTO other = (EscalaCentralDTO) obj;
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
