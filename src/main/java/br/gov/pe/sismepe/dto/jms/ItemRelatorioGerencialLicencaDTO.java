package br.gov.pe.sismepe.dto.jms;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.util.Date;

public class ItemRelatorioGerencialLicencaDTO {
    private Long id;
    private String parecer;
    private String sexoPessoa;
    private Date dataNascimento;
    private Date dataHomologacao;
    private Integer qtdDias;
    private String nomeMedico;
    private String omeTitular;
    private String nomeUsuarioCadastro;

    public ItemRelatorioGerencialLicencaDTO() {
    }

    public ItemRelatorioGerencialLicencaDTO(Long id, String parecer, String sexoPessoa, Date dataNascimento,
                                            Date dataHomologacao, Integer qtdDias, String nomeMedico, String omeTitular,
                                            String nomeUsuarioCadastro) {
        this.id = id;
        this.parecer = parecer;
        this.sexoPessoa = sexoPessoa;
        this.dataNascimento = dataNascimento;
        this.dataHomologacao = dataHomologacao;
        this.qtdDias = qtdDias;
        this.nomeMedico = nomeMedico;
        this.omeTitular = omeTitular;
        this.nomeUsuarioCadastro = nomeUsuarioCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public String getSexoPessoa() {
        return sexoPessoa;
    }

    public void setSexoPessoa(String sexoPessoa) {
        this.sexoPessoa = sexoPessoa;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataHomologacao() {
        return dataHomologacao;
    }

    public void setDataHomologacao(Date dataHomologacao) {
        this.dataHomologacao = dataHomologacao;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getOmeTitular() {
        return omeTitular;
    }

    public void setOmeTitular(String omeTitular) {
        this.omeTitular = omeTitular;
    }

    public String getNomeUsuarioCadastro() {
        return nomeUsuarioCadastro;
    }

    public void setNomeUsuarioCadastro(String nomeUsuarioCadastro) {
        this.nomeUsuarioCadastro = nomeUsuarioCadastro;
    }

    public int getIdadeAnos() {
        LocalDate dtNasc = LocalDate.fromDateFields(this.dataNascimento);
        LocalDate hoje = LocalDate.now();

        Period p = new Period(dtNasc, hoje);

        return p.getYears();
    }

    @Override
    public String toString() {
        return "ItemRelatorioGerencialLicencaDTO{" +
                "qtdLicenca=" + id +
                ", parecer='" + parecer + '\'' +
                ", sexoPessoa='" + sexoPessoa + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
