package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractFacFusFacturaUso entity provides the base persistence definition of
 * the FacFusFacturaUso entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractFacFusFacturaUso implements java.io.Serializable {

	// Fields

	private Integer fusId;
	private String fusNombre;
	private String fusDescripcion;
	private String fusToperacion;
	private Set facFenFacturaEncabezados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractFacFusFacturaUso() {
	}

	/** minimal constructor */
	public AbstractFacFusFacturaUso(Integer fusId, String fusNombre,
			String fusDescripcion) {
		this.fusId = fusId;
		this.fusNombre = fusNombre;
		this.fusDescripcion = fusDescripcion;
	}

	/** full constructor */
	public AbstractFacFusFacturaUso(Integer fusId, String fusNombre,
			String fusDescripcion,String fusToperacion, Set facFenFacturaEncabezados) {
		this.fusId = fusId;
		this.fusNombre = fusNombre;
		this.fusDescripcion = fusDescripcion;
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
		this.fusToperacion = fusToperacion;
	}

	// Property accessors

	public String getFusToperacion() {
		return fusToperacion;
	}

	public void setFusToperacion(String fusToperacion) {
		this.fusToperacion = fusToperacion;
	}
	
	public Integer getFusId() {
		return this.fusId;
	}

	public void setFusId(Integer fusId) {
		this.fusId = fusId;
	}

	public String getFusNombre() {
		return this.fusNombre;
	}

	public void setFusNombre(String fusNombre) {
		this.fusNombre = fusNombre;
	}

	public String getFusDescripcion() {
		return this.fusDescripcion;
	}

	public void setFusDescripcion(String fusDescripcion) {
		this.fusDescripcion = fusDescripcion;
	}

	public Set getFacFenFacturaEncabezados() {
		return this.facFenFacturaEncabezados;
	}

	public void setFacFenFacturaEncabezados(Set facFenFacturaEncabezados) {
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
	}

}