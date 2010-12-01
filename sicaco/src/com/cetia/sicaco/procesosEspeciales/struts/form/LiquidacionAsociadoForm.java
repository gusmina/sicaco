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

public class LiquidacionAsociadoForm extends BasicForm {

	CtaAscAsociado ctaAscAsociadoH = new CtaAscAsociado();
	int mesesProyeccion;
	private Integer[] selectedItemsA;
	private String[] idA;
	private Double[] liquidaA;
	private Double[] saldosA;
	
	private Integer[] selectedItemsP;
	private String[] idP;
	private Double[] liquidaP;
	private Double[] saldosP;
	
	private Integer[] selectedItemsS;
	private String[] IdS;
	private Double[] liquidaS;
	private Double[] saldosS;
	
	
	public String getAscAsociadoPadre() {
		return ctaAscAsociadoH.getAscAsociadoPadre();
	}



	public String getAscCodigo() {
		return ctaAscAsociadoH.getAscCodigo();
	}



	public String getAscCodigoAsociado() {
		return ctaAscAsociadoH.getAscCodigoAsociado();
	}



	public String getAscDirTrabajo() {
		return ctaAscAsociadoH.getAscDirTrabajo();
	}





	public Date getAscDuiFechaExp() {
		return ctaAscAsociadoH.getAscDuiFechaExp();
	}



	public String getAscDuiLugarExp() {
		return ctaAscAsociadoH.getAscDuiLugarExp();
	}



	public String getAscId() {
		return ctaAscAsociadoH.getAscId();
	}



	public Date getAscIngresoCia() {
		return ctaAscAsociadoH.getAscIngresoCia();
	}



	public Date getAscIngresoCoope() {
		return ctaAscAsociadoH.getAscIngresoCoope();
	}



	public String getAscIsss() {
		return ctaAscAsociadoH.getAscIsss();
	}



	public String getAscJefeInmediato() {
		return ctaAscAsociadoH.getAscJefeInmediato();
	}



	public String getAscNacionalidad() {
		return ctaAscAsociadoH.getAscNacionalidad();
	}



	public String getAscNombreNit() {
		return ctaAscAsociadoH.getAscNombreNit();
	}



	public String getAscPagaraPadre() {
		return ctaAscAsociadoH.getAscPagaraPadre();
	}



	public String getAscPagoIngreso() {
		return ctaAscAsociadoH.getAscPagoIngreso();
	}



	public String getAscProfesion() {
		return ctaAscAsociadoH.getAscProfesion();
	}



	public Float getAscRentaDomicilio() {
		return ctaAscAsociadoH.getAscRentaDomicilio();
	}



	public Date getAscRetiroCoope() {
		return ctaAscAsociadoH.getAscRetiroCoope();
	}



	public Float getAscSalario() {
		return ctaAscAsociadoH.getAscSalario();
	}



	public Set getCtaBenBeneficiarioses() {
		return ctaAscAsociadoH.getCtaBenBeneficiarioses();
	}



	public Set getCtaCasCuentaAsociados() {
		return ctaAscAsociadoH.getCtaCasCuentaAsociados();
	}



	public Set getCtaCntCodigosAnterioreses() {
		return ctaAscAsociadoH.getCtaCntCodigosAnterioreses();
	}



	public CtaDomDomicilio getCtaDomDomicilio() {
		return ctaAscAsociadoH.getCtaDomDomicilio();
	}



	public CtaDptDepartamentoTrabajo getCtaDptDepartamentoTrabajo() {
		return ctaAscAsociadoH.getCtaDptDepartamentoTrabajo();
	}



	public Set getCtaFxpFiadorPrestamos() {
		return ctaAscAsociadoH.getCtaFxpFiadorPrestamos();
	}



	public Set getCtaInaIngresosxasociados() {
		return ctaAscAsociadoH.getCtaInaIngresosxasociados();
	}



	public CtaNotNotas getCtaNotNotas() {
		return ctaAscAsociadoH.getCtaNotNotas();
	}



	public CtaTasTipoAsociado getCtaTasTipoAsociado() {
		return ctaAscAsociadoH.getCtaTasTipoAsociado();
	}



	public CtaTcoTipoContrato getCtaTcoTipoContrato() {
		return ctaAscAsociadoH.getCtaTcoTipoContrato();
	}



