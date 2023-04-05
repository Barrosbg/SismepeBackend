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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="lancamento")
public class Lancamento implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cd_lancamento")
	private Integer id;
	

	@Column(name="tp_lancamento")
	private String tp_lancamento;
	
	
    @OneToMany(mappedBy = "cd_equipamento")
    @JsonIgnoreProperties(value = {"cd_lancamento","id"})
    private List<LancamentoEquipamento> equipamento;
	
	@ManyToOne
	@JoinColumn(name="cd_usuario_recebedor")
	private Pessoa usuarioRecebedor;
	
	@ManyToOne
	@JoinColumn(name="cd_setor_recebedor")
	private Setor cd_setor_recebedor;
	
	@ManyToOne
	@JoinColumn(name="cd_usuario_entregador")
	private tecnico cd_usuario_entregador;

	@Column(name="cd_setor_entregador")
	private Integer cd_setor_entregador;
	
	@Column(name="observacao")
	private String observacao;
	
	@Column(name="status_equip")
	private String status;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name="dt_lancamento")
	private Date dt_lancamento;
//	
//	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
//	@Column(name="dt_lancamento_saida")
//	private Date dt_lancamento_saida;
//    @OneToMany(mappedBy = "equipamento")
//    @JsonIgnoreProperties(value = {"equipamento"})
//	private List<CadastroDeEquipamentosDescricao> desc_equipamento;
	
	
	public Lancamento() {
		super();
	}
	
	public Lancamento(Integer id, String tp_lancamento, List<LancamentoEquipamento> equipamento, Pessoa cd_usuario_recebedor,
			Setor cd_setor_recebedor, tecnico cd_usuario_entregador, Integer cd_setor_entregador, String observacao,
			 Date dt_lancamento,String status) {
		super();
		this.id = id;
		this.tp_lancamento = tp_lancamento;
        this.equipamento = equipamento;
		this.usuarioRecebedor = cd_usuario_recebedor;
		this.cd_setor_recebedor = cd_setor_recebedor;
		this.cd_usuario_entregador = cd_usuario_entregador;
		this.cd_setor_entregador = cd_setor_entregador;
		this.observacao = observacao;
		this.dt_lancamento = dt_lancamento;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTp_lancamento() {
		return tp_lancamento;
	}

	public void setTp_lancamento(String tp_lancamento) {
		this.tp_lancamento = tp_lancamento;
	}

	public List<LancamentoEquipamento> getEquipamento() {
		return equipamento;
	}
	
	public void setEquipamento(List<LancamentoEquipamento> equipamento) {
		this.equipamento = equipamento;
	}

	public Pessoa getCd_usuario_recebedor() {
		return usuarioRecebedor;
	}

	public void setCd_usuario_recebedor(Pessoa cd_usuario_recebedor) {
		this.usuarioRecebedor = cd_usuario_recebedor;
	}

	public Setor getCd_setor_recebedor() {
		return cd_setor_recebedor;
	}

	public void setCd_setor_recebedor(Setor cd_setor_recebedor) {
		this.cd_setor_recebedor = cd_setor_recebedor;
	}

	public tecnico getCd_usuario_entregador() {
		return cd_usuario_entregador;
	}

	public void setCd_usuario_entregador(tecnico cd_usuario_entregador) {
		this.cd_usuario_entregador = cd_usuario_entregador;
	}

	public Integer getCd_setor_entregador() {
		return cd_setor_entregador;
	}

	public void setCd_setor_entregador(Integer cd_setor_entregador) {
		this.cd_setor_entregador = cd_setor_entregador;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDt_lancamento() {
		return dt_lancamento;
	}

	public void setDt_lancamento(Date dt_lancamento) {
		this.dt_lancamento = dt_lancamento;
	}


	
	

	public Pessoa getUsuarioRecebedor() {
		return usuarioRecebedor;
	}

	public void setUsuarioRecebedor(Pessoa usuarioRecebedor) {
		this.usuarioRecebedor = usuarioRecebedor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", tp_lancamento=" + tp_lancamento + "" 
				+ ", cd_usuario_recebedor=" + usuarioRecebedor + ", cd_setor_recebedor=" + cd_setor_recebedor
				+ ", cd_usuario_entregador=" + cd_usuario_entregador + ", cd_setor_entregador=" + cd_setor_entregador
				+ ", observacao=" + observacao 
				+ ", dt_lancamento=" + dt_lancamento + "]";
	}


    
	


	
	
	
}
