package br.gov.pe.sismepe.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CID")
public class Cid {
    @Id
    @Column(name = "CD_CID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "TP_CLASSIFICACAO")
    private String tipoClassificacao;

    @Column(name = "TP_SEXO")
    private String sexo;

    @Column(name = "DS_CID")
    private String descricao;

    @Column(name = "DS_CID_ABREV")
    private String abreviacao;

    @Column(name = "SN_ATIVO")
    private String ativo;

    @JsonBackReference
    @ManyToMany(mappedBy = "cids")
    private List<Licenca> licencas = new ArrayList<>();

    public Cid() {
    }

    public Cid(String id, String tipoClassificacao, String sexo, String descricao, String abreviacao, String ativo, List<Licenca> licencas) {
        this.id = id;
        this.tipoClassificacao = tipoClassificacao;
        this.sexo = sexo;
        this.descricao = descricao;
        this.abreviacao = abreviacao;
        this.ativo = ativo;
        this.licencas = licencas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoClassificacao() {
        return tipoClassificacao;
    }

    public void setTipoClassificacao(String tipoClassificacao) {
        this.tipoClassificacao = tipoClassificacao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public List<Licenca> getLicencas() {
        return licencas;
    }

    public void setLicencas(List<Licenca> licencas) {
        this.licencas = licencas;
    }
}
