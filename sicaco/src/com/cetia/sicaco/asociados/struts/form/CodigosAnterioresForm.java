package com.cetia.sicaco.asociados.struts.form;

import java.util.Date;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaCntCodigosAnteriores;
import com.cetia.sicaco.struts.BasicForm;

public class CodigosAnterioresForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8606415263073616901L;
	private CtaCntCodigosAnteriores codigosAnterioresH = new CtaCntCodigosAnteriores();
	
	
	
	public CtaCntCodigosAnteriores getCodigosAnterioresH() {
		return codigosAnterioresH;
	}



	public void setCodigosAnterioresH(CtaCntCodigosAnteriores codigosAnterioresH) {
		this.codigosAnterioresH = codigosAnterioresH;
	}



	public String getCntCod() {
		return codigosAnterioresH.getCntCod();
	}



	public Date getCntFechaFin() {
		return codigosAnterioresH.getCntFechaFin();
	}



	public Date getCntFechaIni() {
		return codigosAnterioresH.getCntFechaIni();
	}

	public String getAscId() {
		return codigosAnterioresH.getCtaAscAsociado().getAscId();
	}

	public Integer getCntId() {
		return codigosAnterioresH.getCntId();
	}



	public CtaAscAsociado getCtaAscAsociado() {
		return codigosAnterioresH.getCtaAscAsociado();
	}



	public void setCntCod(String cntCod) {
		codigosAnterioresH.setCntCod(cntCod);
	}



	public void setCntFechaFin(Date cntFechaFin) {
		codigosAnterioresH.setCntFechaFin(cntFechaFin);
	}



	public void setCntFechaIni(Date cntFechaIni) {
		codigosAnterioresH.setCntFechaIni(cntFechaIni);
	}



	public void setCntId(Integer cntId) {
		codigosAnterioresH.setCntId(cntId);
	}



	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		codigosAnterioresH.setCtaAscAsociado(ctaAscAsociado);
	}
	
	public void setAscId(String ascId) {
		codigosAnterioresH.getCtaAscAsociado().setAscId(ascId);
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
