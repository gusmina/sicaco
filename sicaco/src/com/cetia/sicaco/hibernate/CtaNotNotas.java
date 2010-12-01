package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtaNotNotas entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaNotNotas extends AbstractCtaNotNotas implements
		java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = -201804249184073832L;

	/** default constructor */
	public CtaNotNotas() {
	}

	/** minimal constructor */
	public CtaNotNotas(Integer notId, String notNota, Date notFecha) {
		super(notId, notNota, notFecha);
	}

	/** full constructor */
	public CtaNotNotas(Integer notId, CtaTntTipoNota ctaTntTipoNota,
			Long casCuenta, String notNota,
			Date notFecha, String notCampo, Set ctaAscAsociados,
			Set ctaInaIngresosxasociados, Set ctaTxaTransaccionxcuentaAsociados) {
		super(notId, ctaTntTipoNota, casCuenta, notNota, notFecha, notCampo,
				ctaAscAsociados, ctaInaIngresosxasociados,
				ctaTxaTransaccionxcuentaAsociados);
		// TODO Auto-generated constructor stub
	}
	
}
