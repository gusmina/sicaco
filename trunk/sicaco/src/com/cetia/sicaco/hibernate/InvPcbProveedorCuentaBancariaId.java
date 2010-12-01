package com.cetia.sicaco.hibernate;

/**
 * InvPcbProveedorCuentaBancariaId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvPcbProveedorCuentaBancariaId extends
		AbstractInvPcbProveedorCuentaBancariaId implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvPcbProveedorCuentaBancariaId() {
	}

	/** full constructor */
	public InvPcbProveedorCuentaBancariaId(InvProProveedor invProProveedor,
			CtrBanBanco ctrBanBanco, String pcbNumeroCuenta) {
		super(invProProveedor, ctrBanBanco, pcbNumeroCuenta);
	}

}
