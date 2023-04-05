package br.gov.pe.sismepe.dto.covid;

import java.io.Serializable;

public class EstatisticaCasosDTO implements Serializable {
	
	private static final long serialVersionUID = 8274837680866447279L;
	private Integer suspeitos;
	private Integer positivos;
	private Integer negativos;
	private Integer obitos;
	private Integer aguardando;
	private Integer naoTestados;
	private Integer internados;
	
	public EstatisticaCasosDTO() {
		
	}

	public Integer getSuspeitos() {
		return suspeitos;
	}

	public void setSuspeitos(Integer suspeitos) {
		this.suspeitos = suspeitos;
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

	public Integer getObitos() {
		return obitos;
	}

	public void setObitos(Integer obitos) {
		this.obitos = obitos;
	}

	public Integer getAguardando() {
		return aguardando;
	}

	public void setAguardando(Integer aguardando) {
		this.aguardando = aguardando;
	}

	public Integer getNaoTestados() {
		return naoTestados;
	}

	public void setNaoTestados(Integer naoTestados) {
		this.naoTestados = naoTestados;
	}

	public Integer getInternados() {
		return internados;
	}

	public void setInternados(Integer internados) {
		this.internados = internados;
	}

	public Integer getAltas() {
		return suspeitos - internados - obitos;
	}
}