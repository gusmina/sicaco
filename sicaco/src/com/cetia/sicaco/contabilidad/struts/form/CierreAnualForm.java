package com.cetia.sicaco.contabilidad.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cetia.sicaco.struts.BasicForm;

public class CierreAnualForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4429254006027107126L;
	private Date fechaCierre;
	private int perdidasGananciasId;
	private int excedentesId;
	private int deficitId;
	private int year;
	private int month;
	
	
	
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Date getFechaCierreD() {
return this.fechaCierre;
	}
	
	public int getPerdidasGananciasId() {
		return perdidasGananciasId;
	}

	public void setPerdidasGananciasId(int perdidasGananciasId) {
		this.perdidasGananciasId = perdidasGananciasId;
	}

	public int getExcedentesId() {
		return excedentesId;
	}

	public void setExcedentesId(int excedentesId) {
		this.excedentesId = excedentesId;
	}

	public void setFechaCierreD(Date fechaCierre) {
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
	
	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getDeficitId() {
		return deficitId;
	}

	public void setDeficitId(int deficitId) {
		this.deficitId = deficitId;
	}

}
