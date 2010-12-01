package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtrEstEstado entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrEstEstado extends AbstractCtrEstEstado implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtrEstEstado() {
	}

	/** minimal constructor */
	public CtrEstEstado(Integer estId, String estNombre) {
		super(estId, estNombre);
	}

	/** full constructor */
	public CtrEstEstado(Integer estId, CtrTusTipoUso ctrTusTipoUso,
			String estNombre, Set facFenFacturaEncabezados,
			Set ctaCahCuentaAhorros, Set ctaAscAsociados, Set ctaSegSeguroses,
			Set ctrPaiPaises, Set ctaCasCuentaAsociado) {
		super(estId, ctrTusTipoUso, estNombre, facFenFacturaEncabezados,
				ctaCahCuentaAhorros, ctaAscAsociados, ctaSegSeguroses, 
				ctrPaiPaises, ctaCasCuentaAsociado);
		// TODO Auto-generated constructor stub
	}
}
