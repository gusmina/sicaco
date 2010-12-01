package com.cetia.sicaco.contabilidad.struts.form;

import com.cetia.sicaco.struts.BasicForm;

public class CuentasEspecialesForm extends BasicForm {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3638065012391827166L;
	private int cuentas[]= new int[2] ;
	
	
	



	public int[] getCuentas() {
		return cuentas;
	}

	public int getCuenta(int index) {
		return cuentas[index];
	}



	public void setCuenta(int index,int cuenta) {
		this.cuentas[index] = cuenta;
	}


	public void setCuentas(int[] cuentas) {
		this.cuentas = cuentas;
	}






	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
