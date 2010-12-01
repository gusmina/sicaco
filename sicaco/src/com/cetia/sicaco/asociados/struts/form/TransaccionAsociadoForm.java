package com.cetia.sicaco.asociados.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorro;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCbaCuentaBancaria;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaSegSeguros;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccion;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociado;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociadoDAO;
import com.cetia.sicaco.struts.BasicForm;

public class TransaccionAsociadoForm extends BasicForm {

	/**
	 * 
	 */

	private static final long serialVersionUID = 5711779201810876875L;
	private CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociadoH = new  CtaTxaTransaccionxcuentaAsociado();
	private CtaAscAsociado asociadoH = new CtaAscAsociado();
	private CtaCasCuentaAsociado cuentaAsociadoH = new CtaCasCuentaAsociado();
	private Date fechaInicio;
	private Date fechaFin;
	private String tipoCuenta;
	private Long comprobante;
	
	private int fuente;
	private int destino;
	private int a;
	private int b;
	
	private Integer chequeNum;
	private int cuentaFuente;
	private int cuentaDestino;
	private String ascId2;
	private int banId;
	private double txaMontoD;
	private int banIdD;
	private String chequeNumD;
	
	private String tablaT;
	
	private long[] posicionMov;
	
	private double disponible;
	
	private int imprimirReporte;
	
	private String source;
	private Double cantidadDisponible;
	private Double cant;
	private long idMapaAhorro;
	private String cuentaBan;
	
	private Integer rckId;
	//prestamo o seguro
	private Integer pos;
	private Double dispo;
	
	public Date getFechaFinD() {
		return fechaFin;
	}

