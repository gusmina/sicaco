package com.cetia.sicaco.reporte.struts.form;

import com.cetia.sicaco.struts.BasicForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*; 

public class ReportesOrdenForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4284307856862811144L;
	
	//parametros del reporte
	private Date fechaIni;
	private Date fechaFin;
	private String codigoAsociado;
	private int codigoProveedor;
	private int reporteId;
	private int numDias;
	
	private String perNombre;
	private String proNombre;
	private String perApellido;
	private String asociadoId;
	private String ascId2;
	private Integer selected;
	
	//Numero identificador del Reporte
	private int numR;
	private String nombre;
	
	private int ascEmp;
	
	//private ArrayList listaReport = new ArrayList();
	
	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}


	public int getReporteId() {
		return reporteId;
	}

	public void setReporteId(int reporteId) {
		this.reporteId = reporteId;
	}

	public Date getFechaIniD() {
		return fechaIni;
	}
	public String getFechaIni() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (fechaIni!=null)?(sdf.format(fechaIni)):null;
	}

	public void setFechaIni(String fechaIni) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try{
			this.fechaIni= sdf.parse(fechaIni);
		} catch (ParseException e) {
		}
	}

	public Date getFechaFinD() {
		return fechaFin;
	}
	public String getFechaFin() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (fechaFin!=null)?(sdf.format(fechaFin)):null;
	}

	public void setFechaFin(String fechaFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.fechaFin= sdf.parse(fechaFin);
		} catch (ParseException e) {
		}
	}

	public int getNumR() {
		return numR;
	}

	public void setNumR(int numR) {
		this.numR = numR;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(int numRep) {
		switch(numRep){
		case 1:this.nombre = "Estado de cuenta de Ordenes por Asociado";
			break;
		case 2:this.nombre = "Reporte de Ordenes de Compra Anuladas";
			break;
		case 3:this.nombre = "Reporte de Ordenes de Compra Cobradas";
			break;
		case 4:this.nombre = "Reporte de Ordenes de Compras Emitidas";
			break;
		case 5:this.nombre = "Reporte de Ordenes de Compra por Antiguedad";
			break;
		}
	}

	public String getCodigoAsociado() {
		return codigoAsociado;
	}

	public void setCodigoAsociado(String codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	public int getCodigoProveedor() {
		return codigoProveedor;
	}

	public void setCodigoProveedor(int codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	public int getNumDias() {
		return numDias;
	}

	public void setNumDias(int numDias) {
		this.numDias = numDias;
	}

	public String getPerNombre() {
		return perNombre;
	}

	public void setPerNombre(String perNombre) {
		this.perNombre = perNombre;
	}

	public String getPerApellido() {
		return perApellido;
	}

	public void setPerApellido(String perApellido) {
		this.perApellido = perApellido;
	}

	public String getAsociadoId() {
		return asociadoId;
	}

	public void setAsociadoId(String asociadoId) {
		this.asociadoId = asociadoId;
	}

	public String getAscId2() {
		return ascId2;
	}

	public void setAscId2(String ascId2) {
		this.ascId2 = ascId2;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public int getAscEmp() {
		return ascEmp;
	}

	public void setAscEmp(int ascEmp) {
		this.ascEmp = ascEmp;
	}


	public String getProNombre() {
		return proNombre;
	}


	public void setProNombre(String proNombre) {
		this.proNombre = proNombre;
	}
}
