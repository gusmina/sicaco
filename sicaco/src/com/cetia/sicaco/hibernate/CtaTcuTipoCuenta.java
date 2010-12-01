package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaTcuTipoCuenta entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTcuTipoCuenta extends AbstractCtaTcuTipoCuenta implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaTcuTipoCuenta() {
	}

	/** minimal constructor */
	public CtaTcuTipoCuenta(Integer tcuId, String tcuNombre) {
		super(tcuId, tcuNombre);
	}

	/** full constructor */
	public CtaTcuTipoCuenta(Integer tcuId, String tcuNombre,
			String tcuDescripcion, Set ctaCbaCuentaBancarias,
			Set invPcbProveedorCuentaBancarias) {
		super(tcuId, tcuNombre, tcuDescripcion, ctaCbaCuentaBancarias,
				invPcbProveedorCuentaBancarias);
		// TODO Auto-generated constructor stub
	}

}
