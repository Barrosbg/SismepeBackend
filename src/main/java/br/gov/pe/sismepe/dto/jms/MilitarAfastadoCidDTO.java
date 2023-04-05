package br.gov.pe.sismepe.dto.jms;

import java.util.Date;

public class MilitarAfastadoCidDTO {
    private Integer qtdDias;
    private Date dataFinal;
    private Integer matricula;
    private String nome;
    private String batalhao;
    private String cid;

    public MilitarAfastadoCidDTO() {
    }

    public MilitarAfastadoCidDTO(Integer qtdDias, Date dataFinal, Integer matricula, String nome, String batalhao,
                                 String cid) {
        this.qtdDias = qtdDias;
        this.dataFinal = dataFinal;
        this.matricula = matricula;
        this.nome = nome;
        this.batalhao = batalhao;
        this.cid = cid;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
