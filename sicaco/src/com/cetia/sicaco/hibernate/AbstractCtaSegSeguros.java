package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaSegSeguros entity provides the base persistence definition of the
 * CtaSegSeguros entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaSegSeguros implements java.io.Serializable {

	// Fields

	private String segId;
	private CtaTisTipoSeguro ctaTisTipoSeguro =new CtaTisTipoSeguro();
	private CtaTinTasaInteres ctaTinTasaInteres = new CtaTinTasaInteres();
	private String segCertificado;
	private String segCarnet;
	private Double segMonto;
	private Double segSaldoActual;
	private String segReferencia;
	private Double segCuota;
	private Double segSaldoIni;
	private Integer segNumCuotaCancel;
	private Set ctaPrePrestamos = new HashSet(0);
	private Set ctaCasCuentaAsociados = new HashSet(0);
	private Set ctaMxsMovimientoSeguroses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaSegSeguros() {
	}

	/** minimal constructor */
	public AbstractCtaSegSeguros(String segId, String segCertificado,
			String segCarnet, Double segMonto, Double segSaldoActual,
			Double segCuota, Double segSaldoIni) {
		this.segId = segId;
		this.segCertificado = segCertificado;
		this.segCarnet = segCarnet;
		this.segMonto = segMonto;
		this.segSaldoActual = segSaldoActual;
		this.segCuota = segCuota;
		this.segSaldoIni = segSaldoIni;
	}

	/** full constructor */
	public AbstractCtaSegSeguros(String segId,
			CtaTisTipoSeguro ctaTisTipoSeguro,
			CtaTinTasaInteres ctaTinTasaInteres, 
			String segCertificado, String segCarnet, Double segMonto,
			Double segSaldoActual, String segReferencia, Double segCuota,
			Double segSaldoIni, Integer segNumCuotaCancel, Set ctaPrePrestamos,
			Set ctaCasCuentaAsociados, Set ctaMxsMovimientoSeguroses) {
		this.segId = segId;
		this.ctaTisTipoSeguro = ctaTisTipoSeguro;
		this.ctaTinTasaInteres = ctaTinTasaInteres;
		this.segCertificado = segCertificado;
		this.segCarnet = segCarnet;
		this.segMonto = segMonto;
		this.segSaldoActual = segSaldoActual;
		this.segReferencia = segReferencia;
		this.segCuota = segCuota;
		this.segSaldoIni = segSaldoIni;
		this.segNumCuotaCancel = segNumCuotaCancel;
		this.ctaPrePrestamos = ctaPrePrestamos;
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
		this.ctaMxsMovimientoSeguroses = ctaMxsMovimientoSeguroses;
	}

	// Property accessors

	public String getSegId() {
		return this.segId;
	}

	public void setSegId(String segId) {
		this.segId = segId;
	}

	public CtaTisTipoSeguro getCtaTisTipoSeguro() {
		return this.ctaTisTipoSeguro;
	}

	public void setCtaTisTipoSeguro(CtaTisTipoSeguro ctaTisTipoSeguro) {
		this.ctaTisTipoSeguro = ctaTisTipoSeguro;
	}

	public CtaTinTasaInteres getCtaTinTasaInteres() {
		return this.ctaTinTasaInteres;
	}

	public void setCtaTinTasaInteres(CtaTinTasaInteres ctaTinTasaInteres) {
		this.ctaTinTasaInteres = ctaTinTasaInteres;
	}

	public String getSegCertificado() {
		return this.segCertificado;
	}

	public void setSegCertificado(String segCertificado) {
		this.segCertificado = segCertificado;
	}

	public String getSegCarnet() {
		return this.segCarnet;
	}

	public void setSegCarnet(String segCarnet) {
		this.segCarnet = segCarnet;
	}

	public Double getSegMonto() {
		return this.segMonto;
	}

	public void setSegMonto(Double segMonto) {
		this.segMonto = segMonto;
	}

	public Double getSegSaldoActual() {
		return this.segSaldoActual;
	}

	public void setSegSaldoActual(Double segSaldoActual) {
		this.segSaldoActual = segSaldoActual;
	}

	public String getSegReferencia() {
		return this.segReferencia;
	}

	public void setSegReferencia(String segReferencia) {
		this.segReferencia = segReferencia;
	}

	public Double getSegCuota() {
		return this.segCuota;
	}

	public void setSegCuota(Double segCuota) {
		this.segCuota = segCuota;
	}

	public Double getSegSaldoIni() {
		return this.segSaldoIni;
	}

	public void setSegSaldoIni(Double segSaldoIni) {
		this.segSaldoIni = segSaldoIni;
	}

	public Integer getSegNumCuotaCancel() {
		return this.segNumCuotaCancel;
	}

	public void setSegNumCuotaCancel(Integer segNumCuotaCancel) {
		this.segNumCuotaCancel = segNumCuotaCancel;
	}

	public Set getCtaPrePrestamos() {
		return this.ctaPrePrestamos;
	}

	public void setCtaPrePrestamos(Set ctaPrePrestamos) {
		this.ctaPrePrestamos = ctaPrePrestamos;
	}

	public Set getCtaCasCuentaAsociados() {
		return this.ctaCasCuentaAsociados;
	}

	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
	}

	public Set getCtaMxsMovimientoSeguroses() {
		return ctaMxsMovimientoSeguroses;
	}

	public void setCtaMxsMovimientoSeguroses(Set ctaMxsMovimientoSeguroses) {
		this.ctaMxsMovimientoSeguroses = ctaMxsMovimientoSeguroses;
	}

}