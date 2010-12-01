package com.mad.utilidades;

import java.util.HashMap;

public class ReportFile {
    private String pathReport;
    private String pathJasper;
    private HashMap<String, Object> parameters;
    
    /**
     * Crea una instacia de un reporte con nombre y parametros vacios
     */
    public ReportFile() {
        this("", "", null);
    }
    
    /**
     * Crea una instancia de un reporte nuevo apuntado a un archivo espec&iacute;fico
     * con parametros vacios.
     * @param nameReport direcci&oacute;n f&iacute;sica del archivo jasper report
     */
    public ReportFile(String nameReport) {
        this(nameReport, "", null);
    }
    
    /**
     * Crea una instancia de un reporte nuevo apuntando a un archivo espec&iacute;fico
     * con una cantidad de parametros.
     * @param nameReport direcci&oacute;n f&iacute;sica del archivo jasper report
     * @param parameters parametros del reporte
     */
    public ReportFile(String nameReport, HashMap<String, Object> parameters) {
        this.pathReport = nameReport;
        if(parameters != null)
            this.parameters = parameters;
        else
            this.parameters = new HashMap<String, Object>();
    }
    /**
     * Crea una instancia de reporte nuevo apuntando a un archivo especifico asi como
     * su compilado, incluye los parametros del reporte.
     * @param pathReport direccion fisica del archivo
     * @param pathJasper direccion fisica del compilado
     * @param parameters parametros del reporte
     */
    public ReportFile(String pathReport, String pathJasper, HashMap<String, Object> parameters) {
        this(pathReport, parameters);
        this.pathJasper = pathJasper;
    }
    /**
     * Crea una instancia de reporte nuevo apuntando a un archivo especifico asi como
     * su compilado.
     * @param pathReport direccion fisica del archivo.
     * @param pathJasper direccion fisica de compliado.
     */
    public ReportFile(String pathReport, String pathJasper) {
        this(pathReport, pathJasper, null);
    }
    
    /**
     * Obtiene el nombre del reporte
     * @return nombre del reporte
     */
    public String getPathReport() {
        return pathReport;
    }
    
    /**
     * Cambia el nombre del reporte
     * @param nameReport nombre del reporte
     */
    public void setPathReport(String nameReport) {
        this.pathReport = nameReport;
    }
    
    /**
     * Obtiene los parametros del reporte
     * @return parametros del reporte
     */
    public HashMap<String, Object> getParameters() {
        return parameters;
    }
    
    /**
     * Agrega un parametro al reporte
     * @param key nombre del parametro en el reporte
     * @param value valor especificado para ser evaluado por el reporte
     */
    
	public void addParameter(String key, Object value) {
        this.parameters.put(key, value);
    }
    /**
     * Obtiene la direccion del archivo compilado
     * @return direccion del archivo compilado
     */
    public String getPathJasper() {
        return pathJasper;
    }
    /**
     * Cambia la direccion del archivo compilado
     * @param pathJasper direccion del archivo compilado.
     */
    public void setPathJasper(String pathJasper) {
        this.pathJasper = pathJasper;
    }
}