	public CtrEstEstado getCtrEstEstado() {
		return ctaAscAsociadoH.getCtrEstEstado();
	}



	public Integer getEstId() {
		return ctaAscAsociadoH.getEstId();
	}



	public Set getFacFenFacturaEncabezados() {
		return ctaAscAsociadoH.getFacFenFacturaEncabezados();
	}



	public SecPerPersona getSecPerPersona() {
		return ctaAscAsociadoH.getSecPerPersona();
	}



	public void setAscAsociadoPadre(String ascAsociadoPadre) {
		ctaAscAsociadoH.setAscAsociadoPadre(ascAsociadoPadre);
	}



	public void setAscCodigo(String ascCodigo) {
		ctaAscAsociadoH.setAscCodigo(ascCodigo);
	}



	public void setAscCodigoAsociado(String ascCodigoAsociado) {
		ctaAscAsociadoH.setAscCodigoAsociado(ascCodigoAsociado);
	}



	public void setAscDirTrabajo(String ascDirTrabajo) {
		ctaAscAsociadoH.setAscDirTrabajo(ascDirTrabajo);
	}




	public void setAscDuiFechaExp(Date ascDuiFechaExp) {
		ctaAscAsociadoH.setAscDuiFechaExp(ascDuiFechaExp);
	}



	public void setAscDuiLugarExp(String ascDuiLugarExp) {
		ctaAscAsociadoH.setAscDuiLugarExp(ascDuiLugarExp);
	}



	public void setAscId(String ascId) {
		ctaAscAsociadoH.setAscId(ascId);
	}



	public void setAscIngresoCia(Date ascIngresoCia) {
		ctaAscAsociadoH.setAscIngresoCia(ascIngresoCia);
	}



	public void setAscIngresoCoope(Date ascIngresoCoope) {
		ctaAscAsociadoH.setAscIngresoCoope(ascIngresoCoope);
	}



	public void setAscIsss(String ascIsss) {
		ctaAscAsociadoH.setAscIsss(ascIsss);
	}



	public void setAscJefeInmediato(String ascJefeInmediato) {
		ctaAscAsociadoH.setAscJefeInmediato(ascJefeInmediato);
	}



	public void setAscNacionalidad(String ascNacionalidad) {
		ctaAscAsociadoH.setAscNacionalidad(ascNacionalidad);
	}



	public void setAscNombreNit(String ascNombreNit) {
		ctaAscAsociadoH.setAscNombreNit(ascNombreNit);
	}



	public void setAscPagaraPadre(String ascPagaraPadre) {
		ctaAscAsociadoH.setAscPagaraPadre(ascPagaraPadre);
	}



	public void setAscPagoIngreso(String ascPagoIngreso) {
		ctaAscAsociadoH.setAscPagoIngreso(ascPagoIngreso);
	}



	public void setAscProfesion(String ascProfesion) {
		ctaAscAsociadoH.setAscProfesion(ascProfesion);
	}



	public void setAscRentaDomicilio(Float ascRentaDomicilio) {
		ctaAscAsociadoH.setAscRentaDomicilio(ascRentaDomicilio);
	}



	public void setAscRetiroCoope(Date ascRetiroCoope) {
		ctaAscAsociadoH.setAscRetiroCoope(ascRetiroCoope);
	}



	public void setAscSalario(Float ascSalario) {
		ctaAscAsociadoH.setAscSalario(ascSalario);
	}



