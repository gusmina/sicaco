package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaEtrEmpresaTrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaEtrEmpresaTrabajo extends AbstractCtaEtrEmpresaTrabajo
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaEtrEmpresaTrabajo() {
	}

	/** minimal constructor */
	public CtaEtrEmpresaTrabajo(Integer etrId, String etrNombre,
			String etrEstado) {
		super(etrId, etrNombre, etrEstado);
	}

	/** full constructor */
	public CtaEtrEmpresaTrabajo(Integer etrId, String etrNombre,
			String etrDescripcion, String etrEstado,
			Set ctaDptDepartamentoTrabajos) {
		super(etrId, etrNombre, etrDescripcion, etrEstado,
				ctaDptDepartamentoTrabajos);
	}

}
