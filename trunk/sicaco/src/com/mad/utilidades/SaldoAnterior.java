package com.mad.utilidades;
/**   @category (Contenedor de datos) <br>
 * Contiene Numero de cuenta, cargos, abonos y el multiplicador.
 * 	@update 19-09-2010 Agregado el valor debe y valor haber 
 * 
 * */
public class SaldoAnterior {

	private String multiplicador;
	private String cuenta;
	private double cargos;
	private double abonos;
	private double debe;
	private double haber;
	
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
	public String getMultiplicador() {
		return multiplicador;
	}
	public void setMultiplicador(String multiplicador) {
		this.multiplicador = multiplicador;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public double getCargos() {
		return cargos;
	}
	public void setCargos(double cargos) {
		this.cargos = cargos;
	}
	public double getAbonos() {
		return abonos;
	}
	public void setAbonos(double abonos) {
		this.abonos = abonos;
	}
	
}
