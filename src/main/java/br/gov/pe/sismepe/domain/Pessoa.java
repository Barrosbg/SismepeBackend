package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PESSOA")
@Inheritance(strategy=InheritanceType.JOINED)
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_PESSOA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NM_PESSOA", nullable = false)
	private String nome;
	
	@Column(name = "NM_PAI")
	private String nomePai;
	
	@Column(name = "NM_MAE")
	private String nomeMae;
	
	@Column(name = "TP_SEXO")
	private String sexo;
	
	@Column(name = "NR_CPF")
	private String cpf;
	
	@Column(name = "NR_RG")
	private String rg;
	
	@Column(name = "NR_PIS_PASEP")
	private String nrPisPasep;
	
	@Column(name = "NR_TELEFONE1")
	private String telefone;
	
	@Column(name = "DT_NASCIMENTO")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	@Column(name = "DT_CADASTRO")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCadastro;
	
	@Column(name = "DT_INCL")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataInclusao;
	
	@ManyToOne
	@JoinColumn(name = "CD_USUARIO_CADASTRO")
	@JsonIgnoreProperties(value = {"perfis", "pessoa"})
	private Usuario usuarioCadastro;

	@OneToOne
	@JoinColumn(name="CD_ENDERECO")
	private Endereco endereco;
	
	@Column(name = "CD_ESTADO_CIVIL")
	private Long estadoCivil;

	@Column(name = "SN_ATIVO")
	private String ativo;
	
	@Column(name = "SN_RECADASTRAMENTO")
	private String recadastramento;
	
	@Column(name = "NR_TELEFONE2")
	private String telefone2;
	
	@Column(name = "DS_EMAIL")
	private String email;
	
	@Column(name = "DS_NATURALIDADE")
	private String naturalidade;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "COMORBIDADE_PESSOA",
		joinColumns = @JoinColumn(name = "CD_PESSOA"),
		inverseJoinColumns = @JoinColumn(name = "CD_COMORBIDADE")
	)
	private Set<Comorbidade> comorbidades;
		
	@JsonIgnore
	@Column(name = "NM_BUCKET_FOTO_MINIO")
	private String bucketMinio;
	
	@JsonIgnore
	@Column(name = "NM_FILENAME_FOTO_MINIO")
	private String filenameFotoMinio;

	@Column(name = "SN_CADASTRO_ATUALIZADO")
	private String cadastroAtualizado;

	public Pessoa() {
		
	}

	public Pessoa(Long id) {
		super();
		this.id = id;
	}

	public Pessoa(Long id, String nome, String sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
	}

	public Pessoa(Long id, String nome, String cpf, Date dataCadastro, 
			Date dataInclusao, Usuario usuarioCadastro, String ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataCadastro = dataCadastro;
		this.dataInclusao = dataInclusao;
		this.usuarioCadastro = usuarioCadastro;
		this.ativo = ativo;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRecadastramento() {
		return recadastramento;
	}

	public void setRecadastramento(String recadastramento) {
		this.recadastramento = recadastramento;
	}

	public Set<Comorbidade> getComorbidades() {
		return comorbidades;
	}

	public void setComorbidades(Set<Comorbidade> comorbidades) {
		this.comorbidades = comorbidades;
	}
	
	public String getBucketMinio() {
		return bucketMinio;
	}

	public void setBucketMinio(String bucketMinio) {
		this.bucketMinio = bucketMinio;
	}

	public String getFilenameFotoMinio() {
		return filenameFotoMinio;
	}

	public void setFilenameFotoMinio(String filenameFotoMinio) {
		this.filenameFotoMinio = filenameFotoMinio;
	}

	public Long getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(Long estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getCadastroAtualizado() {
		return cadastroAtualizado;
	}

	public void setCadastroAtualizado(String cadastroAtualizado) {
		this.cadastroAtualizado = cadastroAtualizado;
	}

	public String getNrPisPasep() {
		return nrPisPasep;
	}

	public void setNrPisPasep(String nrPisPasep) {
		this.nrPisPasep = nrPisPasep;
	}

}