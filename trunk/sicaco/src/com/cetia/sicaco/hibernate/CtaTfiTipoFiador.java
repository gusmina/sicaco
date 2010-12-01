package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaTfiTipoFiador entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTfiTipoFiador extends AbstractCtaTfiTipoFiador implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaTfiTipoFiador() {
	}

	/** minimal constructor */
	public CtaTfiTipoFiador(Integer tfiId, String tfiNombre) {
		super(tfiId, tfiNombre);
	}

	/** full constructor */
	public CtaTfiTipoFiador(Integer tfiId, String tfiNombre,
			String tfiDescripcion, Set ctaFxpFiadorPrestamos) {
		super(tfiId, tfiNombre, tfiDescripcion, ctaFxpFiadorPrestamos);
	}

}
