package com.cetia.sicaco.contabilidad.struts.form;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.struts.BasicForm;
import com.cetia.sicaco.hibernate.ConCpaConceptoPartida;
import com.cetia.sicaco.hibernate.ConCueCuenta;
import com.cetia.sicaco.hibernate.ConDpaDetallePartida;
import com.cetia.sicaco.hibernate.ConPcoPartidaContable;
import com.cetia.sicaco.hibernate.ConTpaTipoPartida;
import com.cetia.sicaco.hibernate.CtaChkChequePrestamo;
import com.cetia.sicaco.hibernate.CtrBanBanco;
import com.cetia.sicaco.hibernate.CtrCckControlCheques;
import com.cetia.sicaco.hibernate.CtrRckRepositorioCheques;
import com.cetia.sicaco.hibernate.SecSucSucursal;
public class PartidaContableForm extends BasicForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6741808581646393060L;
	private int nuevoComprobante;
	private boolean mdf;
	private ConPcoPartidaContable conPcoPartidaContableH = new ConPcoPartidaContable();
	private Date fechaFin;//para busqueda de partidas
	//private int chequeEstado;
	private int cpaID;
	private CtrRckRepositorioCheques repositorioCheques = new CtrRckRepositorioCheques();
	
	private String pcoNumeroCheque;//FIXME pendiente de eliminar este campo
	
	////////private CtaChkChequePrestamo chequePartida = new CtaChkChequePrestamo();
	/*datos del cheque*/
	private int chkCorrelativoCheque;
	private String chkEmitidoA;
	private Date chkFecha;
	private String chkLugar;
	private Float chkMontoEmitido;
	private String chkRazon;
	
	private int formDeRedireccion = 0;
	
	private ConDpaDetallePartida conDpaDetallePartidaH = new ConDpaDetallePartida();
	private ConDpaDetallePartida cuentaTemporal = new ConDpaDetallePartida();
	private boolean boolCargoAbono;
	private Double dpaTotalDebe = new Double(0);
	private Double dpaTotalHaber = new Double(0);
	private Double dpaDiferenciaDetalle = new Double(0);
	private int numeroRegistro = -1;
	DecimalFormat df = new DecimalFormat("0.00");
	private String fecha;
	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public ConPcoPartidaContable getConPcoPartidaContableH() {
		return conPcoPartidaContableH;
	}
	public void setConPcoPartidaContableH(
			ConPcoPartidaContable conPcoPartidaContableH) {
		this.conPcoPartidaContableH = conPcoPartidaContableH;
	}
	public Date getAudFechaModificacion() {
		return conPcoPartidaContableH.getAudFechaModificacion();
	}
	public String getAudUsuarioCreacion() {
		return conPcoPartidaContableH.getAudUsuarioCreacion();
	}
	public String getAudUsuarioModificacion() {
		return conPcoPartidaContableH.getAudUsuarioModificacion();
	}
	public ConCpaConceptoPartida getConCpaConceptoPartida() {
		return conPcoPartidaContableH.getConCpaConceptoPartida();
	}
	public Set getConDpaDetallePartidas() {
		return conPcoPartidaContableH.getConDpaDetallePartidas();
	}
	public ConTpaTipoPartida getConTpaTipoPartida() {
		return conPcoPartidaContableH.getConTpaTipoPartida();
	}
	public Integer getPcoChequePendiente() {
		return conPcoPartidaContableH.getPcoChequePendiente();
	}
	public Integer getPcoComprobantePartida() {
		return conPcoPartidaContableH.getPcoComprobantePartida();
	}
	public String getPcoEstado() {
		return conPcoPartidaContableH.getPcoEstado();
	}
	public long getPcoId() {
		return conPcoPartidaContableH.getPcoId();
	}
	public String getPcoOtroConcepto() {
		return conPcoPartidaContableH.getPcoOtroConcepto();
	}
	
	public void setAudFechaModificacion(Date audFechaModificacion) {
		conPcoPartidaContableH.setAudFechaModificacion(audFechaModificacion);
	}
	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		conPcoPartidaContableH.setAudUsuarioCreacion(audUsuarioCreacion);
	}
	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		conPcoPartidaContableH
				.setAudUsuarioModificacion(audUsuarioModificacion);
	}
	public void setConCpaConceptoPartida(ConCpaConceptoPartida conCpaConceptoPartida) {
		conPcoPartidaContableH.setConCpaConceptoPartida(conCpaConceptoPartida);
	}
	public void setConDpaDetallePartidas(Set conDpaDetallePartidas) {
		conPcoPartidaContableH.setConDpaDetallePartidas(conDpaDetallePartidas);
	}
	public void setConTpaTipoPartida(ConTpaTipoPartida conTpaTipoPartida) {
		conPcoPartidaContableH.setConTpaTipoPartida(conTpaTipoPartida);
	}
	public void setPcoChequePendiente(Integer pcoChequePendiente) {
		conPcoPartidaContableH.setPcoChequePendiente(pcoChequePendiente);
	}
	public void setPcoComprobantePartida(Integer pcoComprobantePartida) {
		conPcoPartidaContableH.setPcoComprobantePartida(pcoComprobantePartida);
	}
	public void setPcoEstado(String pcoEstado) {
		conPcoPartidaContableH.setPcoEstado(pcoEstado);
	}
	public void setPcoId(long pcoId) {
		conPcoPartidaContableH.setPcoId(pcoId);
	}
	public void setPcoOtroConcepto(String pcoOtroConcepto) {
		conPcoPartidaContableH.setPcoOtroConcepto(pcoOtroConcepto);
	}
	public String getPcoFechaIngresoPartida() {//tambien se utiiliza como fecha de inicio para el intervalo de busqueda
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (conPcoPartidaContableH.getPcoFechaIngresoPartida()!=null)?(sdf.format(conPcoPartidaContableH.getPcoFechaIngresoPartida())):null;
	}

	public void setPcoFechaIngresoPartida(String pcoFechaIngresoPartida) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date fecha = sdf.parse(pcoFechaIngresoPartida);
			conPcoPartidaContableH.setPcoFechaIngresoPartida(fecha);
		} catch (ParseException e) {
		}
		
	}
	public Integer getPcoChequeNegociable() {
		return conPcoPartidaContableH.getPcoChequeNegociable();
	}
	public Integer getPcoModulo() {
		return conPcoPartidaContableH.getPcoModulo();
	}
	public void setPcoChequeNegociable(Integer pcoChequeNegociable) {
		conPcoPartidaContableH.setPcoChequeNegociable(pcoChequeNegociable);
	}
	public void setPcoModulo(Integer pcoModulo) {
		conPcoPartidaContableH.setPcoModulo(pcoModulo);
	}
	public Date getAudFechaCreacion() {
		return conPcoPartidaContableH.getAudFechaCreacion();
	}
	
	public void setAudFechaCreacion(Date audFechaCreacion) {
		conPcoPartidaContableH.setAudFechaCreacion(audFechaCreacion);
	}
	public CtaChkChequePrestamo getCtaChkChequePrestamo() {
		return conPcoPartidaContableH.getCtaChkChequePrestamo();
	}
	
	public void setCtaChkChequePrestamo(
			CtaChkChequePrestamo ctaChkChequePrestamo) {
		conPcoPartidaContableH.setCtaChkChequePrestamo(ctaChkChequePrestamo);
	}
	
	public String getFechaFin() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (this.fechaFin!=null)?(sdf.format(this.fechaFin)):null;
	}
	
	public void setFechaFin(String fechaFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.fechaFin = sdf.parse(fechaFin);
		} catch (ParseException e) {
		}
	}
	
	public int getNuevoComprobante() {
		return nuevoComprobante;
	}
	
	public void setNuevoComprobante(int nuevoComprobante) {
		this.nuevoComprobante = nuevoComprobante;
	}
	
	public ConDpaDetallePartida getConDpaDetallePartidaH() {
		return conDpaDetallePartidaH;
	}
	public void setConDpaDetallePartidaH(ConDpaDetallePartida conDpaDetallePartidaH) {
		this.conDpaDetallePartidaH = conDpaDetallePartidaH;
	}
	public ConCueCuenta getConCueCuenta() {
		return conDpaDetallePartidaH.getConCueCuenta();
	}
	public ConPcoPartidaContable getConPcoPartidaContable() {
		return conDpaDetallePartidaH.getConPcoPartidaContable();
	}
	public long getDpaId() {
		return conDpaDetallePartidaH.getDpaId();
	}
	public String getDpaOtroConcepto() {
		return conDpaDetallePartidaH.getDpaOtroConcepto();
	}
	public double getDpaValorDebe() {
		return conDpaDetallePartidaH.getDpaValorDebe();
	}
	public double getDpaValorHaber() {
		return conDpaDetallePartidaH.getDpaValorHaber();
	}
	public void setConCueCuenta(ConCueCuenta conCueCuenta) {
		conDpaDetallePartidaH.setConCueCuenta(conCueCuenta);
	}
	public void setConPcoPartidaContable(
			ConPcoPartidaContable conPcoPartidaContable) {
		conDpaDetallePartidaH.setConPcoPartidaContable(conPcoPartidaContable);
	}
	public void setDpaId(long dpaId) {
		conDpaDetallePartidaH.setDpaId(dpaId);
	}
	public void setDpaOtroConcepto(String dpaOtroConcepto) {
		conDpaDetallePartidaH.setDpaOtroConcepto(dpaOtroConcepto);
	}
	public void setDpaValorDebe(double dpaValorDebe) {
		conDpaDetallePartidaH.setDpaValorDebe(dpaValorDebe);
	}
	public void setDpaValorHaber(double dpaValorHaber) {
		conDpaDetallePartidaH.setDpaValorHaber(dpaValorHaber);
	}
	public ConCpaConceptoPartida getConCpaConceptoPartidaD() {
		return conDpaDetallePartidaH.getConCpaConceptoPartida();
	}
	public void setConCpaConceptoPartidaD(ConCpaConceptoPartida conCpaConceptoPartida) {
		conDpaDetallePartidaH.setConCpaConceptoPartida(conCpaConceptoPartida);
	}
	public boolean isBoolCargoAbono() {
		return boolCargoAbono;
	}
	public void setBoolCargoAbono(boolean boolCargoAbono) {
		this.boolCargoAbono = boolCargoAbono;
	}
	public int getFormDeRedireccion() {
		return formDeRedireccion;
	}
	public void setFormDeRedireccion(int formDeRedireccion) {
		this.formDeRedireccion = formDeRedireccion;
	}
	public Double getDpaTotalDebe() {
		return new Double(df.format(dpaTotalDebe));
	}
	public void setDpaTotalDebe(Double dpaTotalDebe) {
		this.dpaTotalDebe = dpaTotalDebe;
	}
	public Double getDpaTotalHaber() {
		return new Double(df.format(dpaTotalHaber));
	}
	public void setDpaTotalHaber(Double dpaTotalHaber) {
		this.dpaTotalHaber = dpaTotalHaber;
	}
	public Double getDpaDiferenciaDetalle() {		
		return new Double(df.format(dpaDiferenciaDetalle));
	}
	public void setDpaDiferenciaDetalle(Double dpaDiferenciaDetalle) {
		this.dpaDiferenciaDetalle = dpaDiferenciaDetalle;
	}
