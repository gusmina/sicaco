package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvMovMovimientos entity provides the base persistence definition of
 * the InvMovMovimientos entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvMovMovimientos implements java.io.Serializable {

	// Fields

	private Integer movId;
	private InvArtArticulo invArtArticulo = new InvArtArticulo();
	private InvBodBodegas invBodBodegas = new InvBodBodegas();
	private InvTmoTipoMovimiento invTmoTipoMovimiento = new InvTmoTipoMovimiento();
	private Integer movUnidades;
	private Double movValor;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set invNxmNivelMovimientos = new HashSet(0);

	// Constructors



	/** default constructor */
	public AbstractInvMovMovimientos() {
	}
	/** full constructor */
	public AbstractInvMovMovimientos(Integer movId,
			InvArtArticulo invArtArticulo, InvBodBodegas invBodBodegas,
			InvTmoTipoMovimiento invTmoTipoMovimiento, Integer movUnidades,
			Double movValor, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set invNxmNivelMovimientos) {
		super();
		this.movId = movId;
		this.invArtArticulo = invArtArticulo;
		this.invBodBodegas = invBodBodegas;
		this.invTmoTipoMovimiento = invTmoTipoMovimiento;
		this.movUnidades = movUnidades;
		this.movValor = movValor;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.invNxmNivelMovimientos = invNxmNivelMovimientos;
	}
	// Property accessors
	public Integer getMovId() {
		return movId;
	}

	public void setMovId(Integer movId) {
		this.movId = movId;
	}

	public InvArtArticulo getInvArtArticulo() {
		return invArtArticulo;
	}

	public void setInvArtArticulo(InvArtArticulo invArtArticulo) {
		this.invArtArticulo = invArtArticulo;
	}

	public InvBodBodegas getInvBodBodegas() {
		return invBodBodegas;
	}

	public void setInvBodBodegas(InvBodBodegas invBodBodegas) {
		this.invBodBodegas = invBodBodegas;
	}

	public InvTmoTipoMovimiento getInvTmoTipoMovimiento() {
		return invTmoTipoMovimiento;
	}

	public void setInvTmoTipoMovimiento(InvTmoTipoMovimiento invTmoTipoMovimiento) {
		this.invTmoTipoMovimiento = invTmoTipoMovimiento;
	}

	public Integer getMovUnidades() {
		return movUnidades;
	}

	public void setMovUnidades(Integer movUnidades) {
		this.movUnidades = movUnidades;
	}

	public Double getMovValor() {
		return movValor;
	}

	public void setMovValor(Double movValor) {
		this.movValor = movValor;
	}

	public Date getAudFechaCreacion() {
		return audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
	}

	public String getAudUsuarioCreacion() {
		return audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaModificacion() {
		return audFechaModificacion;
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		this.audFechaModificacion = audFechaModificacion;
	}

	public String getAudUsuarioModificacion() {
		return audUsuarioModificacion;
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	public Set getInvNxmNivelMovimientos() {
		return invNxmNivelMovimientos;
	}

	public void setInvNxmNivelMovimientos(Set invNxmNivelMovimientos) {
		this.invNxmNivelMovimientos = invNxmNivelMovimientos;
	}



}