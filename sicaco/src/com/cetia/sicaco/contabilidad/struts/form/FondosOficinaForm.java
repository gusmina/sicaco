package com.cetia.sicaco.contabilidad.struts.form;

import com.cetia.sicaco.struts.BasicForm;

public class FondosOficinaForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4429254006027107126L;
	public String banId;
	public String cueId;
	public Double remesar;
	public String solicita;
	public double saldoActual;
	public String autoriza;
	public String fecha;
	
	//remesas
	private long[] posicionRemesas;
	private double[] remesas;


	public long[] getPosicionRemesas() {
		return posicionRemesas;
	}


	public void setPosicionRemesas(long[] posicionRemesas) {
		this.posicionRemesas = posicionRemesas;
	}


	public Double getRemesar() {
		return remesar;
	}


	public void setRemesar(Double remesar) {
		this.remesar = remesar;
	}


	public String getBanId() {
		return banId;
	}


	public void setBanId(String banId) {
		this.banId = banId;
	}


	public String getCueId() {
		return cueId;
	}


	public void setCueId(String cueId) {
		this.cueId = cueId;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}


	public double[] getRemesas() {
		return remesas;
	}


	public void setRemesas(double[] remesas) {
		this.remesas = remesas;
	}


	public String getSolicita() {
		return solicita;
	}


	public void setSolicita(String solicita) {
		this.solicita = solicita;
	}


	public double getSaldoActual() {
		return saldoActual;
	}


	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}


	public String getAutoriza() {
		return autoriza;
	}


	public void setAutoriza(String autoriza) {
		this.autoriza = autoriza;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
