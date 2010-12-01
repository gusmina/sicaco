package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTasTipoAsociado entity provides the base persistence definition of
 * the CtaTasTipoAsociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTasTipoAsociado implements
		java.io.Serializable {

	// Fields

	private Integer tasId;
	private String tasNombre;
	private String tasDescripcion;
	private Set ctaAscAsociados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTasTipoAsociado() {
	}

	/** minimal constructor */
	public AbstractCtaTasTipoAsociado(Integer tasId, String tasNombre) {
		this.tasId = tasId;
		this.tasNombre = tasNombre;
	}

	/** full constructor */
	public AbstractCtaTasTipoAsociado(Integer tasId, String tasNombre,
			String tasDescripcion, Set ctaAscAsociados) {
		this.tasId = tasId;
		this.tasNombre = tasNombre;
		this.tasDescripcion = tasDescripcion;
		this.ctaAscAsociados = ctaAscAsociados;
	}

	// Property accessors

	public Integer getTasId() {
		return this.tasId;
	}

	public void setTasId(Integer tasId) {
		this.tasId = tasId;
	}

	public String getTasNombre() {
		return this.tasNombre;
	}

	public void setTasNombre(String tasNombre) {
		this.tasNombre = tasNombre;
	}

	public String getTasDescripcion() {
		return this.tasDescripcion;
	}

	public void setTasDescripcion(String tasDescripcion) {
		this.tasDescripcion = tasDescripcion;
	}

	public Set getCtaAscAsociados() {
		return this.ctaAscAsociados;
	}

	public void setCtaAscAsociados(Set ctaAscAsociados) {
		this.ctaAscAsociados = ctaAscAsociados;
	}

}