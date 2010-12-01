package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaDptDepartamentoTrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaDptDepartamentoTrabajo extends
		AbstractCtaDptDepartamentoTrabajo implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaDptDepartamentoTrabajo() {
	}

	/** minimal constructor */
	public CtaDptDepartamentoTrabajo(Integer dptId, String dptNombre,
			String dptUbicacion, String dptEstado) {
		super(dptId, dptNombre, dptUbicacion, dptEstado);
	}

	/** full constructor */
	public CtaDptDepartamentoTrabajo(Integer dptId,
			CtaEtrEmpresaTrabajo ctaEtrEmpresaTrabajo, String dptNombre,
			String dptUbicacion, String dptDescripcion, String dptEstado,
			String dptCentroCosto, Set ctaAscAsociados) {
		super(dptId, ctaEtrEmpresaTrabajo, dptNombre, dptUbicacion, dptDescripcion,
				dptEstado, dptCentroCosto, ctaAscAsociados);
		// TODO Auto-generated constructor stub
	}
	
}
