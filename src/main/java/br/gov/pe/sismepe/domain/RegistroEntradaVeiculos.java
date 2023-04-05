package br.gov.pe.sismepe.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="registro_entrada_veiculos")
public class RegistroEntradaVeiculos {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CD_REGISTRO")
	private Long id;
    
   
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="DT_ENTRADA")
	private Date dataEntrada;
 
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="DT_SAIDA")
	private Date dataSaida;
//    
//    @NotNull
//    @Column(name="NR_ENTRADA")
//	private int numero_entrada;
    
    @NotNull
    @Column(name="ST_ENTRADA")
	private String situacaoEntrada;
    
//    @ManyToOne
//    @JoinColumn(name="CD_CADASTRO")
//    private CadastroDeCarros cad;
    
    
    @ManyToOne
    @JoinColumn(name="CD_CADASTRO")
    private CadastroDeCarros cadastroCarros;

	public RegistroEntradaVeiculos(Long id, CadastroDeCarros cadastroDeCarros, Date date, String situacao_entrada) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.dataEntrada = date;
		this.situacaoEntrada = situacao_entrada;
		this.cadastroCarros = cadastroDeCarros;
	}
	public RegistroEntradaVeiculos() {
		
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

//	public int getNumero_entrada() {
//		return numero_entrada;
//	}
//
//	public void setNumero_entrada(int numero_entrada) {
//		this.numero_entrada = numero_entrada;
//	}

	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public String getSituacaoEntrada() {
		return situacaoEntrada;
	}

	public void setSituacaoEntrada(String situacaoEntrada) {
		this.situacaoEntrada = situacaoEntrada;
	}

	public CadastroDeCarros getCadastroCarros() {
		return cadastroCarros;
	}

	public void setCadastroCarros(CadastroDeCarros cadastroCarros) {
		this.cadastroCarros = cadastroCarros;
	}

	@Override
	public String toString() {
		return "RegistroEntradaVeiculos [id=" + id + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida+  ", situacaoEntrada=" + situacaoEntrada + ", cadastroCarros=" + cadastroCarros
				+ "]";
	}
    
    
    
}
