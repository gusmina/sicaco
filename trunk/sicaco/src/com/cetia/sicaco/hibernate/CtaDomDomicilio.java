package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaDomDomicilio entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaDomDomicilio extends AbstractCtaDomDomicilio implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaDomDomicilio() {
	}

	/** minimal constructor */
	public CtaDomDomicilio(Integer domId, String domNombre) {
		super(domId, domNombre);
	}

	/** full constructor */
	public CtaDomDomicilio(Integer domId, String domNombre,
			String domDescripcion, Set ctaAscAsociados) {
		super(domId, domNombre, domDescripcion, ctaAscAsociados);
	}

}
