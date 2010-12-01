package com.cetia.sicaco.struts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

import com.cetia.sicaco.hibernate.CtaResCuentaResumenes;
import com.cetia.sicaco.hibernate.CtaResCuentaResumenesDAO;
import com.cetia.sicaco.hibernate.HibernateSessionFactory;
import com.cetia.sicaco.reporte.struts.action.ReportesAction;
import com.cetia.sicaco.reporte.struts.form.ReportesForm;
import com.mad.utilidades.ElapsedTime;
import com.mad.utilidades.ExportWebReport;
import com.mad.utilidades.LecturaParametrosJasper;
import com.mad.utilidades.ReportFile;
import com.mad.utilidades.Resumen;
import com.mad.utilidades.filtros.FiltroOpenSession;

/**
 * 
 * @author Mauricio Jovel
 * Utilizada para el manejo de la jsp de reportes
 *
 */
public class BasicReportExporter extends Action {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Declaracion de Variables
		ReportesForm rf = (ReportesForm) form;
		
		//Conexion a BD
		String jdbcDriver = "com.mysql.jdbc.Driver";
		Class.forName(jdbcDriver);
		String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
		String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
		String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
		
		Connection con = DriverManager.getConnection(url, user, pass);
		
		//Reporte
		String modulo = request.getParameter("m009o8765d");
		String reporte = request.getParameter("p76e3123r");

		ServletContext servletContext = getServlet().getServletContext();
		String pathJrxml = servletContext.getRealPath("/listaReportes/"
				+ modulo + "/" + reporte + ".jrxml");
		String pathJasper = servletContext.getRealPath("/listaReportes/"
				+ modulo + "/" + reporte + ".jasper");
		
		ExportWebReport export = new ExportWebReport();
		
//
//		System.out.println(" ");System.out.println(" ");
//		System.out.println("===========================================================================");
//		System.out.println("                            MODULO: " +modulo);
//		System.out.println("                            Reporte: " +reporte);
//		System.out.println("===========================================================================");
//		System.out.println(" ");System.out.println(" ");
//		

		if(modulo.equals("contabilidad") && reporte.equals("abonosXcaja")){
		  try{
			String s = rf.getFecha();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(s.length()<1 || s== null){
				s = sdf.format(new Date());
			}
			String s2 = s+" 23:59:59";
			s = s+" 00:00:01";
			Date fecha = sdf.parse(s);
			Date fecha2 = sdf.parse(s2);
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			ReportFile rep = new ReportFile();
			Integer c1 = rf.getC1();
			if(c1==null || c1 == 0)c1=0;
			Integer c2 = rf.getC2();
			if(c2==null || c2 == 0)c2=9999999;
			rep.addParameter("f1", sdf2.format(fecha));
			rep.addParameter("f2", sdf2.format(fecha2));
			rep.addParameter("c1", c1-1);
			rep.addParameter("c2", c2+1);
			rep.addParameter("solicita",  rf.getSolicita());
			rep.addParameter("autoriza",  rf.getAutoriza());
			rep.addParameter("suc_id",  new Integer(rf.getSucId()).toString());
			System.out.println(rep.getParameters());
			String pathSubreporte = servletContext
			.getRealPath("/listaReportes/contabilidad/");
			
			rep.addParameter("SUBREPORT_DIR",pathSubreporte);
			rep.setPathJasper(pathJasper);
			rep.setPathReport(pathJrxml);
			export.setReportFile(rep);
			export.exportPDFWeb("AbonosXCaja", request, response, true, con);
				
			}catch (Exception e) {
					e.printStackTrace();
			}
				return null;
			
		}

		/*
		 ===========================================================================
                           MODULO: estadosCuenta
                           Reporte: estadosCuentas
		=========================================================================== 
		 * 
		*/
		
