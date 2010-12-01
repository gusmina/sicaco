package com.cetia.sicaco.asociados.struts.form;

import java.util.Set;

import com.cetia.sicaco.hibernate.CtaTcoTipoContrato;
import com.cetia.sicaco.struts.BasicForm;

public class TipoContratoForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4570780394563771423L;

	private CtaTcoTipoContrato tipoContratoH = new CtaTcoTipoContrato();
	private boolean mdf;
	
	public boolean isMdf() {
		return mdf;
	}




	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}




	public CtaTcoTipoContrato getTipoContratoH() {
		return tipoContratoH;
	}




	public void setTipoContratoH(CtaTcoTipoContrato tipoContratoH) {
		this.tipoContratoH = tipoContratoH;
	}

	


	public Set getCtaAscAsociados() {
		return tipoContratoH.getCtaAscAsociados();
	}




	public String getTcoDescripcion() {
		return tipoContratoH.getTcoDescripcion();
	}




	public Integer getTcoId() {
		return tipoContratoH.getTcoId();
	}




	public String getTcoNombre() {
		return tipoContratoH.getTcoNombre();
	}




	public void setCtaAscAsociados(Set ctaAscAsociados) {
		tipoContratoH.setCtaAscAsociados(ctaAscAsociados);
	}




	public void setTcoDescripcion(String tcoDescripcion) {
		tipoContratoH.setTcoDescripcion(tcoDescripcion);
	}




	public void setTcoId(Integer tcoId) {
		tipoContratoH.setTcoId(tcoId);
	}




	public void setTcoNombre(String tcoNombre) {
		tipoContratoH.setTcoNombre(tcoNombre);
	}




	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
