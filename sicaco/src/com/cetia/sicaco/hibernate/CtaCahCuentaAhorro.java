package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaCahCuentaAhorro entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaCahCuentaAhorro extends AbstractCtaCahCuentaAhorro implements
		java.io.Serializable {

	public CtaCahCuentaAhorro() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CtaCahCuentaAhorro(String cahId, CtaTahTipoAhorro ctaTahTipoAhorro,
			Double cahSaldoActual, Double cahCuota, Double cahInteresAcumulado,
			Set ctaCasCuentaAsociados, Set ctaSmaSaldosxmesActivos,
			Set ctaMxaMovimientoAhorros, Set ctaStbSolTransBancs) {
		super(cahId, ctaTahTipoAhorro, cahSaldoActual, cahCuota, cahInteresAcumulado,
				ctaCasCuentaAsociados, ctaSmaSaldosxmesActivos,
				ctaMxaMovimientoAhorros, ctaStbSolTransBancs);
		// TODO Auto-generated constructor stub
	}


	public CtaCahCuentaAhorro(String cahId, Double cahSaldoActual,
			Double cahCuota, Double cahInteresAcumulado) {
		super(cahId, cahSaldoActual, cahCuota, cahInteresAcumulado);
		// TODO Auto-generated constructor stub
	}



	

}
