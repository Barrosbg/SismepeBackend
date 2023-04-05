package br.gov.pe.sismepe.dto.externo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.gov.pe.sismepe.domain.PessoaDependente;
import br.gov.pe.sismepe.util.Utils;

public class DependenteExternoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer matricula;
	private Integer sequencial;
	private String nome;
	private Integer codigoEmpresa;
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
	private String solus_tipousuario;
	private String solus_EnviaAns;
	private Integer solus_Operador;
	private String solus_GrauParentesco;
	private String solus_UsaCallCenter;
	private Integer solus_Digitador;
	private String solus_STATUS;
	private Integer solus_IdContrato;
	private Integer solus_IdPlano;
	private Integer solus_IdTabela;
	private String solus_NomeMae;
	
	
	public DependenteExternoDTO(PessoaDependente dep) {
		this.matricula = dep.getMatricula();
		this.sequencial = dep.getSequencial();
		this.nome = dep.getNome();
		this.codigoEmpresa = dep.getCorporacao() == 6 
				? 28 : dep.getCorporacao() == 40 ? 29 : 0;
		
		this.dataNascimento = Utils.ptOnlyDateToString(dep.getDataNascimento());
		this.sexo = dep.getSexo();
		this.parentesco = this.formatParentesco(dep.getParentesco().getId().intValue());
		this.estadoCivil = dep.getEstadoCivil() == null ? "0" : this.formatEstadoCivil(dep.getEstadoCivil().intValue());
		this.dataConcessao = dep.getDataConcessao() == null ? null : Utils.ptOnlyDateToString(dep.getDataConcessao());
		this.dataCancelamento = dep.getDataCancelamento() == null ? null : Utils.ptOnlyDateToString(dep.getDataCancelamento());
		this.dataBloqueio = dep.getDataBloqueio() == null ? null : Utils.ptOnlyDateToString(dep.getDataBloqueio());
		this.dataCadastro = dep.getDataConcessao() == null ? "01/01/1999" 
				: Utils.ptOnlyDateToString(dep.getDataConcessao());
		
		this.dataUltimaAtualizacao = this.getLastDate(dep);
		this.snInvalido = dep.getInvalido().equals("S") ? 1 : 0;
		this.cpf = dep.getCpf();
		this.solus_tipousuario = "D";
		this.solus_EnviaAns = "N";
		this.solus_Operador = 3;
		this.solus_GrauParentesco = "X";
		this.solus_UsaCallCenter = "S";
		this.solus_Digitador = 3;
		this.solus_STATUS = "A";
		this.solus_IdContrato = 94;
		this.solus_IdPlano = 1;
		this.solus_IdTabela = 21;
		this.solus_NomeMae = dep.getNomeMae();
		
	}
	
	
	private String formatParentesco(Integer p) {
		switch (p) {
			case 1:
				return "F";
			case 2:
				return "O";
			case 4:
				return "M";
			case 6:
				return "P";
			case 10:
				return "H";
			case 8:
				return "I";
			case 9:
				return "A";
			case 22:
				return "E";
			case 3:
				return "E";
			case 5:
				return "J";
			default:
				return "0";
		}
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
	public Integer getSequencial() {
		return sequencial;
	}
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
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
	public String getsolus_tipousuario() {
		return solus_tipousuario;
	}
	public void setsolus_tipousuario(String sOLUS_tipousuario) {
		solus_tipousuario = sOLUS_tipousuario;
	}
	public String getsolus_EnviaAns() {
		return solus_EnviaAns;
	}
	public void setsolus_EnviaAns(String sOLUS_EnviaAns) {
		solus_EnviaAns = sOLUS_EnviaAns;
	}
	public Integer getsolus_Operador() {
		return solus_Operador;
	}
	public void setsolus_Operador(Integer sOLUS_Operador) {
		solus_Operador = sOLUS_Operador;
	}
	public String getsolus_GrauParentesco() {
		return solus_GrauParentesco;
	}
	public void setsolus_GrauParentesco(String sOLUS_GrauParentesco) {
		solus_GrauParentesco = sOLUS_GrauParentesco;
	}
	public String getsolus_UsaCallCenter() {
		return solus_UsaCallCenter;
	}
	public void setsolus_UsaCallCenter(String sOLUS_UsaCallCenter) {
		solus_UsaCallCenter = sOLUS_UsaCallCenter;
	}
	public Integer getsolus_Digitador() {
		return solus_Digitador;
	}
	public void setsolus_Digitador(Integer sOLUS_Digitador) {
		solus_Digitador = sOLUS_Digitador;
	}
	public String getsolus_STATUS() {
		return solus_STATUS;
	}
	public void setsolus_STATUS(String sOLUS_STATUS) {
		solus_STATUS = sOLUS_STATUS;
	}
	public Integer getsolus_IdContrato() {
		return solus_IdContrato;
	}
	public void setsolus_IdContrato(Integer sOLUS_IdContrato) {
		solus_IdContrato = sOLUS_IdContrato;
	}
	public Integer getsolus_IdPlano() {
		return solus_IdPlano;
	}
	public void setsolus_IdPlano(Integer sOLUS_IdPlano) {
		solus_IdPlano = sOLUS_IdPlano;
	}
	public Integer getsolus_IdTabela() {
		return solus_IdTabela;
	}
	public void setsolus_IdTabela(Integer sOLUS_IdTabela) {
		solus_IdTabela = sOLUS_IdTabela;
	}
	public String getsolus_NomeMae() {
		return solus_NomeMae;
	}
	public void setsolus_NomeMae(String sOLUS_NomeMae) {
		solus_NomeMae = sOLUS_NomeMae;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getLastDate(PessoaDependente dependente) {
		List<Date> dates = new ArrayList<Date>();
		
		if(dependente.getUltimaAtualizacao() != null) {
			dates.add(dependente.getUltimaAtualizacao());
		}
		
		if(dependente.getDataBloqueio() != null) {
			dates.add(dependente.getDataBloqueio());
		}
		
		if(dependente.getDataCancelamento() != null) {
			dates.add(dependente.getDataCancelamento());
		}

		if(dependente.getDataConcessao() != null) {
			dates.add(dependente.getDataConcessao());
		}

		if(dependente.getDataCadastro() != null) {
			dates.add(dependente.getDataCadastro());
		}
				
		return dates.size() == 0 ? null : Utils.ptOnlyDateToString(dates.stream().map(d -> d).max(Date::compareTo).get());
	}
	
	
	

}
