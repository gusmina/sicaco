package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * InvCprCapacidadProducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvCprCapacidadProducto extends AbstractInvCprCapacidadProducto
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvCprCapacidadProducto() {
	}

	/** full constructor */
	public InvCprCapacidadProducto(InvCprCapacidadProductoId id,
			Integer cprCantidadMinima, Integer cprCantidadMaxima,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(id, cprCantidadMinima, cprCantidadMaxima, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

}
