package com.mad.utilidades;

public class Cuenta{
	public Cuenta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	String nombre;
	String codigo;
	Double saldo;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
		
}