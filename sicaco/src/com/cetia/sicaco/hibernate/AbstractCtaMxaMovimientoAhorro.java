package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCtaMxaMovimientoAhorro entity provides the base persistence
 * definition of the CtaMxaMovimientoAhorro entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaMxaMovimientoAhorro implements
		java.io.Serializable {

	// Fields

	private Integer mxaId;
	private CtaCahCuentaAhorro ctaCahCuentaAhorro = new CtaCahCuentaAhorro();
	private CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado= new CtaTxaTransaccionxcuentaAsociado();
	private Date mxaFecha;
	private Double mxaMonto;
	private Double mxaInteresTran;
	private Double mxaSaldo;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractCtaMxaMovimientoAhorro() {
	}

	/** minimal constructor */
	public AbstractCtaMxaMovimientoAhorro(Integer mxaId, Date mxaFecha,
			Double mxaMonto) {
		this.mxaId = mxaId;
		this.mxaFecha = mxaFecha;
		this.mxaMonto = mxaMonto;
	}

	/** full constructor */
	public AbstractCtaMxaMovimientoAhorro(Integer mxaId,
			CtaCahCuentaAhorro ctaCahCuentaAhorro,
			CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado,
			Date mxaFecha, Double mxaMonto,Double mxaInteresTran,
			Double mxaSaldo,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.mxaId = mxaId;
		this.ctaCahCuentaAhorro = ctaCahCuentaAhorro;
		this.ctaTxaTransaccionxcuentaAsociado = ctaTxaTransaccionxcuentaAsociado;
		this.mxaFecha = mxaFecha;
		this.mxaMonto = mxaMonto;
		this.mxaInteresTran = mxaInteresTran;
		this.mxaSaldo = mxaSaldo;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	// Property accessors

	public Integer getMxaId() {
		return this.mxaId;
	}

	public void setMxaId(Integer mxaId) {
		this.mxaId = mxaId;
	}

	public CtaCahCuentaAhorro getCtaCahCuentaAhorro() {
		return this.ctaCahCuentaAhorro;
	}

	public void setCtaCahCuentaAhorro(CtaCahCuentaAhorro ctaCahCuentaAhorro) {
		this.ctaCahCuentaAhorro = ctaCahCuentaAhorro;
	}

	public CtaTxaTransaccionxcuentaAsociado getCtaTxaTransaccionxcuentaAsociado() {
		return this.ctaTxaTransaccionxcuentaAsociado;
	}

	public void setCtaTxaTransaccionxcuentaAsociado(
			CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado) {
		this.ctaTxaTransaccionxcuentaAsociado = ctaTxaTransaccionxcuentaAsociado;
	}

	public Date getMxaFecha() {
		return this.mxaFecha;
	}

	public void setMxaFecha(Date mxaFecha) {
		this.mxaFecha = mxaFecha;
	}

	public Double getMxaMonto() {
		return mxaMonto;
	}

	public void setMxaMonto(Double mxaMonto) {
		this.mxaMonto = mxaMonto;
	}

	public Double getMxaInteresTran() {
		return mxaInteresTran;
	}

	public void setMxaInteresTran(Double mxaInteresTran) {
		this.mxaInteresTran = mxaInteresTran;
	}

	public Double getMxaSaldo() {
		return mxaSaldo;
	}

	public void setMxaSaldo(Double mxaSaldo) {
		this.mxaSaldo = mxaSaldo;
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

}