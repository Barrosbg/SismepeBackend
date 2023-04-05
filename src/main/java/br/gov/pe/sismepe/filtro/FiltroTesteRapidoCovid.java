package br.gov.pe.sismepe.filtro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.gov.pe.sismepe.domain.TesteRapidoCovid;
import br.gov.pe.sismepe.util.Utils;

public class FiltroTesteRapidoCovid extends FiltroGenerico<TesteRapidoCovid> implements Serializable {

	private static final long serialVersionUID = 2002417971460555754L;
	private Long id;
	private Integer codigoPessoa;
	private String nome;
	private String positivo;
	private Date dataColetaExame;
	private Integer matricula;
	private String sexo;
	private Integer idade;
	private String tipo;
	private String categoria;
	private String corporacao;
	private String situacao;
	private String igg;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataFim;
	
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

	@Override
	public String getCountQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT count(distinct l.id) ");
		sb.append(this.getSQL());
		return sb.toString();
	}

	@Override
	public String getSelectQuery() {
		StringBuilder selectQuery = new StringBuilder();
		selectQuery
				.append("SELECT distinct l.id ");
		selectQuery.append(this.getSQL());
		selectQuery.append(" order by l.id ");
		return selectQuery.toString();
	}

	@Override
	public String getSQL() {
		StringBuilder fromQuery = new StringBuilder();
		fromQuery.append(getFromQuery());
		fromQuery.append(getWhereQuery());
		return fromQuery.toString();
	}
	
	public String getFromQuery() {
		StringBuilder fromQuery = new StringBuilder();
		String tabela = TesteRapidoCovid.class.getAnnotation(javax.persistence.Table.class).name();
		fromQuery.append(" FROM ");
		fromQuery.append(tabela);
		fromQuery.append(" t ");
		
//		if (Utils.isStringNaoNulaNaoVazia(this.getNome())) {
//			fromQuery.append(" AND upper(l.ds_nome) like upper(:nome) ");
//		}
		
		return fromQuery.toString();
	}
	
	public String getWhereQuery() {
		StringBuilder fromQuery = new StringBuilder();
		fromQuery.append(" WHERE 1=1 ");
		
		return fromQuery.toString();
	}

	@Override
	public boolean isConsultaSQL() {
		return true;
	}

	@Override
	public List<TesteRapidoCovid> montarObjeto(List<Object[]> retorno) {
		return null;
	}

	@Override
	public void setParametros(Query query) {
		if (Utils.isStringNaoNulaNaoVazia(this.getNome())) {
			query.setParameter("nome", "%" + this.getNome().trim().toUpperCase() + "%");
		}
	}

	@Override
	public String getSQLExisteEntidade() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	public Integer getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(Integer codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
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

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCorporacao() {
		return corporacao;
	}

	public void setCorporacao(String corporacao) {
		this.corporacao = corporacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getIgg() {
		return igg;
	}

	public void setIgg(String igg) {
		this.igg = igg;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
}