package com.cetia.sicaco.hibernate;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaCahCuentaAhorro entity provides the base persistence definition of
 * the CtaCahCuentaAhorro entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaCahCuentaAhorro implements
		java.io.Serializable {

	// Fields

	private String cahId;
	private CtaTahTipoAhorro ctaTahTipoAhorro=new CtaTahTipoAhorro();
	private Double cahSaldoActual;
	private Double cahCuota;
	private Double cahInteresAcumulado;
	private Set ctaCasCuentaAsociados = new HashSet(0);
	private Set ctaSmaSaldosxmesActivos = new HashSet(0);
	private Set ctaMxaMovimientoAhorros = new HashSet(0);
	private Set ctaStbSolTransBancs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaCahCuentaAhorro() {
	}

	/** minimal constructor */
	public AbstractCtaCahCuentaAhorro(String cahId, Double cahSaldoActual,
			Double cahCuota, Double cahInteresAcumulado) {
		this.cahId = cahId;
		this.cahSaldoActual = cahSaldoActual;
		this.cahCuota = cahCuota;
		this.cahInteresAcumulado = cahInteresAcumulado;
		
	}


	// Property accessors

	public AbstractCtaCahCuentaAhorro(String cahId,
			CtaTahTipoAhorro ctaTahTipoAhorro, Double cahSaldoActual,
			Double cahCuota, Double cahInteresAcumulado,
			Set ctaCasCuentaAsociados, Set ctaSmaSaldosxmesActivos,
			Set ctaMxaMovimientoAhorros, Set ctaStbSolTransBancs) {
		super();
		this.cahId = cahId;
		this.ctaTahTipoAhorro = ctaTahTipoAhorro;
		this.cahSaldoActual = cahSaldoActual;
		this.cahCuota = cahCuota;
		this.cahInteresAcumulado = cahInteresAcumulado;
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
		this.ctaSmaSaldosxmesActivos = ctaSmaSaldosxmesActivos;
		this.ctaMxaMovimientoAhorros = ctaMxaMovimientoAhorros;
		this.ctaStbSolTransBancs = ctaStbSolTransBancs;
	}

	public String getCahId() {
		return this.cahId;
	}

	public void setCahId(String cahId) {
		this.cahId = cahId;
	}

	public CtaTahTipoAhorro getCtaTahTipoAhorro() {
		return this.ctaTahTipoAhorro;
	}

	public void setCtaTahTipoAhorro(CtaTahTipoAhorro ctaTahTipoAhorro) {
		this.ctaTahTipoAhorro = ctaTahTipoAhorro;
	}

	public Double getCahSaldoActual() {
		return this.cahSaldoActual;
	}

	public void setCahSaldoActual(Double cahSaldoActual) {
		this.cahSaldoActual = cahSaldoActual;
	}

	public Double getCahCuota() {
		return this.cahCuota;
	}

	public void setCahCuota(Double cahCuota) {
		this.cahCuota = cahCuota;
	}

	public Double getCahInteresAcumulado() {
		return this.cahInteresAcumulado;
	}

	public void setCahInteresAcumulado(Double cahInteresAcumulado) {
		this.cahInteresAcumulado = cahInteresAcumulado;
	}

	public Set getCtaCasCuentaAsociados() {
		return this.ctaCasCuentaAsociados;
	}

	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
	}

	public Set getCtaSmaSaldosxmesActivos() {
		return this.ctaSmaSaldosxmesActivos;
	}

	public void setCtaSmaSaldosxmesActivos(Set ctaSmaSaldosxmesActivos) {
		this.ctaSmaSaldosxmesActivos = ctaSmaSaldosxmesActivos;
	}

	public Set getCtaMxaMovimientoAhorros() {
		return this.ctaMxaMovimientoAhorros;
	}

	public void setCtaMxaMovimientoAhorros(Set ctaMxaMovimientoAhorros) {
		this.ctaMxaMovimientoAhorros = ctaMxaMovimientoAhorros;
	}

	public Set getCtaStbSolTransBancs() {
		return ctaStbSolTransBancs;
	}

	public void setCtaStbSolTransBancs(Set ctaStbSolTransBancs) {
		this.ctaStbSolTransBancs = ctaStbSolTransBancs;
	}

}