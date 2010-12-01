package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractSecCelCorreoElectronico entity provides the base persistence
 * definition of the SecCelCorreoElectronico entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecCelCorreoElectronico implements
		java.io.Serializable {

	// Fields

	private SecCelCorreoElectronicoId id;
	private String celPrincipal;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractSecCelCorreoElectronico() {
	}

	/** minimal constructor */
	public AbstractSecCelCorreoElectronico(SecCelCorreoElectronicoId id,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.id = id;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractSecCelCorreoElectronico(SecCelCorreoElectronicoId id,
			String celPrincipal, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.id = id;
		this.celPrincipal = celPrincipal;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	// Property accessors

	public SecCelCorreoElectronicoId getId() {
		return this.id;
	}

	public void setId(SecCelCorreoElectronicoId id) {
		this.id = id;
	}

	public String getCelPrincipal() {
		return this.celPrincipal;
	}

	public void setCelPrincipal(String celPrincipal) {
		this.celPrincipal = celPrincipal;
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

}