package br.gov.pe.sismepe.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "reset_senha")
public class ResetSenha {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_RESET_SENHA")
    private Integer id;
 
    @NotBlank
    @Column(name = "DS_TOKEN")
    private String token;
 
    @ManyToOne
	@JoinColumn(name = "CD_USUARIO")
    private Usuario usuario;
 
    @Column(name = "DT_EXPIRACAO")
    private Date dataExpiracao;
    
    @Size(max = 1)
    @Column(name = "SN_ATIVO")
    private String ativo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	
	public String getAtivo() {
		return this.ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	public boolean isAtivo() {
		return ativo.equals("S");
	}

	@Override
	public String toString() {
		return "ResetSenha [id=" + id + ", token=" + token + ", usuario=" + usuario + ", dataExpiracao=" + dataExpiracao
				+ ", ativo=" + ativo + "]";
	}
   
    
}