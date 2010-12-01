package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCtaSmaSaldosxmesActivo entity provides the base persistence
 * definition of the CtaSmaSaldosxmesActivo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaSmaSaldosxmesActivo implements
		java.io.Serializable {

	// Fields

	private Integer smaId;
	private CtaCahCuentaAhorro ctaCahCuentaAhorro = new CtaCahCuentaAhorro();
	private Double smaInterAcum;
	private Double smaSaldoAcum;
	private Date smaFecha;;

	// Constructors

	/** default constructor */
	public AbstractCtaSmaSaldosxmesActivo() {
	}

	/** minimal constructor */
	public AbstractCtaSmaSaldosxmesActivo(Integer smaId, Double smaInterAcum,
			Double smaSaldoAcum, Date smaFecha) {
		this.smaId = smaId;
		this.smaInterAcum = smaInterAcum;
		this.smaSaldoAcum = smaSaldoAcum;
		this.smaFecha = smaFecha;
	}

	/** full constructor */
	public AbstractCtaSmaSaldosxmesActivo(Integer smaId,
			CtaCahCuentaAhorro ctaCahCuentaAhorro, Double smaInterAcum,
			Double smaSaldoAcum, Date smaFecha) {
		this.smaId = smaId;
		this.ctaCahCuentaAhorro = ctaCahCuentaAhorro;
		this.smaInterAcum = smaInterAcum;
		this.smaSaldoAcum = smaSaldoAcum;
		this.smaFecha = smaFecha;
	}

	// Property accessors

	public Integer getSmaId() {
		return this.smaId;
	}

	public void setSmaId(Integer smaId) {
		this.smaId = smaId;
	}

	public CtaCahCuentaAhorro getCtaCahCuentaAhorro() {
		return this.ctaCahCuentaAhorro;
	}

	public void setCtaCahCuentaAhorro(CtaCahCuentaAhorro ctaCahCuentaAhorro) {
		this.ctaCahCuentaAhorro = ctaCahCuentaAhorro;
	}

	public Double getSmaInterAcum() {
		return this.smaInterAcum;
	}

	public void setSmaInterAcum(Double smaInterAcum) {
		this.smaInterAcum = smaInterAcum;
	}

	public Double getSmaSaldoAcum() {
		return this.smaSaldoAcum;
	}

	public void setSmaSaldoAcum(Double smaSaldoAcum) {
		this.smaSaldoAcum = smaSaldoAcum;
	}

	public Date getSmaFecha() {
		return smaFecha;
	}

	public void setSmaFecha(Date smaFecha) {
		this.smaFecha = smaFecha;
	}

}