package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaDexDescuentosExternos entity provides the base persistence
 * definition of the CtaDexDescuentosExternos entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaDexDescuentosExternos implements
		java.io.Serializable {

	// Fields

	private Integer dexId;
	private CtaPrePrestamo ctaPrePrestamo = new CtaPrePrestamo();
	private CtaLprLineaPrestamo ctaLprLineaPrestamo = new CtaLprLineaPrestamo();;
	private double dexMonto;
	private String dexNombreDescuento;

	// Constructors

	/** default constructor */
	public AbstractCtaDexDescuentosExternos() {
	}

	/** full constructor */
	public AbstractCtaDexDescuentosExternos(Integer dexId,CtaPrePrestamo ctaPrePrestamo,
			CtaLprLineaPrestamo ctaLprLineaPrestamo, double dexMonto,
			String dexNombreDescuento) {
		this.dexId = dexId;
		this.ctaPrePrestamo = ctaPrePrestamo;
		this.ctaLprLineaPrestamo = ctaLprLineaPrestamo;
		this.dexMonto = dexMonto;
		this.dexNombreDescuento = dexNombreDescuento;
	}

	// Property accessors

	public Integer getDexId() {
		return this.dexId;
	}

	public void setDexId(Integer dexId) {
		this.dexId = dexId;
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return this.ctaPrePrestamo;
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		this.ctaPrePrestamo = ctaPrePrestamo;
	}

	public CtaLprLineaPrestamo getCtaLprLineaPrestamo() {
		return this.ctaLprLineaPrestamo;
	}

	public void setCtaLprLineaPrestamo(CtaLprLineaPrestamo ctaLprLineaPrestamo) {
		this.ctaLprLineaPrestamo = ctaLprLineaPrestamo;
	}

	public double getDexMonto() {
		return this.dexMonto;
	}

	public void setDexMonto(double dexMonto) {
		this.dexMonto = dexMonto;
	}

	public String getDexNombreDescuento() {
		return this.dexNombreDescuento;
	}

	public void setDexNombreDescuento(String dexNombreDescuento) {
		this.dexNombreDescuento = dexNombreDescuento;
	}

}