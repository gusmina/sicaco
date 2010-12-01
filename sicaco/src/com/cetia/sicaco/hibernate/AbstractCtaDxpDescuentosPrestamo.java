package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaDxpDescuentosPrestamo entity provides the base persistence
 * definition of the CtaDxpDescuentosPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaDxpDescuentosPrestamo implements
		java.io.Serializable {

	// Fields

	private Integer dxpId;
	private CtaPrePrestamo ctaPrePrestamoByPreId;//IDENTIFICADOR DEL PRESTAMO AL QUE SE APLICA EL DESCUENTO
	private CtaSegSeguros ctaSegSeguros;
	private CtaCahCuentaAhorro ctaCahCuentaAhorro;
	private CtaPrePrestamo ctaPrePrestamoByPreId2;//NO ES NULL SI ESTE DESCUENTO SE ABONA A OTRO PRESTAMO.
	private Long casCuenta;
	private Double dxpMonto;
	private Double dxpInteresPagado;

	// Constructors

	/** default constructor */
	public AbstractCtaDxpDescuentosPrestamo() {
	}

	/** minimal constructor */
	public AbstractCtaDxpDescuentosPrestamo(Integer dxpId,
			CtaPrePrestamo ctaPrePrestamoByPreId, Long casCuenta,
			Double dxpMonto, Double dxpInteresPagado) {
		this.dxpId = dxpId;
		this.ctaPrePrestamoByPreId = ctaPrePrestamoByPreId;
		this.casCuenta = casCuenta;
		this.dxpMonto = dxpMonto;
		this.dxpInteresPagado = dxpInteresPagado;
	}

	/** full constructor */
	public AbstractCtaDxpDescuentosPrestamo(Integer dxpId,
			CtaPrePrestamo ctaPrePrestamoByPreId, CtaSegSeguros ctaSegSeguros,
			CtaCahCuentaAhorro ctaCahCuentaAhorro,
			CtaPrePrestamo ctaPrePrestamoByPreId2, Long casCuenta,
			Double dxpMonto, Double dxpInteresPagado) {
		this.dxpId = dxpId;
		this.ctaPrePrestamoByPreId = ctaPrePrestamoByPreId;
		this.ctaSegSeguros = ctaSegSeguros;
		this.ctaCahCuentaAhorro = ctaCahCuentaAhorro;
		this.ctaPrePrestamoByPreId2 = ctaPrePrestamoByPreId2;
		this.casCuenta = casCuenta;
		this.dxpMonto = dxpMonto;
		this.dxpInteresPagado = dxpInteresPagado;
	}

	// Property accessors

	public Integer getDxpId() {
		return this.dxpId;
	}

	public void setDxpId(Integer dxpId) {
		this.dxpId = dxpId;
	}

	public CtaPrePrestamo getCtaPrePrestamoByPreId() {
		return this.ctaPrePrestamoByPreId;
	}

	public void setCtaPrePrestamoByPreId(CtaPrePrestamo ctaPrePrestamoByPreId) {
		this.ctaPrePrestamoByPreId = ctaPrePrestamoByPreId;
	}

	public CtaSegSeguros getCtaSegSeguros() {
		return this.ctaSegSeguros;
	}

	public void setCtaSegSeguros(CtaSegSeguros ctaSegSeguros) {
		this.ctaSegSeguros = ctaSegSeguros;
	}

	public CtaCahCuentaAhorro getCtaCahCuentaAhorro() {
		return this.ctaCahCuentaAhorro;
	}

	public void setCtaCahCuentaAhorro(CtaCahCuentaAhorro ctaCahCuentaAhorro) {
		this.ctaCahCuentaAhorro = ctaCahCuentaAhorro;
	}

	public CtaPrePrestamo getCtaPrePrestamoByPreId2() {
		return this.ctaPrePrestamoByPreId2;
	}

	public void setCtaPrePrestamoByPreId2(CtaPrePrestamo ctaPrePrestamoByPreId2) {
		this.ctaPrePrestamoByPreId2 = ctaPrePrestamoByPreId2;
	}

	public Long getCasCuenta() {
		return this.casCuenta;
	}

	public void setCasCuenta(Long casCuenta) {
		this.casCuenta = casCuenta;
	}

	public Double getDxpMonto() {
		return this.dxpMonto;
	}

	public void setDxpMonto(Double dxpMonto) {
		this.dxpMonto = dxpMonto;
	}

	public Double getDxpInteresPagado() {
		return dxpInteresPagado;
	}

	public void setDxpInteresPagado(Double dxpInteresPagado) {
		this.dxpInteresPagado = dxpInteresPagado;
	}

}