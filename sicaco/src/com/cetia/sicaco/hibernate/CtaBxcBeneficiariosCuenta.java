package com.cetia.sicaco.hibernate;

/**
 * CtaBxcBeneficiariosCuenta entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaBxcBeneficiariosCuenta extends
		AbstractCtaBxcBeneficiariosCuenta implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaBxcBeneficiariosCuenta() {
	}

	/** full constructor */
	public CtaBxcBeneficiariosCuenta(CtaCasCuentaAsociado ctaCasCuentaAsociado,
			CtaBenBeneficiarios ctaBenBeneficiarios, Float bxcPorcentaje) {
		super(ctaCasCuentaAsociado, ctaBenBeneficiarios, bxcPorcentaje);
	}

}
