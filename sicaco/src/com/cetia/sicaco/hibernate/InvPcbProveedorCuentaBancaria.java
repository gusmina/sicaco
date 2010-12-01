package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvPcbProveedorCuentaBancaria entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvPcbProveedorCuentaBancaria extends
		AbstractInvPcbProveedorCuentaBancaria implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvPcbProveedorCuentaBancaria() {
	}

	/** full constructor */
	public InvPcbProveedorCuentaBancaria(InvPcbProveedorCuentaBancariaId id,
			CtaTcuTipoCuenta ctaTcuTipoCuenta, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set ctaStbSolTransBancs) {
		super(id, ctaTcuTipoCuenta, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion, ctaStbSolTransBancs);
		// TODO Auto-generated constructor stub
	}

}
