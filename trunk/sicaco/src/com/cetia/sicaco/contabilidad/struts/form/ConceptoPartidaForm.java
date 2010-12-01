package com.cetia.sicaco.contabilidad.struts.form;

import java.util.Set;

import com.cetia.sicaco.hibernate.ConCpaConceptoPartida;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccion;
import com.cetia.sicaco.struts.BasicForm;

public class ConceptoPartidaForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 756941099820329080L;
	private ConCpaConceptoPartida conceptoPartidaH = new ConCpaConceptoPartida();
	private boolean mdf;
	private int fuente;//1 para si por la transaccion, 0 si para el concepto
	
	public int getTtrId() {
		return conceptoPartidaH.getCtaTtrTipoTransaccion().getTtrId();
	}


	public void setTtrId(int ttrId) {
		this.conceptoPartidaH.getCtaTtrTipoTransaccion().setTtrId(ttrId);
	}
	
	public int getFuente() {
		return fuente;
	}


	public void setFuente(int fuente) {
		this.fuente = fuente;
	}



	public boolean isMdf() {
		return mdf;
	}



	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}



	public Set getConDpaDetallePartidas() {
		return conceptoPartidaH.getConDpaDetallePartidas();
	}



	public Set getConMxcModuloxCuentacontables() {
		return conceptoPartidaH.getConMxcModuloxCuentacontables();
	}



	public Set getConPcoPartidaContables() {
		return conceptoPartidaH.getConPcoPartidaContables();
	}



	public String getCpaConcepto() {
		return conceptoPartidaH.getCpaConcepto();
	}



	public byte getCpaDescripcionConcepto() {
		return conceptoPartidaH.getCpaDescripcionConcepto();
	}



	public Integer getCpaId() {
		return conceptoPartidaH.getCpaId();
	}



	public CtaTtrTipoTransaccion getCtaTtrTipoTransaccion() {
		return conceptoPartidaH.getCtaTtrTipoTransaccion();
	}



	public void setConDpaDetallePartidas(Set conDpaDetallePartidas) {
		conceptoPartidaH.setConDpaDetallePartidas(conDpaDetallePartidas);
	}



	public void setConMxcModuloxCuentacontables(Set conMxcModuloxCuentacontables) {
		conceptoPartidaH
				.setConMxcModuloxCuentacontables(conMxcModuloxCuentacontables);
	}



	public void setConPcoPartidaContables(Set conPcoPartidaContables) {
		conceptoPartidaH.setConPcoPartidaContables(conPcoPartidaContables);
	}



	public void setCpaConcepto(String cpaConcepto) {
		conceptoPartidaH.setCpaConcepto(cpaConcepto);
	}



	public void setCpaDescripcionConcepto(byte cpaDescripcionConcepto) {
		conceptoPartidaH.setCpaDescripcionConcepto(cpaDescripcionConcepto);
	}



	public void setCpaId(Integer cpaId) {
		conceptoPartidaH.setCpaId(cpaId);
	}



	public void setCtaTtrTipoTransaccion(
			CtaTtrTipoTransaccion ctaTtrTipoTransaccion) {
		conceptoPartidaH.setCtaTtrTipoTransaccion(ctaTtrTipoTransaccion);
	}



	public ConCpaConceptoPartida getConceptoPartidaH() {
		return conceptoPartidaH;
	}



	public void setConceptoPartidaH(ConCpaConceptoPartida conceptoPartidaH) {
		this.conceptoPartidaH = conceptoPartidaH;
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
