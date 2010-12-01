package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTntTipoNota entity provides the base persistence definition of the
 * CtaTntTipoNota entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTntTipoNota implements java.io.Serializable {

	// Fields

	private Integer tntId;
	private String tntNombre;
	private String tntDescripcion;
	private Set ctaNotNotases = new HashSet(0);
	private Set ctaTisTipoSeguros = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTntTipoNota() {
	}

	/** minimal constructor */
	public AbstractCtaTntTipoNota(Integer tntId, String tntNombre) {
		this.tntId = tntId;
		this.tntNombre = tntNombre;
	}

	/** full constructor */
	public AbstractCtaTntTipoNota(Integer tntId, String tntNombre,
			String tntDescripcion, Set ctaNotNotases,
			Set ctaTisTipoSeguros) {
		this.tntId = tntId;
		this.tntNombre = tntNombre;
		this.tntDescripcion = tntDescripcion;
		this.ctaNotNotases = ctaNotNotases;
		this.ctaTisTipoSeguros = ctaTisTipoSeguros;
	}

	// Property accessors

	public Integer getTntId() {
		return this.tntId;
	}

	public void setTntId(Integer tntId) {
		this.tntId = tntId;
	}

	public String getTntNombre() {
		return this.tntNombre;
	}

	public void setTntNombre(String tntNombre) {
		this.tntNombre = tntNombre;
	}

	public String getTntDescripcion() {
		return this.tntDescripcion;
	}

	public void setTntDescripcion(String tntDescripcion) {
		this.tntDescripcion = tntDescripcion;
	}

	public Set getCtaNotNotases() {
		return this.ctaNotNotases;
	}

	public void setCtaNotNotases(Set ctaNotNotases) {
		this.ctaNotNotases = ctaNotNotases;
	}

	public Set getCtaTisTipoSeguros() {
		return ctaTisTipoSeguros;
	}

	public void setCtaTisTipoSeguros(Set ctaTisTipoSeguros) {
		this.ctaTisTipoSeguros = ctaTisTipoSeguros;
	}

}