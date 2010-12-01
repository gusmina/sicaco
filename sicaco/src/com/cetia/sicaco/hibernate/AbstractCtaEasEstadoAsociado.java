package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaEasEstadoAsociado entity provides the base persistence definition
 * of the CtaEasEstadoAsociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaEasEstadoAsociado implements
		java.io.Serializable {

	// Fields

	private Integer easId;
	private String easNombre;
	private String easDescripcion;

	// Constructors

	/** default constructor */
	public AbstractCtaEasEstadoAsociado() {
	}

	/** minimal constructor */
	public AbstractCtaEasEstadoAsociado(Integer easId, String easNombre) {
		this.easId = easId;
		this.easNombre = easNombre;
	}

	/** full constructor */
	public AbstractCtaEasEstadoAsociado(Integer easId, String easNombre,
			String easDescripcion) {
		this.easId = easId;
		this.easNombre = easNombre;
		this.easDescripcion = easDescripcion;
	}

	// Property accessors

	public Integer getEasId() {
		return this.easId;
	}

	public void setEasId(Integer easId) {
		this.easId = easId;
	}

	public String getEasNombre() {
		return this.easNombre;
	}

	public void setEasNombre(String easNombre) {
		this.easNombre = easNombre;
	}

	public String getEasDescripcion() {
		return this.easDescripcion;
	}

	public void setEasDescripcion(String easDescripcion) {
		this.easDescripcion = easDescripcion;
	}

}