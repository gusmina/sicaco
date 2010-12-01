package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaTtrTipoTransaccion entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTtrTipoTransaccion extends AbstractCtaTtrTipoTransaccion
		implements java.io.Serializable {

	// Constructors

	public CtaTtrTipoTransaccion(Integer ttrId, String ttrNombre,
			String ttrDescripcion, String ttrUso, Set conCpaConceptoPartidas,
			Set ctaTxaTransaccionxcuentaAsociados) {
		super(ttrId, ttrNombre, ttrDescripcion, ttrUso, conCpaConceptoPartidas,
				ctaTxaTransaccionxcuentaAsociados);
		// TODO Auto-generated constructor stub
	}

	/** default constructor */
	public CtaTtrTipoTransaccion() {
	}

	/** minimal constructor */
	public CtaTtrTipoTransaccion(Integer ttrId, String ttrNombre, String ttrUso) {
		super(ttrId, ttrNombre, ttrUso);
	}

	

}
