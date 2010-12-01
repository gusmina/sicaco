package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTcuTipoCuenta entity provides the base persistence definition of
 * the CtaTcuTipoCuenta entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTcuTipoCuenta implements java.io.Serializable {

	// Fields

	private Integer tcuId;
	private String tcuNombre;
	private String tcuDescripcion;
	private Set ctaCbaCuentaBancarias = new HashSet(0);
	private Set invPcbProveedorCuentaBancarias = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTcuTipoCuenta() {
	}

	/** minimal constructor */
	public AbstractCtaTcuTipoCuenta(Integer tcuId, String tcuNombre) {
		this.tcuId = tcuId;
		this.tcuNombre = tcuNombre;
	}

	/** full constructor */
	public AbstractCtaTcuTipoCuenta(Integer tcuId, String tcuNombre,
			String tcuDescripcion, Set ctaCbaCuentaBancarias,
			Set invPcbProveedorCuentaBancarias) {
		this.tcuId = tcuId;
		this.tcuNombre = tcuNombre;
		this.tcuDescripcion = tcuDescripcion;
		this.ctaCbaCuentaBancarias = ctaCbaCuentaBancarias;
		this.invPcbProveedorCuentaBancarias = invPcbProveedorCuentaBancarias;
	}

	// Property accessors

	public Integer getTcuId() {
		return this.tcuId;
	}

	public void setTcuId(Integer tcuId) {
		this.tcuId = tcuId;
	}

	public String getTcuNombre() {
		return this.tcuNombre;
	}

	public void setTcuNombre(String tcuNombre) {
		this.tcuNombre = tcuNombre;
	}

	public String getTcuDescripcion() {
		return this.tcuDescripcion;
	}

	public void setTcuDescripcion(String tcuDescripcion) {
		this.tcuDescripcion = tcuDescripcion;
	}

	public Set getCtaCbaCuentaBancarias() {
		return this.ctaCbaCuentaBancarias;
	}

	public void setCtaCbaCuentaBancarias(Set ctaCbaCuentaBancarias) {
		this.ctaCbaCuentaBancarias = ctaCbaCuentaBancarias;
	}

	public Set getInvPcbProveedorCuentaBancarias() {
		return invPcbProveedorCuentaBancarias;
	}

	public void setInvPcbProveedorCuentaBancarias(Set invPcbProveedorCuentaBancarias) {
		this.invPcbProveedorCuentaBancarias = invPcbProveedorCuentaBancarias;
	}

}