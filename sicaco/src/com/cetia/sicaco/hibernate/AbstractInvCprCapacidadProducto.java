package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractInvCprCapacidadProducto entity provides the base persistence
 * definition of the InvCprCapacidadProducto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvCprCapacidadProducto implements
		java.io.Serializable {

	// Fields

	private InvCprCapacidadProductoId id;
	private Integer cprCantidadMinima;
	private Integer cprCantidadMaxima;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractInvCprCapacidadProducto() {
	}

	/** full constructor */
	public AbstractInvCprCapacidadProducto(InvCprCapacidadProductoId id,
			Integer cprCantidadMinima, Integer cprCantidadMaxima,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.id = id;
		this.cprCantidadMinima = cprCantidadMinima;
		this.cprCantidadMaxima = cprCantidadMaxima;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	// Property accessors

	public InvCprCapacidadProductoId getId() {
		return this.id;
	}

	public void setId(InvCprCapacidadProductoId id) {
		this.id = id;
	}

	public Integer getCprCantidadMinima() {
		return this.cprCantidadMinima;
	}

	public void setCprCantidadMinima(Integer cprCantidadMinima) {
		this.cprCantidadMinima = cprCantidadMinima;
	}

	public Integer getCprCantidadMaxima() {
		return this.cprCantidadMaxima;
	}

	public void setCprCantidadMaxima(Integer cprCantidadMaxima) {
		this.cprCantidadMaxima = cprCantidadMaxima;
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

}