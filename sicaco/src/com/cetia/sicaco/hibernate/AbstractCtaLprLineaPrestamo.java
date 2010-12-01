package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaLprLineaPrestamo entity provides the base persistence definition
 * of the CtaLprLineaPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaLprLineaPrestamo implements
		java.io.Serializable {

	// Fields

	private Integer lprId;
	private String lprNombre;
	private String lprDescripcion;
	private Date lprDesde;
	private Date lprHasta;
	private String lprOrdenAprov;
	private Double lprComision;
	private Integer lprMinFiadores;
	private Integer lprMinGarantias;
	private Double lprSueldoMinimo;
	private Integer lprMinRefPersonales;
	private Integer lprMinRefComerciales;
	private Set ctaDexDescuentosExternoses = new HashSet(0);
	private Set ctaTprTipoPrestamos = new HashSet(0);
	
	
	// Constructors

	public AbstractCtaLprLineaPrestamo(Integer lprId, String lprNombre,
			String lprDescripcion, Date lprDesde, Date lprHasta,
			String lprOrdenAprov, Double lprComision, Integer lprMinFiadores,
			Integer lprMinGarantias, Double lprSueldoMinimo,
			Integer lprMinRefPersonales, Integer lprMinRefComerciales,
			Set ctaDexDescuentosExternoses, Set ctaTprTipoPrestamos) {
		super();
		this.lprId = lprId;
		this.lprNombre = lprNombre;
		this.lprDescripcion = lprDescripcion;
		this.lprDesde = lprDesde;
		this.lprHasta = lprHasta;
		this.lprOrdenAprov = lprOrdenAprov;
		this.lprComision = lprComision;
		this.lprMinFiadores = lprMinFiadores;
		this.lprMinGarantias = lprMinGarantias;
		this.lprSueldoMinimo = lprSueldoMinimo;
		this.lprMinRefPersonales = lprMinRefPersonales;
		this.lprMinRefComerciales = lprMinRefComerciales;
		this.ctaDexDescuentosExternoses = ctaDexDescuentosExternoses;
		this.ctaTprTipoPrestamos = ctaTprTipoPrestamos;
	}

	/** default constructor */
	public AbstractCtaLprLineaPrestamo() {
	}

	/** minimal constructor */
	public AbstractCtaLprLineaPrestamo(Integer lprId, String lprNombre,
			Date lprDesde) {
		this.lprId = lprId;
		this.lprNombre = lprNombre;
		this.lprDesde = lprDesde;
	}

	/** full constructor */


	// Property accessors

	public Integer getLprId() {
		return this.lprId;
	}

	public void setLprId(Integer lprId) {
		this.lprId = lprId;
	}

	public String getLprNombre() {
		return this.lprNombre;
	}

	public void setLprNombre(String lprNombre) {
		this.lprNombre = lprNombre;
	}

	public String getLprDescripcion() {
		return this.lprDescripcion;
	}

	public void setLprDescripcion(String lprDescripcion) {
		this.lprDescripcion = lprDescripcion;
	}

	public Date getLprDesde() {
		return this.lprDesde;
	}

	public void setLprDesde(Date lprDesde) {
		this.lprDesde = lprDesde;
	}

	public Date getLprHasta() {
		return this.lprHasta;
	}

	public void setLprHasta(Date lprHasta) {
		this.lprHasta = lprHasta;
	}

	public String getLprOrdenAprov() {
		return this.lprOrdenAprov;
	}

	public void setLprOrdenAprov(String lprOrdenAprov) {
		this.lprOrdenAprov = lprOrdenAprov;
	}

	public Double getLprComision() {
		return lprComision;
	}

	public void setLprComision(Double lprComision) {
		this.lprComision = lprComision;
	}

	public Set getCtaDexDescuentosExternoses() {
		return this.ctaDexDescuentosExternoses;
	}

	public void setCtaDexDescuentosExternoses(Set ctaDexDescuentosExternoses) {
		this.ctaDexDescuentosExternoses = ctaDexDescuentosExternoses;
	}

	public Set getCtaTprTipoPrestamos() {
		return this.ctaTprTipoPrestamos;
	}

	public void setCtaTprTipoPrestamos(Set ctaTprTipoPrestamos) {
		this.ctaTprTipoPrestamos = ctaTprTipoPrestamos;
	}

	public Integer getLprMinFiadores() {
		return lprMinFiadores;
	}

	public void setLprMinFiadores(Integer lprMinFiadores) {
		this.lprMinFiadores = lprMinFiadores;
	}

	public Integer getLprMinGarantias() {
		return lprMinGarantias;
	}

	public void setLprMinGarantias(Integer lprMinGarantias) {
		this.lprMinGarantias = lprMinGarantias;
	}

	public Double getLprSueldoMinimo() {
		return lprSueldoMinimo;
	}

	public void setLprSueldoMinimo(Double lprSueldoMinimo) {
		this.lprSueldoMinimo = lprSueldoMinimo;
	}

	public Integer getLprMinRefPersonales() {
		return lprMinRefPersonales;
	}

	public void setLprMinRefPersonales(Integer lprMinRefPersonales) {
		this.lprMinRefPersonales = lprMinRefPersonales;
	}

	public Integer getLprMinRefComerciales() {
		return lprMinRefComerciales;
	}

	public void setLprMinRefComerciales(Integer lprMinRefComerciales) {
		this.lprMinRefComerciales = lprMinRefComerciales;
	}

	
}