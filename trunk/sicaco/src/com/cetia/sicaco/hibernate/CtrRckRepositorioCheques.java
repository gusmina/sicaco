package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CtrRckRepositorioCheques entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrRckRepositorioCheques extends AbstractCtrRckRepositorioCheques
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtrRckRepositorioCheques() {
	}

	public CtrRckRepositorioCheques(Integer rckId,
			CtrCckControlCheques ctrCckControlCheques,
			SecSucSucursal secSucSucursal, String rckNombre,
			Integer rckCorrIni, Integer rckCorrFin, Integer rckCorrActual,
			Date rckFechaIni, Date rckFechaFin, String audUsuarioCreacion,
			Date audFechaCreacion, String audUsuarioModificacion,
			Date audFechaModificacion, String rckEstado) {
		super(rckId, ctrCckControlCheques, secSucSucursal, rckNombre, rckCorrIni,
				rckCorrFin, rckCorrActual, rckFechaIni, rckFechaFin,
				audUsuarioCreacion, audFechaCreacion, audUsuarioModificacion,
				audFechaModificacion, rckEstado);
		// TODO Auto-generated constructor stub
	}

	public CtrRckRepositorioCheques(Integer rckId, String rckNombre,
			Integer rckCorrIni, Integer rckCorrFin, Integer rckCorrActual,
			String audUsuarioCreacion, Date audFechaCreacion, String rckEstado) {
		super(rckId, rckNombre, rckCorrIni, rckCorrFin, rckCorrActual,
				audUsuarioCreacion, audFechaCreacion, rckEstado);
		// TODO Auto-generated constructor stub
	}


}
