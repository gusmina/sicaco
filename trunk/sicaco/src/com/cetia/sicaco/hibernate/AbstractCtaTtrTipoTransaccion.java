package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaTtrTipoTransaccion entity provides the base persistence definition
 * of the CtaTtrTipoTransaccion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaTtrTipoTransaccion implements
		java.io.Serializable {

	// Fields

	private Integer ttrId;
	private String ttrNombre;
	private String ttrDescripcion;
	private String ttrUso;
	private Set conCpaConceptoPartidas = new HashSet(0);
	private Set ctaTxaTransaccionxcuentaAsociados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaTtrTipoTransaccion() {
	}

	/** minimal constructor */
	public AbstractCtaTtrTipoTransaccion(Integer ttrId, String ttrNombre,
			String ttrUso) {
		this.ttrId = ttrId;
		this.ttrNombre = ttrNombre;
		this.ttrUso = ttrUso;
	}

	/** full constructor */
	public AbstractCtaTtrTipoTransaccion(Integer ttrId, String ttrNombre,
			String ttrDescripcion, String ttrUso, Set conCpaConceptoPartidas,
			Set ctaTxaTransaccionxcuentaAsociados) {
		this.ttrId = ttrId;
		this.ttrNombre = ttrNombre;
		this.ttrDescripcion = ttrDescripcion;
		this.ttrUso = ttrUso;
		this.conCpaConceptoPartidas = conCpaConceptoPartidas;
		this.ctaTxaTransaccionxcuentaAsociados = ctaTxaTransaccionxcuentaAsociados;
	}

	// Property accessors

	public Integer getTtrId() {
		return this.ttrId;
	}

	public void setTtrId(Integer ttrId) {
		this.ttrId = ttrId;
	}

	public String getTtrNombre() {
		return this.ttrNombre;
	}

	public void setTtrNombre(String ttrNombre) {
		this.ttrNombre = ttrNombre;
	}

	public String getTtrDescripcion() {
		return this.ttrDescripcion;
	}

	public void setTtrDescripcion(String ttrDescripcion) {
		this.ttrDescripcion = ttrDescripcion;
	}

	public String getTtrUso() {
		return this.ttrUso;
	}

	public void setTtrUso(String ttrUso) {
		this.ttrUso = ttrUso;
	}

	public Set getConCpaConceptoPartidas() {
		return this.conCpaConceptoPartidas;
	}

	public void setConCpaConceptoPartidas(Set conCpaConceptoPartidas) {
		this.conCpaConceptoPartidas = conCpaConceptoPartidas;
	}

	public Set getCtaTxaTransaccionxcuentaAsociados() {
		return this.ctaTxaTransaccionxcuentaAsociados;
	}

	public void setCtaTxaTransaccionxcuentaAsociados(
			Set ctaTxaTransaccionxcuentaAsociados) {
		this.ctaTxaTransaccionxcuentaAsociados = ctaTxaTransaccionxcuentaAsociados;
	}

}