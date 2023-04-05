package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.PessoaDependente;
import br.gov.pe.sismepe.domain.PessoaTitular;

public class PessoaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String tipoBeneficiario;
	
	private Integer matricula;
	
	private Integer digito;
	
	private Integer corporacao;
	
	private Integer sequencial;
	
	private String sexo;
	
	private String cpf;
	
	private String posto;
	
	private String telefone;
	
	private String fezRecadastramento;
	
	private Date dataNascimento;
	
	private int idade;
	
	private String foto;
	
	private String urlFotoRecadastramento;
	
	private String cadastroAtualizado;
	
	public PessoaDTO() {
	}
	
	public PessoaDTO(Pessoa obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.sexo = obj.getSexo();
		this.cpf = obj.getCpf();
		this.dataNascimento = obj.getDataNascimento();
		this.fezRecadastramento = obj.getRecadastramento();
		this.tipoBeneficiario = obj.getClass().getSimpleName();
		this.cadastroAtualizado = obj.getCadastroAtualizado();
		
		if (obj.getDataNascimento() != null) {
			LocalDate hoje = LocalDate.now();
			LocalDate nasc = obj.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			Period p = Period.between(nasc, hoje);
			this.idade = p.getYears();	
		}
		
		if(this.tipoBeneficiario.equals("PessoaTitular")) {
			PessoaTitular pessoa = (PessoaTitular)obj;
			this.matricula = pessoa.getMatricula();
			this.digito = pessoa.getDigito();
			this.corporacao = pessoa.getCorporacao();
			
			this.posto = pessoa.getPosto() != null ? pessoa.getPosto().getNome() : "";
			this.telefone = pessoa.getTelefone();
			this.foto = pessoa.getFoto();
		}else if(this.tipoBeneficiario.equals("PessoaDependente")) {
			PessoaDependente pessoa = (PessoaDependente)obj;
			this.matricula = pessoa.getMatricula();
			this.sequencial = pessoa.getSequencial();
			this.corporacao = pessoa.getCorporacao();
			this.posto = "";
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
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPosto() {
		return posto;
	}

	public void setPosto(String posto) {
		this.posto = posto;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFezRecadastramento() {
		return fezRecadastramento;
	}

	public void setFezRecadastramento(String fezRecadastramento) {
		this.fezRecadastramento = fezRecadastramento;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String enderecoFoto) {
		this.foto = enderecoFoto;
	}

	public String getUrlFotoRecadastramento() {
		return urlFotoRecadastramento;
	}

	public void setUrlFotoRecadastramento(String urlFotoRecadastramento) {
		this.urlFotoRecadastramento = urlFotoRecadastramento;
	}

	public String getCadastroAtualizado() {
		return cadastroAtualizado;
	}

	public void setCadastroAtualizado(String cadastroAtualizado) {
		this.cadastroAtualizado = cadastroAtualizado;
	}
	
}
