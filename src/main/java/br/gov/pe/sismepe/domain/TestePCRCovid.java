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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TESTE_PCR_COVID")
public class TestePCRCovid implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cd_teste_pcr")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cd_pessoa")
	private Pessoa pessoa;
	
	@Column(name = "nm_nome", nullable = true, length = 250)
	private String nome;
	
	@Column(name = "nm_mae", nullable = true, length = 250)
	private String nomeMae;
	
	@Column(name = "nr_cpf", nullable = true, length = 11)
	private String cpf;
	
	@Column(name = "nr_telefone", nullable = true, length = 50)
	private String telefone;
	
	@Column(name = "sn_ativo", nullable = false, length = 1)
	private String ativo;
	
	@Column(name = "sn_exame_positivo", nullable = true, length = 1)
	private String positivo;
	
	@Column(name = "tp_sexo", nullable = true, length = 1)
	private String sexo;
	
	@Column(name = "nr_gal", length = 20)
	private String numeroGAL;
	
	@Column(name = "NR_CEP", nullable = true, length = 8)
	private String cep;
	
	@Column(name = "NM_LOGRADOURO", nullable = true, length = 300)
	private String logradouro;
	
	@Column(name = "NM_BAIRRO", nullable = true, length = 200)
	private String bairro;
	
	@Column(name = "NM_CIDADE", nullable = true, length = 200)
	private String cidade;
	
	@Column(name = "NR_NUMERO", nullable = true, length = 10)
	private String numero;
	
	@Column(name = "DS_COMPLEMENTO", nullable = true, length = 30)
	private String complemento;
	
	@Column(name = "NM_UF", nullable = true, length = 2)
	private String uf;
	
	@Column(name = "dt_coleta_exame")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataColetaExame;
	
	@Column(name = "dt_resultado_exame")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataResultadoExame;
	
	@Column(name = "dt_nascimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "cd_setor")
	private SetorPessoaTesteCovid setor;
	
	@ManyToOne
	@JoinColumn(name = "cd_operativa")
	private OperativaPessoaTesteCovid operativa;
	
	
	public TestePCRCovid() {
		
	}
	
	public TestePCRCovid(Integer id) {
		this.id = id;
	}
	
	public TestePCRCovid(Integer id, String nome) {
		this.id = id;
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		this.setPessoa(pessoa);
	}

	public TestePCRCovid(Integer id, Pessoa pessoa, String ativo, String positivo, Date dataColetaExame,
			Date dataResultadoExame) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.ativo = ativo;
		this.positivo = positivo;
		this.dataColetaExame = dataColetaExame;
		this.dataResultadoExame = dataResultadoExame;
	}
	
	public TestePCRCovid(Integer id, String ativo, String nome) {
		super();
		this.id = id;
		this.ativo = ativo;
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		this.setPessoa(pessoa);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getPositivo() {
		return positivo;
	}

	public void setPositivo(String positivo) {
		this.positivo = positivo;
	}

	public Date getDataColetaExame() {
		return dataColetaExame;
	}

	public void setDataColetaExame(Date dataColetaExame) {
		this.dataColetaExame = dataColetaExame;
	}

	public Date getDataResultadoExame() {
		return dataResultadoExame;
	}

	public void setDataResultadoExame(Date dataResultadoExame) {
		this.dataResultadoExame = dataResultadoExame;
	}

	public String getNumeroGAL() {
		return numeroGAL;
	}

	public void setNumeroGAL(String numeroGAL) {
		this.numeroGAL = numeroGAL;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public SetorPessoaTesteCovid getSetor() {
		return setor;
	}

	public void setSetor(SetorPessoaTesteCovid setor) {
		this.setor = setor;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public OperativaPessoaTesteCovid getOperativa() {
		return operativa;
	}

	public void setOperativa(OperativaPessoaTesteCovid operativa) {
		this.operativa = operativa;
	}
}