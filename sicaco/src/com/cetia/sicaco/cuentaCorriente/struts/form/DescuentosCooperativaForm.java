package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorro;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCbaCuentaBancaria;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaSegSeguros;
import com.cetia.sicaco.hibernate.CtrEstEstado;
import com.cetia.sicaco.struts.BasicForm;

public class DescuentosCooperativaForm extends BasicForm {
	private CtaCasCuentaAsociado cuentaAsociadoH = new CtaCasCuentaAsociado();

	public String  getPreId() {
		return cuentaAsociadoH.getCtaPrePrestamo().getPreId();
	}

	public void setPreId(String preId) {
		this.cuentaAsociadoH.getCtaPrePrestamo().setPreId(preId);
	}

	
	public CtaCasCuentaAsociado getCuentaAsociadoH() {
		return cuentaAsociadoH;
	}

	public void setCuentaAsociadoH(CtaCasCuentaAsociado cuentaAsociadoH) {
		this.cuentaAsociadoH = cuentaAsociadoH;
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

	public Long getCasRefinanciado() {
		return cuentaAsociadoH.getCasRefinanciado();
	}

	/*public Float getCasValorApertura() {
		return cuentaAsociadoH.getCasValorApertura();
	}*/

	public CtaAscAsociado getCtaAscAsociado() {
		return cuentaAsociadoH.getCtaAscAsociado();
	}

	public Set getCtaBxcBeneficiariosCuentas() {
		return cuentaAsociadoH.getCtaBxcBeneficiariosCuentas();
	}

	public CtaCahCuentaAhorro getCtaCahCuentaAhorro() {
		return cuentaAsociadoH.getCtaCahCuentaAhorro();
	}

	public CtaCbaCuentaBancaria getCtaCbaCuentaBancaria() {
		return cuentaAsociadoH.getCtaCbaCuentaBancaria();
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return cuentaAsociadoH.getCtaPrePrestamo();
	}

	public CtaSegSeguros getCtaSegSeguros() {
		return cuentaAsociadoH.getCtaSegSeguros();
	}

	public Set getCtaTxaTransaccionxcuentaAsociados() {
		return cuentaAsociadoH.getCtaTxaTransaccionxcuentaAsociados();
	}

	public CtrEstEstado getCtrEstEstado() {
		return cuentaAsociadoH.getCtrEstEstado();
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

	public void setCasRefinanciado(Long casRefinanciado) {
		cuentaAsociadoH.setCasRefinanciado(casRefinanciado);
	}

	/*public void setCasValorApertura(Float casValorApertura) {
		cuentaAsociadoH.setCasValorApertura(casValorApertura);
	}*/

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		cuentaAsociadoH.setCtaAscAsociado(ctaAscAsociado);
	}

	public void setCtaBxcBeneficiariosCuentas(Set ctaBxcBeneficiariosCuentas) {
		cuentaAsociadoH
				.setCtaBxcBeneficiariosCuentas(ctaBxcBeneficiariosCuentas);
	}

	public void setCtaCahCuentaAhorro(CtaCahCuentaAhorro ctaCahCuentaAhorro) {
		cuentaAsociadoH.setCtaCahCuentaAhorro(ctaCahCuentaAhorro);
	}

	public void setCtaCbaCuentaBancaria(
			CtaCbaCuentaBancaria ctaCbaCuentaBancaria) {
		cuentaAsociadoH.setCtaCbaCuentaBancaria(ctaCbaCuentaBancaria);
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		cuentaAsociadoH.setCtaPrePrestamo(ctaPrePrestamo);
	}

	public void setCtaSegSeguros(CtaSegSeguros ctaSegSeguros) {
		cuentaAsociadoH.setCtaSegSeguros(ctaSegSeguros);
	}

	public void setCtaTxaTransaccionxcuentaAsociados(
			Set ctaTxaTransaccionxcuentaAsociados) {
		cuentaAsociadoH
				.setCtaTxaTransaccionxcuentaAsociados(ctaTxaTransaccionxcuentaAsociados);
	}

	public void setCtrEstEstado(CtrEstEstado ctrEstEstado) {
		cuentaAsociadoH.setCtrEstEstado(ctrEstEstado);
	}

	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
