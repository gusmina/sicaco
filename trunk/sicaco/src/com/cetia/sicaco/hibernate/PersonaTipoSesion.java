package com.cetia.sicaco.hibernate;

public class PersonaTipoSesion {
	private String perId;
	private String tipoSesion;
	
	
	public PersonaTipoSesion(String perId, String tipoSesion) {
		super();
		this.perId = perId;
		this.tipoSesion = tipoSesion;
	}
	
	public String getPerId() {
		return perId;
	}
	public void setPerId(String perId) {
		this.perId = perId;
	}
	public String getTipoSesion() {
		return tipoSesion;
	}
	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
	}
	
	
}
