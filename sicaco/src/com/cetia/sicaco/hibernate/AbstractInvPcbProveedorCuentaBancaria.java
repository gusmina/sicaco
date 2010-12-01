package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvPcbProveedorCuentaBancaria entity provides the base persistence
 * definition of the InvPcbProveedorCuentaBancaria entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvPcbProveedorCuentaBancaria implements
		java.io.Serializable {

	// Fields

	private InvPcbProveedorCuentaBancariaId id;
	private CtaTcuTipoCuenta ctaTcuTipoCuenta = new CtaTcuTipoCuenta();
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set ctaStbSolTransBancs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvPcbProveedorCuentaBancaria() {
	}

	/** full constructor */
	public AbstractInvPcbProveedorCuentaBancaria(
			InvPcbProveedorCuentaBancariaId id, CtaTcuTipoCuenta ctaTcuTipoCuenta,
			Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set ctaStbSolTransBancs ) {
		this.id = id;
		this.ctaTcuTipoCuenta = ctaTcuTipoCuenta;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.ctaStbSolTransBancs = ctaStbSolTransBancs;
	}

	// Property accessors

	public InvPcbProveedorCuentaBancariaId getId() {
		return this.id;
	}

	public void setId(InvPcbProveedorCuentaBancariaId id) {
		this.id = id;
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

	public CtaTcuTipoCuenta getCtaTcuTipoCuenta() {
		return ctaTcuTipoCuenta;
	}

	public void setCtaTcuTipoCuenta(CtaTcuTipoCuenta ctaTcuTipoCuenta) {
		this.ctaTcuTipoCuenta = ctaTcuTipoCuenta;
	}

	public Set getCtaStbSolTransBancs() {
		return ctaStbSolTransBancs;
	}

	public void setCtaStbSolTransBancs(Set ctaStbSolTransBancs) {
		this.ctaStbSolTransBancs = ctaStbSolTransBancs;
	}

}