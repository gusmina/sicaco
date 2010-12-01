package com.cetia.sicaco.control.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.hibernate.CtrCfcControlFacturacion;
import com.cetia.sicaco.hibernate.CtrEmpEmpresa;
import com.cetia.sicaco.struts.BasicForm;

public class ControlFacturaForm extends BasicForm {

	CtrCfcControlFacturacion controlFacturacionH = new CtrCfcControlFacturacion();
	
	
	
	public CtrCfcControlFacturacion getControlFacturacionH() {
		return controlFacturacionH;
	}



	public void setControlFacturacionH(CtrCfcControlFacturacion controlFacturacionH) {
		this.controlFacturacionH = controlFacturacionH;
	}



	public String getAudFechaCreacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (controlFacturacionH.getAudFechaCreacion()!=null)?(sdf.format(controlFacturacionH.getAudFechaCreacion())):null;
	}



	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (controlFacturacionH.getAudFechaModificacion()!=null)?(sdf.format(controlFacturacionH.getAudFechaModificacion())):null;
	}



	public String getAudUsuarioCreacion() {
		return controlFacturacionH.getAudUsuarioCreacion();
	}



	public String getAudUsuarioModificacion() {
		return controlFacturacionH.getAudUsuarioModificacion();
	}



	public Integer getCfcCorrFin() {
		return controlFacturacionH.getCfcCorrFin();
	}



	public Integer getCfcCorrIni() {
		return controlFacturacionH.getCfcCorrIni();
	}



	public String getCfcSerie() {
		return controlFacturacionH.getCfcSerie();
	}



	public CtrEmpEmpresa getCtrEmpEmpresa() {
		return controlFacturacionH.getCtrEmpEmpresa();
	}



	public Set getCtrRfcRepositorioFacturases() {
		return controlFacturacionH.getCtrRfcRepositorioFacturases();
	}



	public void setAudFechaCreacion(Date audFechaCreacion) {
		controlFacturacionH.setAudFechaCreacion(audFechaCreacion);
	}

	public void setAudFechaCreacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			controlFacturacionH.setAudFechaCreacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public void setAudFechaModificacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			controlFacturacionH.setAudFechaModificacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}


	public void setAudFechaModificacion(Date audFechaModificacion) {
		controlFacturacionH.setAudFechaModificacion(audFechaModificacion);
	}



	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		controlFacturacionH.setAudUsuarioCreacion(audUsuarioCreacion);
	}



	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		controlFacturacionH.setAudUsuarioModificacion(audUsuarioModificacion);
	}



	public void setCfcCorrFin(Integer cfcCorrFin) {
		controlFacturacionH.setCfcCorrFin(cfcCorrFin);
	}



	public void setCfcCorrIni(Integer cfcCorrIni) {
		controlFacturacionH.setCfcCorrIni(cfcCorrIni);
	}



	public void setCfcSerie(String cfcSerie) {
		controlFacturacionH.setCfcSerie(cfcSerie);
	}



	public void setCtrEmpEmpresa(CtrEmpEmpresa ctrEmpEmpresa) {
		controlFacturacionH.setCtrEmpEmpresa(ctrEmpEmpresa);
	}



	public void setCtrRfcRepositorioFacturases(Set ctrRfcRepositorioFacturases) {
		controlFacturacionH
				.setCtrRfcRepositorioFacturases(ctrRfcRepositorioFacturases);
	}


	public Integer getCfcDigitos() {
		return controlFacturacionH.getCfcDigitos();
	}



	public void setCfcDigitos(Integer cfcDigitos) {
		controlFacturacionH.setCfcDigitos(cfcDigitos);
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
