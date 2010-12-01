package com.cetia.sicaco.hibernate;

/**
 * InvNivNivelId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvNivNivelId extends AbstractInvNivNivelId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvNivNivelId() {
	}

	/** full constructor */
	public InvNivNivelId(Integer nivFilaId, InvStnEstante invStnEstante,
			Integer nivColId) {
		super(nivFilaId, invStnEstante, nivColId);
	}

}
