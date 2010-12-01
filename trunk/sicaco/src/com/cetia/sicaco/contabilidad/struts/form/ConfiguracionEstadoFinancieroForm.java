package com.cetia.sicaco.contabilidad.struts.form;


import com.cetia.sicaco.struts.BasicForm;

public class ConfiguracionEstadoFinancieroForm extends BasicForm {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6915026351363761330L;
	private int[] posiciones;
	private String financ;
	private int banda;
	
	
	
	public int getBanda() {
		return banda;
	}



	public void setBanda(int banda) {
		this.banda = banda;
	}



	public String getFinanc() {
		return financ;
	}



	public void setFinanc(String financ) {
		this.financ = financ;
	}



	public int[] getPosiciones() {
		return posiciones;
	}


	public int getPosicion(int i) {
		return posiciones[i];
	}
	
	public void setPosiciones(int[] posiciones) {
		this.posiciones = posiciones;
	}
	

	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
