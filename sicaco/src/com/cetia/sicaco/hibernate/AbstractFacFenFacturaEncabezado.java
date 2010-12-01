package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractFacFenFacturaEncabezado entity provides the base persistence
 * definition of the FacFenFacturaEncabezado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractFacFenFacturaEncabezado implements
		java.io.Serializable {

	// Fields

	private Integer fenId;
	private CtrEstEstado ctrEstEstado = new CtrEstEstado();
	private InvBodBodegas invBodBodegas = new InvBodBodegas();
	private InvProProveedor invProProveedor = new InvProProveedor();
	private FacFusFacturaUso facFusFacturaUso = new FacFusFacturaUso(); 
	private ConRimRetencionImpuesto conRimRetencionImpuesto = new ConRimRetencionImpuesto();
	private FacCliCliente facCliCliente = new FacCliCliente();
	private CtaAscAsociado ctaAscAsociado = new CtaAscAsociado();
	private SecSucSucursal secSucSucursal = new SecSucSucursal();
	private String fenSerieFactura;
	private String fenNumeroFactura;
	private String fenRegistro;
	private Date fenFechaFactura;
	private Double fenTotalVenta;
	private Double fenTotalVentasExentas;
	private Double fenIvaRetenido;
	private String fenTipoFactura;
	private String fenCancelada;
	private String fenTipoPago;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Integer fenOriginal;
	private String fenDescripcion;
	private Set facDfaDetalleFacturas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractFacFenFacturaEncabezado() {
	}

	/** minimal constructor */
	public AbstractFacFenFacturaEncabezado(Integer fenId,
			CtrEstEstado ctrEstEstado, InvBodBodegas invBodBodegas,
			FacFusFacturaUso facFusFacturaUso,
			ConRimRetencionImpuesto conRimRetencionImpuesto,
			String fenSerieFactura, String fenNumeroFactura,
			String fenRegistro, Date fenFechaFactura, String fenTipoFactura,
			String fenCancelada,String fenTipoPago, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.fenId = fenId;
		this.ctrEstEstado = ctrEstEstado;
		this.invBodBodegas = invBodBodegas;
		this.facFusFacturaUso = facFusFacturaUso;
		this.conRimRetencionImpuesto = conRimRetencionImpuesto;
		this.fenSerieFactura = fenSerieFactura;
		this.fenNumeroFactura = fenNumeroFactura;
		this.fenRegistro = fenRegistro;
		this.fenFechaFactura = fenFechaFactura;
		this.fenTipoFactura = fenTipoFactura;
		this.fenCancelada = fenCancelada;
		this.fenTipoPago = fenTipoPago;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */

	public AbstractFacFenFacturaEncabezado(Integer fenId,
			CtrEstEstado ctrEstEstado, InvBodBodegas invBodBodegas,
			InvProProveedor invProProveedor, FacFusFacturaUso facFusFacturaUso,
			ConRimRetencionImpuesto conRimRetencionImpuesto,
			FacCliCliente facCliCliente, CtaAscAsociado ctaAscAsociado,
			SecSucSucursal secSucSucursal,
			String fenSerieFactura, String fenNumeroFactura,
			String fenRegistro, Date fenFechaFactura, Double fenTotalVenta,
			Double fenTotalVentasExentas, Double fenIvaRetenido,
			String fenTipoFactura, String fenCancelada, String fenTipoPago,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Integer fenOriginal, String fenDescripcion,
			Set facDfaDetalleFacturas) {
		super();
		this.fenId = fenId;
		this.ctrEstEstado = ctrEstEstado;
		this.invBodBodegas = invBodBodegas;
		this.invProProveedor = invProProveedor;
		this.facFusFacturaUso = facFusFacturaUso;
		this.conRimRetencionImpuesto = conRimRetencionImpuesto;
		this.facCliCliente = facCliCliente;
		this.ctaAscAsociado = ctaAscAsociado;
		this.secSucSucursal = secSucSucursal;
		this.fenSerieFactura = fenSerieFactura;
		this.fenNumeroFactura = fenNumeroFactura;
		this.fenRegistro = fenRegistro;
		this.fenFechaFactura = fenFechaFactura;
		this.fenTotalVenta = fenTotalVenta;
		this.fenTotalVentasExentas = fenTotalVentasExentas;
		this.fenIvaRetenido = fenIvaRetenido;
		this.fenTipoFactura = fenTipoFactura;
		this.fenCancelada = fenCancelada;
		this.fenTipoPago = fenTipoPago;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.fenOriginal = fenOriginal;
		this.fenDescripcion = fenDescripcion;
		this.facDfaDetalleFacturas = facDfaDetalleFacturas;
	}
	
	// Property accessors

	public Integer getFenId() {
		return this.fenId;
	}

	public void setFenId(Integer fenId) {
		this.fenId = fenId;
	}

	public CtrEstEstado getCtrEstEstado() {
		return this.ctrEstEstado;
	}

	public void setCtrEstEstado(CtrEstEstado ctrEstEstado) {
		this.ctrEstEstado = ctrEstEstado;
	}

	public InvBodBodegas getInvBodBodegas() {
		return this.invBodBodegas;
	}

	public void setInvBodBodegas(InvBodBodegas invBodBodegas) {
		this.invBodBodegas = invBodBodegas;
	}

	public FacFusFacturaUso getFacFusFacturaUso() {
		return this.facFusFacturaUso;
	}

	public void setFacFusFacturaUso(FacFusFacturaUso facFusFacturaUso) {
		this.facFusFacturaUso = facFusFacturaUso;
	}

	public ConRimRetencionImpuesto getConRimRetencionImpuesto() {
		return this.conRimRetencionImpuesto;
	}

	public void setConRimRetencionImpuesto(
			ConRimRetencionImpuesto conRimRetencionImpuesto) {
		this.conRimRetencionImpuesto = conRimRetencionImpuesto;
	}

	public FacCliCliente getFacCliCliente() {
		return facCliCliente;
	}

	public void setFacCliCliente(FacCliCliente facCliCliente) {
		this.facCliCliente = facCliCliente;
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return ctaAscAsociado;
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		this.ctaAscAsociado = ctaAscAsociado;
	}

	public SecSucSucursal getSecSucSucursal() {
		return secSucSucursal;
	}

	public void setSecSucSucursal(SecSucSucursal secSucSucursal) {
		this.secSucSucursal = secSucSucursal;
	}

	public String getFenSerieFactura() {
		return this.fenSerieFactura;
	}

	public void setFenSerieFactura(String fenSerieFactura) {
		this.fenSerieFactura = fenSerieFactura;
	}

	public String getFenNumeroFactura() {
		return this.fenNumeroFactura;
	}

	public void setFenNumeroFactura(String fenNumeroFactura) {
		this.fenNumeroFactura = fenNumeroFactura;
	}

	public String getFenRegistro() {
		return this.fenRegistro;
	}

	public void setFenRegistro(String fenRegistro) {
		this.fenRegistro = fenRegistro;
	}

	public Date getFenFechaFactura() {
		return this.fenFechaFactura;
	}

	public void setFenFechaFactura(Date fenFechaFactura) {
		this.fenFechaFactura = fenFechaFactura;
	}

	public Double getFenTotalVenta() {
		return this.fenTotalVenta;
	}

	public void setFenTotalVenta(Double fenTotalVenta) {
		this.fenTotalVenta = fenTotalVenta;
	}

	public Double getFenTotalVentasExentas() {
		return this.fenTotalVentasExentas;
	}

	public void setFenTotalVentasExentas(Double fenTotalVentasExentas) {
		this.fenTotalVentasExentas = fenTotalVentasExentas;
	}

	public Double getFenIvaRetenido() {
		return this.fenIvaRetenido;
	}

	public void setFenIvaRetenido(Double fenIvaRetenido) {
		this.fenIvaRetenido = fenIvaRetenido;
	}

	public String getFenTipoFactura() {
		return this.fenTipoFactura;
	}

	public void setFenTipoFactura(String fenTipoFactura) {
		this.fenTipoFactura = fenTipoFactura;
	}

	public String getFenCancelada() {
		return this.fenCancelada;
	}

	public void setFenCancelada(String fenCancelada) {
		this.fenCancelada = fenCancelada;
	}
	
	public String getFenTipoPago() {
		return fenTipoPago;
	}

	public void setFenTipoPago(String fenTipoPago) {
		this.fenTipoPago = fenTipoPago;
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

	public Set getFacDfaDetalleFacturas() {
		return this.facDfaDetalleFacturas;
	}

	public void setFacDfaDetalleFacturas(Set facDfaDetalleFacturas) {
		this.facDfaDetalleFacturas = facDfaDetalleFacturas;
	}

	public InvProProveedor getInvProProveedor() {
		return invProProveedor;
	}

	public void setInvProProveedor(InvProProveedor invProProveedor) {
		this.invProProveedor = invProProveedor;
	}

	public Integer getFenOriginal() {
		return fenOriginal;
	}

	public void setFenOriginal(Integer fenOriginal) {
		this.fenOriginal = fenOriginal;
	}

	public String getFenDescripcion() {
		return fenDescripcion;
	}

	public void setFenDescripcion(String fenDescripcion) {
		this.fenDescripcion = fenDescripcion;
	}

}