package br.gov.pe.sismepe.dto.jms;

import java.util.Date;

public class LicencaMilitarPorPrestadorDTO {
    private Long codigo;
    private String conselho;
    private String nomePrestador;
    private Integer matriculaPaciente;
    private String nomePaciente;
    private Date dataInicio;
    private Integer qtdDias;

    public LicencaMilitarPorPrestadorDTO() {
    }

    public LicencaMilitarPorPrestadorDTO(Long codigo, String conselho, String nomePrestador, Integer matriculaPaciente, String nomePaciente, Date dataInicio, Integer qtdDias) {
        this.codigo = codigo;
        this.conselho = conselho;
        this.nomePrestador = nomePrestador;
        this.matriculaPaciente = matriculaPaciente;
        this.nomePaciente = nomePaciente;
        this.dataInicio = dataInicio;
        this.qtdDias = qtdDias;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getConselho() {
        return conselho;
    }

    public void setConselho(String conselho) {
        this.conselho = conselho;
    }

    public String getNomePrestador() {
        return nomePrestador;
    }

    public void setNomePrestador(String nomePrestador) {
        this.nomePrestador = nomePrestador;
    }

    public Integer getMatriculaPaciente() {
        return matriculaPaciente;
    }

    public void setMatriculaPaciente(Integer matriculaPaciente) {
        this.matriculaPaciente = matriculaPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }
}
