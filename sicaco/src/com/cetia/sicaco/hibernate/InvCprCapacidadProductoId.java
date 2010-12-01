package com.cetia.sicaco.hibernate;

/**
 * InvCprCapacidadProductoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvCprCapacidadProductoId extends
		AbstractInvCprCapacidadProductoId implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvCprCapacidadProductoId() {
	}

	/** full constructor */
	public InvCprCapacidadProductoId(InvBodBodegas invBodBodegas,
			InvArtArticulo invArtArticulo) {
		super(invBodBodegas, invArtArticulo);
	}

}
