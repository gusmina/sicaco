package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTfiTipoFiador entity provides the base persistence definition of
 * the CtaTfiTipoFiador entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTfiTipoFiador implements java.io.Serializable {

	// Fields

	private Integer tfiId;
	private String tfiNombre;
	private String tfiDescripcion;
	private Set ctaFxpFiadorPrestamos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTfiTipoFiador() {
	}

	/** minimal constructor */
	public AbstractCtaTfiTipoFiador(Integer tfiId, String tfiNombre) {
		this.tfiId = tfiId;
		this.tfiNombre = tfiNombre;
	}

	/** full constructor */
	public AbstractCtaTfiTipoFiador(Integer tfiId, String tfiNombre,
			String tfiDescripcion, Set ctaFxpFiadorPrestamos) {
		this.tfiId = tfiId;
		this.tfiNombre = tfiNombre;
		this.tfiDescripcion = tfiDescripcion;
		this.ctaFxpFiadorPrestamos = ctaFxpFiadorPrestamos;
	}

	// Property accessors

	public Integer getTfiId() {
		return this.tfiId;
	}

	public void setTfiId(Integer tfiId) {
		this.tfiId = tfiId;
	}

	public String getTfiNombre() {
		return this.tfiNombre;
	}

	public void setTfiNombre(String tfiNombre) {
		this.tfiNombre = tfiNombre;
	}

	public String getTfiDescripcion() {
		return this.tfiDescripcion;
	}

	public void setTfiDescripcion(String tfiDescripcion) {
		this.tfiDescripcion = tfiDescripcion;
	}

	public Set getCtaFxpFiadorPrestamos() {
		return this.ctaFxpFiadorPrestamos;
	}

	public void setCtaFxpFiadorPrestamos(Set ctaFxpFiadorPrestamos) {
		this.ctaFxpFiadorPrestamos = ctaFxpFiadorPrestamos;
	}

}