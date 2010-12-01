package com.cetia.sicaco.procesosEspeciales.struts.form;

import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaDomDomicilio;
import com.cetia.sicaco.hibernate.CtaDptDepartamentoTrabajo;
import com.cetia.sicaco.hibernate.CtaNotNotas;
import com.cetia.sicaco.hibernate.CtaTasTipoAsociado;
import com.cetia.sicaco.hibernate.CtaTcoTipoContrato;
import com.cetia.sicaco.hibernate.CtrEstEstado;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.struts.BasicForm;

public class ListaDistribuidosForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2900828388037403865L;
	private CtaAscAsociado asociadoH = new CtaAscAsociado();
	private Integer linea;
	private String[] elementos;
	private Integer selected;
	
	private int tamListaAct;
	
	
	
	public String[] getElementos() {
		return elementos;
	}

	public void setElementos(String[] elementos) {
		this.elementos = elementos;
	}

	public int getTamListaAct() {
		return tamListaAct;
	}

	public void setTamListaAct(int tamListaAct) {
		this.tamListaAct = tamListaAct;
	}



	public Integer getLinea() {
		return linea;
	}



	public void setLinea(Integer linea) {
		this.linea = linea;
	}


	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}



	public String getAscAsociadoPadre() {
		return asociadoH.getAscAsociadoPadre();
	}



	public String getAscCodigo() {
		return asociadoH.getAscCodigo();
	}



	public String getAscCodigoAsociado() {
		return asociadoH.getAscCodigoAsociado();
	}



	public String getAscDirTrabajo() {
		return asociadoH.getAscDirTrabajo();
	}



	public Float getAscDividendoAportaciones() {
		return asociadoH.getAscDividendoAportaciones();
	}



	public Double getAscDividendoPrestamo() {
		return asociadoH.getAscDividendoPrestamo();
	}



	public Date getAscDuiFechaExp() {
		return asociadoH.getAscDuiFechaExp();
	}



	public String getAscDuiLugarExp() {
		return asociadoH.getAscDuiLugarExp();
	}



	public String getAscEstadoDistribucion() {
		return asociadoH.getAscEstadoDistribucion();
	}



	public String getAscId() {
		return asociadoH.getAscId();
	}



	public Date getAscIngresoCia() {
		return asociadoH.getAscIngresoCia();
	}



	public Date getAscIngresoCoope() {
		return asociadoH.getAscIngresoCoope();
	}



	public String getAscIsss() {
		return asociadoH.getAscIsss();
	}



	public String getAscJefeInmediato() {
		return asociadoH.getAscJefeInmediato();
	}



	public String getAscNacionalidad() {
		return asociadoH.getAscNacionalidad();
	}



	public String getAscNombreNit() {
		return asociadoH.getAscNombreNit();
	}



	public String getAscPagaraPadre() {
		return asociadoH.getAscPagaraPadre();
	}



	public String getAscPagoIngreso() {
		return asociadoH.getAscPagoIngreso();
	}



	public String getAscProfesion() {
		return asociadoH.getAscProfesion();
	}



	public Float getAscRentaDomicilio() {
		return asociadoH.getAscRentaDomicilio();
	}



	public Date getAscRetiroCoope() {
		return asociadoH.getAscRetiroCoope();
	}



	public Float getAscSalario() {
		return asociadoH.getAscSalario();
	}



	public Set getCtaBenBeneficiarioses() {
		return asociadoH.getCtaBenBeneficiarioses();
	}



	public Set getCtaCasCuentaAsociados() {
		return asociadoH.getCtaCasCuentaAsociados();
	}



	public Set getCtaCntCodigosAnterioreses() {
		return asociadoH.getCtaCntCodigosAnterioreses();
	}



	public CtaDomDomicilio getCtaDomDomicilio() {
		return asociadoH.getCtaDomDomicilio();
	}



	public CtaDptDepartamentoTrabajo getCtaDptDepartamentoTrabajo() {
		return asociadoH.getCtaDptDepartamentoTrabajo();
	}



	public Set getCtaFxpFiadorPrestamos() {
		return asociadoH.getCtaFxpFiadorPrestamos();
	}



	public Set getCtaInaIngresosxasociados() {
		return asociadoH.getCtaInaIngresosxasociados();
	}



	public CtaNotNotas getCtaNotNotas() {
		return asociadoH.getCtaNotNotas();
	}



	public CtaTasTipoAsociado getCtaTasTipoAsociado() {
		return asociadoH.getCtaTasTipoAsociado();
	}



	public CtaTcoTipoContrato getCtaTcoTipoContrato() {
		return asociadoH.getCtaTcoTipoContrato();
	}



	public CtrEstEstado getCtrEstEstado() {
		return asociadoH.getCtrEstEstado();
	}



	public Integer getEstId() {
		return asociadoH.getEstId();
	}



	public Set getFacFenFacturaEncabezados() {
		return asociadoH.getFacFenFacturaEncabezados();
	}



	public SecPerPersona getSecPerPersona() {
		return asociadoH.getSecPerPersona();
	}



	public void setAscAsociadoPadre(String ascAsociadoPadre) {
		asociadoH.setAscAsociadoPadre(ascAsociadoPadre);
	}



	public void setAscCodigo(String ascCodigo) {
		asociadoH.setAscCodigo(ascCodigo);
	}



	public void setAscCodigoAsociado(String ascCodigoAsociado) {
		asociadoH.setAscCodigoAsociado(ascCodigoAsociado);
	}



	public void setAscDirTrabajo(String ascDirTrabajo) {
		asociadoH.setAscDirTrabajo(ascDirTrabajo);
	}



	public void setAscDividendoAportaciones(Float ascDividendoAportaciones) {
		asociadoH.setAscDividendoAportaciones(ascDividendoAportaciones);
	}



	public void setAscDividendoPrestamo(Double ascDividendoPrestamo) {
		asociadoH.setAscDividendoPrestamo(ascDividendoPrestamo);
	}



	public void setAscDuiFechaExp(Date ascDuiFechaExp) {
		asociadoH.setAscDuiFechaExp(ascDuiFechaExp);
	}



	public void setAscDuiLugarExp(String ascDuiLugarExp) {
		asociadoH.setAscDuiLugarExp(ascDuiLugarExp);
	}



	public void setAscEstadoDistribucion(String ascEstadoDistribucion) {
		asociadoH.setAscEstadoDistribucion(ascEstadoDistribucion);
	}



	public void setAscId(String ascId) {
		asociadoH.setAscId(ascId);
	}



	public void setAscIngresoCia(Date ascIngresoCia) {
		asociadoH.setAscIngresoCia(ascIngresoCia);
	}



	public void setAscIngresoCoope(Date ascIngresoCoope) {
		asociadoH.setAscIngresoCoope(ascIngresoCoope);
	}



	public void setAscIsss(String ascIsss) {
		asociadoH.setAscIsss(ascIsss);
	}



	public void setAscJefeInmediato(String ascJefeInmediato) {
		asociadoH.setAscJefeInmediato(ascJefeInmediato);
	}



	public void setAscNacionalidad(String ascNacionalidad) {
		asociadoH.setAscNacionalidad(ascNacionalidad);
	}



	public void setAscNombreNit(String ascNombreNit) {
		asociadoH.setAscNombreNit(ascNombreNit);
	}



	public void setAscPagaraPadre(String ascPagaraPadre) {
		asociadoH.setAscPagaraPadre(ascPagaraPadre);
	}



	public void setAscPagoIngreso(String ascPagoIngreso) {
		asociadoH.setAscPagoIngreso(ascPagoIngreso);
	}



	public void setAscProfesion(String ascProfesion) {
		asociadoH.setAscProfesion(ascProfesion);
	}



	public void setAscRentaDomicilio(Float ascRentaDomicilio) {
		asociadoH.setAscRentaDomicilio(ascRentaDomicilio);
	}



	public void setAscRetiroCoope(Date ascRetiroCoope) {
		asociadoH.setAscRetiroCoope(ascRetiroCoope);
	}



	public void setAscSalario(Float ascSalario) {
		asociadoH.setAscSalario(ascSalario);
	}



	public void setCtaBenBeneficiarioses(Set ctaBenBeneficiarioses) {
		asociadoH.setCtaBenBeneficiarioses(ctaBenBeneficiarioses);
	}



	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		asociadoH.setCtaCasCuentaAsociados(ctaCasCuentaAsociados);
	}



	public void setCtaCntCodigosAnterioreses(Set ctaCntCodigosAnterioreses) {
		asociadoH.setCtaCntCodigosAnterioreses(ctaCntCodigosAnterioreses);
	}



	public void setCtaDomDomicilio(CtaDomDomicilio ctaDomDomicilio) {
		asociadoH.setCtaDomDomicilio(ctaDomDomicilio);
	}



	public void setCtaDptDepartamentoTrabajo(
			CtaDptDepartamentoTrabajo ctaDptDepartamentoTrabajo) {
		asociadoH.setCtaDptDepartamentoTrabajo(ctaDptDepartamentoTrabajo);
	}



	public void setCtaFxpFiadorPrestamos(Set ctaFxpFiadorPrestamos) {
		asociadoH.setCtaFxpFiadorPrestamos(ctaFxpFiadorPrestamos);
	}



	public void setCtaInaIngresosxasociados(Set ctaInaIngresosxasociados) {
		asociadoH.setCtaInaIngresosxasociados(ctaInaIngresosxasociados);
	}



	public void setCtaNotNotas(CtaNotNotas ctaNotNotas) {
		asociadoH.setCtaNotNotas(ctaNotNotas);
	}



	public void setCtaTasTipoAsociado(CtaTasTipoAsociado ctaTasTipoAsociado) {
		asociadoH.setCtaTasTipoAsociado(ctaTasTipoAsociado);
	}



	public void setCtaTcoTipoContrato(CtaTcoTipoContrato ctaTcoTipoContrato) {
		asociadoH.setCtaTcoTipoContrato(ctaTcoTipoContrato);
	}



	public void setCtrEstEstado(CtrEstEstado ctrEstEstado) {
		asociadoH.setCtrEstEstado(ctrEstEstado);
	}



	public void setEstId(Integer estId) {
		asociadoH.setEstId(estId);
	}



	public void setFacFenFacturaEncabezados(Set facFenFacturaEncabezados) {
		asociadoH.setFacFenFacturaEncabezados(facFenFacturaEncabezados);
	}



	public void setSecPerPersona(SecPerPersona secPerPersona) {
		asociadoH.setSecPerPersona(secPerPersona);
	}



	public CtaAscAsociado getAsociadoH() {
		return asociadoH;
	}



	public void setAsociadoH(CtaAscAsociado asociadoH) {
		this.asociadoH = asociadoH;
	}



	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
