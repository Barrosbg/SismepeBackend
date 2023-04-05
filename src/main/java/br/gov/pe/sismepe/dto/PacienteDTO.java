package br.gov.pe.sismepe.dto;

import java.io.Serializable;

import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.domain.PessoaDependente;
import br.gov.pe.sismepe.domain.PessoaTitular;

public class PacienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String tipoBeneficiario;
	
	private Integer matricula;
	
	private Integer digito;
	
	private Integer corporacao;
	
	private Integer sequencial;
	private String foto;
	private Long pessoaId;
		
	public PacienteDTO() {
	}
	
	public PacienteDTO(Paciente obj) {
		this.id = obj.getId();
		this.nome = obj.getPessoa().getNome();
		this.tipoBeneficiario = obj.getPessoa().getClass().getSimpleName();
		this.pessoaId = obj.getPessoa().getId();
		if(this.tipoBeneficiario.equals("PessoaTitular")) {
			PessoaTitular pessoa = (PessoaTitular)obj.getPessoa();
			this.matricula = pessoa.getMatricula();
			this.digito = pessoa.getDigito();
			this.corporacao = pessoa.getCorporacao();
			this.foto = pessoa.getFoto();
		}else {
			PessoaDependente pessoa = (PessoaDependente)obj.getPessoa();
			this.matricula = pessoa.getMatricula();
			this.sequencial = pessoa.getSequencial();
			this.corporacao = pessoa.getCorporacao();
		}		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipoBeneficiario() {
		return tipoBeneficiario;
	}

	public void setTipoBeneficiario(String tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Integer getDigito() {
		return digito;
	}

	public void setDigito(Integer digito) {
		this.digito = digito;
	}

	public Integer getCorporacao() {
		return corporacao;
	}

	public void setCorporacao(Integer corporacao) {
		this.corporacao = corporacao;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}
}
