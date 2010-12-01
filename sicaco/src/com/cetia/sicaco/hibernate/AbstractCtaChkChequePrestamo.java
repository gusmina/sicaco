package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaChkChequePrestamo entity provides the base persistence definition
 * of the CtaChkChequePrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaChkChequePrestamo implements
		java.io.Serializable {

	// Fields

	private Integer chkId;
	private CtaPrePrestamo ctaPrePrestamo = new CtaPrePrestamo();
	private CtrBanBanco ctrBanBanco = new CtrBanBanco();
	private String chkEmitidoA;
	private Float chkMontoEmitido;
	private String chkRazon;
	private Integer chkCorrelativoCheque;
	private String chkLugar;
	private Date chkFecha;
	private Set conPcoPartidaContables = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaChkChequePrestamo() {
	}

	/** minimal constructor */
	public AbstractCtaChkChequePrestamo(String chkEmitidoA,
			Float chkMontoEmitido, Integer chkCorrelativoCheque,
			String chkLugar, Date chkFecha) {
		this.chkEmitidoA = chkEmitidoA;
		this.chkMontoEmitido = chkMontoEmitido;
		this.chkCorrelativoCheque = chkCorrelativoCheque;
		this.chkLugar = chkLugar;
		this.chkFecha = chkFecha;
	}

	/** full constructor */
	public AbstractCtaChkChequePrestamo(CtaPrePrestamo ctaPrePrestamo,
			CtrBanBanco ctrBanBanco, String chkEmitidoA, Float chkMontoEmitido,
			String chkRazon, Integer chkCorrelativoCheque, String chkLugar,
			Date chkFecha, Set conPcoPartidaContables) {
		this.ctaPrePrestamo = ctaPrePrestamo;
		this.ctrBanBanco = ctrBanBanco;
		this.chkEmitidoA = chkEmitidoA;
		this.chkMontoEmitido = chkMontoEmitido;
		this.chkRazon = chkRazon;
		this.chkCorrelativoCheque = chkCorrelativoCheque;
		this.chkLugar = chkLugar;
		this.chkFecha = chkFecha;
		this.conPcoPartidaContables = conPcoPartidaContables;
	}

	// Property accessors

	public Integer getChkId() {
		return this.chkId;
	}

	public void setChkId(Integer chkId) {
		this.chkId = chkId;
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return this.ctaPrePrestamo;
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		this.ctaPrePrestamo = ctaPrePrestamo;
	}

	public CtrBanBanco getCtrBanBanco() {
		return this.ctrBanBanco;
	}

	public void setCtrBanBanco(CtrBanBanco ctrBanBanco) {
		this.ctrBanBanco = ctrBanBanco;
	}

	public String getChkEmitidoA() {
		return this.chkEmitidoA;
	}

	public void setChkEmitidoA(String chkEmitidoA) {
		this.chkEmitidoA = chkEmitidoA;
	}

	public Float getChkMontoEmitido() {
		return this.chkMontoEmitido;
	}

	public void setChkMontoEmitido(Float chkMontoEmitido) {
		this.chkMontoEmitido = chkMontoEmitido;
	}

	public String getChkRazon() {
		return this.chkRazon;
	}

	public void setChkRazon(String chkRazon) {
		this.chkRazon = chkRazon;
	}

	public Integer getChkCorrelativoCheque() {
		return this.chkCorrelativoCheque;
	}

	public void setChkCorrelativoCheque(Integer chkCorrelativoCheque) {
		this.chkCorrelativoCheque = chkCorrelativoCheque;
	}

	public String getChkLugar() {
		return this.chkLugar;
	}

	public void setChkLugar(String chkLugar) {
		this.chkLugar = chkLugar;
	}

	public Date getChkFecha() {
		return this.chkFecha;
	}

	public void setChkFecha(Date chkFecha) {
		this.chkFecha = chkFecha;
	}

	public Set getConPcoPartidaContables() {
		return this.conPcoPartidaContables;
	}

	public void setConPcoPartidaContables(Set conPcoPartidaContables) {
		this.conPcoPartidaContables = conPcoPartidaContables;
	}

}