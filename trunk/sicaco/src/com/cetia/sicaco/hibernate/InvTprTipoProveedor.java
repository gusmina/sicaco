package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * InvTprTipoProveedor entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvTprTipoProveedor extends AbstractInvTprTipoProveedor implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvTprTipoProveedor() {
	}

	/** minimal constructor */
	public InvTprTipoProveedor(Integer tprId, String tprNombre) {
		super(tprId, tprNombre);
	}

	/** full constructor */
	public InvTprTipoProveedor(Integer tprId, String tprNombre,
			Set invProProveedors) {
		super(tprId, tprNombre, invProProveedors);
	}

}
