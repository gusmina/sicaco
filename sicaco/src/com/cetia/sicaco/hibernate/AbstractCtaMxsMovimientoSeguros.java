package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCtaMxsMovimientoSeguros entity provides the base persistence
 * definition of the CtaMxsMovimientoSeguros entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaMxsMovimientoSeguros implements
		java.io.Serializable {

	// Fields

	private Integer mxsId;
	private CtaSegSeguros ctaSegSeguros;
	private CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado;
	private Double mxsMonto;
	private Double mxsSaldo;
	private Date mxsFecha;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractCtaMxsMovimientoSeguros() {
	}

	/** minimal constructor */
	public AbstractCtaMxsMovimientoSeguros(Double mxsMonto, Double mxsSaldo,
			Date mxsFecha) {
		this.mxsMonto = mxsMonto;
		this.mxsSaldo = mxsSaldo;
		this.mxsFecha = mxsFecha;
	}

	/** full constructor */
	public AbstractCtaMxsMovimientoSeguros(CtaSegSeguros ctaSegSeguros,
			CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado,
			Double mxsMonto, Double mxsSaldo, Date mxsFecha,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.ctaSegSeguros = ctaSegSeguros;
		this.ctaTxaTransaccionxcuentaAsociado = ctaTxaTransaccionxcuentaAsociado;
		this.mxsMonto = mxsMonto;
		this.mxsSaldo = mxsSaldo;
		this.mxsFecha = mxsFecha;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	// Property accessors

	public Integer getMxsId() {
		return this.mxsId;
	}

	public void setMxsId(Integer mxsId) {
		this.mxsId = mxsId;
	}

	public CtaSegSeguros getCtaSegSeguros() {
		return this.ctaSegSeguros;
	}

	public void setCtaSegSeguros(CtaSegSeguros ctaSegSeguros) {
		this.ctaSegSeguros = ctaSegSeguros;
	}

	public CtaTxaTransaccionxcuentaAsociado getCtaTxaTransaccionxcuentaAsociado() {
		return this.ctaTxaTransaccionxcuentaAsociado;
	}

	public void setCtaTxaTransaccionxcuentaAsociado(
			CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado) {
		this.ctaTxaTransaccionxcuentaAsociado = ctaTxaTransaccionxcuentaAsociado;
	}

	public Double getMxsMonto() {
		return this.mxsMonto;
	}

	public void setMxsMonto(Double mxsMonto) {
		this.mxsMonto = mxsMonto;
	}

	public Double getMxsSaldo() {
		return this.mxsSaldo;
	}

	public void setMxsSaldo(Double mxsSaldo) {
		this.mxsSaldo = mxsSaldo;
	}

	public Date getMxsFecha() {
		return this.mxsFecha;
	}

	public void setMxsFecha(Date mxsFecha) {
		this.mxsFecha = mxsFecha;
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