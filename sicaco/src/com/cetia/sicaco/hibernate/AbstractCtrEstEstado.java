package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtrEstEstado entity provides the base persistence definition of the
 * CtrEstEstado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrEstEstado implements java.io.Serializable {

	// Fields

	private Integer estId;
	private CtrTusTipoUso ctrTusTipoUso = new CtrTusTipoUso();
	private String estNombre;
	private Set facFenFacturaEncabezados = new HashSet(0);
	private Set ctaCahCuentaAhorros = new HashSet(0);
	private Set ctaAscAsociados = new HashSet(0);
	private Set ctaSegSeguroses = new HashSet(0);
	private Set ctrPaiPaises = new HashSet(0);
	private Set ctaCasCuentaAsociado = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtrEstEstado() {
	}

	/** minimal constructor */
	public AbstractCtrEstEstado(Integer estId, String estNombre) {
		this.estId = estId;
		this.estNombre = estNombre;
	}

	/** full constructor */
	public AbstractCtrEstEstado(Integer estId, CtrTusTipoUso ctrTusTipoUso,
			String estNombre, Set facFenFacturaEncabezados,
			Set ctaCahCuentaAhorros, Set ctaAscAsociados, Set ctaSegSeguroses,
			Set ctrPaiPaises, Set ctaCasCuentaAsociado) {
		super();
		this.estId = estId;
		this.ctrTusTipoUso = ctrTusTipoUso;
		this.estNombre = estNombre;
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
		this.ctaCahCuentaAhorros = ctaCahCuentaAhorros;
		this.ctaAscAsociados = ctaAscAsociados;
		this.ctaSegSeguroses = ctaSegSeguroses;
		this.ctrPaiPaises = ctrPaiPaises;
		this.ctaCasCuentaAsociado = ctaCasCuentaAsociado;
	}
	
	// Property accessors

	public Integer getEstId() {
		return this.estId;
	}

	public void setEstId(Integer estId) {
		this.estId = estId;
	}

	public CtrTusTipoUso getCtrTusTipoUso() {
		return this.ctrTusTipoUso;
	}

	public void setCtrTusTipoUso(CtrTusTipoUso ctrTusTipoUso) {
		this.ctrTusTipoUso = ctrTusTipoUso;
	}

	public String getEstNombre() {
		return this.estNombre;
	}

	public void setEstNombre(String estNombre) {
		this.estNombre = estNombre;
	}

	public Set getFacFenFacturaEncabezados() {
		return this.facFenFacturaEncabezados;
	}

	public void setFacFenFacturaEncabezados(Set facFenFacturaEncabezados) {
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
	}

	public Set getCtrPaiPaises() {
		return this.ctrPaiPaises;
	}

	public void setCtrPaiPaises(Set ctrPaiPaises) {
		this.ctrPaiPaises = ctrPaiPaises;
	}

	public Set getCtaAscAsociados() {
		return ctaAscAsociados;
	}

	public void setCtaAscAsociados(Set ctaAscAsociados) {
		this.ctaAscAsociados = ctaAscAsociados;
	}

	public Set getCtaCahCuentaAhorros() {
		return ctaCahCuentaAhorros;
	}

	public void setCtaCahCuentaAhorros(Set ctaCahCuentaAhorros) {
		this.ctaCahCuentaAhorros = ctaCahCuentaAhorros;
	}

	public Set getCtaSegSeguroses() {
		return ctaSegSeguroses;
	}

	public void setCtaSegSeguroses(Set ctaSegSeguroses) {
		this.ctaSegSeguroses = ctaSegSeguroses;
	}

	public Set getCtaCasCuentaAsociado() {
		return ctaCasCuentaAsociado;
	}

	public void setCtaCasCuentaAsociado(Set ctaCasCuentaAsociado) {
		this.ctaCasCuentaAsociado = ctaCasCuentaAsociado;
	}

}