package br.gov.pe.sismepe.dto.jms;

import java.util.*;

public class RelatorioGerencialLicencaDTO {
    private String labelColunaDescricao;
    private String labelCol1;
    private String labelCol2;

    // Estatisticas por sexo
    private Long feminino = 0L;
    private Long masculino = 0L;

    // Estatisticas por idade
    private Long abaixoDe19 = 0L;
    private Long entre19e30 = 0L;
    private Long entre31e40 = 0L;
    private Long entre41e50 = 0L;
    private Long acimaDe50 = 0L;
    private Long naoInformado = 0L;

    private Map<String, List<Long>> agrupado;

    private List<GenericItemRelatorioGerencialDTO> lista;

    private Long totalLicencas = 0L;

    public RelatorioGerencialLicencaDTO() {
    }

    public Long getFeminino() {
        return feminino;
    }

    public void setFeminino(Long feminino) {
        this.feminino = feminino;
    }

    public Long getMasculino() {
        return masculino;
    }

    public void setMasculino(Long masculino) {
        this.masculino = masculino;
    }

    public Long getabaixoDe19() {
        return abaixoDe19;
    }

    public void setabaixoDe19(Long abaixoDe19) {
        this.abaixoDe19 = abaixoDe19;
    }

    public Long getEntre19e30() {
        return entre19e30;
    }

    public void setEntre19e30(Long entre19e30) {
        this.entre19e30 = entre19e30;
    }

    public Long getentre31e40() {
        return entre31e40;
    }

    public void setentre31e40(Long entre31e40) {
        this.entre31e40 = entre31e40;
    }

    public Long getEntre41e50() {
        return entre41e50;
    }

    public void setEntre41e50(Long entre41e50) {
        this.entre41e50 = entre41e50;
    }

    public Long getAcimaDe50() {
        return acimaDe50;
    }

    public void setAcimaDe50(Long acimaDe50) {
        this.acimaDe50 = acimaDe50;
    }

    public Long getNaoInformado() {
        return naoInformado;
    }

    public void setNaoInformado(Long naoInformado) {
        this.naoInformado = naoInformado;
    }

    public Long getTotalLicencas() {
        return totalLicencas;
    }

    public void setTotalLicencas(Long totalLicencas) {
        this.totalLicencas = totalLicencas;
    }

    public Map<String, List<Long>> getAgrupado() {
        return agrupado;
    }

    public void setAgrupado(Map<String, List<Long>> agrupado) {
        this.agrupado = agrupado;
    }

    public List<GenericItemRelatorioGerencialDTO> getLista() {
        return lista;
    }

    public void setLista(List<GenericItemRelatorioGerencialDTO> lista) {
        this.lista = lista;
    }

    public String getLabelColunaDescricao() {
        return labelColunaDescricao;
    }

    public void setLabelColunaDescricao(String labelColunaDescricao) {
        this.labelColunaDescricao = labelColunaDescricao;
    }

    public String getLabelCol1() {
        return labelCol1;
    }

    public void setLabelCol1(String labelCol1) {
        this.labelCol1 = labelCol1;
    }

    public String getLabelCol2() {
        return labelCol2;
    }

    public void setLabelCol2(String labelCol2) {
        this.labelCol2 = labelCol2;
    }

    @Override
    public String toString() {
        return "RelatorioGerencialLicencaDTO{" +
                ", feminino=" + feminino +
                ", masculino=" + masculino +
                ", abaixoDe19=" + abaixoDe19 +
                ", entre19e30=" + entre19e30 +
                ", entre31e40=" + entre31e40 +
                ", entre41e50=" + entre41e50 +
                ", acimaDe50=" + acimaDe50 +
                ", naoInformado=" + naoInformado +
                '}';
    }
}
