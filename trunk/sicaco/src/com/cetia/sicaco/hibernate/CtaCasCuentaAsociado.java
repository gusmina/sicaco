package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtaCasCuentaAsociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaCasCuentaAsociado extends AbstractCtaCasCuentaAsociado
		implements java.io.Serializable {

	public CtaCasCuentaAsociado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CtaCasCuentaAsociado(Long casCuenta, CtaPrePrestamo ctaPrePrestamo,
			CtaCahCuentaAhorro ctaCahCuentaAhorro,
			CtaCbaCuentaBancaria ctaCbaCuentaBancaria,
			CtaAscAsociado ctaAscAsociado, CtaSegSeguros ctaSegSeguros,
			CtrEstEstado ctrEstEstado,
			CtaPxtPersonaExterna ctaPxtPersonaExterna, Double casValorApertura,
			Date casFechaApertura, String casPrincipal, Date casFechaCierre,
			Long casRefinanciado, Long casPrestamoPaga, Set ctaTxaTransaccionxcuentaAsociados,
			Set ctaBxcBeneficiariosCuentas, Set ctaNotNotases) {
		super(casCuenta, ctaPrePrestamo, ctaCahCuentaAhorro, ctaCbaCuentaBancaria,
				ctaAscAsociado, ctaSegSeguros, ctrEstEstado, ctaPxtPersonaExterna,
				casValorApertura, casFechaApertura, casPrincipal, casFechaCierre,
				casRefinanciado, casPrestamoPaga, ctaTxaTransaccionxcuentaAsociados,
				ctaBxcBeneficiariosCuentas, ctaNotNotases);
		// TODO Auto-generated constructor stub
	}

	public CtaCasCuentaAsociado(Long casCuenta, Double casValorApertura,
			Date casFechaApertura, String casPrincipal) {
		super(casCuenta, casValorApertura, casFechaApertura, casPrincipal);
		// TODO Auto-generated constructor stub
	}


}
