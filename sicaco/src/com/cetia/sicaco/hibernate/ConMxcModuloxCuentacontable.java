package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * ConMxcModuloxCuentacontable entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConMxcModuloxCuentacontable extends
		AbstractConMxcModuloxCuentacontable implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ConMxcModuloxCuentacontable() {
	}

	/** minimal constructor */
	public ConMxcModuloxCuentacontable(long cxcId, ConCueCuenta conCueCuenta,
			String cxcParametrosUnion) {
		super(cxcId, conCueCuenta, cxcParametrosUnion);
	}

	/** full constructor */
	public ConMxcModuloxCuentacontable(long cxcId,
			ConCpaConceptoPartida conCpaConceptoPartida,
			ConCueCuenta conCueCuenta, String cxcParametrosUnion,
			byte cxcCargoAbono, Date cxcFechaRelacion, String cxaConceptoExtra) {
		super(cxcId, conCpaConceptoPartida, conCueCuenta, cxcParametrosUnion,
				cxcCargoAbono, cxcFechaRelacion, cxaConceptoExtra);
	}

}
