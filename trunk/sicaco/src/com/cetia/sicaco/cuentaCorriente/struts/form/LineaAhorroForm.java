package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.struts.BasicForm;
import com.cetia.sicaco.hibernate.CtaLahLineaAhorro;
public class LineaAhorroForm extends BasicForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8583732058828616530L;
	private  boolean mdf = false;
	private CtaLahLineaAhorro ctaLahLineaAhorroH = new CtaLahLineaAhorro();
	
	
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
	
	public CtaLahLineaAhorro getCtaLahLineaAhorroH() {
		return ctaLahLineaAhorroH;
	}

	public void setCtaLahLineaAhorroH(CtaLahLineaAhorro ctaLahLineaAhorroH) {
		this.ctaLahLineaAhorroH = ctaLahLineaAhorroH;
	}

/*	public Set getCtaTahTipoAhorros_1() {
		return ctaLahLineaAhorroH.getCtaTahTipoAhorros_1();
	}*/

	public Set getCtaTahTipoAhorros() {
		return ctaLahLineaAhorroH.getCtaTahTipoAhorros();
	}

	public String getLahDescripcion() {
		return ctaLahLineaAhorroH.getLahDescripcion();
	}

	public String getLahDesde() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaLahLineaAhorroH.getLahDesde()!=null)?(sdf.format(ctaLahLineaAhorroH.getLahDesde())):null;
	
	}

	public String getLahHasta() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaLahLineaAhorroH.getLahHasta()!=null)?(sdf.format(ctaLahLineaAhorroH.getLahHasta())):null;
	
	}

	public Integer getLahId() {
		return ctaLahLineaAhorroH.getLahId();
	}

	public String getLahNombre() {
		return ctaLahLineaAhorroH.getLahNombre();
	}

	public void setCtaTahTipoAhorros(Set ctaTahTipoAhorros) {
		ctaLahLineaAhorroH.setCtaTahTipoAhorros(ctaTahTipoAhorros);
	}

	public void setLahDescripcion(String lahDescripcion) {
		ctaLahLineaAhorroH.setLahDescripcion(lahDescripcion);
	}

	public void setLahDesde(String lahDesde) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaLahLineaAhorroH.setLahDesde(sdf.parse(lahDesde));
		} catch (ParseException e) {
		}
	}

	public void setLahHasta(String lahHasta) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaLahLineaAhorroH.setLahHasta(sdf.parse(lahHasta));
		} catch (ParseException e) {
		}
	}

	public void setLahId(Integer lahId) {
		ctaLahLineaAhorroH.setLahId(lahId);
	}

	public void setLahNombre(String lahNombre) {
		ctaLahLineaAhorroH.setLahNombre(lahNombre);
	}

}
