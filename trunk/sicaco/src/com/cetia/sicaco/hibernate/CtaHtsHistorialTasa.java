package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CtaHtsHistorialTasa entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaHtsHistorialTasa extends AbstractCtaHtsHistorialTasa implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaHtsHistorialTasa() {
	}

	/** minimal constructor */
	public CtaHtsHistorialTasa(Double htsTasa, Date htsFechaIni,
			Date htsFechaFin) {
		super(htsTasa, htsFechaIni, htsFechaFin);
	}

	/** full constructor */
	public CtaHtsHistorialTasa(CtaTinTasaInteres ctaTinTasaInteres,
			Double htsTasa, Date htsFechaIni, Date htsFechaFin) {
		super(ctaTinTasaInteres, htsTasa, htsFechaIni, htsFechaFin);
	}

}
