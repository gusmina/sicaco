package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaFxpFiadorPrestamo entity provides the base persistence definition
 * of the CtaFxpFiadorPrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaFxpFiadorPrestamo implements
		java.io.Serializable {

	// Fields

	private Integer fxpId;
	private CtaPrePrestamo ctaPrePrestamo = new CtaPrePrestamo();
	private CtaPxtPersonaExterna ctaPxtPersonaExterna = new CtaPxtPersonaExterna();
	private CtaTfiTipoFiador ctaTfiTipoFiador = new CtaTfiTipoFiador();
	private CtaAscAsociado ctaAscAsociado = new CtaAscAsociado();
	private String fxpEstado;

	// Constructors

	/** default constructor */
	public AbstractCtaFxpFiadorPrestamo() {
	}

	/** minimal constructor */
	public AbstractCtaFxpFiadorPrestamo(Integer fxpId,
			CtaPrePrestamo ctaPrePrestamo, String fxpEstado) {
		this.fxpId = fxpId;
		this.ctaPrePrestamo = ctaPrePrestamo;
		this.fxpEstado = fxpEstado;
	}

	/** full constructor */
	public AbstractCtaFxpFiadorPrestamo(Integer fxpId,
			CtaPrePrestamo ctaPrePrestamo,
			CtaPxtPersonaExterna ctaPxtPersonaExterna,
			CtaTfiTipoFiador ctaTfiTipoFiador, CtaAscAsociado ctaAscAsociado,
			String fxpEstado) {
		this.fxpId = fxpId;
		this.ctaPrePrestamo = ctaPrePrestamo;
		this.ctaPxtPersonaExterna = ctaPxtPersonaExterna;
		this.ctaTfiTipoFiador = ctaTfiTipoFiador;
		this.ctaAscAsociado = ctaAscAsociado;
		this.fxpEstado = fxpEstado;
	}

	// Property accessors

	public Integer getFxpId() {
		return this.fxpId;
	}

	public void setFxpId(Integer fxpId) {
		this.fxpId = fxpId;
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return this.ctaPrePrestamo;
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		this.ctaPrePrestamo = ctaPrePrestamo;
	}

	public CtaPxtPersonaExterna getCtaPxtPersonaExterna() {
		return this.ctaPxtPersonaExterna;
	}

	public void setCtaPxtPersonaExterna(
			CtaPxtPersonaExterna ctaPxtPersonaExterna) {
		this.ctaPxtPersonaExterna = ctaPxtPersonaExterna;
	}

	public CtaTfiTipoFiador getCtaTfiTipoFiador() {
		return this.ctaTfiTipoFiador;
	}

	public void setCtaTfiTipoFiador(CtaTfiTipoFiador ctaTfiTipoFiador) {
		this.ctaTfiTipoFiador = ctaTfiTipoFiador;
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return this.ctaAscAsociado;
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		this.ctaAscAsociado = ctaAscAsociado;
	}

	public String getFxpEstado() {
		return this.fxpEstado;
	}

	public void setFxpEstado(String fxpEstado) {
		this.fxpEstado = fxpEstado;
	}

}