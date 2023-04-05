package br.gov.pe.sismepe.dto.jms;

import java.util.Date;

public class LicencasDiasIninterruptosDTO {
    private Integer matricula;
    private String nome;
    private String batalhao;
    private Date dataInicial;
    private Date dataFinal;
    private Integer qtdDias;

    public LicencasDiasIninterruptosDTO() {
    }

    public LicencasDiasIninterruptosDTO(Integer matricula, String nome, String batalhao, Date dataInicial, Date dataFinal, Integer qtdDias) {
        this.matricula = matricula;
        this.nome = nome;
        this.batalhao = batalhao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.qtdDias = qtdDias;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBatalhao() {
        return batalhao;
    }

    public void setBatalhao(String batalhao) {
        this.batalhao = batalhao;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }
}
