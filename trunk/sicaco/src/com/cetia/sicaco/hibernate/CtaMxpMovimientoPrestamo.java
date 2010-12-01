package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CtaMxpMovimientoPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaMxpMovimientoPrestamo extends AbstractCtaMxpMovimientoPrestamo
		implements java.io.Serializable {

	private Date mxpFechaMovIni;
	private Date mxpFechaMovFin;
	
	// Constructors



	/** default constructor */
	public CtaMxpMovimientoPrestamo() {
	}

	

	public CtaMxpMovimientoPrestamo(Integer mxpId,
			CtaPrePrestamo ctaPrePrestamo,
			CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado,
			Date mxpFecha, Double mxpSaldo, Double mxpSaldoActual,
			Double mxpInteresPendiente, Double mxpMora,
			Double mxpInteresAcumulado, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(mxpId, ctaPrePrestamo, ctaTxaTransaccionxcuentaAsociado, mxpFecha,
				mxpSaldo, mxpSaldoActual, mxpInteresPendiente, mxpMora,
				mxpInteresAcumulado, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
		// TODO Auto-generated constructor stub
	}



	public Date getMxpFechaMovIni() {
		return mxpFechaMovIni;
	}

	public void setMxpFechaMovIni(Date mxpFechaMovIni) {
		this.mxpFechaMovIni = mxpFechaMovIni;
	}

	public Date getMxpFechaMovFin() {
		return mxpFechaMovFin;
	}

	public void setMxpFechaMovFin(Date mxpFechaMovFin) {
		this.mxpFechaMovFin = mxpFechaMovFin;
	}

}
