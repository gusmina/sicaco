package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * ConCpaConceptoPartida entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConCpaConceptoPartida extends AbstractConCpaConceptoPartida
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ConCpaConceptoPartida() {
	}

	/** minimal constructor */
	public ConCpaConceptoPartida(Integer cpaId, String cpaConcepto,
			byte cpaDescripcionConcepto) {
		super(cpaId, cpaConcepto, cpaDescripcionConcepto);
	}

	/** full constructor */
	public ConCpaConceptoPartida(Integer cpaId,
			CtaTtrTipoTransaccion ctaTtrTipoTransaccion, String cpaConcepto,
			byte cpaDescripcionConcepto, Set conPcoPartidaContables,
			Set conMxcModuloxCuentacontables, Set conDpaDetallePartidas) {
		super(cpaId, ctaTtrTipoTransaccion, cpaConcepto,
				cpaDescripcionConcepto, conPcoPartidaContables,
				conMxcModuloxCuentacontables, conDpaDetallePartidas);
	}

}