	public void setCtaBenBeneficiarioses(Set ctaBenBeneficiarioses) {
		ctaAscAsociadoH.setCtaBenBeneficiarioses(ctaBenBeneficiarioses);
	}



	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		ctaAscAsociadoH.setCtaCasCuentaAsociados(ctaCasCuentaAsociados);
	}



	public void setCtaCntCodigosAnterioreses(Set ctaCntCodigosAnterioreses) {
		ctaAscAsociadoH.setCtaCntCodigosAnterioreses(ctaCntCodigosAnterioreses);
	}



	public void setCtaDomDomicilio(CtaDomDomicilio ctaDomDomicilio) {
		ctaAscAsociadoH.setCtaDomDomicilio(ctaDomDomicilio);
	}



	public void setCtaDptDepartamentoTrabajo(
			CtaDptDepartamentoTrabajo ctaDptDepartamentoTrabajo) {
		ctaAscAsociadoH.setCtaDptDepartamentoTrabajo(ctaDptDepartamentoTrabajo);
	}



	public void setCtaFxpFiadorPrestamos(Set ctaFxpFiadorPrestamos) {
		ctaAscAsociadoH.setCtaFxpFiadorPrestamos(ctaFxpFiadorPrestamos);
	}



	public void setCtaInaIngresosxasociados(Set ctaInaIngresosxasociados) {
		ctaAscAsociadoH.setCtaInaIngresosxasociados(ctaInaIngresosxasociados);
	}



	public void setCtaNotNotas(CtaNotNotas ctaNotNotas) {
		ctaAscAsociadoH.setCtaNotNotas(ctaNotNotas);
	}



	public void setCtaTasTipoAsociado(CtaTasTipoAsociado ctaTasTipoAsociado) {
		ctaAscAsociadoH.setCtaTasTipoAsociado(ctaTasTipoAsociado);
	}



	public void setCtaTcoTipoContrato(CtaTcoTipoContrato ctaTcoTipoContrato) {
		ctaAscAsociadoH.setCtaTcoTipoContrato(ctaTcoTipoContrato);
	}



	public void setCtrEstEstado(CtrEstEstado ctrEstEstado) {
		ctaAscAsociadoH.setCtrEstEstado(ctrEstEstado);
	}



	public void setEstId(Integer estId) {
		ctaAscAsociadoH.setEstId(estId);
	}



	public void setFacFenFacturaEncabezados(Set facFenFacturaEncabezados) {
		ctaAscAsociadoH.setFacFenFacturaEncabezados(facFenFacturaEncabezados);
	}



	public void setSecPerPersona(SecPerPersona secPerPersona) {
		ctaAscAsociadoH.setSecPerPersona(secPerPersona);
	}



	public CtaAscAsociado getCtaAscAsociadoH() {
		return ctaAscAsociadoH;
	}



	public void setCtaAscAsociadoH(CtaAscAsociado ctaAscAsociadoH) {
		this.ctaAscAsociadoH = ctaAscAsociadoH;
	}



	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}



	public int getMesesProyeccion() {
		return mesesProyeccion;
	}



	public void setMesesProyeccion(int mesesProyeccion) {
		this.mesesProyeccion = mesesProyeccion;
	}



	public Integer[] getSelectedItemsA() {
		return selectedItemsA;
	}



	public void setSelectedItemsA(Integer[] selectedItemsA) {
		this.selectedItemsA = selectedItemsA;
	}



	


	public Double[] getLiquidaA() {
		return liquidaA;
	}



	public void setLiquidaA(Double[] liquidaA) {
		this.liquidaA = liquidaA;
	}



	public Integer[] getSelectedItemsP() {
		return selectedItemsP;
	}



	public void setSelectedItemsP(Integer[] selectedItemsP) {
		this.selectedItemsP = selectedItemsP;
	}







	public Double[] getLiquidaP() {
		return liquidaP;
	}



	public void setLiquidaP(Double[] liquidaP) {
		this.liquidaP = liquidaP;
	}



	public Integer[] getSelectedItemsS() {
		return selectedItemsS;
	}



	public void setSelectedItemsS(Integer[] selectedItemsS) {
		this.selectedItemsS = selectedItemsS;
	}




	public Double[] getLiquidaS() {
		return liquidaS;
	}



	public void setLiquidaS(Double[] liquidaS) {
		this.liquidaS = liquidaS;
	}



	public String[] getIdA() {
		return idA;
	}



	public void setIdA(String[] idA) {
		this.idA = idA;
	}



	public Double[] getSaldosA() {
		return saldosA;
	}



	public void setSaldosA(Double[] saldosA) {
		this.saldosA = saldosA;
	}



	public String[] getIdP() {
		return idP;
	}



	public void setIdP(String[] idP) {
		this.idP = idP;
	}



	public Double[] getSaldosP() {
		return saldosP;
	}



	public void setSaldosP(Double[] saldosP) {
		this.saldosP = saldosP;
	}


	public String[] getIdS() {
		return IdS;
	}



	public void setIdS(String[] idS) {
		IdS = idS;
	}


	public Double[] getSaldosS() {
		return saldosS;
	}



	public void setSaldosS(Double[] saldosS) {
		this.saldosS = saldosS;
	}

}
