package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCtaInaIngresosxasociado entity provides the base persistence
 * definition of the CtaInaIngresosxasociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaInaIngresosxasociado implements
		java.io.Serializable {

	// Fields

	private Integer inaId;
	private CtaNotNotas ctaNotNotas = new CtaNotNotas();
	private CtaAscAsociado ctaAscAsociado = new CtaAscAsociado();
	private Date inaFechaIngreso;
	private Date inaFechaSalida;

	// Constructors

	/** default constructor */
	public AbstractCtaInaIngresosxasociado() {
	}

	/** minimal constructor */
	public AbstractCtaInaIngresosxasociado(Integer inaId, Date inaFechaIngreso) {
		this.inaId = inaId;
		this.inaFechaIngreso = inaFechaIngreso;
	}

	/** full constructor */
	public AbstractCtaInaIngresosxasociado(Integer inaId,
			CtaNotNotas ctaNotNotas, CtaAscAsociado ctaAscAsociado,
			Date inaFechaIngreso, Date inaFechaSalida) {
		this.inaId = inaId;
		this.ctaNotNotas = ctaNotNotas;
		this.ctaAscAsociado = ctaAscAsociado;
		this.inaFechaIngreso = inaFechaIngreso;
		this.inaFechaSalida = inaFechaSalida;
	}

	// Property accessors

	public Integer getInaId() {
		return this.inaId;
	}

	public void setInaId(Integer inaId) {
		this.inaId = inaId;
	}

	public CtaNotNotas getCtaNotNotas() {
		return this.ctaNotNotas;
	}

	public void setCtaNotNotas(CtaNotNotas ctaNotNotas) {
		this.ctaNotNotas = ctaNotNotas;
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return this.ctaAscAsociado;
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		this.ctaAscAsociado = ctaAscAsociado;
	}

	public Date getInaFechaIngreso() {
		return this.inaFechaIngreso;
	}

	public void setInaFechaIngreso(Date inaFechaIngreso) {
		this.inaFechaIngreso = inaFechaIngreso;
	}

	public Date getInaFechaSalida() {
		return this.inaFechaSalida;
	}

	public void setInaFechaSalida(Date inaFechaSalida) {
		this.inaFechaSalida = inaFechaSalida;
	}

}