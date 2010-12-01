package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaCbaCuentaBancaria entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaCbaCuentaBancaria extends AbstractCtaCbaCuentaBancaria
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaCbaCuentaBancaria() {
	}

	/** minimal constructor */
	public CtaCbaCuentaBancaria(String cbaId, String cbaCuenta) {
		super(cbaId, cbaCuenta);
	}

	public CtaCbaCuentaBancaria(String cbaId,
			CtaTcuTipoCuenta ctaTcuTipoCuenta, CtrBanBanco ctrBanBanco,
			String cbaCuenta, Set ctaStbSolTransBancs, Set ctaPrePrestamos,
			Set ctaCasCuentaAsociados) {
		super(cbaId, ctaTcuTipoCuenta, ctrBanBanco, cbaCuenta, ctaStbSolTransBancs,
				ctaPrePrestamos, ctaCasCuentaAsociados);
		// TODO Auto-generated constructor stub
	}

	/** full constructor */

}
