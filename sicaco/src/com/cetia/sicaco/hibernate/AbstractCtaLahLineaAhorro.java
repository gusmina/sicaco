package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaLahLineaAhorro entity provides the base persistence definition of
 * the CtaLahLineaAhorro entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaLahLineaAhorro implements java.io.Serializable {

	// Fields

	private Integer lahId;
	private String lahNombre;
	private String lahDescripcion;
	private Date lahDesde;
	private Date lahHasta;
	private Set ctaTahTipoAhorros = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaLahLineaAhorro() {
	}

	/** minimal constructor */
	public AbstractCtaLahLineaAhorro(Integer lahId, String lahNombre,
			Date lahDesde) {
		this.lahId = lahId;
		this.lahNombre = lahNombre;
		this.lahDesde = lahDesde;
	}

	/** full constructor */
	public AbstractCtaLahLineaAhorro(Integer lahId, String lahNombre,
			String lahDescripcion, Date lahDesde, Date lahHasta,
			Set ctaTahTipoAhorros) {
		this.lahId = lahId;
		this.lahNombre = lahNombre;
		this.lahDescripcion = lahDescripcion;
		this.lahDesde = lahDesde;
		this.lahHasta = lahHasta;
		this.ctaTahTipoAhorros = ctaTahTipoAhorros;
	}

	// Property accessors

	public Integer getLahId() {
		return this.lahId;
	}

	public void setLahId(Integer lahId) {
		this.lahId = lahId;
	}

	public String getLahNombre() {
		return this.lahNombre;
	}

	public void setLahNombre(String lahNombre) {
		this.lahNombre = lahNombre;
	}

	public String getLahDescripcion() {
		return this.lahDescripcion;
	}

	public void setLahDescripcion(String lahDescripcion) {
		this.lahDescripcion = lahDescripcion;
	}

	public Date getLahDesde() {
		return this.lahDesde;
	}

	public void setLahDesde(Date lahDesde) {
		this.lahDesde = lahDesde;
	}

	public Date getLahHasta() {
		return this.lahHasta;
	}

	public void setLahHasta(Date lahHasta) {
		this.lahHasta = lahHasta;
	}

	public Set getCtaTahTipoAhorros() {
		return this.ctaTahTipoAhorros;
	}

	public void setCtaTahTipoAhorros(Set ctaTahTipoAhorros) {
		this.ctaTahTipoAhorros = ctaTahTipoAhorros;
	}

}