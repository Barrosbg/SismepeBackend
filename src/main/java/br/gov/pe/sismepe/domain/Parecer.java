package br.gov.pe.sismepe.domain;

import javax.persistence.*;

@Entity
@Table(name = "PARECER")
public class Parecer {
    @Id
    @Column(name = "CD_PARECER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DS_PARECER")
    private String parecer;

    @Column(name = "TP_PARECER")
    private Integer tipoParecer;

    @Column(name = "SN_ATIVO")
    private String ativo;

    public Parecer() {
    }

    public Parecer(Long id, String parecer, Integer tipoParecer, String ativo) {
        this.id = id;
        this.parecer = parecer;
        this.tipoParecer = tipoParecer;
        this.ativo = ativo;
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

    public Integer getTipoParecer() {
        return tipoParecer;
    }

    public void setTipoParecer(Integer tipoParecer) {
        this.tipoParecer = tipoParecer;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}
