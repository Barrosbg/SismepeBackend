package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import br.gov.pe.sismepe.domain.ExameTransfusional;
import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.PessoaTitular;
import br.gov.pe.sismepe.domain.Prestador;
import br.gov.pe.sismepe.domain.Usuario;

public class ExameTransfusionalDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private PessoaTitular pessoa;
	private Prestador prestador;
	private Pessoa usuarioCadastro;
	private Usuario usuarioAlteracao;	
	private String GrupoSanguineo;
	private String rhd;
	private String comboDireto;
	private String comboIndireto;
	private String situacao;
	private String ativo;
	private Date dataCadastro;
	private Date dataAtualizacao;

	public ExameTransfusionalDTO() {

	}

	public ExameTransfusionalDTO(Long id, PessoaTitular pessoa, Prestador prestador, Pessoa usuarioCadastro,
			Usuario usuarioAlteracao, String grupoSanguineo, String rhd, String comboDireto, String comboIndireto,
			String situacao, String ativo, Date dataCadastro, Date dataAtualizacao) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.prestador = prestador;
		this.usuarioCadastro = usuarioCadastro;
		this.usuarioAlteracao = usuarioAlteracao;
		GrupoSanguineo = grupoSanguineo;
		this.rhd = rhd;
		this.comboDireto = comboDireto;
		this.comboIndireto = comboIndireto;
		this.situacao = situacao;
		this.ativo = ativo;
		this.dataCadastro = dataCadastro;
		this.dataAtualizacao = dataAtualizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Pessoa getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Pessoa usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getGrupoSanguineo() {
		return GrupoSanguineo;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		GrupoSanguineo = grupoSanguineo;
	}

	public String getRhd() {
		return rhd;
	}

	public void setRhd(String rhd) {
		this.rhd = rhd;
	}

	public String getComboDireto() {
		return comboDireto;
	}

	public void setComboDireto(String comboDireto) {
		this.comboDireto = comboDireto;
	}

	public String getComboIndireto() {
		return comboIndireto;
	}

	public void setComboIndireto(String comboIndireto) {
		this.comboIndireto = comboIndireto;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	

}
