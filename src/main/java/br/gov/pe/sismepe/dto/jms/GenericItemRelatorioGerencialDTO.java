package br.gov.pe.sismepe.dto.jms;

public class GenericItemRelatorioGerencialDTO implements Comparable<GenericItemRelatorioGerencialDTO> {
    private String col0;
    private Long col1;
    private Long col2;

    public GenericItemRelatorioGerencialDTO() {
    }

    public GenericItemRelatorioGerencialDTO(String col0, Long col1, Long col2) {
        this.col0 = col0;
        this.col1 = col1;
        this.col2 = col2;
    }

    public String getCol0() {
        return col0;
    }

    public void setCol0(String col0) {
        this.col0 = col0;
    }

    public Long getCol1() {
        return col1;
    }

    public void setCol1(Long col1) {
        this.col1 = col1;
    }

    public Long getCol2() {
        return col2;
    }

    public void setCol2(Long col2) {
        this.col2 = col2;
    }

    @Override
    public int compareTo(GenericItemRelatorioGerencialDTO outro) {
        return Long.compare(getCol1(), outro.getCol1());
    }
}
