package com.mad.utilidades;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Mauricio Jovel
 * Clase creada para llamar los reportes desde la jsp especial que se arma automaticamente
 *
 */
public class ExportWebReport extends ExportReport {

	private static final Log log = LogFactory.getLog(ExportReport.class);

	public void exportPDFWeb(String nombreReporte, HttpServletRequest request,
			HttpServletResponse response) {
		exportPDFWeb(nombreReporte, request, response, true);
	}

	public void exportPDFWeb(HttpServletRequest request,
			HttpServletResponse response) {
		exportPDFWeb("rep_091283", request, response, true);
	}
	
	public void exportPDFWeb(String nombreReporte, HttpServletRequest request,
			HttpServletResponse response, boolean isDownload) {
		try {
			exportPDFWeb(nombreReporte, request,response,isDownload,getDataSource().getConnection());
		} catch (SQLException e) {
			log.fatal("No se pudo obtener la conexion a la base de datos", e);
			throw new RuntimeException(e);
		}
	}
	
	public void exportPDFWeb(String nombreReporte, HttpServletRequest request,
			HttpServletResponse response, boolean isDownload, Connection con) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		JRPdfExporter exporter = new JRPdfExporter();
		JasperPrint jspprint = null;

		response.setHeader("Cache-Control", "private");
		response.setHeader("Pragma", "Cache");
		response.setContentType("application/pdf");
		response
				.setHeader("content-Disposition",
						(((isDownload) ? "attachment;" : "") + "filename="
								+ nombreReporte + "-"
								+ format.format(new Date()) + ".pdf"));

		try {
//			System.out.println("Report Parameters EXPORTWEB REPORT:"+getReportFile().getParameters());

			jspprint = JasperFillManager.fillReport(getReportFile()
					.getPathJasper(), getReportFile().getParameters(),
					con);

			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response
					.getOutputStream());
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jspprint);
			exporter.exportReport();

		} catch (IOException e) {
			log.fatal("No se pudo correr el reporte " + nombreReporte, e);
			throw new RuntimeException(e);
		} catch (JRException e) {
			log.fatal("No se pudo encontrar el jasper print para el reporte "
					+ nombreReporte, e);
			throw new RuntimeException(e);
		} 

	}

	public void exportXLSWeb(String nombreReporte, HttpServletRequest request,
			HttpServletResponse response, boolean isDownload, Connection con) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		
		JExcelApiExporter exporter = new JExcelApiExporter();
		JasperPrint jspprint = null;

		response.setHeader("Cache-Control", "private");
		response.setHeader("Pragma", "Cache");
		response.setContentType("application/vnd.ms-excel");
		response
				.setHeader("content-Disposition",
						(((isDownload) ? "attachment;" : "") + "filename="
								+ nombreReporte + "-"
								+ format.format(new Date()) + ".xls"));

		try {
			//System.out.println("Report Parameters EXPORTWEB REPORT:"+getReportFile().getParameters());
			jspprint = JasperFillManager.fillReport(getReportFile()
					.getPathJasper(), getReportFile().getParameters(),
					con);

			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response
					.getOutputStream());
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jspprint);
			exporter.exportReport();

		} catch (IOException e) {
			log.fatal("No se pudo correr el reporte " + nombreReporte, e);
			throw new RuntimeException(e);
		} catch (JRException e) {
			log.fatal("No se pudo encontrar el jasper print para el reporte "
					+ nombreReporte, e);
			throw new RuntimeException(e);
		} 

	}
	
	public void exportCSVWeb(String nombreReporte, HttpServletRequest request,
			HttpServletResponse response, boolean isDownload, Connection con) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		
		//JExcelApiExporter exporter = new JExcelApiExporter();
		JRCsvExporter exporter = new JRCsvExporter(); 
		JasperPrint jspprint = null;

		response.setHeader("Cache-Control", "private");
		response.setHeader("Pragma", "Cache");
		response.setContentType("application/vnd.ms-excel");
		response
				.setHeader("content-Disposition",
						(((isDownload) ? "attachment;" : "") + "filename="
								+ nombreReporte + "-"
								+ format.format(new Date()) + ".CSV"));

		try {
			//System.out.println("Report Parameters EXPORTWEB REPORT:"+getReportFile().getParameters());
			jspprint = JasperFillManager.fillReport(getReportFile()
					.getPathJasper(), getReportFile().getParameters(),
					con);

			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response
					.getOutputStream());
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jspprint);
			exporter.exportReport();

		} catch (IOException e) {
			log.fatal("No se pudo correr el reporte " + nombreReporte, e);
			throw new RuntimeException(e);
		} catch (JRException e) {
			log.fatal("No se pudo encontrar el jasper print para el reporte "
					+ nombreReporte, e);
			throw new RuntimeException(e);
		} 

	}
	

	@SuppressWarnings("unchecked")
	public Object convertParamObject(HttpServletRequest request, String name,
			String type) {
		String d = request.getParameter(name);

		if (d == null || d.equals("")) {
			return null;
		}else if (type.indexOf("String")>=0) {
			return d;
		} else if (type.indexOf("Date")>=0) {
			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
			
			try {
				return f.parse(d);
			} catch (ParseException e) {
				log
						.fatal("No se pudo convertir a Date el parametro "
								+ name, e);
				throw new RuntimeException(e);
			}

		} else if(type.indexOf("Timestamp")>=0) {
			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
			
			try {
				return new Timestamp(f.parse(d).getTime());
			} catch (ParseException e) {
				log
						.fatal("No se pudo convertir a Date el parametro "
								+ name, e);
				throw new RuntimeException(e);
			}	
		} else if(type.indexOf("Integer")>=0) {
			return new Integer(Integer.parseInt(d));
		} else if(type.indexOf("Float")>=0) {
			return new Float(Float.parseFloat(d));
		} else if(type.indexOf("Double")>=0) {
			return new Double(Double.parseDouble(d));
		} else if(type.indexOf("BigDecimal")>=0) {
			return new BigDecimal(d);
		} else {
			return d;
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> obtenerParametros(HttpServletRequest request ,String[] parametros, String[] clazzes,String pathJrxml) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < parametros.length; i++) {
			String parametro = parametros[i];
			if(!parametro.toUpperCase().equals("SUBREPORT_DIR")) {
				map.put(parametro, convertParamObject(request, parametro, clazzes[i]));
			}else {
				int pos = pathJrxml.lastIndexOf("/");//para unix
				if(pos == -1) pos = pathJrxml.lastIndexOf("\\");// para windows
				map.put("SUBREPORT_DIR", pathJrxml.substring(0,pos+1));
			}
		}
		return map;
	}
}
