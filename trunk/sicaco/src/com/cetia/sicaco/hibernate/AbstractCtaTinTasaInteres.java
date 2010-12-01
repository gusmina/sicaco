package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTinTasaInteres entity provides the base persistence definition of
 * the CtaTinTasaInteres entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTinTasaInteres implements java.io.Serializable {

	// Fields

	private Integer tinId;
	private String tinNombre;
	private Double tinTasa;
	private String tinDescripcion;
	private Set ctaSegSeguroses = new HashSet(0);
	private Set ctaPrePrestamos = new HashSet(0);
	private Set ctaTprTipoPrestamos = new HashSet(0);
	private Set ctaTahTipoAhorros = new HashSet(0);
	private Set ctaHtsHistorialTasas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTinTasaInteres() {
	}

	/** minimal constructor */
	public AbstractCtaTinTasaInteres(Integer tinId, String tinNombre,
			Double tinTasa) {
		this.tinId = tinId;
		this.tinNombre = tinNombre;
		this.tinTasa = tinTasa;
	}

	/** full constructor */
	public AbstractCtaTinTasaInteres(Integer tinId, String tinNombre,
			Double tinTasa, String tinDescripcion, Set ctaSegSeguroses,
			Set ctaPrePrestamos, Set ctaTprTipoPrestamos, Set ctaTahTipoAhorros,
			Set ctaHtsHistorialTasas) {
		this.tinId = tinId;
		this.tinNombre = tinNombre;
		this.tinTasa = tinTasa;
		this.tinDescripcion = tinDescripcion;
		this.ctaSegSeguroses = ctaSegSeguroses;
		this.ctaPrePrestamos = ctaPrePrestamos;
		this.ctaTprTipoPrestamos = ctaTprTipoPrestamos;
		this.ctaTahTipoAhorros = ctaTahTipoAhorros;
		this.ctaHtsHistorialTasas = ctaHtsHistorialTasas;
	}

	// Property accessors

	public Integer getTinId() {
		return this.tinId;
	}

	public void setTinId(Integer tinId) {
		this.tinId = tinId;
	}

	public String getTinNombre() {
		return this.tinNombre;
	}

	public void setTinNombre(String tinNombre) {
		this.tinNombre = tinNombre;
	}

	public Double getTinTasa() {
		return this.tinTasa;
	}

	public void setTinTasa(Double tinTasa) {
		this.tinTasa = tinTasa;
	}

	public String getTinDescripcion() {
		return this.tinDescripcion;
	}

	public void setTinDescripcion(String tinDescripcion) {
		this.tinDescripcion = tinDescripcion;
	}

	public Set getCtaSegSeguroses() {
		return this.ctaSegSeguroses;
	}

	public void setCtaSegSeguroses(Set ctaSegSeguroses) {
		this.ctaSegSeguroses = ctaSegSeguroses;
	}

	public Set getCtaPrePrestamos() {
		return this.ctaPrePrestamos;
	}

	public void setCtaPrePrestamos(Set ctaPrePrestamos) {
		this.ctaPrePrestamos = ctaPrePrestamos;
	}

	public Set getCtaTprTipoPrestamos() {
		return this.ctaTprTipoPrestamos;
	}

	public void setCtaTprTipoPrestamos(Set ctaTprTipoPrestamos) {
		this.ctaTprTipoPrestamos = ctaTprTipoPrestamos;
	}

	public Set getCtaTahTipoAhorros() {
		return this.ctaTahTipoAhorros;
	}

	public void setCtaTahTipoAhorros(Set ctaTahTipoAhorros) {
		this.ctaTahTipoAhorros = ctaTahTipoAhorros;
	}

	public Set getCtaHtsHistorialTasas() {
		return ctaHtsHistorialTasas;
	}

	public void setCtaHtsHistorialTasas(Set ctaHtsHistorialTasas) {
		this.ctaHtsHistorialTasas = ctaHtsHistorialTasas;
	}

}