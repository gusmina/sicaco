package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CtaStbSolTransBanc entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaStbSolTransBanc extends AbstractCtaStbSolTransBanc implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaStbSolTransBanc() {
	}

	/** minimal constructor */
	public CtaStbSolTransBanc(Integer stbId, String stbRazon, Date stbFechaSol,
			Double stbMonto) {
		super(stbId, stbRazon, stbFechaSol, stbMonto);
	}

	public CtaStbSolTransBanc(Integer stbId,
			CtaCbaCuentaBancaria ctaCbaCuentaBancaria,
			CtaCahCuentaAhorro ctaCahCuentaAhorro, String stbRazon,
			Date stbFechaSol, Double stbMonto, String stbNombreAsociado,
			String stbEstado, String stbTipoAhorro, Integer opaId, String preId,
			InvPcbProveedorCuentaBancaria invPcbProveedorCuentaBancaria, Double stbPenalidad) {
		super(stbId, ctaCbaCuentaBancaria, ctaCahCuentaAhorro, stbRazon, stbFechaSol,
				stbMonto, stbNombreAsociado, stbEstado, stbTipoAhorro, opaId, preId,
				invPcbProveedorCuentaBancaria, stbPenalidad);
		// TODO Auto-generated constructor stub
	}

}
