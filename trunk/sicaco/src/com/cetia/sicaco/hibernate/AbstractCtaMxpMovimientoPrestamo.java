package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCtaMxpMovimientoPrestamo entity provides the base persistence
 * definition of the CtaMxpMovimientoPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaMxpMovimientoPrestamo implements
		java.io.Serializable {

	// Fields

	private Integer mxpId;
	private CtaPrePrestamo ctaPrePrestamo = new CtaPrePrestamo();
	private CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado = new CtaTxaTransaccionxcuentaAsociado();
	private Date mxpFecha;
	private Double mxpSaldo;
	private Double mxpSaldoActual;
	private Double mxpInteresPendiente;
	private Double mxpMora;
	private Double mxpInteresAcumulado;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractCtaMxpMovimientoPrestamo() {
	}



	// Property accessors

	public AbstractCtaMxpMovimientoPrestamo(Integer mxpId,
			CtaPrePrestamo ctaPrePrestamo,
			CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado,
			Date mxpFecha, Double mxpSaldo, Double mxpSaldoActual,
			Double mxpInteresPendiente, Double mxpMora,
			Double mxpInteresAcumulado, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super();
		this.mxpId = mxpId;
		this.ctaPrePrestamo = ctaPrePrestamo;
		this.ctaTxaTransaccionxcuentaAsociado = ctaTxaTransaccionxcuentaAsociado;
		this.mxpFecha = mxpFecha;
		this.mxpSaldo = mxpSaldo;
		this.mxpSaldoActual = mxpSaldoActual;
		this.mxpInteresPendiente = mxpInteresPendiente;
		this.mxpMora = mxpMora;
		this.mxpInteresAcumulado = mxpInteresAcumulado;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}



	public Integer getMxpId() {
		return this.mxpId;
	}

	public void setMxpId(Integer mxpId) {
		this.mxpId = mxpId;
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return this.ctaPrePrestamo;
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		this.ctaPrePrestamo = ctaPrePrestamo;
	}

	public CtaTxaTransaccionxcuentaAsociado getCtaTxaTransaccionxcuentaAsociado() {
		return this.ctaTxaTransaccionxcuentaAsociado;
	}

	public void setCtaTxaTransaccionxcuentaAsociado(
			CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado) {
		this.ctaTxaTransaccionxcuentaAsociado = ctaTxaTransaccionxcuentaAsociado;
	}

	public Date getMxpFecha() {
		return this.mxpFecha;
	}

	public void setMxpFecha(Date mxpFecha) {
		this.mxpFecha = mxpFecha;
	}

	public Double getMxpSaldoActual() {
		return this.mxpSaldoActual;
	}

	public void setMxpSaldoActual(Double mxpSaldoActual) {
		this.mxpSaldoActual = mxpSaldoActual;
	}

	public Double getMxpInteresPendiente() {
		return this.mxpInteresPendiente;
	}

	public void setMxpInteresPendiente(Double mxpInteresPendiente) {
		this.mxpInteresPendiente = mxpInteresPendiente;
	}

	public Double getMxpMora() {
		return this.mxpMora;
	}

	public void setMxpMora(Double mxpMora) {
		this.mxpMora = mxpMora;
	}

	public Double getMxpInteresAcumulado() {
		return mxpInteresAcumulado;
	}

	public void setMxpInteresAcumulado(Double mxpInteresAcumulado) {
		this.mxpInteresAcumulado = mxpInteresAcumulado;
	}

	public Date getAudFechaCreacion() {
		return audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
	}

	public String getAudUsuarioCreacion() {
		return audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaModificacion() {
		return audFechaModificacion;
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		this.audFechaModificacion = audFechaModificacion;
	}

	public String getAudUsuarioModificacion() {
		return audUsuarioModificacion;
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	public Double getMxpSaldo() {
		return mxpSaldo;
	}

	public void setMxpSaldo(Double mxpSaldo) {
		this.mxpSaldo = mxpSaldo;
	}

}