package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractConRimRetencionImpuesto entity provides the base persistence
 * definition of the ConRimRetencionImpuesto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConRimRetencionImpuesto implements
		java.io.Serializable {

	// Fields

	private Integer rimId;
	private ConTimTipoImpuesto conTimTipoImpuesto = new ConTimTipoImpuesto();
	private Double rimImpuesto;
	private Date rimFechaInvalidez;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set facFenFacturaEncabezados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractConRimRetencionImpuesto() {
	}

	/** minimal constructor */
	public AbstractConRimRetencionImpuesto(
			ConTimTipoImpuesto conTimTipoImpuesto, Double rimImpuesto,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.conTimTipoImpuesto = conTimTipoImpuesto;
		this.rimImpuesto = rimImpuesto;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractConRimRetencionImpuesto(
			ConTimTipoImpuesto conTimTipoImpuesto, Double rimImpuesto,
			Date rimFechaInvalidez, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set facFenFacturaEncabezados) {
		this.conTimTipoImpuesto = conTimTipoImpuesto;
		this.rimImpuesto = rimImpuesto;
		this.rimFechaInvalidez = rimFechaInvalidez;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
	}

	// Property accessors

	public Integer getRimId() {
		return this.rimId;
	}

	public void setRimId(Integer rimId) {
		this.rimId = rimId;
	}

	public ConTimTipoImpuesto getConTimTipoImpuesto() {
		return this.conTimTipoImpuesto;
	}

	public void setConTimTipoImpuesto(ConTimTipoImpuesto conTimTipoImpuesto) {
		this.conTimTipoImpuesto = conTimTipoImpuesto;
	}

	public Double getRimImpuesto() {
		return this.rimImpuesto;
	}

	public void setRimImpuesto(Double rimImpuesto) {
		this.rimImpuesto = rimImpuesto;
	}

	public Date getRimFechaInvalidez() {
		return this.rimFechaInvalidez;
	}

	public void setRimFechaInvalidez(Date rimFechaInvalidez) {
		this.rimFechaInvalidez = rimFechaInvalidez;
	}

	public Date getAudFechaCreacion() {
		return this.audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
	}

	public String getAudUsuarioCreacion() {
		return this.audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaModificacion() {
		return this.audFechaModificacion;
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		this.audFechaModificacion = audFechaModificacion;
	}

	public String getAudUsuarioModificacion() {
		return this.audUsuarioModificacion;
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	public Set getFacFenFacturaEncabezados() {
		return this.facFenFacturaEncabezados;
	}

	public void setFacFenFacturaEncabezados(Set facFenFacturaEncabezados) {
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
	}

}