package br.gov.pe.sismepe.domain.enums;

public enum HomologacaoEnum {

    INTEGRALMENTE(1, "Homologada Integralmente"),
    REJEITADO(2, "Rejeitado"),
    RETIFICACAO(3, "Homologada com Retificação"),
    SOBPENDENCIA(4, "Sob Pendência"),
    VERIFICANDO(5, "Verificando"),
    VERIFICANDO_SOBPENDENCIA(6, "Verificando/Sob pendência");

    private Integer codigo;
    private String descricao;

    HomologacaoEnum(Integer codigo, String descricao) {
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
