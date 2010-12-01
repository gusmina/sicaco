package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaResCuentaResumenes entity provides the base persistence definition
 * of the CtaResCuentaResumenes entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaResCuentaResumenes implements
		java.io.Serializable {

	// Fields

	private Long resId;
	private String resCuentaNom;
	private Double resSaldoAnt;
	private Double resSaldoAct;
	private String resLinea;

	// Constructors

	/** default constructor */
	public AbstractCtaResCuentaResumenes() {
	}

	/** full constructor */
	public AbstractCtaResCuentaResumenes(String resCuentaNom,
			Double resSaldoAnt, Double resSaldoAct, String resLinea) {
		this.resCuentaNom = resCuentaNom;
		this.resSaldoAnt = resSaldoAnt;
		this.resSaldoAct = resSaldoAct;
		this.resLinea = resLinea;
	}

	// Property accessors

	public Long getResId() {
		return this.resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	public String getResCuentaNom() {
		return this.resCuentaNom;
	}

	public void setResCuentaNom(String resCuentaNom) {
		this.resCuentaNom = resCuentaNom;
	}

	public Double getResSaldoAnt() {
		return this.resSaldoAnt;
	}

	public void setResSaldoAnt(Double resSaldoAnt) {
		this.resSaldoAnt = resSaldoAnt;
	}

	public Double getResSaldoAct() {
		return this.resSaldoAct;
	}

	public void setResSaldoAct(Double resSaldoAct) {
		this.resSaldoAct = resSaldoAct;
	}

	public String getResLinea() {
		return this.resLinea;
	}

	public void setResLinea(String resLinea) {
		this.resLinea = resLinea;
	}

}