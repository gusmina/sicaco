package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtaTahTipoAhorro entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTahTipoAhorro extends AbstractCtaTahTipoAhorro implements
		java.io.Serializable {

	public CtaTahTipoAhorro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CtaTahTipoAhorro(Integer tahId, CtaPlmPlanMeses ctaPlmPlanMeses,
			CtaTinTasaInteres ctaTinTasaInteres,
			CtaLahLineaAhorro ctaLahLineaAhorro, String tahNombre,
			String tahDescripcion, Date tahFechaFin, Set ctaCahCuentaAhorros) {
		super(tahId, ctaPlmPlanMeses, ctaTinTasaInteres, ctaLahLineaAhorro, tahNombre,
				tahDescripcion, tahFechaFin, ctaCahCuentaAhorros);
		// TODO Auto-generated constructor stub
	}

	public CtaTahTipoAhorro(Integer tahId, CtaPlmPlanMeses ctaPlmPlanMeses,
			CtaTinTasaInteres ctaTinTasaInteres,
			CtaLahLineaAhorro ctaLahLineaAhorro, String tahNombre) {
		super(tahId, ctaPlmPlanMeses, ctaTinTasaInteres, ctaLahLineaAhorro, tahNombre);
		// TODO Auto-generated constructor stub
	}

			
}
