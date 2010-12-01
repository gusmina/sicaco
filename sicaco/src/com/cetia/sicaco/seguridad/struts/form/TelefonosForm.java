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

import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.hibernate.SecTelTelefono;
import com.cetia.sicaco.hibernate.SecTelTelefonoId;
import com.cetia.sicaco.hibernate.SecTteTipoTelefono;
import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 02-20-2008
 * 
 * XDoclet definition:
 * @struts.form name="telefonosForm"
 */
public class TelefonosForm extends BasicForm {
	/*
	 * Generated Methods
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -4945418338644612632L;

	private SecTelTelefono telefonoH = new SecTelTelefono();
	private boolean asoc = false;
	
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
		return (telefonoH.getAudFechaCreacion()!=null)?(sdf.format(telefonoH.getAudFechaCreacion())):null;
	}

	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (telefonoH.getAudUsuarioModificacion()!=null)?(sdf.format(telefonoH.getAudFechaModificacion())):null;
	}

	public String getAudUsuarioCreacion() {
		return telefonoH.getAudUsuarioCreacion();
	}

	public String getAudUsuarioModificacion() {
		return telefonoH.getAudUsuarioModificacion();
	}

	public SecTelTelefonoId getId() {
		if(telefonoH.getId()== null){
			telefonoH.setId(new SecTelTelefonoId());
		}
		return telefonoH.getId();
	}

	public SecTteTipoTelefono getSecTteTipoTelefono() {
		return telefonoH.getSecTteTipoTelefono();
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		telefonoH.setAudFechaCreacion(audFechaCreacion);
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		telefonoH.setAudFechaModificacion(audFechaModificacion);
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		telefonoH.setAudUsuarioCreacion(audUsuarioCreacion);
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		telefonoH.setAudUsuarioModificacion(audUsuarioModificacion);
	}

	public void setId(SecTelTelefonoId id) {
		if(telefonoH.getId()== null){
			telefonoH.setId(new SecTelTelefonoId());
		}
		telefonoH.setId(id);
	}

	public void setSecTteTipoTelefono(SecTteTipoTelefono secTteTipoTelefono) {
		telefonoH.setSecTteTipoTelefono(secTteTipoTelefono);
	}
	
	public void setPerId(String perId) {
		if(telefonoH.getId()== null){
			telefonoH.setId(new SecTelTelefonoId());
		}
		SecTelTelefonoId id = telefonoH.getId();
		SecPerPersona persona = new SecPerPersona();
		persona.setPerId(perId);
		id.setSecPerPersona(persona);
		telefonoH.setId(id);
	}
	
	public String getPerId() {
		if(telefonoH.getId()== null){
			telefonoH.setId(new SecTelTelefonoId());
		}
		return telefonoH.getId().getSecPerPersona().getPerId();
	}
	
	public String getTteDescripcion() {
		return telefonoH.getTteDescripcion();
	}

	public Integer getTteId() {
		return telefonoH.getTteId();
	}

	public String getTelExt() {
		return telefonoH.getTelExt();
	}

	public void setTelExt(String telExt) {
		telefonoH.setTelExt(telExt);
	}

	public SecTelTelefono getTelefonoH() {
		return telefonoH;
	}

	public void setTelefonoH(SecTelTelefono telefonoH) {
		this.telefonoH = telefonoH;
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
	
	
}