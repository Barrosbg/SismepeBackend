package br.gov.pe.sismepe.filtro;

import java.io.Serializable;

import javax.persistence.Query;

import br.gov.pe.sismepe.domain.UnidadeInternacao;

public class FiltroUnidadeInternacao extends FiltroGenerico<UnidadeInternacao> implements Serializable {

	private static final long serialVersionUID = 2002417971460555754L;
	private Long id;
	private String covid;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean isConsultaSQL() {
		return true;
	}

	@Override
	public String getSQLExisteEntidade() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	@Override
	public String getSelectQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParametros(Query query) {
		// TODO Auto-generated method stub
		
	}

	public String getCovid() {
		return covid;
	}

	public void setCovid(String covid) {
		this.covid = covid;
	}
}