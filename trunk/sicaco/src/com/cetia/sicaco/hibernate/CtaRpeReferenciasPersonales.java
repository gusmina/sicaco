package com.cetia.sicaco.hibernate;

/**
 * CtaRpeReferenciasPersonales entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaRpeReferenciasPersonales extends
		AbstractCtaRpeReferenciasPersonales implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaRpeReferenciasPersonales() {
	}

	/** minimal constructor */
	public CtaRpeReferenciasPersonales(Integer rpeId, String rpeApellidos,
			String rpeNombres, String rpeTelefono, String rpeDireccion,
			String rpeEstadoValidez) {
		super(rpeId, rpeApellidos, rpeNombres, rpeTelefono, rpeDireccion,
				rpeEstadoValidez);
	}

	/** full constructor */
	public CtaRpeReferenciasPersonales(Integer rpeId,
			CtaPrePrestamo ctaPrePrestamo, Integer parId, String rpeApellidos,
			String rpeNombres, String rpeTelefono, String rpeDireccion,
			String rpeEstadoValidez) {
		super(rpeId, ctaPrePrestamo, parId, rpeApellidos, rpeNombres,
				rpeTelefono, rpeDireccion, rpeEstadoValidez);
	}

}
