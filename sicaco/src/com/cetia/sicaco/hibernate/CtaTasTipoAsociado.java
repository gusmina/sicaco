package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaTasTipoAsociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTasTipoAsociado extends AbstractCtaTasTipoAsociado implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaTasTipoAsociado() {
	}

	/** minimal constructor */
	public CtaTasTipoAsociado(Integer tasId, String tasNombre) {
		super(tasId, tasNombre);
	}

	/** full constructor */
	public CtaTasTipoAsociado(Integer tasId, String tasNombre,
			String tasDescripcion, Set ctaAscAsociados) {
		super(tasId, tasNombre, tasDescripcion, ctaAscAsociados);
	}

}
