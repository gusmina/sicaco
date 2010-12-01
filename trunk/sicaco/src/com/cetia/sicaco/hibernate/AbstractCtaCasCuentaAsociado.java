package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaCasCuentaAsociado entity provides the base persistence definition
 * of the CtaCasCuentaAsociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaCasCuentaAsociado implements
		java.io.Serializable {

	// Fields

	private Long casCuenta;
	private CtaPrePrestamo ctaPrePrestamo = new CtaPrePrestamo();
	private CtaCahCuentaAhorro ctaCahCuentaAhorro = new CtaCahCuentaAhorro();
	private CtaCbaCuentaBancaria ctaCbaCuentaBancaria = new CtaCbaCuentaBancaria();
	private CtaAscAsociado ctaAscAsociado = new CtaAscAsociado();
	private CtaSegSeguros ctaSegSeguros = new CtaSegSeguros();
	private CtrEstEstado ctrEstEstado = new CtrEstEstado();
	private CtaPxtPersonaExterna ctaPxtPersonaExterna = new CtaPxtPersonaExterna();
	private Double casValorApertura;
	private Date casFechaApertura;
	private String casPrincipal;
	private Date casFechaCierre;
	private Long casRefinanciado;
	private Long casPrestamoPaga;
	private Set ctaTxaTransaccionxcuentaAsociados = new HashSet(0);
	private Set ctaBxcBeneficiariosCuentas = new HashSet(0);
	private Set ctaNotNotases = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaCasCuentaAsociado() {
	}

	/** minimal constructor */
	public AbstractCtaCasCuentaAsociado(Long casCuenta,
			Double casValorApertura,
			Date casFechaApertura, String casPrincipal) {
		this.casCuenta = casCuenta;
		this.casValorApertura = casValorApertura;
		this.casFechaApertura = casFechaApertura;
		this.casPrincipal = casPrincipal;
	}

	/** full constructor */
	public AbstractCtaCasCuentaAsociado(Long casCuenta,
			CtaPrePrestamo ctaPrePrestamo,
			CtaCahCuentaAhorro ctaCahCuentaAhorro,
			CtaCbaCuentaBancaria ctaCbaCuentaBancaria,
			CtaAscAsociado ctaAscAsociado, CtaSegSeguros ctaSegSeguros,
			CtrEstEstado ctrEstEstado, CtaPxtPersonaExterna ctaPxtPersonaExterna,
			Double casValorApertura,
			Date casFechaApertura, String casPrincipal, Date casFechaCierre,
			Long casRefinanciado, Long casPrestamoPaga,
			Set ctaTxaTransaccionxcuentaAsociados,
			Set ctaBxcBeneficiariosCuentas,
			Set ctaNotNotases) {
		super();
		this.casCuenta = casCuenta;
		this.ctaPrePrestamo = ctaPrePrestamo;
		this.ctaCahCuentaAhorro = ctaCahCuentaAhorro;
		this.ctaCbaCuentaBancaria = ctaCbaCuentaBancaria;
		this.ctaAscAsociado = ctaAscAsociado;
		this.ctaSegSeguros = ctaSegSeguros;
		this.ctrEstEstado = ctrEstEstado;
		this.ctaPxtPersonaExterna = ctaPxtPersonaExterna;
		this.casValorApertura = casValorApertura;
		this.casFechaApertura = casFechaApertura;
		this.casPrincipal = casPrincipal;
		this.casFechaCierre = casFechaCierre;
		this.casRefinanciado = casRefinanciado;
		this.casPrestamoPaga = casPrestamoPaga;
		this.ctaTxaTransaccionxcuentaAsociados = ctaTxaTransaccionxcuentaAsociados;
		this.ctaBxcBeneficiariosCuentas = ctaBxcBeneficiariosCuentas;
		this.ctaNotNotases = ctaNotNotases;
	}
	
	// Property accessors

	public Long getCasCuenta() {
		return this.casCuenta;
	}

	public void setCasCuenta(Long casCuenta) {
		this.casCuenta = casCuenta;
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return this.ctaPrePrestamo;
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		this.ctaPrePrestamo = ctaPrePrestamo;
	}

	public CtaCahCuentaAhorro getCtaCahCuentaAhorro() {
		return this.ctaCahCuentaAhorro;
	}

	public void setCtaCahCuentaAhorro(CtaCahCuentaAhorro ctaCahCuentaAhorro) {
		this.ctaCahCuentaAhorro = ctaCahCuentaAhorro;
	}

	public CtaCbaCuentaBancaria getCtaCbaCuentaBancaria() {
		return this.ctaCbaCuentaBancaria;
	}

	public void setCtaCbaCuentaBancaria(
			CtaCbaCuentaBancaria ctaCbaCuentaBancaria) {
		this.ctaCbaCuentaBancaria = ctaCbaCuentaBancaria;
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return this.ctaAscAsociado;
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		this.ctaAscAsociado = ctaAscAsociado;
	}

	public CtaSegSeguros getCtaSegSeguros() {
		return this.ctaSegSeguros;
	}

	public void setCtaSegSeguros(CtaSegSeguros ctaSegSeguros) {
		this.ctaSegSeguros = ctaSegSeguros;
	}

	public Double getCasValorApertura() {
		return this.casValorApertura;
	}

	public void setCasValorApertura(Double casValorApertura) {
		this.casValorApertura = casValorApertura;
	}

	public Date getCasFechaApertura() {
		return this.casFechaApertura;
	}

	public void setCasFechaApertura(Date casFechaApertura) {
		this.casFechaApertura = casFechaApertura;
	}

	public String getCasPrincipal() {
		return this.casPrincipal;
	}

	public void setCasPrincipal(String casPrincipal) {
		this.casPrincipal = casPrincipal;
	}

	public Date getCasFechaCierre() {
		return this.casFechaCierre;
	}

	public void setCasFechaCierre(Date casFechaCierre) {
		this.casFechaCierre = casFechaCierre;
	}

	public Set getCtaBxcBeneficiariosCuentas() {
		return this.ctaBxcBeneficiariosCuentas;
	}

	public void setCtaBxcBeneficiariosCuentas(Set ctaBxcBeneficiariosCuentas) {
		this.ctaBxcBeneficiariosCuentas = ctaBxcBeneficiariosCuentas;
	}

	public Set getCtaTxaTransaccionxcuentaAsociados() {
		return this.ctaTxaTransaccionxcuentaAsociados;
	}

	public void setCtaTxaTransaccionxcuentaAsociados(
			Set ctaTxaTransaccionxcuentaAsociados) {
		this.ctaTxaTransaccionxcuentaAsociados = ctaTxaTransaccionxcuentaAsociados;
	}

	public CtrEstEstado getCtrEstEstado() {
		return ctrEstEstado;
	}

	public void setCtrEstEstado(CtrEstEstado ctrEstEstado) {
		this.ctrEstEstado = ctrEstEstado;
	}

	public Long getCasRefinanciado() {
		return casRefinanciado;
	}

	public void setCasRefinanciado(Long casRefinanciado) {
		this.casRefinanciado = casRefinanciado;
	}

	public Long getCasPrestamoPaga() {
		return casPrestamoPaga;
	}

	public void setCasPrestamoPaga(Long casPrestamoPaga) {
		this.casPrestamoPaga = casPrestamoPaga;
	}

	public CtaPxtPersonaExterna getCtaPxtPersonaExterna() {
		return ctaPxtPersonaExterna;
	}

	public void setCtaPxtPersonaExterna(CtaPxtPersonaExterna ctaPxtPersonaExterna) {
		this.ctaPxtPersonaExterna = ctaPxtPersonaExterna;
	}

	public Set getCtaNotNotases() {
		return ctaNotNotases;
	}

	public void setCtaNotNotases(Set ctaNotNotases) {
		this.ctaNotNotases = ctaNotNotases;
	}

}