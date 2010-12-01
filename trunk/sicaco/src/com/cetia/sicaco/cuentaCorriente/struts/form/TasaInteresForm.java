package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.util.Set;

import com.cetia.sicaco.struts.BasicForm;
import com.cetia.sicaco.hibernate.CtaTinTasaInteres;

public class TasaInteresForm extends BasicForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8296865339300301816L;
	private CtaTinTasaInteres ctaTinTasaInteresH = new CtaTinTasaInteres();
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
		
	public CtaTinTasaInteres getCtaTinTasaInteresH() {
		return ctaTinTasaInteresH;
	}


	public void setCtaTinTasaInteresH(CtaTinTasaInteres ctaTinTasaInteresH) {
		this.ctaTinTasaInteresH = ctaTinTasaInteresH;
	}


	public Set getCtaSegSeguroses() {
		return ctaTinTasaInteresH.getCtaSegSeguroses();
	}


	/*public Set getCtaTahTipoAhorros_1() {
		return ctaTinTasaInteresH.getCtaTahTipoAhorros_1();
	}*/


	public Set getCtaTahTipoAhorros() {
		return ctaTinTasaInteresH.getCtaTahTipoAhorros();
	}


	public Set getCtaTprTipoPrestamos() {
		return ctaTinTasaInteresH.getCtaTprTipoPrestamos();
	}


	public String getTinDescripcion() {
		return ctaTinTasaInteresH.getTinDescripcion();
	}


	public Integer getTinId() {
		return ctaTinTasaInteresH.getTinId();
	}


	public String getTinNombre() {
		return ctaTinTasaInteresH.getTinNombre();
	}


	public Double getTinTasa() {
		return ctaTinTasaInteresH.getTinTasa();
	}


	public void setCtaSegSeguroses(Set ctaSegSeguroses) {
		ctaTinTasaInteresH.setCtaSegSeguroses(ctaSegSeguroses);
	}


/*	public void setCtaTahTipoAhorros_1(Set ctaTahTipoAhorros_1) {
		ctaTinTasaInteresH.setCtaTahTipoAhorros_1(ctaTahTipoAhorros_1);
	}*/


	public void setCtaTahTipoAhorros(Set ctaTahTipoAhorros) {
		ctaTinTasaInteresH.setCtaTahTipoAhorros(ctaTahTipoAhorros);
	}


	public void setCtaTprTipoPrestamos(Set ctaTprTipoPrestamos) {
		ctaTinTasaInteresH.setCtaTprTipoPrestamos(ctaTprTipoPrestamos);
	}


	public void setTinDescripcion(String tinDescripcion) {
		ctaTinTasaInteresH.setTinDescripcion(tinDescripcion);
	}


	public void setTinId(Integer tinId) {
		ctaTinTasaInteresH.setTinId(tinId);
	}


	public void setTinNombre(String tinNombre) {
		ctaTinTasaInteresH.setTinNombre(tinNombre);
	}


	public void setTinTasa(Double tinTasa) {
		ctaTinTasaInteresH.setTinTasa(tinTasa);
	}

}
