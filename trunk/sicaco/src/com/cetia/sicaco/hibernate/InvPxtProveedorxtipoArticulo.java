package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * InvClaClasificado entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvPxtProveedorxtipoArticulo extends AbstractInvPxtProveedorxtipoArticulo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvPxtProveedorxtipoArticulo() {
	}

	/** full constructor */
	public InvPxtProveedorxtipoArticulo(Integer pxtId,
			InvProProveedor invProProveedor,
			InvTarTipoArticulo invTarTipoArticulo) {
		super(pxtId, invProProveedor, invTarTipoArticulo);
		// TODO Auto-generated constructor stub
	}
	
}
