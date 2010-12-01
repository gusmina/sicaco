package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * OrdRefCuentaReferencia entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class OrdRefCuentaReferencia extends AbstractOrdRefCuentaReferencia
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public OrdRefCuentaReferencia() {
	}

	/** minimal constructor */
	public OrdRefCuentaReferencia(Integer refId, String refCuenta,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(refId, refCuenta, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
	}

	/** full constructor */
	public OrdRefCuentaReferencia(Integer refId,
			InvProProveedor invProProveedor, String refCuenta,
			String refDescripcion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String refEstado,
			Set ordOcoOrdenDeCompras) {
		super(refId, invProProveedor, refCuenta, refDescripcion, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion, audUsuarioModificacion,
				refEstado, ordOcoOrdenDeCompras);
		// TODO Auto-generated constructor stub
	}


}
