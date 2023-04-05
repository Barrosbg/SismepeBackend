package br.gov.pe.sismepe.domain.enums;

public enum CorporacaoEnum {
    PM(6, "PM"),
    BM(40, "BM");

    private Integer codigo;
    private String descricao;

    CorporacaoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
