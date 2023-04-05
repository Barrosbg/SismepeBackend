package br.gov.pe.sismepe.dto;

public class DataTablesFilter {

	private int start;
	private int length;
	private int pagina;
	private int sortColumn;
	private String sortType;

	public int getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(int sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public int getLength() {
		return this.length;
	}

	public int getPagina() {
		return this.pagina;
	}

	public int getStart() {
		return this.start;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public void setStart(int start) {
		this.start = start;
	}
}
