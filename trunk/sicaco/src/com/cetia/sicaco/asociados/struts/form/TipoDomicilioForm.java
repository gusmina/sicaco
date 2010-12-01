package com.cetia.sicaco.asociados.struts.form;

import java.util.Set;

import com.cetia.sicaco.hibernate.CtaDomDomicilio;
import com.cetia.sicaco.struts.BasicForm;

public class TipoDomicilioForm extends BasicForm {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2223155465762671661L;
	
	private CtaDomDomicilio domicilioH = new CtaDomDomicilio();
	private boolean mdf;
	

	
	public CtaDomDomicilio getDomicilioH() {
		return domicilioH;
	}


	public void setDomicilioH(CtaDomDomicilio domicilioH) {
		this.domicilioH = domicilioH;
	}





	public boolean isMdf() {
		return mdf;
	}





	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}





	public Set getCtaAscAsociados() {
		return domicilioH.getCtaAscAsociados();
	}


	public String getDomDescripcion() {
		return domicilioH.getDomDescripcion();
	}


	public Integer getDomId() {
		return domicilioH.getDomId();
	}


	public String getDomNombre() {
		return domicilioH.getDomNombre();
	}


	public void setCtaAscAsociados(Set ctaAscAsociados) {
		domicilioH.setCtaAscAsociados(ctaAscAsociados);
	}


	public void setDomDescripcion(String domDescripcion) {
		domicilioH.setDomDescripcion(domDescripcion);
	}


	public void setDomId(Integer domId) {
		domicilioH.setDomId(domId);
	}


	public void setDomNombre(String domNombre) {
		domicilioH.setDomNombre(domNombre);
	}


	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
