package br.gov.pe.sismepe.dto.estacionamento;

import java.io.Serializable;
import java.util.Date;

import br.gov.pe.sismepe.domain.CadastroDeCarros;
import br.gov.pe.sismepe.domain.RegistroEntradaVeiculos;

public class RegistroEntradaVeiculoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dataEntrada;
	private Date dataSaida;
	private int numero_entrada;
	private String situacao_entrada;
	private CadastroDeCarros cadastroCarros;
	
	public RegistroEntradaVeiculos toModel() {
		RegistroEntradaVeiculos registro = new RegistroEntradaVeiculos();
		registro.setId(this.id);	
		registro.setDataEntrada(this.dataEntrada);
		registro.setDataSaida(this.dataSaida);
		registro.setSituacaoEntrada(this.situacao_entrada);
		registro.setCadastroCarros(this.cadastroCarros);
	
		return registro;
	}
	
	
	
	
	public RegistroEntradaVeiculoDTO(Long id, Date dataEntrada,Date dataSaida,CadastroDeCarros cadastroCarros, int numero_entrada, String situacao_entrada) {
		super();
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.situacao_entrada = situacao_entrada;
		this.cadastroCarros = cadastroCarros;
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
	
	
	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public int getNumero_entrada() {
		return numero_entrada;
	}
	public void setNumero_entrada(int numero_entrada) {
		this.numero_entrada = numero_entrada;
	}
	public String getSituacao_entrada() {
		return situacao_entrada;
	}
	public void setSituacao_entrada(String situacao_entrada) {
		this.situacao_entrada = situacao_entrada;
	}
	
	

	public CadastroDeCarros getCadastroCarros() {
		return cadastroCarros;
	}

	public void setCadastro_carros(CadastroDeCarros cadastroCarros) {
		this.cadastroCarros = cadastroCarros;
	}

	@Override
	public String toString() {
		return "RegistroEntradaVeiculoDTO [id=" + id + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida
				+ ", numero_entrada=" + numero_entrada + ", situacao_entrada=" + situacao_entrada + ", cadastroCarros="
				+ cadastroCarros + "]";
	}



	
	
	
}
