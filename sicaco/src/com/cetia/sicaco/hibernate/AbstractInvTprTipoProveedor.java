package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvTprTipoProveedor entity provides the base persistence definition
 * of the InvTprTipoProveedor entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvTprTipoProveedor implements
		java.io.Serializable {

	// Fields

	private Integer tprId;
	private String tprNombre;
	private Set invProProveedors = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvTprTipoProveedor() {
	}

	/** minimal constructor */
	public AbstractInvTprTipoProveedor(Integer tprId, String tprNombre) {
		this.tprId = tprId;
		this.tprNombre = tprNombre;
	}

	/** full constructor */
	public AbstractInvTprTipoProveedor(Integer tprId, String tprNombre,
			Set invProProveedors) {
		this.tprId = tprId;
		this.tprNombre = tprNombre;
		this.invProProveedors = invProProveedors;
	}

	// Property accessors

	public Integer getTprId() {
		return this.tprId;
	}

	public void setTprId(Integer tprId) {
		this.tprId = tprId;
	}

	public String getTprNombre() {
		return this.tprNombre;
	}

	public void setTprNombre(String tprNombre) {
		this.tprNombre = tprNombre;
	}

	public Set getInvProProveedors() {
		return this.invProProveedors;
	}

	public void setInvProProveedors(Set invProProveedors) {
		this.invProProveedors = invProProveedors;
	}

}