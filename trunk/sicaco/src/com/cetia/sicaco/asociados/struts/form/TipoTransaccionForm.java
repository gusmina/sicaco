package com.cetia.sicaco.asociados.struts.form;

import java.util.Set;

import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccion;
import com.cetia.sicaco.struts.BasicForm;

public class TipoTransaccionForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5610885671957086957L;

	private CtaTtrTipoTransaccion tipoTransaccionH = new CtaTtrTipoTransaccion();
	private boolean mdf;
	
	
	
	public String getTtrUso() {
		return tipoTransaccionH.getTtrUso();
	}



	public void setTtrUso(String ttrUso) {
		tipoTransaccionH.setTtrUso(ttrUso);
	}



	public CtaTtrTipoTransaccion getTipoTransaccionH() {
		return tipoTransaccionH;
	}



	public void setTipoTransaccionH(CtaTtrTipoTransaccion tipoTransaccionH) {
		this.tipoTransaccionH = tipoTransaccionH;
	}



	public boolean isMdf() {
		return mdf;
	}



	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}

	

	/*public Set getCtaTxaTransaccionAsociados() {
		return tipoTransaccionH.getCtaTxaTransaccionAsociados();
	}*/



	public String getTtrDescripcion() {
		return tipoTransaccionH.getTtrDescripcion();
	}



	public Integer getTtrId() {
		return tipoTransaccionH.getTtrId();
	}



	public String getTtrNombre() {
		return tipoTransaccionH.getTtrNombre();
	}



/*	public void setCtaTxaTransaccionAsociados(Set ctaTxaTransaccionAsociados) {
		tipoTransaccionH
				.setCtaTxaTransaccionAsociados(ctaTxaTransaccionAsociados);
	}*/



	public void setTtrDescripcion(String ttrDescripcion) {
		tipoTransaccionH.setTtrDescripcion(ttrDescripcion);
	}



	public void setTtrId(Integer ttrId) {
		tipoTransaccionH.setTtrId(ttrId);
	}



	public void setTtrNombre(String ttrNombre) {
		tipoTransaccionH.setTtrNombre(ttrNombre);
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}



	public Set getConCpaConceptoPartidas() {
		return tipoTransaccionH.getConCpaConceptoPartidas();
	}



	public Set getCtaTxaTransaccionxcuentaAsociados() {
		return tipoTransaccionH.getCtaTxaTransaccionxcuentaAsociados();
	}



	public void setConCpaConceptoPartidas(Set conCpaConceptoPartidas) {
		tipoTransaccionH.setConCpaConceptoPartidas(conCpaConceptoPartidas);
	}



	public void setCtaTxaTransaccionxcuentaAsociados(
			Set ctaTxaTransaccionxcuentaAsociados) {
		tipoTransaccionH
				.setCtaTxaTransaccionxcuentaAsociados(ctaTxaTransaccionxcuentaAsociados);
	}

}
