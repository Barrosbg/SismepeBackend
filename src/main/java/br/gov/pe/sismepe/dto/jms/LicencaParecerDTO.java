package br.gov.pe.sismepe.dto.jms;

import java.util.Calendar;
import java.util.Date;

public class LicencaParecerDTO {
    private Long cdLicenca;
    private String nomePessoa;
    private String parecer;
    private Date dataInicio;
    private Date dataFim;
    private Date dataCadastro;
    private Integer qtdDias;

    public LicencaParecerDTO() {
    }

    public LicencaParecerDTO(Long cdLicenca, String nomePessoa, String parecer, Date dataInicio, Date dataCadastro, Integer qtdDias) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, qtdDias);

        this.cdLicenca = cdLicenca;
        this.nomePessoa = nomePessoa;
        this.parecer = parecer;
        this.dataInicio = dataInicio;
        this.dataFim = c.getTime();
        this.dataCadastro = dataCadastro;
        this.qtdDias = qtdDias;
    }

    public Long getCdLicenca() {
        return cdLicenca;
    }

    public void setCdLicenca(Long cdLicenca) {
        this.cdLicenca = cdLicenca;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }
}
