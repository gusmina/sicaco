package com.cetia.sicaco.reporte.struts.form;

import com.cetia.sicaco.struts.BasicForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*; 

public class ReporteForm extends BasicForm {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	//parametros del reporte
	private Date fechaReporte;
	private String txtSumario;
	private String txtAuxiliar;
	private Integer sucursal;
	private Date fechaIni;
	private Date fechaFin;
	private String mes;
	private String anio;
	private Date fechaDePresentacion;
	private String tipoDeDocumento;
	private int reporteId;
	private int linId;
	private int bodId;
	private String cueId1;
	private String cueId2;
	private int etrId;
	private int sucId;
	//Beneficiarios X Asociado
	private int rubro; 
	private Integer selected;
	private String perNombre;
	private String perApellido;
	private String asociadoId;
	private String ascId2;
	
	private boolean excel;
	
	
	//Numero identificador del Reporte
	private int numR;
	private String nombre;
	
	//private ArrayList listaReport = new ArrayList();
	
	public String getFechaReporte() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (fechaReporte!=null)?(sdf.format(fechaReporte)):null;
	}
	
	public Date getFechaReporteD() {
		return fechaReporte;
	}
	public void setFechaReporte(String fechaReporte) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if(fechaReporte.equals("")) this.fechaReporte = new Date();
			else this.fechaReporte = sdf.parse(fechaReporte);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}


	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
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

	public String getTxtSumario() {
		return txtSumario;
	}

	public void setTxtSumario(String txtSumario) {
		this.txtSumario = txtSumario;
	}


	public String getTxtAuxiliar() {
		return txtAuxiliar;
	}


	public void setTxtAuxiliar(String txtAuxiliar) {
		this.txtAuxiliar = txtAuxiliar;
	}

	public int getReporteId() {
		return reporteId;
	}



	public void setReporteId(int reporteId) {
		this.reporteId = reporteId;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
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

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	
	}

	public void setAnio(String anio) {
			this.anio = anio;
	}

	public Date getFechaDePresentacionD() {
		return fechaDePresentacion;
	}
	
	public String getFechaDePresentacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (fechaDePresentacion!=null)?(sdf.format(fechaDePresentacion)):null;
	}
	public void setFechaDePresentacion(String fechaDePresentacion) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.fechaDePresentacion= sdf.parse(fechaDePresentacion);
		} catch (ParseException e) {
		}
	}

	public String getTipoDeDocumento() {
		return tipoDeDocumento;
	}

	public void setTipoDeDocumento(String tipoDeDocumento) {
		this.tipoDeDocumento = tipoDeDocumento;
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
		case 1:this.nombre = "Reporte de Ventas del D&iacute;a";
			break;
		case 2:this.nombre = "Reporte de Ventas y Costos de Venta de Iventario";
			break;
		case 3:this.nombre = "Reporte de Ventas por Per&iacute;odo";
			break;
		case 4:this.nombre = "Reporte de Ventas por d&iacute;a Consolidado";
			break;
		case 5:this.nombre = "Detalle de Ventas del D&iacute;a";
			break;
		case 6:this.nombre = "Detalle de Costos del D&iacute;a";
			break;
		case 7:this.nombre = "Reporte de Costos de Inventario";
			break;
		case 8:this.nombre = "Reporte de Levantamiento F&iacute;sico";
			break;
		case 9:this.nombre = "Reporte de Saldos de Inventario para la Contabilidad";
			break;
		case 10:this.nombre = "Reporte de Movimientos por Art&iacute;culo y Fecha";
			break;
		case 11:this.nombre = "Libro de Compras";
			break;
		case 12:this.nombre = "Libro de Ventas Consumidor Consumidor Final";
			break;
		case 13:this.nombre = "Libro de Ventas a Contribuyentes";
			break;
		case 14: this.nombre = "Balanza General de Comprobacion";
			break;
		case 15: this.nombre = "Estado de Resultados";
			break;
		case 16: this.nombre = "Balance General";
			break;
		case 17: this.nombre = "Renta de Asociados";
			break;
		case 18: this.nombre = "Movimientos Cuentas contables";
			break;
		case 19: this.nombre = "Libro Auxiliar";
			break;
		case 20: this.nombre = "Beneficiarios por asociados";
			break;
		case 21: this.nombre = "Deduccion Quincenal";
			break;
		case 22: this.nombre = "Actualizacion de Planilla";
			break;
		}
		
	}

	public int getLinId() {
		return linId;
	}

	public void setLinId(int linId) {
		this.linId = linId;
	}

	public int getBodId() {
		return bodId;
	}

	public void setBodId(int bodId) {
		this.bodId = bodId;
	}

	public String getCueId1() {
		return cueId1;
	}

	public void setCueId1(String cueId1) {
		this.cueId1 = cueId1;
	}

	public String getCueId2() {
		return cueId2;
	}

	public void setCueId2(String cueId2) {
		this.cueId2 = cueId2;
	}

	public int getEtrId() {
		return etrId;
	}

	public void setEtrId(int etrId) {
		this.etrId = etrId;
	}

	public int getSucId() {
		return sucId;
	}

	public void setSucId(int sucId) {
		this.sucId = sucId;
	}

	public int getRubro() {
		return rubro;
	}

	public void setRubro(int rubro) {
		this.rubro = rubro;
	}

	public boolean isExcel() {
		return excel;
	}

	public void setExcel(boolean excel) {
		this.excel = excel;
	}
	

	
}
