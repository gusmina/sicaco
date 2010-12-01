package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCtaCntCodigosAnteriores entity provides the base persistence
 * definition of the CtaCntCodigosAnteriores entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaCntCodigosAnteriores implements
		java.io.Serializable {

	// Fields

	private Integer cntId;
	private CtaAscAsociado ctaAscAsociado = new CtaAscAsociado();
	private String cntCod;
	private Date cntFechaIni;
	private Date cntFechaFin;

	// Constructors

	/** default constructor */
	public AbstractCtaCntCodigosAnteriores() {
	}

	/** minimal constructor */
	public AbstractCtaCntCodigosAnteriores(Integer cntId, String cntCod,
			Date cntFechaIni) {
		this.cntId = cntId;
		this.cntCod = cntCod;
		this.cntFechaIni = cntFechaIni;
	}

	/** full constructor */
	public AbstractCtaCntCodigosAnteriores(Integer cntId,
			CtaAscAsociado ctaAscAsociado, String cntCod, Date cntFechaIni,
			Date cntFechaFin) {
		this.cntId = cntId;
		this.ctaAscAsociado = ctaAscAsociado;
		this.cntCod = cntCod;
		this.cntFechaIni = cntFechaIni;
		this.cntFechaFin = cntFechaFin;
	}

	// Property accessors

	public Integer getCntId() {
		return this.cntId;
	}

	public void setCntId(Integer cntId) {
		this.cntId = cntId;
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return this.ctaAscAsociado;
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		this.ctaAscAsociado = ctaAscAsociado;
	}

	public String getCntCod() {
		return this.cntCod;
	}

	public void setCntCod(String cntCod) {
		this.cntCod = cntCod;
	}

	public Date getCntFechaIni() {
		return this.cntFechaIni;
	}

	public void setCntFechaIni(Date cntFechaIni) {
		this.cntFechaIni = cntFechaIni;
	}

	public Date getCntFechaFin() {
		return this.cntFechaFin;
	}

	public void setCntFechaFin(Date cntFechaFin) {
		this.cntFechaFin = cntFechaFin;
	}

}