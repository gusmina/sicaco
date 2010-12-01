package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * ConTpaTipoPartida entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConTpaTipoPartida extends AbstractConTpaTipoPartida implements
		java.io.Serializable {

	public ConTpaTipoPartida() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConTpaTipoPartida(Integer tpaId, String tpaNombre,
			String tpaDescripcion, String tpaCorrelativoInicio,
			Set conPcoPartidaContables) {
		super(tpaId, tpaNombre, tpaDescripcion, tpaCorrelativoInicio,
				conPcoPartidaContables);
		// TODO Auto-generated constructor stub
	}

	public ConTpaTipoPartida(Integer tpaId, String tpaNombre) {
		super(tpaId, tpaNombre);
		// TODO Auto-generated constructor stub
	}

	// Constructors

}
