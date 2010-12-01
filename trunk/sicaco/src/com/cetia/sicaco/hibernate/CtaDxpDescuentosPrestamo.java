package com.cetia.sicaco.hibernate;

/**
 * CtaDxpDescuentosPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaDxpDescuentosPrestamo extends AbstractCtaDxpDescuentosPrestamo
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaDxpDescuentosPrestamo() {
	}

	/** full constructor */
	public CtaDxpDescuentosPrestamo(Integer dxpId,
			CtaPrePrestamo ctaPrePrestamo, Long casCuenta, Double dxpMonto, Double dxpInteresPagado) {
		super(dxpId, ctaPrePrestamo, casCuenta, dxpMonto, dxpInteresPagado);
	}

}
