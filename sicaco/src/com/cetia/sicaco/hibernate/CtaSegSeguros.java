package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaSegSeguros entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaSegSeguros extends AbstractCtaSegSeguros implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaSegSeguros() {
	}

	/** minimal constructor */
	public CtaSegSeguros(String segId, String segCertificado, String segCarnet,
			Double segMonto, Double segSaldoActual, Double segCuota,
			Double segSaldoIni) {
		super(segId, segCertificado, segCarnet, segMonto, segSaldoActual,
				segCuota, segSaldoIni);
	}

	public CtaSegSeguros(String segId, CtaTisTipoSeguro ctaTisTipoSeguro,
			CtaTinTasaInteres ctaTinTasaInteres, String segCertificado,
			String segCarnet, Double segMonto, Double segSaldoActual,
			String segReferencia, Double segCuota, Double segSaldoIni,
			Integer segNumCuotaCancel, Set ctaPrePrestamos,
			Set ctaCasCuentaAsociados, Set ctaMxsMovimientoSeguroses) {
		super(segId, ctaTisTipoSeguro, ctaTinTasaInteres, segCertificado, segCarnet,
				segMonto, segSaldoActual, segReferencia, segCuota, segSaldoIni,
				segNumCuotaCancel, ctaPrePrestamos, ctaCasCuentaAsociados,
				ctaMxsMovimientoSeguroses);
		// TODO Auto-generated constructor stub
	}

}