	public void setFechaFinD(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setFechaInicioD(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Date getFechaInicioD() {
		return this.fechaInicio;
	}

	public String getFechaInicio() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (this.fechaInicio!=null)?(sdf.format(this.fechaInicio)):null;
	}

	public void setFechaInicio(String fechaInicio) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.fechaInicio = (sdf.parse(fechaInicio));
		} catch (ParseException e) {
		}
	}	
	
	
	public String getfechaFin() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (this.fechaFin !=null)?(sdf.format(this.fechaFin)):null;
	}

	public void setfechaFin(String fechaFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.fechaFin = sdf.parse(fechaFin);
		} catch (ParseException e) {
		}
	}
	
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getAscId() {
		return asociadoH.getAscId();
	}

	public void setAscId(String ascId) {
		asociadoH.setAscId(ascId);
	}

	

	public Long getCasCuenta() {
		return cuentaAsociadoH.getCasCuenta();
	}

	public String getCasPrincipal() {
		return cuentaAsociadoH.getCasPrincipal();
	}

	public Double getCasValorApertura() {
		return cuentaAsociadoH.getCasValorApertura();
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return cuentaAsociadoH.getCtaAscAsociado();
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

	public void setCasCuenta(Long casCuenta) {
		cuentaAsociadoH.setCasCuenta(casCuenta);
	}

	public void setCasPrincipal(String casPrincipal) {
		cuentaAsociadoH.setCasPrincipal(casPrincipal);
	}

	public void setCasValorApertura(Double casValorApertura) {
		cuentaAsociadoH.setCasValorApertura(casValorApertura);
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		cuentaAsociadoH.setCtaAscAsociado(ctaAscAsociado);
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

	public CtaCasCuentaAsociado getCuentaAsociadoH() {
		return cuentaAsociadoH;
	}

	public void setCuentaAsociadoH(CtaCasCuentaAsociado cuentaAsociadoH) {
		this.cuentaAsociadoH = cuentaAsociadoH;
	}

	public CtaCasCuentaAsociado getCtaCasCuentaAsociado() {
		return ctaTxaTransaccionxcuentaAsociadoH.getCtaCasCuentaAsociado();
	}

	public Set getCtaMxaMovimientoAhorros() {
		return ctaTxaTransaccionxcuentaAsociadoH.getCtaMxaMovimientoAhorros();
	}

	public Set getCtaMxpMovimientoPrestamos() {
		return ctaTxaTransaccionxcuentaAsociadoH.getCtaMxpMovimientoPrestamos();
	}

	public CtaTtrTipoTransaccion getCtaTtrTipoTransaccion() {
		return ctaTxaTransaccionxcuentaAsociadoH.getCtaTtrTipoTransaccion();
	}

	public Long getTxaComprobante() {
		return ctaTxaTransaccionxcuentaAsociadoH.getTxaComprobante();
	}

	public Long getTxaId() {
		return ctaTxaTransaccionxcuentaAsociadoH.getTxaId();
	}

	public Double getTxaMonto() {
		return ctaTxaTransaccionxcuentaAsociadoH.getTxaMonto();
	}

	public void setCtaCasCuentaAsociado(
			CtaCasCuentaAsociado ctaCasCuentaAsociado) {
		ctaTxaTransaccionxcuentaAsociadoH
				.setCtaCasCuentaAsociado(ctaCasCuentaAsociado);
	}

	public void setCtaMxaMovimientoAhorros(Set ctaMxaMovimientoAhorros) {
		ctaTxaTransaccionxcuentaAsociadoH
				.setCtaMxaMovimientoAhorros(ctaMxaMovimientoAhorros);
	}

	public void setCtaMxpMovimientoPrestamos(Set ctaMxpMovimientoPrestamos) {
		ctaTxaTransaccionxcuentaAsociadoH
				.setCtaMxpMovimientoPrestamos(ctaMxpMovimientoPrestamos);
	}

	public void setCtaTtrTipoTransaccion(
			CtaTtrTipoTransaccion ctaTtrTipoTransaccion) {
		ctaTxaTransaccionxcuentaAsociadoH
				.setCtaTtrTipoTransaccion(ctaTtrTipoTransaccion);
	}

	public void setTxaComprobante(Long txaComprobante) {
		ctaTxaTransaccionxcuentaAsociadoH.setTxaComprobante(txaComprobante);
	}

	public void setTxaFecha(Date txaFecha) {
		ctaTxaTransaccionxcuentaAsociadoH.setTxaFecha(txaFecha);
	}

	public void setTxaId(Long txaId) {
		ctaTxaTransaccionxcuentaAsociadoH.setTxaId(txaId);
	}

	public void setTxaMonto(Double txaMonto) {
		ctaTxaTransaccionxcuentaAsociadoH.setTxaMonto(txaMonto);
	}

	public CtaTxaTransaccionxcuentaAsociado getCtaTxaTransaccionxcuentaAsociadoH() {
		return ctaTxaTransaccionxcuentaAsociadoH;
	}

	public void setCtaTxaTransaccionxcuentaAsociadoH(
			CtaTxaTransaccionxcuentaAsociado ctaTxaTransaccionxcuentaAsociadoH) {
		this.ctaTxaTransaccionxcuentaAsociadoH = ctaTxaTransaccionxcuentaAsociadoH;
	}
	
	public String getAudFechaCreacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaTxaTransaccionxcuentaAsociadoH.getAudFechaCreacion()!=null)?(sdf.format(ctaTxaTransaccionxcuentaAsociadoH.getAudFechaCreacion())):null;

	}

	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaTxaTransaccionxcuentaAsociadoH.getAudFechaModificacion()!=null)?(sdf.format(ctaTxaTransaccionxcuentaAsociadoH.getAudFechaModificacion())):null;

	}
	
	public void setAudFechaCreacion(Date fecha) {
		ctaTxaTransaccionxcuentaAsociadoH.setAudFechaCreacion(fecha);
	}

	public void setAudFechaModificacion(Date fecha) {
		ctaTxaTransaccionxcuentaAsociadoH.setAudFechaModificacion(fecha);
	}

	public void setAudFechaCreacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaTxaTransaccionxcuentaAsociadoH.setAudFechaCreacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public void setAudFechaModificacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaTxaTransaccionxcuentaAsociadoH.setAudFechaModificacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}

	public String getAudUsuarioCreacion() {
		return ctaTxaTransaccionxcuentaAsociadoH.getAudUsuarioCreacion();
	}

	public String getAudUsuarioModificacion() {
		return ctaTxaTransaccionxcuentaAsociadoH.getAudUsuarioModificacion();
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		ctaTxaTransaccionxcuentaAsociadoH
				.setAudUsuarioCreacion(audUsuarioCreacion);
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		ctaTxaTransaccionxcuentaAsociadoH
				.setAudUsuarioModificacion(audUsuarioModificacion);
	}

	public CtaAscAsociado getAsociadoH() {
		return asociadoH;
	}

	public void setAsociadoH(CtaAscAsociado asociadoH) {
		this.asociadoH = asociadoH;
	}


	public String getTxaFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaTxaTransaccionxcuentaAsociadoH.getTxaFecha()!=null)?(sdf.format(ctaTxaTransaccionxcuentaAsociadoH.getTxaFecha())):null;
	}

	public void setTxaFecha(String txaFecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaTxaTransaccionxcuentaAsociadoH.setTxaFecha(sdf.parse(txaFecha));
		} catch (ParseException e) {
		}
	}

	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return true;
	
	}

	public Set getCtaBxcBeneficiariosCuentas() {
		return cuentaAsociadoH.getCtaBxcBeneficiariosCuentas();
	}

	public void setCtaBxcBeneficiariosCuentas(Set ctaBxcBeneficiariosCuentas) {
		cuentaAsociadoH
				.setCtaBxcBeneficiariosCuentas(ctaBxcBeneficiariosCuentas);
	}

	public int getFuente() {
		return fuente;
	}

	public void setFuente(int fuente) {
		this.fuente = fuente;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public Integer getChequeNum() {
		return chequeNum;
	}

	public void setChequeNum(Integer chequeNum) {
		this.chequeNum = chequeNum;
	}

	public String getAscId2() {
		return ascId2;
	}

	public void setAscId2(String ascId2) {
		this.ascId2 = ascId2;
	}

	public int getBanId() {
		return banId;
	}

	public void setBanId(int banId) {
		this.banId = banId;
	}

	public double getTxaMontoD() {
		return txaMontoD;
	}

	public void setTxaMontoD(double txaMontoD) {
		this.txaMontoD = txaMontoD;
	}

	public int getBanIdD() {
		return banIdD;
	}

	public void setBanIdD(int banIdD) {
		this.banIdD = banIdD;
	}

	public String getChequeNumD() {
		return chequeNumD;
	}

	public void setChequeNumD(String chequeNumD) {
		this.chequeNumD = chequeNumD;
	}

	public int getCuentaFuente() {
		return cuentaFuente;
	}

	public void setCuentaFuente(int cuentaFuente) {
		this.cuentaFuente = cuentaFuente;
	}

	public int getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(int cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public long[] getPosicionMov() {
		return posicionMov;
	}

	public void setPosicionMov(long[] posicionMov) {
		this.posicionMov = posicionMov;
	}

	public String getTablaT() {
		return tablaT;
	}

	public void setTablaT(String tablaT) {
		this.tablaT = tablaT;
	}

	public double getDisponible() {
		return disponible;
	}

	public void setDisponible(double disponible) {
		this.disponible = disponible;
	}

	public int getImprimirReporte() {
		return imprimirReporte;
	}

	public void setImprimirReporte(int imprimirReporte) {
		this.imprimirReporte = imprimirReporte;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Double getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(Double cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public long getIdMapaAhorro() {
		return idMapaAhorro;
	}

	public void setIdMapaAhorro(long idMapaAhorro) {
		this.idMapaAhorro = idMapaAhorro;
	}

	public Double getCant() {
		return cant;
	}

	public void setCant(Double cant) {
		this.cant = cant;
	}

	public String getCuentaBan() {
		return cuentaBan;
	}

	public void setCuentaBan(String cuentaBan) {
		this.cuentaBan = cuentaBan;
	}

	public Integer getRckId() {
		return rckId;
	}

	public void setRckId(Integer rckId) {
		this.rckId = rckId;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Double getDispo() {
		return dispo;
	}

	public void setDispo(Double dispo) {
		this.dispo = dispo;
	}

	public Long getComprobante() {
		return comprobante;
	}

	public void setComprobante(Long comprobante) {
		this.comprobante = comprobante;
	}

}
