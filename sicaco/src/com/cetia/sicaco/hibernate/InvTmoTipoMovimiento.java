package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * InvTmoTipoMovimiento entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvTmoTipoMovimiento extends AbstractInvTmoTipoMovimiento
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvTmoTipoMovimiento() {
	}

	/** minimal constructor */
	public InvTmoTipoMovimiento(Integer tmoId, String nombre) {
		super(tmoId, nombre);
	}

	/** full constructor */
	public InvTmoTipoMovimiento(Integer tmoId, String nombre,
			Set invMovMovimientoses) {
		super(tmoId, nombre, invMovMovimientoses);
	}

}
