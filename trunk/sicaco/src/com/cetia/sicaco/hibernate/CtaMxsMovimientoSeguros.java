package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CtaMxsMovimientoSeguros entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaMxsMovimientoSeguros extends AbstractCtaMxsMovimientoSeguros
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaMxsMovimientoSeguros() {
	}

	/** minimal constructor */
	public CtaMxsMovimientoSeguros(Double mxsMonto, Double mxsSaldo,
			Date mxsFecha) {
		super(mxsMonto, mxsSaldo, mxsFecha);
	}

	public CtaMxsMovimientoSeguros(CtaSegSeguros ctaSegSeguros,
			CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado,
			Double mxsMonto, Double mxsSaldo, Date mxsFecha,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(ctaSegSeguros, ctaTxaTransaccionxcuentaAsociado, mxsMonto, mxsSaldo,
				mxsFecha, audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
		// TODO Auto-generated constructor stub
	}

	/** full constructor */

}
