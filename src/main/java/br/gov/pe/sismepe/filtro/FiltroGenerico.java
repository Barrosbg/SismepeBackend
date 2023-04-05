package br.gov.pe.sismepe.filtro;

import java.util.List;

import javax.persistence.Query;

import br.gov.pe.sismepe.domain.SuspeitaCovid;

public abstract class FiltroGenerico <T> {

	private int start;
	private int length;
	private int pagina;
	private long totalResultados;

	public String getCountQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT count(*) ");
		sb.append(this.getSQL());
		return sb.toString();
	}

	public int getLength() {
		return this.length;
	}

	public int getPagina() {
		return this.pagina;
	}

	public abstract String getSelectQuery();

	public abstract String getSQL();
	
	public abstract String getSQLExisteEntidade();
	
	public int getStart() {
		return this.start;
	}

	public long getTotalResultados() {
		return this.totalResultados;
	}

	public boolean isConsultaSQL() {
		return false;
	}

	public List<T> montarObjeto(List<Object[]> retorno) {

		return null;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public abstract void setParametros(Query query);

	public void setStart(int start) {
		this.start = start;
	}

	public void setTotalResultados(long totalResultados) {
		this.totalResultados = totalResultados;
	}
}