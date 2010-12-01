package com.cetia.sicaco.hibernate;

/**
 * OrdPcoPagoCompraId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class OrdPcoPagoCompraId extends AbstractOrdPcoPagoCompraId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OrdPcoPagoCompraId() {
	}

	/** full constructor */
	public OrdPcoPagoCompraId(OrdOpaOrdenDePago ordOpaOrdenDePago,
			OrdOcoOrdenDeCompra ordOcoOrdenDeCompra) {
		super(ordOpaOrdenDePago, ordOcoOrdenDeCompra);
	}

}
