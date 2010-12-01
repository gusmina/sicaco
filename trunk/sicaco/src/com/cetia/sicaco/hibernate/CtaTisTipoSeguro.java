package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtaTisTipoSeguro entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTisTipoSeguro extends AbstractCtaTisTipoSeguro implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaTisTipoSeguro() {
	}

	/** minimal constructor */
	public CtaTisTipoSeguro(Integer tisId, String tisNombre, String tisPoliza) {
		super(tisId, tisNombre, tisPoliza);
	}

	/** full constructor */
	public CtaTisTipoSeguro(Integer tisId, CtaPlmPlanMeses ctaPlmPlanMeses,
			CtaTntTipoNota ctaTntTipoNota, String tisNombre,
			String tisDescripcion, String tisPoliza, Date tisInicioPoliza,
			Date tisFinPoliza, Double tisMontoBasico, Double tisCostoAnual,
			Set ctaSegSeguroses) {
		super(tisId, ctaPlmPlanMeses, ctaTntTipoNota, tisNombre, tisDescripcion,
				tisPoliza, tisInicioPoliza, tisFinPoliza, tisMontoBasico,
				tisCostoAnual, ctaSegSeguroses);
		// TODO Auto-generated constructor stub
	}
	
}
