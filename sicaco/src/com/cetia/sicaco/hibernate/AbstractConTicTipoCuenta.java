package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractConTicTipoCuenta entity provides the base persistence definition of
 * the ConTicTipoCuenta entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConTicTipoCuenta implements java.io.Serializable {

	// Fields

	private Integer ticId;
	private String ticNombre;
	private Integer ticAcreeDeudo;
	private Set conCueCuentas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractConTicTipoCuenta() {
	}

	/** minimal constructor */
	public AbstractConTicTipoCuenta(String ticNombre, Integer ticAcreeDeudo) {
		this.ticNombre = ticNombre;
		this.ticAcreeDeudo = ticAcreeDeudo;
	}

	/** full constructor */
	public AbstractConTicTipoCuenta(String ticNombre, Integer ticAcreeDeudo,
			Set conCueCuentas) {
		this.ticNombre = ticNombre;
		this.ticAcreeDeudo = ticAcreeDeudo;
		this.conCueCuentas = conCueCuentas;
	}

	// Property accessors

	public Integer getTicId() {
		return this.ticId;
	}

	public void setTicId(Integer ticId) {
		this.ticId = ticId;
	}

	public String getTicNombre() {
		return this.ticNombre;
	}

	public void setTicNombre(String ticNombre) {
		this.ticNombre = ticNombre;
	}

	public Integer getTicAcreeDeudo() {
		return this.ticAcreeDeudo;
	}

	public void setTicAcreeDeudo(Integer ticAcreeDeudo) {
		this.ticAcreeDeudo = ticAcreeDeudo;
	}

	public Set getConCueCuentas() {
		return this.conCueCuentas;
	}

	public void setConCueCuentas(Set conCueCuentas) {
		this.conCueCuentas = conCueCuentas;
	}

}