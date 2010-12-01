package com.cetia.sicaco.hibernate;

/**
 * CtaFxpFiadorPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaFxpFiadorPrestamo extends AbstractCtaFxpFiadorPrestamo
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaFxpFiadorPrestamo() {
	}

	/** minimal constructor */
	public CtaFxpFiadorPrestamo(Integer fxpId, CtaPrePrestamo ctaPrePrestamo,
			String fxpEstado) {
		super(fxpId, ctaPrePrestamo, fxpEstado);
	}

	/** full constructor */
	public CtaFxpFiadorPrestamo(Integer fxpId, CtaPrePrestamo ctaPrePrestamo,
			CtaPxtPersonaExterna ctaPxtPersonaExterna,
			CtaTfiTipoFiador ctaTfiTipoFiador, CtaAscAsociado ctaAscAsociado,
			String fxpEstado) {
		super(fxpId, ctaPrePrestamo, ctaPxtPersonaExterna, ctaTfiTipoFiador,
				ctaAscAsociado, fxpEstado);
	}

}
