package com.cetia.sicaco.struts;

public class CuentaAsociadoVista {
	private Long id;
	private String nombreCuenta;
	
	public CuentaAsociadoVista(){
		
	}
	
	public CuentaAsociadoVista(Long id,String nombreCuenta){
		this.id = id;
		this.nombreCuenta = nombreCuenta;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	
	
}
