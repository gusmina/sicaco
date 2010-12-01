/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.contabilidad.struts.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Transaction;


import com.cetia.sicaco.contabilidad.struts.form.CierreMensualForm;
import com.cetia.sicaco.hibernate.ConCueCuenta;
import com.cetia.sicaco.hibernate.ConCueCuentaDAO;
import com.cetia.sicaco.hibernate.ConPcoPartidaContableDAO;
import com.cetia.sicaco.hibernate.ConSacSaldosAnterioresCuenta;
import com.cetia.sicaco.hibernate.ConSacSaldosAnterioresCuentaDAO;
import com.cetia.sicaco.hibernate.InvProProveedor;
import com.cetia.sicaco.hibernate.InvProProveedorDAO;
import com.cetia.sicaco.hibernate.SecIseInicioSesion;
import com.cetia.sicaco.hibernate.SecIseInicioSesionDAO;
import com.cetia.sicaco.orden.struts.form.OrdenCompraForm;
import com.cetia.sicaco.struts.BasicReportExporter;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.mad.utilidades.PartidaDesc;
import com.mad.utilidades.SaldoAnterior;

/** 
 * MyEclipse Struts
 * Creation date: 12-03-2008
 * 
 * XDoclet definition:
 * @param <E>
 * @struts.action parameter="accion"
 */
public class CierreMensualAction<E> extends DMLAction {
	
	public ActionForward dml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		SecIseInicioSesionDAO sesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
		CierreMensualForm cierreForm= (CierreMensualForm) form; 
		String usuId = cierreForm.getUsuarioConectado().getNombreUsuario();
		SecIseInicioSesion sesion = sesionDAO.findById(usuId);
		if(sesion.getSecRolRoles().getRolNombre().equals("ADMINISTRADOR")){
			request.setAttribute("x", 1);
		}else{
			request.removeAttribute("x");
		}
		
