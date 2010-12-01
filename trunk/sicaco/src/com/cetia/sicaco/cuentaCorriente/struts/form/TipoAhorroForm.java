package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.hibernate.CtaLahLineaAhorro;
import com.cetia.sicaco.hibernate.CtaPlmPlanMeses;
import com.cetia.sicaco.hibernate.CtaTahTipoAhorro;
import com.cetia.sicaco.hibernate.CtaTinTasaInteres;
import com.cetia.sicaco.struts.BasicForm;

public class TipoAhorroForm extends BasicForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2471374409608983433L;
	private  boolean mdf = false;
	private  boolean duracion = false;
	private CtaTahTipoAhorro ctaTahTipoAhorroH = new CtaTahTipoAhorro();
	
	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isMdf() {

		return mdf;
	}

	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}
	
	public CtaTahTipoAhorro getCtaTahTipoAhorroH() {
		return ctaTahTipoAhorroH;
	}

	public void setCtaTahTipoAhorroH(CtaTahTipoAhorro ctaTahTipoAhorroH) {
		this.ctaTahTipoAhorroH = ctaTahTipoAhorroH;
	}

	public Set getCtaCahCuentaAhorros() {
		return ctaTahTipoAhorroH.getCtaCahCuentaAhorros();
	}

	public CtaLahLineaAhorro getCtaLahLineaAhorro() {
		return ctaTahTipoAhorroH.getCtaLahLineaAhorro();
	}

	public CtaPlmPlanMeses getCtaPlmPlanMeses() {
		return ctaTahTipoAhorroH.getCtaPlmPlanMeses();
	}

	public CtaTinTasaInteres getCtaTinTasaInteres() {
		return ctaTahTipoAhorroH.getCtaTinTasaInteres();
	}

	public String getTahDescripcion() {
		return ctaTahTipoAhorroH.getTahDescripcion();
	}

	public Integer getTahId() {
		return ctaTahTipoAhorroH.getTahId();
	}

	public String getTahNombre() {
		return ctaTahTipoAhorroH.getTahNombre();
	}

	public void setCtaCahCuentaAhorros(Set ctaCahCuentaAhorros) {
		ctaTahTipoAhorroH.setCtaCahCuentaAhorros(ctaCahCuentaAhorros);
	}

	public void setCtaLahLineaAhorro(CtaLahLineaAhorro ctaLahLineaAhorro) {
		ctaTahTipoAhorroH.setCtaLahLineaAhorro(ctaLahLineaAhorro);
	}

	public void setCtaPlmPlanMeses(CtaPlmPlanMeses ctaPlmPlanMeses) {
		ctaTahTipoAhorroH.setCtaPlmPlanMeses(ctaPlmPlanMeses);
	}

	public void setCtaTinTasaInteres(CtaTinTasaInteres ctaTinTasaInteres) {
		ctaTahTipoAhorroH.setCtaTinTasaInteres(ctaTinTasaInteres);
	}

	public void setTahDescripcion(String tahDescripcion) {
		ctaTahTipoAhorroH.setTahDescripcion(tahDescripcion);
	}

	public void setTahId(Integer tahId) {
		ctaTahTipoAhorroH.setTahId(tahId);
	}

	public void setTahNombre(String tahNombre) {
		ctaTahTipoAhorroH.setTahNombre(tahNombre);
	}

	public String getTahFechaFin() {
		Date tahFechaFin  = ctaTahTipoAhorroH.getTahFechaFin();
		try {
			return new  SimpleDateFormat("dd-MM-yyyy").format(tahFechaFin);
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
	
	public void setTahFechaFin(String tahFechaFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try{
			ctaTahTipoAhorroH.setTahFechaFin(sdf.parse(tahFechaFin));
		}catch (ParseException e) {
		}
	}
	
	public void setTahFechaFin(Date tahFechaFin) {
		ctaTahTipoAhorroH.setTahFechaFin(tahFechaFin);
	}

	public boolean isDuracion() {
		return duracion;
	}

	public void setDuracion(boolean duracion) {
		this.duracion = duracion;
	}
}
