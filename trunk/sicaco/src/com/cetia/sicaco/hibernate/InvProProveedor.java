package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvProProveedor entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvProProveedor extends AbstractInvProProveedor implements
		java.io.Serializable {

	public InvProProveedor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvProProveedor(Integer proId,
			InvTprTipoProveedor invTprTipoProveedor, CtrPaiPais ctrPaiPais,
			String proNombre, String proDireccion, String proNumeroTelefono,
			String proHorarioOficina, String proNit, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String proCodigo, String proRegistro) {
		super(proId, invTprTipoProveedor, ctrPaiPais, proNombre, proDireccion,
				proNumeroTelefono, proHorarioOficina, proNit, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion, audUsuarioModificacion,
				proCodigo, proRegistro);
		// TODO Auto-generated constructor stub
	}

	public InvProProveedor(Integer proId,
			InvTprTipoProveedor invTprTipoProveedor, CtrPaiPais ctrPaiPais,
			String proNombre, String proDireccion, String proNumeroTelefono,
			String proHorarioOficina, String proNit, Double proLimiteCredito,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			String proCodigo, String proRegistro, String proEstado,
			String proGiro, Integer proDiasPago, Set ordOpaOrdenDePagos,
			Set ordOcoOrdenDeCompras, Set ordRefCuentaReferencias,
			Set invClaClasificados, Set invCprContactoProveedors,
			Set invPcbProveedorCuentaBancarias, Set facFenFacturaEncabezados,
			Set iucPutProveedorTipoPrestamos) {
		super(proId, invTprTipoProveedor, ctrPaiPais, proNombre, proDireccion,
				proNumeroTelefono, proHorarioOficina, proNit, proLimiteCredito,
				audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion, proCodigo, proRegistro, proEstado, proGiro,
				proDiasPago, ordOpaOrdenDePagos, ordOcoOrdenDeCompras,
				ordRefCuentaReferencias, invClaClasificados, invCprContactoProveedors,
				invPcbProveedorCuentaBancarias, facFenFacturaEncabezados,
				iucPutProveedorTipoPrestamos);
		// TODO Auto-generated constructor stub
	}


	// Constructors
}
