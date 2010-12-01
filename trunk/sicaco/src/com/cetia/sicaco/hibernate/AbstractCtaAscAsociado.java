package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaAscAsociado entity provides the base persistence definition of the
 * CtaAscAsociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaAscAsociado implements java.io.Serializable {

	// Fields

	private String ascId;
	private CtaDomDomicilio ctaDomDomicilio = new CtaDomDomicilio();
	private CtrEstEstado ctrEstEstado = new CtrEstEstado();
	private CtaNotNotas ctaNotNotas = new CtaNotNotas();
	private CtaTcoTipoContrato ctaTcoTipoContrato = new CtaTcoTipoContrato();;
	private CtaDptDepartamentoTrabajo ctaDptDepartamentoTrabajo = new CtaDptDepartamentoTrabajo();
	private SecPerPersona secPerPersona = new SecPerPersona();
	private CtaTasTipoAsociado ctaTasTipoAsociado = new CtaTasTipoAsociado();
	private String ascCodigo;
	private Integer estId;
	private Date ascIngresoCoope;
	private Date ascRetiroCoope;
	private String ascProfesion;
	private Float ascSalario;
	private String ascAsociadoPadre;
	private String ascNacionalidad;
	private String ascJefeInmediato;
	private Date ascIngresoCia;
	private Float ascRentaDomicilio;
	private String ascIsss;
	private Date ascDuiFechaExp;
	private String ascDuiLugarExp;
	private Float ascDividendoAportaciones;
	private String ascNombreNit;
	private String ascDirTrabajo;
	private String ascCodigoAsociado;
	private String ascPagoIngreso;
	private String ascPagaraPadre;
	private String ascEstadoDistribucion;
	private Double ascDividendoPrestamo;
	private Double ascSaldoAFavor;
	private Set ctaInaIngresosxasociados = new HashSet(0);
	private Set ctaFxpFiadorPrestamos = new HashSet(0);
	private Set ctaCasCuentaAsociados = new HashSet(0);
	private Set ctaCntCodigosAnterioreses = new HashSet(0);
	private Set ctaBenBeneficiarioses = new HashSet(0);
	private Set facFenFacturaEncabezados = new HashSet(0);
	// Constructors

	/** default constructor */
	public AbstractCtaAscAsociado() {
	}

	/** minimal constructor */
	public AbstractCtaAscAsociado(String ascId, String ascCodigo,
			Date ascIngresoCoope, Float ascSalario, String ascIsss,
			Date ascDuiFechaExp, String ascDuiLugarExp, String ascNombreNit) {
		this.ascId = ascId;
		this.ascCodigo = ascCodigo;
		this.ascIngresoCoope = ascIngresoCoope;
		this.ascSalario = ascSalario;
		this.ascIsss = ascIsss;
		this.ascDuiFechaExp = ascDuiFechaExp;
		this.ascDuiLugarExp = ascDuiLugarExp;
		this.ascNombreNit = ascNombreNit;
	}

	/** full constructor */
	public AbstractCtaAscAsociado(String ascId,
			CtaDomDomicilio ctaDomDomicilio, CtrEstEstado ctrEstEstado,
			CtaNotNotas ctaNotNotas, CtaTcoTipoContrato ctaTcoTipoContrato,
			CtaDptDepartamentoTrabajo ctaDptDepartamentoTrabajo,
			SecPerPersona secPerPersona, CtaTasTipoAsociado ctaTasTipoAsociado,
			String ascCodigo, Integer estId, Date ascIngresoCoope,
			Date ascRetiroCoope, String ascProfesion, Float ascSalario,
			String ascAsociadoPadre, String ascNacionalidad,
			String ascJefeInmediato, Date ascIngresoCia,
			Float ascRentaDomicilio, String ascIsss, Date ascDuiFechaExp,
			String ascDuiLugarExp, Float ascDividendoAportaciones,
			String ascNombreNit, String ascDirTrabajo,
			String ascCodigoAsociado, String ascPagoIngreso,
			String ascPagaraPadre,String ascEstadoDistribucion, Double ascDividendoPrestamo, Double ascSaldoAFavor,
			Set ctaInaIngresosxasociados, Set ctaFxpFiadorPrestamos,
			Set ctaCasCuentaAsociados, Set ctaCntCodigosAnterioreses,
			Set ctaBenBeneficiarioses, Set facFenFacturaEncabezados) {
		super();
		this.ascId = ascId;
		this.ctaDomDomicilio = ctaDomDomicilio;
		this.ctrEstEstado = ctrEstEstado;
		this.ctaNotNotas = ctaNotNotas;
		this.ctaTcoTipoContrato = ctaTcoTipoContrato;
		this.ctaDptDepartamentoTrabajo = ctaDptDepartamentoTrabajo;
		this.secPerPersona = secPerPersona;
		this.ctaTasTipoAsociado = ctaTasTipoAsociado;
		this.ascCodigo = ascCodigo;
		this.estId = estId;
		this.ascIngresoCoope = ascIngresoCoope;
		this.ascRetiroCoope = ascRetiroCoope;
		this.ascProfesion = ascProfesion;
		this.ascSalario = ascSalario;
		this.ascAsociadoPadre = ascAsociadoPadre;
		this.ascNacionalidad = ascNacionalidad;
		this.ascJefeInmediato = ascJefeInmediato;
		this.ascIngresoCia = ascIngresoCia;
		this.ascRentaDomicilio = ascRentaDomicilio;
		this.ascIsss = ascIsss;
		this.ascDuiFechaExp = ascDuiFechaExp;
		this.ascDuiLugarExp = ascDuiLugarExp;
		this.ascDividendoAportaciones = ascDividendoAportaciones;
		this.ascNombreNit = ascNombreNit;
		this.ascDirTrabajo = ascDirTrabajo;
		this.ascCodigoAsociado = ascCodigoAsociado;
		this.ascPagoIngreso = ascPagoIngreso;
		this.ascPagaraPadre = ascPagaraPadre;
		this.ctaInaIngresosxasociados = ctaInaIngresosxasociados;
		this.ctaFxpFiadorPrestamos = ctaFxpFiadorPrestamos;
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
		this.ctaCntCodigosAnterioreses = ctaCntCodigosAnterioreses;
		this.ctaBenBeneficiarioses = ctaBenBeneficiarioses;
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
		this.ascEstadoDistribucion = ascEstadoDistribucion;
		this.ascDividendoPrestamo = ascDividendoPrestamo;
		this.ascSaldoAFavor = ascSaldoAFavor;
	}
	
	// Property accessors

	public String getAscId() {
		return this.ascId;
	}

	public void setAscId(String ascId) {
		this.ascId = ascId;
	}

	public CtaDomDomicilio getCtaDomDomicilio() {
		return this.ctaDomDomicilio;
	}

	public void setCtaDomDomicilio(CtaDomDomicilio ctaDomDomicilio) {
		this.ctaDomDomicilio = ctaDomDomicilio;
	}

	public CtrEstEstado getCtrEstEstado() {
		return this.ctrEstEstado;
	}

	public void setCtrEstEstado(CtrEstEstado ctrEstEstado) {
		this.ctrEstEstado = ctrEstEstado;
	}

	public CtaNotNotas getCtaNotNotas() {
		return this.ctaNotNotas;
	}

	public void setCtaNotNotas(CtaNotNotas ctaNotNotas) {
		this.ctaNotNotas = ctaNotNotas;
	}

	public CtaTcoTipoContrato getCtaTcoTipoContrato() {
		return this.ctaTcoTipoContrato;
	}

	public void setCtaTcoTipoContrato(CtaTcoTipoContrato ctaTcoTipoContrato) {
		this.ctaTcoTipoContrato = ctaTcoTipoContrato;
	}

	public CtaDptDepartamentoTrabajo getCtaDptDepartamentoTrabajo() {
		return this.ctaDptDepartamentoTrabajo;
	}

	public void setCtaDptDepartamentoTrabajo(
			CtaDptDepartamentoTrabajo ctaDptDepartamentoTrabajo) {
		this.ctaDptDepartamentoTrabajo = ctaDptDepartamentoTrabajo;
	}

	public SecPerPersona getSecPerPersona() {
		return this.secPerPersona;
	}

	public void setSecPerPersona(SecPerPersona secPerPersona) {
		this.secPerPersona = secPerPersona;
	}

	public CtaTasTipoAsociado getCtaTasTipoAsociado() {
		return this.ctaTasTipoAsociado;
	}

	public void setCtaTasTipoAsociado(CtaTasTipoAsociado ctaTasTipoAsociado) {
		this.ctaTasTipoAsociado = ctaTasTipoAsociado;
	}

	public String getAscCodigo() {
		return this.ascCodigo;
	}

	public void setAscCodigo(String ascCodigo) {
		this.ascCodigo = ascCodigo;
	}

	public Integer getEstId() {
		return this.estId;
	}

	public void setEstId(Integer estId) {
		this.estId = estId;
	}

	public Date getAscIngresoCoope() {
		return this.ascIngresoCoope;
	}

	public void setAscIngresoCoope(Date ascIngresoCoope) {
		this.ascIngresoCoope = ascIngresoCoope;
	}

	public Date getAscRetiroCoope() {
		return this.ascRetiroCoope;
	}

	public void setAscRetiroCoope(Date ascRetiroCoope) {
		this.ascRetiroCoope = ascRetiroCoope;
	}

	public String getAscProfesion() {
		return this.ascProfesion;
	}

	public void setAscProfesion(String ascProfesion) {
		this.ascProfesion = ascProfesion;
	}

	public Float getAscSalario() {
		return this.ascSalario;
	}

	public void setAscSalario(Float ascSalario) {
		this.ascSalario = ascSalario;
	}

	public String getAscAsociadoPadre() {
		return this.ascAsociadoPadre;
	}

	public void setAscAsociadoPadre(String ascAsociadoPadre) {
		this.ascAsociadoPadre = ascAsociadoPadre;
	}

	public String getAscNacionalidad() {
		return this.ascNacionalidad;
	}

	public void setAscNacionalidad(String ascNacionalidad) {
		this.ascNacionalidad = ascNacionalidad;
	}

	public String getAscJefeInmediato() {
		return this.ascJefeInmediato;
	}

	public void setAscJefeInmediato(String ascJefeInmediato) {
		this.ascJefeInmediato = ascJefeInmediato;
	}

	public Date getAscIngresoCia() {
		return this.ascIngresoCia;
	}

	public void setAscIngresoCia(Date ascIngresoCia) {
		this.ascIngresoCia = ascIngresoCia;
	}

	public Float getAscRentaDomicilio() {
		return this.ascRentaDomicilio;
	}

	public void setAscRentaDomicilio(Float ascRentaDomicilio) {
		this.ascRentaDomicilio = ascRentaDomicilio;
	}

	public String getAscIsss() {
		return this.ascIsss;
	}

	public void setAscIsss(String ascIsss) {
		this.ascIsss = ascIsss;
	}

	public Date getAscDuiFechaExp() {
		return this.ascDuiFechaExp;
	}

	public void setAscDuiFechaExp(Date ascDuiFechaExp) {
		this.ascDuiFechaExp = ascDuiFechaExp;
	}

	public String getAscDuiLugarExp() {
		return this.ascDuiLugarExp;
	}

	public void setAscDuiLugarExp(String ascDuiLugarExp) {
		this.ascDuiLugarExp = ascDuiLugarExp;
	}

	public String getAscNombreNit() {
		return this.ascNombreNit;
	}

	public void setAscNombreNit(String ascNombreNit) {
		this.ascNombreNit = ascNombreNit;
	}

	public String getAscDirTrabajo() {
		return this.ascDirTrabajo;
	}

	public void setAscDirTrabajo(String ascDirTrabajo) {
		this.ascDirTrabajo = ascDirTrabajo;
	}

	public Set getCtaInaIngresosxasociados() {
		return this.ctaInaIngresosxasociados;
	}

	public void setCtaInaIngresosxasociados(Set ctaInaIngresosxasociados) {
		this.ctaInaIngresosxasociados = ctaInaIngresosxasociados;
	}
	
	public Set getCtaFxpFiadorPrestamos() {
		return this.ctaFxpFiadorPrestamos;
	}

	public void setCtaFxpFiadorPrestamos(Set ctaFxpFiadorPrestamos) {
		this.ctaFxpFiadorPrestamos = ctaFxpFiadorPrestamos;
	}

	public Set getCtaCasCuentaAsociados() {
		return this.ctaCasCuentaAsociados;
	}

	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
	}

	public Set getCtaCntCodigosAnterioreses() {
		return this.ctaCntCodigosAnterioreses;
	}

	public void setCtaCntCodigosAnterioreses(Set ctaCntCodigosAnterioreses) {
		this.ctaCntCodigosAnterioreses = ctaCntCodigosAnterioreses;
	}
	
	public Set getCtaBenBeneficiarioses() {
		return this.ctaBenBeneficiarioses;
	}

	public void setCtaBenBeneficiarioses(Set ctaBenBeneficiarioses) {
		this.ctaBenBeneficiarioses = ctaBenBeneficiarioses;
	}

	public Set getFacFenFacturaEncabezados() {
		return facFenFacturaEncabezados;
	}

	public void setFacFenFacturaEncabezados(Set facFenFacturaEncabezados) {
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
	}

	public String getAscCodigoAsociado() {
		return ascCodigoAsociado;
	}

	public void setAscCodigoAsociado(String ascCodigoAsociado) {
		this.ascCodigoAsociado = ascCodigoAsociado;
	}

	public String getAscPagoIngreso() {
		return ascPagoIngreso;
	}

	public void setAscPagoIngreso(String ascPagoIngreso) {
		this.ascPagoIngreso = ascPagoIngreso;
	}

	public String getAscPagaraPadre() {
		return ascPagaraPadre;
	}

	public void setAscPagaraPadre(String ascPagaraPadre) {
		this.ascPagaraPadre = ascPagaraPadre;
	}

	public String getAscEstadoDistribucion() {
		return ascEstadoDistribucion;
	}

	public void setAscEstadoDistribucion(String ascEstadoDistribucion) {
		this.ascEstadoDistribucion = ascEstadoDistribucion;
	}

	public Float getAscDividendoAportaciones() {
		return ascDividendoAportaciones;
	}

	public void setAscDividendoAportaciones(Float ascDividendoAportaciones) {
		this.ascDividendoAportaciones = ascDividendoAportaciones;
	}

	public Double getAscDividendoPrestamo() {
		return ascDividendoPrestamo;
	}

	public void setAscDividendoPrestamo(Double ascDividendoPrestamo) {
		this.ascDividendoPrestamo = ascDividendoPrestamo;
	}

	public Double getAscSaldoAFavor() {
		return ascSaldoAFavor;
	}

	public void setAscSaldoAFavor(Double ascSaldoAFavor) {
		this.ascSaldoAFavor = ascSaldoAFavor;
	}	
	
}