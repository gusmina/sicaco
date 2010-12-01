package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaTgaTipoGarantia entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTgaTipoGarantia extends AbstractCtaTgaTipoGarantia implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaTgaTipoGarantia() {
	}

	/** minimal constructor */
	public CtaTgaTipoGarantia(Integer tgaId, String tgaNombre) {
		super(tgaId, tgaNombre);
	}

	/** full constructor */
	public CtaTgaTipoGarantia(Integer tgaId, String tgaNombre,
			String tgaDescripcion, Set ctaGarGarantias) {
		super(tgaId, tgaNombre, tgaDescripcion, ctaGarGarantias);
	}

}