		if(modulo.equals("estadosCuenta") && reporte.equals("estadosCuentas")){
			Calendar calendar = Calendar.getInstance();;
			Date f1 = new Date();
			Date f2 = new Date();
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
			if(rf.getFecha1()!=null && rf.getFecha1().length() > 1)
				f1 = formatoDelTexto.parse(rf.getFecha1());
			else{
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				//System.out.println(calendar.getTime());
				f1 = calendar.getTime();
			}
				
			
			if(rf.getFecha2()!=null && rf.getFecha2().length() > 1)
				f2 = formatoDelTexto.parse(rf.getFecha2());
			else
				f2= new Date();
				
			ReportFile rep = new ReportFile();
			rep.setPathJasper(pathJasper);
			rep.setPathReport(pathJrxml);
			
			rep.addParameter("ascId", request.getParameter("ascId"));
			rep.addParameter("fecha1",f1);
			rep.addParameter("fecha2", f2);
			
			CtaResCuentaResumenesDAO resDAO = new CtaResCuentaResumenesDAO(getSessionHibernate(request));
			Transaction tx = resDAO.getSession().beginTransaction();
			
			List l1 = resDAO.findSaldosAnteriores(f1, request.getParameter("ascId")); 
			List l2 = resDAO.findSaldosActuales(f2, request.getParameter("ascId"));
			int i=0, j=0;
			Resumen r1;
			Resumen r2;
			if(l1.size() > l2.size()){
				while(i< l1.size()){
					CtaResCuentaResumenes res = new CtaResCuentaResumenes();
					r1 = (Resumen)l1.get(i); 
					r2 = (Resumen)l2.get(j);
					if(r1.getCuenta().equals(r2.getCuenta())){
						res.setResCuentaNom(r1.getCuenta());
						res.setResLinea(r1.getLinea());
						res.setResSaldoAct(r2.getSaldo());
						res.setResSaldoAnt(r1.getSaldo());
						i++;
						j++;	
					}else{
						res.setResCuentaNom(r1.getCuenta());
						res.setResLinea(r1.getLinea());
						res.setResSaldoAct(0.0);
						res.setResSaldoAnt(r1.getSaldo());						
						i++;
					}
					if (res.getResSaldoAct() != 0.0 || res.getResSaldoAnt()!=0.0){
						resDAO.save(res);
						tx.commit();	
					}
					
				}
			}else{
				while(j< l2.size()){
					CtaResCuentaResumenes res = new CtaResCuentaResumenes();
					r2 = (Resumen)l2.get(j);
					if(l1.size()>0 && i < l1.size()){
						r1 = (Resumen)l1.get(i);
					}else{
						r1=new Resumen();
						r1.setCuenta("&%/&(");
					}
					
					if(r2.getCuenta().equals(r1.getCuenta())){
						res.setResCuentaNom(r2.getCuenta());
						res.setResLinea(r2.getLinea());
						res.setResSaldoAct(r2.getSaldo());
						res.setResSaldoAnt(r1.getSaldo());
						i++;
						j++;	
					}else{
						res.setResCuentaNom(r2.getCuenta());
						res.setResLinea(r2.getLinea());
						res.setResSaldoAct(r2.getSaldo());
						res.setResSaldoAnt(0.0);						
						j++;
					}
					if (res.getResSaldoAct() != 0.0 || res.getResSaldoAnt()!=0.0){
						resDAO.save(res);
						tx.commit();	
					}
				}
			}
			
			try{
				export.setReportFile(rep);
				export.exportPDFWeb("EstadoDeCuenta", request, response, true, con);
				
			}catch (Exception e) {
					e.printStackTrace();
			}finally{
				resDAO.deleteAll();
				tx.commit();
			}
			return null;
		}
		
		
		
