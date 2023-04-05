package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.AgendaCentralAmb;
import br.gov.pe.sismepe.domain.ItAgendaCentral;
import br.gov.pe.sismepe.domain.PessoaDependente;
import br.gov.pe.sismepe.domain.PessoaTitular;
import br.gov.pe.sismepe.services.validation.ItAgendaCentralUpdate;

@ItAgendaCentralUpdate
public class ItAgendaCentralDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataAgendamento;
	
	@JsonFormat(pattern = "HH:mm:ss")
	private Date horaInicial;
	
	private String situacaoAgendamento;
	
	private Long idPrestador;
	
	private String nomePrestador;
	
	private Long idEspecialidade;
	
	private String especialidade;
	
	private String permiteEncaixe;
		
	private Long idPaciente;
	
	private String nomePaciente;
	
	private String matriculaPaciente;
	
	private Integer situacaoAtendimento;
	
	private Long atendimentoId;
	
	public ItAgendaCentralDTO() {
	}
	
	public ItAgendaCentralDTO(ItAgendaCentral obj) {
		this.id = obj.getId();
		this.dataAgendamento = obj.getAgendaCentral().getDataAgendamento();
		this.permiteEncaixe = obj.getEncaixe();
		this.horaInicial = obj.getAgendaCentral().getHoraInicial();
		this.situacaoAgendamento = obj.getSituacao().getDescricao();
		if(obj.getAtendimento() != null) {
			this.situacaoAtendimento = obj.getAtendimento().getSituacao().getCodigo();
			this.atendimentoId = obj.getAtendimento().getId();
		}
		if(obj.getPaciente() != null) {
			this.idPaciente = obj.getPaciente().getId();
			this.nomePaciente = obj.getPaciente().getPessoa().getNome();
			if(obj.getPaciente().getPessoa().getClass().equals(PessoaTitular.class)) {
				PessoaTitular tit = ((PessoaTitular)obj.getPaciente().getPessoa());
				matriculaPaciente = tit.getMatricula()+"-"+tit.getDigito();
			}else {
				PessoaDependente dep = ((PessoaDependente)obj.getPaciente().getPessoa());
				matriculaPaciente = dep.getMatricula()+"/"+dep.getSequencial();
			}			
		}
		if(obj.getAgendaCentral().getTipo().equals("A")) {
			AgendaCentralAmb agendaAmb = (AgendaCentralAmb)obj.getAgendaCentral();
			this.idPrestador = agendaAmb.getPrestador().getId();
			this.nomePrestador = agendaAmb.getPrestador().getNome();
			this.idEspecialidade = agendaAmb.getEspecialidade().getId();
			this.especialidade = agendaAmb.getEspecialidade().getDescricao();
		}				
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public String getSituacaoAgendamento() {
		return situacaoAgendamento;
	}

	public void setSituacaoAgendamento(String situacaoAgendamento) {
		this.situacaoAgendamento = situacaoAgendamento;
	}
	
	public String getMatriculaPaciente() {
		return matriculaPaciente;
	}

	public void setMatriculaPaciente(String matriculaPaciente) {
		this.matriculaPaciente = matriculaPaciente;
	}
	
	public Long getIdPrestador() {
		return idPrestador;
	}

	public void setIdPrestador(Long idPrestador) {
		this.idPrestador = idPrestador;
	}

	public Long getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(Long idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	public String getNomePrestador() {
		return nomePrestador;
	}

	public void setNomePrestador(String nomePrestador) {
		this.nomePrestador = nomePrestador;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getPermiteEncaixe() {
		return permiteEncaixe;
	}

	public void setPermiteEncaixe(String permiteEncaixe) {
		this.permiteEncaixe = permiteEncaixe;
	}
	
	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public Integer getSituacaoAtendimento() {
		return situacaoAtendimento;
	}

	public void setSituacaoAtendimento(Integer situacaoAtendimento) {
		this.situacaoAtendimento = situacaoAtendimento;
	}

	public Long getAtendimentoId() {
		return atendimentoId;
	}

	public void setAtendimentoId(Long atendimentoId) {
		this.atendimentoId = atendimentoId;
	}
	
}
