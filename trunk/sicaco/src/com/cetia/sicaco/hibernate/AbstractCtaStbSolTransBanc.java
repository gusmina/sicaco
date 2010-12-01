package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCtaStbSolTransBanc entity provides the base persistence definition of
 * the CtaStbSolTransBanc entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaStbSolTransBanc implements
		java.io.Serializable {

	// Fields

	private Integer stbId;
	private CtaCbaCuentaBancaria ctaCbaCuentaBancaria = new CtaCbaCuentaBancaria();
	private CtaCahCuentaAhorro ctaCahCuentaAhorro = new CtaCahCuentaAhorro();
	private InvPcbProveedorCuentaBancaria invPcbProveedorCuentaBancaria = new InvPcbProveedorCuentaBancaria();
	private String stbRazon;
	private Date stbFechaSol;
	private Double stbMonto;
	private String stbNombreAsociado;
	private String stbEstado;
	private String stbTipoAhorro;
	private Integer opaId;
	private String preId;
	private Double stbPenalidad;

	// Constructors

	/** default constructor */
	public AbstractCtaStbSolTransBanc() {
	}

	/** minimal constructor */
	public AbstractCtaStbSolTransBanc(Integer stbId, String stbRazon,
			Date stbFechaSol, Double stbMonto) {
		this.stbId = stbId;
		this.stbRazon = stbRazon;
		this.stbFechaSol = stbFechaSol;
		this.stbMonto = stbMonto;
	}


	// Property accessors

	public AbstractCtaStbSolTransBanc(Integer stbId,
			CtaCbaCuentaBancaria ctaCbaCuentaBancaria,
			CtaCahCuentaAhorro ctaCahCuentaAhorro, String stbRazon,
			Date stbFechaSol, Double stbMonto, String stbNombreAsociado,
			String stbEstado, String stbTipoAhorro, Integer opaId, String preId,
			InvPcbProveedorCuentaBancaria invPcbProveedorCuentaBancaria, Double stbPenalidad) {
		super();
		this.stbId = stbId;
		this.ctaCbaCuentaBancaria = ctaCbaCuentaBancaria;
		this.ctaCahCuentaAhorro = ctaCahCuentaAhorro;
		this.stbRazon = stbRazon;
		this.stbFechaSol = stbFechaSol;
		this.stbMonto = stbMonto;
		this.stbNombreAsociado = stbNombreAsociado;
		this.stbEstado = stbEstado;
		this.stbTipoAhorro = stbTipoAhorro;
		this.opaId = opaId;
		this.preId = preId;
		this.invPcbProveedorCuentaBancaria = invPcbProveedorCuentaBancaria;
		this.stbPenalidad = stbPenalidad;
	}

	public Integer getStbId() {
		return this.stbId;
	}

	public void setStbId(Integer stbId) {
		this.stbId = stbId;
	}

	public CtaCbaCuentaBancaria getCtaCbaCuentaBancaria() {
		return this.ctaCbaCuentaBancaria;
	}

	public void setCtaCbaCuentaBancaria(
			CtaCbaCuentaBancaria ctaCbaCuentaBancaria) {
		this.ctaCbaCuentaBancaria = ctaCbaCuentaBancaria;
	}

	public CtaCahCuentaAhorro getCtaCahCuentaAhorro() {
		return ctaCahCuentaAhorro;
	}

	public void setCtaCahCuentaAhorro(CtaCahCuentaAhorro ctaCahCuentaAhorro) {
		this.ctaCahCuentaAhorro = ctaCahCuentaAhorro;
	}

	public String getStbRazon() {
		return this.stbRazon;
	}

	public void setStbRazon(String stbRazon) {
		this.stbRazon = stbRazon;
	}

	public Date getStbFechaSol() {
		return this.stbFechaSol;
	}

	public void setStbFechaSol(Date stbFechaSol) {
		this.stbFechaSol = stbFechaSol;
	}

	public Double getStbMonto() {
		return this.stbMonto;
	}

	public void setStbMonto(Double stbMonto) {
		this.stbMonto = stbMonto;
	}

	public String getStbNombreAsociado() {
		return stbNombreAsociado;
	}

	public void setStbNombreAsociado(String stbNombreAsociado) {
		this.stbNombreAsociado = stbNombreAsociado;
	}

	public String getStbEstado() {
		return stbEstado;
	}

	public void setStbEstado(String stbEstado) {
		this.stbEstado = stbEstado;
	}

	public String getStbTipoAhorro() {
		return stbTipoAhorro;
	}

	public void setStbTipoAhorro(String stbTipoAhorro) {
		this.stbTipoAhorro = stbTipoAhorro;
	}

	public InvPcbProveedorCuentaBancaria getInvPcbProveedorCuentaBancaria() {
		return invPcbProveedorCuentaBancaria;
	}

	public void setInvPcbProveedorCuentaBancaria(
			InvPcbProveedorCuentaBancaria invPcbProveedorCuentaBancaria) {
		this.invPcbProveedorCuentaBancaria = invPcbProveedorCuentaBancaria;
	}

	public Integer getOpaId() {
		return opaId;
	}

	public void setOpaId(Integer opaId) {
		this.opaId = opaId;
	}

	public String getPreId() {
		return preId;
	}

	public void setPreId(String preId) {
		this.preId = preId;
	}

	public Double getStbPenalidad() {
		return stbPenalidad;
	}

	public void setStbPenalidad(Double stbPenalidad) {
		this.stbPenalidad = stbPenalidad;
	}

}