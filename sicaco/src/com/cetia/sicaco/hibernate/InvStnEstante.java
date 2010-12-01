package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvStnEstante entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvStnEstante extends AbstractInvStnEstante implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvStnEstante() {
	}

	/** minimal constructor */
	public InvStnEstante(Integer stnId, InvBodBodegas invBodBodegas,
			Integer stnCantFilas, Integer stnCantColumnas, String stnEstado,
			String stnPosicion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String stnCodigo, Set invNivNivels) {
		super(stnId, invBodBodegas, stnCantFilas, stnCantColumnas, stnEstado,
				stnPosicion, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion, stnCodigo, invNivNivels);
		// TODO Auto-generated constructor stub
	}

	/** full constructor */
	public InvStnEstante(Integer stnId, InvBodBodegas invBodBodegas,
			Integer stnCantFilas, Integer stnCantColumnas, String stnEstado,
			String stnPosicion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String stnCodigo) {
		super(stnId, invBodBodegas, stnCantFilas, stnCantColumnas, stnEstado,
				stnPosicion, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion, stnCodigo);
		// TODO Auto-generated constructor stub
	}


}
