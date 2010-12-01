package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTteTipoTelefono entity provides the base persistence definition of
 * the SecTteTipoTelefono entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTteTipoTelefono implements
		java.io.Serializable {

	// Fields

	private Integer tteId;
	private String tteDescripcion;
	private Set secTelTelefonos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTteTipoTelefono() {
	}

	/** minimal constructor */
	public AbstractSecTteTipoTelefono(Integer tteId) {
		this.tteId = tteId;
	}

	/** full constructor */
	public AbstractSecTteTipoTelefono(Integer tteId, String tteDescripcion,
			Set secTelTelefonos) {
		this.tteId = tteId;
		this.tteDescripcion = tteDescripcion;
		this.secTelTelefonos = secTelTelefonos;
	}

	// Property accessors

	public Integer getTteId() {
		return this.tteId;
	}

	public void setTteId(Integer tteId) {
		this.tteId = tteId;
	}

	public String getTteDescripcion() {
		return this.tteDescripcion;
	}

	public void setTteDescripcion(String tteDescripcion) {
		this.tteDescripcion = tteDescripcion;
	}

	public Set getSecTelTelefonos() {
		return this.secTelTelefonos;
	}

	public void setSecTelTelefonos(Set secTelTelefonos) {
		this.secTelTelefonos = secTelTelefonos;
	}

}