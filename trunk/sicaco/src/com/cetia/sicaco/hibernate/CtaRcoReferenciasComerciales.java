package com.cetia.sicaco.hibernate;

/**
 * CtaRcoReferenciasComerciales entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaRcoReferenciasComerciales extends
		AbstractCtaRcoReferenciasComerciales implements java.io.Serializable {

	// Constructors
	
	public CtaRcoReferenciasComerciales() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CtaRcoReferenciasComerciales(Integer rcoId,
			CtaPrePrestamo ctaPrePrestamo, String rcoReferencia,
			String rcoSucursal, Float rcoMonto, String rcoEstado,
			String rcoConcepto) {
		super(rcoId, ctaPrePrestamo, rcoReferencia, rcoSucursal, rcoMonto, rcoEstado,
				rcoConcepto);
		// TODO Auto-generated constructor stub
	}

	



}
