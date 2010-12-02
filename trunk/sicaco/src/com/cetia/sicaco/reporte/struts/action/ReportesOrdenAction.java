/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.reporte.struts.action;

//import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaAscAsociadoDAO;
import com.cetia.sicaco.hibernate.HibernateSessionFactory;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.reporte.struts.form.ReportesOrdenForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.mad.utilidades.ElapsedTime;
import com.mad.utilidades.ExportReport;
import com.mad.utilidades.ReportFile;


public class ReportesOrdenAction extends DMLAction{
	
	public static final String LISTA_SUCURSAL = "listaSecSucSucursal";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//List l = llenarReportes();
		ReportesOrdenForm reportesOrdenForm = (ReportesOrdenForm)form;
		request = seleccionarFiltro(request, reportesOrdenForm.getNumR());
		request.setAttribute("form", reportesOrdenForm);
		reportesOrdenForm.setNombre(reportesOrdenForm.getNumR());
		///******request.setAttribute("numR", reporteForm.getNumR());
		request.setAttribute(Constantes.ACCION_KEY, "/reportesOrden");
		//request.setAttribute("listaReport",l);
		return mapping.findForward("lista");
	}
	
	public HttpServletRequest seleccionarFiltro(HttpServletRequest request, int numR ) {
		switch(numR){
		case 1: request.setAttribute("filtro", "1");
				break;
		case 2:case 3:case 4:case 5: request.setAttribute("filtro", "2");
				break;
		/*case 5: request.setAttribute("filtro", "3");
				break;*/
		}
		/*request.setAttribute("form", rForm);
		request.setAttribute(Constantes.ACCION_KEY, "/reporte");*/
		return request;// 
	}
	
	public ActionForward generar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			ActionForward forward = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try{
				Date fechaActual = new Date();
				ReportesOrdenForm rOrdenForm = (ReportesOrdenForm) form;
				if(rOrdenForm.getNumR()!=5){
					if(rOrdenForm.getFechaFinD()!=null && rOrdenForm.getFechaIniD()!=null){
						if(ElapsedTime.fechaMenor(rOrdenForm.getFechaFinD(), rOrdenForm.getFechaIniD())){
							mensajes("error.rOrden.finMayorQueInicio", request);
							return lista(mapping, rOrdenForm, request, response);
						}
					}else{
						mensajes("error.rOrden.fechaVacia", request);
						return lista(mapping, rOrdenForm, request, response);
					}
				}else{
					if(rOrdenForm.getNumDias()<0){
						mensajes("error.rOrden.numDias", request);
						return lista(mapping, rOrdenForm, request, response);
					}
				}
				response.setHeader("Cache-Control","private");
				response.setHeader("Pragma", "Cache");
				String pathReporte = "";//ruta reporte
				String nombreReporte = "";
				ExportReport exportar = null;
				ReportFile reporte = new ReportFile();
				ServletContext servletContext = getServlet().getServletContext();
				
				switch(rOrdenForm.getNumR()){
					case 1://Reporte por Asociado del Estado de sus Ordenes de Compra
						nombreReporte = "RepEstCuentaOrdxAsoc";
						pathReporte = servletContext
						.getRealPath("/listaReportesOrden/RepEstCuentaOrdXAsoc/RepEstCuentaOrdxAsoc.jasper");
						reporte.addParameter("FECHA", rOrdenForm.getFechaIniD());
						reporte.addParameter("FECHA2",rOrdenForm.getFechaFinD());
						
						if(rOrdenForm.getAscId2()==null || rOrdenForm.getAscId2().equals("")){
							mensajes("error.rOrden.asociado", request);
							return lista(mapping, rOrdenForm, request, response);							
						}else reporte.addParameter("ASC_ID", rOrdenForm.getAscId2());
						/*if(rOrdenForm.getAsociadoId() == null || rOrdenForm.getAsociadoId().equals("")){
							rOrdenForm.setAsociadoId(rOrdenForm.getAscId2());
						}
						if(rOrdenForm.getAsociadoId()==null || rOrdenForm.getAsociadoId().trim().equals("")){
							mensajes("error.rOrden.asociado", request);
							return lista(mapping, rOrdenForm, request, response);
						}
						if(rOrdenForm.getAscId2()!=null && !rOrdenForm.getAscId2().trim().equals("")){
							reporte.addParameter("ASC_ID", rOrdenForm.getAsociadoId());
						}else{
							//Obtener AscId
							CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
							CtaAscAsociado asociado = null;
							if(rOrdenForm.getAscEmp()==1){
								List lAsoc = asociadoDAO.findByAscCodigoAsociado(rOrdenForm.getAsociadoId());
								if(lAsoc.size() > 0){
									asociado = (CtaAscAsociado) lAsoc.get(0);
									reporte.addParameter("ASC_ID", asociado.getAscId());
								}else{
									mensajes("error.rOrden.asociado", request);
									return lista(mapping, rOrdenForm, request, response);
								}
							}else{
								List lAsoc = asociadoDAO.findByAscCodigo(rOrdenForm.getAsociadoId());
								if(lAsoc.size() > 0){
									asociado = (CtaAscAsociado) lAsoc.get(0);
									reporte.addParameter("ASC_ID", asociado.getAscId());
								}else{
									mensajes("error.rOrden.asociado", request);
									return lista(mapping, rOrdenForm, request, response);
								}
							}
						}*/
					break;
					
					case 3://Reporte de Ordenes de Compra Cobradas
						nombreReporte = "RepOrdCobraFecha";
						pathReporte = servletContext
						.getRealPath("/listaReportesOrden/RepOrdCobraFechas/RepOrdCobraFecha.jasper");
						reporte.addParameter("FECHA", dateFormat.format(rOrdenForm.getFechaIniD()));
						reporte.addParameter("FECHA2",dateFormat.format(rOrdenForm.getFechaFinD()));
					break;
					
					case 4://Reporte de Ordenes de Compras emitidas ordenadas por proveedor
						nombreReporte = "RepOrdEmitidasXFechaXProv";
						pathReporte = servletContext
						.getRealPath("/listaReportesOrden/RepOrdEmitidasXFechaXProv/RepOrdEmitidasXFechaXProv.jasper");
						reporte.addParameter("FECHA", dateFormat.format(rOrdenForm.getFechaIniD()));
						reporte.addParameter("FECHA2",dateFormat.format(rOrdenForm.getFechaFinD()));
					break;
					
					case 5://Reporte de Ordenes de Compras emitidas anteriores a x dias
						nombreReporte = "RepOrdXAntiguedad";
						pathReporte = servletContext
						.getRealPath("/listaReportesOrden/RepOrdXAntiguedad/RepOrdXAntiguedad.jasper");
						//reporte.addParameter("DIAS", rOrdenForm.getNumDias());
						reporte.addParameter("FECHA", dateFormat.format(rOrdenForm.getFechaIniD()));
						reporte.addParameter("FECHA2",dateFormat.format(rOrdenForm.getFechaFinD()));						
					break;
					
				}
				reporte.setPathJasper(pathReporte);
				exportar = new ExportReport(reporte);//reporte a exportar
				String jdbcDriver = "com.mysql.jdbc.Driver";
				Class.forName(jdbcDriver);
				String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
				String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
				String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
				
				Connection con = DriverManager.getConnection(url, user, pass);
				
				//System.out.println(JRPropertiesMap.class.getPackage().getImplementationVersion());
				byte[] repCompilado = exportar.exportReportPDF(con);
				
				response.setContentType("application/pdf");
				response.setContentLength(repCompilado.length);
				response.setHeader("content-Disposition", "attachment;filename="+nombreReporte+"-"+fechaActual.getTime()+".pdf");
				//response.setHeader("content-Disposition", "attachment;filename="+nombreReporte+"-"+rForm.getFechaReporte()+".pdf");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(repCompilado, 0, repCompilado.length);
				outputStream.flush();
				outputStream.close();
				
			}catch(Exception e){
				e.printStackTrace();
				log.error("Se produjo un error al tratar de generar el reporte...", e);
				System.out.println("Se produjo un error al tratar de generar el reporte...\n");
			}
			return forward;
}
	
	public String nombreMes(String sMes){
		Integer numMes = new Integer(sMes);
		String mes="";
		switch(numMes.intValue()){
		case 1: mes="ENERO";
		case 2: mes="FEBRERO";
		case 3: mes="MARZO";
		case 4: mes="ABRIL";
		case 5: mes="MAYO";
		case 6: mes="JUNIO";
		case 7: mes="JULIO";
		case 8: mes="AGOSTO";
		case 9: mes="SEPTIEMBRE";
		case 10: mes="OCTUBRE";
		case 11: mes="NOVIEMBRE";
		case 12: mes="DICIEMBRE";
		}
		return mes;
	}
	
	public ActionForward cargarAsociado(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ReportesOrdenForm reportesOrdenForm = (ReportesOrdenForm)form;
		List listaAsociados = null;
		
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtaAscAsociado asociado = new CtaAscAsociado();
		asociado.setSecPerPersona(new SecPerPersona());
		asociado.getSecPerPersona().setPerPrimerNombre(
				reportesOrdenForm.getPerNombre());
		asociado.getSecPerPersona().setPerPrimerApellido(
				reportesOrdenForm.getPerApellido());
		
		if(reportesOrdenForm.getAscEmp()==1){
			asociado.setAscCodigoAsociado(reportesOrdenForm.getAsociadoId());
		}else{
			asociado.setAscCodigo(reportesOrdenForm.getAsociadoId());
		}
		
		try {
			listaAsociados = asociadoDAO.findByNameUser(asociado,10);
			Boolean nulo = false;
			if(listaAsociados.size() < 1){
				nulo = true;
			}
			String listaResponse = construirListaClientes(listaAsociados, nulo);
			response.getWriter().write(listaResponse);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (RuntimeException e) {
			log.error("Error runtime", e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	private String construirListaClientes(List<CtaAscAsociado> listaAsociados, Boolean nulo) {
		String lista = "<table id=\"resultadoCli\">";
		if(nulo == true){
			lista+= "<td><span style=\"font-size: 10px;color: red;font-style: italic;\">"
			+ "No se encontro ningun cliente o asociado en el sistema para esta b&uacute;squeda</span></td>";
		}else{
			int max1 = 0;
			int max2 = 0;
			int x = 10;
			if(listaAsociados != null){
				for (Iterator iterator = listaAsociados.iterator(); iterator.hasNext();) {
					//SecAscAsociado asociado = (SecAscAsociado) iterator.next();
					CtaAscAsociado asociado = (CtaAscAsociado)iterator.next();
					if(max2<5){
						lista += "<tr>";
						lista += "<td><input onclick=\"JavaScript:saveSeleccionC(this.value);\" type=\"radio\" name=\"_miseleccion\" value=\""
								+ asociado.getAscCodigo()
								+ ";"
								+ asociado.getSecPerPersona().getPerPrimerNombre()
								+ ";"
								+ asociado.getSecPerPersona().getPerPrimerApellido()
								+ ";"
								+ asociado.getAscId()
								+ "\"/></td>";
						lista += "<td><span style=\"font-size: 10px;color: #6E6E6E;font-style: italic;\">"
								+ asociado.getAscCodigo()
								+ " - "
								+ asociado.getSecPerPersona().getPerPrimerNombre()
								+ " "
								+ asociado.getSecPerPersona().getPerPrimerApellido()
								+ "</span></td>";
						lista += "</tr>";
					}
					max2++;
				}
			}
		}
		lista += "</table>";
		return lista;
	}
	
	public void mensajes(String msg, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
		saveMessages(request, errors);
	}
	
	protected Map<String, String> getKeyMethodMap() {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.reportesOrd.lista", "lista");
		map.put("cmd.reportesOrd.generarReporte", "generar");
		map.put("cmd.reportesOrd.seleccionarReporte", "seleccionar");
		map.put("cmd.reportesOrd.cargarAsociado", "cargarAsociado");
		return map;
	}
}