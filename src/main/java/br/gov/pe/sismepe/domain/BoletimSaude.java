package br.gov.pe.sismepe.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "boletim_saude")
public class BoletimSaude {
    @Id
    @Column(name = "CD_BOLETIM_SAUDE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NR_BOLETIM_SAUDE")
    private Integer numero;

    @Column(name = "CD_USUARIO")
    private Usuario usuario;

    @Column(name = "DT_CADASTRO")
    private Date dataCadastro;

    @Column(name = "DS_PARTE1")
    private String parte1;

    @Column(name = "DS_PARTE2")
    private String parte2;

    @Column(name = "DS_PARTE3")
    private String parte3;

    @Column(name = "DS_PARTE4")
    private String parte4;

    @Column(name = "SN_ATIVO")
    private String ativo;

    public BoletimSaude() {
    }

    public BoletimSaude(Long id, Integer numero, Usuario usuario, Date dataCadastro, String parte1, String parte2, String parte3, String parte4, String ativo) {
        this.id = id;
        this.numero = numero;
        this.usuario = usuario;
        this.dataCadastro = dataCadastro;
        this.parte1 = parte1;
        this.parte2 = parte2;
        this.parte3 = parte3;
        this.parte4 = parte4;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getParte1() {
        return parte1;
    }

    public void setParte1(String parte1) {
        this.parte1 = parte1;
    }

    public String getParte2() {
        return parte2;
    }

    public void setParte2(String parte2) {
        this.parte2 = parte2;
    }

    public String getParte3() {
        return parte3;
    }

    public void setParte3(String parte3) {
        this.parte3 = parte3;
    }

    public String getParte4() {
        return parte4;
    }

    public void setParte4(String parte4) {
        this.parte4 = parte4;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}
