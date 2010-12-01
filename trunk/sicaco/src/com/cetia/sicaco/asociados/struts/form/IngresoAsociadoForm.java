package com.cetia.sicaco.asociados.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaInaIngresosxasociado;
import com.cetia.sicaco.hibernate.CtaNotNotas;
import com.cetia.sicaco.struts.BasicForm;

public class IngresoAsociadoForm extends BasicForm {
	
	CtaInaIngresosxasociado ctaInaIngresosxasociadoH = new CtaInaIngresosxasociado();
	
	

	public CtaInaIngresosxasociado getCtaInaIngresosxasociadoH() {
		return ctaInaIngresosxasociadoH;
	}

	public String  getAscId() {
		 return ctaInaIngresosxasociadoH.getCtaAscAsociado().getAscId();
	}
	
	public void setAscId(String ascId) {
		this.ctaInaIngresosxasociadoH.getCtaAscAsociado().setAscId(ascId);
	}

	public void setCtaInaIngresosxasociadoH(
			CtaInaIngresosxasociado ctaInaIngresosxasociadoH) {
		this.ctaInaIngresosxasociadoH = ctaInaIngresosxasociadoH;
	}

	

	public CtaAscAsociado getCtaAscAsociado() {
		return ctaInaIngresosxasociadoH.getCtaAscAsociado();
	}



	public CtaNotNotas getCtaNotNotas() {
		return ctaInaIngresosxasociadoH.getCtaNotNotas();
	}

	public Integer getNotId() {
		return ctaInaIngresosxasociadoH.getCtaNotNotas().getNotId();
	}
	
	public void setNotId(Integer notId) {
		ctaInaIngresosxasociadoH.getCtaNotNotas().setNotId(notId);
	}

	public String getInaFechaIngreso() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaInaIngresosxasociadoH.getInaFechaIngreso()!=null)?(sdf.format(ctaInaIngresosxasociadoH.getInaFechaIngreso())):null;
	}


	public String getInaFechaSalida() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaInaIngresosxasociadoH.getInaFechaSalida()!=null)?(sdf.format(ctaInaIngresosxasociadoH.getInaFechaSalida())):null;
	}



	public Integer getInaId() {
		return ctaInaIngresosxasociadoH.getInaId();
	}



	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		ctaInaIngresosxasociadoH.setCtaAscAsociado(ctaAscAsociado);
	}



	public void setCtaNotNotas(CtaNotNotas ctaNotNotas) {
		ctaInaIngresosxasociadoH.setCtaNotNotas(ctaNotNotas);
	}



	public void setInaFechaIngreso(String inaFechaIngreso) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaInaIngresosxasociadoH.setInaFechaIngreso(sdf.parse(inaFechaIngreso));
		} catch (ParseException e) {
		}
	}



	public void setInaFechaSalida(String inaFechaSalida) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaInaIngresosxasociadoH.setInaFechaSalida(sdf.parse(inaFechaSalida));
		} catch (ParseException e) {
		}
	}



	public void setInaId(Integer inaId) {
		ctaInaIngresosxasociadoH.setInaId(inaId);
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
