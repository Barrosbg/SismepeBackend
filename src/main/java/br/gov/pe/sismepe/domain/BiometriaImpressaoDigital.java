package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "biometria_impressao_digital")
//@IdClass(BiometriaImpressaoDigitalId.class)
public class BiometriaImpressaoDigital implements Serializable {
	private static final long serialVersionUID = 7782331600296727383L;

	@Id
	@Column(name="CD_BIOMETRIA_IMPRESSAO_DIGITAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="CD_IDENTIFICADOR_DEDO")
	private Integer cdIdentificadorDedo;
	
	@Column(name="DS_BIOMETRIA_HASH")
	private String dsBiometriaHash;
	
	@Column(name="DT_CADASTRO")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dtCadastro;
	
	@Column(name="CD_USUARIO")
	private Integer cdUsuario;
	
	@Column(name="SN_ATIVO")
	private String snAtivo;
	
	@ManyToOne
	@JoinColumn(name="CD_BIOMETRIA")
	@JsonBackReference
	private Biometria biometria;

	public BiometriaImpressaoDigital() {
		
	}
	
	public BiometriaImpressaoDigital(Integer cdIdentificadorDedo, String dsBiometriaHash,
			Date dtCadastro, Integer cdUsuario, String snAtivo, Biometria biometria) {
		super();
		this.cdIdentificadorDedo = cdIdentificadorDedo;
		this.dsBiometriaHash = dsBiometriaHash;
		this.dtCadastro = dtCadastro;
		this.cdUsuario = cdUsuario;
		this.snAtivo = snAtivo;
		this.biometria = biometria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCdIdentificadorDedo() {
		return cdIdentificadorDedo;
	}

	public void setCdIdentificadorDedo(Integer cdIdentificadorDedo) {
		this.cdIdentificadorDedo = cdIdentificadorDedo;
	}

	public String getDsBiometriaHash() {
		return dsBiometriaHash;
	}

	public void setDsBiometriaHash(String dsBiometriaHash) {
		this.dsBiometriaHash = dsBiometriaHash;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Integer getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(Integer cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public String getSnAtivo() {
		return snAtivo;
	}

	public void setSnAtivo(String snAtivo) {
		this.snAtivo = snAtivo;
	}

	public Biometria getBiometria() {
		return biometria;
	}

	public void setBiometria(Biometria biometria) {
		this.biometria = biometria;
	}

	@Override
	public String toString() {
		return "BiometriaImpressaoDigital [cdIdentificadorDedo=" + cdIdentificadorDedo + ", dsBiometriaHash="
				+ dsBiometriaHash + ", dtCadastro=" + dtCadastro + ", cdUsuario=" + cdUsuario + ", snAtivo=" + snAtivo
				+ ", biometria=" + biometria + "]";
	}
	
}
