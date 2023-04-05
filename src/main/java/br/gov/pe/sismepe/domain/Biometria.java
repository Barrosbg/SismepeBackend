package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "BIOMETRIA")
public class Biometria implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_BIOMETRIA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "CD_PESSOA")
	private Integer cdPessoa;
	
	@Column(name = "DT_CADASTRO", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCadastro;
	
	@OneToMany(mappedBy = "biometria", fetch = FetchType.LAZY)
	private List<BiometriaImpressaoDigital> digitais;
	
	public Biometria() {
		
	}

	public Biometria(Integer id, Integer cdPessoa, Date dataCadastro) {
		super();
		this.id = id;
		this.cdPessoa = cdPessoa;
		this.dataCadastro = dataCadastro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCdPessoa() {
		return cdPessoa;
	}

	public void setCdPessoa(Integer cdPessoa) {
		this.cdPessoa = cdPessoa;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<BiometriaImpressaoDigital> getDigitais() {
		return digitais;
	}

	public void setDigitais(List<BiometriaImpressaoDigital> digitais) {
		this.digitais = digitais;
	}

	@Override
	public String toString() {
		return "Biometria [id=" + id + ", cdPessoa=" + cdPessoa + ", dataCadastro=" + dataCadastro + ", digitais="
				+ digitais + "]";
	}
	
}
