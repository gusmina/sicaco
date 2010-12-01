package com.cetia.sicaco.hibernate;

/**
 * AbstractIucPutProveedorTipoPrestamo entity provides the base persistence
 * definition of the IucPutProveedorTipoPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractIucPutProveedorTipoPrestamo implements
		java.io.Serializable {

	// Fields

	private Integer putId;
	private InvProProveedor invProProveedor = new InvProProveedor();
	private CtaTprTipoPrestamo ctaTprTipoPrestamo = new CtaTprTipoPrestamo();

	// Constructors

	/** default constructor */
	public AbstractIucPutProveedorTipoPrestamo() {
	}

	/** full constructor */
	public AbstractIucPutProveedorTipoPrestamo(InvProProveedor invProProveedor,
			CtaTprTipoPrestamo ctaTprTipoPrestamo) {
		this.invProProveedor = invProProveedor;
		this.ctaTprTipoPrestamo = ctaTprTipoPrestamo;
	}

	// Property accessors

	public Integer getPutId() {
		return this.putId;
	}

	public void setPutId(Integer putId) {
		this.putId = putId;
	}

	public InvProProveedor getInvProProveedor() {
		return this.invProProveedor;
	}

	public void setInvProProveedor(InvProProveedor invProProveedor) {
		this.invProProveedor = invProProveedor;
	}

	public CtaTprTipoPrestamo getCtaTprTipoPrestamo() {
		return this.ctaTprTipoPrestamo;
	}

	public void setCtaTprTipoPrestamo(CtaTprTipoPrestamo ctaTprTipoPrestamo) {
		this.ctaTprTipoPrestamo = ctaTprTipoPrestamo;
	}

}