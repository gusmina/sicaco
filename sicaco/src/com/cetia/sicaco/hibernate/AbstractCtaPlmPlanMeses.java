package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaPlmPlanMeses entity provides the base persistence definition of
 * the CtaPlmPlanMeses entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaPlmPlanMeses implements java.io.Serializable {

	// Fields

	private Integer plmId;
	private String plmNombre;
	private String plmDescripcion;
	private Integer plmDuracion;
	private Set ctaTprTipoPrestamos = new HashSet(0);
	private Set ctaTisTipoSeguros = new HashSet(0);
	private Set ctaTahTipoAhorros = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaPlmPlanMeses() {
	}

	/** minimal constructor */
	public AbstractCtaPlmPlanMeses(Integer plmId, String plmNombre,
			Integer plmDuracion) {
		this.plmId = plmId;
		this.plmNombre = plmNombre;
		this.plmDuracion = plmDuracion;
	}

	/** full constructor */
	public AbstractCtaPlmPlanMeses(Integer plmId, String plmNombre,
			String plmDescripcion, Integer plmDuracion,
			Set ctaTprTipoPrestamos, Set ctaTisTipoSeguros,
			Set ctaTahTipoAhorros) {
		this.plmId = plmId;
		this.plmNombre = plmNombre;
		this.plmDescripcion = plmDescripcion;
		this.plmDuracion = plmDuracion;
		this.ctaTprTipoPrestamos = ctaTprTipoPrestamos;
		this.ctaTisTipoSeguros = ctaTisTipoSeguros;
		this.ctaTahTipoAhorros = ctaTahTipoAhorros;
	}

	// Property accessors

	public Integer getPlmId() {
		return this.plmId;
	}

	public void setPlmId(Integer plmId) {
		this.plmId = plmId;
	}

	public String getPlmNombre() {
		return this.plmNombre;
	}

	public void setPlmNombre(String plmNombre) {
		this.plmNombre = plmNombre;
	}

	public String getPlmDescripcion() {
		return this.plmDescripcion;
	}

	public void setPlmDescripcion(String plmDescripcion) {
		this.plmDescripcion = plmDescripcion;
	}

	public Integer getPlmDuracion() {
		return this.plmDuracion;
	}

	public void setPlmDuracion(Integer plmDuracion) {
		this.plmDuracion = plmDuracion;
	}

	public Set getCtaTprTipoPrestamos() {
		return this.ctaTprTipoPrestamos;
	}

	public void setCtaTprTipoPrestamos(Set ctaTprTipoPrestamos) {
		this.ctaTprTipoPrestamos = ctaTprTipoPrestamos;
	}

	public Set getCtaTisTipoSeguros() {
		return this.ctaTisTipoSeguros;
	}

	public void setCtaTisTipoSeguros(Set ctaTisTipoSeguros) {
		this.ctaTisTipoSeguros = ctaTisTipoSeguros;
	}

	public Set getCtaTahTipoAhorros() {
		return this.ctaTahTipoAhorros;
	}

	public void setCtaTahTipoAhorros(Set ctaTahTipoAhorros) {
		this.ctaTahTipoAhorros = ctaTahTipoAhorros;
	}

}