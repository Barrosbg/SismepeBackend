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

@Entity
@Table(name="transfusional_solicitacao")
public class TransfusionalSolicitacao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_transfusional_solicitacao")
	private Integer id;
	
	@Column(name = "numero_solicitacao")
	private String numeroSolicitacao;

	@ManyToOne
	@JoinColumn(name = "cd_pessoa")
	private Pessoa cdPessoa;
	
	@Column(name = "cd_orgao")
	private Integer cdOrgao;
	
	@Column(name = "matricula")
	private Integer matricula;
	
	@Column(name = "sequencial")
	private Integer sequencial;
	
	@Column(name = "idade")
	private Integer idade;
	
	@Column(name = "peso")
	private Float peso;
	
	@Column(name = "cns")
	private String cns;
	
	@Column(name = "setor")
	private String setor;
	
	@Column(name = "telefone_setor")
	private String telefoneSetor;
	
	@Column(name = "num_registro")
	private String nemroRegistro;
	
	@Column(name = "diagnostico")
	private String diagnostico;
	
	@Column(name = "antecedente_transf")
	private Boolean antecedenteTransf;
	
	@Column(name = "antecedente_transf_ch")
	private Boolean antecedenteTransfCh;
	
	@Column(name = "antecedente_transf_cp")
	private Boolean antecedenteTransfCp;
	
	@Column(name = "antecedente_transf_pfc")
	private Boolean antecedenteTransfPfc;
	
	@Column(name = "antecedente_transf_outros")
	private Boolean antecedenteTransfOutros;
	
	@Column(name = "antecedente_gest_gesta")
	private Boolean antecedenteGestGesta;
	
	@Column(name = "antecedente_gest_para")
	private Boolean antecedenteGestPara;
	
	@Column(name = "antecedente_gest_aborto")
	private Boolean antecedenteGestAborto;
	
	@Column(name = "reacao_transf")
	private Boolean reacaoTransf;
	
	@Column(name = "reacao_transf_tipo")
	private String reacaoTransfTipo;
	
	@Column(name = "reacao_transf_data")
	//@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date reacaoTransfData;
	
	@Column(name = "sangue_fenotipado")
	private Boolean sangueFenotipado;
	
	@Column(name = "sangue_fenotipado_tipos")
	private String sangueFenotipadoTipos;
	
	@Column(name = "hemocomp_tipo")
	private String hemocompTipo;
	
	@Column(name = "hemocomp_medida")
	private String hemocompMedida;
	
	@Column(name = "hemocomp_quantidade")
	private Float hemocompQuantidade;
	
	@Column(name = "hemocomp_desleucocitado")
	private Boolean hemocompDesleucocitado;
	
	@Column(name = "hemocomp_filtrado")
	private Boolean hemocompFiltrado;
	
	@Column(name = "hemocomp_lavado")
	private Boolean hemocompLavado;
	
	@Column(name = "hemocomp_fenotipado")
	private Boolean hemocompFenotipado;
	
	@Column(name = "hemocomp_irradiado")
	private Boolean hemocompIrradiado;
	
	@Column(name = "exame_data")
	//@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date exame_data;
	
	@Column(name = "exame_hb")
	private Float exameHb;
	
	@Column(name = "exame_ht")
	private Float exameHt;
	
	@Column(name = "exame_plaquetas")
	private Float examePlaquetas;
	
	@Column(name = "exame_inr")
	private Float exameInr;
	
	@Column(name = "transfusao_tipo")
	private String transfusaoTipo;
	
	@Column(name = "transfusao_data")
	//@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date transfusaoData;
	
	@Column(name = "transfusao_hora")
	//@JsonFormat(pattern = "HH:mm")
	private String transfusaoHora;
	
	@Column(name = "cd_medico")
	private Integer cdMedico;
	
	@Column(name = "dt_solicitacao")
	//@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dtSolicitacao;
	
	@Column(name = "situacao")
	private String situacao;
	
	@Column(name = "dt_cadastro")
	//@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dtCadastro;
	
	@Column(name = "cd_usuario")
	private Integer cdUsuario;

	public TransfusionalSolicitacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransfusionalSolicitacao(Integer id, String numeroSolicitacao, Pessoa cdPessoa, Integer cdOrgao,
			Integer matricula, Integer sequencial, Integer idade, Float peso, String cns,String setor,
			String telefoneSetor, String nemroRegistro, String diagnostico, Boolean antecedenteTransf,
			Boolean antecedenteTransfCh, Boolean antecedenteTransfCp, Boolean antecedenteTransfPfc,
			Boolean antecedenteTransfOutros, Boolean antecedenteGestGesta, Boolean antecedenteGestPara,
			Boolean antecedenteGestAborto, Boolean reacaoTransf, String reacaoTransfTipo, Date reacaoTransfData,
			Boolean sangueFenotipado, String sangueFenotipadoTipos, String hemocompTipo, String hemocompMedida,
			Float hemocompQuantidade, Boolean hemocompDesleucocitado, Boolean hemocompFiltrado, Boolean hemocompLavado,
			Boolean hemocompFenotipado, Boolean hemocompIrradiado, Date exame_data, Float exameHb, Float exameHt,
			Float examePlaquetas, Float exameInr, String transfusaoTipo, Date transfusaoData, String transfusaoHora,
			Integer cdMedico, Date dtSolicitacao, String situacao, Date dtCadastro, Integer cdUsuario) {
		super();
		this.id = id;
		this.numeroSolicitacao = numeroSolicitacao;
		this.cdPessoa = cdPessoa;
		this.cdOrgao = cdOrgao;
		this.matricula = matricula;
		this.sequencial = sequencial;
		this.idade = idade;
		this.peso = peso;
		this.cns = cns;
		this.setor = setor;
		this.telefoneSetor = telefoneSetor;
		this.nemroRegistro = nemroRegistro;
		this.diagnostico = diagnostico;
		this.antecedenteTransf = antecedenteTransf;
		this.antecedenteTransfCh = antecedenteTransfCh;
		this.antecedenteTransfCp = antecedenteTransfCp;
		this.antecedenteTransfPfc = antecedenteTransfPfc;
		this.antecedenteTransfOutros = antecedenteTransfOutros;
		this.antecedenteGestGesta = antecedenteGestGesta;
		this.antecedenteGestPara = antecedenteGestPara;
		this.antecedenteGestAborto = antecedenteGestAborto;
		this.reacaoTransf = reacaoTransf;
		this.reacaoTransfTipo = reacaoTransfTipo;
		this.reacaoTransfData = reacaoTransfData;
		this.sangueFenotipado = sangueFenotipado;
		this.sangueFenotipadoTipos = sangueFenotipadoTipos;
		this.hemocompTipo = hemocompTipo;
		this.hemocompMedida = hemocompMedida;
		this.hemocompQuantidade = hemocompQuantidade;
		this.hemocompDesleucocitado = hemocompDesleucocitado;
		this.hemocompFiltrado = hemocompFiltrado;
		this.hemocompLavado = hemocompLavado;
		this.hemocompFenotipado = hemocompFenotipado;
		this.hemocompIrradiado = hemocompIrradiado;
		this.exame_data = exame_data;
		this.exameHb = exameHb;
		this.exameHt = exameHt;
		this.examePlaquetas = examePlaquetas;
		this.exameInr = exameInr;
		this.transfusaoTipo = transfusaoTipo;
		this.transfusaoData = transfusaoData;
		this.transfusaoHora = transfusaoHora;
		this.cdMedico = cdMedico;
		this.dtSolicitacao = dtSolicitacao;
		this.situacao = situacao;
		this.dtCadastro = dtCadastro;
		this.cdUsuario = cdUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroSolicitacao() {
		return numeroSolicitacao;
	}

	public void setNumeroSolicitacao(String numeroSolicitacao) {
		this.numeroSolicitacao = numeroSolicitacao;
	}

	public Pessoa getCdPessoa() {
		return cdPessoa;
	}

	public void setCdPessoa(Pessoa cdPessoa) {
		this.cdPessoa = cdPessoa;
	}

	public Integer getCdOrgao() {
		return cdOrgao;
	}

	public void setCdOrgao(Integer cdOrgao) {
		this.cdOrgao = cdOrgao;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}
	
	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getTelefoneSetor() {
		return telefoneSetor;
	}

	public void setTelefoneSetor(String telefoneSetor) {
		this.telefoneSetor = telefoneSetor;
	}

	public String getNemroRegistro() {
		return nemroRegistro;
	}

	public void setNemroRegistro(String nemroRegistro) {
		this.nemroRegistro = nemroRegistro;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Boolean getAntecedenteTransf() {
		return antecedenteTransf;
	}

	public void setAntecedenteTransf(Boolean antecedenteTransf) {
		this.antecedenteTransf = antecedenteTransf;
	}

	public Boolean getAntecedenteTransfCh() {
		return antecedenteTransfCh;
	}

	public void setAntecedenteTransfCh(Boolean antecedenteTransfCh) {
		this.antecedenteTransfCh = antecedenteTransfCh;
	}

	public Boolean getAntecedenteTransfCp() {
		return antecedenteTransfCp;
	}

	public void setAntecedenteTransfCp(Boolean antecedenteTransfCp) {
		this.antecedenteTransfCp = antecedenteTransfCp;
	}

	public Boolean getAntecedenteTransfPfc() {
		return antecedenteTransfPfc;
	}

	public void setAntecedenteTransfPfc(Boolean antecedenteTransfPfc) {
		this.antecedenteTransfPfc = antecedenteTransfPfc;
	}

	public Boolean getAntecedenteTransfOutros() {
		return antecedenteTransfOutros;
	}

	public void setAntecedenteTransfOutros(Boolean antecedenteTransfOutros) {
		this.antecedenteTransfOutros = antecedenteTransfOutros;
	}

	public Boolean getAntecedenteGestGesta() {
		return antecedenteGestGesta;
	}

	public void setAntecedenteGestGesta(Boolean antecedenteGestGesta) {
		this.antecedenteGestGesta = antecedenteGestGesta;
	}

	public Boolean getAntecedenteGestPara() {
		return antecedenteGestPara;
	}

	public void setAntecedenteGestPara(Boolean antecedenteGestPara) {
		this.antecedenteGestPara = antecedenteGestPara;
	}

	public Boolean getAntecedenteGestAborto() {
		return antecedenteGestAborto;
	}

	public void setAntecedenteGestAborto(Boolean antecedenteGestAborto) {
		this.antecedenteGestAborto = antecedenteGestAborto;
	}

	public Boolean getReacaoTransf() {
		return reacaoTransf;
	}

	public void setReacaoTransf(Boolean reacaoTransf) {
		this.reacaoTransf = reacaoTransf;
	}

	public String getReacaoTransfTipo() {
		return reacaoTransfTipo;
	}

	public void setReacaoTransfTipo(String reacaoTransfTipo) {
		this.reacaoTransfTipo = reacaoTransfTipo;
	}

	public Date getReacaoTransfData() {
		return reacaoTransfData;
	}

	public void setReacaoTransfData(Date reacaoTransfData) {
		this.reacaoTransfData = reacaoTransfData;
	}

	public Boolean getSangueFenotipado() {
		return sangueFenotipado;
	}

	public void setSangueFenotipado(Boolean sangueFenotipado) {
		this.sangueFenotipado = sangueFenotipado;
	}

	public String getSangueFenotipadoTipos() {
		return sangueFenotipadoTipos;
	}

	public void setSangueFenotipadoTipos(String sangueFenotipadoTipos) {
		this.sangueFenotipadoTipos = sangueFenotipadoTipos;
	}

	public String getHemocompTipo() {
		return hemocompTipo;
	}

	public void setHemocompTipo(String hemocompTipo) {
		this.hemocompTipo = hemocompTipo;
	}

	public String getHemocompMedida() {
		return hemocompMedida;
	}

	public void setHemocompMedida(String hemocompMedida) {
		this.hemocompMedida = hemocompMedida;
	}

	public Float getHemocompQuantidade() {
		return hemocompQuantidade;
	}

	public void setHemocompQuantidade(Float hemocompQuantidade) {
		this.hemocompQuantidade = hemocompQuantidade;
	}

	public Boolean getHemocompDesleucocitado() {
		return hemocompDesleucocitado;
	}

	public void setHemocompDesleucocitado(Boolean hemocompDesleucocitado) {
		this.hemocompDesleucocitado = hemocompDesleucocitado;
	}

	public Boolean getHemocompFiltrado() {
		return hemocompFiltrado;
	}

	public void setHemocompFiltrado(Boolean hemocompFiltrado) {
		this.hemocompFiltrado = hemocompFiltrado;
	}

	public Boolean getHemocompLavado() {
		return hemocompLavado;
	}

	public void setHemocompLavado(Boolean hemocompLavado) {
		this.hemocompLavado = hemocompLavado;
	}

	public Boolean getHemocompFenotipado() {
		return hemocompFenotipado;
	}

	public void setHemocompFenotipado(Boolean hemocompFenotipado) {
		this.hemocompFenotipado = hemocompFenotipado;
	}

	public Boolean getHemocompIrradiado() {
		return hemocompIrradiado;
	}

	public void setHemocompIrradiado(Boolean hemocompIrradiado) {
		this.hemocompIrradiado = hemocompIrradiado;
	}

	public Date getExame_data() {
		return exame_data;
	}

	public void setExame_data(Date exame_data) {
		this.exame_data = exame_data;
	}

	public Float getExameHb() {
		return exameHb;
	}

	public void setExameHb(Float exameHb) {
		this.exameHb = exameHb;
	}

	public Float getExameHt() {
		return exameHt;
	}

	public void setExameHt(Float exameHt) {
		this.exameHt = exameHt;
	}

	public Float getExamePlaquetas() {
		return examePlaquetas;
	}

	public void setExamePlaquetas(Float examePlaquetas) {
		this.examePlaquetas = examePlaquetas;
	}

	public Float getExameInr() {
		return exameInr;
	}

	public void setExameInr(Float exameInr) {
		this.exameInr = exameInr;
	}

	public String getTransfusaoTipo() {
		return transfusaoTipo;
	}

	public void setTransfusaoTipo(String transfusaoTipo) {
		this.transfusaoTipo = transfusaoTipo;
	}

	public Date getTransfusaoData() {
		return transfusaoData;
	}

	public void setTransfusaoData(Date transfusaoData) {
		this.transfusaoData = transfusaoData;
	}

	public String getTransfusaoHora() {
		return transfusaoHora;
	}

	public void setTransfusaoHora(String transfusaoHora) {
		this.transfusaoHora = transfusaoHora;
	}

	public Integer getCdMedico() {
		return cdMedico;
	}

	public void setCdMedico(Integer cdMedico) {
		this.cdMedico = cdMedico;
	}

	public Date getDtSolicitacao() {
		return dtSolicitacao;
	}

	public void setDtSolicitacao(Date dtSolicitacao) {
		this.dtSolicitacao = dtSolicitacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Integer getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(Integer cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdMedico == null) ? 0 : cdMedico.hashCode());
		result = prime * result + ((cdOrgao == null) ? 0 : cdOrgao.hashCode());
		result = prime * result + ((cdPessoa == null) ? 0 : cdPessoa.hashCode());
		result = prime * result + ((cdUsuario == null) ? 0 : cdUsuario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		TransfusionalSolicitacao other = (TransfusionalSolicitacao) obj;
		if (cdMedico == null) {
			if (other.cdMedico != null)
				return false;
		} else if (!cdMedico.equals(other.cdMedico))
			return false;
		if (cdOrgao == null) {
			if (other.cdOrgao != null)
				return false;
		} else if (!cdOrgao.equals(other.cdOrgao))
			return false;
		if (cdPessoa == null) {
			if (other.cdPessoa != null)
				return false;
		} else if (!cdPessoa.equals(other.cdPessoa))
			return false;
		if (cdUsuario == null) {
			if (other.cdUsuario != null)
				return false;
		} else if (!cdUsuario.equals(other.cdUsuario))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transfusional_solicitacao [id=" + id + ", numeroSolicitacao=" + numeroSolicitacao + ", cdPessoa="
				+ cdPessoa + ", cdOrgao=" + cdOrgao + ", matricula=" + matricula + ", sequencial=" + sequencial
				+ ", idade=" + idade + ", peso=" + peso + ", cns=" + cns + ", setor=" + setor + ",  telefoneSetor="
				+ telefoneSetor + ", nemroRegistro=" + nemroRegistro + ", diagnostico=" + diagnostico
				+ ", antecedenteTransf=" + antecedenteTransf + ", antecedenteTransfCh=" + antecedenteTransfCh
				+ ", antecedenteTransfCp=" + antecedenteTransfCp + ", antecedenteTransfPfc=" + antecedenteTransfPfc
				+ ", antecedenteTransfOutros=" + antecedenteTransfOutros + ", antecedenteGestGesta="
				+ antecedenteGestGesta + ", antecedenteGestPara=" + antecedenteGestPara + ", antecedenteGestAborto="
				+ antecedenteGestAborto + ", reacaoTransf=" + reacaoTransf + ", reacaoTransfTipo=" + reacaoTransfTipo
				+ ", reacaoTransfData=" + reacaoTransfData + ", sangueFenotipado=" + sangueFenotipado
				+ ", sangueFenotipadoTipos=" + sangueFenotipadoTipos + ", hemocompTipo=" + hemocompTipo
				+ ", hemocompMedida=" + hemocompMedida + ", hemocompQuantidade=" + hemocompQuantidade
				+ ", hemocompDesleucocitado=" + hemocompDesleucocitado + ", hemocompFiltrado=" + hemocompFiltrado
				+ ", hemocompLavado=" + hemocompLavado + ", hemocompFenotipado=" + hemocompFenotipado
				+ ", hemocompIrradiado=" + hemocompIrradiado + ", exame_data=" + exame_data + ", exameHb=" + exameHb
				+ ", exameHt=" + exameHt + ", examePlaquetas=" + examePlaquetas + ", exameInr=" + exameInr
				+ ", transfusaoTipo=" + transfusaoTipo + ", transfusaoData=" + transfusaoData + ", transfusaoHora="
				+ transfusaoHora + ", cdMedico=" + cdMedico + ", dtSolicitacao=" + dtSolicitacao + ", situacao="
				+ situacao + ", dtCadastro=" + dtCadastro + ", cdUsuario=" + cdUsuario + "]";
	}
	
	
	
}
