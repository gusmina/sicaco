package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaRetRetencionesDeMas entity provides the base persistence
 * definition of the CtaRetRetencionesDeMas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaRetRetencionesDeMas implements
		java.io.Serializable {

	// Fields

	private Long retId;
	private String retCodigoAsc;
	private String retNombreAsc;
	private String retEmp;
	private Double retValorDesc;

	// Constructors

	/** default constructor */
	public AbstractCtaRetRetencionesDeMas() {
	}

	/** full constructor */
	public AbstractCtaRetRetencionesDeMas(String retCodigoAsc,
			String retNombreAsc, String retEmp, Double retValorDesc) {
		this.retCodigoAsc = retCodigoAsc;
		this.retNombreAsc = retNombreAsc;
		this.retEmp = retEmp;
		this.retValorDesc = retValorDesc;
	}

	// Property accessors

	public Long getRetId() {
		return this.retId;
	}

	public void setRetId(Long retId) {
		this.retId = retId;
	}

	public String getRetCodigoAsc() {
		return this.retCodigoAsc;
	}

	public void setRetCodigoAsc(String retCodigoAsc) {
		this.retCodigoAsc = retCodigoAsc;
	}

	public String getRetNombreAsc() {
		return this.retNombreAsc;
	}

	public void setRetNombreAsc(String retNombreAsc) {
		this.retNombreAsc = retNombreAsc;
	}

	public String getRetEmp() {
		return this.retEmp;
	}

	public void setRetEmp(String retEmp) {
		this.retEmp = retEmp;
	}

	public Double getRetValorDesc() {
		return this.retValorDesc;
	}

	public void setRetValorDesc(Double retValorDesc) {
		this.retValorDesc = retValorDesc;
	}

}