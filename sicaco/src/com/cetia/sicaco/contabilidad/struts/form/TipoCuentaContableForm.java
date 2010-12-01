package com.cetia.sicaco.contabilidad.struts.form;

import java.util.Set;

import com.cetia.sicaco.hibernate.ConTicTipoCuenta;
import com.cetia.sicaco.struts.BasicForm;

public class TipoCuentaContableForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5845367689205101103L;
	ConTicTipoCuenta tipoCuentaH = new ConTicTipoCuenta();
	private boolean mdf;
	
	
	
	

	public boolean isMdf() {
		return mdf;
	}



	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}



	public Set getConCueCuentas() {
		return tipoCuentaH.getConCueCuentas();
	}



	public Integer getTicAcreeDeudo() {
		return tipoCuentaH.getTicAcreeDeudo();
	}



	public Integer getTicId() {
		return tipoCuentaH.getTicId();
	}



	public String getTicNombre() {
		return tipoCuentaH.getTicNombre();
	}



	public void setConCueCuentas(Set conCueCuentas) {
		tipoCuentaH.setConCueCuentas(conCueCuentas);
	}



	public void setTicAcreeDeudo(Integer ticAcreeDeudo) {
		tipoCuentaH.setTicAcreeDeudo(ticAcreeDeudo);
	}



	public void setTicId(Integer ticId) {
		tipoCuentaH.setTicId(ticId);
	}



	public void setTicNombre(String ticNombre) {
		tipoCuentaH.setTicNombre(ticNombre);
	}



	public ConTicTipoCuenta getTipoCuentaH() {
		return tipoCuentaH;
	}



	public void setTipoCuentaH(ConTicTipoCuenta tipoCuentaH) {
		this.tipoCuentaH = tipoCuentaH;
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
