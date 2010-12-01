package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * InvClaClasificado entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvClaClasificado extends AbstractInvClaClasificado implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvClaClasificado() {
	}

	/** full constructor */
	public InvClaClasificado(InvClaClasificadoId id, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(id, audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

}
