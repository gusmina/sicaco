package com.mad.utilidades;

import java.util.Date;

public class PartidaDesc {

	public double debe;
	public double haber;
	public Date fechaIn;
	public int comprobante;
	
	public double getDebe() {
		return debe;
	}
	public void setDebe(double debe) {
		this.debe = debe;
	}
	public double getHaber() {
		return haber;
	}
	public void setHaber(double haber) {
		this.haber = haber;
	}
	public Date getFechaIn() {
		return fechaIn;
	}
	public void setFechaIn(Date fechaIn) {
		this.fechaIn = fechaIn;
	}
	public int getComprobante() {
		return comprobante;
	}
	public void setComprobante(int comprobante) {
		this.comprobante = comprobante;
	}
	
}