		//Saldos Ahorro Prestamo
		if(modulo.equals("saldosAhorroPrestamo") && reporte.equals("MovimientoPrestamosDelDia")){
			//String pathSubreport = servletContext.getRealPath("/listaReportes/"
			//		+ modulo);
			ReportFile rep = new ReportFile();
			String pathSubreporte = servletContext
			.getRealPath("/listaReportes/saldosAhorroPrestamo/");
			rep.addParameter("SUBREPORT_DIR",pathSubreporte + "/");
			
			
			rep.setPathJasper(pathJasper);
			rep.setPathReport(pathJrxml);
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
			Date fecha = new Date();
			if(rf.getFecha()!=null)fecha = formatoDelTexto.parse(rf.getFecha());
			rep.addParameter("tprId", rf.getTprId());
			rep.addParameter("ttrId", rf.getTtrId());
			rep.addParameter("lprId", rf.getLprId());
			rep.addParameter("nombreEmpresa", rf.getNombreEmpresa());
			rep.addParameter("Dia", fecha);
			try{
				export.setReportFile(rep);
				export.exportPDFWeb("MovimientoPrestamosDelDia", request, response, true, con);
				
				return null;
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}
		//ReporteSaldosDePrestamos
		if(modulo.equals("ReporteSaldosDePrestamos") && reporte.equals("ReporteSaldosDePrestamos")){
			ReportFile rep = new ReportFile();
			rep.setPathJasper(pathJasper);
			rep.setPathReport(pathJrxml);
			rep.addParameter("lineaId", rf.getLprId());
			rep.addParameter("IDEmpresa", rf.getEtrId());
			try{
				export.setReportFile(rep);
				export.exportPDFWeb("ReporteSaldosDePrestamos", request, response, true, con);
				
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}
		//ReporteSaldosDePrestamos
		if(modulo.equals("ReportePrestamosPorTipoPrestamo") && reporte.equals("ReportePrestamosPorTipoPrestamo")){
			ReportFile rep = new ReportFile();
			rep.setPathJasper(pathJasper);
			rep.setPathReport(pathJrxml);
			rep.addParameter("lineaId", rf.getLprId());
			rep.addParameter("mes", Integer.parseInt(rf.getMes()));
			rep.addParameter("anio",Integer.parseInt(rf.getAnio()));
			rep.addParameter("IDEmpresa", rf.getEtrId());
			try{
				export.setReportFile(rep);
				export.exportPDFWeb("ReporteOtorgamientoPrestamos", request, response, true, con);
				
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}			
		//reporteSaldosAhorro
		if(modulo.equals("saldosAhorroPrestamo") && reporte.equals("reporteSaldosAhorro")){
			ReportFile rep = new ReportFile();
			rep.setPathJasper(pathJasper);
			rep.setPathReport(pathJrxml);
			String ascId = rf.getAscId2();
			if(ascId.equals("")|| ascId.length() < 1 || ascId == null) ascId = "%";
			rep.addParameter("ascId", ascId);
			rep.addParameter("tipoAhorroId", rf.getTahId());
			rep.addParameter("lineaAhorroId", rf.getLahId() );
			rep.addParameter("idEmpresa", rf.getEtrId());
			
			
			
			try{
				export.setReportFile(rep);
				if (rf.isExcel()){
				export.exportXLSWeb("reporteSaldosAhorro", request, response, true, con);
				}else{
				export.exportPDFWeb("reporteSaldosAhorro", request, response, true, con);
				}
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}		
		//ReportePorRangoDeCuentas
		if(modulo.equals("ReportePorRangoDeCuentas") && reporte.equals("ReportePorRangoDeCuentas")){
			ReportFile rep = new ReportFile();
			rep.setPathJasper(pathJasper);
			rep.setPathReport(pathJrxml);
			
			rep.addParameter("IDEmpresa", rf.getEtrId());
			String rangoCuentaInicio = "0";
			String rangoCuentaFin = "_FIN";
			if(rf.getRangoCuentaIni()!= null && rf.getRangoCuentaIni().length() > 0)rangoCuentaInicio=rf.getRangoCuentaIni();
			if(rf.getRangoCuentaFin()!= null && rf.getRangoCuentaFin().length() > 0)rangoCuentaFin=rf.getRangoCuentaFin();
			rep.addParameter("RangoCuentaFin", rangoCuentaFin);
			rep.addParameter("RangoCuentaInicio", rangoCuentaInicio);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			rep.addParameter("fecha1", sdf.parseObject(rf.getFecha1()));
			rep.addParameter("fecha2", sdf.parseObject(rf.getFecha2()));			
			try{
				export.setReportFile(rep);
				export.exportPDFWeb("ReportePorRangoDeCuentas", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}
		/*
		 ===========================================================================
                            MODULO: Reporte_PlanillasXEmpresa
                            Reporte: report_planilla_empresa
		===========================================================================
		 */
		if(modulo.equals("Reporte_PlanillasXEmpresa") && reporte.equals("report_planilla_empresa")){
			ReportFile rep = new ReportFile();
			rep.setPathJasper(pathJasper);
			rep.setPathReport(pathJrxml);
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
			Date fecha = new Date();
			if(rf.getFecha()!=null)fecha = formatoDelTexto.parse(rf.getFecha());
			
			rep.addParameter("codEmpresa", rf.getEtrId());
			rep.addParameter("fecha", fecha);			
			try{
				export.setReportFile(rep);
				export.exportPDFWeb("ReportePlanillaPorEmpresa", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}
		//InteresCapitalizadoPorAhorrante
		if(modulo.equals("saldosAhorroPrestamo") && reporte.equals("InteresCapitalizadoPorAhorrante")){
			ReportFile rep = new ReportFile();
			
			if(rf.isExcel()){
				pathJasper = servletContext.getRealPath("/listaReportes/" +
								"ReportesExtras/InteresExcel.jasper");
			}
			rep.setPathJasper(pathJasper);
			rep.setPathReport(pathJrxml);
			
			rep.addParameter("tipoAhorroId", rf.getTahId());
			rep.addParameter("lineaAhorroId", rf.getLahId());
			rep.addParameter("ver_interes", rf.getVer_intereses());
			
			Date fecha = getUltimoDiaDeMes(new Integer(rf.getMes()),new Integer(rf.getAnio()));
			
			rep.addParameter("fecha", fecha);
			
			String asc;
			if(rf.getAscId2().equals("")|| rf.getAscId2().length() < 1 ||rf.getAscId2() == null )
				asc="%";
			else
				asc=rf.getAscId2();
			rep.addParameter("ascId", asc);
			rep.addParameter("etrId", rf.getEtrId());
			
			try{
				export.setReportFile(rep);
				if(rf.isExcel()){
					export.exportXLSWeb("InteresCapitalizadoPorAhorrante", request, response, true, con);	
				}
				else
				export.exportPDFWeb("InteresCapitalizadoPorAhorrante", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}
		//ReporteRegistroAsociados
		if(modulo.equals("asociados") && reporte.equals("reporte_asociados")){
			ReportFile rep = new ReportFile();
			rep.setPathJasper(pathJasper);
			rep.setPathReport(pathJrxml);
			
			rep.addParameter("id_asociado", rf.getAscCodigoAsociado());
		
			try{
				export.setReportFile(rep);
				export.exportPDFWeb("reporte_asociados", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}
		//MovimientoDeAportaciones
		if(modulo.equals("saldosAhorroPrestamo") && reporte.equals("movimientoDeAportaciones")){
			String pathSubreport = servletContext.getRealPath("/listaReportes/"
					+ modulo);
			ReportFile rep = new ReportFile();
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
			Date dia = new Date();
			if(rf.getDia()!=null) dia = formatoDelTexto.parse(rf.getDia());			
			rep.addParameter("dia",dia);

			try{
				rep.setPathJasper(pathJasper);
				export.setReportFile(rep);
				export.exportPDFWeb("movimientoDeAportaciones", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}
		//MovimientoDeAhorros
		if(modulo.equals("saldosAhorroPrestamo") && reporte.equals("movimientoDeAhorros")){
			String pathSubreport = servletContext.getRealPath("/listaReportes/"
					+ modulo);
			ReportFile rep = new ReportFile();
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
			Date dia = new Date();
			if(rf.getDia()!=null && rf.getDia().length()>1 && !rf.getDia().equals("")) dia = formatoDelTexto.parse(rf.getDia());			
			rep.addParameter("dia",dia);
			String ascId = rf.getAscId2();
			if(ascId.equals("")|| ascId.length() < 1 || ascId == null) ascId = "%";
			rep.addParameter("tipoAhorroId", rf.getTahId());
			rep.addParameter("lineaAhorroId", rf.getLahId() );
			rep.addParameter("etrId", rf.getEtrId());
			rep.addParameter("ascId", ascId);
			rep.addParameter("ttrId", rf.getTtrId());
			
			try{
				rep.setPathJasper(pathJasper);
				export.setReportFile(rep);
				if(rf.isExcel())
					export.exportXLSWeb("movimientoDeAhorros", request, response, true, con);
				else
					export.exportPDFWeb("movimientoDeAhorros", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}
		//q1
		//movimineto de cuentas corrientes
		if(modulo.equals("q1") && reporte.equals("q1")){
			pathJasper = servletContext.getRealPath("/listaReportes/movimientosCuentas" +
					"/movimientosCuCo.jasper");
			
			ReportFile rep = new ReportFile();
			Date fechaIni = new Date();
			Date fechaFin = new Date();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				fechaIni = sdf.parse(rf.getFecha1());
				fechaFin =sdf.parse(rf.getFecha2());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			rep.addParameter("fechaIni", fechaIni);
			rep.addParameter("fechaFin", fechaFin);
			rep.addParameter("ttrId", rf.getTtrId().toString());
			rep.addParameter("tahId", rf.getTahId().toString());
			rep.addParameter("lahId", rf.getLahId().toString());
			rep.addParameter("lprId", rf.getLprId().toString());
			rep.addParameter("tisId", rf.getTisId().toString());
			rep.addParameter("tipo", new Integer(rf.getTipo()).toString());
			
			try{
				rep.setPathJasper(pathJasper);
				export.setReportFile(rep);
					export.exportXLSWeb("movimientosCuCor", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}
		
		//saldos a la fecha
		if(modulo.equals("saldosAl")){
			pathJasper = servletContext.getRealPath("/listaReportes/saldosAhorroPrestamo" +
					"/saldosAl.jasper");
			
			ReportFile rep = new ReportFile();
			Date fecha = new Date();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				fecha = sdf.parse(rf.getFecha());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			rep.addParameter("fecha", fecha);
			rep.addParameter("tahId", rf.getTahId().toString());
			rep.addParameter("lahId", rf.getLahId().toString());
			rep.addParameter("lprId", rf.getLprId().toString());
			rep.addParameter("tisId", rf.getTisId().toString());
			rep.addParameter("tipo", new Integer(rf.getTipo()));
			rep.addParameter("idEmpresa", new Long(rf.getEtrId()));
			
			try{
				rep.setPathJasper(pathJasper);
				export.setReportFile(rep);
					export.exportPDFWeb("saldosPorFecha", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}
		
		
		//LibroDiarioGeneral
		
		if(reporte.equals("libroDiarioGeneral")){
			pathJasper = servletContext.getRealPath("/listaReportes/contabilidad" +
					"/LibroDiarioGeneral.jasper");
			
			ReportFile rep = new ReportFile();
			String mes=rf.getMes();
			String anio = rf.getAnio();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fechaIni = sdf.parse("01-"+rf.getMes()+"-"+rf.getAnio());
			Date fechaFin = getUltimoDiaDeMes(new Integer(rf.getMes()), new Integer(rf.getAnio()));
			
			rep.addParameter("fecha2", fechaIni);
			rep.addParameter("fecha", fechaFin);
			
			try{
				rep.setPathJasper(pathJasper);
				export.setReportFile(rep);
					export.exportPDFWeb("libroDiarioGeneral", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}
		
		
		
		//Libro Mayor
		if(modulo.equals("libroMayor") && reporte.equals("libroMayor")){
			ReportFile rep = new ReportFile();
			int mes=0,mesA=0, anio=0;
			String fechaL = "";
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
			Date fecha = new Date();
			if(rf.getFecha()!=null) 
				fecha = formatoDelTexto.parse(rf.getFecha());
			else
				System.out.println("Warn: fecha vacia");
			
//			rep.addParameter("FECHA",fecha);
			
			SimpleDateFormat sdM = new SimpleDateFormat("MM");
			SimpleDateFormat sdy = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdd = new SimpleDateFormat("dd");
			
			mes= Integer.parseInt(sdM.format(fecha));
			anio= Integer.parseInt(sdy.format(fecha));
			
			if(mes == 12)
				mesA=1;
			else 
				mesA=mes-1; 
			fechaL= "REPORTE AL: "+sdd.format(getUltimoDiaDeMes(mes, anio))+" DE "+ReportesAction.nombreMes(sdM.format(fecha))+" DE "+anio;
			rep.addParameter("mesA",mesA);
			rep.addParameter("mes",mes);
			rep.addParameter("anio",anio);
			rep.addParameter("fecha",fechaL);
			
			try{
				rep.setPathJasper(pathJasper);
				export.setReportFile(rep);
				export.exportPDFWeb("libroMayor", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}			
		}		
		//Reporte Afiliados
		if(modulo.equals("reporte_afiliados") && reporte.equals("reporte_afiliados")){
			String pathSubreport = servletContext.getRealPath("/listaReportes/"
					+ modulo);
			ReportFile rep = new ReportFile();
			rep.addParameter("SUBREPORT_DIR",pathSubreport);			
			rep.addParameter("mes",new Integer(rf.getMes()));
			rep.addParameter("ano",new Integer(rf.getAnio()));
			rep.addParameter("gerente",rf.getGerente());
			rep.addParameter("promotora",rf.getPromotora());
			try{
				if(rf.isExcel()){
					pathJasper = servletContext.getRealPath("/listaReportes/" +
					"ReportesExtras/AfiliadosMesExcel.jasper");
					rep.setPathJasper(pathJasper);
					export.setReportFile(rep);
					export.exportXLSWeb("reporte_afiliados", request, response, true, con);
				}else{
					rep.setPathJasper(pathJasper);
					export.setReportFile(rep);
					export.exportPDFWeb("reporte_afiliados", request, response, true, con);	
				}
				

			}catch (Exception e) {
					e.printStackTrace();
			}			
		}
		
		
		if(modulo.equals("socios_liquidados") && reporte.equals("socios_liquidados")){
			ReportFile rep = new ReportFile();	
			rep.addParameter("mes",rf.getMes());
			rep.addParameter("anio",rf.getAnio());
			
			try{
				//System.out.println("socios_liquidados");
				rep.setPathJasper(pathJasper);
				export.setReportFile(rep);
				export.exportPDFWeb("socios_liquidados", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}
		}
		/* 
		===========================================================================
		                            MODULO: reporteSaldoSeguros
		                            Reporte: Reporte_Saldo_Seguros
		===========================================================================		
		*/
		if(modulo.equals("reporteSaldoSeguros") && reporte.equals("Reporte_Saldo_Seguros")){
			ReportFile rep = new ReportFile();
			String par = "";
			if(rf.getEtrId().toString().equals("-1")) 
				par = "%";
			else
				par = rf.getEtrId().toString();
			rep.addParameter("codEmpresa",par);
			
			if(rf.getTisId().toString().equals("-1")) 
				par = "%";
			else
				par = rf.getTisId().toString();
			rep.addParameter("tipoSeguro",par);
			
			try{
				//System.out.println("Reporte_Saldo_Seguros");
				rep.setPathJasper(pathJasper);
				export.setReportFile(rep);
				export.exportPDFWeb("saldos_seguros", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}
		}
		
		/* 
		===========================================================================
		                            MODULO: Reporte_Renovacion_Seguros
		                            Reporte: Reporte_Renovacion_Seguros
		===========================================================================		
		*/
		if(modulo.equals("Reporte_Renovacion_Seguros") && reporte.equals("Reporte_Renovacion_Seguros")){
			ReportFile rep = new ReportFile();
			String par = "";
			if(rf.getEtrId().toString().equals("-1")) 
				par = "%";
			else
				par = rf.getEtrId().toString();
			rep.addParameter("codEmpresa",par);
			
			if(rf.getTisId().toString().equals("-1")) 
				par = "%";
			else
				par = rf.getTisId().toString();
			rep.addParameter("codTipoSeguro",par);
			
			try{
				//System.out.println("Reporte_Renovacion_Seguros");
				rep.setPathJasper(pathJasper);
				export.setReportFile(rep);
				export.exportPDFWeb("renovacion_seguros", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}
		}
		/*
		 
 
===========================================================================
                            MODULO: reporte_afiliados
                            Reporte: reporte_afiliados
===========================================================================
		 * */
		if(modulo.equals("reporte_afiliados") && reporte.equals("reporte_afiliados")){
			ReportFile rep = new ReportFile();	
			rep.addParameter("mes",new Integer(rf.getMes()));
			rep.addParameter("ano",new Integer(rf.getAnio()));
			rep.addParameter("gerente",rf.getGerente());
			rep.addParameter("promotora",rf.getPromotora());
			
			try{
				//System.out.println("reporte_afiliados");
				rep.setPathJasper(pathJasper);
				export.setReportFile(rep);
				export.exportPDFWeb("reporte_afiliados", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}
		}
/* 
===========================================================================
                            MODULO: reporte_socios
                            Reporte: reporte_socios
===========================================================================
asociados insafocoop!
*/
		
		if(modulo.equals("reporte_socios") && reporte.equals("reporte_socios")){
			ReportFile rep = new ReportFile();	
			String par = "";
			if(rf.getEtrId().toString().equals("-1")) 
				par = "%";
			else
				par = rf.getEtrId().toString();
			rep.addParameter("etrId",par);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(rf.getFecha() == null || rf.getFecha().equals("")|| rf.getFecha().length() < 1 ){
				rf.setFecha(sdf.format(new Date()));
			}
			
			try{
				
				Date fechaRep = sdf.parse(rf.getFecha());
				rep.addParameter("fecha", fechaRep);
				//System.out.println("reporte_afiliados");
				
				if(rf.isExcel()){
					modulo = "ReportesExtras";
					reporte = "AfiliadosEXCEL";
					pathJasper = servletContext.getRealPath("/listaReportes/"
							+ modulo + "/" + reporte + ".jasper");
					rep.setPathJasper(pathJasper);
					export.setReportFile(rep);
					export.exportCSVWeb("reporte_socios", request, response, true, con);
				}else{
					rep.setPathJasper(pathJasper);
					export.setReportFile(rep);
					export.exportPDFWeb("reporte_socios", request, response, true, con);	
				}
				
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}
		}
/* 
===========================================================================
                            MODULO: ReportePorTipoGarantia
                            Reporte: ReportePorTipoGarantia
===========================================================================
*/
				
		if(modulo.equals("ReportePorTipoGarantia") && reporte.equals("ReportePorTipoGarantia")){
			ReportFile rep = new ReportFile();	
			try{
				rep.addParameter("TipoGarantiaID",rf.getTipoGarantiaId());
				if(rf.getAscId2().equals("")) rf.setAscId2("%");
				rep.addParameter("ascId", rf.getAscId2());
				rep.setPathJasper(pathJasper);
				export.setReportFile(rep);
				export.exportPDFWeb("ReportePorTipoGarantia", request, response, true, con);
				return null;
			}catch (Exception e) {
					e.printStackTrace();
			}
		}
		/* 
		===========================================================================
		                            MODULO: ReportePrestamosEnMora
		                            Reporte: ReportePrestamosEnMora
		===========================================================================
		*/
						
				if(modulo.equals("ReportePrestamosEnMora") && reporte.equals("ReportePrestamosEnMora")){
					ReportFile rep = new ReportFile();	
					try{
						rep.addParameter("NumMeses",rf.getNumMeses());
						rep.addParameter("lprId", rf.getLprId());
						rep.setPathJasper(pathJasper);
						export.setReportFile(rep);
						export.exportPDFWeb("ReportePrestamosEnMora", request, response, true, con);
						return null;
					}catch (Exception e) {
							e.printStackTrace();
					}
				}		
		else{			
			String[] parametrosNombres = LecturaParametrosJasper
					.nombresParametrosArray(pathJrxml);
			String[] clazzes = LecturaParametrosJasper.nombresClazzArray(pathJrxml);
			
			HashMap<String, Object> mapa = export.obtenerParametros(request, parametrosNombres, clazzes,pathJrxml);
			if(reporte.equalsIgnoreCase("reporteMovEnCuentaContable") || reporte.equalsIgnoreCase("libro_auxiliar_mayor")){
				Date fechaFin = (Date)mapa.get("FECHA_FIN_MOV");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String fecha = sdf.format(fechaFin);
				int dias = new Integer(fecha.substring(0, 2)) - 1;
				ElapsedTime elapT = new ElapsedTime();
				Date fechaIni = elapT.obtenerFecha(fechaFin, -dias);
				mapa.remove("FECHA_INI_MOV");
				mapa.put("FECHA_INI_MOV", fechaIni);
				
			}
			ReportFile rep = new ReportFile(pathJrxml,mapa);
			rep.setPathJasper(pathJasper);
			export.setReportFile(rep);
			export.exportPDFWeb(reporte, request, response, true, con);	
		}		
		return null;
	}
	
	public Session getSessionHibernate(HttpServletRequest request) {
		return (Session) request.getAttribute(FiltroOpenSession.KEY_SESSION);
	}
	
	public static Date getUltimoDiaDeMes(int mes, int anio) {
		Calendar calendar = Calendar.getInstance();		
		calendar.set(Calendar.MONTH, mes-1);
		calendar.set(Calendar.YEAR, anio);
		int lastDate = calendar.getActualMaximum(Calendar.DATE);		
		calendar.set(Calendar.DATE, lastDate);
		//System.out.println("Ultimo dia: " +calendar.getTime()); 
		return calendar.getTime();
	}

}
