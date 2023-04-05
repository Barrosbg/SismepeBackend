package br.gov.pe.sismepe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupo_unidade_militar")
public class GrupoUnidadeMilitar {
    @Id
    @Column(name = "CD_GRUPO_UNIDADE_MILITAR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DS_GRUPO_UNIDADE_MILITAR")
    private String descricao;

    @OneToMany(mappedBy = "grupoUnidadeMilitar")
    @JsonManagedReference
    private List<Ome> omes = new ArrayList<Ome>();

    public GrupoUnidadeMilitar() {
    }

    public GrupoUnidadeMilitar(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

    public List<Ome> getOmes() {
        return omes;
    }

    public void setOmes(List<Ome> omes) {
        this.omes = omes;
    }
}
