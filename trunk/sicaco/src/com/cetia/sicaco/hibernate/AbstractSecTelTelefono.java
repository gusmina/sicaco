package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractSecTelTelefono entity provides the base persistence definition of the
 * SecTelTelefono entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTelTelefono implements java.io.Serializable {

	// Fields

	private SecTelTelefonoId id;
	private SecTteTipoTelefono secTteTipoTelefono = new SecTteTipoTelefono();
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private String telExt;

	// Constructors

	public AbstractSecTelTelefono(SecTelTelefonoId id,
			SecTteTipoTelefono secTteTipoTelefono, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String telExt) {
		super();
		this.id = id;
		this.secTteTipoTelefono = secTteTipoTelefono;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.telExt = telExt;
	}

	/** default constructor */
	public AbstractSecTelTelefono() {
	}

	/** full constructor */

	// Property accessors

	public SecTelTelefonoId getId() {
		return this.id;
	}

	public void setId(SecTelTelefonoId id) {
		this.id = id;
	}

	public SecTteTipoTelefono getSecTteTipoTelefono() {
		if(secTteTipoTelefono==null)
			secTteTipoTelefono = new SecTteTipoTelefono();
		return this.secTteTipoTelefono;
	}

	public void setSecTteTipoTelefono(SecTteTipoTelefono secTteTipoTelefono) {
		this.secTteTipoTelefono = secTteTipoTelefono;
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
	
	public void setTteDescripcion(String tteDescripcion) {
		secTteTipoTelefono.setTteDescripcion(tteDescripcion);
	}

	public void setTteId(Integer tteId) {
		secTteTipoTelefono.setTteId(tteId);
	}

	public String getTteDescripcion() {
		
		return secTteTipoTelefono.getTteDescripcion();
	}

	public Integer getTteId() {
		return secTteTipoTelefono.getTteId();
	}

	public String getTelExt() {
		return telExt;
	}

	public void setTelExt(String telExt) {
		this.telExt = telExt;
	}



}