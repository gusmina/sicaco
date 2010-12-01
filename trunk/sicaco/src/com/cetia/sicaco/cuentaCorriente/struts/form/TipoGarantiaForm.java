package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.hibernate.CtaTfiTipoFiador;
import com.cetia.sicaco.hibernate.CtaTgaTipoGarantia;
import com.cetia.sicaco.struts.BasicForm;

public class TipoGarantiaForm extends  BasicForm{
	

	
	private CtaTgaTipoGarantia ctaTgaTipoGarantiaH  = new CtaTgaTipoGarantia();
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




	public CtaTgaTipoGarantia getCtaTgaTipoGarantiaH() {
		return ctaTgaTipoGarantiaH;
	}








	public void setCtaTgaTipoGarantiaH(CtaTgaTipoGarantia ctaTgaTipoGarantiaH) {
		this.ctaTgaTipoGarantiaH = ctaTgaTipoGarantiaH;
	}








	public Set getCtaGarGarantias() {
		return ctaTgaTipoGarantiaH.getCtaGarGarantias();
	}








	public String getTgaDescripcion() {
		return ctaTgaTipoGarantiaH.getTgaDescripcion();
	}








	public Integer getTgaId() {
		return ctaTgaTipoGarantiaH.getTgaId();
	}








	public String getTgaNombre() {
		return ctaTgaTipoGarantiaH.getTgaNombre();
	}








	public void setCtaGarGarantias(Set ctaGarGarantias) {
		ctaTgaTipoGarantiaH.setCtaGarGarantias(ctaGarGarantias);
	}








	public void setTgaDescripcion(String tgaDescripcion) {
		ctaTgaTipoGarantiaH.setTgaDescripcion(tgaDescripcion);
	}








	public void setTgaId(Integer tgaId) {
		ctaTgaTipoGarantiaH.setTgaId(tgaId);
	}








	public void setTgaNombre(String tgaNombre) {
		ctaTgaTipoGarantiaH.setTgaNombre(tgaNombre);
	}
	
	
	

}
