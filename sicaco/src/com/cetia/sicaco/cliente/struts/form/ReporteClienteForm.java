package com.cetia.sicaco.cliente.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cetia.sicaco.struts.BasicForm;

public class ReporteClienteForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3713470440122164834L;
	private Date fecha1;
	private Date fecha2;
	private String ascId;
	
	public String getAscId() {
		return ascId;
	}



	public void setAscId(String ascId) {
		this.ascId = ascId;
	}



	public Date getFecha1D() {
		return fecha1;
	}



	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}



	public Date getFecha2D() {
		return fecha2;
	}



	public void setFecha2(Date fecha2) {
		this.fecha2 = fecha2;
	}

	public String getFecha1() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (getFecha1D()!=null)?(sdf.format(getFecha1D())):null;

	}

	public String getFecha2() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (getFecha2D()!=null)?(sdf.format(getFecha2D())):null;
	}
	
	public void setFecha1(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			setFecha1((sdf.parse(adufecString)));
		} catch (ParseException e) {
		}
	}
	
	public void setFecha2(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			setFecha2((sdf.parse(adufecString)));
		} catch (ParseException e) {
		}
	}
	
	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