		request.setAttribute(Constantes.ACCION_KEY, "/cierreMensual");
		return mapping.findForward("dml");
	}
	
	public ActionForward realizarCierreMensual(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = new ActionErrors();
		ConPcoPartidaContableDAO partidaContableDAO = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		CierreMensualForm cierreMensualForm = (CierreMensualForm) form;
		Date fechaCierre = cierreMensualForm.getFechaCierreD();
		Transaction tx = partidaContableDAO.getSession().beginTransaction();
		try{
			SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
			SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");
			Integer mes = new Integer(sdfMes.format(fechaCierre));
			Integer anio = new Integer(sdfAnio.format(fechaCierre));
			int error = validarCierre(mes, anio, request);
			if(error != 0 ){
				if(error==1){
					errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.cierre.mesAnterior"));
				}
				if(error==2){
					errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.cierre.descuadres"));
				}
				saveMessages(request, errors);
				return dml(mapping, form, request, response);
			}
			partidaContableDAO.updateParaCierre(cierreMensualForm.getUsuarioConectado().getNombreUsuario(), fechaCierre);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			actualizarSaldosAnteriores(sdf.parse(cierreMensualForm.getFechaCierre()),request);
			tx.commit();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.cierre.exito"));
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.cierre.fallo"));
		}finally{
			partidaContableDAO.getSession().flush();
			partidaContableDAO.getSession().clear();
			
		}
		saveMessages(request, errors);
		return dml(mapping, form, request, response);
	}
	
	/**@return el estado del mes a cerrar:  
	 * 		0 ok , 1 mes anterior abierto, 2 existen partidas descuadradas
	 * */
	public int validarCierre(int mes, int anio, HttpServletRequest request){
		ConPcoPartidaContableDAO pcoDao = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		if(mes<=3&& anio==2010)return 0;
		List<PartidaDesc> descuadres = pcoDao.obtenerDescuadres(mes, anio);
		if(descuadres.size()>0)
			return 2;// existen partidas descuadradas
		
		if(mes == 1){
			mes = 12;
			anio = anio-1;
		}else{
			mes=mes-1;
		}
		
		if(pcoDao.obtenerEstado(mes, anio).equals("F")) //mes anterior cerrado (bueno)
			return 0;
		else			//mes anterior abierto (malo)
			return 1;
	}
	
	public int validarApertura(int mes, int anio, HttpServletRequest request){
		ConPcoPartidaContableDAO pcoDao = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		if(mes == 12){
			mes = 1;
			anio = anio+1;
		}else{
			mes=mes+1;
		}
		
		if(pcoDao.obtenerEstado(mes, anio).equals("P"))
			return 1;
		if(pcoDao.obtenerEstado(mes, anio).equals("X"))
			return 1;
		else
			return 0;
	}
	
	public ActionForward abrirMes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = new ActionErrors();
		ConPcoPartidaContableDAO partidaContableDAO = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		ConSacSaldosAnterioresCuentaDAO sacDao = new ConSacSaldosAnterioresCuentaDAO(getSessionHibernate(request));
		CierreMensualForm cierreMensualForm = (CierreMensualForm) form;
		Date fechaCierre = cierreMensualForm.getFechaCierreD();
		Transaction tx = partidaContableDAO.getSession().beginTransaction();
		try{
			SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
			SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");
			Integer mes = new Integer(sdfMes.format(fechaCierre));
			Integer anio = new Integer(sdfAnio.format(fechaCierre));
			int valida = validarApertura(mes, anio, request);
			if(valida !=1){//hay un error
				if (valida==0)// el mes siguiente no esta abierto
					errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.cierre.mesSiguiente"));
				else //no existen partidas para ese mes
					//no importa si no hay partidas el mes siguiente...
					//errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.cierre.noPar"));
				saveMessages(request, errors);
				return dml(mapping, form, request, response);
			}
			
			partidaContableDAO.updateParaApertura(cierreMensualForm.getUsuarioConectado().getNombreUsuario(), fechaCierre);
//			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			//actualizarSaldosAnteriores(sdf.parse(cierreMensualForm.getFechaCierre()),request);
			List<ConSacSaldosAnterioresCuenta> l= new ArrayList<ConSacSaldosAnterioresCuenta>();
			l = sacDao.getSacs(anio, mes);
			Iterator<ConSacSaldosAnterioresCuenta> i = l.iterator();
			while(i.hasNext()){
				sacDao.delete(i.next());
				tx.commit();
			}
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.apertura.exito"));;
			
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.cierre.fallo"));
		}finally{
			partidaContableDAO.getSession().flush();
			partidaContableDAO.getSession().clear();
		}
		saveMessages(request, errors);
		return dml(mapping, form, request, response);
	}
	
	
	public void mensajes(String msg, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
        saveMessages(request, errors);
	}
	
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.cierreMensual.dml","dml");
		map.put("cmd.cierreMensual.generarCierreMensual","realizarCierreMensual");
		map.put("cmd.cierreMensual.abrirMes","abrirMes");
		map.put("cmd.cierreMensual.cargarDescuadres","cargarDescuadres");
		return map;
	}
	
	
	 public ActionForward cargarDescuadres(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		 	CierreMensualForm cf = (CierreMensualForm) form;
		 	String fecha = cf.getFechaCierre();
		 	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		 	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
		 	SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
		 	
		try {
			Date fechaD = sdf1.parse(fecha);
			String mes = sdf3.format(fechaD);
			String anio = sdf2.format(fechaD);
			String listaResponse=construirListaResponse(new Integer(mes), new Integer(anio), request);
			response.getWriter().write(listaResponse);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (RuntimeException e) {
			log.error("Error runtime", e);
		} catch (IOException e) {
			log.error(e);
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

		return null;
	}
	 
	private String construirListaResponse(int mes, int anio, HttpServletRequest request){
		
		
		ConPcoPartidaContableDAO pcoDao = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		List<PartidaDesc> descuadres = pcoDao.obtenerDescuadres(mes, anio);
		if(descuadres.size()< 1) return "<label>No se han encontrado partidas descuadradas</label>";
		Iterator i = (Iterator) descuadres.iterator();
		PartidaDesc desc = new PartidaDesc();
		String response="<label> Las siguientes partidas se encuentran descuadradas:</label> <br><br> " +
		"<table class= \"stats\" > " +
		"<tr>" +
		"<th>Fecha</th>" +
		"<th>Comprobante</th>" +
		"<th>Debe</th>" +
		"<th>Haber</th>" +
		"<th>Difencia</th>"+
		"</tr>" ;
		DecimalFormat df = new DecimalFormat("$ ###,###,###.00");
		while(i.hasNext()){
			
			response+="<tr>";
			desc= (PartidaDesc) i.next();
			response+=
				"<td>"+desc.getFechaIn()+"</td>"+
				"<td>"+desc.getComprobante()+
				"</td>"+"<td>"+df.format(desc.getDebe())+"</td>" +
				"<td>"+df.format(desc.getHaber())+"</td>"+
				"<td>"+df.format(Math.abs(desc.getHaber()-desc.getDebe()))+"</td>";
			response+="</tr>";
		}
		response+="</table>";
		return response;
	}
	
	
	
	public void actualizarSaldosAnteriores(Date fecha,HttpServletRequest request){
		 
		System.out.println("...Actualizando saldos anteriores");
		
		
		ConSacSaldosAnterioresCuentaDAO sacDao = new ConSacSaldosAnterioresCuentaDAO(getSessionHibernate(request));
		SimpleDateFormat sdM = new SimpleDateFormat("MM");
		SimpleDateFormat sdA = new SimpleDateFormat("yyyy");
		
		String mes = sdM.format(fecha);
		String anio = sdA.format(fecha);
		
		List<SaldoAnterior> l = new ArrayList<SaldoAnterior>(); 
		l = sacDao.saldosEnElMes(new Integer(mes), new Integer(anio));
		Iterator<SaldoAnterior> i = l.iterator();
		SaldoAnterior saldo = new SaldoAnterior();
		while(i.hasNext()){
			saldo = i.next();
			actualizar(saldo, request, new Integer(mes), new Integer(anio));
		}
		 
	}
	
	@SuppressWarnings("unchecked")
	private int actualizar(SaldoAnterior saldoAnterior,HttpServletRequest request, int mes, int anio){
		/**
		 * 
		 **/
		ConSacSaldosAnterioresCuentaDAO sacDao = new ConSacSaldosAnterioresCuentaDAO(getSessionHibernate(request));
		Transaction tx = sacDao.getSession().beginTransaction();
		double saldoAnt = 0.00;
		double saldoActual=0.00;
		
		ConCueCuentaDAO cueDao = new ConCueCuentaDAO(getSessionHibernate(request));
		ConCueCuenta cuenta = (ConCueCuenta) cueDao.findByCueCodigoCuenta(saldoAnterior.getCuenta()).get(0);
		if(cuenta.getConCueCuenta()!= null){
			//cuenta hija o sub-cuenta
			saldoAnt = sacDao.findUltimoSaldo(cuenta.getCueId().toString());
			
			// CARGOS
			
			if(saldoAnterior.getMultiplicador().equalsIgnoreCase("c")){ 
				 saldoActual = saldoAnt+saldoAnterior.getCargos()-saldoAnterior.getAbonos();
			
			//ABONOS
				 
			 }else{ 
				 saldoActual = saldoAnt-saldoAnterior.getCargos()+saldoAnterior.getAbonos();
			 }
				ConSacSaldosAnterioresCuenta sac = new ConSacSaldosAnterioresCuenta();
				 
				 if (sacDao.hayCuentas(anio, mes, cuenta.getCueId()).size()> 0){
					 sac=(ConSacSaldosAnterioresCuenta) sacDao.hayCuentas(anio, mes, cuenta.getCueId()).get(0);
					 sac.setSacTotalDebe(sac.getSacTotalDebe()+saldoAnterior.getCargos());
					 sac.setSacTotalHaber(sac.getSacTotalHaber()+saldoAnterior.getAbonos());
					 sac.setSacSaldoALaFecha(saldoActual);
					 sac.setSacFecha(BasicReportExporter.getUltimoDiaDeMes(mes, anio));
					 sac.setConCueCuenta(cuenta);
					sacDao.merge(sac); 
				 }
				 else{
					 sac.setSacTotalDebe(saldoAnterior.getCargos());
					 sac.setSacTotalHaber(saldoAnterior.getAbonos());
					 sac.setSacSaldoALaFecha(saldoActual);
					 sac.setSacFecha(BasicReportExporter.getUltimoDiaDeMes(mes, anio));
					 sac.setConCueCuenta(cuenta);
					 sacDao.save(sac);
				 }
					 
				 tx.commit();
				 sacDao.getSession().flush();
				 sacDao.getSession().clear();
				 saldoAnterior.setCuenta(cuenta.getConCueCuenta().getCueCodigoCuenta());
//				 System.out.println("actualizado: "+cuenta.getCueCodigoCuenta());
				 return 1*actualizar(saldoAnterior, request,mes,anio);
		}else{
			//Cuenta padre
			saldoAnt = sacDao.findUltimoSaldo(cuenta.getCueId().toString());
//			System.out.println("cuentaPadre: "+cuenta.getCueCodigoCuenta());
			if(saldoAnterior.getMultiplicador().equalsIgnoreCase("c")){
				 saldoActual = saldoAnt+saldoAnterior.getCargos()-saldoAnterior.getAbonos(); 
			 }else{
				 saldoActual = saldoAnt-saldoAnterior.getCargos()+saldoAnterior.getAbonos();
			 }
				 ConSacSaldosAnterioresCuenta sac = new ConSacSaldosAnterioresCuenta();
				 if (sacDao.hayCuentas(anio, mes, cuenta.getCueId()).size()> 0){
					 sac=(ConSacSaldosAnterioresCuenta) sacDao.hayCuentas(anio, mes, cuenta.getCueId()).get(0);
				 }

				 sac.setSacSaldoALaFecha(saldoActual);
				 sac.setSacFecha(BasicReportExporter.getUltimoDiaDeMes(mes, anio));
				 sac.setConCueCuenta(cuenta);
				 if (sacDao.hayCuentas(anio, mes, cuenta.getCueId()).size()> 0){
					 sac=(ConSacSaldosAnterioresCuenta) sacDao.hayCuentas(anio, mes, cuenta.getCueId()).get(0);
				 	 sac.setSacTotalDebe(sac.getSacTotalDebe()+saldoAnterior.getCargos());
				 	 sac.setSacTotalHaber(sac.getSacTotalHaber()+saldoAnterior.getAbonos());
				 	 sac.setSacSaldoALaFecha(saldoActual);
				 	 sac.setSacFecha(BasicReportExporter.getUltimoDiaDeMes(mes, anio));
				 	 sac.setConCueCuenta(cuenta);
				 	 sacDao.merge(sac);
				 }
				 else{
					 sac.setSacTotalDebe(saldoAnterior.getCargos());
					 sac.setSacTotalHaber(saldoAnterior.getAbonos());
					 sac.setSacSaldoALaFecha(saldoActual);
					 sac.setSacFecha(BasicReportExporter.getUltimoDiaDeMes(mes, anio));
					 sac.setConCueCuenta(cuenta);
					 sacDao.save(sac);
				 }
				 tx.commit();
				 sacDao.getSession().flush();
				 sacDao.getSession().clear();
				 return 1;
		}
	}
		
}
	
	