package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTahTipoAhorro entity provides the base persistence definition of
 * the CtaTahTipoAhorro entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTahTipoAhorro implements java.io.Serializable {

	// Fields

	private Integer tahId;
	private CtaPlmPlanMeses ctaPlmPlanMeses = new CtaPlmPlanMeses();
	private CtaTinTasaInteres ctaTinTasaInteres = new CtaTinTasaInteres();
	private CtaLahLineaAhorro ctaLahLineaAhorro = new CtaLahLineaAhorro();
	private String tahNombre;
	private String tahDescripcion;
	private Date tahFechaFin;
	private Set ctaCahCuentaAhorros = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTahTipoAhorro() {
	}

	/** minimal constructor */
	public AbstractCtaTahTipoAhorro(Integer tahId,
			CtaPlmPlanMeses ctaPlmPlanMeses,
			CtaTinTasaInteres ctaTinTasaInteres,
			CtaLahLineaAhorro ctaLahLineaAhorro, String tahNombre) {
		this.tahId = tahId;
		this.ctaPlmPlanMeses = ctaPlmPlanMeses;
		this.ctaTinTasaInteres = ctaTinTasaInteres;
		this.ctaLahLineaAhorro = ctaLahLineaAhorro;
		this.tahNombre = tahNombre;
	}

	/** full constructor */
	public AbstractCtaTahTipoAhorro(Integer tahId,
			CtaPlmPlanMeses ctaPlmPlanMeses,
			CtaTinTasaInteres ctaTinTasaInteres,
			CtaLahLineaAhorro ctaLahLineaAhorro, String tahNombre, 
			String tahDescripcion, Date tahFechaFin, Set ctaCahCuentaAhorros) {
		this.tahId = tahId;
		this.ctaPlmPlanMeses = ctaPlmPlanMeses;
		this.ctaTinTasaInteres = ctaTinTasaInteres;
		this.ctaLahLineaAhorro = ctaLahLineaAhorro;
		this.tahNombre = tahNombre;
		this.tahDescripcion = tahDescripcion;
		this.tahFechaFin = tahFechaFin;
		this.ctaCahCuentaAhorros = ctaCahCuentaAhorros;
		
	}

	// Property accessors

	public Integer getTahId() {
		return this.tahId;
	}

	public void setTahId(Integer tahId) {
		this.tahId = tahId;
	}

	public CtaPlmPlanMeses getCtaPlmPlanMeses() {
		return this.ctaPlmPlanMeses;
	}

	public void setCtaPlmPlanMeses(CtaPlmPlanMeses ctaPlmPlanMeses) {
		this.ctaPlmPlanMeses = ctaPlmPlanMeses;
	}

	public CtaTinTasaInteres getCtaTinTasaInteres() {
		return this.ctaTinTasaInteres;
	}

	public void setCtaTinTasaInteres(CtaTinTasaInteres ctaTinTasaInteres) {
		this.ctaTinTasaInteres = ctaTinTasaInteres;
	}

	public CtaLahLineaAhorro getCtaLahLineaAhorro() {
		return this.ctaLahLineaAhorro;
	}

	public void setCtaLahLineaAhorro(CtaLahLineaAhorro ctaLahLineaAhorro) {
		this.ctaLahLineaAhorro = ctaLahLineaAhorro;
	}

	public String getTahNombre() {
		return this.tahNombre;
	}

	public void setTahNombre(String tahNombre) {
		this.tahNombre = tahNombre;
	}

	public String getTahDescripcion() {
		return this.tahDescripcion;
	}

	public void setTahDescripcion(String tahDescripcion) {
		this.tahDescripcion = tahDescripcion;
	}

	public Set getCtaCahCuentaAhorros() {
		return this.ctaCahCuentaAhorros;
	}

	public void setCtaCahCuentaAhorros(Set ctaCahCuentaAhorros) {
		this.ctaCahCuentaAhorros = ctaCahCuentaAhorros;
	}

	public Date getTahFechaFin() {
		return tahFechaFin;
	}

	public void setTahFechaFin(Date tahFechaFin) {
		this.tahFechaFin = tahFechaFin;
	}

}