package br.gov.pe.sismepe.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="equipamento_cad")
public class Equipamento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cd_equipamento")
	private Integer id;
     
	@Column(name="tp_equipamento")
	private String tipo_equipamento;
	

	@Column(name="numero_serie")
	private String numeroSerie;

	@Column(name="patrimonio_numero")
	private String patrimonioNumero;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
//	@DateTimeFormat(pattern = "dd.MM HH:mm:ss")
	@Column(name="dt_cadastro")
	private Date dt_cadastro;
	
	@Column(name="marca")
	private String marca;
	
	@Column(name="ativo")
	private String ativo;
	
	@Column(name="status_equip")
	private String status;

	@ManyToOne
	@JoinColumn(name="cd_responsavel")
	private Pessoa cd_responsavel;
	
	@ManyToOne
	@JoinColumn(name="setor_equipe")
	private Setor setor_equipe;

	
//    @OneToMany(mappedBy = "cd_equipamento")
//    @JsonIgnoreProperties(value = {"cd_equipamento","id"})
	@Transient
    private List<Lancamento> lancamento;

	public Equipamento() {
		super();
	}



	




	public Equipamento(Integer id, String tipo_equipamento, String numeroSerie, String patrimonioNumero,
			Date dt_cadastro, String marca, String ativo, String status, Pessoa cd_responsavel, Setor setor_equipe,
			List<Lancamento> lancamento) {
		super();
		this.id = id;
		this.tipo_equipamento = tipo_equipamento;
		this.numeroSerie = numeroSerie;
		this.patrimonioNumero = patrimonioNumero;
		this.dt_cadastro = dt_cadastro;
		this.marca = marca;
		this.ativo = ativo;
		this.status = status;
		this.cd_responsavel = cd_responsavel;
		this.setor_equipe = setor_equipe;
		this.lancamento = lancamento;
	}








	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public List<Lancamento>  getLancamento() {
		return lancamento;
	}


	public void setLancamento(List<Lancamento> lanc) {
		this.lancamento = lanc;
	}


	public String getTipo_equipamento() {
		return tipo_equipamento;
	}


	public void setTipo_equipamento(String tipo_equipamento) {
		this.tipo_equipamento = tipo_equipamento;
	}


	public String getNumeroSerie() {
		return numeroSerie;
	}


	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}


	public String getPatrimonioNumero() {
		return patrimonioNumero;
	}





	public Date getDt_cadastro() {
		return dt_cadastro;
	}


	public void setDt_cadastro(Date dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getAtivo() {
		return ativo;
	}


	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}


	
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public void setPatrimonioNumero(String patrimonioNumero) {
		this.patrimonioNumero = patrimonioNumero;
	}
	


	public Pessoa getCd_responsavel() {
		return cd_responsavel;
	}








	public void setCd_responsavel(Pessoa cd_responsavel) {
		this.cd_responsavel = cd_responsavel;
	}








	public Setor getSetor_equipe() {
		return setor_equipe;
	}








	public void setSetor_equipe(Setor setor_equipe) {
		this.setor_equipe = setor_equipe;
	}








	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Equipamento other = (Equipamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CadastroDeEquipamentosDescricao [id=" + id + ", tipo_equipamento="
				+ tipo_equipamento + ", numeroSerie=" + numeroSerie + ", patrimonioNumero=" + patrimonioNumero
				+ ", dt_cadastro=" + dt_cadastro + ", marca=" + marca + ", ativo=" + ativo + "]";
	}









	

	
}
