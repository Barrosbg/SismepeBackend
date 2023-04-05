package br.gov.pe.sismepe.dto.covid;

import java.io.Serializable;

public class EstatisticaTestesDTO implements Serializable {
	
	private static final long serialVersionUID = 8274837680866447279L;
	private Integer testes;
	private Integer positivos;
	private Integer negativos;
	private Integer aguardando;
	
	public EstatisticaTestesDTO() {
		
	}

	public Integer getTestes() {
		return testes;
	}

	public void setTestes(Integer testes) {
		this.testes = testes;
	}

	public Integer getPositivos() {
		return positivos;
	}

	public void setPositivos(Integer positivos) {
		this.positivos = positivos;
	}

	public Integer getNegativos() {
		return negativos;
	}

	public void setNegativos(Integer negativos) {
		this.negativos = negativos;
	}

	public Integer getAguardando() {
		return aguardando;
	}

	public void setAguardando(Integer aguardando) {
		this.aguardando = aguardando;
	}
}