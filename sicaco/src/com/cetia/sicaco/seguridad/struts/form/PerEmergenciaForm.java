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

import com.cetia.sicaco.hibernate.SecParParentesco;
import com.cetia.sicaco.hibernate.SecPemPersonaEmergencia;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 02-15-2008
 * 
 * XDoclet definition:
 * @struts.form name="perEmergenciaForm"
 */
public class PerEmergenciaForm extends BasicForm {
	/*
	 * Generated Methods
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 3387288371044268663L;
	
	private SecPemPersonaEmergencia personaEmergenciaH = new SecPemPersonaEmergencia();
	private boolean asoc;
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
		
	}

	
	
	public boolean isAsoc() {
		return asoc;
	}



	public void setAsoc(boolean asoc) {
		this.asoc = asoc;
	}



	public String getAudFechaCreacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (personaEmergenciaH.getAudFechaCreacion()!=null)?(sdf.format(personaEmergenciaH.getAudFechaCreacion())):null;
	}

	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (personaEmergenciaH.getAudFechaModificacion()!=null)?(sdf.format(personaEmergenciaH.getAudFechaModificacion())):null;
	}

	public String getAudUsuarioCreacion() {
		return personaEmergenciaH.getAudUsuarioCreacion();
	}

	public String getAudUsuarioModificacion() {
		return personaEmergenciaH.getAudUsuarioModificacion();
	}

	public String getPemApellidoCasada() {
		return personaEmergenciaH.getPemApellidoCasada();
	}

	public Integer getPemId() {
		return personaEmergenciaH.getPemId();
	}

	public String getPemPrimerApellido() {
		return personaEmergenciaH.getPemPrimerApellido();
	}

	public String getPemPrimerNombre() {
		return personaEmergenciaH.getPemPrimerNombre();
	}

	public String getPemSegundoApellido() {
		return personaEmergenciaH.getPemSegundoApellido();
	}

	public String getPemSegundoNombre() {
		return personaEmergenciaH.getPemSegundoNombre();
	}

	public String getPemTelefono() {
		return personaEmergenciaH.getPemTelefono();
	}

	public String getPemTercerNombre() {
		return personaEmergenciaH.getPemTercerNombre();
	}

	public SecParParentesco getSecParParentesco() {
		if(personaEmergenciaH.getSecParParentesco()==null){
			personaEmergenciaH.setSecParParentesco(new SecParParentesco());
		}
		return personaEmergenciaH.getSecParParentesco();
	}

	public SecPerPersona getSecPerPersona() {
		return personaEmergenciaH.getSecPerPersona();
	}
	
	public void setAudFechaCreacion(Date audFechaCreacion) {
		personaEmergenciaH.setAudFechaCreacion(audFechaCreacion);
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		personaEmergenciaH.setAudFechaModificacion(audFechaModificacion);
	}
	
	public void setAudFechaCreacion(String audFechaCreacion) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			personaEmergenciaH.setAudFechaCreacion(sdf.parse(audFechaCreacion));
		} catch (ParseException e) {
		}
		
	}
	public void setAudFechaModificacion(String audFechaModificacion) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			personaEmergenciaH.setAudFechaModificacion(sdf.parse(audFechaModificacion));
		} catch (ParseException e) {
		}
	}
	

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		personaEmergenciaH.setAudUsuarioCreacion(audUsuarioCreacion);
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		personaEmergenciaH.setAudUsuarioModificacion(audUsuarioModificacion);
	}

	public void setPemApellidoCasada(String pemApellidoCasada) {
		personaEmergenciaH.setPemApellidoCasada(pemApellidoCasada);
	}

	public void setPemId(Integer pemId) {
		personaEmergenciaH.setPemId(pemId);
	}

	public void setPemPrimerApellido(String pemPrimerApellido) {
		personaEmergenciaH.setPemPrimerApellido(pemPrimerApellido);
	}

	public void setPemPrimerNombre(String pemPrimerNombre) {
		personaEmergenciaH.setPemPrimerNombre(pemPrimerNombre);
	}

	public void setPemSegundoApellido(String pemSegundoApellido) {
		personaEmergenciaH.setPemSegundoApellido(pemSegundoApellido);
	}

	public void setPemSegundoNombre(String pemSegundoNombre) {
		personaEmergenciaH.setPemSegundoNombre(pemSegundoNombre);
	}

	public void setPemTelefono(String pemTelefono) {
		personaEmergenciaH.setPemTelefono(pemTelefono);
	}

	public void setPemTercerNombre(String pemTercerNombre) {
		personaEmergenciaH.setPemTercerNombre(pemTercerNombre);
	}

	public void setSecParParentesco(SecParParentesco secParParentesco) {
		if(personaEmergenciaH.getSecParParentesco()==null){
			personaEmergenciaH.setSecParParentesco(new SecParParentesco());
		}
		personaEmergenciaH.setSecParParentesco(secParParentesco);
	}

	public void setSecPerPersona(SecPerPersona secPerPersona) {
		personaEmergenciaH.setSecPerPersona(secPerPersona);
	}
	
	public void setPerId(String perId) {
		if(personaEmergenciaH.getSecPerPersona()==null){
			personaEmergenciaH.setSecPerPersona(new SecPerPersona());
		}
		personaEmergenciaH.getSecPerPersona().setPerId(perId);
	}
	
	public String getPerId() {
		if(personaEmergenciaH.getSecPerPersona()==null){
			personaEmergenciaH.setSecPerPersona(new SecPerPersona());
		}
		return personaEmergenciaH.getSecPerPersona().getPerId();
	}
	
	public void setParId(Integer parId) {
		personaEmergenciaH.getSecParParentesco().setParId(parId);
	}
	
	public Integer getParId() {
		return personaEmergenciaH.getSecParParentesco().getParId();
	}

	public SecPemPersonaEmergencia getPersonaEmergenciaH() {
		return personaEmergenciaH;
	}

	public void setPersonaEmergenciaH(SecPemPersonaEmergencia personaEmergenciaH) {
		this.personaEmergenciaH = personaEmergenciaH;
	}

	
	public boolean isFillAuditoria() {
		return true;
	}
}