/*	public int getChequeEstado() {
		return chequeEstado;
	}
	public void setChequeEstado(int chequeEstado) {
		this.chequeEstado = chequeEstado;
	}*/
	
	public ConDpaDetallePartida getCuentaTemporal() {
		return cuentaTemporal;
	}
	public void setCuentaTemporal(ConDpaDetallePartida cuentaTemporal) {
		this.cuentaTemporal = cuentaTemporal;
	}
	
	public String getPcoNumeroCheque() {
		return pcoNumeroCheque;
	}
	public void setPcoNumeroCheque(String pcoNumeroCheque) {
		pcoNumeroCheque = pcoNumeroCheque;
	}
	
	/*DATOS DEL CHEQUE*/
	public int getChkCorrelativoCheque() {
		return chkCorrelativoCheque;
	}

	public void setChkCorrelativoCheque(int chkCorrelativoCheque) {
		this.chkCorrelativoCheque = chkCorrelativoCheque;
	}

	public String getChkEmitidoA() {
		return chkEmitidoA;
	}

	public void setChkEmitidoA(String chkEmitidoA) {
		this.chkEmitidoA = chkEmitidoA;
	}

	public String getChkFecha() {
		//return chkFecha;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (this.chkFecha!=null)?(sdf.format(this.chkFecha)):null;
	}
	
	public Date getChkFechaD() {
		return this.chkFecha;
	}
	
	public void setChkFecha(String chkFecha) {
		//this.chkFecha = chkFecha;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try{
			this.chkFecha= sdf.parse(chkFecha);
		}catch(ParseException e){
			
		}
	}

	public String getChkLugar() {
		return chkLugar;
	}

	public void setChkLugar(String chkLugar) {
		this.chkLugar = chkLugar;
	}

	public Float getChkMontoEmitido() {
		return chkMontoEmitido;
	}

	public void setChkMontoEmitido(Float chkMontoEmitido) {
		this.chkMontoEmitido = chkMontoEmitido;
	}

	public String getChkRazon() {
		return chkRazon;
	}

	public void setChkRazon(String chkRazon) {
		this.chkRazon = chkRazon;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/*public Integer getChkCorrelativoCheque() {
		return chequePartida.getChkCorrelativoCheque();
	}

	public String getChkEmitidoA() {
		return chequePartida.getChkEmitidoA();
	}

	
	/*
	public String getChkFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (chequePartida.getChkFecha()!=null)?(sdf.format(chequePartida.getChkFecha())):null;
	}

	public Integer getChkId() {
		return chequePartida.getChkId();
	}

	public String getChkLugar() {
		return chequePartida.getChkLugar();
	}

	public Float getChkMontoEmitido() {
		return chequePartida.getChkMontoEmitido();
	}

	public String getChkRazon() {
		return chequePartida.getChkRazon();
	}
	
	

	public Set getConPcoPartidaContables() {
		return chequePartida.getConPcoPartidaContables();
	}

	public void setChkCorrelativoCheque(Integer chkCorrelativoCheque) {
		chequePartida.setChkCorrelativoCheque(chkCorrelativoCheque);
	}

	public void setChkEmitidoA(String chkEmitidoA) {
		chequePartida.setChkEmitidoA(chkEmitidoA);
	}
	
	public void setChkFecha(String chkFecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try{
			chequePartida.setChkFecha(sdf.parse(chkFecha));
		}catch(ParseException e){
			
		}
	}

	/*
	public void setChkId(Integer chkId) {
		chequePartida.setChkId(chkId);
	}

	public void setChkLugar(String chkLugar) {
		chequePartida.setChkLugar(chkLugar);
	}

	public void setChkMontoEmitido(Float chkMontoEmitido) {
		chequePartida.setChkMontoEmitido(chkMontoEmitido);
	}

	public void setChkRazon(String chkRazon) {
		chequePartida.setChkRazon(chkRazon);
	}

	public void setConPcoPartidaContables(Set conPcoPartidaContables) {
		chequePartida.setConPcoPartidaContables(conPcoPartidaContables);
	}

	public CtaChkChequePrestamo getChequePartida() {
		return chequePartida;
	}

	public void setChequePartida(CtaChkChequePrestamo chequePartida) {
		this.chequePartida = chequePartida;
	}
*/

	public CtrRckRepositorioCheques getRepositorioCheques() {
		return repositorioCheques;
	}

	public void setRepositorioCheques(CtrRckRepositorioCheques repositorioCheques) {
		this.repositorioCheques = repositorioCheques;
	}

	public CtrCckControlCheques getCtrCckControlCheques() {
		return repositorioCheques.getCtrCckControlCheques();
	}

	public Integer getRckCorrActual() {
		return repositorioCheques.getRckCorrActual();
	}

	public Integer getRckCorrFin() {
		return repositorioCheques.getRckCorrFin();
	}

	public Integer getRckCorrIni() {
		return repositorioCheques.getRckCorrIni();
	}

	public String getRckEstado() {
		return repositorioCheques.getRckEstado();
	}

	public Date getRckFechaFin() {
		return repositorioCheques.getRckFechaFin();
	}

	public Date getRckFechaIni() {
		return repositorioCheques.getRckFechaIni();
	}

	public Integer getRckId() {
		return repositorioCheques.getRckId();
	}

	public String getRckNombre() {
		return repositorioCheques.getRckNombre();
	}

	public SecSucSucursal getSecSucSucursal() {
		return repositorioCheques.getSecSucSucursal();
	}

	public void setCtrCckControlCheques(
			CtrCckControlCheques ctrCckControlCheques) {
		repositorioCheques.setCtrCckControlCheques(ctrCckControlCheques);
	}

	public void setRckCorrActual(Integer rckCorrActual) {
		repositorioCheques.setRckCorrActual(rckCorrActual);
	}

	public void setRckCorrFin(Integer rckCorrFin) {
		repositorioCheques.setRckCorrFin(rckCorrFin);
	}

	public void setRckCorrIni(Integer rckCorrIni) {
		repositorioCheques.setRckCorrIni(rckCorrIni);
	}

	public void setRckEstado(String rckEstado) {
		repositorioCheques.setRckEstado(rckEstado);
	}

	public void setRckFechaFin(Date rckFechaFin) {
		repositorioCheques.setRckFechaFin(rckFechaFin);
	}

	public void setRckFechaIni(Date rckFechaIni) {
		repositorioCheques.setRckFechaIni(rckFechaIni);
	}

	public void setRckId(Integer rckId) {
		repositorioCheques.setRckId(rckId);
	}

	public void setRckNombre(String rckNombre) {
		repositorioCheques.setRckNombre(rckNombre);
	}

	public void setSecSucSucursal(SecSucSucursal secSucSucursal) {
		repositorioCheques.setSecSucSucursal(secSucSucursal);
	}

	public int getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(int numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public boolean isMdf() {
		return mdf;
	}

	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}

	public int getCpaID() {
		return cpaID;
	}

	public void setCpaID(int cpaID) {
		this.cpaID = cpaID;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
