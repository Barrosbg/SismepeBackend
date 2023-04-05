package br.gov.pe.sismepe.dto.estacionamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.gov.pe.sismepe.domain.CadastroDeCarros;

public class CadastroDeCarrosEstacionamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Long id;
	private String nome;
	private String setor;
	private String marca;
	private String modelo;
	private String cor;
	private String telefone;
	private String placa;
	private String ativo;
	
	
	public CadastroDeCarrosEstacionamentoDTO() {
		
	}
	

	


	public CadastroDeCarrosEstacionamentoDTO(Long id, String nome, String setor, String marca, String modelo,
			String cor, String telefone, String placa, String ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.setor = setor;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.telefone = telefone;
		this.placa = placa;
		this.ativo = ativo;
	}

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
	




	public String getAtivo() {
		return ativo;
	}





	public void setAtivo(String ativo) {
		this.ativo = ativo;
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





	@Override
	public String toString() {
		return "CadastroDeCarrosEstacionamentoDTO [id=" + id + ", nome=" + nome + ", setor=" + setor + ", marca="
				+ marca + ", modelo=" + modelo + ", cor=" + cor + ", telefone=" + telefone + ", placa=" + placa
				+ ", ativo=" + ativo + "]";
	}



	



	
	
	
}
