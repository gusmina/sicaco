package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractInvNxmNivelMovimiento entity provides the base persistence definition
 * of the InvNxmNivelMovimiento entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvNxmNivelMovimiento implements
		java.io.Serializable {

	// Fields

	private Integer nxmId;
	private InvMovMovimientos invMovMovimientos;
	private InvNivNivel invNivNivel;
	private Integer nxmUnidades;
	private Double nxmValor;
	private Date nxmFecha;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractInvNxmNivelMovimiento() {
	}

	/** minimal constructor */
	public AbstractInvNxmNivelMovimiento(Integer nxmId, Integer nxmUnidades,
			Double nxmValor, Date nxmFecha, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.nxmId = nxmId;
		this.nxmUnidades = nxmUnidades;
		this.nxmValor = nxmValor;
		this.nxmFecha = nxmFecha;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractInvNxmNivelMovimiento(Integer nxmId,
			InvMovMovimientos invMovMovimientos, InvNivNivel invNivNivel,
			Integer nxmUnidades, Double nxmValor, Date nxmFecha,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.nxmId = nxmId;
		this.invMovMovimientos = invMovMovimientos;
		this.invNivNivel = invNivNivel;
		this.nxmUnidades = nxmUnidades;
		this.nxmValor = nxmValor;
		this.nxmFecha = nxmFecha;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	// Property accessors

	public Integer getNxmId() {
		return this.nxmId;
	}

	public void setNxmId(Integer nxmId) {
		this.nxmId = nxmId;
	}

	public InvMovMovimientos getInvMovMovimientos() {
		return this.invMovMovimientos;
	}

	public void setInvMovMovimientos(InvMovMovimientos invMovMovimientos) {
		this.invMovMovimientos = invMovMovimientos;
	}

	public InvNivNivel getInvNivNivel() {
		return this.invNivNivel;
	}

	public void setInvNivNivel(InvNivNivel invNivNivel) {
		this.invNivNivel = invNivNivel;
	}

	public Integer getNxmUnidades() {
		return this.nxmUnidades;
	}

	public void setNxmUnidades(Integer nxmUnidades) {
		this.nxmUnidades = nxmUnidades;
	}

	public Double getNxmValor() {
		return this.nxmValor;
	}

	public void setNxmValor(Double nxmValor) {
		this.nxmValor = nxmValor;
	}

	public Date getNxmFecha() {
		return this.nxmFecha;
	}

	public void setNxmFecha(Date nxmFecha) {
		this.nxmFecha = nxmFecha;
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