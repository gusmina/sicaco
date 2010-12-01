package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractConCueCuenta entity provides the base persistence definition of the
 * ConCueCuenta entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConCueCuenta implements java.io.Serializable {

	// Fields

	private Integer cueId;
	private ConTicTipoCuenta conTicTipoCuenta=new ConTicTipoCuenta();
	private ConCueCuenta conCueCuenta;
	private String cueCodigoCuenta;
	private byte cueTipoCuenta;
	private String cueNombre;
	private double cueSaldoActual;
	private byte cuePosteable;
	private String cueDescripcion;
	private Integer cueEstado;
	private float cuePorcentaje;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set conCueCuentas = new HashSet(0);
	private Set conDpaDetallePartidas = new HashSet(0);
	private Set conSacSaldosAnterioresCuentas = new HashSet(0);
	private Set conMxcModuloxCuentacontables = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractConCueCuenta() {
	}

	/** minimal constructor */
	public AbstractConCueCuenta(ConTicTipoCuenta conTicTipoCuenta,
			String cueCodigoCuenta, byte cueTipoCuenta, String cueNombre,
			double cueSaldoActual, Integer cueEstado, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.conTicTipoCuenta = conTicTipoCuenta;
		this.cueCodigoCuenta = cueCodigoCuenta;
		this.cueTipoCuenta = cueTipoCuenta;
		this.cueNombre = cueNombre;
		this.cueSaldoActual = cueSaldoActual;
		this.cueEstado = cueEstado;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractConCueCuenta(ConTicTipoCuenta conTicTipoCuenta,
			ConCueCuenta conCueCuenta, String cueCodigoCuenta,
			byte cueTipoCuenta, String cueNombre, double cueSaldoActual,
			byte cuePosteable, String cueDescripcion, Integer cueEstado,
			float cuePorcentaje, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set conCueCuentas,
			Set conDpaDetallePartidas, Set conSacSaldosAnterioresCuentas,
			Set conMxcModuloxCuentacontables) {
		this.conTicTipoCuenta = conTicTipoCuenta;
		this.conCueCuenta = conCueCuenta;
		this.cueCodigoCuenta = cueCodigoCuenta;
		this.cueTipoCuenta = cueTipoCuenta;
		this.cueNombre = cueNombre;
		this.cueSaldoActual = cueSaldoActual;
		this.cuePosteable = cuePosteable;
		this.cueDescripcion = cueDescripcion;
		this.cueEstado = cueEstado;
		this.cuePorcentaje = cuePorcentaje;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.conCueCuentas = conCueCuentas;
		this.conDpaDetallePartidas = conDpaDetallePartidas;
		this.conSacSaldosAnterioresCuentas = conSacSaldosAnterioresCuentas;
		this.conMxcModuloxCuentacontables = conMxcModuloxCuentacontables;
	}

	// Property accessors

	public Integer getCueId() {
		return this.cueId;
	}

	public void setCueId(Integer cueId) {
		this.cueId = cueId;
	}

	public ConTicTipoCuenta getConTicTipoCuenta() {
		return this.conTicTipoCuenta;
	}

	public void setConTicTipoCuenta(ConTicTipoCuenta conTicTipoCuenta) {
		this.conTicTipoCuenta = conTicTipoCuenta;
	}

	public ConCueCuenta getConCueCuenta() {
		return this.conCueCuenta;
	}

	public void setConCueCuenta(ConCueCuenta conCueCuenta) {
		this.conCueCuenta = conCueCuenta;
	}

	public String getCueCodigoCuenta() {
		return this.cueCodigoCuenta;
	}

	public void setCueCodigoCuenta(String cueCodigoCuenta) {
		this.cueCodigoCuenta = cueCodigoCuenta;
	}

	public byte getCueTipoCuenta() {
		return this.cueTipoCuenta;
	}

	public void setCueTipoCuenta(byte cueTipoCuenta) {
		this.cueTipoCuenta = cueTipoCuenta;
	}

	public String getCueNombre() {
		return this.cueNombre;
	}

	public void setCueNombre(String cueNombre) {
		this.cueNombre = cueNombre;
	}

	public double getCueSaldoActual() {
		return this.cueSaldoActual;
	}

	public void setCueSaldoActual(double cueSaldoActual) {
		this.cueSaldoActual = cueSaldoActual;
	}

	public byte getCuePosteable() {
		return this.cuePosteable;
	}

	public void setCuePosteable(byte cuePosteable) {
		this.cuePosteable = cuePosteable;
	}

	public String getCueDescripcion() {
		return this.cueDescripcion;
	}

	public void setCueDescripcion(String cueDescripcion) {
		this.cueDescripcion = cueDescripcion;
	}

	public Integer getCueEstado() {
		return this.cueEstado;
	}

	public void setCueEstado(Integer cueEstado) {
		this.cueEstado = cueEstado;
	}

	public float getCuePorcentaje() {
		return this.cuePorcentaje;
	}

	public void setCuePorcentaje(float cuePorcentaje) {
		this.cuePorcentaje = cuePorcentaje;
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

	public Set getConCueCuentas() {
		return this.conCueCuentas;
	}

	public void setConCueCuentas(Set conCueCuentas) {
		this.conCueCuentas = conCueCuentas;
	}

	public Set getConDpaDetallePartidas() {
		return this.conDpaDetallePartidas;
	}

	public void setConDpaDetallePartidas(Set conDpaDetallePartidas) {
		this.conDpaDetallePartidas = conDpaDetallePartidas;
	}

	public Set getConSacSaldosAnterioresCuentas() {
		return this.conSacSaldosAnterioresCuentas;
	}

	public void setConSacSaldosAnterioresCuentas(
			Set conSacSaldosAnterioresCuentas) {
		this.conSacSaldosAnterioresCuentas = conSacSaldosAnterioresCuentas;
	}

	public Set getConMxcModuloxCuentacontables() {
		return this.conMxcModuloxCuentacontables;
	}

	public void setConMxcModuloxCuentacontables(Set conMxcModuloxCuentacontables) {
		this.conMxcModuloxCuentacontables = conMxcModuloxCuentacontables;
	}

}