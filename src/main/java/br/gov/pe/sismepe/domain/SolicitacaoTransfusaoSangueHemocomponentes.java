package br.gov.pe.sismepe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="sts_hemocomponentes")
public class SolicitacaoTransfusaoSangueHemocomponentes implements Serializable {

//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Column(name="CD_SOLICITACAO")
//	private int id;
//	
//	@Column(name="CM_HEMACIA")
//	private String cm_hemacias;
//	
//	@Column(name="CM_QTD_UNIDADES")
//	private String cm_qtd_unidades;
//	
//	@Column(name="CM_COMPO_MODIFICADO")
//	private String cm_campo_modificado;
//	
//	@Column(name="CM_COMPO_MODIFICADO_DESLO")
//	private String cm_compo_modificado_deslo;
//	
//	@Column(name="CM_COMPO_MODIFICADO_FILT")
//	private String cm_compo_modificado_filt;
//	
//	@Column(name="CM_COMPO_MODIFICADO_LAV")
//	private String cm_compo_modificado_lav;
//	
//	@Column(name="CM_COMPO_MODIFICADO_FENO")
//	private String cm_compo_modificado_feno;
//	
//	@Column(name="CM_COMPO_MODIFICADO_IRRA")
//	private String cm_compo_modificado_irra;
//	
//	@Column(name="CONCENTRADO_PLAQUETAS")
//	private String concentrado_plaquetas;
//	
//	@Column(name="CP_QTD_UNIDADES")
//	private String cp_qtd_unidades;
//	
//	@Column(name="CP_COMPO_MODI_FILTRADO")
//	private String cp_compo_modi_filtrado;
//	
//	@Column(name="CP_COMPO_MODI_IRRADIADO")
//	private String cp_compo_modi_irradiado;
//	
//	@Column(name="PFC")
//	private String pfc;
//	
//	@Column(name="CM_HEMACIA")
//	private String pfc_qtd_unidade;
//	
//	@Column(name="PFC_TIPO")
//	private String pfc_tipo;
//	
//	@Column(name="CRIOPRECIPITADO")
//	private String crioprecipitado;
//	
//	@Column(name="CRIOPRECIPITADO_QTD_UNIDADES")
//	private String crioprecipitado_qtd_unidades;
//	
//	
//	
//
//	public SolicitacaoTransfusaoSangueHemocomponentes() {
//		super();
//	}
//
//
//
//
//	public SolicitacaoTransfusaoSangueHemocomponentes(int id, String cm_hemacias, String cm_qtd_unidades,
//			String cm_campo_modificado, String cm_compo_modificado_deslo, String cm_compo_modificado_filt,
//			String cm_compo_modificado_lav, String cm_compo_modificado_feno, String cm_compo_modificado_irra,
//			String concentrado_plaquetas, String cp_qtd_unidades, String cp_compo_modi_filtrado,
//			String cp_compo_modi_irradiado, String pfc, String pfc_qtd_unidade, String pfc_tipo, String crioprecipitado,
//			String crioprecipitado_qtd_unidades) {
//		super();
//		this.id = id;
//		this.cm_hemacias = cm_hemacias;
//		this.cm_qtd_unidades = cm_qtd_unidades;
//		this.cm_campo_modificado = cm_campo_modificado;
//		this.cm_compo_modificado_deslo = cm_compo_modificado_deslo;
//		this.cm_compo_modificado_filt = cm_compo_modificado_filt;
//		this.cm_compo_modificado_lav = cm_compo_modificado_lav;
//		this.cm_compo_modificado_feno = cm_compo_modificado_feno;
//		this.cm_compo_modificado_irra = cm_compo_modificado_irra;
//		this.concentrado_plaquetas = concentrado_plaquetas;
//		this.cp_qtd_unidades = cp_qtd_unidades;
//		this.cp_compo_modi_filtrado = cp_compo_modi_filtrado;
//		this.cp_compo_modi_irradiado = cp_compo_modi_irradiado;
//		this.pfc = pfc;
//		this.pfc_qtd_unidade = pfc_qtd_unidade;
//		this.pfc_tipo = pfc_tipo;
//		this.crioprecipitado = crioprecipitado;
//		this.crioprecipitado_qtd_unidades = crioprecipitado_qtd_unidades;
//	}
//
//
//
//
//	public int getId() {
//		return id;
//	}
//
//
//
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//
//
//
//	public String getCm_hemacias() {
//		return cm_hemacias;
//	}
//
//
//
//
//	public void setCm_hemacias(String cm_hemacias) {
//		this.cm_hemacias = cm_hemacias;
//	}
//
//
//
//
//	public String getCm_qtd_unidades() {
//		return cm_qtd_unidades;
//	}
//
//
//
//
//	public void setCm_qtd_unidades(String cm_qtd_unidades) {
//		this.cm_qtd_unidades = cm_qtd_unidades;
//	}
//
//
//
//
//	public String getCm_campo_modificado() {
//		return cm_campo_modificado;
//	}
//
//
//
//
//	public void setCm_campo_modificado(String cm_campo_modificado) {
//		this.cm_campo_modificado = cm_campo_modificado;
//	}
//
//
//
//
//	public String getCm_compo_modificado_deslo() {
//		return cm_compo_modificado_deslo;
//	}
//
//
//
//
//	public void setCm_compo_modificado_deslo(String cm_compo_modificado_deslo) {
//		this.cm_compo_modificado_deslo = cm_compo_modificado_deslo;
//	}
//
//
//
//
//	public String getCm_compo_modificado_filt() {
//		return cm_compo_modificado_filt;
//	}
//
//
//
//
//	public void setCm_compo_modificado_filt(String cm_compo_modificado_filt) {
//		this.cm_compo_modificado_filt = cm_compo_modificado_filt;
//	}
//
//
//
//
//	public String getCm_compo_modificado_lav() {
//		return cm_compo_modificado_lav;
//	}
//
//
//
//
//	public void setCm_compo_modificado_lav(String cm_compo_modificado_lav) {
//		this.cm_compo_modificado_lav = cm_compo_modificado_lav;
//	}
//
//
//
//
//	public String getCm_compo_modificado_feno() {
//		return cm_compo_modificado_feno;
//	}
//
//
//
//
//	public void setCm_compo_modificado_feno(String cm_compo_modificado_feno) {
//		this.cm_compo_modificado_feno = cm_compo_modificado_feno;
//	}
//
//
//
//
//	public String getCm_compo_modificado_irra() {
//		return cm_compo_modificado_irra;
//	}
//
//
//
//
//	public void setCm_compo_modificado_irra(String cm_compo_modificado_irra) {
//		this.cm_compo_modificado_irra = cm_compo_modificado_irra;
//	}
//
//
//
//
//	public String getConcentrado_plaquetas() {
//		return concentrado_plaquetas;
//	}
//
//
//
//
//	public void setConcentrado_plaquetas(String concentrado_plaquetas) {
//		this.concentrado_plaquetas = concentrado_plaquetas;
//	}
//
//
//
//
//	public String getCp_qtd_unidades() {
//		return cp_qtd_unidades;
//	}
//
//
//
//
//	public void setCp_qtd_unidades(String cp_qtd_unidades) {
//		this.cp_qtd_unidades = cp_qtd_unidades;
//	}
//
//
//
//
//	public String getCp_compo_modi_filtrado() {
//		return cp_compo_modi_filtrado;
//	}
//
//
//
//
//	public void setCp_compo_modi_filtrado(String cp_compo_modi_filtrado) {
//		this.cp_compo_modi_filtrado = cp_compo_modi_filtrado;
//	}
//
//
//
//
//	public String getCp_compo_modi_irradiado() {
//		return cp_compo_modi_irradiado;
//	}
//
//
//
//
//	public void setCp_compo_modi_irradiado(String cp_compo_modi_irradiado) {
//		this.cp_compo_modi_irradiado = cp_compo_modi_irradiado;
//	}
//
//
//
//
//	public String getPfc() {
//		return pfc;
//	}
//
//
//
//
//	public void setPfc(String pfc) {
//		this.pfc = pfc;
//	}
//
//
//
//
//	public String getPfc_qtd_unidade() {
//		return pfc_qtd_unidade;
//	}
//
//
//
//
//	public void setPfc_qtd_unidade(String pfc_qtd_unidade) {
//		this.pfc_qtd_unidade = pfc_qtd_unidade;
//	}
//
//
//
//
//	public String getPfc_tipo() {
//		return pfc_tipo;
//	}
//
//
//
//
//	public void setPfc_tipo(String pfc_tipo) {
//		this.pfc_tipo = pfc_tipo;
//	}
//
//
//
//
//	public String getCrioprecipitado() {
//		return crioprecipitado;
//	}
//
//
//
//
//	public void setCrioprecipitado(String crioprecipitado) {
//		this.crioprecipitado = crioprecipitado;
//	}
//
//
//
//
//	public String getCrioprecipitado_qtd_unidades() {
//		return crioprecipitado_qtd_unidades;
//	}
//
//
//
//
//	public void setCrioprecipitado_qtd_unidades(String crioprecipitado_qtd_unidades) {
//		this.crioprecipitado_qtd_unidades = crioprecipitado_qtd_unidades;
//	}
//
//
//
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + id;
//		return result;
//	}
//
//
//
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		SolicitacaoTransfusaoSangueHemocomponentes other = (SolicitacaoTransfusaoSangueHemocomponentes) obj;
//		if (id != other.id)
//			return false;
//		return true;
//	}
//	
//	
//	
	
}
