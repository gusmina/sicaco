package com.cetia.sicaco.struts;

/**
 * 
 * @author Carlos Ramirez
 * Sigue la misma idea que FiadorVista, es la vista del Jmesa
 *
 */
public class PrestamoVista {
	String codigoPrestamo;
	String tipo;
	Double saldo;
	Double intereses;
	Double mora;
	
	
	
	public Double getMora() {
		return mora;
	}
	public void setMora(Double mora) {
		this.mora = mora;
	}
	public String getCodigoPrestamo() {
		return codigoPrestamo;
	}
	public void setCodigoPrestamo(String codigoPrestamo) {
		this.codigoPrestamo = codigoPrestamo;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Double getIntereses() {
		return intereses;
	}
	public void setIntereses(Double intereses) {
		this.intereses = intereses;
	}
	
	
}
