package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaRcoReferenciasComerciales entity provides the base persistence
 * definition of the CtaRcoReferenciasComerciales entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaRcoReferenciasComerciales implements
		java.io.Serializable {

	// Fields

	private Integer rcoId;
	private CtaPrePrestamo ctaPrePrestamo = new CtaPrePrestamo();
	private String rcoReferencia;
	private String rcoSucursal;
	private Float rcoMonto;
	private String rcoEstado;
	private String rcoConcepto;

	// Constructors

	/** default constructor */
	public AbstractCtaRcoReferenciasComerciales() {
	}

	/** full constructor */
	public AbstractCtaRcoReferenciasComerciales(Integer rcoId,
			CtaPrePrestamo ctaPrePrestamo, String rcoReferencia,
			String rcoSucursal, Float rcoMonto, String rcoEstado,String rcoConcepto) {
		this.rcoId = rcoId;
		this.ctaPrePrestamo = ctaPrePrestamo;
		this.rcoReferencia = rcoReferencia;
		this.rcoSucursal = rcoSucursal;
		this.rcoMonto = rcoMonto;
		this.rcoEstado = rcoEstado;
		this.rcoConcepto=rcoConcepto;
	}

	// Property accessors

	public Integer getRcoId() {
		return this.rcoId;
	}

	public void setRcoId(Integer rcoId) {
		this.rcoId = rcoId;
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return this.ctaPrePrestamo;
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		this.ctaPrePrestamo = ctaPrePrestamo;
	}

	public String getRcoReferencia() {
		return this.rcoReferencia;
	}

	public void setRcoReferencia(String rcoReferencia) {
		this.rcoReferencia = rcoReferencia;
	}

	public String getRcoSucursal() {
		return this.rcoSucursal;
	}

	public void setRcoSucursal(String rcoSucursal) {
		this.rcoSucursal = rcoSucursal;
	}

	public Float getRcoMonto() {
		return this.rcoMonto;
	}

	public void setRcoMonto(Float rcoMonto) {
		this.rcoMonto = rcoMonto;
	}

	public String getRcoEstado() {
		return this.rcoEstado;
	}

	public void setRcoEstado(String rcoEstado) {
		this.rcoEstado = rcoEstado;
	}

	public String getRcoConcepto() {
		return rcoConcepto;
	}

	public void setRcoConcepto(String rcoConcepto) {
		this.rcoConcepto = rcoConcepto;
	}
	
	
}