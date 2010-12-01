package com.cetia.sicaco.hibernate;

/**
 * InvEboExistenciaBodegaId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvEboExistenciaBodegaId extends AbstractInvEboExistenciaBodegaId
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvEboExistenciaBodegaId() {
	}

	/** full constructor */
	public InvEboExistenciaBodegaId(InvBodBodegas invBodBodegas,
			InvPexProductosExistencia invPexProductosExistencia) {
		super(invBodBodegas, invPexProductosExistencia);
	}

}
