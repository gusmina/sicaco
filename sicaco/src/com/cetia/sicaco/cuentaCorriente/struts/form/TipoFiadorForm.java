package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.hibernate.CtaTfiTipoFiador;
import com.cetia.sicaco.struts.BasicForm;

public class TipoFiadorForm extends  BasicForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7149307885593084712L;
	private CtaTfiTipoFiador ctatFiadorH = new CtaTfiTipoFiador();
	private boolean mdf;
	
	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isMdf() {
		return mdf;
	}

	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}
	public CtaTfiTipoFiador getCtatFiadorH() {
		return ctatFiadorH;
	}

	public void setCtatFiadorH(CtaTfiTipoFiador ctatFiadorH) {
		this.ctatFiadorH = ctatFiadorH;
	}

	public boolean equals(Object obj) {
		return ctatFiadorH.equals(obj);
	}

	public Set getCtaFxpFiadorPrestamos() {
		return ctatFiadorH.getCtaFxpFiadorPrestamos();
	}

	public String getTfiDescripcion() {
		return ctatFiadorH.getTfiDescripcion();
	}

	public Integer getTfiId() {
		return ctatFiadorH.getTfiId();
	}

	public String getTfiNombre() {
		return ctatFiadorH.getTfiNombre();
	}


	public void setCtaFxpFiadorPrestamos(Set ctaFxpFiadorPrestamos) {
		ctatFiadorH.setCtaFxpFiadorPrestamos(ctaFxpFiadorPrestamos);
	}

	public void setTfiDescripcion(String tfiDescripcion) {
		ctatFiadorH.setTfiDescripcion(tfiDescripcion);
	}

	public void setTfiId(Integer tfiId) {
		ctatFiadorH.setTfiId(tfiId);
	}

	public void setTfiNombre(String tfiNombre) {
		ctatFiadorH.setTfiNombre(tfiNombre);
	}

}
