package com.cetia.sicaco.hibernate;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractOrdOpaOrdenDePago entity provides the base persistence definition of
 * the OrdOpaOrdenDePago entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrdOpaOrdenDePago implements java.io.Serializable {

	// Fields

	private Integer opaId;
	private InvProProveedor invProProveedor = new InvProProveedor();
	private Integer opaCodigo;
	private Date opaFechaPago;
	private Double opaTotal;
	private Double opaDescuento;
	private String opaTipoPago;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private String opaEstado;
	private String opaNota;
	private Set ordPcoPagoCompras = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractOrdOpaOrdenDePago() {
	}

	/** minimal constructor */
	public AbstractOrdOpaOrdenDePago(Integer opaId, Integer opaCodigo,
			Date opaFechaPago, Double opaTotal, Double opaDescuento,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			String opaEstado) {
		this.opaId = opaId;
		this.opaCodigo = opaCodigo;
		this.opaFechaPago = opaFechaPago;
		this.opaTotal = opaTotal;
		this.opaDescuento = opaDescuento;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.opaEstado = opaEstado;
	}

	/** full constructor */
	public AbstractOrdOpaOrdenDePago(Integer opaId,
			InvProProveedor invProProveedor, Integer opaCodigo,
			Date opaFechaPago, Double opaTotal, Double opaDescuento,
			String opaTipoPago, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String opaEstado,
			String opaNota,
			Set ordPcoPagoCompras) {
		this.opaId = opaId;
		this.invProProveedor = invProProveedor;
		this.opaCodigo = opaCodigo;
		this.opaFechaPago = opaFechaPago;
		this.opaTotal = opaTotal;
		this.opaDescuento = opaDescuento;
		this.opaTipoPago = opaTipoPago;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.opaEstado = opaEstado;
		this.opaNota = opaNota;
		this.ordPcoPagoCompras = ordPcoPagoCompras;
	}

	// Property accessors

	public Integer getOpaId() {
		return this.opaId;
	}

	public void setOpaId(Integer opaId) {
		this.opaId = opaId;
	}

	public InvProProveedor getInvProProveedor() {
		return this.invProProveedor;
	}

	public void setInvProProveedor(InvProProveedor invProProveedor) {
		this.invProProveedor = invProProveedor;
	}

	public Integer getOpaCodigo() {
		return this.opaCodigo;
	}

	public void setOpaCodigo(Integer opaCodigo) {
		this.opaCodigo = opaCodigo;
	}

	public Date getOpaFechaPago() {
		return this.opaFechaPago;
	}

	public void setOpaFechaPago(Date opaFechaPago) {
		this.opaFechaPago = opaFechaPago;
	}

	public Double getOpaTotal() {
		return this.opaTotal;
	}

	public void setOpaTotal(Double opaTotal) {
		this.opaTotal = opaTotal;
	}

	public Double getOpaDescuento() {
		return this.opaDescuento;
	}

	public void setOpaDescuento(Double opaDescuento) {
		this.opaDescuento = opaDescuento;
	}

	public String getOpaTipoPago() {
		return this.opaTipoPago;
	}

	public void setOpaTipoPago(String opaTipoPago) {
		this.opaTipoPago = opaTipoPago;
	}

	public Date getAudFechaCreacion() {
		return this.audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
	}

	public String getAudUsuarioCreacion() {
		return this.audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaModificacion() {
		return this.audFechaModificacion;
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		this.audFechaModificacion = audFechaModificacion;
	}

	public String getAudUsuarioModificacion() {
		return this.audUsuarioModificacion;
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	public String getOpaEstado() {
		return this.opaEstado;
	}

	public void setOpaEstado(String opaEstado) {
		this.opaEstado = opaEstado;
	}

	public Set getOrdPcoPagoCompras() {
		return this.ordPcoPagoCompras;
	}

	public void setOrdPcoPagoCompras(Set ordPcoPagoCompras) {
		this.ordPcoPagoCompras = ordPcoPagoCompras;
	}

	public String getOpaNota() {
		return opaNota;
	}

	public void setOpaNota(String opaNota) {
		this.opaNota = opaNota;
	}

}