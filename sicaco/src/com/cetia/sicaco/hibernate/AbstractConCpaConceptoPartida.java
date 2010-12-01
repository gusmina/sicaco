package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractConCpaConceptoPartida entity provides the base persistence definition
 * of the ConCpaConceptoPartida entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConCpaConceptoPartida implements
		java.io.Serializable {

	// Fields

	private Integer cpaId;
	private CtaTtrTipoTransaccion ctaTtrTipoTransaccion = new CtaTtrTipoTransaccion();
	private String cpaConcepto;
	private byte cpaDescripcionConcepto;
	private Set conPcoPartidaContables = new HashSet(0);
	private Set conMxcModuloxCuentacontables = new HashSet(0);
	private Set conDpaDetallePartidas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractConCpaConceptoPartida() {
	}

	/** minimal constructor */
	public AbstractConCpaConceptoPartida(Integer cpaId, String cpaConcepto,
			byte cpaDescripcionConcepto) {
		this.cpaId = cpaId;
		this.cpaConcepto = cpaConcepto;
		this.cpaDescripcionConcepto = cpaDescripcionConcepto;
	}

	/** full constructor */
	public AbstractConCpaConceptoPartida(Integer cpaId,
			CtaTtrTipoTransaccion ctaTtrTipoTransaccion, String cpaConcepto,
			byte cpaDescripcionConcepto, Set conPcoPartidaContables,
			Set conMxcModuloxCuentacontables, Set conDpaDetallePartidas) {
		this.cpaId = cpaId;
		this.ctaTtrTipoTransaccion = ctaTtrTipoTransaccion;
		this.cpaConcepto = cpaConcepto;
		this.cpaDescripcionConcepto = cpaDescripcionConcepto;
		this.conPcoPartidaContables = conPcoPartidaContables;
		this.conMxcModuloxCuentacontables = conMxcModuloxCuentacontables;
		this.conDpaDetallePartidas = conDpaDetallePartidas;
	}

	// Property accessors

	public Integer getCpaId() {
		return this.cpaId;
	}

	public void setCpaId(Integer cpaId) {
		this.cpaId = cpaId;
	}

	public CtaTtrTipoTransaccion getCtaTtrTipoTransaccion() {
		return this.ctaTtrTipoTransaccion;
	}

	public void setCtaTtrTipoTransaccion(
			CtaTtrTipoTransaccion ctaTtrTipoTransaccion) {
		this.ctaTtrTipoTransaccion = ctaTtrTipoTransaccion;
	}

	public String getCpaConcepto() {
		return this.cpaConcepto;
	}

	public void setCpaConcepto(String cpaConcepto) {
		this.cpaConcepto = cpaConcepto;
	}

	public byte getCpaDescripcionConcepto() {
		return this.cpaDescripcionConcepto;
	}

	public void setCpaDescripcionConcepto(byte cpaDescripcionConcepto) {
		this.cpaDescripcionConcepto = cpaDescripcionConcepto;
	}

	public Set getConPcoPartidaContables() {
		return this.conPcoPartidaContables;
	}

	public void setConPcoPartidaContables(Set conPcoPartidaContables) {
		this.conPcoPartidaContables = conPcoPartidaContables;
	}

	public Set getConMxcModuloxCuentacontables() {
		return this.conMxcModuloxCuentacontables;
	}

	public void setConMxcModuloxCuentacontables(Set conMxcModuloxCuentacontables) {
		this.conMxcModuloxCuentacontables = conMxcModuloxCuentacontables;
	}

	public Set getConDpaDetallePartidas() {
		return this.conDpaDetallePartidas;
	}

	public void setConDpaDetallePartidas(Set conDpaDetallePartidas) {
		this.conDpaDetallePartidas = conDpaDetallePartidas;
	}

}