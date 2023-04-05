package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="exames_agencia_transfusional")
public class ExameTransfusional implements Serializable  {
	

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CD_EXAME")
	private Long id;
	
	
	@JoinColumn(name="CD_PESSOA")
    @ManyToOne
	private PessoaTitular pessoa;
    
   
	@JoinColumn(name="CD_PRESTADOR")
    @ManyToOne
	private Prestador prestador;
    
    
	@JoinColumn(name="CD_USUARIO_CADASTRO")
    @ManyToOne
	private Pessoa usuarioCadastro;
	
    @Column(name="GP_SANGUINEO")
	private String  GrupoSanguineo;
	
    @Column(name="RHD")
	private String rhd;
	
    @Column(name="COMB_DIRETO")
	private String comboDireto;
	
    @Column(name="COMB_INDIRETO")
	private String comboIndireto;
    
    @Column(name="CD_SITUACAO_RESULTADO", nullable = true)
	private String situacao;
    @JsonIgnore
	@ManyToOne
    @JoinColumn(name="CD_USUARIO_ALTERACAO", nullable = true)
   	private Usuario usuarioAlteracao;
	
    @Column(name="SN_ATIVO")
	private String ativo;
    

	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="DT_CADASTRO")
	private Date dataCadastro = new Date();
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name="DT_ATUALIZACAO")
	private Date dataAtualizacao;

    public ExameTransfusional() {
    	
    }
    
    
    

	public ExameTransfusional(Long id, PessoaTitular pessoa, Prestador prestador, Pessoa usuarioCadastro,
			String grupoSanguineo, String rhd, String comboDireto, String comboIndireto, String situacao,
			Usuario usuarioAlteracao, String ativo, Date dataCadastro, Date dataAtualizacao) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.prestador = prestador;
		this.usuarioCadastro = usuarioCadastro;
		GrupoSanguineo = grupoSanguineo;
		this.rhd = rhd;
		this.comboDireto = comboDireto;
		this.comboIndireto = comboIndireto;
		this.situacao = situacao;
		this.usuarioAlteracao = usuarioAlteracao;
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

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExameTransfusional other = (ExameTransfusional) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    
    
    
}
