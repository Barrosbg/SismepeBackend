package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;

import br.gov.pe.sismepe.domain.PesquisaDeSatisfacao;
import br.gov.pe.sismepe.domain.PesquisaPerguntaResposta;


public class PesquisaPerguntaRespostaDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long Pesquisa;
	private int pergunta;
	private String resposta;
	
	
	
	public PesquisaPerguntaResposta toModel() {
		PesquisaPerguntaResposta pesquisa = new PesquisaPerguntaResposta();
		pesquisa.setId(this.id);	
		pesquisa.setPesquisa(this.Pesquisa);
		pesquisa.setPergunta(this.pergunta);
		pesquisa.setResposta(this.resposta);	
	

		return pesquisa;
	}
	
	
	public PesquisaPerguntaRespostaDTO() {
		super();
	}
	public PesquisaPerguntaRespostaDTO(Long id, Long Pesquisa, int pergunta, String resposta) {
		super();
		this.id = id;
		this.Pesquisa = Pesquisa;
		this.pergunta = pergunta;
		this.resposta = resposta;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPesquisa() {
		return Pesquisa;
	}
	public void setPesquisa(Long long1) {
		this.Pesquisa = long1;
	}
	public int getPergunta() {
		return pergunta;
	}
	public void setPergunta(int pergunta) {
		this.pergunta = pergunta;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	
	
}
