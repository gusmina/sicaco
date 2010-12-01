package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtrBanBanco entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrBanBanco extends AbstractCtrBanBanco implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtrBanBanco() {
	}

	public CtrBanBanco(Integer banId, CtrPaiPais ctrPaiPais, String banNombre,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set invPcbProveedorCuentaBancarias, Set ctrCckControlChequeses,
			Set ctaCbaCuentaBancarias, Set ctaChkChequePrestamos) {
		super(banId, ctrPaiPais, banNombre, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion,
				invPcbProveedorCuentaBancarias, ctrCckControlChequeses,
				ctaCbaCuentaBancarias, ctaChkChequePrestamos);
		// TODO Auto-generated constructor stub
	}

	public CtrBanBanco(CtrPaiPais ctrPaiPais, String banNombre,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(ctrPaiPais, banNombre, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
		// TODO Auto-generated constructor stub
	}


}
