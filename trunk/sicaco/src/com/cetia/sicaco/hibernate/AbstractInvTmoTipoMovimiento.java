package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvTmoTipoMovimiento entity provides the base persistence definition
 * of the InvTmoTipoMovimiento entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvTmoTipoMovimiento implements
		java.io.Serializable {

	// Fields

	private Integer tmoId;
	private String nombre;
	private Set invMovMovimientoses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvTmoTipoMovimiento() {
	}

	/** minimal constructor */
	public AbstractInvTmoTipoMovimiento(Integer tmoId, String nombre) {
		this.tmoId = tmoId;
		this.nombre = nombre;
	}

	/** full constructor */
	public AbstractInvTmoTipoMovimiento(Integer tmoId, String nombre,
			Set invMovMovimientoses) {
		this.tmoId = tmoId;
		this.nombre = nombre;
		this.invMovMovimientoses = invMovMovimientoses;
	}

	// Property accessors

	public Integer getTmoId() {
		return this.tmoId;
	}

	public void setTmoId(Integer tmoId) {
		this.tmoId = tmoId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getInvMovMovimientoses() {
		return this.invMovMovimientoses;
	}

	public void setInvMovMovimientoses(Set invMovMovimientoses) {
		this.invMovMovimientoses = invMovMovimientoses;
	}

}