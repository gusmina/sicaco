package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CtaMxaMovimientoAhorro entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaMxaMovimientoAhorro extends AbstractCtaMxaMovimientoAhorro
		implements java.io.Serializable {

	public CtaMxaMovimientoAhorro() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CtaMxaMovimientoAhorro(Integer mxaId,
			CtaCahCuentaAhorro ctaCahCuentaAhorro,
			CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado,
			Date mxaFecha, Double mxaMonto, Double mxaInteresTran,
			Double mxaSaldo, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(mxaId, ctaCahCuentaAhorro, ctaTxaTransaccionxcuentaAsociado, mxaFecha,
				mxaMonto, mxaInteresTran, mxaSaldo, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion, audUsuarioModificacion);
		// TODO Auto-generated constructor stub
	}


	public CtaMxaMovimientoAhorro(Integer mxaId, Date mxaFecha, Double mxaMonto) {
		super(mxaId, mxaFecha, mxaMonto);
		// TODO Auto-generated constructor stub
	}

		
}
