package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtaPrePrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaPrePrestamo extends AbstractCtaPrePrestamo implements
		java.io.Serializable {

	public CtaPrePrestamo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CtaPrePrestamo(String preId, CtaTprTipoPrestamo ctaTprTipoPrestamo,
			CtaTinTasaInteres ctaTinTasaInteres, CtaSegSeguros ctaSegSeguros,
			CtaCbaCuentaBancaria ctaCbaCuentaBancaria,
			Long casCuenta, String preReferencia, Double preCuota,
			Double preMontoSolicitado, Double preSaldoActualT,
			Date preFechaSolicitud, Double preInteresAcumulado,
			Double preGastoAbogado, Double preMoraEmbargo,
			Double preLiquidoARecibir, Integer preNumCuotaCancel,
			Double preOtrasDeducciones, Double preIvaDeducciones,
			Double preAportaciones, Double prePendMov, Double preMoraMov,
			Double preAcumMov, String preCredito,
			Set ctaMxpMovimientoPrestamos, Set ctaRcoReferenciasComercialeses,
			Set ctaCasCuentaAsociados, Set ctaDexDescuentosExternoses,
			Set ctaRpeReferenciasPersonaleses, Set ctaGarGarantias,
			Set ctaChkChequePrestamos, Set ctaFxpFiadorPrestamos) {
		super(preId, ctaTprTipoPrestamo, ctaTinTasaInteres, ctaSegSeguros, ctaCbaCuentaBancaria, casCuenta,
				preReferencia, preCuota, preMontoSolicitado, preSaldoActualT,
				preFechaSolicitud, preInteresAcumulado, preGastoAbogado,
				preMoraEmbargo, preLiquidoARecibir, preNumCuotaCancel,
				preOtrasDeducciones, preIvaDeducciones, preAportaciones, prePendMov, preMoraMov,
				preAcumMov, preCredito, ctaMxpMovimientoPrestamos,
				ctaRcoReferenciasComercialeses, ctaCasCuentaAsociados,
				ctaDexDescuentosExternoses, ctaRpeReferenciasPersonaleses,
				ctaGarGarantias, ctaChkChequePrestamos, ctaFxpFiadorPrestamos);
		// TODO Auto-generated constructor stub
	}


	public CtaPrePrestamo(String preId, Double preCuota,
			Double preMontoSolicitado, Double preSaldoActualT,
			Date preFechaSolicitud, Double preInteresAcumulado,
			Double preLiquidoARecibir) {
		super(preId, preCuota, preMontoSolicitado, preSaldoActualT, preFechaSolicitud,
				preInteresAcumulado, preLiquidoARecibir);
		// TODO Auto-generated constructor stub
	}



}
