package com.cetia.sicaco.asociados.struts.form;

import java.util.Set;

import com.cetia.sicaco.hibernate.CtaTasTipoAsociado;
import com.cetia.sicaco.struts.BasicForm;

public class TipoAsociadoForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2609582270066224936L;

	private CtaTasTipoAsociado tipoAsociadoH = new CtaTasTipoAsociado();
	private boolean mdf;
	
	
	
	public CtaTasTipoAsociado getTipoAsociadoH() {
		return tipoAsociadoH;
	}



	public void setTipoAsociadoH(CtaTasTipoAsociado tipoAsociadoH) {
		this.tipoAsociadoH = tipoAsociadoH;
	}



	public boolean isMdf() {
		return mdf;
	}



	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}

	

	public Set getCtaAscAsociados() {
		return tipoAsociadoH.getCtaAscAsociados();
	}



	public String getTasDescripcion() {
		return tipoAsociadoH.getTasDescripcion();
	}



	public Integer getTasId() {
		return tipoAsociadoH.getTasId();
	}



	public String getTasNombre() {
		return tipoAsociadoH.getTasNombre();
	}



	public void setCtaAscAsociados(Set ctaAscAsociados) {
		tipoAsociadoH.setCtaAscAsociados(ctaAscAsociados);
	}



	public void setTasDescripcion(String tasDescripcion) {
		tipoAsociadoH.setTasDescripcion(tasDescripcion);
	}



	public void setTasId(Integer tasId) {
		tipoAsociadoH.setTasId(tasId);
	}



	public void setTasNombre(String tasNombre) {
		tipoAsociadoH.setTasNombre(tasNombre);
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
