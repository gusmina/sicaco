package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * FacDfaDetalleFactura entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class FacDfaDetalleFactura extends AbstractFacDfaDetalleFactura
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public FacDfaDetalleFactura() {
	}

	/** minimal constructor */
	public FacDfaDetalleFactura(FacDfaDetalleFacturaId id,
			FacFenFacturaEncabezado facFenFacturaEncabezado,
			Integer dfaCantidad, Double dfaPrecioUnitario, Byte dfaExento,
			Double dfaPrecioTotal, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(id, facFenFacturaEncabezado, dfaCantidad, dfaPrecioUnitario,
				dfaExento, dfaPrecioTotal, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

	public FacDfaDetalleFactura(FacDfaDetalleFacturaId id,
			FacFenFacturaEncabezado facFenFacturaEncabezado,
			Integer dfaCantidad, String dfaDescripcion,
			Double dfaPrecioUnitario, Byte dfaExento, Double dfaPrecioTotal,
			int cnvId, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set invMovMovimientoses) {
		super(id, facFenFacturaEncabezado, dfaCantidad, dfaDescripcion,
				dfaPrecioUnitario, dfaExento, dfaPrecioTotal, cnvId, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion, audUsuarioModificacion,
				invMovMovimientoses);
		// TODO Auto-generated constructor stub
	}

	

}
