package com.cetia.sicaco.cliente.struts.form;

import com.cetia.sicaco.struts.BasicForm;

public class SolicitudesForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int opSolicitud;
	private String cuenta;
	private Double txaMonto;
	private Integer banIdD;
	private Double disponible;
	private String cuentaBan;
	
	//Solicitud de Orden de Compra
	
	private Integer provId;
	private Double ocoMonto;
	private Integer lugar;

	public int getOpSolicitud() {
		return opSolicitud;
	}

	public void setOpSolicitud(int opSolicitud) {
		this.opSolicitud = opSolicitud;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	public Double getTxaMonto() {
		return txaMonto;
	}

	public void setTxaMonto(Double txaMonto) {
		this.txaMonto = txaMonto;
	}

	public Integer getBanIdD() {
		return banIdD;
	}

	public void setBanIdD(Integer banIdD) {
		this.banIdD = banIdD;
	}
	
	public Double getDisponible() {
		return disponible;
	}

	public void setDisponible(Double disponible) {
		this.disponible = disponible;
	}

	public String getCuentaBan() {
		return cuentaBan;
	}

	public void setCuentaBan(String cuentaBan) {
		this.cuentaBan = cuentaBan;
	}
	
	//Solicitud de Orden de Compra
	public Integer getProvId() {
		return provId;
	}

	public void setProvId(Integer provId) {
		this.provId = provId;
	}

	public Double getOcoMonto() {
		return ocoMonto;
	}

	public void setOcoMonto(Double ocoMonto) {
		this.ocoMonto = ocoMonto;
	}

	public Integer getLugar() {
		return lugar;
	}

	public void setLugar(Integer lugar) {
		this.lugar = lugar;
	}

	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
