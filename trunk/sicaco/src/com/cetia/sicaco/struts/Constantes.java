package com.cetia.sicaco.struts;


import java.io.Serializable;

/**
 * Clase especializada para guardar todas las constantes de la aplicaci&oacute;n
 * @author Mauricio Jovel
 * @version 1.0 24-ENE-2008
 */
public class Constantes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3819132187570443960L;
	
	/**
	 * Nombre de variables que se guardara en sesi&oacute;n para reconocer
	 * los datos del usuario que en este momento se ha conectado.
	 */
	public static final String USUARIO_KEY = "_usuarioSesion";
	
	/**
	 * Nombre de variable que se guardara en sesi&oacute;n para reconocer
	 * a las opciones que el usuario tendra acceso cuando haya
	 * tenido un acceso exitoso a la aplicaci&oacute;n.
	 */
	public static final String MENU_KEY = "_menuAplicacion";
	
	public static final String FECHA_KEY = "_fechaInicioSesion";
	
	public static final String LISTA_KEY = "_lista";
	
	public static final String BOTON_KEY = "_boton";
	
	public static final String ACCION_KEY = "_accion";
	
	public final static String HSEID = "_hseid";
	
	public final static String TIPO_SESSION_ADIMINISTRADOR = "A";
	
	public final static String TIPO_SESSION_CLIENTE = "C";
	
	public final static int SIZE_PWF = 3;
	
	//Formato de la fecha
	public final static String FORMATE_DATE = "dd-MM-yyyy";
	
	/**
	 * Guarda un identificador unico para poder recuperar luego los parametros de la aplicacion.
	 */
	public final static String PARAMETROS_KEY = "_parametros";
	public final static String ERRORS = "errors";
	public final static String PERSONA = "persona";
	public final static String TABLA_IDAJAX = "tablaIdAjax";
	public final static String PROVEEDOR = "proveedor";
	public final static String METHOD_NAME = "methodName";
	public final static String ARTICULO = "articulo";
	
	public final static String TIPO_TEXTO_FECHA_MES = "TIPO_TEXTO_FECHA_MES";
	public final static String TIPO_TEXTO_FECHA_COMPLETA = "TIPO_TEXTO_FECHA_COMPLETA";
	public final static String TIPO_CANTIDAD_MONETARIA = "TIPO_CANTIDAD_MONETARIA";
	public final static String MAYUSCULAS="MAYUSCULAS";
	public final static String MINUSCULAS="MINUSCULAS";
}
