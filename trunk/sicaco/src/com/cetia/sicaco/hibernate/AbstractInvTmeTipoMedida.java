package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvTmeTipoMedida entity provides the base persistence definition of
 * the InvTmeTipoMedida entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvTmeTipoMedida implements java.io.Serializable {

	// Fields

	private Integer tmeId;
	private String tmeNombre;
	private String tmeDescripcion;
	private Set invMedMedidas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvTmeTipoMedida() {
	}

	/** minimal constructor */
	public AbstractInvTmeTipoMedida(Integer tmeId, String tmeNombre) {
		this.tmeId = tmeId;
		this.tmeNombre = tmeNombre;
	}

	/** full constructor */
	public AbstractInvTmeTipoMedida(Integer tmeId, String tmeNombre,
			String tmeDescripcion, Set invMedMedidas) {
		this.tmeId = tmeId;
		this.tmeNombre = tmeNombre;
		this.tmeDescripcion = tmeDescripcion;
		this.invMedMedidas = invMedMedidas;
	}

	// Property accessors

	public Integer getTmeId() {
		return this.tmeId;
	}

	public void setTmeId(Integer tmeId) {
		this.tmeId = tmeId;
	}

	public String getTmeNombre() {
		return this.tmeNombre;
	}

	public void setTmeNombre(String tmeNombre) {
		this.tmeNombre = tmeNombre;
	}

	public String getTmeDescripcion() {
		return this.tmeDescripcion;
	}

	public void setTmeDescripcion(String tmeDescripcion) {
		this.tmeDescripcion = tmeDescripcion;
	}

	public Set getInvMedMedidas() {
		return this.invMedMedidas;
	}

	public void setInvMedMedidas(Set invMedMedidas) {
		this.invMedMedidas = invMedMedidas;
	}

}