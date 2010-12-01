package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvArtArticulo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvArtArticulo extends AbstractInvArtArticulo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvArtArticulo() {
	}

	/** minimal constructor */
	public InvArtArticulo(String artCodigo, String artNombre,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(artCodigo, artNombre, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
	}

	/** full constructor */
	public InvArtArticulo(String artCodigo, InvMedMedida invMedMedida,
			InvTarTipoArticulo invTarTipoArticulo, InvLinLinea invLinLinea,
			String artNombre, Double artPorcentajeUtilidad,
			Double artPrecioSugerido, Double artPrecioMinimo,
			Byte artExcentoIva, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set invPexProductosExistencias,
			Set invMovMovimientoses, Set invCprCapacidadProductos,
			Set facDfaDetalleFacturas) {
		super(artCodigo, invMedMedida, invTarTipoArticulo, invLinLinea, artNombre,
				artPorcentajeUtilidad, artPrecioSugerido, artPrecioMinimo,
				artExcentoIva, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion,
				invPexProductosExistencias, invMovMovimientoses,
				invCprCapacidadProductos, facDfaDetalleFacturas);
		// TODO Auto-generated constructor stub
	}

}
