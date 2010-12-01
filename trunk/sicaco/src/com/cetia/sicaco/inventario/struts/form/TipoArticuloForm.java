package com.cetia.sicaco.inventario.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.hibernate.InvTarTipoArticulo;
import com.cetia.sicaco.struts.BasicForm;
import com.cetia.sicaco.struts.Constantes;

public class TipoArticuloForm extends BasicForm {
	
	private InvTarTipoArticulo invTarTipoArticulo = new InvTarTipoArticulo();
	private String redirectOnError; 
	private String mode;
	public String tarIdHidden;
	private String idTipo;
	
	public String getTarIdHidden() {
		return this.invTarTipoArticulo.getTarId();
	}

	public void setTarIdHidden(String tarIdHidden) {
		this.invTarTipoArticulo.setTarId(tarIdHidden);
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setInvTarTipoArticulo(InvTarTipoArticulo invTarTipoArticulo) {
		this.invTarTipoArticulo = invTarTipoArticulo;
	}

	public String getRedirectOnError() {
		return redirectOnError;
	}

	public void setRedirectOnError(String redirectOnError) {
		this.redirectOnError = redirectOnError;
	}

	public InvTarTipoArticulo getInvTarTipoArticulo() {
		return invTarTipoArticulo;
	}

	public String getAudFechaCreacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (invTarTipoArticulo.getAudFechaCreacion()!=null)?(sdf.format(invTarTipoArticulo.getAudFechaCreacion())):null;
	}

	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (invTarTipoArticulo.getAudUsuarioModificacion()!=null)?(sdf.format(invTarTipoArticulo.getAudFechaModificacion())):null;
	}
	
	public void setAudFechaCreacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			invTarTipoArticulo.setAudFechaCreacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public void setAudFechaModificacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			invTarTipoArticulo.setAudFechaModificacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}

	public String getAudUsuarioCreacion() {
		return invTarTipoArticulo.getAudUsuarioCreacion();
	}

	public String getAudUsuarioModificacion() {
		return invTarTipoArticulo.getAudUsuarioModificacion();
	}

	public String getTarDescripcion() {
		return invTarTipoArticulo.getTarDescripcion();
	}

	public String getTarNombre() {
		return invTarTipoArticulo.getTarNombre();
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		invTarTipoArticulo.setAudFechaCreacion(audFechaCreacion);
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		invTarTipoArticulo.setAudFechaModificacion(audFechaModificacion);
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		invTarTipoArticulo.setAudUsuarioCreacion(audUsuarioCreacion);
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		invTarTipoArticulo.setAudUsuarioModificacion(audUsuarioModificacion);
	}

	public void setTarDescripcion(String tarDescripcion) {
		invTarTipoArticulo.setTarDescripcion(tarDescripcion);
	}

	public void setTarNombre(String tarNombre) {
		invTarTipoArticulo.setTarNombre(tarNombre);
	}

	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return true;
	}

	
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

	public Set getInvArtArticulos() {
		return invTarTipoArticulo.getInvArtArticulos();
	}

	public String getTarId() {
		return invTarTipoArticulo.getTarId();
	}

	public void setInvArtArticulos(Set invArtArticulos) {
		invTarTipoArticulo.setInvArtArticulos(invArtArticulos);
	}

	public void setTarId(String tarId) {
		invTarTipoArticulo.setTarId(tarId);
	}

	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}
	
}
