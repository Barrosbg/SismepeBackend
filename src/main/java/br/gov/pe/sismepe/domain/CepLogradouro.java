package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ceplogradourobrasil")
public class CepLogradouro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CHAVELOG")
	private  Long id;
	
	@Column(name="NOMELOG")
	private String nome;
	
	@OneToOne(optional = false)
    @JoinColumn(name = "CHAVEBAI", updatable = false, insertable = false)
	private CepBairro chaveLocalLog;
	
	
    @Column(name="CHAVEBAI")
	private Long chaveBairro1Log;
	
	@Column(name="CHVBAI2LOG")
	private Long chaveBairro2Log;
	
	@Column(name="CEP8LOG")
	private Long cepLog;
	
	@Column(name="UFLOG")
	private String ufLog;
	
	@Column(name="CHVTIPOLOG")
	private Long chaveTipoLog;
	
	
	public CepLogradouro() {
		
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CepBairro getChaveLocalLog() {
		return chaveLocalLog;
	}

	public void setChaveLocalLog(CepBairro chaveLocalLog) {
		this.chaveLocalLog = chaveLocalLog;
	}

	public Long getChaveBairro1Log() {
		return chaveBairro1Log;
	}

	public void setChaveBairro1Log(Long chaveBairro1Log) {
		this.chaveBairro1Log = chaveBairro1Log;
	}

	public Long getChaveBairro2Log() {
		return chaveBairro2Log;
	}

	public void setChaveBairro2Log(Long chaveBairro2Log) {
		this.chaveBairro2Log = chaveBairro2Log;
	}

	public Long getCepLog() {
		return cepLog;
	}

	public void setCepLog(Long cepLog) {
		this.cepLog = cepLog;
	}

	public String getUfLog() {
		return ufLog;
	}

	public void setUfLog(String ufLog) {
		this.ufLog = ufLog;
	}

	public Long getChaveTipoLog() {
		return chaveTipoLog;
	}

	public void setChaveTipoLog(Long chaveTipoLog) {
		this.chaveTipoLog = chaveTipoLog;
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
		CepLogradouro other = (CepLogradouro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CepLogradouro [id=" + id + ", nome=" + nome + ", chaveLocalLog=" + chaveLocalLog + ", chaveBairro1Log="
				+ chaveBairro1Log + ", chaveBairro2Log=" + chaveBairro2Log + ", cepLog=" + cepLog + ", ufLog=" + ufLog
				+ ", chaveTipoLog=" + chaveTipoLog + "]";
	}
	
	
	
	
	
	
	
}
