package com.cetia.sicaco.hibernate;

/**
 * IucPutProveedorTipoPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class IucPutProveedorTipoPrestamo extends
		AbstractIucPutProveedorTipoPrestamo implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public IucPutProveedorTipoPrestamo() {
	}

	/** full constructor */
	public IucPutProveedorTipoPrestamo(InvProProveedor invProProveedor,
			CtaTprTipoPrestamo ctaTprTipoPrestamo) {
		super(invProProveedor, ctaTprTipoPrestamo);
	}

}
