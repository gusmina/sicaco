package com.cetia.sicaco.hibernate;

/**
 * CtrSreSucursalRepositorioId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrSreSucursalRepositorioId extends
		AbstractCtrSreSucursalRepositorioId implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtrSreSucursalRepositorioId() {
	}

	/** full constructor */
	public CtrSreSucursalRepositorioId(
			CtrRfcRepositorioFacturas ctrRfcRepositorioFacturas,
			SecSucSucursal secSucSucursal) {
		super(ctrRfcRepositorioFacturas, secSucSucursal);
	}

}
