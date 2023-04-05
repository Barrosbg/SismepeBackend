package br.gov.pe.sismepe.domain;

import br.gov.pe.sismepe.domain.enums.HomologacaoEnum;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "LICENCA")
public class Licenca implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "CD_LICENCA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @PastOrPresent(message = "Data de início não pode ser no futuro")
    @Column(name = "DT_INICIO")
    private Date dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "DT_CADASTRO")
    private Date dataCadastro;

    @Column(name = "QT_DIAS")
    @PositiveOrZero(message = "Informe uma quantidade de dias")
    private Integer qtdDias;

    @Column(name = "SN_ATIVO")
    private String ativo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "DT_HOMOLOGACAO")
    private Date dataHomologacao;

    @JoinColumn(name = "CD_PESSOA")
    @OneToOne
    private PessoaTitular pessoa;

    @JsonIgnoreProperties(value = {"pessoa", "conselho", "corpoClinico", "ativo", "especialidades"})
    @JoinColumn(name = "CD_PRESTADOR_EMISSOR")
    @OneToOne
    private Prestador prestador;

    @Column(name = "CD_HOMOLOGACAO")
    private HomologacaoEnum homologacao;

    @Column(name = "DS_OBSERVACAO_HOMOLOGACAO")
    private String observacaoHomologacao;

    @JoinColumn(name = "CD_PARECER")
    @OneToOne
    private Parecer parecer;

    @JoinColumn(name = "CD_USUARIO")
    @OneToOne
    @JsonIgnoreProperties(value = {"perfis", "login", "senha", "ativo", "nivelAcesso"})
    private Usuario usuarioCadastro;

    @JoinColumn(name = "CD_OME_CADASTRO")
    @OneToOne
    private Ome omeCadastro;

    @JoinColumn(name = "CD_OME_PESSOA_TIT")
    @OneToOne
    private Ome omePessoaTitular;

//    @JsonManagedReference
    @NotEmpty(message = "Ao menos um CID deve ser fornecido")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "licenca_cid",
            joinColumns = @JoinColumn(name = "CD_LICENCA"),
            inverseJoinColumns = @JoinColumn(name = "CD_CID")
    )
    private List<Cid> cids = new ArrayList<>();

    public Licenca() {
    }

    public Licenca(Long id, Date dataInicio, Date dataCadastro, Integer qtdDias, String ativo, Date dataHomologacao, PessoaTitular pessoa, Prestador prestador, HomologacaoEnum homologacao, String observacaoHomologacao, Parecer parecer, Usuario usuarioCadastro, Ome omeCadastro, List<Cid> cids) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataCadastro = dataCadastro;
        this.qtdDias = qtdDias;
        this.ativo = ativo;
        this.dataHomologacao = dataHomologacao;
        this.pessoa = pessoa;
        this.prestador = prestador;
        this.homologacao = homologacao;
        this.observacaoHomologacao = observacaoHomologacao;
        this.parecer = parecer;
        this.usuarioCadastro = usuarioCadastro;
        this.omeCadastro = omeCadastro;
        this.cids = cids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Date getDataHomologacao() {
        return dataHomologacao;
    }

    public void setDataHomologacao(Date dataHomologacao) {
        this.dataHomologacao = dataHomologacao;
    }

    public PessoaTitular getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaTitular pessoa) {
        this.pessoa = pessoa;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public HomologacaoEnum getHomologacao() {
        return homologacao;
    }

    public void setHomologacao(HomologacaoEnum homologacao) {
        this.homologacao = homologacao;
    }

    public String getObservacaoHomologacao() {
        return observacaoHomologacao;
    }

    public void setObservacaoHomologacao(String observacaoHomologacao) {
        this.observacaoHomologacao = observacaoHomologacao;
    }

    public Parecer getParecer() {
        return parecer;
    }

    public void setParecer(Parecer parecer) {
        this.parecer = parecer;
    }

    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public Ome getOmeCadastro() {
        return omeCadastro;
    }

    public void setOmeCadastro(Ome omeCadastro) {
        this.omeCadastro = omeCadastro;
    }

    public List<Cid> getCids() {
        return cids;
    }

    public void setCids(List<Cid> cids) {
        this.cids = cids;
    }

    public Ome getOmePessoaTitular() {
        return omePessoaTitular;
    }

    public void setOmePessoaTitular(Ome omePessoaTitular) {
        this.omePessoaTitular = omePessoaTitular;
    }
}
