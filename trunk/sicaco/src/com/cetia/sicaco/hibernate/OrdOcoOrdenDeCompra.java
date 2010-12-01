package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * OrdOcoOrdenDeCompra entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class OrdOcoOrdenDeCompra extends AbstractOrdOcoOrdenDeCompra implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OrdOcoOrdenDeCompra() {
	}

	public OrdOcoOrdenDeCompra(Integer ocoId, Integer ocoCodigo,
			Date ocoEmision, Double ocoMonto, String ocoElaborado,
			String ocoEstado, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(ocoId, ocoCodigo, ocoEmision, ocoMonto, ocoElaborado, ocoEstado,
				audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
		// TODO Auto-generated constructor stub
	}

	public OrdOcoOrdenDeCompra(Integer ocoId,
			OrdRefCuentaReferencia ordRefCuentaReferencia,
			String ascCodigo, InvProProveedor invProProveedor,
			Integer ocoDonacion, Integer ocoCodigo, Date ocoEmision,
			Date ocoVencimiento, Double ocoMonto, Double ocoPagado,
			Double ocoSaldo, String ocoElaborado, String ocoEstado,
			String cliCodigo,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set ordPcoPagoCompras) {
		super(ocoId, ordRefCuentaReferencia, ascCodigo, invProProveedor,
				ocoDonacion, ocoCodigo, ocoEmision, ocoVencimiento, ocoMonto,
				ocoPagado, ocoSaldo, ocoElaborado, ocoEstado, cliCodigo, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion, audUsuarioModificacion,
				ordPcoPagoCompras);
		// TODO Auto-generated constructor stub
	}



}
