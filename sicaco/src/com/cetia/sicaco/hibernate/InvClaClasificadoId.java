package com.cetia.sicaco.hibernate;

/**
 * InvClaClasificadoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvClaClasificadoId extends AbstractInvClaClasificadoId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvClaClasificadoId() {
	}

	/** full constructor */
	public InvClaClasificadoId(InvProProveedor invProProveedor,
			InvTclTipoClasificacion invTclTipoClasificacion) {
		super(invProProveedor, invTclTipoClasificacion);
	}

}
