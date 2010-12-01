package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaBxcBeneficiariosCuenta entity provides the base persistence
 * definition of the CtaBxcBeneficiariosCuenta entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaBxcBeneficiariosCuenta implements
		java.io.Serializable {

	// Fields

	private Integer bxcId;
	private CtaCasCuentaAsociado ctaCasCuentaAsociado = new CtaCasCuentaAsociado();
	private CtaBenBeneficiarios ctaBenBeneficiarios = new CtaBenBeneficiarios();
	private Float bxcPorcentaje;

	// Constructors

	/** default constructor */
	public AbstractCtaBxcBeneficiariosCuenta() {
	}

	/** full constructor */
	public AbstractCtaBxcBeneficiariosCuenta(
			CtaCasCuentaAsociado ctaCasCuentaAsociado,
			CtaBenBeneficiarios ctaBenBeneficiarios, Float bxcPorcentaje) {
		this.ctaCasCuentaAsociado = ctaCasCuentaAsociado;
		this.ctaBenBeneficiarios = ctaBenBeneficiarios;
		this.bxcPorcentaje = bxcPorcentaje;
	}

	// Property accessors

	public Integer getBxcId() {
		return this.bxcId;
	}

	public void setBxcId(Integer bxcId) {
		this.bxcId = bxcId;
	}

	public CtaCasCuentaAsociado getCtaCasCuentaAsociado() {
		return this.ctaCasCuentaAsociado;
	}

	public void setCtaCasCuentaAsociado(
			CtaCasCuentaAsociado ctaCasCuentaAsociado) {
		this.ctaCasCuentaAsociado = ctaCasCuentaAsociado;
	}

	public CtaBenBeneficiarios getCtaBenBeneficiarios() {
		return this.ctaBenBeneficiarios;
	}

	public void setCtaBenBeneficiarios(CtaBenBeneficiarios ctaBenBeneficiarios) {
		this.ctaBenBeneficiarios = ctaBenBeneficiarios;
	}

	public Float getBxcPorcentaje() {
		return this.bxcPorcentaje;
	}

	public void setBxcPorcentaje(Float bxcPorcentaje) {
		this.bxcPorcentaje = bxcPorcentaje;
	}

}