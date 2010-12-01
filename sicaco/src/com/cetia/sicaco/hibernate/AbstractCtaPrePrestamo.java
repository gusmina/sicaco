package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaPrePrestamo entity provides the base persistence definition of the
 * CtaPrePrestamo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaPrePrestamo implements java.io.Serializable {

	// Fields

	private String preId;
	private CtaTprTipoPrestamo ctaTprTipoPrestamo = new CtaTprTipoPrestamo();
	private CtaTinTasaInteres ctaTinTasaInteres = new CtaTinTasaInteres();
	private CtaSegSeguros ctaSegSeguros = new CtaSegSeguros();
	private CtaCbaCuentaBancaria ctaCbaCuentaBancaria = new CtaCbaCuentaBancaria();
	private Long casCuenta;
	private String preReferencia;
	private Double preCuota;
	private Double preMontoSolicitado;
	private Double preSaldoActualT;
	private Date preFechaSolicitud;
	private Double preInteresAcumulado;
	private Double preGastoAbogado;
	private Double preMoraEmbargo;
	private Double preLiquidoARecibir;
	private Integer preNumCuotaCancel;
	private Double preOtrasDeducciones;
	private Double preIvaDeducciones;
	private Double preAportaciones;
	private Double prePendMov;
	private Double preMoraMov;
	private Double preAcumMov;
	private String preCredito;
	private Set ctaMxpMovimientoPrestamos = new HashSet(0);
	private Set ctaRcoReferenciasComercialeses = new HashSet(0);
	private Set ctaCasCuentaAsociados = new HashSet(0);
	private Set ctaDexDescuentosExternoses = new HashSet(0);
	private Set ctaRpeReferenciasPersonaleses = new HashSet(0);
	private Set ctaGarGarantias = new HashSet(0);
	private Set ctaChkChequePrestamos = new HashSet(0);
	private Set ctaFxpFiadorPrestamos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaPrePrestamo() {
	}

	/** minimal constructor */
	public AbstractCtaPrePrestamo(String preId, Double preCuota,
			Double preMontoSolicitado, Double preSaldoActualT,
			Date preFechaSolicitud, Double preInteresAcumulado,
			Double preLiquidoARecibir) {
		this.preId = preId;
		this.preCuota = preCuota;
		this.preMontoSolicitado = preMontoSolicitado;
		this.preSaldoActualT = preSaldoActualT;
		this.preFechaSolicitud = preFechaSolicitud;
		this.preInteresAcumulado = preInteresAcumulado;
		this.preLiquidoARecibir = preLiquidoARecibir;
	}


	// Property accessors

	public AbstractCtaPrePrestamo(String preId,
			CtaTprTipoPrestamo ctaTprTipoPrestamo,
			CtaTinTasaInteres ctaTinTasaInteres, CtaSegSeguros ctaSegSeguros,
			CtaCbaCuentaBancaria ctaCbaCuentaBancaria,
			Long casCuenta, String preReferencia, Double preCuota,
			Double preMontoSolicitado, Double preSaldoActualT,
			Date preFechaSolicitud, Double preInteresAcumulado,
			Double preGastoAbogado, Double preMoraEmbargo,
			Double preLiquidoARecibir, Integer preNumCuotaCancel,
			Double preOtrasDeducciones, Double preIvaDeducciones, Double preAportaciones, Double prePendMov, Double preMoraMov,
			Double preAcumMov, String preCredito,
			Set ctaMxpMovimientoPrestamos, Set ctaRcoReferenciasComercialeses,
			Set ctaCasCuentaAsociados, Set ctaDexDescuentosExternoses,
			Set ctaRpeReferenciasPersonaleses, Set ctaGarGarantias,
			Set ctaChkChequePrestamos, Set ctaFxpFiadorPrestamos) {
		super();
		this.preId = preId;
		this.ctaTprTipoPrestamo = ctaTprTipoPrestamo;
		this.ctaTinTasaInteres = ctaTinTasaInteres;
		this.ctaSegSeguros = ctaSegSeguros;
		this.ctaCbaCuentaBancaria = ctaCbaCuentaBancaria;
		this.casCuenta = casCuenta;
		this.preReferencia = preReferencia;
		this.preCuota = preCuota;
		this.preMontoSolicitado = preMontoSolicitado;
		this.preSaldoActualT = preSaldoActualT;
		this.preFechaSolicitud = preFechaSolicitud;
		this.preInteresAcumulado = preInteresAcumulado;
		this.preGastoAbogado = preGastoAbogado;
		this.preMoraEmbargo = preMoraEmbargo;
		this.preLiquidoARecibir = preLiquidoARecibir;
		this.preNumCuotaCancel = preNumCuotaCancel;
		this.preOtrasDeducciones = preOtrasDeducciones;
		this.preIvaDeducciones = preIvaDeducciones;
		this.preAportaciones = preAportaciones;
		this.prePendMov = prePendMov;
		this.preMoraMov = preMoraMov;
		this.preAcumMov = preAcumMov;
		this.preCredito = preCredito;
		this.ctaMxpMovimientoPrestamos = ctaMxpMovimientoPrestamos;
		this.ctaRcoReferenciasComercialeses = ctaRcoReferenciasComercialeses;
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
		this.ctaDexDescuentosExternoses = ctaDexDescuentosExternoses;
		this.ctaRpeReferenciasPersonaleses = ctaRpeReferenciasPersonaleses;
		this.ctaGarGarantias = ctaGarGarantias;
		this.ctaChkChequePrestamos = ctaChkChequePrestamos;
		this.ctaFxpFiadorPrestamos = ctaFxpFiadorPrestamos;
	}

	public String getPreId() {
		return this.preId;
	}

	public void setPreId(String preId) {
		this.preId = preId;
	}

	public CtaTprTipoPrestamo getCtaTprTipoPrestamo() {
		return this.ctaTprTipoPrestamo;
	}

	public void setCtaTprTipoPrestamo(CtaTprTipoPrestamo ctaTprTipoPrestamo) {
		this.ctaTprTipoPrestamo = ctaTprTipoPrestamo;
	}

	public CtaTinTasaInteres getCtaTinTasaInteres() {
		return this.ctaTinTasaInteres;
	}

	public void setCtaTinTasaInteres(CtaTinTasaInteres ctaTinTasaInteres) {
		this.ctaTinTasaInteres = ctaTinTasaInteres;
	}

	public CtaSegSeguros getCtaSegSeguros() {
		return this.ctaSegSeguros;
	}

	public void setCtaSegSeguros(CtaSegSeguros ctaSegSeguros) {
		this.ctaSegSeguros = ctaSegSeguros;
	}
	
	public CtaCbaCuentaBancaria getCtaCbaCuentaBancaria() {
		return ctaCbaCuentaBancaria;
	}

	public void setCtaCbaCuentaBancaria(CtaCbaCuentaBancaria ctaCbaCuentaBancaria) {
		this.ctaCbaCuentaBancaria = ctaCbaCuentaBancaria;
	}

	public Long getCasCuenta() {
		return this.casCuenta;
	}

	public void setCasCuenta(Long casCuenta) {
		this.casCuenta = casCuenta;
	}

	public String getPreReferencia() {
		return this.preReferencia;
	}

	public void setPreReferencia(String preReferencia) {
		this.preReferencia = preReferencia;
	}

	public Double getPreCuota() {
		return this.preCuota;
	}

	public void setPreCuota(Double preCuota) {
		this.preCuota = preCuota;
	}

	public Double getPreMontoSolicitado() {
		return this.preMontoSolicitado;
	}

	public void setPreMontoSolicitado(Double preMontoSolicitado) {
		this.preMontoSolicitado = preMontoSolicitado;
	}

	public Double getPreSaldoActualT() {
		return this.preSaldoActualT;
	}

	public void setPreSaldoActualT(Double preSaldoActualT) {
		this.preSaldoActualT = preSaldoActualT;
	}

	public Date getPreFechaSolicitud() {
		return this.preFechaSolicitud;
	}

	public void setPreFechaSolicitud(Date preFechaSolicitud) {
		this.preFechaSolicitud = preFechaSolicitud;
	}

	public Double getPreInteresAcumulado() {
		return this.preInteresAcumulado;
	}

	public void setPreInteresAcumulado(Double preInteresAcumulado) {
		this.preInteresAcumulado = preInteresAcumulado;
	}

	public Double getPreGastoAbogado() {
		return this.preGastoAbogado;
	}

	public void setPreGastoAbogado(Double preGastoAbogado) {
		this.preGastoAbogado = preGastoAbogado;
	}

	public Double getPreMoraEmbargo() {
		return this.preMoraEmbargo;
	}

	public void setPreMoraEmbargo(Double preMoraEmbargo) {
		this.preMoraEmbargo = preMoraEmbargo;
	}

	public Double getPreLiquidoARecibir() {
		return this.preLiquidoARecibir;
	}

	public void setPreLiquidoARecibir(Double preLiquidoARecibir) {
		this.preLiquidoARecibir = preLiquidoARecibir;
	}

	public Integer getPreNumCuotaCancel() {
		return this.preNumCuotaCancel;
	}

	public void setPreNumCuotaCancel(Integer preNumCuotaCancel) {
		this.preNumCuotaCancel = preNumCuotaCancel;
	}

	public Set getCtaMxpMovimientoPrestamos() {
		return this.ctaMxpMovimientoPrestamos;
	}

	public void setCtaMxpMovimientoPrestamos(Set ctaMxpMovimientoPrestamos) {
		this.ctaMxpMovimientoPrestamos = ctaMxpMovimientoPrestamos;
	}

	public Set getCtaRcoReferenciasComercialeses() {
		return this.ctaRcoReferenciasComercialeses;
	}

	public void setCtaRcoReferenciasComercialeses(
			Set ctaRcoReferenciasComercialeses) {
		this.ctaRcoReferenciasComercialeses = ctaRcoReferenciasComercialeses;
	}

	public Set getCtaCasCuentaAsociados() {
		return this.ctaCasCuentaAsociados;
	}

	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
	}

	public Set getCtaDexDescuentosExternoses() {
		return this.ctaDexDescuentosExternoses;
	}

	public void setCtaDexDescuentosExternoses(Set ctaDexDescuentosExternoses) {
		this.ctaDexDescuentosExternoses = ctaDexDescuentosExternoses;
	}

	public Set getCtaRpeReferenciasPersonaleses() {
		return this.ctaRpeReferenciasPersonaleses;
	}

	public void setCtaRpeReferenciasPersonaleses(
			Set ctaRpeReferenciasPersonaleses) {
		this.ctaRpeReferenciasPersonaleses = ctaRpeReferenciasPersonaleses;
	}

	public Set getCtaGarGarantias() {
		return this.ctaGarGarantias;
	}

	public void setCtaGarGarantias(Set ctaGarGarantias) {
		this.ctaGarGarantias = ctaGarGarantias;
	}

	public Set getCtaChkChequePrestamos() {
		return this.ctaChkChequePrestamos;
	}

	public void setCtaChkChequePrestamos(Set ctaChkChequePrestamos) {
		this.ctaChkChequePrestamos = ctaChkChequePrestamos;
	}

	public Set getCtaFxpFiadorPrestamos() {
		return this.ctaFxpFiadorPrestamos;
	}

	public void setCtaFxpFiadorPrestamos(Set ctaFxpFiadorPrestamos) {
		this.ctaFxpFiadorPrestamos = ctaFxpFiadorPrestamos;
	}

	public Double getPreOtrasDeducciones() {
		return preOtrasDeducciones;
	}

	public void setPreOtrasDeducciones(Double preOtrasDeducciones) {
		this.preOtrasDeducciones = preOtrasDeducciones;
	}


	public Double getPreIvaDeducciones() {
		return preIvaDeducciones;
	}

	public void setPreIvaDeducciones(Double preIvaDeducciones) {
		this.preIvaDeducciones = preIvaDeducciones;
	}

	public Double getPreAportaciones() {
		return preAportaciones;
	}

	public void setPreAportaciones(Double preAportaciones) {
		this.preAportaciones = preAportaciones;
	}

	public Double getPrePendMov() {
		return prePendMov;
	}

	public void setPrePendMov(Double prePendMov) {
		this.prePendMov = prePendMov;
	}

	public Double getPreMoraMov() {
		return preMoraMov;
	}

	public void setPreMoraMov(Double preMoraMov) {
		this.preMoraMov = preMoraMov;
	}

	public Double getPreAcumMov() {
		return preAcumMov;
	}

	public void setPreAcumMov(Double preAcumMov) {
		this.preAcumMov = preAcumMov;
	}

	public String getPreCredito() {
		return preCredito;
	}

	public void setPreCredito(String preCredito) {
		this.preCredito = preCredito;
	}

}