package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CtaInaIngresosxasociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaInaIngresosxasociado extends AbstractCtaInaIngresosxasociado
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaInaIngresosxasociado() {
	}

	/** minimal constructor */
	public CtaInaIngresosxasociado(Integer inaId, Date inaFechaIngreso) {
		super(inaId, inaFechaIngreso);
	}

	/** full constructor */
	public CtaInaIngresosxasociado(Integer inaId, CtaNotNotas ctaNotNotas,
			CtaAscAsociado ctaAscAsociado, Date inaFechaIngreso,
			Date inaFechaSalida) {
		super(inaId, ctaNotNotas, ctaAscAsociado, inaFechaIngreso,
				inaFechaSalida);
	}

}
