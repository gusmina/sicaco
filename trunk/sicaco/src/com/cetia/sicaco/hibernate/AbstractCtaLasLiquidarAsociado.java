package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaLasLiquidarAsociado entity provides the base persistence
 * definition of the CtaLasLiquidarAsociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaLasLiquidarAsociado implements
		java.io.Serializable {

	// Fields

	private CtaLasLiquidarAsociadoId id;

	// Constructors

	/** default constructor */
	public AbstractCtaLasLiquidarAsociado() {
	}

	/** full constructor */
	public AbstractCtaLasLiquidarAsociado(CtaLasLiquidarAsociadoId id) {
		this.id = id;
	}

	// Property accessors

	public CtaLasLiquidarAsociadoId getId() {
		return this.id;
	}

	public void setId(CtaLasLiquidarAsociadoId id) {
		this.id = id;
	}

}