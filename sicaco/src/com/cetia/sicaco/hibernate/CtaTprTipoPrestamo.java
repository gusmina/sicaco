package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaTprTipoPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTprTipoPrestamo extends AbstractCtaTprTipoPrestamo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaTprTipoPrestamo() {
	}

	/** minimal constructor */
	public CtaTprTipoPrestamo(Integer tprId, String tprNombre) {
		super(tprId, tprNombre);
	}

	/** full constructor */
	public CtaTprTipoPrestamo(Integer tprId, CtaPlmPlanMeses ctaPlmPlanMeses,
			CtaTinTasaInteres ctaTinTasaInteres,
			CtaLprLineaPrestamo ctaLprLineaPrestamo, String tprNombre,
			String tprDescripcion, Set ctaPrePrestamos,
			Set iucPutProveedorTipoPrestamos, Set iucTutTarTprs) {
		super(tprId, ctaPlmPlanMeses, ctaTinTasaInteres, ctaLprLineaPrestamo,
				tprNombre, tprDescripcion, ctaPrePrestamos,
				iucPutProveedorTipoPrestamos, iucTutTarTprs);
		// TODO Auto-generated constructor stub
	}

}
