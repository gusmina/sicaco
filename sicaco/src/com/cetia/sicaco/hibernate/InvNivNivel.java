package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvNivNivel entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvNivNivel extends AbstractInvNivNivel implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvNivNivel() {
	}

	/** minimal constructor */
	public InvNivNivel(InvNivNivelId id, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(id, audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

	/** full constructor */
	public InvNivNivel(InvNivNivelId id, String nivEstado,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set invNxmNivelMovimientos) {
		super(id, nivEstado, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion,
				invNxmNivelMovimientos);
	}

}
