package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTisTipoSeguro entity provides the base persistence definition of
 * the CtaTisTipoSeguro entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTisTipoSeguro implements java.io.Serializable {

	// Fields

	private Integer tisId;
	private CtaPlmPlanMeses ctaPlmPlanMeses;
	private CtaTntTipoNota ctaTntTipoNota = new CtaTntTipoNota();
	private String tisNombre;
	private String tisDescripcion;
	private String tisPoliza;
	private Date tisInicioPoliza;
	private Date tisFinPoliza;
	private Double tisMontoBasico;
	private Double tisCostoAnual;
	private Set ctaSegSeguroses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTisTipoSeguro() {
	}

	/** minimal constructor */
	public AbstractCtaTisTipoSeguro(Integer tisId, String tisNombre,
			String tisPoliza) {
		this.tisId = tisId;
		this.tisNombre = tisNombre;
		this.tisPoliza = tisPoliza;
	}

	/** full constructor */
	public AbstractCtaTisTipoSeguro(Integer tisId,
			CtaPlmPlanMeses ctaPlmPlanMeses, CtaTntTipoNota ctaTntTipoNota, String tisNombre,
			String tisDescripcion, String tisPoliza, Date tisInicioPoliza,
			Date tisFinPoliza, Double tisMontoBasico, Double tisCostoAnual,
			Set ctaSegSeguroses) {
		super();
		this.tisId = tisId;
		this.ctaPlmPlanMeses = ctaPlmPlanMeses;
		this.ctaTntTipoNota = ctaTntTipoNota;
		this.tisNombre = tisNombre;
		this.tisDescripcion = tisDescripcion;
		this.tisPoliza = tisPoliza;
		this.tisInicioPoliza = tisInicioPoliza;
		this.tisFinPoliza = tisFinPoliza;
		this.tisMontoBasico = tisMontoBasico;
		this.tisCostoAnual = tisCostoAnual;
		this.ctaSegSeguroses = ctaSegSeguroses;
	}
	
	// Property accessors

	public Integer getTisId() {
		return this.tisId;
	}

	public void setTisId(Integer tisId) {
		this.tisId = tisId;
	}

	public CtaPlmPlanMeses getCtaPlmPlanMeses() {
		return this.ctaPlmPlanMeses;
	}

	public void setCtaPlmPlanMeses(CtaPlmPlanMeses ctaPlmPlanMeses) {
		this.ctaPlmPlanMeses = ctaPlmPlanMeses;
	}

	public CtaTntTipoNota getCtaTntTipoNota() {
		return ctaTntTipoNota;
	}

	public void setCtaTntTipoNota(CtaTntTipoNota ctaTntTipoNota) {
		this.ctaTntTipoNota = ctaTntTipoNota;
	}

	public String getTisNombre() {
		return this.tisNombre;
	}

	public void setTisNombre(String tisNombre) {
		this.tisNombre = tisNombre;
	}

	public String getTisDescripcion() {
		return this.tisDescripcion;
	}

	public void setTisDescripcion(String tisDescripcion) {
		this.tisDescripcion = tisDescripcion;
	}

	public String getTisPoliza() {
		return this.tisPoliza;
	}

	public void setTisPoliza(String tisPoliza) {
		this.tisPoliza = tisPoliza;
	}

	public Date getTisInicioPoliza() {
		return tisInicioPoliza;
	}

	public void setTisInicioPoliza(Date tisInicioPoliza) {
		this.tisInicioPoliza = tisInicioPoliza;
	}

	public Date getTisFinPoliza() {
		return tisFinPoliza;
	}

	public void setTisFinPoliza(Date tisFinPoliza) {
		this.tisFinPoliza = tisFinPoliza;
	}

	public Double getTisMontoBasico() {
		return tisMontoBasico;
	}

	public void setTisMontoBasico(Double tisMontoBasico) {
		this.tisMontoBasico = tisMontoBasico;
	}

	public Double getTisCostoAnual() {
		return tisCostoAnual;
	}

	public void setTisCostoAnual(Double tisCostoAnual) {
		this.tisCostoAnual = tisCostoAnual;
	}

	public Set getCtaSegSeguroses() {
		return this.ctaSegSeguroses;
	}

	public void setCtaSegSeguroses(Set ctaSegSeguroses) {
		this.ctaSegSeguroses = ctaSegSeguroses;
	}

}