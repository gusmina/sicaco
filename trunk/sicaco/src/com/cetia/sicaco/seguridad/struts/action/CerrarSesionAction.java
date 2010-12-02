/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.seguridad.struts.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cetia.sicaco.hibernate.CtaCahCuentaAhorro;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorroDAO;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaMxaMovimientoAhorro;
import com.cetia.sicaco.hibernate.CtaMxaMovimientoAhorroDAO;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccionDAO;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociado;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.SecHseHistorialSesion;
import com.cetia.sicaco.hibernate.SecHseHistorialSesionDAO;
import com.cetia.sicaco.hibernate.SecIseInicioSesion;
import com.cetia.sicaco.hibernate.SecIseInicioSesionDAO;
import com.cetia.sicaco.struts.BasicAction;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.UsuarioConectado;
import com.mad.utilidades.filtros.FiltroOpenSession;
import com.mad.utilidades.seguridad.Hasher;

/** 
 * MyEclipse Struts
 * Creation date: 02-13-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="inicioSesion" path="/autenticacion.do?accion=cargar"
 */
public class CerrarSesionAction extends BasicAction {
	
	private Log log = LogFactory.getLog(CerrarSesionAction.class);
	
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		
//	System.out.println("-- || Correccion de Intereses de Ahorros V 1.0 || ---");

//	Corregir los datos a descontar para los empleados fiadores al momento de 
//	generar las planillas 
		
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		CtaCahCuentaAhorroDAO cahDAO = new CtaCahCuentaAhorroDAO();
//		CtaCahCuentaAhorro cah = new CtaCahCuentaAhorro();
//		CtaMxaMovimientoAhorroDAO mxaDAO = new CtaMxaMovimientoAhorroDAO();
//		CtaMxaMovimientoAhorro mxaAnterior = new CtaMxaMovimientoAhorro();
//		CtaCasCuentaAsociadoDAO casDAO = new CtaCasCuentaAsociadoDAO();
//		CtaCasCuentaAsociado cas = new CtaCasCuentaAsociado();
//		
//		ArrayList<?> listaAhorrosACapitalizar = (ArrayList<?>) cahDAO.findAllCuAhorroCapitalizacion2();
//		
//
//		Iterator itera = listaAhorrosACapitalizar.iterator();
//		
//		while(itera.hasNext()){
//			
//			cah = (CtaCahCuentaAhorro) itera.next();
//			cas = casDAO.findbyCahId(cah.getCahId());
//			Date fecha = (Date) mxaDAO.getFechaPrimerMov(cah.getCahId());
//			if(cas.getCasPrincipal().equalsIgnoreCase("S")){
//				try {
//					fecha = sdf.parse("2010-02-28");
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//				}
//				}
//			List l = mxaDAO.findMovimientosSiguientes(cah.getCahId(), sdf.format(fecha));
//			mxaAnterior = mxaDAO.findPrimerMovimiento(cah.getCahId());
//			CtrParParametrosDAO parametroDAO = new CtrParParametrosDAO();
//			CtrParParametros parametroAnio = parametroDAO.findById("ANHO_CALENDARIO");
//			CtaTahTipoAhorroDAO tAhorroDAO = new CtaTahTipoAhorroDAO();
//			Transaction tx = mxaDAO.getSession().beginTransaction();
//			Double nuevoInteres = 0.0;
//			Iterator i = l.iterator();
//			CtaMxaMovimientoAhorro mxaSiguiente = new CtaMxaMovimientoAhorro();
//			int numDiasTranscurridos = 0;
//			double saldoActual = mxaAnterior.getMxaSaldo();
//			System.out.println("Numero de movimientos siguientes: "+ l.size());
//			double interesCap = 0.00;
//			while (i.hasNext()) {
//				mxaSiguiente = (CtaMxaMovimientoAhorro)i.next();
//				saldoActual = mxaAnterior.getMxaSaldo();
//				numDiasTranscurridos = diferenciaEnDias(mxaAnterior.getMxaFecha(), mxaSiguiente.getMxaFecha());
//				CtaTahTipoAhorro tipoAhorro = tAhorroDAO.findById(cah.getCtaTahTipoAhorro().getTahId());
//				Double interes = tipoAhorro.getCtaTinTasaInteres().getTinTasa();
//				interes = interes/100;
//				//interesGenerado = 
//				nuevoInteres = (saldoActual*interes)*numDiasTranscurridos/parametroAnio.getParValorNumber();
//				System.out.println("Interes movimiento : "+nuevoInteres);
//				if(mxaAnterior.getMxaInteresTran() ==null)mxaAnterior.setMxaInteresTran(0.00);
//				//nuevoInteres+=mxaAnterior.getMxaInteresTran();
//				mxaSiguiente.setMxaInteresTran(nuevoInteres);
//				interesCap+=nuevoInteres;
//				mxaDAO.merge(mxaSiguiente);
//				tx.commit();
//				mxaAnterior=mxaSiguiente;
//				mxaDAO.getSession().flush();
//				mxaDAO.getSession().clear();
//				mxaAnterior=mxaSiguiente;
//			}
//			
//			Transaction tx2 = cahDAO.getSession().beginTransaction();
//			cah.setCahInteresAcumulado(interesCap);
//			cahDAO.merge(cah);
//			tx2.commit();
//			cahDAO.getSession().flush();
//			cahDAO.getSession().clear();
//			
//			System.out.println("Inter�s final: "+interesCap);
//		}
//		
/*
		String asc  = "ASC10001872";
		String password = Hasher.getHash("BARRIOS");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
		
//		System.out.println("-----------||||||||||||||||||||||||--------------");
		
		asc  = "ASC10001867";
		password = Hasher.getHash("RUIZ");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
		
//		System.out.println("-------------------------");
		
		asc  = "ASC10001865";
		password = Hasher.getHash("GALDAMEZ");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
//		System.out.println("-------------------------");
		
		asc  = "ASC10001866";
		password = Hasher.getHash("COLINDRES");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
//		System.out.println("-------------------------");

		asc  = "ASC10001870";
		password = Hasher.getHash("PAYES");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
//		System.out.println("-------------------------");
		
	
		asc  = "ASC10001868";
		password = Hasher.getHash("ESCOBAR");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
//		System.out.println("-------------------------");
		
		asc  = "ASC10001869";
		password = Hasher.getHash("FLORES");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
//		System.out.println("-------------------------");
		
		asc  = "ASC10001871";
		password = Hasher.getHash("GARCIA");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
//		System.out.println("-------------------------");
		
		asc  = "ASC10000200";
		password = Hasher.getHash("HIDALGO");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
//		System.out.println("-------------------------");
		
		
		asc  = "ASC10001515";
		password = Hasher.getHash("ACOSTA");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
//		System.out.println("-------------------------");
		
		asc  = "ASC10001286";
		password = Hasher.getHash("ARCHILLA");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
//		System.out.println("-------------------------");
		
		asc  = "ASC10000065";
		password = Hasher.getHash("CABEZAS");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
//		System.out.println("-------------------------");
		
		asc  = "ASC10000466";
		password = Hasher.getHash("SIFONTES");
//		System.out.println("usuario:  "+asc+ "\npassword: "+password);
		System.out.println("UPDATE sec_ise_inicio_sesion " +
				"SET ISE_CONTRASENIA = '"+password+"' " +
						"WHERE ISE_NOMBRE_USUARIO = '"+asc+"';");
//		System.out.println("-------------------------");
		*/
		updateHistorial(request);
		request.getSession().removeAttribute(Constantes.USUARIO_KEY);
		request.getSession().removeAttribute(Constantes.FECHA_KEY); 
		request.getSession().removeAttribute(Constantes.MENU_KEY);
		request.getSession().invalidate();
		return mapping.findForward("inicioSesion");
	}
	
	public static int diferenciaEnDias(Date fechainicial, Date fechafinal) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String fi = df.format(fechainicial);
		try {
			fechainicial = df.parse(fi);
		} catch (ParseException ex) {
		}
		String fechafinalstring = df.format(fechafinal);
		try {
			fechafinal = df.parse(fechafinalstring);
		} catch (ParseException ex) {

		}
		long fechainicialms = fechainicial.getTime();
		long fechafinalms = fechafinal.getTime();
		long diferencia = fechafinalms - fechainicialms;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}
	
	/*			
public static Double calculoInteresTransaccion(String cahId, Date fecha){
		CtaCasCuentaAsociadoDAO ctaCuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO();
		CtaCasCuentaAsociado cuentaAsociadoC = ctaCuentaAsociadoDAO.findbyCahId(cahId);
		CtaCahCuentaAhorro ctaCahCuentaAhorro = cuentaAsociadoC.getCtaCahCuentaAhorro();
		CtaTinTasaInteresDAO tasaInteresDAO = new CtaTinTasaInteresDAO();
		CtaTahTipoAhorroDAO tAhorroDAO = new CtaTahTipoAhorroDAO();
		Date fechaApertura = cuentaAsociadoC.getCasFechaApertura();
		ElapsedTime elapsed = new ElapsedTime();
		
		GregorianCalendar greCalendarI = new GregorianCalendar();//inicial
		GregorianCalendar greCalendarF = new GregorianCalendar();//final
		GregorianCalendar greCalendarM = new GregorianCalendar();//despues de un mes
		greCalendarF = ElapsedTime.dTGC(fecha);
		greCalendarI=ElapsedTime.dTGC(fechaApertura);
		greCalendarM=ElapsedTime.dTGC(fechaApertura);
		greCalendarM.add(Calendar.MONTH, 1);
		
		greCalendarF.set(Calendar.MILLISECOND, 0);
		greCalendarF.set(Calendar.SECOND, 0);
		greCalendarF.set(Calendar.MINUTE, 0);
		greCalendarF.set(Calendar.HOUR_OF_DAY, 0);
		
		greCalendarI.set(Calendar.MILLISECOND, 0);
		greCalendarI.set(Calendar.SECOND, 0);
		greCalendarI.set(Calendar.MINUTE, 0);
		greCalendarI.set(Calendar.HOUR_OF_DAY, 0);
		
		
		greCalendarM.set(Calendar.MILLISECOND, 0);
		greCalendarM.set(Calendar.SECOND, 0);
		greCalendarM.set(Calendar.MINUTE, 0);
		greCalendarM.set(Calendar.HOUR_OF_DAY, 0);
		
		int numDiasCuentaActiva = elapsed.getDays(greCalendarI,greCalendarF);
		//int numDiasCuentaActiva = diferenciaEnDias(fechainicial, fecha);
		//diferenciaEnDias
		
		
		Double interesGenerado = new Double(0);
		int numDiasMes = elapsed.getDays(greCalendarI,greCalendarM);
		if(numDiasCuentaActiva > numDiasMes){
			CtaMxaMovimientoAhorroDAO movimientoAhDAO = new CtaMxaMovimientoAhorroDAO();
			Date mxaFechaU = (movimientoAhDAO.findFechaUltimoMovimientoAhorro(ctaCahCuentaAhorro.getCahId())==null?
								new Date():movimientoAhDAO.findFechaUltimoMovimientoAhorro(ctaCahCuentaAhorro.getCahId()));
			if(mxaFechaU != null){
				greCalendarI = ElapsedTime.dTGC(mxaFechaU);
				greCalendarI.set(Calendar.MILLISECOND, 0);
				greCalendarI.set(Calendar.SECOND, 0);
				greCalendarI.set(Calendar.MINUTE, 0);
				greCalendarI.set(Calendar.HOUR_OF_DAY, 0);
				
				CtrParParametrosDAO parametroDAO = new CtrParParametrosDAO();
				CtrParParametros parametroAnio = parametroDAO.findById("ANHO_CALENDARIO");
				int numDiasTranscurridos = diferenciaEnDias(mxaFechaU,fecha);
				
				if(numDiasTranscurridos>0){
					CtaTahTipoAhorro tipoAhorro = tAhorroDAO.findById(ctaCahCuentaAhorro.getCtaTahTipoAhorro().getTahId());
					Double interes = tipoAhorro.getCtaTinTasaInteres().getTinTasa();
					interes = interes/100;
					Double saldoActual = ctaCahCuentaAhorro.getCahSaldoActual();
					interesGenerado = (saldoActual*interes)*numDiasTranscurridos/parametroAnio.getParValorNumber();
				}
			}
		}
		return interesGenerado;
	}
				
	*/
	public void  updateHistorial(HttpServletRequest request){
		    Integer hseid = (Integer)request.getSession().getAttribute(Constantes.HSEID);
			SecHseHistorialSesionDAO secHseHistorialSesionDAO = null;
			SecHseHistorialSesion modelHistorialSesion;
			secHseHistorialSesionDAO = new SecHseHistorialSesionDAO(getSessionHibernate(request));
			Transaction tx = secHseHistorialSesionDAO.getSession().beginTransaction();
			try {
				modelHistorialSesion = secHseHistorialSesionDAO.findById(hseid);
				modelHistorialSesion.setHseFechaSalida(new java.util.Date());
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				log.error("No se pudo guardar la finalizacion de sesion.",e);
			}finally{
				secHseHistorialSesionDAO.getSession().flush();
				secHseHistorialSesionDAO.getSession().clear();
				
			}
	}
	
	
	public Session getSessionHibernate(HttpServletRequest request) {
		return (Session) request.getAttribute(FiltroOpenSession.KEY_SESSION);
	}
	
	public void updateInicioSesion(HttpServletRequest request){
			UsuarioConectado usuarioConectado = (UsuarioConectado)request.getSession().getAttribute(Constantes.USUARIO_KEY);
			SecIseInicioSesionDAO secInicioSesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
			SecIseInicioSesion modelSesion = null;
			Transaction tx = secInicioSesionDAO.getSession().beginTransaction();
				modelSesion = secInicioSesionDAO.findById(usuarioConectado.getNombreUsuario());
				modelSesion.setIseUltimaIp(request.getRemoteAddr());
				modelSesion.setIseUltimaSesion(new java.util.Date());
				modelSesion.setIseVecesUtilizado(1 + modelSesion.getIseVecesUtilizado());
			tx.commit();
			secInicioSesionDAO.getSession().flush();
			secInicioSesionDAO.getSession().clear();
			
	}
	
	
}