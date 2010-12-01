package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractConMxcModuloxCuentacontable entity provides the base persistence
 * definition of the ConMxcModuloxCuentacontable entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConMxcModuloxCuentacontable implements
		java.io.Serializable {

	// Fields

	private long cxcId;
	private ConCpaConceptoPartida conCpaConceptoPartida = new ConCpaConceptoPartida();
	private ConCueCuenta conCueCuenta = new ConCueCuenta();;
	private String cxcParametrosUnion;
	private byte cxcCargoAbono;
	private Date cxcFechaRelacion;
	private String cxaConceptoExtra;

	// Constructors

	/** default constructor */
	public AbstractConMxcModuloxCuentacontable() {
	}

	/** minimal constructor */
	public AbstractConMxcModuloxCuentacontable(long cxcId,
			ConCueCuenta conCueCuenta, String cxcParametrosUnion) {
		this.cxcId = cxcId;
		this.conCueCuenta = conCueCuenta;
		this.cxcParametrosUnion = cxcParametrosUnion;
	}

	/** full constructor */
	public AbstractConMxcModuloxCuentacontable(long cxcId,
			ConCpaConceptoPartida conCpaConceptoPartida,
			ConCueCuenta conCueCuenta, String cxcParametrosUnion,
			byte cxcCargoAbono, Date cxcFechaRelacion, String cxaConceptoExtra) {
		this.cxcId = cxcId;
		this.conCpaConceptoPartida = conCpaConceptoPartida;
		this.conCueCuenta = conCueCuenta;
		this.cxcParametrosUnion = cxcParametrosUnion;
		this.cxcCargoAbono = cxcCargoAbono;
		this.cxcFechaRelacion = cxcFechaRelacion;
		this.cxaConceptoExtra = cxaConceptoExtra;
	}

	// Property accessors

	public long getCxcId() {
		return this.cxcId;
	}

	public void setCxcId(long cxcId) {
		this.cxcId = cxcId;
	}

	public ConCpaConceptoPartida getConCpaConceptoPartida() {
		return this.conCpaConceptoPartida;
	}

	public void setConCpaConceptoPartida(
			ConCpaConceptoPartida conCpaConceptoPartida) {
		this.conCpaConceptoPartida = conCpaConceptoPartida;
	}

	public ConCueCuenta getConCueCuenta() {
		return this.conCueCuenta;
	}

	public void setConCueCuenta(ConCueCuenta conCueCuenta) {
		this.conCueCuenta = conCueCuenta;
	}

	public String getCxcParametrosUnion() {
		return this.cxcParametrosUnion;
	}

	public void setCxcParametrosUnion(String cxcParametrosUnion) {
		this.cxcParametrosUnion = cxcParametrosUnion;
	}

	public byte getCxcCargoAbono() {
		return this.cxcCargoAbono;
	}

	public void setCxcCargoAbono(byte cxcCargoAbono) {
		this.cxcCargoAbono = cxcCargoAbono;
	}

	public Date getCxcFechaRelacion() {
		return this.cxcFechaRelacion;
	}

	public void setCxcFechaRelacion(Date cxcFechaRelacion) {
		this.cxcFechaRelacion = cxcFechaRelacion;
	}

	public String getCxaConceptoExtra() {
		return this.cxaConceptoExtra;
	}

	public void setCxaConceptoExtra(String cxaConceptoExtra) {
		this.cxaConceptoExtra = cxaConceptoExtra;
	}

}