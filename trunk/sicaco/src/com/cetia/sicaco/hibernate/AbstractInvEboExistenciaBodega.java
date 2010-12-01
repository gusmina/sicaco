package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractInvEboExistenciaBodega entity provides the base persistence
 * definition of the InvEboExistenciaBodega entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvEboExistenciaBodega implements
		java.io.Serializable {

	// Fields

	private InvEboExistenciaBodegaId id;
	private Integer eboCantidadProducto;
	private Double eboSaldo;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractInvEboExistenciaBodega() {
	}

	/** full constructor */
	public AbstractInvEboExistenciaBodega(InvEboExistenciaBodegaId id,
			Integer eboCantidadProducto, Double eboSaldo,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.id = id;
		this.eboCantidadProducto = eboCantidadProducto;
		this.eboSaldo = eboSaldo;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	// Property accessors

	public InvEboExistenciaBodegaId getId() {
		return this.id;
	}

	public void setId(InvEboExistenciaBodegaId id) {
		this.id = id;
	}

	public Integer getEboCantidadProducto() {
		return this.eboCantidadProducto;
	}

	public void setEboCantidadProducto(Integer eboCantidadProducto) {
		this.eboCantidadProducto = eboCantidadProducto;
	}

	public Double getEboSaldo() {
		return this.eboSaldo;
	}

	public void setEboSaldo(Double eboSaldo) {
		this.eboSaldo = eboSaldo;
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