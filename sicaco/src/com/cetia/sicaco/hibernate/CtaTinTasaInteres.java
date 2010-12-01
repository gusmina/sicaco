package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaTinTasaInteres entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTinTasaInteres extends AbstractCtaTinTasaInteres implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaTinTasaInteres() {
	}

	/** minimal constructor */
	public CtaTinTasaInteres(Integer tinId, String tinNombre, Double tinTasa) {
		super(tinId, tinNombre, tinTasa);
	}

	/** full constructor */
	public CtaTinTasaInteres(Integer tinId, String tinNombre, Double tinTasa,
			String tinDescripcion, Set ctaSegSeguroses, Set ctaPrePrestamos,
			Set ctaTprTipoPrestamos, Set ctaTahTipoAhorros,
			Set ctaHtsHistorialTasas) {
		super(tinId, tinNombre, tinTasa, tinDescripcion, ctaSegSeguroses,
				ctaPrePrestamos, ctaTprTipoPrestamos, ctaTahTipoAhorros,
				ctaHtsHistorialTasas);
	}

}
