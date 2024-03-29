/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.seguridad.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.hibernate.SecCelCorreoElectronico;
import com.cetia.sicaco.hibernate.SecCelCorreoElectronicoId;
import com.cetia.sicaco.struts.BasicForm;
import com.cetia.sicaco.struts.Constantes;

/** 
 * MyEclipse Struts
 * Creation date: 02-12-2008
 * 
 * XDoclet definition:
 * @struts.form name="correoForm"
 */
public class CorreoForm extends BasicForm {
	/*
	 * Generated Methods
	 */

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5825691098063742468L;
	
	private SecCelCorreoElectronico correoElectronicoH = new SecCelCorreoElectronico();
	private boolean asoc;
	private String email;
	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public String getAudFechaCreacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (correoElectronicoH.getAudFechaCreacion()!=null)?(sdf.format(correoElectronicoH.getAudFechaCreacion())):null;
	}

	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (correoElectronicoH.getAudUsuarioModificacion()!=null)?(sdf.format(correoElectronicoH.getAudFechaModificacion())):null;
	}

	public String getAudUsuarioCreacion() {
		return correoElectronicoH.getAudUsuarioCreacion();
	}

	public String getAudUsuarioModificacion() {
		return correoElectronicoH.getAudUsuarioModificacion();
	}

	public String getCelPrincipal() {
		return correoElectronicoH.getCelPrincipal();
	}

	public SecCelCorreoElectronicoId getId() {
		if(correoElectronicoH.getId()== null){
			correoElectronicoH.setId(new SecCelCorreoElectronicoId());
		}
		return correoElectronicoH.getId();
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		correoElectronicoH.setAudFechaCreacion(audFechaCreacion);
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		correoElectronicoH.setAudFechaModificacion(audFechaModificacion);
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		correoElectronicoH.setAudUsuarioCreacion(audUsuarioCreacion);
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		correoElectronicoH.setAudUsuarioModificacion(audUsuarioModificacion);
	}

	public void setCelPrincipal(String celPrincipal) {
		correoElectronicoH.setCelPrincipal(celPrincipal);
	}

	public void setId(SecCelCorreoElectronicoId id) {
		if(correoElectronicoH.getId()== null){
			correoElectronicoH.setId(new SecCelCorreoElectronicoId());
		}
		correoElectronicoH.setId(id);
	}

	public SecCelCorreoElectronico getCorreoElectronicoH() {
		return correoElectronicoH;
	}

	public void setCorreoElectronicoH(SecCelCorreoElectronico correoElectronicoH) {
		this.correoElectronicoH = correoElectronicoH;
	}
	
	public void setPerId(String perId) {
		if(correoElectronicoH.getId()== null){
			correoElectronicoH.setId(new SecCelCorreoElectronicoId());
		}
		SecCelCorreoElectronicoId id = new SecCelCorreoElectronicoId();
		SecPerPersona persona = new SecPerPersona();
		persona.setPerId(perId);
		id.setSecPerPersona(persona);
		correoElectronicoH.setId(id);
	}
	
	public String getPerId() {
		if(correoElectronicoH.getId()== null){
			correoElectronicoH.setId(new SecCelCorreoElectronicoId());
		}
		return correoElectronicoH.getId().getSecPerPersona().getPerId();
	}

	
	public boolean isFillAuditoria() {
		return true;
	}

	public boolean isAsoc() {
		return asoc;
	}

	public void setAsoc(boolean asoc) {
		this.asoc = asoc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}