package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCtaHtsHistorialTasa entity provides the base persistence definition
 * of the CtaHtsHistorialTasa entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaHtsHistorialTasa implements
		java.io.Serializable {

	// Fields

	private Integer htsId;
	private CtaTinTasaInteres ctaTinTasaInteres = new CtaTinTasaInteres();
	private Double htsTasa;
	private Date htsFechaIni;
	private Date htsFechaFin;

	// Constructors

	/** default constructor */
	public AbstractCtaHtsHistorialTasa() {
	}

	/** minimal constructor */
	public AbstractCtaHtsHistorialTasa(Double htsTasa, Date htsFechaIni,
			Date htsFechaFin) {
		this.htsTasa = htsTasa;
		this.htsFechaIni = htsFechaIni;
		this.htsFechaFin = htsFechaFin;
	}

	/** full constructor */
	public AbstractCtaHtsHistorialTasa(CtaTinTasaInteres ctaTinTasaInteres,
			Double htsTasa, Date htsFechaIni, Date htsFechaFin) {
		this.ctaTinTasaInteres = ctaTinTasaInteres;
		this.htsTasa = htsTasa;
		this.htsFechaIni = htsFechaIni;
		this.htsFechaFin = htsFechaFin;
	}

	// Property accessors

	public Integer getHtsId() {
		return this.htsId;
	}

	public void setHtsId(Integer htsId) {
		this.htsId = htsId;
	}

	public CtaTinTasaInteres getCtaTinTasaInteres() {
		return this.ctaTinTasaInteres;
	}

	public void setCtaTinTasaInteres(CtaTinTasaInteres ctaTinTasaInteres) {
		this.ctaTinTasaInteres = ctaTinTasaInteres;
	}

	public Double getHtsTasa() {
		return this.htsTasa;
	}

	public void setHtsTasa(Double htsTasa) {
		this.htsTasa = htsTasa;
	}

	public Date getHtsFechaIni() {
		return this.htsFechaIni;
	}

	public void setHtsFechaIni(Date htsFechaIni) {
		this.htsFechaIni = htsFechaIni;
	}

	public Date getHtsFechaFin() {
		return this.htsFechaFin;
	}

	public void setHtsFechaFin(Date htsFechaFin) {
		this.htsFechaFin = htsFechaFin;
	}

}