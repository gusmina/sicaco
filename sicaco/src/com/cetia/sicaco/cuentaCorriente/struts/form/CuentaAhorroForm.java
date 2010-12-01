package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorro;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaTahTipoAhorro;
import com.cetia.sicaco.hibernate.CtrEstEstado;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.struts.BasicForm;

public class CuentaAhorroForm extends BasicForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2638732536753679061L;
	private boolean mdf = false;
	private Date fechaIni;
	private Date fechaFin;
	private Double penalidad;
	private boolean opPenalidad = false;
	private CtaCahCuentaAhorro ctaCahCuentaAhorroH = new CtaCahCuentaAhorro();
	private CtaAscAsociado ctaAscAsociadoH = new CtaAscAsociado();
	private CtaCasCuentaAsociado ctaCasCuentaAsociado = new CtaCasCuentaAsociado();
	private boolean apertura;
	private long comprobante; // se utiliza para generar el comprobante al aperturar una cuenta 
	
	
	
	public void setCasCuenta(Long casCuenta) {
		ctaCasCuentaAsociado.setCasCuenta(casCuenta);
	}

	public long getComprobante() {
		return comprobante;
	}

	public void setComprobante(long comprobante) {
		this.comprobante = comprobante;
	}

	public boolean isApertura() {
		return apertura;
	}

	public void setApertura(boolean apertura) {
		this.apertura = apertura;
	}

	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isMdf() {
		return mdf;
	}

	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}
	public String getFechaIni() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (fechaIni!=null)?(sdf.format(fechaIni)):null;
	}

	public void setFechaIni(String fechaIni) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.fechaIni = sdf.parse(fechaIni);
		} catch (ParseException e) {
		}
	}

	public String getFechaFin() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (fechaFin!=null)?(sdf.format(fechaFin)):null;
	}

	public void setFechaFin(String fechaFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.fechaFin = sdf.parse(fechaFin);
		} catch (ParseException e) {
		}
	}


	
	//**//
	
	public CtaCahCuentaAhorro getCtaCahCuentaAhorroH() {
		return ctaCahCuentaAhorroH;
	}

	public void setCtaCahCuentaAhorroH(CtaCahCuentaAhorro ctaCahCuentaAhorroH) {
		this.ctaCahCuentaAhorroH = ctaCahCuentaAhorroH;
	}

	public Double getCahCuota() {
		return ctaCahCuentaAhorroH.getCahCuota();
	}

	public String getCahId() {
		return ctaCahCuentaAhorroH.getCahId();
	}

	public Double getCahInteresAcumulado() {
		return ctaCahCuentaAhorroH.getCahInteresAcumulado();
	}

	

	public Double getCahSaldoActual() {
		return ctaCahCuentaAhorroH.getCahSaldoActual();
	}

	

	public Set getCtaMxaMovimientoAhorros() {
		return ctaCahCuentaAhorroH.getCtaMxaMovimientoAhorros();
	}

	public Set getCtaSmaSaldosxmesActivos() {
		return ctaCahCuentaAhorroH.getCtaSmaSaldosxmesActivos();
	}

	public CtaTahTipoAhorro getCtaTahTipoAhorro() {
		return ctaCahCuentaAhorroH.getCtaTahTipoAhorro();
	}

	public void setCahCuota(Double cahCuota) {
		ctaCahCuentaAhorroH.setCahCuota(cahCuota);
	}

	public void setCahId(String cahId) {
		ctaCahCuentaAhorroH.setCahId(cahId);
	}

	

	public void setCahInteresAcumulado(Double cahInteresAcumulado) {
		ctaCahCuentaAhorroH.setCahInteresAcumulado(cahInteresAcumulado);
	}

	
	public void setCahSaldoActual(Double cahSaldoActual) {
		ctaCahCuentaAhorroH.setCahSaldoActual(cahSaldoActual);
	}

	public void setCtaMxaMovimientoAhorros(Set ctaMxaMovimientoAhorros) {
		ctaCahCuentaAhorroH.setCtaMxaMovimientoAhorros(ctaMxaMovimientoAhorros);
	}

	public void setCtaSmaSaldosxmesActivos(Set ctaSmaSaldosxmesActivos) {
		ctaCahCuentaAhorroH.setCtaSmaSaldosxmesActivos(ctaSmaSaldosxmesActivos);
	}

	public void setCtaTahTipoAhorro(CtaTahTipoAhorro ctaTahTipoAhorro) {
		ctaCahCuentaAhorroH.setCtaTahTipoAhorro(ctaTahTipoAhorro);
	}

	//**//
	public CtaAscAsociado getCtaAscAsociadoH() {
		return ctaAscAsociadoH;
	}

	public void setCtaAscAsociadoH(CtaAscAsociado ctaAscAsociadoH) {
		this.ctaAscAsociadoH = ctaAscAsociadoH;
	}

	public String getAscCodigo() {
		return ctaAscAsociadoH.getAscCodigo();
	}

	public String getAscId() {
		return ctaAscAsociadoH.getAscId();
	}

	public Date getAscIngresoCoope() {
		return ctaAscAsociadoH.getAscIngresoCoope();
	}

	public String getAscNombreNit() {
		return ctaAscAsociadoH.getAscNombreNit();
	}

	public Integer getEstId() {
		return ctaAscAsociadoH.getEstId();
	}

	public void setAscCodigo(String ascCodigo) {
		ctaAscAsociadoH.setAscCodigo(ascCodigo);
	}

	public void setAscId(String ascId) {
		ctaAscAsociadoH.setAscId(ascId);
	}

	public void setAscNombreNit(String ascNombreNit) {
		ctaAscAsociadoH.setAscNombreNit(ascNombreNit);
	}

	public void setEstId(Integer estId) {
		ctaAscAsociadoH.setEstId(estId);
	}
	
	
	//**//
	public CtaCasCuentaAsociado getCtaCasCuentaAsociado() {
		return ctaCasCuentaAsociado;
	}

	public void setCtaCasCuentaAsociado(CtaCasCuentaAsociado ctaCasCuentaAsociado) {
		this.ctaCasCuentaAsociado = ctaCasCuentaAsociado;
	}

	public Long getCasCuenta() {
		return ctaCasCuentaAsociado.getCasCuenta();
	}

	public String getCasFechaApertura() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaCasCuentaAsociado.getCasFechaApertura()!=null)?(sdf.format(ctaCasCuentaAsociado.getCasFechaApertura())):null;
	}

	public String getCasFechaCierre() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaCasCuentaAsociado.getCasFechaCierre()!=null)?(sdf.format(ctaCasCuentaAsociado.getCasFechaCierre())):null;
	}

	public String getCasPrincipal() {
		return ctaCasCuentaAsociado.getCasPrincipal();
	}

	public Double getCasValorApertura() {
		return ctaCasCuentaAsociado.getCasValorApertura();
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return ctaCasCuentaAsociado.getCtaAscAsociado();
	}

	public void setCasFechaApertura(String casFechaApertura) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaCasCuentaAsociado.setCasFechaApertura(sdf.parse(casFechaApertura));
		} catch (ParseException e) {
		}
	}

	public void setCasFechaCierre(String casFechaCierre) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaCasCuentaAsociado.setCasFechaCierre(sdf.parse(casFechaCierre));
		} catch (ParseException e) {
		}
	}

	public void setCasPrincipal(String casPrincipal) {
		ctaCasCuentaAsociado.setCasPrincipal(casPrincipal);
	}

	public void setCasValorApertura(Double casValorApertura) {
		ctaCasCuentaAsociado.setCasValorApertura(casValorApertura);
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		ctaCasCuentaAsociado.setCtaAscAsociado(ctaAscAsociado);
	}

	public Set getCtaCasCuentaAsociados() {
		return ctaAscAsociadoH.getCtaCasCuentaAsociados();
	}

	public SecPerPersona getSecPerPersona() {
		return ctaAscAsociadoH.getSecPerPersona();
	}

	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		ctaAscAsociadoH.setCtaCasCuentaAsociados(ctaCasCuentaAsociados);
	}

	public void setSecPerPersona(SecPerPersona secPerPersona) {
		ctaAscAsociadoH.setSecPerPersona(secPerPersona);
	}

	public Set getCtaBxcBeneficiariosCuentas() {
		return ctaCasCuentaAsociado.getCtaBxcBeneficiariosCuentas();
	}

	public void setCtaBxcBeneficiariosCuentas(Set ctaBxcBeneficiariosCuentas) {
		ctaCasCuentaAsociado
				.setCtaBxcBeneficiariosCuentas(ctaBxcBeneficiariosCuentas);
	}

	public Double getPenalidad() {
		return penalidad;
	}

	public void setPenalidad(Double penalidad) {
		this.penalidad = penalidad;
	}

	public boolean isOpPenalidad() {
		return opPenalidad;
	}

	public void setOpPenalidad(boolean opPenalidad) {
		this.opPenalidad = opPenalidad;
	}

	

}
