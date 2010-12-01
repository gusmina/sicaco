package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorro;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCbaCuentaBancaria;
import com.cetia.sicaco.hibernate.CtaNotNotas;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaSegSeguros;
import com.cetia.sicaco.hibernate.CtrEstEstado;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.struts.BasicForm;

public class CuentaAsociadoForm extends BasicForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1122910952181921063L;
	private CtaCasCuentaAsociado ctaCasCuentaAsociadoH = new CtaCasCuentaAsociado();
	private CtaAscAsociado ctaAscAsociadoH = new CtaAscAsociado();
	private String referenciaCuenta = "";
	private String tipoCuentaMadre = "";
	private boolean mdf;
	//la lista de tipòs de cuentas esta pendiente
	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

	

	public String getAscCodigoAsociado() {
		return ctaAscAsociadoH.getAscCodigoAsociado();
	}



	public void setAscCodigoAsociado(String ascCodigoAsociado) {
		ctaAscAsociadoH.setAscCodigoAsociado(ascCodigoAsociado);
	}



	public CtaAscAsociado getCtaAscAsociadoH() {
		return ctaAscAsociadoH;
	}



	public void setCtaAscAsociadoH(CtaAscAsociado ctaAscAsociadoH) {
		this.ctaAscAsociadoH = ctaAscAsociadoH;
	}



	public CtaCasCuentaAsociado getCtaCasCuentaAsociadoH() {
		return ctaCasCuentaAsociadoH;
	}


	public void setCtaCasCuentaAsociadoH(CtaCasCuentaAsociado ctaCasCuentaAsociadoH) {
		this.ctaCasCuentaAsociadoH = ctaCasCuentaAsociadoH;
	}

	public String getTipoCuentaMadre() {
		return tipoCuentaMadre;
	}


	public void setTipoCuentaMadre(String tipoCuentaMadre) {
		this.tipoCuentaMadre = tipoCuentaMadre;
	}


	public Long getCasCuenta() {
		return ctaCasCuentaAsociadoH.getCasCuenta();
	}


	public String getCasFechaApertura() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaCasCuentaAsociadoH.getCasFechaApertura()!=null)?(sdf.format(ctaCasCuentaAsociadoH.getCasFechaApertura())):null;
		
	}


	public String getCasFechaCierre() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaCasCuentaAsociadoH.getCasFechaCierre()!=null)?(sdf.format(ctaCasCuentaAsociadoH.getCasFechaCierre())):null;
	}


	public String getCasPrincipal() {
		return ctaCasCuentaAsociadoH.getCasPrincipal();
	}


	public Double getCasValorApertura() {
		return ctaCasCuentaAsociadoH.getCasValorApertura();
	}


	public CtaAscAsociado getCtaAscAsociado() {
		return ctaCasCuentaAsociadoH.getCtaAscAsociado();
	}


	public CtaCahCuentaAhorro getCtaCahCuentaAhorro() {
		return ctaCasCuentaAsociadoH.getCtaCahCuentaAhorro();
	}


	public CtaCbaCuentaBancaria getCtaCbaCuentaBancaria() {
		return ctaCasCuentaAsociadoH.getCtaCbaCuentaBancaria();
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return ctaCasCuentaAsociadoH.getCtaPrePrestamo();
	}


	public CtaSegSeguros getCtaSegSeguros() {
		return ctaCasCuentaAsociadoH.getCtaSegSeguros();
	}


	public Set getCtaTxaTransaccionxcuentaAsociados() {
		return ctaCasCuentaAsociadoH.getCtaTxaTransaccionxcuentaAsociados();
	}


	public void setCasCuenta(Long casCuenta) {
		ctaCasCuentaAsociadoH.setCasCuenta(casCuenta);
	}


	public void setCasFechaApertura(String casFechaApertura) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaCasCuentaAsociadoH.setCasFechaApertura(sdf.parse(casFechaApertura));
		} catch (ParseException e) {
		}
		
	}


	public void setCasFechaCierre(String casFechaCierre) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaCasCuentaAsociadoH.setCasFechaCierre(sdf.parse(casFechaCierre));
		} catch (ParseException e) {
		}
	}


	public void setCasPrincipal(String casPrincipal) {
		ctaCasCuentaAsociadoH.setCasPrincipal(casPrincipal);
	}


	public void setCasValorApertura(Double casValorApertura) {
		ctaCasCuentaAsociadoH.setCasValorApertura(casValorApertura);
	}


	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		ctaCasCuentaAsociadoH.setCtaAscAsociado(ctaAscAsociado);
	}


	public void setCtaCahCuentaAhorro(CtaCahCuentaAhorro ctaCahCuentaAhorro) {
		ctaCasCuentaAsociadoH.setCtaCahCuentaAhorro(ctaCahCuentaAhorro);
	}


	public void setCtaCbaCuentaBancaria(
			CtaCbaCuentaBancaria ctaCbaCuentaBancaria) {
		ctaCasCuentaAsociadoH.setCtaCbaCuentaBancaria(ctaCbaCuentaBancaria);
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		ctaCasCuentaAsociadoH.setCtaPrePrestamo(ctaPrePrestamo);
	}


	public void setCtaSegSeguros(CtaSegSeguros ctaSegSeguros) {
		ctaCasCuentaAsociadoH.setCtaSegSeguros(ctaSegSeguros);
	}


	public void setCtaTxaTransaccionxcuentaAsociados(
			Set ctaTxaTransaccionxcuentaAsociados) {
		ctaCasCuentaAsociadoH
				.setCtaTxaTransaccionxcuentaAsociados(ctaTxaTransaccionxcuentaAsociados);
	}


	public String getReferenciaCuenta() {
		return referenciaCuenta;
	}


	public void setReferenciaCuenta(String referenciaCuenta) {
		this.referenciaCuenta = referenciaCuenta;
	}


	public String getAscAsociadoPadre() {
		return ctaAscAsociadoH.getAscAsociadoPadre();
	}


	public String getAscCodigo() {
		return ctaAscAsociadoH.getAscCodigo();
	}


	public String getAscId() {
		return ctaAscAsociadoH.getAscId();
	}


	public String getAscNombreNit() {
		return ctaAscAsociadoH.getAscNombreNit();
	}


	public CtaNotNotas getCtaNotNotas() {
		return ctaAscAsociadoH.getCtaNotNotas();
	}

	public Integer getEstId() {
		return ctaAscAsociadoH.getEstId();
	}


	public void setEstId(Integer estId) {
		ctaAscAsociadoH.setEstId(estId);
	}


	public void setAscAsociadoPadre(String ascAsociadoPadre) {
		ctaAscAsociadoH.setAscAsociadoPadre(ascAsociadoPadre);
	}


	public void setAscCodigo(String ascCodigo) {
		ctaAscAsociadoH.setAscCodigo(ascCodigo);
	}


	public void setAscId(String ascId) {
		ctaAscAsociadoH.setAscId(ascId);
	}


	public void setAscNombreNit(String ascNombreNit) {
		ctaAscAsociadoH.setAscNombreNit(ascNombreNit);
	}


	public boolean isMdf() {
		return mdf;
	}


	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}


	public Set getCtaBxcBeneficiariosCuentas() {
		return ctaCasCuentaAsociadoH.getCtaBxcBeneficiariosCuentas();
	}


	public void setCtaBxcBeneficiariosCuentas(Set ctaBxcBeneficiariosCuentas) {
		ctaCasCuentaAsociadoH
				.setCtaBxcBeneficiariosCuentas(ctaBxcBeneficiariosCuentas);
	}

	public CtrEstEstado getCtrEstEstado() {
		return ctaCasCuentaAsociadoH.getCtrEstEstado();
	}

	public void setCtrEstEstado(CtrEstEstado ctrEstEstado) {
		ctaCasCuentaAsociadoH.setCtrEstEstado(ctrEstEstado);
	}


	public SecPerPersona getSecPerPersona() {
		return ctaAscAsociadoH.getSecPerPersona();
	}


	public void setSecPerPersona(SecPerPersona secPerPersona) {
		ctaAscAsociadoH.setSecPerPersona(secPerPersona);
	}

}
