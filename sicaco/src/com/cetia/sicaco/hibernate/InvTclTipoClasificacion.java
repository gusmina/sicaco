package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvTclTipoClasificacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvTclTipoClasificacion extends AbstractInvTclTipoClasificacion
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvTclTipoClasificacion() {
	}

	/** minimal constructor */
	public InvTclTipoClasificacion(String tclClasificacion,
			String tclDescripcion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(tclClasificacion, tclDescripcion, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

	/** full constructor */
	public InvTclTipoClasificacion(String tclClasificacion,
			String tclDescripcion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set invClaClasificados) {
		super(tclClasificacion, tclDescripcion, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion, invClaClasificados);
	}

}
