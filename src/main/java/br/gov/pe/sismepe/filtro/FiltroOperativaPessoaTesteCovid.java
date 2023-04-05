package br.gov.pe.sismepe.filtro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.gov.pe.sismepe.domain.OperativaPessoaTesteCovid;
import br.gov.pe.sismepe.util.Utils;

public class FiltroOperativaPessoaTesteCovid extends FiltroGenerico<OperativaPessoaTesteCovid> implements Serializable {

	private static final long serialVersionUID = 2002417971460555754L;
	private Long id;
	private String descricao;
	private String ativo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		String tabela = OperativaPessoaTesteCovid.class.getAnnotation(javax.persistence.Table.class).name();
		fromQuery.append(" FROM ");
		fromQuery.append(tabela);
		fromQuery.append(" t ");
		
		return fromQuery.toString();
	}
	
	public String getWhereQuery() {
		StringBuilder fromQuery = new StringBuilder();
		fromQuery.append(" WHERE 1=1 ");
		
		if (Utils.isStringNaoNulaNaoVazia(this.getDescricao())) {
			fromQuery.append(" AND upper(l.ds_setor) like upper(:descricao) ");
		}
		
		return fromQuery.toString();
	}

	@Override
	public boolean isConsultaSQL() {
		return true;
	}

	@Override
	public List<OperativaPessoaTesteCovid> montarObjeto(List<Object[]> retorno) {
		List<OperativaPessoaTesteCovid> lista = new ArrayList<OperativaPessoaTesteCovid>();
		for (Object[] obj : retorno) {
			Integer id = ((Integer) obj[0]);
			String descricao = (String) obj[1];
			String ativo = (String) obj[2];
			
			OperativaPessoaTesteCovid entidade = new OperativaPessoaTesteCovid();
			entidade.setId(id);
			entidade.setDescricao(descricao);
			entidade.setAtivo(ativo);
			lista.add(entidade);
		}
		return lista;
	}

	@Override
	public void setParametros(Query query) {
		if (Utils.isStringNaoNulaNaoVazia(this.getDescricao())) {
			query.setParameter("descricao", "%" + this.getDescricao().trim().toUpperCase() + "%");
		}
	}

	@Override
	public String getSQLExisteEntidade() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
}