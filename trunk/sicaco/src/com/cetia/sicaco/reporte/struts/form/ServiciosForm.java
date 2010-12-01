package com.cetia.sicaco.reporte.struts.form;

import com.cetia.sicaco.struts.BasicForm;

public class ServiciosForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5643985045368610934L;
	
	private Integer idPadre;
	private int saldo;
	/**
	 * @return the idPadre
	 */
	public Integer getIdPadre() {
		return idPadre;
	}

	/**
	 * @param idPadre the idPadre to set
	 */
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}

	public boolean isFillAuditoria() {
		return false;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

}
