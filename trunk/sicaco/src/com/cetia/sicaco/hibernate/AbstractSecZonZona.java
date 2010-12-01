package com.cetia.sicaco.hibernate;

/**
 * AbstractSecZonZona entity provides the base persistence definition of the
 * SecZonZona entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecZonZona implements java.io.Serializable {

	// Fields

	private Integer zonId;
	private String zonCodigo;
	private String zonNombre;
	private String zonDescripcion;

	// Constructors

	/** default constructor */
	public AbstractSecZonZona() {
	}

	/** minimal constructor */
	public AbstractSecZonZona(Integer zonId, String zonCodigo, String zonNombre) {
		this.zonId = zonId;
		this.zonCodigo = zonCodigo;
		this.zonNombre = zonNombre;
	}

	/** full constructor */
	public AbstractSecZonZona(Integer zonId, String zonCodigo,
			String zonNombre, String zonDescripcion) {
		this.zonId = zonId;
		this.zonCodigo = zonCodigo;
		this.zonNombre = zonNombre;
		this.zonDescripcion = zonDescripcion;
	}

	// Property accessors

	public Integer getZonId() {
		return this.zonId;
	}

	public void setZonId(Integer zonId) {
		this.zonId = zonId;
	}

	public String getZonCodigo() {
		return this.zonCodigo;
	}

	public void setZonCodigo(String zonCodigo) {
		this.zonCodigo = zonCodigo;
	}

	public String getZonNombre() {
		return this.zonNombre;
	}

	public void setZonNombre(String zonNombre) {
		this.zonNombre = zonNombre;
	}

	public String getZonDescripcion() {
		return this.zonDescripcion;
	}

	public void setZonDescripcion(String zonDescripcion) {
		this.zonDescripcion = zonDescripcion;
	}

}