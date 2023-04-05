package br.gov.pe.sismepe.dto.covid.boletim;

import java.io.Serializable;

public class BoletimCovidDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BoletimPagina1DTO pagina1;
	private BoletimPagina2DTO pagina2;
	private BoletimPagina3DTO pagina3;
	private BoletimPagina4DTO pagina4;

	public BoletimCovidDTO() {}
	
	public BoletimPagina1DTO getPagina1() {
		return pagina1;
	}

	public void setPagina1(BoletimPagina1DTO pagina1) {
		this.pagina1 = pagina1;
	}

	public BoletimPagina2DTO getPagina2() {
		return pagina2;
	}
	
	public void setPagina2(BoletimPagina2DTO pagina2) {
		this.pagina2 = pagina2;
	}
	
	public BoletimPagina3DTO getPagina3() {
		return pagina3;
	}

	public void setPagina3(BoletimPagina3DTO pagina3) {
		this.pagina3 = pagina3;
	}

	public BoletimPagina4DTO getPagina4() {
		return pagina4;
	}

	public void setPagina4(BoletimPagina4DTO pagina4) {
		this.pagina4 = pagina4;
	}	
	
	
}