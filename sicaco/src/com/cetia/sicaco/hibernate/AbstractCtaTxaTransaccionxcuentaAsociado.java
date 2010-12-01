package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTxaTransaccionxcuentaAsociado entity provides the base persistence
 * definition of the CtaTxaTransaccionxcuentaAsociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTxaTransaccionxcuentaAsociado implements
		java.io.Serializable {

	// Fields

	private Long txaId;
	private CtaCasCuentaAsociado ctaCasCuentaAsociado = new CtaCasCuentaAsociado();
	private CtaTtrTipoTransaccion ctaTtrTipoTransaccion = new CtaTtrTipoTransaccion();
	private CtaNotNotas ctaNotNotas = new CtaNotNotas();
	private Double txaMonto;
	private Date txaFecha;
	private Long txaComprobante;
	private String txaNota;
	private String txaCheque;
	private String txaEstado = "E";
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set ctaMxpMovimientoPrestamos = new HashSet(0);
	private Set ctaMxaMovimientoAhorros = new HashSet(0);
	private Set ctaMxsMovimientoSeguroses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTxaTransaccionxcuentaAsociado() {
	}

	/** minimal constructor */
	public AbstractCtaTxaTransaccionxcuentaAsociado(Long txaId,
			Double txaMonto, Date txaFecha,
			Long txaComprobante) {
		this.txaId = txaId;
		this.txaMonto = txaMonto;
		this.txaFecha = txaFecha;
		this.txaComprobante = txaComprobante;
	}

	/** full constructor */
	public AbstractCtaTxaTransaccionxcuentaAsociado(Long txaId,
			CtaCasCuentaAsociado ctaCasCuentaAsociado,
			CtaTtrTipoTransaccion ctaTtrTipoTransaccion,
			CtaNotNotas ctaNotNotas, Double txaMonto, Date txaFecha,
			Long txaComprobante, String txaNota, String txaCheque,
			String txaEstado, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set ctaMxpMovimientoPrestamos, Set ctaMxaMovimientoAhorros,
			Set ctaMxsMovimientoSeguroses) {
		super();
		this.txaId = txaId;
		this.ctaCasCuentaAsociado = ctaCasCuentaAsociado;
		this.ctaTtrTipoTransaccion = ctaTtrTipoTransaccion;
		this.ctaNotNotas = ctaNotNotas;
		this.txaMonto = txaMonto;
		this.txaFecha = txaFecha;
		this.txaComprobante = txaComprobante;
		this.txaNota = txaNota;
		this.txaCheque = txaCheque;
		this.txaEstado = txaEstado;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.ctaMxpMovimientoPrestamos = ctaMxpMovimientoPrestamos;
		this.ctaMxaMovimientoAhorros = ctaMxaMovimientoAhorros;
		this.ctaMxsMovimientoSeguroses = ctaMxsMovimientoSeguroses;
	}
	
	// Property accessors

	public Long getTxaId() {
		return this.txaId;
	}

	public void setTxaId(Long txaId) {
		this.txaId = txaId;
	}

	public CtaCasCuentaAsociado getCtaCasCuentaAsociado() {
		return this.ctaCasCuentaAsociado;
	}

	public void setCtaCasCuentaAsociado(
			CtaCasCuentaAsociado ctaCasCuentaAsociado) {
		this.ctaCasCuentaAsociado = ctaCasCuentaAsociado;
	}

	public CtaTtrTipoTransaccion getCtaTtrTipoTransaccion() {
		return this.ctaTtrTipoTransaccion;
	}

	public void setCtaTtrTipoTransaccion(
			CtaTtrTipoTransaccion ctaTtrTipoTransaccion) {
		this.ctaTtrTipoTransaccion = ctaTtrTipoTransaccion;
	}

	public CtaNotNotas getCtaNotNotas() {
		return ctaNotNotas;
	}

	public void setCtaNotNotas(CtaNotNotas ctaNotNotas) {
		this.ctaNotNotas = ctaNotNotas;
	}

	public Double getTxaMonto() {
		return this.txaMonto;
	}

	public void setTxaMonto(Double txaMonto) {
		this.txaMonto = txaMonto;
	}

	public Date getTxaFecha() {
		return this.txaFecha;
	}

	public void setTxaFecha(Date txaFecha) {
		this.txaFecha = txaFecha;
	}

	public Long getTxaComprobante() {
		return this.txaComprobante;
	}

	public void setTxaComprobante(Long txaComprobante) {
		this.txaComprobante = txaComprobante;
	}

	public Set getCtaMxpMovimientoPrestamos() {
		return this.ctaMxpMovimientoPrestamos;
	}

	public void setCtaMxpMovimientoPrestamos(Set ctaMxpMovimientoPrestamos) {
		this.ctaMxpMovimientoPrestamos = ctaMxpMovimientoPrestamos;
	}

	public Set getCtaMxaMovimientoAhorros() {
		return this.ctaMxaMovimientoAhorros;
	}

	public void setCtaMxaMovimientoAhorros(Set ctaMxaMovimientoAhorros) {
		this.ctaMxaMovimientoAhorros = ctaMxaMovimientoAhorros;
	}

	public String getTxaNota() {
		return txaNota;
	}

	public void setTxaNota(String txaNota) {
		this.txaNota = txaNota;
	}

	public String getTxaCheque() {
		return txaCheque;
	}

	public void setTxaCheque(String txaCheque) {
		this.txaCheque = txaCheque;
	}

	public String getTxaEstado() {
		return txaEstado;
	}

	public void setTxaEstado(String txaEstado) {
		this.txaEstado = txaEstado;
	}

	public Set getCtaMxsMovimientoSeguroses() {
		return ctaMxsMovimientoSeguroses;
	}

	public void setCtaMxsMovimientoSeguroses(Set ctaMxsMovimientoSeguroses) {
		this.ctaMxsMovimientoSeguroses = ctaMxsMovimientoSeguroses;
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