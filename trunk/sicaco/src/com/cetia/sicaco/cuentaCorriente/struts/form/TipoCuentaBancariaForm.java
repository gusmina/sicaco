package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.util.Set;

import com.cetia.sicaco.hibernate.CtaTcuTipoCuenta;
import com.cetia.sicaco.struts.BasicForm;

public class TipoCuentaBancariaForm extends BasicForm {
	
	private CtaTcuTipoCuenta tipoCuentaH = new CtaTcuTipoCuenta();
	private boolean mdf;
	
	

	public Set getCtaCbaCuentaBancarias() {
		return tipoCuentaH.getCtaCbaCuentaBancarias();
	}



	public String getTcuDescripcion() {
		return tipoCuentaH.getTcuDescripcion();
	}



	public Integer getTcuId() {
		return tipoCuentaH.getTcuId();
	}



	public String getTcuNombre() {
		return tipoCuentaH.getTcuNombre();
	}



	public void setCtaCbaCuentaBancarias(Set ctaCbaCuentaBancarias) {
		tipoCuentaH.setCtaCbaCuentaBancarias(ctaCbaCuentaBancarias);
	}



	public void setTcuDescripcion(String tcuDescripcion) {
		tipoCuentaH.setTcuDescripcion(tcuDescripcion);
	}



	public void setTcuId(Integer tcuId) {
		tipoCuentaH.setTcuId(tcuId);
	}



	public void setTcuNombre(String tcuNombre) {
		tipoCuentaH.setTcuNombre(tcuNombre);
	}



	public CtaTcuTipoCuenta getTipoCuentaH() {
		return tipoCuentaH;
	}



	public void setTipoCuentaH(CtaTcuTipoCuenta tipoCuentaH) {
		this.tipoCuentaH = tipoCuentaH;
	}



	public boolean isMdf() {
		return mdf;
	}



	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
