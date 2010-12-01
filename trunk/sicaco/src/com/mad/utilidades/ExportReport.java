package com.mad.utilidades;

import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import java.sql.Connection;

import java.sql.SQLException;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;

public class ExportReport {
    private DataSource dataSource;
    private ReportFile reportFile;
    
    /**
     * Crea una nueva instancia de reporte con un datasource y el reporte a utilizar
     * @param dataSource fuente de conexion para la base de datos
     * @param reportFile reporte el cual se va exportar
     */
    public ExportReport(DataSource dataSource, ReportFile reportFile) {
        this.setDataSource(dataSource);
        this.setReportFile(reportFile);
    }
    /**
     * Crea una nueva instancia de reporte con un datasource. 
     * @param dataSource fuente de conexion para la base de datos
     */
    public ExportReport(DataSource dataSource) {
        this(dataSource, null);
    }
    
    /**
     * Crea una instancia predeterminada del exportador
     */
    public ExportReport() {
        this(null, null);
    }
    
    /**
     * Crea una nueva instancia de reporte con un datasource. 
     * @param reportFile reporte el cual se va exportar
     */
    public ExportReport(ReportFile reportFile) {
        this(null,reportFile);
    }
    
    /**
     * Exporta un reporte jasper report a un archivo pdf.
     * @param fileOutput direccion fisica del archivo a exportar
     * @param conn conexion de la cual se sacara los datos
     * @throws JRException
     */
    public void exportReportPDF(String fileOutput, Connection conn) throws JRException{
        //declaracion de variables
        JasperPrint jspprint = null;
        //JasperFillManager fillMng = null;
        JRExporter exporter = null;
        try {
            jspprint = JasperFillManager.fillReport(getReportFile().getPathJasper(), getReportFile().getParameters(), conn);
            exporter = new JRPdfExporter();
            
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileOutput);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jspprint);
            
            //exportamos el archivo a pdf
            exporter.exportReport();
        } catch (JRException e) {
            throw e;
        }
    }
    
    public void exportReportTxt(String fileOutput, Connection conn) throws JRException{
        //declaracion de variables
        JasperPrint jspprint = null;
        //JasperFillManager fillMng = null;
        JRExporter exporter = null;
        try {
            jspprint = JasperFillManager.fillReport(getReportFile().getPathJasper(), getReportFile().getParameters(), conn);
            exporter = new JRTextExporter();
            
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileOutput);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jspprint);
	        exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, 0);//text exporter
	        exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, 0);//text exporter
//            exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, 40); // en caracteres
//            exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, 80); // en caracteres
//            //exportamos el archivo a txt
            exporter.exportReport();
        } catch (JRException e) {
            throw e;
        }
    }
    
    
    
    
    public byte[] exportReportPDF(Connection conn) throws JRException {
        byte[] bytes = JasperRunManager.runReportToPdf(getReportFile().getPathJasper(), getReportFile().getParameters(), conn);
        return bytes;
    }
    /**
     * Crea un reporte jasper report a un archivo pdf, ocupando la conexion predeterminada
     * @param fileOutput direccion fisica del archivo a exportar.
     * @throws JRException
     * @throws SQLException
     */
    public void exportReportPDF(String fileOutput) throws JRException, SQLException{
        Connection conn = null;
        try {
            conn = getDataSource().getConnection();
            this.exportReportPDF(fileOutput, conn);
        }catch(JRException e) {
            throw e;
        }catch(SQLException e) {
            throw e;
        }finally {
            if(conn != null)try{conn.close();}catch(Exception e){e.printStackTrace();}
        }
    }
    
    public byte[] exportReportPDF() throws JRException, SQLException {
        Connection conn = null;
        byte [] bytes = null;
        try {
            conn = getDataSource().getConnection();
            bytes = this.exportReportPDF(conn);
        }catch(JRException e) {
            throw e;
        }catch(SQLException e) {
            throw e;
        }finally {
            if(conn != null)
            	try{
            		conn.close();
            	}catch(Exception e){
            		e.printStackTrace();
            	}
        }
        return bytes;
    }
    /**
     * Obtiene el datasource
     * @return datasource
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Cambia el datasource
     * @param dataSource nuevo datasource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * obtiene el path fisico del reporte
     * @return direccion fisica del reporte
     */
    public ReportFile getReportFile() {
        return reportFile;
    }
    /**
     * Cambia el path fisico del reporte
     * @param reportFile nuevo path
     */
    public void setReportFile(ReportFile reportFile) {
        this.reportFile = reportFile;
    }
}

