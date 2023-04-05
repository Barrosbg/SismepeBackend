package br.gov.pe.sismepe.dto;

import br.gov.pe.sismepe.domain.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LicencaDTO implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    private Long id;
    private Date dataInicio;
    private Integer qtdDias;
    private PessoaTitular pessoa;
    private Prestador prestador;
    private Parecer parecer;
    private Usuario usuarioCadastro;
    private List<Cid> cids;

    private String ativo;
    private Date dataHomologacao;
    private String observacaoHomologacao;
    private Ome omeCadastro;

    public LicencaDTO() {}

    public LicencaDTO(Date dataInicio, Integer qtdDias, PessoaTitular pessoa, Prestador prestador, Parecer parecer,
                      Usuario usuarioCadastro, List<Cid> cids) {
        this.dataInicio = dataInicio;
        this.qtdDias = qtdDias;
        this.pessoa = pessoa;
        this.prestador = prestador;
        this.parecer = parecer;
        this.usuarioCadastro = usuarioCadastro;
        this.cids = cids;
    }

    public Licenca toLicenca() {
        Licenca lic = new Licenca();
        lic.setDataInicio(this.dataInicio);
        lic.setQtdDias(this.qtdDias);
        lic.setPessoa(this.pessoa);
        lic.setPrestador(this.prestador);
        lic.setParecer(this.parecer);
        lic.setUsuarioCadastro(this.usuarioCadastro);
        lic.setCids(this.cids);

        lic.setAtivo(this.ativo);
        lic.setDataHomologacao(this.dataHomologacao);
        lic.setObservacaoHomologacao(this.getObservacaoHomologacao());
        lic.setOmeCadastro(this.getOmeCadastro());

        return lic;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
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

    public List<Cid> getCids() {
        return cids;
    }

    public void setCids(List<Cid> cids) {
        this.cids = cids;
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

    public String getObservacaoHomologacao() {
        return observacaoHomologacao;
    }

    public void setObservacaoHomologacao(String observacaoHomologacao) {
        this.observacaoHomologacao = observacaoHomologacao;
    }

    public Ome getOmeCadastro() {
        return omeCadastro;
    }

    public void setOmeCadastro(Ome omeCadastro) {
        this.omeCadastro = omeCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
