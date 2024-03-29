/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.cuentaCorriente.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 09-25-2008
 * 
 * XDoclet definition:
 * @struts.form name="desembolsoPrestamoForm"
 */
public class DesembolsoPrestamoForm extends BasicForm {
	/*
	 * Generated Methods
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7644101357348568034L;

	private String prestamoId;

	private String perNombre;
	private String perApellido;
	private String asociadoId;
	private Integer lineaPrestamoId;
	private Integer tipoPrestamoId;

	private Integer linea;
	private Integer[] posiciones;
	private Integer selected;
	
	private int tamListaDesembolso;
	
	private Integer rckId;
	private Integer banId;
	private String chkCorrelativoCheque;
	
	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public String getPrestamoId() {
		return prestamoId;
	}

	public void setPrestamoId(String prestamoId) {
		this.prestamoId = prestamoId;
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

	public Integer getLineaPrestamoId() {
		return lineaPrestamoId;
	}

	public void setLineaPrestamoId(Integer lineaPrestamoId) {
		this.lineaPrestamoId = lineaPrestamoId;
	}

	public Integer getTipoPrestamoId() {
		return tipoPrestamoId;
	}

	public void setTipoPrestamoId(Integer tipoPrestamoId) {
		this.tipoPrestamoId = tipoPrestamoId;
	}

	public Integer getLinea() {
		return linea;
	}

	public void setLinea(Integer linea) {
		this.linea = linea;
	}

	public Integer[] getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(Integer[] posiciones) {
		this.posiciones = posiciones;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public int getTamListaDesembolso() {
		return tamListaDesembolso;
	}

	public void setTamListaDesembolso(int tamListaDesembolso) {
		this.tamListaDesembolso = tamListaDesembolso;
	}

	public Integer getRckId() {
		return rckId;
	}

	public void setRckId(Integer rckId) {
		this.rckId = rckId;
	}

	public Integer getBanId() {
		return banId;
	}

	public void setBanId(Integer banId) {
		this.banId = banId;
	}

	public String getChkCorrelativoCheque() {
		return chkCorrelativoCheque;
	}

	public void setChkCorrelativoCheque(String chkCorrelativoCheque) {
		this.chkCorrelativoCheque = chkCorrelativoCheque;
	}

	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}
	
}