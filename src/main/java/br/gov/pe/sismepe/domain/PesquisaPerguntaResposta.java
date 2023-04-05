package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.pe.sismepe.dto.PesquisaDeSatisfacaoDTO;


@Entity
@Table(name="pesquisa_pergunta_resposta")
public class PesquisaPerguntaResposta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CD_PESQUISA_RESP")
	private Long id;
	
	@Column(name="CD_PESQUISA")
	private Long Pesquisa;
	
	@Column(name="CD_PERGUNTA")
	private int pergunta;
	
	@Column(name="DESC_RESPOSTA")
	private String resposta;
	
	
	
	public PesquisaPerguntaResposta() {
		super();
	}
	
	

	public PesquisaPerguntaResposta(Long id, Long Pesquisa, int pergunta, String resposta) {
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

	public void setPesquisa(Long Pesquisa) {
		this.Pesquisa = Pesquisa;
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Pesquisa == null) ? 0 : Pesquisa.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PesquisaPerguntaResposta other = (PesquisaPerguntaResposta) obj;
		if (Pesquisa == null) {
			if (other.Pesquisa != null)
				return false;
		} else if (!Pesquisa.equals(other.Pesquisa))
			return false;
		return true;
	}
	
	
	
	
}
