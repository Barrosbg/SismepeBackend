package br.gov.pe.sismepe.domain;

import javax.persistence.*;

@Entity
@Table(name = "unidade_militar")
public class UnidadeMilitar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CD_JMS")
    private Long cdJms;

    @Column(name = "CD_OME")
    private Long cdOme;

    public UnidadeMilitar() {
    }

    public UnidadeMilitar(Long cdJms, Long cdOme) {
        this.cdJms = cdJms;
        this.cdOme = cdOme;
    }

    public Long getCdJms() {
        return cdJms;
    }

    public void setCdJms(Long cdJms) {
        this.cdJms = cdJms;
    }

    public Long getCdOme() {
        return cdOme;
    }

    public void setCdOme(Long cdOme) {
        this.cdOme = cdOme;
    }
}
