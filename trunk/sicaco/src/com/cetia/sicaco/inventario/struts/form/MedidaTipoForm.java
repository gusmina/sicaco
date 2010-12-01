package com.cetia.sicaco.inventario.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.hibernate.InvTmeTipoMedida;
import com.cetia.sicaco.struts.BasicForm;
import com.cetia.sicaco.struts.Constantes;

public class MedidaTipoForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1594843656845748329L;
	
	private InvTmeTipoMedida invTmeTipoMedida = new InvTmeTipoMedida();
	private String mode;
	private String TmeIdHidden;
	private String redirectOnError;
	private int tmId;
	
		
	public String getRedirectOnError() {
		return redirectOnError;
	}



	public void setRedirectOnError(String redirectOnError) {
		this.redirectOnError = redirectOnError;
	}



	public String getTmeIdHidden() {
		return TmeIdHidden;
	}



	public void setTmeIdHidden(String tmeIdHidden) {
		TmeIdHidden = tmeIdHidden;
	}



	public String getMode() {
		return mode;
	}



	public void setMode(String mode) {
		this.mode = mode;
	}



	public InvTmeTipoMedida getInvTmeTipoMedida() {
		return invTmeTipoMedida;
	}



	public void setInvTmeTipoMedida(InvTmeTipoMedida invTmeTipoMedida) {
		this.invTmeTipoMedida = invTmeTipoMedida;
	}



	public String getTmeDescripcion() {
		return invTmeTipoMedida.getTmeDescripcion();
	}



	public Integer getTmeId() {
		return invTmeTipoMedida.getTmeId();
	}



	public String getTmeNombre() {
		return invTmeTipoMedida.getTmeNombre();
	}



	public void setTmeDescripcion(String tmeDescripcion) {
		invTmeTipoMedida.setTmeDescripcion(tmeDescripcion);
	}



	public void setTmeId(Integer tmeId) {
		invTmeTipoMedida.setTmeId(tmeId);
	}



	public void setTmeNombre(String tmeNombre) {
		invTmeTipoMedida.setTmeNombre(tmeNombre);
	}



	public int getTmId() {
		return tmId;
	}



	public void setTmId(int tmId) {
		this.tmId = tmId;
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}
/*	
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActionErrors actionErrors = super.validate(mapping, request);
		if(actionErrors.size() > 0){
			if(getRedirectOnError() != null && !getRedirectOnError().equals("")){
				if(getRedirectOnError().equals("buscar")){
					request.getSession().setAttribute(Constantes.METHOD_NAME,"invalidSave");
				}else{
					request.getSession().setAttribute(Constantes.METHOD_NAME,"invalidEdit");
				}
			}
		}
		return actionErrors;
	}
*/
}
