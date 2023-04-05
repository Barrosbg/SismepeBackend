package br.gov.pe.sismepe.dto.estacionamento;


import java.io.Serializable;
import java.util.Date;

public class totalVeiculosNoPatioDTO implements Serializable {



	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String dataEntrada;
	private int totalVeiculos;
	
	public totalVeiculosNoPatioDTO() {
		
	}
	
	public totalVeiculosNoPatioDTO(Long id, String dataEntrada, int totalVeiculos) {
		super();
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.totalVeiculos = totalVeiculos;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public int getTotalVeiculos() {
		return totalVeiculos;
	}
	public void setTotalVeiculos(int totalVeiculos) {
		this.totalVeiculos = totalVeiculos;
	}
	
	@Override
	public String toString() {
		return "totalVeiculosNoPatioDTO [id=" + id + ", dataEntrada=" + dataEntrada + ", totalVeiculos=" + totalVeiculos
				+ "]";
	}
	
	
	
	
	
}
