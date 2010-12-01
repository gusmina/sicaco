package com.cetia.sicaco.hibernate;

/**
 * AbstractOrdPcoPagoCompra entity provides the base persistence definition of
 * the OrdPcoPagoCompra entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrdPcoPagoCompra implements java.io.Serializable {

	// Fields

	private OrdPcoPagoCompraId id;

	// Constructors

	/** default constructor */
	public AbstractOrdPcoPagoCompra() {
	}

	/** full constructor */
	public AbstractOrdPcoPagoCompra(OrdPcoPagoCompraId id) {
		this.id = id;
	}

	// Property accessors

	public OrdPcoPagoCompraId getId() {
		return this.id;
	}

	public void setId(OrdPcoPagoCompraId id) {
		this.id = id;
	}

}