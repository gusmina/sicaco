package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtrPaiPais entity provides the base persistence definition of the
 * CtrPaiPais entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrPaiPais implements java.io.Serializable {

	// Fields

	private Integer paiId;
	private CtrEstEstado ctrEstEstado=new CtrEstEstado();
	private String paiNombre;
	private String audUsuarioCreacion;
	private Date audFechaCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set invProProveedors = new HashSet(0);
	private Set invBodBodegases = new HashSet(0);
	private Set ctrBanBancos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtrPaiPais() {
	}

	/** minimal constructor */
	public AbstractCtrPaiPais(Integer paiId, CtrEstEstado ctrEstEstado,
			String paiNombre, String audUsuarioCreacion, Date audFechaCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.paiId = paiId;
		this.ctrEstEstado = ctrEstEstado;
		this.paiNombre = paiNombre;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractCtrPaiPais(Integer paiId, CtrEstEstado ctrEstEstado,
			String paiNombre, String audUsuarioCreacion, Date audFechaCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set invProProveedors, Set invBodBodegases, Set ctrBanBancos) {
		this.paiId = paiId;
		this.ctrEstEstado = ctrEstEstado;
		this.paiNombre = paiNombre;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.invProProveedors = invProProveedors;
		this.invBodBodegases = invBodBodegases;
		this.ctrBanBancos = ctrBanBancos;
	}

	// Property accessors

	public Integer getPaiId() {
		return this.paiId;
	}

	public void setPaiId(Integer paiId) {
		this.paiId = paiId;
	}

	public CtrEstEstado getCtrEstEstado() {
		return this.ctrEstEstado;
	}

	public void setCtrEstEstado(CtrEstEstado ctrEstEstado) {
		this.ctrEstEstado = ctrEstEstado;
	}

	public String getPaiNombre() {
		return this.paiNombre;
	}

	public void setPaiNombre(String paiNombre) {
		this.paiNombre = paiNombre;
	}

	public String getAudUsuarioCreacion() {
		return this.audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaCreacion() {
		return this.audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
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

	public Set getInvProProveedors() {
		return this.invProProveedors;
	}

	public void setInvProProveedors(Set invProProveedors) {
		this.invProProveedors = invProProveedors;
	}

	public Set getInvBodBodegases() {
		return this.invBodBodegases;
	}

	public void setInvBodBodegases(Set invBodBodegases) {
		this.invBodBodegases = invBodBodegases;
	}

	public Set getCtrBanBancos() {
		return this.ctrBanBancos;
	}

	public void setCtrBanBancos(Set ctrBanBancos) {
		this.ctrBanBancos = ctrBanBancos;
	}

}