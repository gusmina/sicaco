package com.mad.utilidades;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.record.formula.functions.Today;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cetia.sicaco.hibernate.CtaAscAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaMxpMovimientoPrestamo;
import com.cetia.sicaco.hibernate.CtaNotNotas;
import com.cetia.sicaco.hibernate.CtaNotNotasDAO;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaPrePrestamoDAO;
import com.cetia.sicaco.hibernate.CtrParParametros;
import com.cetia.sicaco.hibernate.CtrParParametrosDAO;
import com.mad.utilidades.filtros.FiltroOpenSession;

/**
 * 
 * @author Francisco Lozano
 * @category Utilidad
 *
 */
public class IntereseYMora {
	private static final Log log = LogFactory.getLog(CtaAscAsociadoDAO.class);
	
	private Double mora;
	private Double pendiente;
	private Double acumulado;
	private Double porcInteres;
	
	public IntereseYMora() {
		super();
	}

	public IntereseYMora(Double mora, Double pendiente, Double acumulado,
			Double porcInteres) {
		super();
		this.mora = mora;
		this.pendiente = pendiente;
		this.acumulado = acumulado;
		this.porcInteres = porcInteres;
	}
	public Double getMora() {
		return mora;
	}
	public void setMora(Double mora) {
		this.mora = mora;
	}
	public Double getPendiente() {
		DecimalFormat df = new DecimalFormat("0.00");
		if(pendiente == null) pendiente = 0.00;
		return new Double(df.format(pendiente));
	}
	public void setPendiente(Double pendiente) {
		this.pendiente = pendiente;
	}
	public Double getAcumulado() {
		DecimalFormat df = new DecimalFormat("0.00");
		if (acumulado==null) acumulado = 0.00;
		return new Double(df.format(acumulado));
	}
	public void setAcumulado(Double acumulado) {
		this.acumulado = acumulado;
	}
	public Double getPorcInteres() {
		return porcInteres;
	}
	public void setPorcInteres(Double porcInteres) {
		this.porcInteres = porcInteres;
	}
	
