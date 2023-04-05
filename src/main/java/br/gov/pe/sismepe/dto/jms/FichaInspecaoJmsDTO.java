package br.gov.pe.sismepe.dto.jms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.gov.pe.sismepe.domain.Comorbidade;
import br.gov.pe.sismepe.domain.FichaInspecaoJms;
import br.gov.pe.sismepe.domain.PessoaTitular;
import br.gov.pe.sismepe.domain.Usuario;


public class FichaInspecaoJmsDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;	
	private String numeroRegistro;
    private PessoaTitular pessoaTitular;	
	private Usuario usuarioCadastro;	
	private double peso;	
	private double altura;
	private double imc;	
	private String fezExameDeSangueNosUltimos6meses;	
	private String esteveInternadoNosUltimos12meses;
	private String fezAlgumaCirurgiaNosUltimos12meses;
	private String fezExameDoCoracaoNosUltimos12meses;
	private String fazUsoDeMedicacaoContinua;
	private String descricaoMedicacaoContinua;	
	private String possuiLicencaOudispensaAtualmente;
	private String descricaoTempoLicencaDispensa;	
	private String possuiOutroProblemaDeSaude;	
	private String descricaoOutroProblemaSaude;
	private String praticaAtividadeFisica;	
	private String aptoParaIngresso;	
	private String ativo;	
	private Date dataCadastro;	
	private Date dataAtualizacao;
	private List<Comorbidade> comorbidades;
	
	public FichaInspecaoJms toModel() {
		FichaInspecaoJms ficha = new FichaInspecaoJms();
		ficha.setId(this.id);	
		ficha.setNumeroRegistro(this.numeroRegistro);
		ficha.setPessoaTitular(this.pessoaTitular);
		ficha.setUsuarioCadastro(this.usuarioCadastro);	
		ficha.setPeso(this.peso);	
		ficha.setAltura(this.altura);
		ficha.setImc(this.imc);
		ficha.setFezExameDeSangueNosUltimos6meses(this.fezExameDeSangueNosUltimos6meses);
		ficha.setFezCirurgiaNosUltimos12meses(this.fezAlgumaCirurgiaNosUltimos12meses);
		ficha.setEsteveInternadoNosUltimos12meses(this.esteveInternadoNosUltimos12meses);
		ficha.setFezCirurgiaNosUltimos12meses(this.fezAlgumaCirurgiaNosUltimos12meses);
		ficha.setFezExameDoCoracaoNosUltimos12meses(this.fezExameDoCoracaoNosUltimos12meses);
		ficha.setFazUsoDeMedicacaoContinua(this.fazUsoDeMedicacaoContinua);
		ficha.setDescricaoMedicacaoContinua(this.descricaoMedicacaoContinua);	
		ficha.setPossuiLicencaOudispensaAtualmente(this.possuiLicencaOudispensaAtualmente);
		ficha.setDescricaoTempoLicencaDispensa(this.descricaoTempoLicencaDispensa);	
		ficha.setPossuiOutroProblemaDeSaude(this.possuiOutroProblemaDeSaude);	
		ficha.setDescricaoOutroProblemaSaude(this.descricaoOutroProblemaSaude);
		ficha.setPraticaAtividadeFisica(this.praticaAtividadeFisica);	
		ficha.setAptoParaIngresso(this.aptoParaIngresso);
		ficha.setAtivo(this.ativo);
		ficha.setDataCadastro(this.dataCadastro);	
		ficha.setDataAtualizacao(this.dataAtualizacao); 
		return ficha;
	}

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
	public String getFezAlgumaCirurgiaNosUltimos12meses() {
		return fezAlgumaCirurgiaNosUltimos12meses;
	}

	public void setFezAlgumaCirurgiaNosUltimos12meses(String fezAlgumaCirurgiaNosUltimos12meses) {
		this.fezAlgumaCirurgiaNosUltimos12meses = fezAlgumaCirurgiaNosUltimos12meses;
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

	public List<Comorbidade> getComorbidades() {
		return comorbidades;
	}

	public void setComorbidades(List<Comorbidade> comorbidades) {
		this.comorbidades = comorbidades;
	}
	
	
	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	@Override
	public String toString() {
		return "FichaInspecaoJmsDTO [id=" + id + ", pessoaTitular=" + pessoaTitular + ", usuarioCadastro="
				+ usuarioCadastro + ", peso=" + peso + ", altura=" + altura + ", imc=" + imc
				+ ", fezExameDeSangueNosUltimos6meses=" + fezExameDeSangueNosUltimos6meses
				+ ", esteveInternadoNosUltimos12meses=" + esteveInternadoNosUltimos12meses
				+", fezAlgumaCirurgiaNosUltimos12meses ="+ fezAlgumaCirurgiaNosUltimos12meses
				+ ", fezExameDoCoracaoNosUltimos12meses=" + fezExameDoCoracaoNosUltimos12meses
				+ ", fazUsoDeMedicacaoContinua=" + fazUsoDeMedicacaoContinua + ", descricaoMedicacaoContinua="
				+ descricaoMedicacaoContinua + ", possuiLicencaOudispensaAtualmente="
				+ possuiLicencaOudispensaAtualmente + ", descricaoTempoLicencaDispensa=" + descricaoTempoLicencaDispensa
				+ ", possuiOutroProblemaDeSaude=" + possuiOutroProblemaDeSaude + ", descricaoOutroProblemaSaude="
				+ descricaoOutroProblemaSaude + ", praticaAtividadeFisica=" + praticaAtividadeFisica
				+ ", aptoParaIngresso=" + aptoParaIngresso + ", ativo=" + ativo + ", dataCadastro=" + dataCadastro
				+ ", dataAtualizacao=" + dataAtualizacao + ", comorbidades=" + comorbidades + "]";
	}
		
	
	
}
