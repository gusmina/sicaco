package com.cetia.sicaco.asociados.struts.form;

import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCbaCuentaBancaria;
import com.cetia.sicaco.hibernate.CtaTcuTipoCuenta;
import com.cetia.sicaco.hibernate.CtrBanBanco;
import com.cetia.sicaco.struts.BasicForm;


public class CuentaBancariaForm extends BasicForm {
	
	private CtaCbaCuentaBancaria cuentaBancariaH = new CtaCbaCuentaBancaria();
	private CtaCasCuentaAsociado cuentaAsociadoH = new CtaCasCuentaAsociado();
	private boolean mdf;
	private String ascId2;
	
	
	public String getCbaCuenta() {
		return cuentaBancariaH.getCbaCuenta();
	}
	public String getCbaId() {
		return cuentaBancariaH.getCbaId();
	}
	public Set getCtaCasCuentaAsociados() {
		return cuentaBancariaH.getCtaCasCuentaAsociados();
	}
	public Set getCtaPrePrestamos() {
		return cuentaBancariaH.getCtaPrePrestamos();
	}
	public Set getCtaStbSolTransBancs() {
		return cuentaBancariaH.getCtaStbSolTransBancs();
	}
	public CtaTcuTipoCuenta getCtaTcuTipoCuenta() {
		return cuentaBancariaH.getCtaTcuTipoCuenta();
	}
	public void setCbaCuenta(String cbaCuenta) {
		cuentaBancariaH.setCbaCuenta(cbaCuenta);
	}
	public void setCbaId(String cbaId) {
		cuentaBancariaH.setCbaId(cbaId);
	}
	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		cuentaBancariaH.setCtaCasCuentaAsociados(ctaCasCuentaAsociados);
	}
	public void setCtaPrePrestamos(Set ctaPrePrestamos) {
		cuentaBancariaH.setCtaPrePrestamos(ctaPrePrestamos);
	}
	public void setCtaStbSolTransBancs(Set ctaStbSolTransBancs) {
		cuentaBancariaH.setCtaStbSolTransBancs(ctaStbSolTransBancs);
	}
	public void setCtaTcuTipoCuenta(CtaTcuTipoCuenta ctaTcuTipoCuenta) {
		cuentaBancariaH.setCtaTcuTipoCuenta(ctaTcuTipoCuenta);
	}
	public CtaCbaCuentaBancaria getCuentaBancariaH() {
		return cuentaBancariaH;
	}
	public void setCuentaBancariaH(CtaCbaCuentaBancaria cuentaBancariaH) {
		this.cuentaBancariaH = cuentaBancariaH;
	}
	public CtaCasCuentaAsociado getCuentaAsociadoH() {
		return cuentaAsociadoH;
	}
	public void setCuentaAsociadoH(CtaCasCuentaAsociado cuentaAsociadoH) {
		this.cuentaAsociadoH = cuentaAsociadoH;
	}
	public boolean isMdf() {
		return mdf;
	}
	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}
	public Long getCasCuenta() {
		return cuentaAsociadoH.getCasCuenta();
	}
	public Date getCasFechaApertura() {
		return cuentaAsociadoH.getCasFechaApertura();
	}
	public Date getCasFechaCierre() {
		return cuentaAsociadoH.getCasFechaCierre();
	}
	public String getCasPrincipal() {
		return cuentaAsociadoH.getCasPrincipal();
	}
	public Double getCasValorApertura() {
		return cuentaAsociadoH.getCasValorApertura();
	}
	public CtaAscAsociado getCtaAscAsociado() {
		return cuentaAsociadoH.getCtaAscAsociado();
	}
	
	public String getAscId() {
		return cuentaAsociadoH.getCtaAscAsociado().getAscId();
	}

	public void setAscId(String ascId) {
		cuentaAsociadoH.getCtaAscAsociado().setAscId(ascId);
	}

	public void setCasCuenta(Long casCuenta) {
		cuentaAsociadoH.setCasCuenta(casCuenta);
	}
	public void setCasFechaApertura(Date casFechaApertura) {
		cuentaAsociadoH.setCasFechaApertura(casFechaApertura);
	}
	public void setCasFechaCierre(Date casFechaCierre) {
		cuentaAsociadoH.setCasFechaCierre(casFechaCierre);
	}
	public void setCasPrincipal(String casPrincipal) {
		cuentaAsociadoH.setCasPrincipal(casPrincipal);
	}
	public void setCasValorApertura(Double casValorApertura) {
		cuentaAsociadoH.setCasValorApertura(casValorApertura);
	}
	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		cuentaAsociadoH.setCtaAscAsociado(ctaAscAsociado);
	}


	
	public CtrBanBanco getCtrBanBanco() {
		return cuentaBancariaH.getCtrBanBanco();
	}
	public void setCtrBanBanco(CtrBanBanco ctrBanBanco) {
		cuentaBancariaH.setCtrBanBanco(ctrBanBanco);
	}
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}
	public Set getCtaBxcBeneficiariosCuentas() {
		return cuentaAsociadoH.getCtaBxcBeneficiariosCuentas();
	}
	public void setCtaBxcBeneficiariosCuentas(Set ctaBxcBeneficiariosCuentas) {
		cuentaAsociadoH
				.setCtaBxcBeneficiariosCuentas(ctaBxcBeneficiariosCuentas);
	}
	
	public String getAscId2() {
		return ascId2;
	}
	
	public void setAscId2(String ascId2) {
		this.ascId2 = ascId2;
	}
	
}
