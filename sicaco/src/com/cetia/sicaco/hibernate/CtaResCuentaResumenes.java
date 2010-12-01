package com.cetia.sicaco.hibernate;

/**
 * CtaResCuentaResumenes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaResCuentaResumenes extends AbstractCtaResCuentaResumenes
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaResCuentaResumenes() {
	}

	/** full constructor */
	public CtaResCuentaResumenes(String resCuentaNom, Double resSaldoAnt,
			Double resSaldoAct, String resLinea) {
		super(resCuentaNom, resSaldoAnt, resSaldoAct, resLinea);
	}

}
