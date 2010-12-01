package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtaChkChequePrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaChkChequePrestamo extends AbstractCtaChkChequePrestamo
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaChkChequePrestamo() {
	}

	public CtaChkChequePrestamo(CtaPrePrestamo ctaPrePrestamo,
			CtrBanBanco ctrBanBanco, String chkEmitidoA, Float chkMontoEmitido,
			String chkRazon, Integer chkCorrelativoCheque, String chkLugar,
			Date chkFecha, Set conPcoPartidaContables) {
		super(ctaPrePrestamo, ctrBanBanco, chkEmitidoA, chkMontoEmitido, chkRazon,
				chkCorrelativoCheque, chkLugar, chkFecha, conPcoPartidaContables);
		// TODO Auto-generated constructor stub
	}

	public CtaChkChequePrestamo(String chkEmitidoA, Float chkMontoEmitido,
			Integer chkCorrelativoCheque, String chkLugar, Date chkFecha) {
		super(chkEmitidoA, chkMontoEmitido, chkCorrelativoCheque, chkLugar, chkFecha);
		// TODO Auto-generated constructor stub
	}


}
