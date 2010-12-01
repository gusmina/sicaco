package com.cetia.sicaco.hibernate;

/**
 * CtaDexDescuentosExternos entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaDexDescuentosExternos extends AbstractCtaDexDescuentosExternos
		implements java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = -4378581031201805958L;

	/** default constructor */
	public CtaDexDescuentosExternos() {
	}

	/** full constructor */
	public CtaDexDescuentosExternos(Integer dexId,
			CtaPrePrestamo ctaPrePrestamo,
			CtaLprLineaPrestamo ctaLprLineaPrestamo, Double dexMonto,
			String dexNombreDescuento) {
		super(dexId, ctaPrePrestamo, ctaLprLineaPrestamo, dexMonto,
				dexNombreDescuento);
	}

}
