package com.cetia.sicaco.hibernate;

/**
 * IucTutTarTpr entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class IucTutTarTpr extends AbstractIucTutTarTpr implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public IucTutTarTpr() {
	}

	/** full constructor */
	public IucTutTarTpr(CtaTprTipoPrestamo ctaTprTipoPrestamo,
			InvTarTipoArticulo invTarTipoArticulo) {
		super(ctaTprTipoPrestamo, invTarTipoArticulo);
	}

}
