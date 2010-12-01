package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CtaSmaSaldosxmesActivo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaSmaSaldosxmesActivo extends AbstractCtaSmaSaldosxmesActivo
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaSmaSaldosxmesActivo() {
	}

	public CtaSmaSaldosxmesActivo(Integer smaId,
			CtaCahCuentaAhorro ctaCahCuentaAhorro, Double smaInterAcum,
			Double smaSaldoAcum, Date smaFecha) {
		super(smaId, ctaCahCuentaAhorro, smaInterAcum, smaSaldoAcum, smaFecha);
		// TODO Auto-generated constructor stub
	}

	public CtaSmaSaldosxmesActivo(Integer smaId, Double smaInterAcum,
			Double smaSaldoAcum, Date smaFecha) {
		super(smaId, smaInterAcum, smaSaldoAcum, smaFecha);
		// TODO Auto-generated constructor stub
	}

}
