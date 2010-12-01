package com.cetia.sicaco.hibernate;

/**
 * CtaEasEstadoAsociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaEasEstadoAsociado extends AbstractCtaEasEstadoAsociado
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaEasEstadoAsociado() {
	}

	/** minimal constructor */
	public CtaEasEstadoAsociado(Integer easId, String easNombre) {
		super(easId, easNombre);
	}

	/** full constructor */
	public CtaEasEstadoAsociado(Integer easId, String easNombre,
			String easDescripcion) {
		super(easId, easNombre, easDescripcion);
	}

}
