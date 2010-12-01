package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTprTipoPrestamo entity provides the base persistence definition of
 * the CtaTprTipoPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTprTipoPrestamo implements
		java.io.Serializable {

	// Fields

	private Integer tprId;
	private CtaPlmPlanMeses ctaPlmPlanMeses = new CtaPlmPlanMeses();
	private CtaTinTasaInteres ctaTinTasaInteres = new CtaTinTasaInteres();
	private CtaLprLineaPrestamo ctaLprLineaPrestamo = new CtaLprLineaPrestamo();
	private String tprNombre;
	private String tprDescripcion;
	private Set ctaPrePrestamos = new HashSet(0);
	private Set iucPutProveedorTipoPrestamos = new HashSet(0);
	private Set iucTutTarTprs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTprTipoPrestamo() {
	}

	/** minimal constructor */
	public AbstractCtaTprTipoPrestamo(Integer tprId, String tprNombre) {
		this.tprId = tprId;
		this.tprNombre = tprNombre;
	}

	/** full constructor */
	public AbstractCtaTprTipoPrestamo(Integer tprId,
			CtaPlmPlanMeses ctaPlmPlanMeses,
			CtaTinTasaInteres ctaTinTasaInteres,
			CtaLprLineaPrestamo ctaLprLineaPrestamo, String tprNombre,
			String tprDescripcion, Set ctaPrePrestamos,
			Set iucPutProveedorTipoPrestamos, Set iucTutTarTprs) {
		super();
		this.tprId = tprId;
		this.ctaPlmPlanMeses = ctaPlmPlanMeses;
		this.ctaTinTasaInteres = ctaTinTasaInteres;
		this.ctaLprLineaPrestamo = ctaLprLineaPrestamo;
		this.tprNombre = tprNombre;
		this.tprDescripcion = tprDescripcion;
		this.ctaPrePrestamos = ctaPrePrestamos;
		this.iucPutProveedorTipoPrestamos = iucPutProveedorTipoPrestamos;
		this.iucTutTarTprs = iucTutTarTprs;
	}

	// Property accessors

	public Integer getTprId() {
		return this.tprId;
	}

	public void setTprId(Integer tprId) {
		this.tprId = tprId;
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

	public CtaLprLineaPrestamo getCtaLprLineaPrestamo() {
		return this.ctaLprLineaPrestamo;
	}

	public void setCtaLprLineaPrestamo(CtaLprLineaPrestamo ctaLprLineaPrestamo) {
		this.ctaLprLineaPrestamo = ctaLprLineaPrestamo;
	}

	public String getTprNombre() {
		return this.tprNombre;
	}

	public void setTprNombre(String tprNombre) {
		this.tprNombre = tprNombre;
	}

	public String getTprDescripcion() {
		return this.tprDescripcion;
	}

	public void setTprDescripcion(String tprDescripcion) {
		this.tprDescripcion = tprDescripcion;
	}

	public Set getCtaPrePrestamos() {
		return this.ctaPrePrestamos;
	}

	public void setCtaPrePrestamos(Set ctaPrePrestamos) {
		this.ctaPrePrestamos = ctaPrePrestamos;
	}

	public Set getIucPutProveedorTipoPrestamos() {
		return iucPutProveedorTipoPrestamos;
	}

	public void setIucPutProveedorTipoPrestamos(Set iucPutProveedorTipoPrestamos) {
		this.iucPutProveedorTipoPrestamos = iucPutProveedorTipoPrestamos;
	}

	public Set getIucTutTarTprs() {
		return iucTutTarTprs;
	}

	public void setIucTutTarTprs(Set iucTutTarTprs) {
		this.iucTutTarTprs = iucTutTarTprs;
	}

}