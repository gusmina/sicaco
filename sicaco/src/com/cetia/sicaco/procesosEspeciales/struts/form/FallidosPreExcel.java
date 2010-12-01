package com.cetia.sicaco.procesosEspeciales.struts.form;

public class FallidosPreExcel {
	private String codigo;
	private String error;
	
	public FallidosPreExcel(String codigo, String error) {
		super();
		this.codigo = codigo;
		this.error = error;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
