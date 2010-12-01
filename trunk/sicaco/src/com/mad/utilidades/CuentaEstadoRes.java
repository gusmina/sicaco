package com.mad.utilidades;

import java.util.Date;

public class CuentaEstadoRes {
	private String cetInf;
	private Integer ctePos;
	private Integer cetBan;
	private String cueNombre;
	private Double saldo;
	private Date fecha;
	
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getCetInf() {
		return cetInf;
	}
	public void setCetInf(String cetInf) {
		this.cetInf = cetInf;
	}
	public Integer getCtePos() {
		return ctePos;
	}
	public void setCtePos(Integer ctePos) {
		this.ctePos = ctePos;
	}
	public Integer getCetBan() {
		return cetBan;
	}
	public void setCetBan(Integer cetBan) {
		this.cetBan = cetBan;
	}
	public String getCueNombre() {
		return cueNombre;
	}
	public void setCueNombre(String cueNombre) {
		this.cueNombre = cueNombre;
	}
	
	
	
}
