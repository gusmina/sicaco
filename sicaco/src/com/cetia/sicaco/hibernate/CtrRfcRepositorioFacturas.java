package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtrRfcRepositorioFacturas entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrRfcRepositorioFacturas extends
		AbstractCtrRfcRepositorioFacturas implements java.io.Serializable {

	//constructors
	
	public CtrRfcRepositorioFacturas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CtrRfcRepositorioFacturas(Integer rfcId,
			CtrCfcControlFacturacion ctrCfcControlFacturacion,
			String rfcNombre, Integer rfcCorrIni, Integer rfcCorrFin,
			Integer rfcCorrActual, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String tipoFactCont, Integer sucID,
			String rfcEstado, Set ctrUreUsuarioRepositorios) {
		super(rfcId, ctrCfcControlFacturacion, rfcNombre, rfcCorrIni, rfcCorrFin,
				rfcCorrActual, audUsuarioCreacion, audFechaCreacion,
				audFechaModificacion, audUsuarioModificacion, tipoFactCont, sucID,
				rfcEstado, ctrUreUsuarioRepositorios);
		// TODO Auto-generated constructor stub
	}

	public CtrRfcRepositorioFacturas(Integer rfcId, String rfcNombre,
			Integer rfcCorrIni, Integer rfcCorrFin, Integer rfcCorrActual,
			String audUsuarioCreacion, Date audFechaCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			String tipoFactCont, Integer sucID, String rfcEstado) {
		super(rfcId, rfcNombre, rfcCorrIni, rfcCorrFin, rfcCorrActual,
				audUsuarioCreacion, audFechaCreacion, audFechaModificacion,
				audUsuarioModificacion, tipoFactCont, sucID, rfcEstado);
		// TODO Auto-generated constructor stub
	}

}
