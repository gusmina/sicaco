package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaPxtPersonaExterna entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaPxtPersonaExterna extends AbstractCtaPxtPersonaExterna
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaPxtPersonaExterna() {
	}

	/** minimal constructor */
	public CtaPxtPersonaExterna(String pxtId, String pxtPrimerApellido,
			String pxtNombres, String pxtDireccion, Float pxtSalario,
			String pxtDui, String pxtTrabajo, String pxtTelefonoCasa) {
		super(pxtId, pxtPrimerApellido, pxtNombres, pxtDireccion, pxtSalario,
				pxtDui, pxtTrabajo, pxtTelefonoCasa);
	}

	/** full constructor */
	public CtaPxtPersonaExterna(String pxtId, String pxtPrimerApellido,
			String pxtSegundoApellido, String pxtNombres, String pxtDireccion,
			Float pxtSalario, String pxtDui, String pxtTrabajo,
			String pxtJefeInmediato, String pxtTelefonoCasa,
			String pxtTelefonoOficina, String pxtCodigoEmpleado,
			String pxtEmpresa, String pxtEmail, Set ctaFxpFiadorPrestamos,
			Set ctaCasCuentaAsociados) {
		super(pxtId, pxtPrimerApellido, pxtSegundoApellido, pxtNombres, pxtDireccion,
				pxtSalario, pxtDui, pxtTrabajo, pxtJefeInmediato, pxtTelefonoCasa,
				pxtTelefonoOficina, pxtCodigoEmpleado, pxtEmpresa, pxtEmail,
				ctaFxpFiadorPrestamos, ctaCasCuentaAsociados);
		// TODO Auto-generated constructor stub
	}
	
}
