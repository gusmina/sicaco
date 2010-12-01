package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * ConDpaDetallePartida entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConDpaDetallePartida extends AbstractConDpaDetallePartida
		implements java.io.Serializable {

	public ConDpaDetallePartida() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConDpaDetallePartida(ConPcoPartidaContable conPcoPartidaContable,
			ConCpaConceptoPartida conCpaConceptoPartida,
			ConCueCuenta conCueCuenta, double dpaValorDebe,
			double dpaValorHaber, String dpaOtroConcepto) {
		super(conPcoPartidaContable, conCpaConceptoPartida, conCueCuenta, dpaValorDebe,
				dpaValorHaber, dpaOtroConcepto);
		// TODO Auto-generated constructor stub
	}

	public ConDpaDetallePartida(ConPcoPartidaContable conPcoPartidaContable) {
		super(conPcoPartidaContable);
		// TODO Auto-generated constructor stub
	}

	// Constructors

	
}
