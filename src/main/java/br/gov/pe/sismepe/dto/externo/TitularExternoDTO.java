package br.gov.pe.sismepe.dto.externo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.gov.pe.sismepe.domain.PessoaTitular;
import br.gov.pe.sismepe.util.Utils;

public class TitularExternoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer matricula;
	private Integer digito;
	private String nome;
	private Integer codigoEmpresa;
	private String identidadePM;
	private String dataNascimento;
	private String sexo;
	private String parentesco; 
	private String estadoCivil;
	private String dataConcessao;
	private String dataCancelamento;
	private String dataBloqueio;
	private String dataCadastro;
	private String dataUltimaAtualizacao;
	private Integer snInvalido;
	private String cpf;
	private String tipoUsuario;
	private String enviaAns;
	private Integer operador;
	private String grauParentesco;
	private String usaCallCenter;
	private Integer digitador;
	private String status;
	private Integer idContrato;
	private Integer idPlano;
	private Integer idTabela;
	private String nomeMae;
	private String nrPisPasep;
	private String dataInclusao;
	private Long codigoPosto;
	private int numeroPatente;
	
	
	public TitularExternoDTO(PessoaTitular tit) {
		this.matricula = tit.getMatricula();
		this.digito = tit.getDigito();
		this.nome = tit.getNome();
		this.codigoEmpresa = tit.getCorporacao() == 6 
				? 28 : tit.getCorporacao() == 40 ? 29 : 0;
		
		this.nrPisPasep = tit.getNrPisPasep();
		this.dataInclusao = tit.getDataInclusao() != null 
				? Utils.ptOnlyDateToString(tit.getDataInclusao()) : "01/01/1999";
		this.codigoPosto = tit.getPosto() != null ? tit.getPosto().getId() : 0;
		this.nomeMae = tit.getNomeMae();
		this.dataNascimento = tit.getDataNascimento() == null ? null
				: Utils.ptOnlyDateToString(tit.getDataNascimento());
		this.sexo = tit.getSexo();
		this.estadoCivil = tit.getEstadoCivil() == null ? "0" : this.formatEstadoCivil(tit.getEstadoCivil().intValue());
		this.dataConcessao = tit.getDataConcessao() == null ? null 
				: Utils.ptOnlyDateToString(tit.getDataConcessao());
		this.dataCancelamento = tit.getDataCancelamento() == null ? null
				: Utils.ptOnlyDateToString(tit.getDataCancelamento());
		this.dataCadastro = tit.getDataConcessao() == null ? "01/01/1999" 
				: Utils.ptOnlyDateToString(tit.getDataConcessao());
		
		this.dataUltimaAtualizacao = this.getLastDate(tit);
		this.cpf = tit.getCpf();
		this.idContrato = 94;
		this.idPlano = 1;
		this.idTabela = 21;
		this.tipoUsuario = "T";
		this.enviaAns = "N";
		this.operador = 3;
		this.grauParentesco = "X";
		this.usaCallCenter = "S";
		this.digitador = 3;
		this.status = "A";
		this.numeroPatente = 105;
		
	}
		
	
	private String formatEstadoCivil(Integer e) {
		switch (e) {
			case 1:
				return "S";
			case 2:
				return "C";
			case 3:
				return "V";
			case 4:
				return "D";
			case 5:
				return "A";
			
			default:
				return "0";
		}
	}
	
		
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public Integer getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(Integer codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getParentesco() {
		return parentesco;
	}
	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getDataConcessao() {
		return dataConcessao;
	}
	public void setDataConcessao(String dataConcessao) {
		this.dataConcessao = dataConcessao;
	}
	public String getDataCancelamento() {
		return dataCancelamento;
	}
	public void setDataCancelamento(String dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	public String getDataBloqueio() {
		return dataBloqueio;
	}
	public void setDataBloqueio(String dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}
	public void setDataUltimaAtualizacao(String dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}
	public Integer getSnInvalido() {
		return snInvalido;
	}
	public void setSnInvalido(Integer snInvalido) {
		this.snInvalido = snInvalido;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	

	public Integer getDigito() {
		return digito;
	}


	public void setDigito(Integer digito) {
		this.digito = digito;
	}


	public String getIdentidadePM() {
		return identidadePM;
	}


	public void setIdentidadePM(String identidadePM) {
		this.identidadePM = identidadePM;
	}

	
	public String getTipoUsuario() {
		return tipoUsuario;
	}


	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


	public String getEnviaAns() {
		return enviaAns;
	}


	public void setEnviaAns(String enviaAns) {
		this.enviaAns = enviaAns;
	}


	public Integer getOperador() {
		return operador;
	}


	public void setOperador(Integer operador) {
		this.operador = operador;
	}


	public String getGrauParentesco() {
		return grauParentesco;
	}


	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}


	public String getUsaCallCenter() {
		return usaCallCenter;
	}


	public void setUsaCallCenter(String usaCallCenter) {
		this.usaCallCenter = usaCallCenter;
	}


	public Integer getDigitador() {
		return digitador;
	}


	public void setDigitador(Integer digitador) {
		this.digitador = digitador;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getIdContrato() {
		return idContrato;
	}


	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}


	public Integer getIdPlano() {
		return idPlano;
	}


	public void setIdPlano(Integer idPlano) {
		this.idPlano = idPlano;
	}


	public Integer getIdTabela() {
		return idTabela;
	}


	public void setIdTabela(Integer idTabela) {
		this.idTabela = idTabela;
	}


	public String getNomeMae() {
		return nomeMae;
	}


	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}


	public String getNrPisPasep() {
		return nrPisPasep;
	}


	public void setNrPisPasep(String nrPisPasep) {
		this.nrPisPasep = nrPisPasep;
	}


	public String getDataInclusao() {
		return dataInclusao;
	}


	public void setDataInclusao(String dataInclusao) {
		this.dataInclusao = dataInclusao;
	}


	public Long getCodigoPosto() {
		return codigoPosto;
	}


	public void setCodigoPosto(Long codigoPosto) {
		this.codigoPosto = codigoPosto;
	}


	public int getNumeroPatente() {
		return numeroPatente;
	}


	public void setNumeroPatente(int numeroPatente) {
		this.numeroPatente = numeroPatente;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLastDate(PessoaTitular titular) {
		List<Date> dates = new ArrayList<Date>();

		if(titular.getDataAlteracao() != null) {
			dates.add(titular.getDataAlteracao());
		}

		if(titular.getDataCancelamento() != null) {
			dates.add(titular.getDataCancelamento());
		}

		if(titular.getDataConcessao() != null) {
			dates.add(titular.getDataConcessao());
		}

		if(titular.getDataCadastro() != null) {
			dates.add(titular.getDataCadastro());
		}

		return dates.size() == 0 ? null : Utils.ptOnlyDateToString(dates.stream().map(d -> d).max(Date::compareTo).get());
	}
	

}
