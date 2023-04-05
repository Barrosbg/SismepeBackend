package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.gov.pe.sismepe.dto.estacionamento.CadastroDeCarrosEstacionamentoDTO;

@Entity
@Table(name="cadastro_carros")
public class CadastroDeCarros implements Serializable {
	
	
	@Id
	@Column(name = "CD_CADASTRO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="NM_PESSOA")
	private String nome;
	
	@Column(name="NM_SETOR")
	private String setor;
	
	@Column(name="NM_MARCA")
	private String marca;
	
	@Column(name="NM_MODELO")
	private String modelo;
	
	@Column(name="NM_COR")
	private String cor;
	
	@Column(name="NR_PLACA")
	private String placa;
	
	@Column(name="NR_TELEFONE")
	private String telefone;
	
	@Column(name="SN_ATIVO")
	private String ativo;
	

	public CadastroDeCarros toModel() {
		CadastroDeCarros cadCarros = new CadastroDeCarros();
	    cadCarros.setId(this.id);
	    cadCarros.setNome(this.nome);
	    cadCarros.setSetor(this.setor);
	    cadCarros.setMarca(this.marca);
	    cadCarros.setCor(this.cor);
	    cadCarros.setPlaca(this.placa);
	    cadCarros.setModelo(this.modelo);
	    cadCarros.setTelefone(this.telefone);
	    cadCarros.setAtivo(this.ativo);
		return cadCarros;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSetor() {
		return setor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	
	

}
