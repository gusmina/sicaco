/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.control.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.hibernate.CtrBanBanco;
import com.cetia.sicaco.hibernate.CtrCckControlCheques;
import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-24-2008
 * 
 * XDoclet definition:
 * @struts.form name="controlChequeForm"
 */
public class ControlChequeForm extends BasicForm {
	/*
	 * Generated Methods
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -6765601275120544772L;
	CtrCckControlCheques controlChequesH = new CtrCckControlCheques();
	
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
		return (controlChequesH.getAudFechaCreacion()!=null)?(sdf.format(controlChequesH.getAudFechaCreacion())):null;
	}

	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (controlChequesH.getAudFechaModificacion()!=null)?(sdf.format(controlChequesH.getAudFechaModificacion())):null;
	}

	public String getAudUsuarioCreacion() {
		return controlChequesH.getAudUsuarioCreacion();
	}

	public String getAudUsuarioModificacion() {
		return controlChequesH.getAudUsuarioModificacion();
	}

	public Integer getCckCorrFin() {
		return controlChequesH.getCckCorrFin();
	}

	public Integer getCckCorrIni() {
		return controlChequesH.getCckCorrIni();
	}

	public Integer getCckId() {
		return controlChequesH.getCckId();
	}

	public String getCckSerie() {
		return controlChequesH.getCckSerie();
	}

	public CtrBanBanco getCtrBanBanco() {
		return controlChequesH.getCtrBanBanco();
	}

	public Set getCtrRckRepositorioChequeses() {
		return controlChequesH.getCtrRckRepositorioChequeses();
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		controlChequesH.setAudFechaCreacion(audFechaCreacion);
	}

	public void setAudFechaCreacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			controlChequesH.setAudFechaCreacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public void setAudFechaModificacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			controlChequesH.setAudFechaModificacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		controlChequesH.setAudFechaModificacion(audFechaModificacion);
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		controlChequesH.setAudUsuarioCreacion(audUsuarioCreacion);
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		controlChequesH.setAudUsuarioModificacion(audUsuarioModificacion);
	}

	public void setCckCorrFin(Integer cckCorrFin) {
		controlChequesH.setCckCorrFin(cckCorrFin);
	}

	public void setCckCorrIni(Integer cckCorrIni) {
		controlChequesH.setCckCorrIni(cckCorrIni);
	}

	public void setCckId(Integer cckId) {
		controlChequesH.setCckId(cckId);
	}

	public void setCckSerie(String cckSerie) {
		controlChequesH.setCckSerie(cckSerie);
	}

	public void setCtrBanBanco(CtrBanBanco ctrBanBanco) {
		controlChequesH.setCtrBanBanco(ctrBanBanco);
	}

	public void setCtrRckRepositorioChequeses(Set ctrRckRepositorioChequeses) {
		controlChequesH
				.setCtrRckRepositorioChequeses(ctrRckRepositorioChequeses);
	}

	public Integer getCckDigitos() {
		return controlChequesH.getCckDigitos();
	}

	public void setCckDigitos(Integer cckDigitos) {
		controlChequesH.setCckDigitos(cckDigitos);
	}

	public CtrCckControlCheques getControlChequesH() {
		return controlChequesH;
	}

	public void setControlChequesH(CtrCckControlCheques controlChequesH) {
		this.controlChequesH = controlChequesH;
	}

	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return true;
	}
}