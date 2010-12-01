package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractFacDfaDetalleFactura entity provides the base persistence definition
 * of the FacDfaDetalleFactura entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractFacDfaDetalleFactura implements
		java.io.Serializable {

	// Fields

	private FacDfaDetalleFacturaId id;
	private FacFenFacturaEncabezado facFenFacturaEncabezado = new FacFenFacturaEncabezado();
	private Integer dfaCantidad;
	private String dfaDescripcion;
	private Double dfaPrecioUnitario;
	private Byte dfaExento;
	private Double dfaPrecioTotal;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private int cnvId;
	private Set invMovMovimientoses = new HashSet(0);

	// Constructors

	public int getCnvId() {
		return cnvId;
	}

	public void setCnvId(int cnvId) {
		this.cnvId = cnvId;
	}

	/** default constructor */
	public AbstractFacDfaDetalleFactura() {
	}

	/** minimal constructor */
	public AbstractFacDfaDetalleFactura(FacDfaDetalleFacturaId id,
			FacFenFacturaEncabezado facFenFacturaEncabezado,
			Integer dfaCantidad, Double dfaPrecioUnitario, Byte dfaExento,
			Double dfaPrecioTotal, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.id = id;
		this.facFenFacturaEncabezado = facFenFacturaEncabezado;
		this.dfaCantidad = dfaCantidad;
		this.dfaPrecioUnitario = dfaPrecioUnitario;
		this.dfaExento = dfaExento;
		this.dfaPrecioTotal = dfaPrecioTotal;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;

	}

	/** full constructor */
	public AbstractFacDfaDetalleFactura(FacDfaDetalleFacturaId id,
			FacFenFacturaEncabezado facFenFacturaEncabezado,
			Integer dfaCantidad, String dfaDescripcion,
			Double dfaPrecioUnitario, Byte dfaExento, Double dfaPrecioTotal,int cnvId,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set invMovMovimientoses) {
		this.id = id;
		this.facFenFacturaEncabezado = facFenFacturaEncabezado;
		this.dfaCantidad = dfaCantidad;
		this.dfaDescripcion = dfaDescripcion;
		this.dfaPrecioUnitario = dfaPrecioUnitario;
		this.dfaExento = dfaExento;
		this.dfaPrecioTotal = dfaPrecioTotal;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.cnvId = cnvId;
		this.invMovMovimientoses = invMovMovimientoses;
	}

	// Property accessors

	
	
	public FacDfaDetalleFacturaId getId() {
		return this.id;
	}

	public void setId(FacDfaDetalleFacturaId id) {
		this.id = id;
	}

	public FacFenFacturaEncabezado getFacFenFacturaEncabezado() {
		return this.facFenFacturaEncabezado;
	}

	public void setFacFenFacturaEncabezado(
			FacFenFacturaEncabezado facFenFacturaEncabezado) {
		this.facFenFacturaEncabezado = facFenFacturaEncabezado;
	}

	public Integer getDfaCantidad() {
		return this.dfaCantidad;
	}

	public void setDfaCantidad(Integer dfaCantidad) {
		this.dfaCantidad = dfaCantidad;
	}

	public String getDfaDescripcion() {
		return this.dfaDescripcion;
	}

	public void setDfaDescripcion(String dfaDescripcion) {
		this.dfaDescripcion = dfaDescripcion;
	}

	public Double getDfaPrecioUnitario() {
		return this.dfaPrecioUnitario;
	}

	public void setDfaPrecioUnitario(Double dfaPrecioUnitario) {
		this.dfaPrecioUnitario = dfaPrecioUnitario;
	}

	public Byte getDfaExento() {
		return this.dfaExento;
	}

	public void setDfaExento(Byte dfaExento) {
		this.dfaExento = dfaExento;
	}

	public Double getDfaPrecioTotal() {
		return this.dfaPrecioTotal;
	}

	public void setDfaPrecioTotal(Double dfaPrecioTotal) {
		this.dfaPrecioTotal = dfaPrecioTotal;
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

	public Set getInvMovMovimientoses() {
		return this.invMovMovimientoses;
	}

	public void setInvMovMovimientoses(Set invMovMovimientoses) {
		this.invMovMovimientoses = invMovMovimientoses;
	}

}