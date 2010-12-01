package com.cetia.sicaco.contabilidad.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cetia.sicaco.hibernate.ConCueCuenta;
import com.cetia.sicaco.hibernate.ConSacSaldosAnterioresCuenta;
import com.cetia.sicaco.struts.BasicForm;

public class CierreMensualForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7104700822644024397L;
	
	private ConSacSaldosAnterioresCuenta saldosAnterioresCuentaH = new ConSacSaldosAnterioresCuenta();
	private Date fechaCierre;
	private int acum;
	
	

	
	public int getAcum() {
		return acum;
	}




	public void setAcum(int acum) {
		this.acum = acum;
	}




	public ConCueCuenta getConCueCuenta() {
		return saldosAnterioresCuentaH.getConCueCuenta();
	}




	public Date getSacFecha() {
		return saldosAnterioresCuentaH.getSacFecha();
	}




	public Long getSacId() {
		return saldosAnterioresCuentaH.getSacId();
	}




	public double getSacSaldoALaFecha() {
		return saldosAnterioresCuentaH.getSacSaldoALaFecha();
	}




	public void setConCueCuenta(ConCueCuenta conCueCuenta) {
		saldosAnterioresCuentaH.setConCueCuenta(conCueCuenta);
	}




	public void setSacFecha(Date sacFecha) {
		saldosAnterioresCuentaH.setSacFecha(sacFecha);
	}




	public void setSacId(Long sacId) {
		saldosAnterioresCuentaH.setSacId(sacId);
	}




	public void setSacSaldoALaFecha(double sacSaldoALaFecha) {
		saldosAnterioresCuentaH.setSacSaldoALaFecha(sacSaldoALaFecha);
	}




	public ConSacSaldosAnterioresCuenta getSaldosAnterioresCuentaH() {
		return saldosAnterioresCuentaH;
	}




	public void setSaldosAnterioresCuentaH(
			ConSacSaldosAnterioresCuenta saldosAnterioresCuentaH) {
		this.saldosAnterioresCuentaH = saldosAnterioresCuentaH;
	}


	public Date getFechaCierreD() {
		return fechaCierre;
	}




	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getFechaCierre() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (fechaCierre!=null)?(sdf.format(fechaCierre)):null;
	}




	public void setFechaCierre(String fechaCierre) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.fechaCierre = sdf.parse( fechaCierre);
		} catch (ParseException e) {
		}
	}


	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
