package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * FacFenFacturaEncabezado entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class FacFenFacturaEncabezado extends AbstractFacFenFacturaEncabezado
		implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2356027887328257836L;

	private Date hseFechaFactIni;
	private Date hseFechaFactFin;
	
	public FacFenFacturaEncabezado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FacFenFacturaEncabezado(Integer fenId, CtrEstEstado ctrEstEstado,
			InvBodBodegas invBodBodegas, FacFusFacturaUso facFusFacturaUso,
			ConRimRetencionImpuesto conRimRetencionImpuesto,
			String fenSerieFactura, String fenNumeroFactura,
			String fenRegistro, Date fenFechaFactura, String fenTipoFactura,
			String fenCancelada, String fenTipoPago, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(fenId, ctrEstEstado, invBodBodegas, facFusFacturaUso,
				conRimRetencionImpuesto, fenSerieFactura, fenNumeroFactura,
				fenRegistro, fenFechaFactura, fenTipoFactura, fenCancelada,
				fenTipoPago, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
		// TODO Auto-generated constructor stub
	}

	public FacFenFacturaEncabezado(Integer fenId, CtrEstEstado ctrEstEstado,
			InvBodBodegas invBodBodegas, InvProProveedor invProProveedor,
			FacFusFacturaUso facFusFacturaUso,
			ConRimRetencionImpuesto conRimRetencionImpuesto,
			FacCliCliente facCliCliente, CtaAscAsociado ctaAscAsociado,
			SecSucSucursal secSucSucursal, String fenSerieFactura,
			String fenNumeroFactura, String fenRegistro, Date fenFechaFactura,
			Double fenTotalVenta, Double fenTotalVentasExentas,
			Double fenIvaRetenido, String fenTipoFactura, String fenCancelada,
			String fenTipoPago, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Integer fenOriginal, String fenDescripcion,
			Set facDfaDetalleFacturas) {
		super(fenId, ctrEstEstado, invBodBodegas, invProProveedor, facFusFacturaUso,
				conRimRetencionImpuesto, facCliCliente, ctaAscAsociado, secSucSucursal,
				fenSerieFactura, fenNumeroFactura, fenRegistro, fenFechaFactura,
				fenTotalVenta, fenTotalVentasExentas, fenIvaRetenido, fenTipoFactura,
				fenCancelada, fenTipoPago, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion, fenOriginal,
				fenDescripcion, facDfaDetalleFacturas);
		// TODO Auto-generated constructor stub
	}

	public Date getHseFechaFactIni() {
		return hseFechaFactIni;
	}

	public void setHseFechaFactIni(Date hseFechaFactIni) {
		this.hseFechaFactIni = hseFechaFactIni;
	}

	public Date getHseFechaFactFin() {
		return hseFechaFactFin;
	}

	public void setHseFechaFactFin(Date hseFechaFactFin) {
		this.hseFechaFactFin = hseFechaFactFin;
	}
}
