package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtrBanBanco entity provides the base persistence definition of the
 * CtrBanBanco entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrBanBanco implements java.io.Serializable {

	// Fields

	private Integer banId;
	private CtrPaiPais ctrPaiPais=new CtrPaiPais();
	private String banNombre;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set invPcbProveedorCuentaBancarias = new HashSet(0);
	private Set ctrCckControlChequeses = new HashSet(0);
	private Set ctaCbaCuentaBancarias = new HashSet(0);
	private Set ctaChkChequePrestamos = new HashSet(0);

	// Constructors


	public AbstractCtrBanBanco(Integer banId, CtrPaiPais ctrPaiPais,
			String banNombre, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set invPcbProveedorCuentaBancarias, Set ctrCckControlChequeses,
			Set ctaCbaCuentaBancarias, Set ctaChkChequePrestamos) {
		super();
		this.banId = banId;
		this.ctrPaiPais = ctrPaiPais;
		this.banNombre = banNombre;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.invPcbProveedorCuentaBancarias = invPcbProveedorCuentaBancarias;
		this.ctrCckControlChequeses = ctrCckControlChequeses;
		this.ctaCbaCuentaBancarias = ctaCbaCuentaBancarias;
		this.ctaChkChequePrestamos = ctaChkChequePrestamos;
	}

	/** default constructor */
	public AbstractCtrBanBanco() {
	}

	/** minimal constructor */
	public AbstractCtrBanBanco(CtrPaiPais ctrPaiPais, String banNombre,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.ctrPaiPais = ctrPaiPais;
		this.banNombre = banNombre;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */

	// Property accessors

	public Integer getBanId() {
		return this.banId;
	}

	public void setBanId(Integer banId) {
		this.banId = banId;
	}

	public CtrPaiPais getCtrPaiPais() {
		return this.ctrPaiPais;
	}

	public void setCtrPaiPais(CtrPaiPais ctrPaiPais) {
		this.ctrPaiPais = ctrPaiPais;
	}

	public String getBanNombre() {
		return this.banNombre;
	}

	public void setBanNombre(String banNombre) {
		this.banNombre = banNombre;
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

	public Set getInvPcbProveedorCuentaBancarias() {
		return this.invPcbProveedorCuentaBancarias;
	}

	public void setInvPcbProveedorCuentaBancarias(
			Set invPcbProveedorCuentaBancarias) {
		this.invPcbProveedorCuentaBancarias = invPcbProveedorCuentaBancarias;
	}

	public Set getCtrCckControlChequeses() {
		return this.ctrCckControlChequeses;
	}

	public void setCtrCckControlChequeses(Set ctrCckControlChequeses) {
		this.ctrCckControlChequeses = ctrCckControlChequeses;
	}

	public Set getCtaCbaCuentaBancarias() {
		return ctaCbaCuentaBancarias;
	}

	public void setCtaCbaCuentaBancarias(Set ctaCbaCuentaBancarias) {
		this.ctaCbaCuentaBancarias = ctaCbaCuentaBancarias;
	}

	public Set getCtaChkChequePrestamos() {
		return ctaChkChequePrestamos;
	}

	public void setCtaChkChequePrestamos(Set ctaChkChequePrestamos) {
		this.ctaChkChequePrestamos = ctaChkChequePrestamos;
	}

}