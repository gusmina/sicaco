package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaTcoTipoContrato entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTcoTipoContrato extends AbstractCtaTcoTipoContrato implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaTcoTipoContrato() {
	}

	/** minimal constructor */
	public CtaTcoTipoContrato(Integer tcoId, String tcoNombre) {
		super(tcoId, tcoNombre);
	}

	/** full constructor */
	public CtaTcoTipoContrato(Integer tcoId, String tcoNombre,
			String tcoDescripcion, Set ctaAscAsociados) {
		super(tcoId, tcoNombre, tcoDescripcion, ctaAscAsociados);
	}

}
