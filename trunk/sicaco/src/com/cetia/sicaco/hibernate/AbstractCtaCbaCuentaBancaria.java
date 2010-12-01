package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaCbaCuentaBancaria entity provides the base persistence definition
 * of the CtaCbaCuentaBancaria entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaCbaCuentaBancaria implements
		java.io.Serializable {

	// Fields

	private String cbaId;
	private CtaTcuTipoCuenta ctaTcuTipoCuenta = new CtaTcuTipoCuenta();
	private CtrBanBanco ctrBanBanco = new CtrBanBanco();
	private String cbaCuenta;
	private Set ctaStbSolTransBancs = new HashSet(0);
	private Set ctaPrePrestamos = new HashSet(0);
	private Set ctaCasCuentaAsociados = new HashSet(0);

	// Constructors

	public AbstractCtaCbaCuentaBancaria(String cbaId,
			CtaTcuTipoCuenta ctaTcuTipoCuenta, CtrBanBanco ctrBanBanco,
			String cbaCuenta, Set ctaStbSolTransBancs, Set ctaPrePrestamos,
			Set ctaCasCuentaAsociados) {
		super();
		this.cbaId = cbaId;
		this.ctaTcuTipoCuenta = ctaTcuTipoCuenta;
		this.ctrBanBanco = ctrBanBanco;
		this.cbaCuenta = cbaCuenta;
		this.ctaStbSolTransBancs = ctaStbSolTransBancs;
		this.ctaPrePrestamos = ctaPrePrestamos;
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
	}

	/** default constructor */
	public AbstractCtaCbaCuentaBancaria() {
	}

	/** minimal constructor */
	public AbstractCtaCbaCuentaBancaria(String cbaId, String cbaCuenta) {
		this.cbaId = cbaId;
		this.cbaCuenta = cbaCuenta;
	}

	/** full constructor */

	// Property accessors

	public String getCbaId() {
		return this.cbaId;
	}

	public void setCbaId(String cbaId) {
		this.cbaId = cbaId;
	}

	public CtaTcuTipoCuenta getCtaTcuTipoCuenta() {
		return this.ctaTcuTipoCuenta;
	}

	public void setCtaTcuTipoCuenta(CtaTcuTipoCuenta ctaTcuTipoCuenta) {
		this.ctaTcuTipoCuenta = ctaTcuTipoCuenta;
	}

	public CtrBanBanco getCtrBanBanco() {
		return ctrBanBanco;
	}

	public void setCtrBanBanco(CtrBanBanco ctrBanBanco) {
		this.ctrBanBanco = ctrBanBanco;
	}

	public String getCbaCuenta() {
		return this.cbaCuenta;
	}

	public void setCbaCuenta(String cbaCuenta) {
		this.cbaCuenta = cbaCuenta;
	}

	public Set getCtaStbSolTransBancs() {
		return this.ctaStbSolTransBancs;
	}

	public void setCtaStbSolTransBancs(Set ctaStbSolTransBancs) {
		this.ctaStbSolTransBancs = ctaStbSolTransBancs;
	}

	public Set getCtaPrePrestamos() {
		return this.ctaPrePrestamos;
	}

	public void setCtaPrePrestamos(Set ctaPrePrestamos) {
		this.ctaPrePrestamos = ctaPrePrestamos;
	}

	public Set getCtaCasCuentaAsociados() {
		return this.ctaCasCuentaAsociados;
	}

	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
	}

}