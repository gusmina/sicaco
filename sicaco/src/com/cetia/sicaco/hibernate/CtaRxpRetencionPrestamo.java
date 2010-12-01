package com.cetia.sicaco.hibernate;

/**
 * CtaRxpRetencionPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaRxpRetencionPrestamo extends AbstractCtaRxpRetencionPrestamo
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaRxpRetencionPrestamo() {
	}

	/** full constructor */
	public CtaRxpRetencionPrestamo(Integer rxpId,
			CtaPrePrestamo ctaPrePrestamo, String rxpNombre, Double rxpMonto) {
		super(rxpId, ctaPrePrestamo, rxpNombre, rxpMonto);
	}

}
