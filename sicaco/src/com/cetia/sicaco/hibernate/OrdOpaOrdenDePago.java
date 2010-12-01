package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * OrdOpaOrdenDePago entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class OrdOpaOrdenDePago extends AbstractOrdOpaOrdenDePago implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OrdOpaOrdenDePago() {
	}

	public OrdOpaOrdenDePago(Integer opaId, Integer opaCodigo,
			Date opaFechaPago, Double opaTotal, Double opaDescuento,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			String opaEstado) {
		super(opaId, opaCodigo, opaFechaPago, opaTotal, opaDescuento, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion, audUsuarioModificacion,
				opaEstado);
		// TODO Auto-generated constructor stub
	}

	public OrdOpaOrdenDePago(Integer opaId, InvProProveedor invProProveedor,
			Integer opaCodigo, Date opaFechaPago, Double opaTotal,
			Double opaDescuento, String opaTipoPago, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String opaEstado, String opaNota,
			Set ordPcoPagoCompras) {
		super(opaId, invProProveedor, opaCodigo, opaFechaPago, opaTotal, opaDescuento,
				opaTipoPago, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion, opaEstado,
				opaNota, ordPcoPagoCompras);
		// TODO Auto-generated constructor stub
	}

}
