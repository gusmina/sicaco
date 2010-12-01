package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaDomDomicilio entity provides the base persistence definition of
 * the CtaDomDomicilio entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaDomDomicilio implements java.io.Serializable {

	// Fields

	private Integer domId;
	private String domNombre;
	private String domDescripcion;
	private Set ctaAscAsociados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaDomDomicilio() {
	}

	/** minimal constructor */
	public AbstractCtaDomDomicilio(Integer domId, String domNombre) {
		this.domId = domId;
		this.domNombre = domNombre;
	}

	/** full constructor */
	public AbstractCtaDomDomicilio(Integer domId, String domNombre,
			String domDescripcion, Set ctaAscAsociados) {
		this.domId = domId;
		this.domNombre = domNombre;
		this.domDescripcion = domDescripcion;
		this.ctaAscAsociados = ctaAscAsociados;
	}

	// Property accessors

	public Integer getDomId() {
		return this.domId;
	}

	public void setDomId(Integer domId) {
		this.domId = domId;
	}

	public String getDomNombre() {
		return this.domNombre;
	}

	public void setDomNombre(String domNombre) {
		this.domNombre = domNombre;
	}

	public String getDomDescripcion() {
		return this.domDescripcion;
	}

	public void setDomDescripcion(String domDescripcion) {
		this.domDescripcion = domDescripcion;
	}

	public Set getCtaAscAsociados() {
		return this.ctaAscAsociados;
	}

	public void setCtaAscAsociados(Set ctaAscAsociados) {
		this.ctaAscAsociados = ctaAscAsociados;
	}

}