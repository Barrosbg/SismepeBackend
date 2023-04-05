package br.gov.pe.sismepe.dto;

import java.io.Serializable;

import br.gov.pe.sismepe.domain.Endereco;

public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = -7914885771869305772L;
	
	private Long id;
	private String dsLogradouro;
	private Long nrEndereco;
	private String dsComplemento;
	private String nmBairro;
	private Long cdCidade;
	private String cdUf;
	private String nrCep;
	private Long cdPessoa;
	
	public Endereco toEndereco() {
		Endereco end = new Endereco();
		end.setId(this.id);
		end.setLogradouro(this.dsLogradouro);
		end.setNumero(this.nrEndereco);
		end.setComplemento(this.dsComplemento);
		end.setBairro(this.nmBairro);
		end.setCidade(this.cdCidade);
		end.setUf(this.cdUf);
		end.setCep(this.nrCep);
		
		return end;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsLogradouro() {
		return dsLogradouro;
	}

	public void setDsLogradouro(String dsLogradouro) {
		this.dsLogradouro = dsLogradouro;
	}

	public Long getNrEndereco() {
		return nrEndereco;
	}

	public void setNrEndereco(Long nrEndereco) {
		this.nrEndereco = nrEndereco;
	}

	public String getDsComplemento() {
		return dsComplemento;
	}

	public void setDsComplemento(String dsComplemento) {
		this.dsComplemento = dsComplemento;
	}

	public String getNmBairro() {
		return nmBairro;
	}

	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}

	public Long getCdCidade() {
		return cdCidade;
	}

	public void setCdCidade(Long cdCidade) {
		this.cdCidade = cdCidade;
	}

	public String getCdUf() {
		return cdUf;
	}

	public void setCdUf(String cdUf) {
		this.cdUf = cdUf;
	}

	public String getNrCep() {
		return nrCep;
	}

	public void setNrCep(String nrCep) {
		this.nrCep = nrCep;
	}

	public Long getCdPessoa() {
		return cdPessoa;
	}

	public void setCdPessoa(Long cdPessoa) {
		this.cdPessoa = cdPessoa;
	}
}
