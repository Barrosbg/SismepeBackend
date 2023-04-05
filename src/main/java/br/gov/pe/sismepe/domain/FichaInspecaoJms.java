package br.gov.pe.sismepe.domain;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table (name = "jms_ficha_inspecao")
@Entity
public class FichaInspecaoJms {
	
	
	@Id
	@Column(name = "CD_FICHA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JoinColumn(name = "CD_PESSOA")
    @OneToOne
	@JsonIgnoreProperties(value = {"usuarioCadastro"})
    private PessoaTitular pessoaTitular;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CD_USUARIO_CADASTRO")
	private Usuario usuarioCadastro;
	
	@Column(name="NR_REGISTRO")
	private String numeroRegistro;
	
	@NotNull
	@Column(name = "VL_PESO")
	private double peso;
	
	@NotNull
	@Column(name = "VL_ALTURA")
	private double altura;

	@NotNull
	@Column(name = "VL_IMC")
	private double imc;
	
	@Size(max=1)
	@Column(name="SN_EXAME_SANG_ULTIMOS_6_MESES")
	private String fezExameDeSangueNosUltimos6meses;
	
	@Size(max=1)
	@Column(name="SN_CIRURG_ULTIMOS_12_MESES")
	private String fezCirurgiaNosUltimos12meses;
	
	@Size(max=1)
	@Column(name="SN_INTERNACAO_ULTIMOS_12_MESES")
	private String esteveInternadoNosUltimos12meses;
	
	@Size(max=1)
	@Column(name="SN_EXAME_CORACAO_ULTIMOS_12_MESES")
	private String fezExameDoCoracaoNosUltimos12meses;
	
	@Size(max=1)
	@Column(name="SN_MEDICACAO_CONTINUA")
	private String fazUsoDeMedicacaoContinua;
	
	@Size(max=300)
	@Column(name="DS_MEDICACAO_CONTINUA")
	private String descricaoMedicacaoContinua;
	
	@Size(max=1)
	@Column(name="SN_MOMENTO_LICENCA_OU_DISPENSA")
	private String possuiLicencaOudispensaAtualmente;
	
	@Size(max=300)
	@Column(name="DS_TEMPO_LICENCA_OU_DISPENSA")
	private String descricaoTempoLicencaDispensa;
	
	@Size(max=1)
	@Column(name="SN_OUTRO_PROB_SAUDE")
	private String possuiOutroProblemaDeSaude;
	
	@Size(max=300)
	@Column(name="DS_OUTRO_PROB_SAUDE")
	private String descricaoOutroProblemaSaude;
	
	@Size(max=1)
	@Column(name="SN_PRATICA_ATIVIDADE_FISICA")
	private String praticaAtividadeFisica;
	
	@Size(max=1)
	@Column(name="SN_APTO_INGRESSO")
	private String aptoParaIngresso;
	
	@Size(max=1)
	@Column(name="SN_ATIVO")
	private String ativo;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name="DT_CADASTRO")
	private Date dataCadastro;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name="DT_ATUALIZACAO")
	private Date dataAtualizacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PessoaTitular getPessoaTitular() {
		return pessoaTitular;
	}

	public void setPessoaTitular(PessoaTitular pessoaTitular) {
		this.pessoaTitular = pessoaTitular;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getImc() {
		return imc;
	}

	public void setImc(double imc) {
		this.imc = imc;
	}

	public String getFezExameDeSangueNosUltimos6meses() {
		return fezExameDeSangueNosUltimos6meses;
	}

	public void setFezExameDeSangueNosUltimos6meses(String fezExameDeSangueNosUltimos6meses) {
		this.fezExameDeSangueNosUltimos6meses = fezExameDeSangueNosUltimos6meses;
	}

	public String getEsteveInternadoNosUltimos12meses() {
		return esteveInternadoNosUltimos12meses;
	}

	public void setEsteveInternadoNosUltimos12meses(String esteveInternadoNosUltimos12meses) {
		this.esteveInternadoNosUltimos12meses = esteveInternadoNosUltimos12meses;
	}

	public String getFezExameDoCoracaoNosUltimos12meses() {
		return fezExameDoCoracaoNosUltimos12meses;
	}

	public void setFezExameDoCoracaoNosUltimos12meses(String fezExameDoCoracaoNosUltimos12meses) {
		this.fezExameDoCoracaoNosUltimos12meses = fezExameDoCoracaoNosUltimos12meses;
	}

	public String getFazUsoDeMedicacaoContinua() {
		return fazUsoDeMedicacaoContinua;
	}

	public void setFazUsoDeMedicacaoContinua(String fazUsoDeMedicacaoContinua) {
		this.fazUsoDeMedicacaoContinua = fazUsoDeMedicacaoContinua;
	}

	public String getDescricaoMedicacaoContinua() {
		return descricaoMedicacaoContinua;
	}

	public void setDescricaoMedicacaoContinua(String descricaoMedicacaoContinua) {
		this.descricaoMedicacaoContinua = descricaoMedicacaoContinua;
	}

	public String getPossuiLicencaOudispensaAtualmente() {
		return possuiLicencaOudispensaAtualmente;
	}

	public void setPossuiLicencaOudispensaAtualmente(String possuiLicencaOudispensaAtualmente) {
		this.possuiLicencaOudispensaAtualmente = possuiLicencaOudispensaAtualmente;
	}

	public String getDescricaoTempoLicencaDispensa() {
		return descricaoTempoLicencaDispensa;
	}

	public void setDescricaoTempoLicencaDispensa(String descricaoTempoLicencaDispensa) {
		this.descricaoTempoLicencaDispensa = descricaoTempoLicencaDispensa;
	}

	public String getPossuiOutroProblemaDeSaude() {
		return possuiOutroProblemaDeSaude;
	}

	public void setPossuiOutroProblemaDeSaude(String possuiOutroProblemaDeSaude) {
		this.possuiOutroProblemaDeSaude = possuiOutroProblemaDeSaude;
	}

	public String getDescricaoOutroProblemaSaude() {
		return descricaoOutroProblemaSaude;
	}

	public void setDescricaoOutroProblemaSaude(String descricaoOutroProblemaSaude) {
		this.descricaoOutroProblemaSaude = descricaoOutroProblemaSaude;
	}

	public String getPraticaAtividadeFisica() {
		return praticaAtividadeFisica;
	}

	public void setPraticaAtividadeFisica(String praticaAtividadeFisica) {
		this.praticaAtividadeFisica = praticaAtividadeFisica;
	}

	public String getAptoParaIngresso() {
		return aptoParaIngresso;
	}

	public void setAptoParaIngresso(String aptoParaIngresso) {
		this.aptoParaIngresso = aptoParaIngresso;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getFezCirurgiaNosUltimos12meses() {
		return fezCirurgiaNosUltimos12meses;
	}

	public void setFezCirurgiaNosUltimos12meses(String fezCirurgiaNosUltimos12meses) {
		this.fezCirurgiaNosUltimos12meses = fezCirurgiaNosUltimos12meses;
	}

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}
	
	@JsonIgnore	
	public String getFezExameDeSangueNosUltimos6mesesFormat() {
		if(fezExameDeSangueNosUltimos6meses == null) {
			return "";
		} else if (fezExameDeSangueNosUltimos6meses.equals("S")) {
			return "SIM";
		} else {
			return "NÃO";
		}
	}

	@JsonIgnore
	public String getEsteveInternadoNosUltimos12mesesFormat() {
		if(esteveInternadoNosUltimos12meses == null) {
			return "";
		} else if (esteveInternadoNosUltimos12meses.equals("S")) {
			return "SIM";
		} else {
			return "NÃO";
		}
	}

	@JsonIgnore
	public String getFezExameDoCoracaoNosUltimos12mesesFormat() {
		if(fezExameDoCoracaoNosUltimos12meses == null) {
			return "";
		} else if (fezExameDoCoracaoNosUltimos12meses.equals("S")) {
			return "SIM";
		} else {
			return "NÃO";
		}
	}

	@JsonIgnore
	public String getFazUsoDeMedicacaoContinuaFormat() {
		if(fazUsoDeMedicacaoContinua == null) {
			return "";
		} else if (fazUsoDeMedicacaoContinua.equals("S")) {
			return "SIM";
		} else {
			return "NÃO";
		}
	}

	@JsonIgnore
	public String getPossuiLicencaOudispensaAtualmenteFormat() {
		if(possuiLicencaOudispensaAtualmente == null) {
			return "";
		} else if (possuiLicencaOudispensaAtualmente.equals("S")) {
			return "SIM";
		} else {
			return "NÃO";
		}
	}

	@JsonIgnore
	public String getPossuiOutroProblemaDeSaudeFormat() {
		if(possuiOutroProblemaDeSaude == null) {
			return "";
		} else if (possuiOutroProblemaDeSaude.equals("S")) {
			return "SIM";
		} else {
			return "NÃO";
		}
	}

	@JsonIgnore
	public String getPraticaAtividadeFisicaFormat() {
		if(praticaAtividadeFisica == null) {
			return "";
		} else if (praticaAtividadeFisica.equals("S")) {
			return "SIM";
		} else {
			return "NÃO";
		}
	}

	@JsonIgnore
	public String getAptoParaIngressoFormat() {
		if(aptoParaIngresso == null) {
			return "";
		} else if (aptoParaIngresso.equals("S")) {
			return "SIM";
		} else {
			return "NÃO";
		}
	}

	@JsonIgnore
	public String getFezCirurgiaNosUltimos12mesesFormat() {
		if(fezCirurgiaNosUltimos12meses == null) {
			return "";
		} else if (fezCirurgiaNosUltimos12meses.equals("S")) {
			return "SIM";
		} else {
			return "NÃO";
		}
	}
	
	@JsonIgnore
	public String getDescricaoTempoLicencaDispensaFormat() {
		if(descricaoTempoLicencaDispensa == null) {
			return "";
		} else {
			return descricaoTempoLicencaDispensa;
		}
	}

	@JsonIgnore
	public String getDescricaoOutroProblemaSaudeFormat() {
		if(descricaoOutroProblemaSaude == null) {
			return "";
		} else {
			return descricaoOutroProblemaSaude;
		}
	}

	@JsonIgnore
	public String getDescricaoMedicacaoContinuaFormat() {
		if(descricaoMedicacaoContinua == null) {
			return "";
		} else {
			return descricaoMedicacaoContinua;
		}
	}
}
