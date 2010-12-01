package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * InvEboExistenciaBodega entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvEboExistenciaBodega extends AbstractInvEboExistenciaBodega
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvEboExistenciaBodega() {
	}

	/** full constructor */
	public InvEboExistenciaBodega(InvEboExistenciaBodegaId id,
			Integer eboCantidadProducto, Double eboSaldo,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(id, eboCantidadProducto, eboSaldo, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

}
