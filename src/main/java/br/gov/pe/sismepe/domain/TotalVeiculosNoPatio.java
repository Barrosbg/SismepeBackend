package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="total_veiculos_no_patio")
public class TotalVeiculosNoPatio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="DT_REGISTRO")
	private Date dataEntrada;
	
	@Column(name="T_VEICULOS")
	private int totalVeiculos;
	
	public TotalVeiculosNoPatio() {
		
	}

	public TotalVeiculosNoPatio(Long id, Date dataEntrada, int totalVeiculos) {
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

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
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
		return "TotalVeiculosNoPatio [id=" + id + ", dataEntrada=" + dataEntrada + ", totalVeiculos=" + totalVeiculos
				+ "]";
	}


	
	
	
	
}
