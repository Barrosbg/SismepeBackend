package br.gov.pe.sismepe.filtro;

import java.io.Serializable;

import javax.persistence.Query;

import br.gov.pe.sismepe.domain.MotivoAlta;
import br.gov.pe.sismepe.domain.enums.TipoMotivoAltaEnum;

public class FiltroMotivoAlta extends FiltroGenerico<MotivoAlta> implements Serializable {

	private static final long serialVersionUID = 2002417971460555754L;
	private Long id;
	private TipoMotivoAltaEnum tipo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean isConsultaSQL() {
		return true;
	}

	@Override
	public String getSQLExisteEntidade() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	public TipoMotivoAltaEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoMotivoAltaEnum tipo) {
		this.tipo = tipo;
	}

	@Override
	public String getSelectQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParametros(Query query) {
		// TODO Auto-generated method stub
		
	}
}