package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtaLprLineaPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaLprLineaPrestamo extends AbstractCtaLprLineaPrestamo implements
		java.io.Serializable {

	public CtaLprLineaPrestamo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CtaLprLineaPrestamo(Integer lprId, String lprNombre, Date lprDesde) {
		super(lprId, lprNombre, lprDesde);
		// TODO Auto-generated constructor stub
	}

	public CtaLprLineaPrestamo(Integer lprId, String lprNombre,
			String lprDescripcion, Date lprDesde, Date lprHasta,
			String lprOrdenAprov, Double lprComision, Integer lprMinFiadores,
			Integer lprMinGarantias, Double lprSueldoMinimo,
			Integer lprMinRefPersonales, Integer lprMinRefComerciales,
			Set ctaDexDescuentosExternoses, Set ctaTprTipoPrestamos) {
		super(lprId, lprNombre, lprDescripcion, lprDesde, lprHasta, lprOrdenAprov,
				lprComision, lprMinFiadores, lprMinGarantias, lprSueldoMinimo,
				lprMinRefPersonales, lprMinRefComerciales, ctaDexDescuentosExternoses,
				ctaTprTipoPrestamos);
		// TODO Auto-generated constructor stub
	}

	

}
