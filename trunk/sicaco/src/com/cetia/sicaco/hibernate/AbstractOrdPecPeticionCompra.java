package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractOrdPecPeticionCompra entity provides the base persistence definition
 * of the OrdPecPeticionCompra entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrdPecPeticionCompra implements
		java.io.Serializable {

	// Fields

	private Integer pecId;
	private InvProProveedor invProProveedor;
	private SecSucSucursal secSucSucursal;
	private CtaAscAsociado ctaAscAsociado;
	private Double pecMonto;
	private Date pecFecha;

	// Constructors

	/** default constructor */
	public AbstractOrdPecPeticionCompra() {
	}

	/** full constructor */
	public AbstractOrdPecPeticionCompra(Integer pecId,
			InvProProveedor invProProveedor, SecSucSucursal secSucSucursal,
			CtaAscAsociado ctaAscAsociado, Double pecMonto, Date pecFecha) {
		this.pecId = pecId;
		this.invProProveedor = invProProveedor;
		this.secSucSucursal = secSucSucursal;
		this.ctaAscAsociado = ctaAscAsociado;
		this.pecMonto = pecMonto;
		this.pecFecha = pecFecha;
	}

	// Property accessors

	public Integer getPecId() {
		return this.pecId;
	}

	public void setPecId(Integer pecId) {
		this.pecId = pecId;
	}

	public InvProProveedor getInvProProveedor() {
		return this.invProProveedor;
	}

	public void setInvProProveedor(InvProProveedor invProProveedor) {
		this.invProProveedor = invProProveedor;
	}

	public SecSucSucursal getSecSucSucursal() {
		return this.secSucSucursal;
	}

	public void setSecSucSucursal(SecSucSucursal secSucSucursal) {
		this.secSucSucursal = secSucSucursal;
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return this.ctaAscAsociado;
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		this.ctaAscAsociado = ctaAscAsociado;
	}

	public Double getPecMonto() {
		return this.pecMonto;
	}

	public void setPecMonto(Double pecMonto) {
		this.pecMonto = pecMonto;
	}

	public Date getPecFecha() {
		return this.pecFecha;
	}

	public void setPecFecha(Date pecFecha) {
		this.pecFecha = pecFecha;
	}

}