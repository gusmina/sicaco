/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.cuentaCorriente.struts.form;

import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 10-16-2009
 * 
 * XDoclet definition:
 * @struts.form name="aplicarDescuentosPrestamoForm"
 */
public class AplicarDescuentosPrestamoForm extends BasicForm {

	private Long casCuenta;
	private Double aportaciones;
	//Ahorros
	private long[] posicionAho;
	private double[] ahorros;
	
	//Seguros
	private long[] posicionSeg;
	private double[] seguros;
	private int[] digitadoSeg;
	
	//Prestamos
	private long[] posicionPre;
	private double[] prestamos;
	private int[] digitadoPre;	
	
	
	public Long getCasCuenta() {
		return casCuenta;
	}

	public void setCasCuenta(Long casCuenta) {
		this.casCuenta = casCuenta;
	}

	public boolean isFillAuditoria() {
		return false;
	}

	public Double getAportaciones() {
		return aportaciones;
	}

	public void setAportaciones(Double aportaciones) {
		this.aportaciones = aportaciones;
	}

	public long[] getPosicionPre() {
		return posicionPre;
	}

	public void setPosicionPre(long[] posicionPre) {
		this.posicionPre = posicionPre;
	}

	public double[] getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(double[] prestamos) {
		this.prestamos = prestamos;
	}
	
	public int[] getDigitadoPre() {
		return digitadoPre;
	}

	public void setDigitadoPre(int[] digitadoPre) {
		this.digitadoPre = digitadoPre;
	}	

	public long[] getPosicionAho() {
		return posicionAho;
	}

	public void setPosicionAho(long[] posicionAho) {
		this.posicionAho = posicionAho;
	}

	public double[] getAhorros() {
		return ahorros;
	}

	public void setAhorros(double[] ahorros) {
		this.ahorros = ahorros;
	}

	public long[] getPosicionSeg() {
		return posicionSeg;
	}

	public void setPosicionSeg(long[] posicionSeg) {
		this.posicionSeg = posicionSeg;
	}

	public double[] getSeguros() {
		return seguros;
	}

	public void setSeguros(double[] seguros) {
		this.seguros = seguros;
	}

	public int[] getDigitadoSeg() {
		return digitadoSeg;
	}

	public void setDigitadoSeg(int[] digitadoSeg) {
		this.digitadoSeg = digitadoSeg;
	}
	
}