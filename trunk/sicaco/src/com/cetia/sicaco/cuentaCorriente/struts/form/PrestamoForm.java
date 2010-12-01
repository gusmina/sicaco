package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaDomDomicilio;
import com.cetia.sicaco.hibernate.CtaDptDepartamentoTrabajo;
import com.cetia.sicaco.hibernate.CtaNotNotas;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaSegSeguros;
import com.cetia.sicaco.hibernate.CtaTasTipoAsociado;
import com.cetia.sicaco.hibernate.CtaTcoTipoContrato;
import com.cetia.sicaco.hibernate.CtaTinTasaInteres;
import com.cetia.sicaco.hibernate.CtaTprTipoPrestamo;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.struts.BasicForm;

public class PrestamoForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6096696722165122000L;
	private CtaPrePrestamo prestamoH = new CtaPrePrestamo();
	private boolean fuente;
	private CtaAscAsociado asociadoH = new CtaAscAsociado();
	private long[] posicionNotas;
	private int nueva;//1 si es verdadero; 0 es falso
	private String referenciaCuenta;
	private Date casFechaApertura;
	private String nota;
	//se utiliza para realizar la ocultacion de botones y mostrar los mensajes
	//cuando se ha aprobado o denegado un prestamo.
	private int estado;
	private double montoSolicitadoAnterior;
	private Double nuevoMontoSolicitado;
	private double montoRecibir; 
	
	private Date vencimiento;
	private int refinanciado;
	private Double aportaciones;
	private Double salario;
	private Integer lprId;
	private Integer plmId;
	private Integer tinPlazoId;
	
	private Double cuotaPlanilla;
	private Double tasaMora;
	private Integer cuotasPendientes;
	private Boolean hidFuente;
	//agragado
	private Double interesSolicitado;
	private int mesesSolicitados;
	
	public String getCasFechaApertura() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (this.casFechaApertura!=null)?(sdf.format(this.casFechaApertura)):null;
	}
	
	public void setCasFechaApertura(Date casFechaApertura) {
		this.casFechaApertura = casFechaApertura;
	}

	public void setCasFechaApertura(String casFechaApertura) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.casFechaApertura= sdf.parse(casFechaApertura);
		} catch (ParseException e) {
		}
	}

	public String getReferenciaCuenta() {
		return referenciaCuenta;
	}

	public void setReferenciaCuenta(String referenciaCuenta) {
		this.referenciaCuenta = referenciaCuenta;
	}

	public String getAscAsociadoPadre() {
		return asociadoH.getAscAsociadoPadre();
	}

	public String getAscCodigoAsociado() {
		return asociadoH.getAscCodigoAsociado();
	}

	public String getAscDirTrabajo() {
		return asociadoH.getAscDirTrabajo();
	}



	public Date getAscDuiFechaExp() {
		return asociadoH.getAscDuiFechaExp();
	}

	public String getAscDuiLugarExp() {
		return asociadoH.getAscDuiLugarExp();
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

	public Set getCtaCntCodigosAnterioreses() {
		return asociadoH.getCtaCntCodigosAnterioreses();
	}

	public CtaDomDomicilio getCtaDomDomicilio() {
		return asociadoH.getCtaDomDomicilio();
	}

	public CtaDptDepartamentoTrabajo getCtaDptDepartamentoTrabajo() {
		return asociadoH.getCtaDptDepartamentoTrabajo();
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

	public Integer getEstId() {
		return asociadoH.getEstId();
	}

	public SecPerPersona getSecPerPersona() {
		return asociadoH.getSecPerPersona();
	}

	public void setAscAsociadoPadre(String ascAsociadoPadre) {
		asociadoH.setAscAsociadoPadre(ascAsociadoPadre);
	}

	public void setAscCodigoAsociado(String ascCodigo) {
		asociadoH.setAscCodigoAsociado(ascCodigo);
	}

	public void setAscDirTrabajo(String ascDirTrabajo) {
		asociadoH.setAscDirTrabajo(ascDirTrabajo);
	}


	public void setAscDuiFechaExp(Date ascDuiFechaExp) {
		asociadoH.setAscDuiFechaExp(ascDuiFechaExp);
	}

	public void setAscDuiLugarExp(String ascDuiLugarExp) {
		asociadoH.setAscDuiLugarExp(ascDuiLugarExp);
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

	public void setEstId(Integer estId) {
		asociadoH.setEstId(estId);
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

	public int getNueva() {
		return nueva;
	}

	public void setNueva(int nueva) {
		this.nueva = nueva;
	}
	
	public boolean isFuente() {
		return fuente;
	}

	public void setFuente(boolean fuente) {
		this.fuente = fuente;
	}

	public String getAscId() {
		return this.asociadoH.getAscId();
	}

	public void setAscId(String ascId) {
		this.asociadoH.setAscId(ascId);
	}

	public CtaPrePrestamo getPrestamoH() {
		return prestamoH;
	}

	public void setPrestamoH(CtaPrePrestamo prestamoH) {
		this.prestamoH = prestamoH;
	}

	public Set getCtaCasCuentaAsociados() {
		return prestamoH.getCtaCasCuentaAsociados();
	}

	public Set getCtaChkChequePrestamos() {
		return prestamoH.getCtaChkChequePrestamos();
	}

	public Set getCtaDexDescuentosExternoses() {
		return prestamoH.getCtaDexDescuentosExternoses();
	}

	public Set getCtaFxpFiadorPrestamos() {
		return prestamoH.getCtaFxpFiadorPrestamos();
	}

	public Set getCtaGarGarantias() {
		return prestamoH.getCtaGarGarantias();
	}

	public Set getCtaMxpMovimientoPrestamos() {
		return prestamoH.getCtaMxpMovimientoPrestamos();
	}

	public Set getCtaRcoReferenciasComercialeses() {
		return prestamoH.getCtaRcoReferenciasComercialeses();
	}

	public Set getCtaRpeReferenciasPersonaleses() {
		return prestamoH.getCtaRpeReferenciasPersonaleses();
	}

	public CtaSegSeguros getCtaSegSeguros() {
		return prestamoH.getCtaSegSeguros();
	}

	public CtaTinTasaInteres getCtaTinTasaInteres() {
		return prestamoH.getCtaTinTasaInteres();
	}

	public CtaTprTipoPrestamo getCtaTprTipoPrestamo() {
		return prestamoH.getCtaTprTipoPrestamo();
	}

	public Double getPreCuota() {
		return prestamoH.getPreCuota();
	}

	public String getPreFechaSolicitud() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (prestamoH.getPreFechaSolicitud()!=null)?(sdf.format(prestamoH.getPreFechaSolicitud())):null;
	}

	public Double getPreGastoAbogado() {
		return prestamoH.getPreGastoAbogado();
	}

	public String getPreId() {
		return prestamoH.getPreId();
	}

	public Double getPreInteresAcumulado() {
		return prestamoH.getPreInteresAcumulado();
	}

	public Double getPreLiquidoARecibir() {
		return prestamoH.getPreLiquidoARecibir();
	}

	public Double getPreMontoSolicitado() {
		return prestamoH.getPreMontoSolicitado();
	}

	public Double getPreMoraEmbargo() {
		return prestamoH.getPreMoraEmbargo();
	}

	public Integer getPreNumCuotaCancel() {
		return prestamoH.getPreNumCuotaCancel();
	}

	public String getPreReferencia() {
		return prestamoH.getPreReferencia();
	}

	public Double getPreSaldoActualT() {
		return prestamoH.getPreSaldoActualT();
	}

	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		prestamoH.setCtaCasCuentaAsociados(ctaCasCuentaAsociados);
	}

	public void setCtaChkChequePrestamos(Set ctaChkChequePrestamos) {
		prestamoH.setCtaChkChequePrestamos(ctaChkChequePrestamos);
	}

	public void setCtaDexDescuentosExternoses(Set ctaDexDescuentosExternoses) {
		prestamoH.setCtaDexDescuentosExternoses(ctaDexDescuentosExternoses);
	}

	public void setCtaFxpFiadorPrestamos(Set ctaFxpFiadorPrestamos) {
		prestamoH.setCtaFxpFiadorPrestamos(ctaFxpFiadorPrestamos);
	}

	public void setCtaGarGarantias(Set ctaGarGarantias) {
		prestamoH.setCtaGarGarantias(ctaGarGarantias);
	}

	public void setCtaMxpMovimientoPrestamos(Set ctaMxpMovimientoPrestamos) {
		prestamoH.setCtaMxpMovimientoPrestamos(ctaMxpMovimientoPrestamos);
	}

	public void setCtaRcoReferenciasComercialeses(
			Set ctaRcoReferenciasComercialeses) {
		prestamoH
				.setCtaRcoReferenciasComercialeses(ctaRcoReferenciasComercialeses);
	}

	public void setCtaRpeReferenciasPersonaleses(
			Set ctaRpeReferenciasPersonaleses) {
		prestamoH
				.setCtaRpeReferenciasPersonaleses(ctaRpeReferenciasPersonaleses);
	}

	public void setCtaSegSeguros(CtaSegSeguros ctaSegSeguros) {
		prestamoH.setCtaSegSeguros(ctaSegSeguros);
	}

	public void setCtaTinTasaInteres(CtaTinTasaInteres ctaTinTasaInteres) {
		prestamoH.setCtaTinTasaInteres(ctaTinTasaInteres);
	}

	public void setCtaTprTipoPrestamo(CtaTprTipoPrestamo ctaTprTipoPrestamo) {
		prestamoH.setCtaTprTipoPrestamo(ctaTprTipoPrestamo);
	}

	public void setPreCuota(Double preCuota) {
		prestamoH.setPreCuota(preCuota);
	}
	
	public void setPreFechaSolicitud(Date adufecString) {
			prestamoH.setPreFechaSolicitud(adufecString);
	}

	public void setPreFechaSolicitud(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			prestamoH.setPreFechaSolicitud(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}

	public void setPreGastoAbogado(Double preGastoAbogado) {
		prestamoH.setPreGastoAbogado(preGastoAbogado);
	}

	public void setPreId(String preId) {
		prestamoH.setPreId(preId);
	}

	public void setPreInteresAcumulado(Double preInteresAcumulado) {
		prestamoH.setPreInteresAcumulado(preInteresAcumulado);
	}

	public void setPreLiquidoARecibir(Double preLiquidoARecibir) {
		prestamoH.setPreLiquidoARecibir(preLiquidoARecibir);
	}

	public void setPreMontoSolicitado(Double preMontoSolicitado) {
		prestamoH.setPreMontoSolicitado(preMontoSolicitado);
	}

	public void setPreMoraEmbargo(Double preMoraEmbargo) {
		prestamoH.setPreMoraEmbargo(preMoraEmbargo);
	}

	public void setPreNumCuotaCancel(Integer preNumCuotaCancel) {
		prestamoH.setPreNumCuotaCancel(preNumCuotaCancel);
	}

	public void setPreReferencia(String preReferencia) {
		prestamoH.setPreReferencia(preReferencia);
	}

	public void setPreSaldoActualT(Double preSaldoActualT) {
		prestamoH.setPreSaldoActualT(preSaldoActualT);
	}

	public Long getCasCuenta() {
		return prestamoH.getCasCuenta();
	}

	public void setCasCuenta(Long casCuenta) {
		prestamoH.setCasCuenta(casCuenta);
	}
	
	public Double getPreAcumMov() {
		return prestamoH.getPreAcumMov();
	}

	public void setPreAcumMov(Double preAcumMov) {
		prestamoH.setPreAcumMov(preAcumMov);
	}

	public double getMontoSolicitadoAnterior() {
		return montoSolicitadoAnterior;
	}

	public void setMontoSolicitadoAnterior(double montoSolicitadoAnterior) {
		this.montoSolicitadoAnterior = montoSolicitadoAnterior;
	}

	public Double getNuevoMontoSolicitado() {
		return nuevoMontoSolicitado;
	}

	public void setNuevoMontoSolicitado(Double nuevoMontoSolicitado) {
		this.nuevoMontoSolicitado = nuevoMontoSolicitado;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public long[] getPosicionNotas() {
		return posicionNotas;
	}

	public void setPosicionNotas(long[] posicionNotas) {
		this.posicionNotas = posicionNotas;
	}

	public double getMontoRecibir() {
		return montoRecibir;
	}

	public void setMontoRecibir(double montoRecibir) {
		this.montoRecibir = montoRecibir;
	}
	
	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getAscCodigo() {
		return asociadoH.getAscCodigo();
	}

	public void setAscCodigo(String ascCodigo) {
		asociadoH.setAscCodigo(ascCodigo);
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Double getPreOtrasDeducciones() {
		return prestamoH.getPreOtrasDeducciones();
	}

	public void setPreOtrasDeducciones(Double preOtrasDeducciones) {
		prestamoH.setPreOtrasDeducciones(preOtrasDeducciones);
	}
	
	public Double getPreIvaDeducciones() {
		return prestamoH.getPreIvaDeducciones();
	}

	public void setPreIvaDeducciones(Double preIvaDeducciones) {
		prestamoH.setPreIvaDeducciones(preIvaDeducciones);
	}	

	public Double getPreMoraMov() {
		return prestamoH.getPreMoraMov();
	}

	public Double getPrePendMov() {
		return prestamoH.getPrePendMov();
	}

	public void setPreMoraMov(Double preMoraMov) {
		prestamoH.setPreMoraMov(preMoraMov);
	}

	public void setPrePendMov(Double prePendMov) {
		prestamoH.setPrePendMov(prePendMov);
	}

	public String getVencimiento() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (this.vencimiento!=null)?(sdf.format(this.vencimiento)):null;
	}
	
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public void setVencimiento(String vencimiento) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.vencimiento= sdf.parse(vencimiento);
		} catch (ParseException e) {
		}
	}

	public int getRefinanciado() {
		return refinanciado;
	}

	public void setRefinanciado(int refinanciado) {
		this.refinanciado = refinanciado;
	}

	public Double getAportaciones() {
		return aportaciones;
	}

	public void setAportaciones(Double aportaciones) {
		this.aportaciones = aportaciones;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Integer getLprId() {
		return lprId;
	}

	public void setLprId(Integer lprId) {
		this.lprId = lprId;
	}

	public Double getCuotaPlanilla() {
		return cuotaPlanilla;
	}

	public void setCuotaPlanilla(Double cuotaPlanilla) {
		this.cuotaPlanilla = cuotaPlanilla;
	}

	public Double getTasaMora() {
		return tasaMora;
	}

	public void setTasaMora(Double tasaMora) {
		this.tasaMora = tasaMora;
	}

	public Integer getCuotasPendientes() {
		return cuotasPendientes;
	}

	public void setCuotasPendientes(Integer cuotasPendientes) {
		this.cuotasPendientes = cuotasPendientes;
	}

	public Boolean getHidFuente() {
		return hidFuente;
	}

	public void setHidFuente(Boolean hidFuente) {
		this.hidFuente = hidFuente;
	}
//agregado
	public Double getInteresSolicitado() {
		return interesSolicitado;
	}

	public void setInteresSolicitado(Double interesSolicitado) {
		this.interesSolicitado = interesSolicitado;
	}

	public int getMesesSolicitados() {
		return mesesSolicitados;
	}

	public void setMesesSolicitados(int mesesSolicitados) {
		this.mesesSolicitados = mesesSolicitados;
	}

	public Integer getPlmId() {
		return plmId;
	}

	public void setPlmId(Integer plmId) {
		this.plmId = plmId;
	}

	public Integer getTinPlazoId() {
		return tinPlazoId;
	}

	public void setTinPlazoId(Integer tinPlazoId) {
		this.tinPlazoId = tinPlazoId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
