package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTgaTipoGarantia entity provides the base persistence definition of
 * the CtaTgaTipoGarantia entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTgaTipoGarantia implements
		java.io.Serializable {

	// Fields

	private Integer tgaId;
	private String tgaNombre;
	private String tgaDescripcion;
	private Set ctaGarGarantias = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTgaTipoGarantia() {
	}

	/** minimal constructor */
	public AbstractCtaTgaTipoGarantia(Integer tgaId, String tgaNombre) {
		this.tgaId = tgaId;
		this.tgaNombre = tgaNombre;
	}

	/** full constructor */
	public AbstractCtaTgaTipoGarantia(Integer tgaId, String tgaNombre,
			String tgaDescripcion, Set ctaGarGarantias) {
		this.tgaId = tgaId;
		this.tgaNombre = tgaNombre;
		this.tgaDescripcion = tgaDescripcion;
		this.ctaGarGarantias = ctaGarGarantias;
	}

	// Property accessors

	public Integer getTgaId() {
		return this.tgaId;
	}

	public void setTgaId(Integer tgaId) {
		this.tgaId = tgaId;
	}

	public String getTgaNombre() {
		return this.tgaNombre;
	}

	public void setTgaNombre(String tgaNombre) {
		this.tgaNombre = tgaNombre;
	}

	public String getTgaDescripcion() {
		return this.tgaDescripcion;
	}

	public void setTgaDescripcion(String tgaDescripcion) {
		this.tgaDescripcion = tgaDescripcion;
	}

	public Set getCtaGarGarantias() {
		return this.ctaGarGarantias;
	}

	public void setCtaGarGarantias(Set ctaGarGarantias) {
		this.ctaGarGarantias = ctaGarGarantias;
	}

}