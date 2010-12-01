package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTcoTipoContrato entity provides the base persistence definition of
 * the CtaTcoTipoContrato entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTcoTipoContrato implements
		java.io.Serializable {

	// Fields

	private Integer tcoId;
	private String tcoNombre;
	private String tcoDescripcion;
	private Set ctaAscAsociados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTcoTipoContrato() {
	}

	/** minimal constructor */
	public AbstractCtaTcoTipoContrato(Integer tcoId, String tcoNombre) {
		this.tcoId = tcoId;
		this.tcoNombre = tcoNombre;
	}

	/** full constructor */
	public AbstractCtaTcoTipoContrato(Integer tcoId, String tcoNombre,
			String tcoDescripcion, Set ctaAscAsociados) {
		this.tcoId = tcoId;
		this.tcoNombre = tcoNombre;
		this.tcoDescripcion = tcoDescripcion;
		this.ctaAscAsociados = ctaAscAsociados;
	}

	// Property accessors

	public Integer getTcoId() {
		return this.tcoId;
	}

	public void setTcoId(Integer tcoId) {
		this.tcoId = tcoId;
	}

	public String getTcoNombre() {
		return this.tcoNombre;
	}

	public void setTcoNombre(String tcoNombre) {
		this.tcoNombre = tcoNombre;
	}

	public String getTcoDescripcion() {
		return this.tcoDescripcion;
	}

	public void setTcoDescripcion(String tcoDescripcion) {
		this.tcoDescripcion = tcoDescripcion;
	}

	public Set getCtaAscAsociados() {
		return this.ctaAscAsociados;
	}

	public void setCtaAscAsociados(Set ctaAscAsociados) {
		this.ctaAscAsociados = ctaAscAsociados;
	}

}