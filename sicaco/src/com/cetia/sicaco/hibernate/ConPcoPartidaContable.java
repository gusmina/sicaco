package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * ConPcoPartidaContable entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConPcoPartidaContable extends AbstractConPcoPartidaContable
		implements java.io.Serializable {

	public ConPcoPartidaContable() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ConPcoPartidaContable(long pcoId,
			ConTpaTipoPartida conTpaTipoPartida,
			ConCpaConceptoPartida conCpaConceptoPartida,
			CtaChkChequePrestamo ctaChkChequePrestamo,
			Integer pcoComprobantePartida, String pcoEstado,
			String pcoOtroConcepto, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Integer pcoChequePendiente,
			Date pcoFechaIngresoPartida, Integer pcoChequeNegociable,
			Integer pcoModulo, Set conDpaDetallePartidas) {
		super(pcoId, conTpaTipoPartida, conCpaConceptoPartida, ctaChkChequePrestamo,
				pcoComprobantePartida, pcoEstado, pcoOtroConcepto, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion, audUsuarioModificacion,
				pcoChequePendiente, pcoFechaIngresoPartida, pcoChequeNegociable,
				pcoModulo, conDpaDetallePartidas);
		// TODO Auto-generated constructor stub
	}


	public ConPcoPartidaContable(long pcoId, Integer pcoComprobantePartida,
			String pcoEstado, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Date pcoFechaIngresoPartida, Integer pcoChequeNegociable,
			Integer pcoModulo) {
		super(pcoId, pcoComprobantePartida, pcoEstado, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion, audUsuarioModificacion,
				pcoFechaIngresoPartida, pcoChequeNegociable, pcoModulo);
		// TODO Auto-generated constructor stub
	}

	
	
	

}
