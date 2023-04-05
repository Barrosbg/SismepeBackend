package br.gov.pe.sismepe.dto;

import br.gov.pe.sismepe.domain.Lancamento;
import br.gov.pe.sismepe.domain.Equipamento;

public class CadastroDeEquipamentosDescricaoDTO {


	private Integer id;
    private Integer equipamento;
	private String tipo_equipamento;
	private int sn_equipamento;
	private String movimento;
	private String desc_movimento;
	private String marca;
	private int nr_patrimonio;

	public CadastroDeEquipamentosDescricaoDTO() {
		super();
	}

	

	public CadastroDeEquipamentosDescricaoDTO(Integer id, Integer equipamento, String tipo_equipamento, int sn_equipamento,
			String movimento, String desc_movimento, String marca, int nr_patrimonio) {
		super();
		this.id = id;
		this.equipamento = equipamento;
		this.tipo_equipamento = tipo_equipamento;
		this.sn_equipamento = sn_equipamento;
		this.movimento = movimento;
		this.desc_movimento = desc_movimento;
		this.marca = marca;
		this.nr_patrimonio = nr_patrimonio;
	}

	
	
//	public CadastroDeEquipamentosDescricao toModel() {
//		CadastroDeEquipamentosDescricao EquipeDesc = new CadastroDeEquipamentosDescricao();
//		EquipeDesc.setId(this.id);	
////		EquipeDesc.setEquipamento(this.equipamento);
//		EquipeDesc.setTipo_equipamento(this.tipo_equipamento);
//		EquipeDesc.setSn_equipamento(this.sn_equipamento);	
//		EquipeDesc.setMovimento(this.movimento);	
//		EquipeDesc.setDesc_movimento(this.desc_movimento);
//		EquipeDesc.setMarca(this.marca);
//		EquipeDesc.setNr_patrimonio(this.nr_patrimonio);
//		
//		return EquipeDesc;
//	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getEquipamento() {
		return equipamento;
	}



	public void setEquipamento(Integer equipamento) {
		this.equipamento = equipamento;
	}



	public String getTipo_equipamento() {
		return tipo_equipamento;
	}



	public void setTipo_equipamento(String tipo_equipamento) {
		this.tipo_equipamento = tipo_equipamento;
	}



	public int getSn_equipamento() {
		return sn_equipamento;
	}



	public void setSn_equipamento(int sn_equipamento) {
		this.sn_equipamento = sn_equipamento;
	}



	public String getMovimento() {
		return movimento;
	}



	public void setMovimento(String movimento) {
		this.movimento = movimento;
	}



	public String getDesc_movimento() {
		return desc_movimento;
	}



	public void setDesc_movimento(String desc_movimento) {
		this.desc_movimento = desc_movimento;
	}



	public String getMarca() {
		return marca;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}







	public int getNr_patrimonio() {
		return nr_patrimonio;
	}



	public void setNr_patrimonio(int nr_patrimonio) {
		this.nr_patrimonio = nr_patrimonio;
	}

	
	
}
