package com.cetia.sicaco.contabilidad.struts.form;

import com.cetia.sicaco.struts.BasicForm;

public class DesembolsoDeFondosForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4429254006027107126L;
	public String banId;
	public String cueId;
	public Double remesar;
	public String solicita;
	public double saldoActual;
	public String autoriza;
	public String idBancoC;
	public boolean chkProveedores;
	public boolean chkAhorros;
	public boolean chkPrestamos;
	public Double saldoCue;
	public Double transferir;
	public Double ahorros;
	public Double prestamos;
	public Double proveedores;
	public String bancoF;
	public String bancoD;
	
	//Desembolsos
	private int[] posicionDesembolsos;
	private double[] desembolsos;

	public int[] getPosicionDesembolsos() {
		return posicionDesembolsos;
	}


	public void setPosicionDesembolsos(int[] posicionDesembolsos) {
		this.posicionDesembolsos = posicionDesembolsos;
	}


	public Double getRemesar() {
		return remesar;
	}


	public void setRemesar(Double remesar) {
		this.remesar = remesar;
	}


	public String getBanId() {
		return banId;
	}


	public void setBanId(String banId) {
		this.banId = banId;
	}


	public String getCueId() {
		return cueId;
	}


	public void setCueId(String cueId) {
		this.cueId = cueId;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}


	public double[] getDesembolsos() {
		return desembolsos;
	}


	public void setDesembolsos(double[] desembolsos) {
		this.desembolsos = desembolsos;
	}


	public String getSolicita() {
		return solicita;
	}


	public void setSolicita(String solicita) {
		this.solicita = solicita;
	}


	public double getSaldoActual() {
		return saldoActual;
	}


	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}


	public String getAutoriza() {
		return autoriza;
	}


	public void setAutoriza(String autoriza) {
		this.autoriza = autoriza;
	}


	public String getIdBancoC() {
		return idBancoC;
	}


	public void setIdBancoC(String idBancoC) {
		this.idBancoC = idBancoC;
	}


	public boolean isChkProveedores() {
		return chkProveedores;
	}


	public void setChkProveedores(boolean chkProveedores) {
		this.chkProveedores = chkProveedores;
	}


	public boolean isChkAhorros() {
		return chkAhorros;
	}


	public void setChkAhorros(boolean chkAhorros) {
		this.chkAhorros = chkAhorros;
	}


	public boolean isChkPrestamos() {
		return chkPrestamos;
	}


	public void setChkPrestamos(boolean chkPrestamos) {
		this.chkPrestamos = chkPrestamos;
	}


	public Double getSaldoCue() {
		return saldoCue;
	}


	public void setSaldoCue(Double saldoCue) {
		this.saldoCue = saldoCue;
	}


	public Double getTransferir() {
		return transferir;
	}


	public void setTransferir(Double transferir) {
		this.transferir = transferir;
	}


	public Double getAhorros() {
		return ahorros;
	}


	public void setAhorros(Double ahorros) {
		this.ahorros = ahorros;
	}


	public Double getPrestamos() {
		return prestamos;
	}


	public void setPrestamos(Double prestamos) {
		this.prestamos = prestamos;
	}


	public Double getProveedores() {
		return proveedores;
	}


	public void setProveedores(Double proveedores) {
		this.proveedores = proveedores;
	}


	public String getBancoF() {
		return bancoF;
	}


	public void setBancoF(String bancoF) {
		this.bancoF = bancoF;
	}


	public String getBancoD() {
		return bancoD;
	}


	public void setBancoD(String bancoD) {
		this.bancoD = bancoD;
	}

}
