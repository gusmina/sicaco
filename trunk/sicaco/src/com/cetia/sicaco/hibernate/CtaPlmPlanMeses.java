package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaPlmPlanMeses entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaPlmPlanMeses extends AbstractCtaPlmPlanMeses implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaPlmPlanMeses() {
	}

	/** minimal constructor */
	public CtaPlmPlanMeses(Integer plmId, String plmNombre, Integer plmDuracion) {
		super(plmId, plmNombre, plmDuracion);
	}

	/** full constructor */
	public CtaPlmPlanMeses(Integer plmId, String plmNombre,
			String plmDescripcion, Integer plmDuracion,
			Set ctaTprTipoPrestamos, Set ctaTisTipoSeguros,
			Set ctaTahTipoAhorros) {
		super(plmId, plmNombre, plmDescripcion, plmDuracion,
				ctaTprTipoPrestamos, ctaTisTipoSeguros, ctaTahTipoAhorros);
	}

}
