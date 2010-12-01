package com.cetia.sicaco.hibernate;

/**
 * FacDfaDetalleFacturaId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class FacDfaDetalleFacturaId extends AbstractFacDfaDetalleFacturaId
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public FacDfaDetalleFacturaId() {
	}

	/** full constructor */
	public FacDfaDetalleFacturaId(Integer dfaId, InvArtArticulo invArtArticulo) {
		super(dfaId, invArtArticulo);
	}

}
