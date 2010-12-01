package com.mad.utilidades;

import java.text.DecimalFormat;

public class Format {
		
	public static final String FORMAT_MONEY = "$###,###,##0.00";
	public static final String FORMAT_MONEY2 = "#0.00";
	
	/**
	 * 
	 * @param nit (sin formato solo digitos)
	 * @return String formateado del NIT
	 * 
	 */
	public static String formatNit(String nit){
		String nitFormat;
		if(nit !=null && !nit.trim().equals("")){
			nitFormat = nit.substring(0, 4) + "-" + nit.substring(4, 10) + "-" + nit.substring(10, 13) + "-" + nit.substring(13, 14);
		}
		else{
			nitFormat = "";
		}
		return nitFormat;
	}
	
	/**
	 * 
	 * @param dui (sin formato)
	 * @return String DUI formateado
	 */
	public static String formatDui(String dui){
		String formatDui;
		if (dui != null && !dui.trim().equals("")){
			formatDui = dui.substring(0, 8) + "-" + dui.substring(8, 9);
		}
		else{
			formatDui = "";
		}
		return formatDui;
	}
	
	/**
	 * 
	 * @param correlativo (Entero que necesita los ceros a la izquierda)
	 * @param tam (Longitud del nuevo numero a obtener)
	 * @return String del correlativo ya con formato
	 * Ej. correlativo = 5, tam = 3, 
	 * Resultado = "005"
	 */
	public static String formatNumeroFactura(Integer correlativo,int tam){
		String corr;
		String zero="00000000000000000000";
		int size;
		if(correlativo < 1 || tam <1){
			corr = correlativo.toString();
		}else{
			size = correlativo.toString().length();
			if(tam < size){
				corr = correlativo.toString();
			}else{
				corr = zero.substring(0, tam - size) + correlativo.toString();
			}
		}
		return corr;
	}
	
	/**
	 * 
	 * @param valor (cantidad de dinero a formatear)
	 * @return DecimalFormat del valor
	 * 
	 */
	public static String formatDinero(double valor) {
		DecimalFormat format = new DecimalFormat(FORMAT_MONEY);
		return format.format(valor);
	}
	
	/**
	 * 
	 * @param valor (cantidad de dinero a formatear)
	 * @return DecimalFormat del valor
	 * 
	 */
	public static String formatDinero(Object valor) {
		DecimalFormat format = new DecimalFormat(FORMAT_MONEY);
		return format.format(valor);
	}
	
	/**
	 * 
	 * @param valor (cantidad de dinero a formatear)
	 * @return DecimalFormat del valor
	 * 
	 */
	public static String formatDineroSinSimbolo(double valor) {
		DecimalFormat format = new DecimalFormat(FORMAT_MONEY2);
		return format.format(valor);
	}
	
	/**
	 * 
	 * @param valor (cantidad de dinero a formatear)
	 * @return DecimalFormat del valor
	 * 
	 */
	public static String formatDineroSinSimbolo(Object valor) {
		DecimalFormat format = new DecimalFormat(FORMAT_MONEY2);
		return format.format(valor);
	}
	
	/**
	 * 
	 * @param reg (numero o letras que conforman en numero de registro de una empresa)
	 * @return String registro formateado (con guion)
	 * 
	 */
	public static String formatRegistro(String reg){
		String reg2 = "";
		int tam = reg.length();
		reg2 = reg.substring(0, tam - 2);
		reg2 += "-" + reg.substring(tam - 1);
		return reg2;
	}

/**
 * CREADA POR: DARIO EGUIZABAL
 * 
 * @param cadenaInicial
 * @param cadenaRelleno
 * @param longitudFinal
 * @return String de la cadena inicial con nuevo formato
 * Ej. cadenaInicial = "123", cadenaRelleno = "9", longitudFinal = 6,
 *  resultado = "999123"
 */
public static String rellenarIzquierda(String cadenaInicial, String cadenaRelleno, Integer longitudFinal){
		
		String s;
		s = cadenaInicial;
		
		Integer longitudInicial;
		longitudInicial = cadenaInicial.length();
		
		Integer numeroIteraciones;
		numeroIteraciones = longitudFinal - longitudInicial;
		
		if (numeroIteraciones < 0){
			return s;
		}
		
		if (cadenaRelleno.length() != 1){
			return s;
		}
		
		for(int i=0; i<numeroIteraciones; i++){
			s = cadenaRelleno+s;
		}
		
		return s;
	}
	


	/**
	 * CREADA POR: DARIO EGUIZABAL
	 * MODIFICADA POR: FRANCISCO LOZANO
	 * 
	 * @param cadenaInicial
	 * @param cadenaRelleno
	 * @param numeroRepeticiones
	 * @return String de la cadena inicial con nuevo formato
	 * Ej. cadenaInicial = "hijo", cadenaRelleno = "sub-", numeroRepeticiones = 3,
	 *  resultado = "sub-sub-sub-hijo"
	 */
	public static String rellenarIzquierdaV2(String cadenaInicial, String cadenaRelleno, Integer numeroRepeticiones){
		
		String s;
		s = cadenaInicial;
		
		Integer longitudInicial;
		longitudInicial = cadenaInicial.length();
		
		Integer numeroIteraciones;
		numeroIteraciones = numeroRepeticiones;
		
		if (numeroIteraciones < 0){
			return s;
		}
		
		for(int i=0; i<numeroIteraciones; i++){
			s = cadenaRelleno+s;
		}
		
		return s;
	}
	
	
	public static String mesNumericAString(int mesInt){
		String s="";
		switch (mesInt) {
		case 1: s = "Enero";
					break;
		case 2: s = "Febrero";
					break;
		case 3: s = "Marzo";
					break;
		case 4: s = "Abril";
					break;
		case 5: s = "Mayo";
					break;
		case 6: s = "Junio";
					break;
		case 7: s = "Julio";
					break;
		case 8: s = "Agosto";
					break;
		case 9: s = "Septiembre";
					break;
		case 10: s = "Octubre";
					break;
		case 11: s = "Noviembre";
					break;
		case 12: s = "Diciembre";
					break;
		}
		return s;
	}

}