	/**
	 * 
	 * @param mxpAnt (Movimiento anterior del prestamo, si no hay anterior ingresar un new de mxp)
	 * @param prestamo (Prestamo para el cual se desea calcular los intereses)
	 * @param cas (Cuenta del Asociado a la cual pertenece el prestamo)
	 * @param fechaTeorica (Fecha para la que se esta realizando el calculo)
	 * @return Instancia del objeto IntereseYMora 
	 * el cual contiene dentro de el mora, pendiente, acumulado y porcInteres actualizados.
	 */
	public IntereseYMora actualizaInteres(CtaMxpMovimientoPrestamo mxpAnt,
			CtaPrePrestamo prestamo, CtaCasCuentaAsociado cas, Date fechaTeorica,HttpServletRequest request) {
		CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
		
		//Fecha inicial para contar los intereses en el caso que no existiese movimiento anterior
		Date fechaSinMov = parametrosDAO.findById("INICIO_ACUMULACION_INTERESES").getParValorDate();
		IntereseYMora iYm = new IntereseYMora();
		iYm.setMora(0.0);
		iYm.setPendiente(0.0);
		iYm.setAcumulado(0.0);
		iYm.setPorcInteres(0.0);
		if(mxpAnt == null || mxpAnt.getMxpId() == null){
			if(prestamo.getPrePendMov() != null){
				iYm.setPendiente(prestamo.getPrePendMov());
			}
			/*if(prestamo.getPreMoraMov() != null){
				iYm.setMora(prestamo.getPreMoraMov());
			}*/
		}
		/*if(prestamo.getPreMoraMov() == null){
			prestamo.setPreMoraMov(0.0);
		}*/
		if(prestamo.getPrePendMov() == null){
			prestamo.setPrePendMov(0.0);
		}
		if(prestamo.getPreAcumMov() == null){
			prestamo.setPreAcumMov(0.0);
		}
		
		ElapsedTime et = new ElapsedTime();
		//Parametro que indica cuantos meses tienen que pasar 
		//para empezar a cobrar la mora como interes pendiente
		CtrParParametros mesesMoraPar = parametrosDAO.findById("MESES_INICIO_MORA");
		Integer mesesMora = mesesMoraPar.getParValorNumber().intValue();
		
		//Cantidades actuales antes de iniciar el movimiento en si
		//iYm.setMora(iYm.getMora() + prestamo.getPreMoraMov());
		iYm.setPendiente(iYm.getPendiente() + prestamo.getPrePendMov());
		iYm.setAcumulado(iYm.getAcumulado() + prestamo.getPreAcumMov());
		if(prestamo.getPreCredito() == null 
				|| (!prestamo.getPreCredito().equals("O") 
				&& !prestamo.getPreCredito().equals("F")
				&& !prestamo.getPreCredito().equals("A"))){
			DecimalFormat df = new DecimalFormat("0.00");
			Double por;
			por =prestamo.getCtaTprTipoPrestamo().getCtaTinTasaInteres().getTinTasa()/12;
			//por = Double.parseDouble(df.format(prestamo.getCtaTprTipoPrestamo().getCtaTinTasaInteres().getTinTasa()/12));
			iYm.setPorcInteres(por);
			
			if(cas.getCasFechaCierre() == null){
				//obtener fecha meses 
				cas.setCasFechaCierre(ElapsedTime.obtenerFechaMeses(cas.getCasFechaApertura(), 
						prestamo.getCtaTprTipoPrestamo().getCtaPlmPlanMeses().getPlmDuracion()));
				if(cas.getCtaAscAsociado() == null || cas.getCtaAscAsociado().getAscId() == null){
					cas.setCtaAscAsociado(null);
				}else{
					cas.setCtaPxtPersonaExterna(null);
				}
				CtaCasCuentaAsociadoDAO casDao = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
				Transaction tx = casDao.getSession().beginTransaction();
				//log.error("cas cuenta: " + cas.getCasCuenta() + "; prestamo: " + prestamo.getPreId());
				casDao.merge(cas);
				tx.commit();
			}
			
			//se verifica si la fecha de cierre es menor que la fecha teorica de hoy, 
			//es decir, si la cuenta ya deberia estar cerrada 
			if(ElapsedTime.fechaMenor(cas.getCasFechaCierre(), fechaTeorica)){
				//Verifica si el movimiento anterior fue hace x meses y si ya se le puede empezar a cobrar mora
				//luego de la primera vez se mantiene siempre cobrandose
				//Continuar Abajo
				
				//Cargar la cantidad de meses que deben pasar para que se cargue al abogado
				CtrParParametros parMesAbogado = parametrosDAO.findById("MESES_PAGO_ABOGADO");
				//Cargar el porcentaje que se cobra por el abogado
				CtrParParametros parPorcAbogado = parametrosDAO.findById("PAGO_ABOGADO");
				
				if(mxpAnt!=null && mxpAnt.getMxpId()!= null
						&& (ElapsedTime.fechaMenor(ElapsedTime.obtenerFechaMeses(mxpAnt.getMxpFecha(), mesesMora),fechaTeorica)
								|| (prestamo.getPreMoraMov()!= null && prestamo.getPreMoraMov()>=1))){
					iYm.setMora(prestamo.getCtaTinTasaInteres().getTinTasa()/12);
					if(prestamo.getPreMoraMov()==null || prestamo.getPreMoraMov()<1){
						prestamo.setPreMoraMov(1.0);
					}
				}else{
					if(ElapsedTime.fechaMenor(ElapsedTime.obtenerFechaMeses(fechaSinMov, mesesMora),fechaTeorica)
								|| (prestamo.getPreMoraMov()!= null && prestamo.getPreMoraMov()>=1)){
						iYm.setMora(prestamo.getCtaTinTasaInteres().getTinTasa()/12);
						if(prestamo.getPreMoraMov()==null || prestamo.getPreMoraMov()<1){
							prestamo.setPreMoraMov(1.0);
						}
					}
				}
				
				CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
				if(!prestamoDAO.notaEspecificaPorPrestamo(cas.getCasCuenta(), "Pagar a abogado")){
				
					if(mxpAnt!=null && mxpAnt.getMxpId()!= null
							&& (ElapsedTime.fechaMenor(
									ElapsedTime.obtenerFechaMeses(
											mxpAnt.getMxpFecha(), 
											parMesAbogado.getParValorNumber().intValue()),
											fechaTeorica))){
						CtaNotNotas notaAbogado = new CtaNotNotas();
						notaAbogado.setCasCuenta(cas.getCasCuenta());
						notaAbogado.setCtaTntTipoNota(null);
						notaAbogado.setNotCampo("Pagar a abogado");
						notaAbogado.setNotFecha(new Date());
						Double pago = prestamo.getPreSaldoActualT()*parPorcAbogado.getParValorNumber()/100;
						notaAbogado.setNotNota(Format.formatDinero(pago));
						CtaNotNotasDAO notDao = new CtaNotNotasDAO(getSessionHibernate(request));
						notaAbogado.setNotId(notDao.nextId());
						notDao.save(notaAbogado);
					}else{
						if(ElapsedTime.fechaMenor(ElapsedTime.obtenerFechaMeses(fechaSinMov,parMesAbogado.getParValorNumber().intValue()),fechaTeorica)
									|| (prestamo.getPreMoraMov()!= null && prestamo.getPreMoraMov()>=1)){
							CtaNotNotas notaAbogado = new CtaNotNotas();
							notaAbogado.setCasCuenta(cas.getCasCuenta());
							notaAbogado.setCtaTntTipoNota(null);
							notaAbogado.setNotCampo("Pagar a abogado");
							notaAbogado.setNotFecha(new Date());
							Double pago = prestamo.getPreSaldoActualT()*parPorcAbogado.getParValorNumber()/100;
							notaAbogado.setNotNota(Format.formatDinero(pago));
							CtaNotNotasDAO notDao = new CtaNotNotasDAO(getSessionHibernate(request));
							notaAbogado.setNotId(notDao.nextId());
							notDao.save(notaAbogado);
						}
					}
				}
			}
		}
		
		//Dias desde el ultimo movimiento
		Date fechaActual = fechaTeorica;
		
		int diasUM = 0;
		if(mxpAnt == null || mxpAnt.getMxpId() == null){
			Date today = new Date();
			if(ElapsedTime.fechaMenor(fechaSinMov, today)){
				diasUM = et.getDays(ElapsedTime.dTGC(noTime(fechaActual)), ElapsedTime.dTGC(noTime(fechaSinMov)));
			}else{
				diasUM = 0;
			}
		}else{
			diasUM = et.getDays(ElapsedTime.dTGC(noTime(fechaActual)), ElapsedTime.dTGC(noTime(mxpAnt.getMxpFecha())));
		}		
		//Dias desde la ultima fecha de pago
		Date fechaPago = cas.getCasFechaApertura();
		Date fechaUltimoPago = new Date();
		try {
			fechaUltimoPago = ElapsedTime.obtenUltimaFechaPago(noTime(fechaPago));
		} catch (ParseException e) {
			log.error("Fallo la generacion del ultimo pago en el parsing");
			e.printStackTrace();
		}
		int diasUP = et.getDays(ElapsedTime.dTGC(noTime(fechaActual)), ElapsedTime.dTGC(noTime(fechaUltimoPago)));		
		
		//Numero pagos no efectuados
		int numPagosNoEfectuados = 0;
		if(mxpAnt == null || mxpAnt.getMxpId() == null){
			Date today = fechaTeorica;
			if(ElapsedTime.fechaMenor(fechaSinMov, today)){
				numPagosNoEfectuados = et.getMonths(ElapsedTime.dTGC(noTime(fechaUltimoPago)), ElapsedTime.dTGC(noTime(fechaSinMov)));
			}else{
				numPagosNoEfectuados = 0;
			}
		}else{
			numPagosNoEfectuados = et.getMonths(ElapsedTime.dTGC(noTime(fechaUltimoPago)), ElapsedTime.dTGC(noTime(mxpAnt.getMxpFecha())));
		}
		
		/*
		 * Se utiliza formato para obtener el dia, mes, anho para cada una de las fechas de la comparacion
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		int diaUp = Integer.valueOf(sdf.format(fechaUltimoPago).substring(0,2));
		int mesUp = Integer.valueOf(sdf.format(fechaUltimoPago).substring(3,5));
		int anhoUp = Integer.valueOf(sdf.format(fechaUltimoPago).substring(6,10));
		int diaUm = 0;
		int mesUm = 0;
		int anhoUm = 0;
		if(mxpAnt == null || mxpAnt.getMxpId() == null){
			Date today = fechaTeorica;
			if(ElapsedTime.fechaMenor(fechaSinMov, today)){
				diaUm = Integer.valueOf(sdf.format(fechaSinMov).substring(0,2));
				mesUm = Integer.valueOf(sdf.format(fechaSinMov).substring(3,5));
				anhoUm = Integer.valueOf(sdf.format(fechaSinMov).substring(6,10));
			}else{
				diaUm = Integer.valueOf(sdf.format(today).substring(0,2));
				mesUm = Integer.valueOf(sdf.format(today).substring(3,5));
				anhoUm = Integer.valueOf(sdf.format(today).substring(6,10));
			}
		}else{
			diaUm = Integer.valueOf(sdf.format(mxpAnt.getMxpFecha()).substring(0,2));
			mesUm = Integer.valueOf(sdf.format(mxpAnt.getMxpFecha()).substring(3,5));
			anhoUm = Integer.valueOf(sdf.format(mxpAnt.getMxpFecha()).substring(6,10));
		}
		if(anhoUm == anhoUp){
			if(mesUp == mesUm){
				numPagosNoEfectuados = 0;
			}
			if(diaUm <= diaUp){
				numPagosNoEfectuados = numPagosNoEfectuados -1;
			}
		}else{
			if(mesUp == 12 && mesUm == 1){
				if(diaUm <= diaUp){
					numPagosNoEfectuados = numPagosNoEfectuados - 1;
				}
			}
		}
		
		/*
		 * El interes pendiente acumulado se obtiene de multiplicar el saldo actual del prestamo por 
		 * por el porcentaje de interes
		 */
		Double interesPendienteAcum = 0.0;
		Double moraPendAcum = 0.0;
		if(mxpAnt == null || mxpAnt.getMxpId() == null){
			interesPendienteAcum = prestamo.getPreSaldoActualT() * (iYm.getPorcInteres()/100);
			moraPendAcum = prestamo.getPreSaldoActualT() * (iYm.getMora()/100);
		}else{
			Double d = mxpAnt.getMxpSaldo();
			if(d==null) d=0.00;
			interesPendienteAcum = (d/*prestamo.getPreMontoSolicitado() - mxpAnt.getMxpSaldoActual()*/) * (iYm.getPorcInteres()/100);
			moraPendAcum = (d/*prestamo.getPreMontoSolicitado() - mxpAnt.getMxpSaldoActual()*/) * (iYm.getMora()/100);
		}
		
		/*
		 * Si el numero de pagos no efectuados es mayor a cero, se obtiene la mora.
		 */
		
		/*
		if(mxpAnt != null && mxpAnt.getMxpId()!=null){
			if(numPagosNoEfectuados > 0){
				iYm.setPendiente(iYm.getPendiente() + (interesPendienteAcum * numPagosNoEfectuados));
				//Esta mora no se utiliza
				/*
				Double moraAcum = 0.0;
				if(prestamo.getCtaTinTasaInteres() == null || prestamo.getCtaTinTasaInteres().getTinTasa() == null){
					moraAcum = 0.0;
				}else{
					moraAcum = (interesPendienteAcum * prestamo.getCtaTinTasaInteres().getTinTasa()) * numPagosNoEfectuados;
				}
				iYm.setMora(iYm.getMora() + moraAcum);*/
		/*	}
		}*/
		/*
		 * El acumulado actual es entonces el pendiente acumulado dividido entre 30 dias
		 * y luego multiplicado por la cantidad de dias pasados desde el ultimo movimiento.
		 */
		//Double interAcum = diasUM * (interesPendienteAcum/30);
		Double interAcum = diasUM * (interesPendienteAcum/30);
		Double moraAcum = diasUM * (moraPendAcum/30);
		if(mxpAnt == null || mxpAnt.getMxpId() == null){
			//ElapsedTime et = new ElapsedTime();
			Integer diasFechaSinMov = et.getDays(ElapsedTime.dTGC(new Date()), ElapsedTime.dTGC(fechaSinMov));
			interAcum = diasFechaSinMov * (interesPendienteAcum/30);
			moraAcum = diasFechaSinMov * (moraPendAcum/30);
			if(diasUP < diasFechaSinMov){
				Double pendAcum = diasUP * (interesPendienteAcum/30);
				iYm.setPendiente(iYm.getPendiente() + pendAcum);
				interAcum = interAcum - pendAcum;
			}
		}else{
			if(diasUP < diasUM){
				Double pendAcum = diasUP * (interesPendienteAcum/30);
				iYm.setPendiente(iYm.getPendiente() + pendAcum);
				interAcum = interAcum - pendAcum;
			}
			
			Double por =prestamo.getCtaTprTipoPrestamo().getCtaTinTasaInteres().getTinTasa()/100;
			if(prestamo.getPreCredito()!=null)por=0.00; 

			
			//aqui se calculan los interes, realmente:
			iYm.setPendiente(prestamo.getPrePendMov());
			Double saldoAnt = mxpAnt.getMxpSaldo();
			if(saldoAnt==null) saldoAnt=0.00;
			Double anioCom = parametrosDAO.findById("ANHO_COMERCIAL").getParValorNumber();
			
			
			Double acumula2 =  ((saldoAnt*por)*diasUM)/anioCom;
			iYm.setAcumulado(acumula2);
			interAcum = 0.00;
			
		}
		/*if(diasUM > 30){
			interAcum = (et.getDays(ElapsedTime.dTGC(new Date()), ElapsedTime.dTGC(fechaUltimoPago))) * (interesPendienteAcum/30);
		}*/
		iYm.setAcumulado(iYm.getAcumulado() + interAcum);
		iYm.setPendiente(iYm.getPendiente() + moraAcum);
		return iYm;
	}

	/**
	 * 
	 * @param fecha
	 * @return fecha sin el tiempo (horas, min, segundos).
	 * 
	 */
	public Date noTime(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date nuevaFecha = new Date();
		try {
			nuevaFecha = sdf.parse(sdf.format(fecha));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return nuevaFecha;
	}
	
	public Session getSessionHibernate(HttpServletRequest request) {
		return (Session) request.getAttribute(FiltroOpenSession.KEY_SESSION);
	}
}
