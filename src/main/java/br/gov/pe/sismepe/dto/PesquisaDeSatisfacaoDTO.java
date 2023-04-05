package br.gov.pe.sismepe.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.gov.pe.sismepe.domain.PesquisaDeSatisfacao;
import br.gov.pe.sismepe.domain.PesquisaProjeto;
import br.gov.pe.sismepe.domain.Pessoa;

public class PesquisaDeSatisfacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date dataCadastro;
	private Date dataExpiracao;
	private String pesquisaExpirada;
	private PesquisaProjeto pesquisaProjeto;
	private Long codigoAtendimento;
	private List<PesquisaPerguntaRespostaDTO> pesquisaPRDTO;

	public PesquisaDeSatisfacao toModel() {
		PesquisaDeSatisfacao ps = new PesquisaDeSatisfacao();
		ps.setId(this.id);	
		ps.setDataCadastro(this.dataCadastro);
		ps.setDataExpiracao(this.dataExpiracao);
		ps.setPesquisaExpirada(this.pesquisaExpirada);
		ps.setPesquisaProjeto(this.pesquisaProjeto);
		ps.setCodigoAtendimento(this.codigoAtendimento);

		return ps;
	}

	public PesquisaDeSatisfacaoDTO() {

	}
	
	public PesquisaDeSatisfacaoDTO(Long id, Date dataCadastro,String pesquisaExpirada, Date dataExpiracao, 
			Pessoa pessoa, PesquisaProjeto pesquisaProjeto, Long codigoAtendimento, String pergunta,
			String resposta, List<PesquisaPerguntaRespostaDTO> pesquisaPRDTO) {
		super();
		this.id = id;
		this.dataCadastro = dataCadastro;
		this.dataExpiracao = dataExpiracao;
		this.pesquisaExpirada = pesquisaExpirada;
		this.pesquisaProjeto = pesquisaProjeto;
		this.codigoAtendimento = codigoAtendimento;
		this.pesquisaPRDTO = pesquisaPRDTO;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataExpiracao() {
		return dataExpiracao;
	}
	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	public PesquisaProjeto getPesquisaProjeto() {
		return pesquisaProjeto;
	}
	public void setPesquisaProjeto(PesquisaProjeto pesquisaProjeto) {
		this.pesquisaProjeto = pesquisaProjeto;
	}
	public Long getCodigoAtendimento() {
		return codigoAtendimento;
	}
	public void setCodigoAtendimento(Long codigoAtendimento) {
		this.codigoAtendimento = codigoAtendimento;
	}


	public String getPesquisaExpirada() {
		return pesquisaExpirada;
	}
	public void setPesquisaExpirada(String pesquisaExpirada) {
		this.pesquisaExpirada = pesquisaExpirada;
	}
	public List<PesquisaPerguntaRespostaDTO> getPesquisaPRDTO() {
		return pesquisaPRDTO;
	}
	public void setPesquisaPRDTO(List<PesquisaPerguntaRespostaDTO> pesquisaPRDTO) {
		this.pesquisaPRDTO = pesquisaPRDTO;
	}

}