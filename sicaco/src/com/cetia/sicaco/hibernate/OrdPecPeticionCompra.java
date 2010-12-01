package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * OrdPecPeticionCompra entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class OrdPecPeticionCompra extends AbstractOrdPecPeticionCompra
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public OrdPecPeticionCompra() {
	}

	/** full constructor */
	public OrdPecPeticionCompra(Integer pecId, InvProProveedor invProProveedor,
			SecSucSucursal secSucSucursal, CtaAscAsociado ctaAscAsociado, Double pecMonto,
			Date pecFecha) {
		super(pecId, invProProveedor, secSucSucursal, ctaAscAsociado, pecMonto,
				pecFecha);
	}

}
