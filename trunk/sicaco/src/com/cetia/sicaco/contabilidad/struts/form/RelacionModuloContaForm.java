package com.cetia.sicaco.contabilidad.struts.form;

import java.util.Date;

import com.cetia.sicaco.hibernate.ConCpaConceptoPartida;
import com.cetia.sicaco.hibernate.ConCueCuenta;
import com.cetia.sicaco.hibernate.ConMxcModuloxCuentacontable;
import com.cetia.sicaco.struts.BasicForm;

public class RelacionModuloContaForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1031304648662841611L;
	private ConMxcModuloxCuentacontable moduloxCuentacontable = new ConMxcModuloxCuentacontable(); 
	private String[] parametros = new String[9];
	private int cuentaIntereses;
	private String conceptoCuenta;
	private String otroConcepto;
	private int accionC;
	//estos se utilizan para manejar el filtrado de los usos de factura
	private int id1;
	private int id2;
	
	
	
	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public String getOtroConcepto() {
		return otroConcepto;
	}

	public void setOtroConcepto(String otroConcepto) {
		this.otroConcepto = otroConcepto;
	}

	public String[] getParametros() {
		return parametros;
	}

	public void setParametros(String[] parametros) {
		this.parametros = parametros;
	}

	public String getConceptoCuenta() {
		return conceptoCuenta;
	}

	public void setConceptoCuenta(String conceptoCuenta) {
		this.conceptoCuenta = conceptoCuenta;
	}

	public int getAccionC() {
		return accionC;
	}

	public void setAccionC(int accionC) {
		this.accionC = accionC;
	}

	public int getCuentaIntereses() {
		return cuentaIntereses;
	}

	public void setCuentaIntereses(int cuentaIntereses) {
		this.cuentaIntereses = cuentaIntereses;
	}

	public String getParametro(int index) {
	       return this.parametros[index];
	   }
	   
	   public void setParametro ( int index , String value ) {
		    this.parametros [ index ] = value ;
		}
	   
	public ConMxcModuloxCuentacontable getModuloxCuentacontable() {
		return moduloxCuentacontable;
	}



	public void setModuloxCuentacontable(
			ConMxcModuloxCuentacontable moduloxCuentacontable) {
		this.moduloxCuentacontable = moduloxCuentacontable;
	}



	public ConCpaConceptoPartida getConCpaConceptoPartida() {
		return moduloxCuentacontable.getConCpaConceptoPartida();
	}



	public ConCueCuenta getConCueCuenta() {
		return moduloxCuentacontable.getConCueCuenta();
	}



	public String getCxaConceptoExtra() {
		return moduloxCuentacontable.getCxaConceptoExtra();
	}



	public byte getCxcCargoAbono() {
		return moduloxCuentacontable.getCxcCargoAbono();
	}



	public Date getCxcFechaRelacion() {
		return moduloxCuentacontable.getCxcFechaRelacion();
	}



	public long getCxcId() {
		return moduloxCuentacontable.getCxcId();
	}



	public String getCxcParametrosUnion() {
		return moduloxCuentacontable.getCxcParametrosUnion();
	}



	public void setConCpaConceptoPartida(
			ConCpaConceptoPartida conCpaConceptoPartida) {
		moduloxCuentacontable.setConCpaConceptoPartida(conCpaConceptoPartida);
	}



	public void setConCueCuenta(ConCueCuenta conCueCuenta) {
		moduloxCuentacontable.setConCueCuenta(conCueCuenta);
	}



	public void setCxaConceptoExtra(String cxaConceptoExtra) {
		moduloxCuentacontable.setCxaConceptoExtra(cxaConceptoExtra);
	}



	public void setCxcCargoAbono(byte cxcCargoAbono) {
		moduloxCuentacontable.setCxcCargoAbono(cxcCargoAbono);
	}



	public void setCxcFechaRelacion(Date cxcFechaRelacion) {
		moduloxCuentacontable.setCxcFechaRelacion(cxcFechaRelacion);
	}



	public void setCxcId(long cxcId) {
		moduloxCuentacontable.setCxcId(cxcId);
	}



	public void setCxcParametrosUnion(String cxcParametrosUnion) {
		moduloxCuentacontable.setCxcParametrosUnion(cxcParametrosUnion);
	}



	
	public boolean isFillAuditoria() {
		return false;
	}

}
