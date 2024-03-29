/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.hibernate.CtaMxpMovimientoPrestamo;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociado;
import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 09-16-2008
 * 
 * XDoclet definition:
 * @struts.form name="movXPrestamoForm"
 */
public class MovXPrestamoForm extends BasicForm {
	/*
	 * Generated Methods
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -1847690782703695348L;

	CtaMxpMovimientoPrestamo movimientoPrestamoH = new CtaMxpMovimientoPrestamo();
	
	private String prestamoId;

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return movimientoPrestamoH.getCtaPrePrestamo();
	}

	public CtaTxaTransaccionxcuentaAsociado getCtaTxaTransaccionxcuentaAsociado() {
		return movimientoPrestamoH.getCtaTxaTransaccionxcuentaAsociado();
	}

	public Date getMxpFecha() {
		return movimientoPrestamoH.getMxpFecha();
	}

	public Integer getMxpId() {
		return movimientoPrestamoH.getMxpId();
	}

	public Double getMxpInteresPendiente() {
		return movimientoPrestamoH.getMxpInteresPendiente();
	}

	public Double getMxpMora() {
		return movimientoPrestamoH.getMxpMora();
	}

	public Double getMxpSaldoActual() {
		return movimientoPrestamoH.getMxpSaldoActual();
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		movimientoPrestamoH.setCtaPrePrestamo(ctaPrePrestamo);
	}

	public void setCtaTxaTransaccionxcuentaAsociado(
			CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociado) {
		movimientoPrestamoH
				.setCtaTxaTransaccionxcuentaAsociado(ctaTxaTransaccionxcuentaAsociado);
	}

	public void setMxpFecha(Date mxpFecha) {
		movimientoPrestamoH.setMxpFecha(mxpFecha);
	}

	public void setMxpId(Integer mxpId) {
		movimientoPrestamoH.setMxpId(mxpId);
	}

	public void setMxpInteresPendiente(Double mxpInteresPendiente) {
		movimientoPrestamoH.setMxpInteresPendiente(mxpInteresPendiente);
	}

	public void setMxpMora(Double mxpMora) {
		movimientoPrestamoH.setMxpMora(mxpMora);
	}

	public void setMxpSaldoActual(Double mxpSaldoActual) {
		movimientoPrestamoH.setMxpSaldoActual(mxpSaldoActual);
	}

	public CtaMxpMovimientoPrestamo getMovimientoPrestamoH() {
		return movimientoPrestamoH;
	}

	public void setMovimientoPrestamoH(CtaMxpMovimientoPrestamo movimientoPrestamoH) {
		this.movimientoPrestamoH = movimientoPrestamoH;
	}

	public String getPrestamoId() {
		return prestamoId;
	}

	public void setPrestamoId(String prestamoId) {
		this.prestamoId = prestamoId;
	}

	public String getMxpFechaMovFin() {
		Date fechaMovFin  = movimientoPrestamoH.getMxpFechaMovFin();
		try {
			return new  SimpleDateFormat("dd-MM-yyyy").format(fechaMovFin);
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public String getMxpFechaMovIni() {
		Date fechaMovIni = movimientoPrestamoH.getMxpFechaMovIni();
		try {
			return new  SimpleDateFormat("dd-MM-yyyy").format(fechaMovIni);
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public void setMxpFechaMovFin(Date mxpFechaMovFin) {
		movimientoPrestamoH.setMxpFechaMovFin(mxpFechaMovFin);
	}
	
	public void setMxpFechaMovFin(String mxpFechaMovFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			movimientoPrestamoH.setMxpFechaMovFin(sdf.parse(mxpFechaMovFin));
		} catch (ParseException e) {
		}
	}

	public void setMxpFechaMovIni(Date mxpFechaMovIni) {
		movimientoPrestamoH.setMxpFechaMovIni(mxpFechaMovIni);
	}
	
	public void setMxpFechaMovIni(String mxpFechaMovIni) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			movimientoPrestamoH.setMxpFechaMovIni(sdf.parse(mxpFechaMovIni));
		} catch (ParseException e) {
		}
	}

	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}
}