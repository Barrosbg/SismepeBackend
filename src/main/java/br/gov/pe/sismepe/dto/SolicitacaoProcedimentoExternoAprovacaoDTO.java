package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.Cid;
import br.gov.pe.sismepe.domain.Ome;
import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.domain.Prestador;
import br.gov.pe.sismepe.domain.SolicitacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.enums.AcomodacaoSolicitacaoProcedimentoEnum;
import br.gov.pe.sismepe.domain.enums.CaraterSolicitacaoProcedimentoEnum;
import br.gov.pe.sismepe.domain.enums.ClassificacaoSolicitacaoProcedimentoEnum;
import br.gov.pe.sismepe.domain.enums.NaturezaSolicitacaoProcedimentoEnum;
import br.gov.pe.sismepe.domain.enums.RegimeSolicitacaoProcedimentoEnum;

public class SolicitacaoProcedimentoExternoAprovacaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull
	private Prestador prestadorSolicitante;
	
	@NotNull
	private Paciente paciente;
	
	List<AutorizacaoProcedimentoExternoDTO> itRealizados = new ArrayList<AutorizacaoProcedimentoExternoDTO>();
	
	private String carater;
	private String natureza;
	private String classificacao;
	private String regimeInterno;
	private String acomodacao;
	private String situacao;
	private String justificativa;
	private String observacao;
	private String observacaoRestrita;
	
	private SolicitacaoProcedimentoExterno solicitacaoPai;
	
	private Ome ome;
	private Cid cid;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataCadastro;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataAlteracao;
	
	private String ativo;
	
	
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

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<AutorizacaoProcedimentoExternoDTO> getItRealizados() {
		return itRealizados;
	}

	public void setItRealizados(List<AutorizacaoProcedimentoExternoDTO> itRealizados) {
		this.itRealizados = itRealizados;
	}


	public CaraterSolicitacaoProcedimentoEnum getCarater() {
		return CaraterSolicitacaoProcedimentoEnum.valueOf(carater);
	}

	public void setCarater(String carater) {
		this.carater = carater;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	public Ome getOme() {
		return ome;
	}

	public void setOme(Ome ome) {
		this.ome = ome;
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

	public NaturezaSolicitacaoProcedimentoEnum getNatureza() {
		return natureza != null ? NaturezaSolicitacaoProcedimentoEnum.valueOf(natureza) : null;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public ClassificacaoSolicitacaoProcedimentoEnum getClassificacao() {
		return classificacao != null ? ClassificacaoSolicitacaoProcedimentoEnum.valueOf(classificacao) : null;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public RegimeSolicitacaoProcedimentoEnum getRegimeInterno() {
		return regimeInterno != null ? RegimeSolicitacaoProcedimentoEnum.valueOf(regimeInterno) : null;
	}

	public void setRegimeInterno(String regimeInterno) {
		this.regimeInterno = regimeInterno;
	}

	public AcomodacaoSolicitacaoProcedimentoEnum getAcomodacao() {
		return acomodacao != null ? AcomodacaoSolicitacaoProcedimentoEnum.valueOf(acomodacao) : null;
	}

	public void setAcomodacao(String acomodacao) {
		this.acomodacao = acomodacao;
	}

	public SolicitacaoProcedimentoExterno getSolicitacaoPai() {
		return solicitacaoPai;
	}

	public void setSolicitacaoPai(SolicitacaoProcedimentoExterno solicitacaoPai) {
		this.solicitacaoPai = solicitacaoPai;
	}

	@Override
	public String toString() {
		return "SolicitacaoProcedimentoExternoAprovacaoDTO [prestadorSolicitante=" + prestadorSolicitante
				+ ", paciente=" + paciente + ", itRealizados=" + itRealizados + ", carater=" + carater + ", natureza="
				+ natureza + ", classificacao=" + classificacao + ", regimeInterno=" + regimeInterno + ", acomodacao="
				+ acomodacao + ", situacao=" + situacao + ", justificativa=" + justificativa + ", observacao="
				+ observacao + ", observacaoRestrita=" + observacaoRestrita + ", solicitacaoPai=" + solicitacaoPai
				+ ", ome=" + ome + ", cid=" + cid + ", dataCadastro=" + dataCadastro + ", dataAlteracao="
				+ dataAlteracao + ", ativo=" + ativo + "]";
	}

	
	
}
