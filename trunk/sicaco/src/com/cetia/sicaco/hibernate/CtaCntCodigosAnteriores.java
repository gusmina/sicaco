package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CtaCntCodigosAnteriores entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaCntCodigosAnteriores extends AbstractCtaCntCodigosAnteriores
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaCntCodigosAnteriores() {
	}

	/** minimal constructor */
	public CtaCntCodigosAnteriores(Integer cntId, String cntCod,
			Date cntFechaIni) {
		super(cntId, cntCod, cntFechaIni);
	}

	/** full constructor */
	public CtaCntCodigosAnteriores(Integer cntId,
			CtaAscAsociado ctaAscAsociado, String cntCod, Date cntFechaIni,
			Date cntFechaFin) {
		super(cntId, ctaAscAsociado, cntCod, cntFechaIni, cntFechaFin);
	}

}
