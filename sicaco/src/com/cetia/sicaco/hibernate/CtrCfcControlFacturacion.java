package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtrCfcControlFacturacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrCfcControlFacturacion extends AbstractCtrCfcControlFacturacion
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtrCfcControlFacturacion() {
	}

	/** minimal constructor */
	public CtrCfcControlFacturacion(String cfcSerie, Integer cfcCorrIni,
			Integer cfcCorrFin, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(cfcSerie, cfcCorrIni, cfcCorrFin, audUsuarioCreacion,
				audFechaCreacion, audFechaModificacion, audUsuarioModificacion);
	}

	/** full constructor */
	public CtrCfcControlFacturacion(String cfcSerie,
			CtrEmpEmpresa ctrEmpEmpresa, Integer cfcCorrIni,
			Integer cfcCorrFin, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Integer cfcDigitos,
			Set ctrRfcRepositorioFacturases) {
		super(cfcSerie, ctrEmpEmpresa, cfcCorrIni, cfcCorrFin,
				audUsuarioCreacion, audFechaCreacion, audFechaModificacion,
				audUsuarioModificacion, cfcDigitos,
				ctrRfcRepositorioFacturases);
	}

}
