package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvArtArticulo entity provides the base persistence definition of the
 * InvArtArticulo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvArtArticulo implements java.io.Serializable {

	// Fields

	private String artCodigo;
	private InvMedMedida invMedMedida = new InvMedMedida();
	private InvTarTipoArticulo invTarTipoArticulo = new InvTarTipoArticulo();
	private InvLinLinea invLinLinea = new InvLinLinea();
	private String artNombre;
	private Double artPorcentajeUtilidad;
	private Double artPrecioSugerido;
	private Double artPrecioMinimo;
	private Byte artExcentoIva;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set invPexProductosExistencias = new HashSet(0);
	private Set invMovMovimientoses = new HashSet(0);
	private Set invCprCapacidadProductos = new HashSet(0);
	private Set facDfaDetalleFacturas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvArtArticulo() {
	}

	/** minimal constructor */
	public AbstractInvArtArticulo(String artCodigo, String artNombre,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.artCodigo = artCodigo;
		this.artNombre = artNombre;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */

	public AbstractInvArtArticulo(String artCodigo, InvMedMedida invMedMedida,
			InvTarTipoArticulo invTarTipoArticulo, InvLinLinea invLinLinea,
			String artNombre, Double artPorcentajeUtilidad,
			Double artPrecioSugerido, Double artPrecioMinimo,
			Byte artExcentoIva, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set invPexProductosExistencias,
			Set invMovMovimientoses, Set invCprCapacidadProductos,
			Set facDfaDetalleFacturas) {
		super();
		this.artCodigo = artCodigo;
		this.invMedMedida = invMedMedida;
		this.invTarTipoArticulo = invTarTipoArticulo;
		this.invLinLinea = invLinLinea;
		this.artNombre = artNombre;
		this.artPorcentajeUtilidad = artPorcentajeUtilidad;
		this.artPrecioSugerido = artPrecioSugerido;
		this.artPrecioMinimo = artPrecioMinimo;
		this.artExcentoIva = artExcentoIva;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.invPexProductosExistencias = invPexProductosExistencias;
		this.invMovMovimientoses = invMovMovimientoses;
		this.invCprCapacidadProductos = invCprCapacidadProductos;
		this.facDfaDetalleFacturas = facDfaDetalleFacturas;
	}

	// Property accessors

	public String getArtCodigo() {
		return this.artCodigo;
	}

	public void setArtCodigo(String artCodigo) {
		this.artCodigo = artCodigo;
	}

	public InvMedMedida getInvMedMedida() {
		return this.invMedMedida;
	}

	public void setInvMedMedida(InvMedMedida invMedMedida) {
		this.invMedMedida = invMedMedida;
	}

	public InvTarTipoArticulo getInvTarTipoArticulo() {
		return this.invTarTipoArticulo;
	}

	public void setInvTarTipoArticulo(InvTarTipoArticulo invTarTipoArticulo) {
		this.invTarTipoArticulo = invTarTipoArticulo;
	}

	public InvLinLinea getInvLinLinea() {
		return this.invLinLinea;
	}

	public void setInvLinLinea(InvLinLinea invLinLinea) {
		this.invLinLinea = invLinLinea;
	}

	public String getArtNombre() {
		return this.artNombre;
	}

	public void setArtNombre(String artNombre) {
		this.artNombre = artNombre;
	}

	public Double getArtPorcentajeUtilidad() {
		return this.artPorcentajeUtilidad;
	}

	public void setArtPorcentajeUtilidad(Double artPorcentajeUtilidad) {
		this.artPorcentajeUtilidad = artPorcentajeUtilidad;
	}

	public Double getArtPrecioSugerido() {
		return this.artPrecioSugerido;
	}

	public void setArtPrecioSugerido(Double artPrecioSugerido) {
		this.artPrecioSugerido = artPrecioSugerido;
	}

	public Double getArtPrecioMinimo() {
		return this.artPrecioMinimo;
	}

	public void setArtPrecioMinimo(Double artPrecioMinimo) {
		this.artPrecioMinimo = artPrecioMinimo;
	}

	public Byte getArtExcentoIva() {
		return this.artExcentoIva;
	}

	public void setArtExcentoIva(Byte artExcentoIva) {
		this.artExcentoIva = artExcentoIva;
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

	public Set getInvPexProductosExistencias() {
		return this.invPexProductosExistencias;
	}

	public void setInvPexProductosExistencias(Set invPexProductosExistencias) {
		this.invPexProductosExistencias = invPexProductosExistencias;
	}

	public Set getInvCprCapacidadProductos() {
		return this.invCprCapacidadProductos;
	}

	public void setInvCprCapacidadProductos(Set invCprCapacidadProductos) {
		this.invCprCapacidadProductos = invCprCapacidadProductos;
	}

	public Set getFacDfaDetalleFacturas() {
		return this.facDfaDetalleFacturas;
	}

	public void setFacDfaDetalleFacturas(Set facDfaDetalleFacturas) {
		this.facDfaDetalleFacturas = facDfaDetalleFacturas;
	}

	public Set getInvMovMovimientoses() {
		return invMovMovimientoses;
	}

	public void setInvMovMovimientoses(Set invMovMovimientoses) {
		this.invMovMovimientoses = invMovMovimientoses;
	}

}