package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * InvNxmNivelMovimiento entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvNxmNivelMovimiento extends AbstractInvNxmNivelMovimiento
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvNxmNivelMovimiento() {
	}

	/** minimal constructor */
	public InvNxmNivelMovimiento(Integer nxmId, Integer nxmUnidades,
			Double nxmValor, Date nxmFecha, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(nxmId, nxmUnidades, nxmValor, nxmFecha, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

	/** full constructor */
	public InvNxmNivelMovimiento(Integer nxmId,
			InvMovMovimientos invMovMovimientos, InvNivNivel invNivNivel,
			Integer nxmUnidades, Double nxmValor, Date nxmFecha,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(nxmId, invMovMovimientos, invNivNivel, nxmUnidades, nxmValor,
				nxmFecha, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
	}

}
