package com.cetia.sicaco.hibernate;

/**
 * AbstractOrdPcoPagoCompraId entity provides the base persistence definition of
 * the OrdPcoPagoCompraId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrdPcoPagoCompraId implements
		java.io.Serializable {

	// Fields

	private OrdOpaOrdenDePago ordOpaOrdenDePago;
	private OrdOcoOrdenDeCompra ordOcoOrdenDeCompra;

	// Constructors

	/** default constructor */
	public AbstractOrdPcoPagoCompraId() {
	}

	/** full constructor */
	public AbstractOrdPcoPagoCompraId(OrdOpaOrdenDePago ordOpaOrdenDePago,
			OrdOcoOrdenDeCompra ordOcoOrdenDeCompra) {
		this.ordOpaOrdenDePago = ordOpaOrdenDePago;
		this.ordOcoOrdenDeCompra = ordOcoOrdenDeCompra;
	}

	// Property accessors

	public OrdOpaOrdenDePago getOrdOpaOrdenDePago() {
		return this.ordOpaOrdenDePago;
	}

	public void setOrdOpaOrdenDePago(OrdOpaOrdenDePago ordOpaOrdenDePago) {
		this.ordOpaOrdenDePago = ordOpaOrdenDePago;
	}

	public OrdOcoOrdenDeCompra getOrdOcoOrdenDeCompra() {
		return this.ordOcoOrdenDeCompra;
	}

	public void setOrdOcoOrdenDeCompra(OrdOcoOrdenDeCompra ordOcoOrdenDeCompra) {
		this.ordOcoOrdenDeCompra = ordOcoOrdenDeCompra;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractOrdPcoPagoCompraId))
			return false;
		AbstractOrdPcoPagoCompraId castOther = (AbstractOrdPcoPagoCompraId) other;

		return ((this.getOrdOpaOrdenDePago() == castOther
				.getOrdOpaOrdenDePago()) || (this.getOrdOpaOrdenDePago() != null
				&& castOther.getOrdOpaOrdenDePago() != null && this
				.getOrdOpaOrdenDePago()
				.equals(castOther.getOrdOpaOrdenDePago())))
				&& ((this.getOrdOcoOrdenDeCompra() == castOther
						.getOrdOcoOrdenDeCompra()) || (this
						.getOrdOcoOrdenDeCompra() != null
						&& castOther.getOrdOcoOrdenDeCompra() != null && this
						.getOrdOcoOrdenDeCompra().equals(
								castOther.getOrdOcoOrdenDeCompra())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getOrdOpaOrdenDePago() == null ? 0 : this
						.getOrdOpaOrdenDePago().hashCode());
		result = 37
				* result
				+ (getOrdOcoOrdenDeCompra() == null ? 0 : this
						.getOrdOcoOrdenDeCompra().hashCode());
		return result;
	}

}