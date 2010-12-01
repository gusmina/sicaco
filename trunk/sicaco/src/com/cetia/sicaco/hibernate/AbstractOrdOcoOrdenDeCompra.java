package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractOrdOcoOrdenDeCompra entity provides the base persistence definition
 * of the OrdOcoOrdenDeCompra entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrdOcoOrdenDeCompra implements
		java.io.Serializable {

	// Fields

	private Integer ocoId;
	private OrdRefCuentaReferencia ordRefCuentaReferencia = new OrdRefCuentaReferencia();
    private String ascCodigo;
    private InvProProveedor invProProveedor = new InvProProveedor();
	private Integer ocoDonacion;
	private Integer ocoCodigo;
	private Date ocoEmision;
	private Date ocoVencimiento;
	private Double ocoMonto;
	private Double ocoPagado;
	private Double ocoSaldo;
	private String ocoElaborado;
	private String ocoEstado;
	private String cliCodigo;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set ordPcoPagoCompras = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractOrdOcoOrdenDeCompra() {
	}

	/** minimal constructor */
	public AbstractOrdOcoOrdenDeCompra(Integer ocoId, Integer ocoCodigo,
			Date ocoEmision, Double ocoMonto, String ocoElaborado,
			String ocoEstado, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.ocoId = ocoId;
		this.ocoCodigo = ocoCodigo;
		this.ocoEmision = ocoEmision;
		this.ocoMonto = ocoMonto;
		this.ocoElaborado = ocoElaborado;
		this.ocoEstado = ocoEstado;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractOrdOcoOrdenDeCompra(Integer ocoId,
			OrdRefCuentaReferencia ordRefCuentaReferencia,
			String ascCodigo, InvProProveedor invProProveedor,
			Integer ocoDonacion, Integer ocoCodigo, Date ocoEmision,
			Date ocoVencimiento, Double ocoMonto, Double ocoPagado,
			Double ocoSaldo, String ocoElaborado, String ocoEstado,
			String cliCodigo,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set ordPcoPagoCompras) {
		this.ocoId = ocoId;
		this.ordRefCuentaReferencia = ordRefCuentaReferencia;
		this.ascCodigo = ascCodigo;
		this.invProProveedor = invProProveedor;
		this.ocoDonacion = ocoDonacion;
		this.ocoCodigo = ocoCodigo;
		this.ocoEmision = ocoEmision;
		this.ocoVencimiento = ocoVencimiento;
		this.ocoMonto = ocoMonto;
		this.ocoPagado = ocoPagado;
		this.ocoSaldo = ocoSaldo;
		this.ocoElaborado = ocoElaborado;
		this.ocoEstado = ocoEstado;
		this.cliCodigo = cliCodigo;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.ordPcoPagoCompras = ordPcoPagoCompras;
	}

	// Property accessors

	public Integer getOcoId() {
		return this.ocoId;
	}

	public void setOcoId(Integer ocoId) {
		this.ocoId = ocoId;
	}

	public OrdRefCuentaReferencia getOrdRefCuentaReferencia() {
		return this.ordRefCuentaReferencia;
	}

	public void setOrdRefCuentaReferencia(
			OrdRefCuentaReferencia ordRefCuentaReferencia) {
		this.ordRefCuentaReferencia = ordRefCuentaReferencia;
	}

	public String getAscCodigo() {
		return ascCodigo;
	}

	public void setAscCodigo(String ascCodigo) {
		this.ascCodigo = ascCodigo;
	}

	public InvProProveedor getInvProProveedor() {
		return this.invProProveedor;
	}

	public void setInvProProveedor(InvProProveedor invProProveedor) {
		this.invProProveedor = invProProveedor;
	}

	public Integer getOcoDonacion() {
		return this.ocoDonacion;
	}

	public void setOcoDonacion(Integer ocoDonacion) {
		this.ocoDonacion = ocoDonacion;
	}

	public Integer getOcoCodigo() {
		return this.ocoCodigo;
	}

	public void setOcoCodigo(Integer ocoCodigo) {
		this.ocoCodigo = ocoCodigo;
	}

	public Date getOcoEmision() {
		return this.ocoEmision;
	}

	public void setOcoEmision(Date ocoEmision) {
		this.ocoEmision = ocoEmision;
	}

	public Date getOcoVencimiento() {
		return this.ocoVencimiento;
	}

	public void setOcoVencimiento(Date ocoVencimiento) {
		this.ocoVencimiento = ocoVencimiento;
	}

	public Double getOcoMonto() {
		return this.ocoMonto;
	}

	public void setOcoMonto(Double ocoMonto) {
		this.ocoMonto = ocoMonto;
	}

	public Double getOcoPagado() {
		return this.ocoPagado;
	}

	public void setOcoPagado(Double ocoPagado) {
		this.ocoPagado = ocoPagado;
	}

	public Double getOcoSaldo() {
		return this.ocoSaldo;
	}

	public void setOcoSaldo(Double ocoSaldo) {
		this.ocoSaldo = ocoSaldo;
	}

	public String getOcoElaborado() {
		return this.ocoElaborado;
	}

	public void setOcoElaborado(String ocoElaborado) {
		this.ocoElaborado = ocoElaborado;
	}

	public String getOcoEstado() {
		return this.ocoEstado;
	}

	public void setOcoEstado(String ocoEstado) {
		this.ocoEstado = ocoEstado;
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

	public Set getOrdPcoPagoCompras() {
		return this.ordPcoPagoCompras;
	}

	public void setOrdPcoPagoCompras(Set ordPcoPagoCompras) {
		this.ordPcoPagoCompras = ordPcoPagoCompras;
	}

	public String getCliCodigo() {
		return cliCodigo;
	}

	public void setCliCodigo(String cliCodigo) {
		this.cliCodigo = cliCodigo;
	}

}