package com.cetia.sicaco.asociados.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class AsociadoForm extends BasicForm {
	/**
	 * 
	 **/
	private static final long serialVersionUID = -1894177661579800132L;

	private CtaAscAsociado ascAsociadoH = new CtaAscAsociado();
	private String correoElectronico;
	private String viejoCodigo;
	private boolean otrTrab;
	private boolean mdf;
	private String notaTemp;
	private boolean usr;
	private boolean inactive;
	private boolean comp;
	
	private String centroCosto;
	private Integer etr;
	private String dptNombre;
	private boolean entrada;//para saber si se va a generar o no comprobante 
	// de ingreso
	private long comprobante;
	
	

	public long getComprobante() {
		return comprobante;
	}

	public void setComprobante(long comprobante) {
		this.comprobante = comprobante;
	}

	public boolean isComp() {
		return comp;
	}

	public void setComp(boolean comp) {
		this.comp = comp;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	public String getAscCodigoAsociado() {
		return ascAsociadoH.getAscCodigoAsociado();
	}

	public String getAscPagoIngreso() {
		return ascAsociadoH.getAscPagoIngreso();
	}

	public void setAscCodigoAsociado(String ascCodigoAsociado) {
		ascAsociadoH.setAscCodigoAsociado(ascCodigoAsociado);
	}

	public void setAscPagoIngreso(String ascPagoIngreso) {
		ascAsociadoH.setAscPagoIngreso(ascPagoIngreso);
	}

	public boolean isUsr() {
		return usr;
	}

	public void setUsr(boolean usr) {
		this.usr = usr;
	}

	public String getNotaTemp() {
		return notaTemp;
	}

	public void setNotaTemp(String notaTemp) {
		this.notaTemp = notaTemp;
	}

	public String getViejoCodigo() {
		return viejoCodigo;
	}

	public void setViejoCodigo(String viejoCodigo) {
		this.viejoCodigo = viejoCodigo;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public CtaAscAsociado getAscAsociadoH() {
		return ascAsociadoH;
	}

	public void setAscAsociadoH(CtaAscAsociado ascAsociadoH) {
		this.ascAsociadoH = ascAsociadoH;
	}

	public boolean isOtrTrab() {
		return otrTrab;
	}

	public void setOtrTrab(boolean otrTrab) {
		this.otrTrab = otrTrab;
	}

	public boolean isMdf() {
		return mdf;
	}

	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}

	public String getAscAsociadoPadre() {
		return ascAsociadoH.getAscAsociadoPadre();
	}

	public String getAscCodigo() {
		return ascAsociadoH.getAscCodigo();
	}

	public String getAscDirTrabajo() {
		return ascAsociadoH.getAscDirTrabajo();
	}

	public Float getAscDividendoAcumulado() {
		return ascAsociadoH.getAscDividendoAportaciones();
	}

	public String getAscId() {
		return ascAsociadoH.getAscId();
	}

	public String getAscIsss() {
		return ascAsociadoH.getAscIsss();
	}

	public String getAscJefeInmediato() {
		return ascAsociadoH.getAscJefeInmediato();
	}

	public String getAscNacionalidad() {
		return ascAsociadoH.getAscNacionalidad();
	}

	public String getAscNombreNit() {
		return ascAsociadoH.getAscNombreNit();
	}

	public String getAscProfesion() {
		return ascAsociadoH.getAscProfesion();
	}

	public Float getAscRentaDomicilio() {
		return ascAsociadoH.getAscRentaDomicilio();
	}

	public Float getAscSalario() {
		return ascAsociadoH.getAscSalario();
	}

	public Set getCtaCasCuentaAsociados() {
		return ascAsociadoH.getCtaCasCuentaAsociados();
	}

	public Set getCtaCntCodigosAnterioreses() {
		return ascAsociadoH.getCtaCntCodigosAnterioreses();
	}

	public CtaDomDomicilio getCtaDomDomicilio() {
		return ascAsociadoH.getCtaDomDomicilio();
	}

	public CtaDptDepartamentoTrabajo getCtaDptDepartamentoTrabajo() {
		return ascAsociadoH.getCtaDptDepartamentoTrabajo();
	}

	public Set getCtaInaIngresosxasociados() {
		return ascAsociadoH.getCtaInaIngresosxasociados();
	}

	public CtaNotNotas getCtaNotNotas() {
		return ascAsociadoH.getCtaNotNotas();
	}

	public CtaTasTipoAsociado getCtaTasTipoAsociado() {
		return ascAsociadoH.getCtaTasTipoAsociado();
	}

	public CtaTcoTipoContrato getCtaTcoTipoContrato() {
		return ascAsociadoH.getCtaTcoTipoContrato();
	}

	public CtrEstEstado getCtrEstEstado() {
		return ascAsociadoH.getCtrEstEstado();
	}

	public Integer getEstId() {
		return ascAsociadoH.getEstId();
	}

	public SecPerPersona getSecPerPersona() {
		return ascAsociadoH.getSecPerPersona();
	}

	public void setAscAsociadoPadre(String ascAsociadoPadre) {
		ascAsociadoH.setAscAsociadoPadre(ascAsociadoPadre);
	}

	public void setAscCodigo(String ascCodigo) {
		ascAsociadoH.setAscCodigo(ascCodigo);
	}

	public void setAscDirTrabajo(String ascDirTrabajo) {
		ascAsociadoH.setAscDirTrabajo(ascDirTrabajo);
	}

	public void setAscDividendoAcumulado(Float ascDividendoAcumulado) {
		ascAsociadoH.setAscDividendoAportaciones(ascDividendoAcumulado);
	}

	public void setAscDuiLugarExp(String ascDuiLugarExp) {
		ascAsociadoH.setAscDuiLugarExp(ascDuiLugarExp);
	}

	public String getAscDuiLugarExp() {
		return ascAsociadoH.getAscDuiLugarExp();
	}	
	
	public void setAscId(String ascId) {
		ascAsociadoH.setAscId(ascId);
	}

	public void setAscIsss(String ascIsss) {
		ascAsociadoH.setAscIsss(ascIsss);
	}

	public void setAscJefeInmediato(String ascJefeInmediato) {
		ascAsociadoH.setAscJefeInmediato(ascJefeInmediato);
	}

	public void setAscNacionalidad(String ascNacionalidad) {
		ascAsociadoH.setAscNacionalidad(ascNacionalidad);
	}

	public void setAscNombreNit(String ascNombreNit) {
		ascAsociadoH.setAscNombreNit(ascNombreNit);
	}

	public void setAscProfesion(String ascProfesion) {
		ascAsociadoH.setAscProfesion(ascProfesion);
	}

	public void setAscRentaDomicilio(Float ascRentaDomicilio) {
		ascAsociadoH.setAscRentaDomicilio(ascRentaDomicilio);
	}

	public void setAscSalario(Float ascSalario) {
		ascAsociadoH.setAscSalario(ascSalario);
	}

	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		ascAsociadoH.setCtaCasCuentaAsociados(ctaCasCuentaAsociados);
	}

	public void setCtaCntCodigosAnterioreses(Set ctaCntCodigosAnterioreses) {
		ascAsociadoH.setCtaCntCodigosAnterioreses(ctaCntCodigosAnterioreses);
	}

	public void setCtaDomDomicilio(CtaDomDomicilio ctaDomDomicilio) {
		ascAsociadoH.setCtaDomDomicilio(ctaDomDomicilio);
	}

	public void setCtaDptDepartamentoTrabajo(
			CtaDptDepartamentoTrabajo ctaDptDepartamentoTrabajo) {
		ascAsociadoH.setCtaDptDepartamentoTrabajo(ctaDptDepartamentoTrabajo);
	}

	public void setCtaInaIngresosxasociados(Set ctaInaIngresosxasociados) {
		ascAsociadoH.setCtaInaIngresosxasociados(ctaInaIngresosxasociados);
	}

	public void setCtaNotNotas(CtaNotNotas ctaNotNotas) {
		ascAsociadoH.setCtaNotNotas(ctaNotNotas);
	}

	public void setCtaTasTipoAsociado(CtaTasTipoAsociado ctaTasTipoAsociado) {
		ascAsociadoH.setCtaTasTipoAsociado(ctaTasTipoAsociado);
	}

	public void setCtaTcoTipoContrato(CtaTcoTipoContrato ctaTcoTipoContrato) {
		ascAsociadoH.setCtaTcoTipoContrato(ctaTcoTipoContrato);
	}

	public void setCtrEstEstado(CtrEstEstado ctrEstEstado) {
		ascAsociadoH.setCtrEstEstado(ctrEstEstado);
	}

	public void setEstId(Integer estId) {
		ascAsociadoH.setEstId(estId);
	}

	public void setSecPerPersona(SecPerPersona secPerPersona) {
		ascAsociadoH.setSecPerPersona(secPerPersona);
	}

	public String getAscDuiFechaExp() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ascAsociadoH.getAscDuiFechaExp()!=null)?(sdf.format(ascAsociadoH.getAscDuiFechaExp())):null;
	}

	public String getAscIngresoCia() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ascAsociadoH.getAscIngresoCia()!=null)?(sdf.format(ascAsociadoH.getAscIngresoCia())):null;
	}

	public String getAscIngresoCoope() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ascAsociadoH.getAscIngresoCoope()!=null)?(sdf.format(ascAsociadoH.getAscIngresoCoope())):null;
	}

	public String getAscRetiroCoope() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ascAsociadoH.getAscRetiroCoope()!=null)?(sdf.format(ascAsociadoH.getAscRetiroCoope())):null;
	}

	public void setAscDuiFechaExp(String ascDuiFechaExp) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ascAsociadoH.setAscDuiFechaExp(sdf.parse(ascDuiFechaExp));
		} catch (ParseException e) {
		}
	}

	public void setAscIngresoCia(String ascIngresoCia) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ascAsociadoH.setAscIngresoCia(sdf.parse(ascIngresoCia));
		} catch (ParseException e) {
		}
	}

	public void setAscIngresoCoope(String ascIngresoCoope) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ascAsociadoH.setAscIngresoCoope(sdf.parse(ascIngresoCoope));
		} catch (ParseException e) {
		}
	}

	public void setAscRetiroCoope(String ascRetiroCoope) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ascAsociadoH.setAscRetiroCoope(sdf.parse(ascRetiroCoope));
		} catch (ParseException e) {
		}
	}

	public void setSecPerPersonaFechaNacimiento(String fechaNacimientos) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date fecha = sdf.parse(fechaNacimientos);
			ascAsociadoH.getSecPerPersona().setPerFechaNacimiento(fecha);
		} catch (ParseException e) {
		}
	}
	
	public String getSecPerPersonaFechaNacimiento() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ascAsociadoH.getSecPerPersona().getPerFechaNacimiento()!=null)?(sdf.format(ascAsociadoH.getSecPerPersona().getPerFechaNacimiento())):null;
	}
	
	public void setPerNit(String perNit) {
		ascAsociadoH.getSecPerPersona().setPerNit(perNit.replace("-",""));
	}
	
	public String getPerNit() {
		return ascAsociadoH.getSecPerPersona().getPerNit();
	}
	
	public void setPerId(String perId) {
		ascAsociadoH.getSecPerPersona().setPerId(perId);
	}
	
	public String getPerId() {
		return ascAsociadoH.getSecPerPersona().getPerId();
	}
	
	public String getSecPerPersonaAudFechaCreacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ascAsociadoH.getSecPerPersona().getAudFechaCreacion()!=null)?(sdf.format(ascAsociadoH.getSecPerPersona().getAudFechaCreacion())):null;

	}
	
	public void setSecPerPersonaAudFechaCreacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ascAsociadoH.getSecPerPersona().setAudFechaCreacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}

	public String getNotFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return (ascAsociadoH.getCtaNotNotas().getNotFecha()!=null)?(sdf.format(ascAsociadoH.getCtaNotNotas().getNotFecha())):null;

	}
	
	public void setNotFecha(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			ascAsociadoH.getCtaNotNotas().setNotFecha(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public String getAscPagaraPadre() {
		return ascAsociadoH.getAscPagaraPadre();
	}

	public void setAscPagaraPadre(String ascPagaraPadre) {
		ascAsociadoH.setAscPagaraPadre(ascPagaraPadre);
	}

	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getAscEstadoDistribucion() {
		return ascAsociadoH.getAscEstadoDistribucion();
	}

	public void setAscEstadoDistribucion(String ascEstadoDistribucion) {
		ascAsociadoH.setAscEstadoDistribucion(ascEstadoDistribucion);
	}

	public Float getAscDividendoAportaciones() {
		return ascAsociadoH.getAscDividendoAportaciones();
	}

	public Double getAscDividendoPrestamo() {
		return ascAsociadoH.getAscDividendoPrestamo();
	}

	public void setAscDividendoPrestamo(Double ascDividendoPrestamo) {
		ascAsociadoH.setAscDividendoPrestamo(ascDividendoPrestamo);
	}

	public String getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	public Integer getEtr() {
		return etr;
	}

	public void setEtr(Integer etr) {
		this.etr = etr;
	}

	public String getDptNombre() {
		return dptNombre;
	}

	public void setDptNombre(String dptNombre) {
		this.dptNombre = dptNombre;
	}

	public boolean isEntrada() {
		return entrada;
	}

	public void setEntrada(boolean entrada) {
		this.entrada = entrada;
	}

}
