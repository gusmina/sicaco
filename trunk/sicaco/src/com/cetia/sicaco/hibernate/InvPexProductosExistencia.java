package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvPexProductosExistencia entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvPexProductosExistencia extends
		AbstractInvPexProductosExistencia implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvPexProductosExistencia() {
	}

	/** minimal constructor */
	public InvPexProductosExistencia(String artCodigo,
			InvArtArticulo invArtArticulo, Integer pexCantidadProducto,
			Double pexSaldo, Double pexCostoProducto, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(artCodigo, invArtArticulo, pexCantidadProducto, pexSaldo,
				pexCostoProducto, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
	}

	/** full constructor */
	public InvPexProductosExistencia(String artCodigo,
			InvArtArticulo invArtArticulo, Integer pexCantidadProducto,
			Double pexSaldo, Double pexCostoProducto, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set invEboExistenciaBodegas) {
		super(artCodigo, invArtArticulo, pexCantidadProducto, pexSaldo,
				pexCostoProducto, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion,
				invEboExistenciaBodegas);
	}

}
