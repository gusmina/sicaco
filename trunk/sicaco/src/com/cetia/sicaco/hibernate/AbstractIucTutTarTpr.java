package com.cetia.sicaco.hibernate;

/**
 * AbstractIucTutTarTpr entity provides the base persistence definition of the
 * IucTutTarTpr entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractIucTutTarTpr implements java.io.Serializable {

	// Fields

	private Integer tutId;
	private CtaTprTipoPrestamo ctaTprTipoPrestamo = new CtaTprTipoPrestamo();
	private InvTarTipoArticulo invTarTipoArticulo = new InvTarTipoArticulo() ;

	// Constructors

	/** default constructor */
	public AbstractIucTutTarTpr() {
	}

	/** full constructor */
	public AbstractIucTutTarTpr(CtaTprTipoPrestamo ctaTprTipoPrestamo,
			InvTarTipoArticulo invTarTipoArticulo) {
		this.ctaTprTipoPrestamo = ctaTprTipoPrestamo;
		this.invTarTipoArticulo = invTarTipoArticulo;
	}

	// Property accessors

	public Integer getTutId() {
		return this.tutId;
	}

	public void setTutId(Integer tutId) {
		this.tutId = tutId;
	}

	public CtaTprTipoPrestamo getCtaTprTipoPrestamo() {
		return this.ctaTprTipoPrestamo;
	}

	public void setCtaTprTipoPrestamo(CtaTprTipoPrestamo ctaTprTipoPrestamo) {
		this.ctaTprTipoPrestamo = ctaTprTipoPrestamo;
	}

	public InvTarTipoArticulo getInvTarTipoArticulo() {
		return this.invTarTipoArticulo;
	}

	public void setInvTarTipoArticulo(InvTarTipoArticulo invTarTipoArticulo) {
		this.invTarTipoArticulo = invTarTipoArticulo;
	}

}