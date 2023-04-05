package br.gov.pe.sismepe.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "OME")
public class Ome {
    @Id
    @Column(name = "CD_OME")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DS_OME")
    private String descricao;

    @Column(name = "DS_OME_ABREV")
    private String abreviacao;

    @Column(name = "DS_CEP")
    private String cep;

    @Column(name = "DS_LOGRADOURO")
    private String logradouro;

    @Column(name = "DS_BAIRRO")
    private String bairro;

    @Column(name = "CD_UF")
    private String uf;

    @Column(name = "DS_CIDADE")
    private String cidade;

    @Column(name = "TP_DIRETORIA")
    private String diretoria;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinTable(name = "unidade_militar",
            joinColumns = @JoinColumn(name = "CD_OME"),
            inverseJoinColumns = @JoinColumn(name = "CD_GRUPO_UNIDADE_MIL"))
    @JsonBackReference
    private GrupoUnidadeMilitar grupoUnidadeMilitar;

    public Ome() {
    }

    public Ome(Long id, String descricao, String abreviacao, String cep, String logradouro, String bairro, String uf, String cidade, String diretoria) {
        this.id = id;
        this.descricao = descricao;
        this.abreviacao = abreviacao;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.uf = uf;
        this.cidade = cidade;
        this.diretoria = diretoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getDiretoria() {
        return diretoria;
    }

    public void setDiretoria(String diretoria) {
        this.diretoria = diretoria;
    }

    public GrupoUnidadeMilitar getGrupoUnidadeMilitar() {
        return grupoUnidadeMilitar;
    }

    public void setGrupoUnidadeMilitar(GrupoUnidadeMilitar grupoUnidadeMilitar) {
        this.grupoUnidadeMilitar = grupoUnidadeMilitar;
    }
}
