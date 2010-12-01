package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvPexProductosExistencia entity provides the base persistence
 * definition of the InvPexProductosExistencia entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvPexProductosExistencia implements
		java.io.Serializable {

	// Fields

	private String artCodigo;
	private InvArtArticulo invArtArticulo = new InvArtArticulo();
	private Integer pexCantidadProducto;
	private Double pexSaldo;
	private Double pexCostoProducto;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set invEboExistenciaBodegas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvPexProductosExistencia() {
	}

	/** minimal constructor */
	public AbstractInvPexProductosExistencia(String artCodigo,
			InvArtArticulo invArtArticulo, Integer pexCantidadProducto,
			Double pexSaldo, Double pexCostoProducto, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.artCodigo = artCodigo;
		this.invArtArticulo = invArtArticulo;
		this.pexCantidadProducto = pexCantidadProducto;
		this.pexSaldo = pexSaldo;
		this.pexCostoProducto = pexCostoProducto;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractInvPexProductosExistencia(String artCodigo,
			InvArtArticulo invArtArticulo, Integer pexCantidadProducto,
			Double pexSaldo, Double pexCostoProducto, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set invEboExistenciaBodegas) {
		this.artCodigo = artCodigo;
		this.invArtArticulo = invArtArticulo;
		this.pexCantidadProducto = pexCantidadProducto;
		this.pexSaldo = pexSaldo;
		this.pexCostoProducto = pexCostoProducto;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.invEboExistenciaBodegas = invEboExistenciaBodegas;
	}

	// Property accessors

	public String getArtCodigo() {
		return this.artCodigo;
	}

	public void setArtCodigo(String artCodigo) {
		this.artCodigo = artCodigo;
	}

	public InvArtArticulo getInvArtArticulo() {
		return this.invArtArticulo;
	}

	public void setInvArtArticulo(InvArtArticulo invArtArticulo) {
		this.invArtArticulo = invArtArticulo;
	}

	public Integer getPexCantidadProducto() {
		return this.pexCantidadProducto;
	}

	public void setPexCantidadProducto(Integer pexCantidadProducto) {
		this.pexCantidadProducto = pexCantidadProducto;
	}

	public Double getPexSaldo() {
		return this.pexSaldo;
	}

	public void setPexSaldo(Double pexSaldo) {
		this.pexSaldo = pexSaldo;
	}

	public Double getPexCostoProducto() {
		return this.pexCostoProducto;
	}

	public void setPexCostoProducto(Double pexCostoProducto) {
		this.pexCostoProducto = pexCostoProducto;
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

	public Set getInvEboExistenciaBodegas() {
		return this.invEboExistenciaBodegas;
	}

	public void setInvEboExistenciaBodegas(Set invEboExistenciaBodegas) {
		this.invEboExistenciaBodegas = invEboExistenciaBodegas;
	}

}