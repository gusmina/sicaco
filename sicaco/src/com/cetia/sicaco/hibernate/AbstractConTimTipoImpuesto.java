package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractConTimTipoImpuesto entity provides the base persistence definition of
 * the ConTimTipoImpuesto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConTimTipoImpuesto implements
		java.io.Serializable {

	// Fields

	private String timId;
	private String timNombre;
	private String timDescripcion;
	private Set conRimRetencionImpuestos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractConTimTipoImpuesto() {
	}

	/** minimal constructor */
	public AbstractConTimTipoImpuesto(String timId, String timNombre) {
		this.timId = timId;
		this.timNombre = timNombre;
	}

	/** full constructor */
	public AbstractConTimTipoImpuesto(String timId, String timNombre,
			String timDescripcion, Set conRimRetencionImpuestos) {
		this.timId = timId;
		this.timNombre = timNombre;
		this.timDescripcion = timDescripcion;
		this.conRimRetencionImpuestos = conRimRetencionImpuestos;
	}

	// Property accessors

	public String getTimId() {
		return this.timId;
	}

	public void setTimId(String timId) {
		this.timId = timId;
	}

	public String getTimNombre() {
		return this.timNombre;
	}

	public void setTimNombre(String timNombre) {
		this.timNombre = timNombre;
	}

	public String getTimDescripcion() {
		return this.timDescripcion;
	}

	public void setTimDescripcion(String timDescripcion) {
		this.timDescripcion = timDescripcion;
	}

	public Set getConRimRetencionImpuestos() {
		return this.conRimRetencionImpuestos;
	}

	public void setConRimRetencionImpuestos(Set conRimRetencionImpuestos) {
		this.conRimRetencionImpuestos = conRimRetencionImpuestos;
	}

}