package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaRxpRetencionPrestamo entity provides the base persistence
 * definition of the CtaRxpRetencionPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaRxpRetencionPrestamo implements
		java.io.Serializable {

	// Fields

	private Integer rxpId;
	private CtaPrePrestamo ctaPrePrestamo;
	private String rxpNombre;
	private Double rxpMonto;

	// Constructors

	/** default constructor */
	public AbstractCtaRxpRetencionPrestamo() {
	}

	/** full constructor */
	public AbstractCtaRxpRetencionPrestamo(Integer rxpId,
			CtaPrePrestamo ctaPrePrestamo, String rxpNombre, Double rxpMonto) {
		this.rxpId = rxpId;
		this.ctaPrePrestamo = ctaPrePrestamo;
		this.rxpNombre = rxpNombre;
		this.rxpMonto = rxpMonto;
	}

	// Property accessors

	public Integer getRxpId() {
		return this.rxpId;
	}

	public void setRxpId(Integer rxpId) {
		this.rxpId = rxpId;
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return this.ctaPrePrestamo;
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		this.ctaPrePrestamo = ctaPrePrestamo;
	}

	public String getRxpNombre() {
		return this.rxpNombre;
	}

	public void setRxpNombre(String rxpNombre) {
		this.rxpNombre = rxpNombre;
	}

	public Double getRxpMonto() {
		return this.rxpMonto;
	}

	public void setRxpMonto(Double rxpMonto) {
		this.rxpMonto = rxpMonto;
	}

}