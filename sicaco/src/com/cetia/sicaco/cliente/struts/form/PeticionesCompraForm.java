/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.cliente.struts.form;

import com.cetia.sicaco.struts.BasicForm;

public class PeticionesCompraForm extends BasicForm {
	
	/**
	 * 
	 */
	String pecId;
	Integer ocoId;
	
	String ascEmp;
	String ascCodigo;
	String ascNombre;
	Integer sucId;
	Integer proId;
	String fechaSolicitud;
	
	public String getPecId() {
		return pecId;
	}

	public void setPecId(String pecId) {
		this.pecId = pecId;
	}
	
	public Integer getOcoId() {
		return ocoId;
	}

	public void setOcoId(Integer ocoId) {
		this.ocoId = ocoId;
	}	
	
	public String getAscEmp() {
		return ascEmp;
	}

	public void setAscEmp(String ascEmp) {
		this.ascEmp = ascEmp;
	}

	public String getAscCodigo() {
		return ascCodigo;
	}

	public void setAscCodigo(String ascCodigo) {
		this.ascCodigo = ascCodigo;
	}

	public String getAscNombre() {
		return ascNombre;
	}

	public void setAscNombre(String ascNombre) {
		this.ascNombre = ascNombre;
	}

	public Integer getSucId() {
		return sucId;
	}

	public void setSucId(Integer sucId) {
		this.sucId = sucId;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	public boolean isFillAuditoria() {
		return false;
	}	
	
}