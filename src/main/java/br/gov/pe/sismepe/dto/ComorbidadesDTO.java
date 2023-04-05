package br.gov.pe.sismepe.dto;

import java.util.Set;

import br.gov.pe.sismepe.domain.Comorbidade;

public class ComorbidadesDTO {
	
	public String temDiabetes;
	public String temPneumopatias;
	public String temHipertensao;
	public String temInsuficiencia;
	public String fuma;
	public String temDoencaCerebro;
	public String temDoencaRenal;
	public String imunossuprimidos;
	public String temAnemia;
	public String temObesidade;
	
	
	public ComorbidadesDTO(Set<Comorbidade> set) {
		
		this.initDefault();
		
		for(Comorbidade c: set) {
			
			switch (c.getDescricao()) {
			
			case "DIABETE MELLITUS":
				
				this.temDiabetes = "SIM";
				break;
			case "PNEUMOPATIAS CRÔNICAS GRAVES":
				this.temPneumopatias = "SIM";
				break;
			case "HIPERTENSÃO ARTERIAL RESISTENTE\r\n"
					+ "(USO DE DUAS/TRÊS MEDICAÇÕES)":
				this.temHipertensao = "SIM";
				break;
			case "Insuficiência Cardíaca":
				this.temInsuficiencia = "SIM";
				break;
			case "FUMA":
				this.fuma = "SIM";
				break;
			case "DOENÇA CEREBROVASCULAR":
				this.temDoencaCerebro = "SIM";
				break;
			case "DOENÇA RENAL CRÔNICA":
				this.temDoencaRenal = "SIM";
				break;
			case "IMUNOSSUPRIMIDOS":
				this.imunossuprimidos = "SIM";
				break;
			case "ANEMIA FALCIFORME":
				this.temAnemia = "SIM";
				break;
			case "OBESIDADE MÓRBIDA (IMC>=40)":
				this.temObesidade = "SIM";
				break;
			default:
				break;
			}
			
			
		}
	}
	
	
	public void initDefault() {
		this.temDiabetes = "NÃO";
		this.temPneumopatias = "NÃO";
		this.temHipertensao = "NÃO";
		this.temInsuficiencia = "NÃO";
		this.fuma = "NÃO";
		this.temDoencaCerebro = "NÃO";
		this.temDoencaRenal = "NÃO";
		this.imunossuprimidos = "NÃO";
		this.temAnemia = "NÃO";
		this.temObesidade = "NÃO";
	}


	public String getTemDiabetes() {
		return temDiabetes;
	}


	public void setTemDiabetes(String temDiabetes) {
		this.temDiabetes = temDiabetes;
	}


	public String getTemPneumopatias() {
		return temPneumopatias;
	}


	public void setTemPneumopatias(String temPneumopatias) {
		this.temPneumopatias = temPneumopatias;
	}


	public String getTemHipertensao() {
		return temHipertensao;
	}


	public void setTemHipertensao(String temHipertensao) {
		this.temHipertensao = temHipertensao;
	}


	public String getTemInsuficiencia() {
		return temInsuficiencia;
	}


	public void setTemInsuficiencia(String temInsuficiencia) {
		this.temInsuficiencia = temInsuficiencia;
	}


	public String getFuma() {
		return fuma;
	}


	public void setFuma(String fuma) {
		this.fuma = fuma;
	}


	public String getTemDoencaCerebro() {
		return temDoencaCerebro;
	}


	public void setTemDoencaCerebro(String temDoencaCerebro) {
		this.temDoencaCerebro = temDoencaCerebro;
	}


	public String getTemDoencaRenal() {
		return temDoencaRenal;
	}


	public void setTemDoencaRenal(String temDoencaRenal) {
		this.temDoencaRenal = temDoencaRenal;
	}


	public String getImunossuprimidos() {
		return imunossuprimidos;
	}


	public void setImunossuprimidos(String imunossuprimidos) {
		this.imunossuprimidos = imunossuprimidos;
	}


	public String getTemAnemia() {
		return temAnemia;
	}


	public void setTemAnemia(String temAnemia) {
		this.temAnemia = temAnemia;
	}


	public String getTemObesidade() {
		return temObesidade;
	}


	public void setTemObesidade(String temObesidade) {
		this.temObesidade = temObesidade;
	}
	
	
	
}
