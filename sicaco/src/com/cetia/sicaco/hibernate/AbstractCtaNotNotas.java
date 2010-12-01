package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaNotNotas entity provides the base persistence definition of the
 * CtaNotNotas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaNotNotas implements java.io.Serializable {

	// Fields

	private Integer notId;
	private CtaTntTipoNota ctaTntTipoNota;
	private Long casCuenta;
	private String notNota;
	private Date notFecha;
	private String notCampo;
	private Set ctaAscAsociados = new HashSet(0);
	private Set ctaInaIngresosxasociados = new HashSet(0);
	private Set ctaTxaTransaccionxcuentaAsociados = new HashSet(0);
	
	// Constructors

	/** default constructor */
	public AbstractCtaNotNotas() {
	}

	/** minimal constructor */
	public AbstractCtaNotNotas(Integer notId, String notNota, Date notFecha) {
		this.notId = notId;
		this.notNota = notNota;
		this.notFecha = notFecha;
	}

	/** full constructor */
	public AbstractCtaNotNotas(Integer notId, CtaTntTipoNota ctaTntTipoNota,
			Long casCuenta, String notNota,
			Date notFecha, String notCampo, Set ctaAscAsociados, 
			Set ctaInaIngresosxasociados, Set ctaTxaTransaccionxcuentaAsociados) {
		this.notId = notId;
		this.ctaTntTipoNota = ctaTntTipoNota;
		this.casCuenta = casCuenta;
		this.notNota = notNota;
		this.notFecha = notFecha;
		this.notCampo = notCampo;
		this.ctaAscAsociados = ctaAscAsociados;
		this.ctaInaIngresosxasociados = ctaInaIngresosxasociados;
		this.ctaTxaTransaccionxcuentaAsociados = ctaTxaTransaccionxcuentaAsociados;
	}

	// Property accessors

	public Integer getNotId() {
		return this.notId;
	}

	public void setNotId(Integer notId) {
		this.notId = notId;
	}

	public CtaTntTipoNota getCtaTntTipoNota() {
		return this.ctaTntTipoNota;
	}

	public void setCtaTntTipoNota(CtaTntTipoNota ctaTntTipoNota) {
		this.ctaTntTipoNota = ctaTntTipoNota;
	}

	public Long getCasCuenta() {
		return casCuenta;
	}

	public void setCasCuenta(Long casCuenta) {
		this.casCuenta = casCuenta;
	}

	public String getNotNota() {
		return this.notNota;
	}

	public void setNotNota(String notNota) {
		this.notNota = notNota;
	}

	public Date getNotFecha() {
		return this.notFecha;
	}

	public void setNotFecha(Date notFecha) {
		this.notFecha = notFecha;
	}

	public String getNotCampo() {
		return notCampo;
	}

	public void setNotCampo(String notCampo) {
		this.notCampo = notCampo;
	}

	public Set getCtaAscAsociados() {
		return this.ctaAscAsociados;
	}

	public void setCtaAscAsociados(Set ctaAscAsociados) {
		this.ctaAscAsociados = ctaAscAsociados;
	}

	public Set getCtaInaIngresosxasociados() {
		return this.ctaInaIngresosxasociados;
	}

	public void setCtaInaIngresosxasociados(Set ctaInaIngresosxasociados) {
		this.ctaInaIngresosxasociados = ctaInaIngresosxasociados;
	}

	public Set getCtaTxaTransaccionxcuentaAsociados() {
		return ctaTxaTransaccionxcuentaAsociados;
	}

	public void setCtaTxaTransaccionxcuentaAsociados(
			Set ctaTxaTransaccionxcuentaAsociados) {
		this.ctaTxaTransaccionxcuentaAsociados = ctaTxaTransaccionxcuentaAsociados;
	}

}