package com.mad.utilidades;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.cetia.sicaco.struts.Constantes;

/**
 * @author dsaravia
 * @version 1.0 30-dic-2008 14:03 hr
 * @see Number2Letter
 *
 */
public class NumeroALetra {
	
	
	/**
	 * @param nO: valor a traducir. puede ser objeto Integer o Double 
	 * @param tipo: tipo de traduccion, ejemplo mes, cantidad monetaria.
	 * @return String de traduccion
	 */
	public static String obtenerTexto(Object nO, String tipo){
		if(tipo==null) return "";
		String resultado="";
		
		if(tipo.equals(Constantes.TIPO_TEXTO_FECHA_COMPLETA)){
			int n=((Integer)nO).intValue();
			switch (n){
			case 1: resultado="Enero"; break;
			case 2: resultado="Febrero"; break;
			case 3: resultado="Marzo"; break;
			case 4: resultado="Abril"; break;
			case 5: resultado="Mayo"; break;
			case 6: resultado="Junio"; break;
			case 7: resultado="Julio"; break;
			case 8: resultado="Agosto"; break;
			case 9: resultado="Septiembre"; break;
			case 10: resultado="Octubre"; break;
			case 11: resultado="Noviembre"; break;
			case 12: resultado="Diciembre"; break;
			default : resultado="Mes Inexistente";
			}
			Calendar c=Calendar.getInstance();
			resultado=
				c.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)+
				" de "+ resultado + " de "+(c.get(c.YEAR));//(c.getTime().getYear());//(c.get(c.YEAR)-1900);
		}
		else
		if(tipo.equals(Constantes.TIPO_CANTIDAD_MONETARIA)){
			double n=((Double)nO).doubleValue();
			resultado=new Number2Letter().getLetter(n);
		}
		
			
		return resultado;
	}
	
	/**Obtener el ultimo dia del mes  y anio especificado, en formato de texto
	 * @param nO: valor a traducir. puede ser objeto Integer o Double 
	 * @param tipo: tipo de traduccion, ejemplo mes, cantidad monetaria.
	 * @return String de fecha
	 */
	public static String obtenerUltimoDiaMesTexto(Integer anio,Integer mes){
		String resultado="";
		
			int n=mes.intValue();
			switch (n){
			case 1: resultado="Enero"; break;
			case 2: resultado="Febrero"; break;
			case 3: resultado="Marzo"; break;
			case 4: resultado="Abril"; break;
			case 5: resultado="Mayo"; break;
			case 6: resultado="Junio"; break;
			case 7: resultado="Julio"; break;
			case 8: resultado="Agosto"; break;
			case 9: resultado="Septiembre"; break;
			case 10: resultado="Octubre"; break;
			case 11: resultado="Noviembre"; break;
			case 12: resultado="Diciembre"; break;
			default : resultado="Mes Inexistente";
			}
			Calendar c=Calendar.getInstance();
			c.set(anio, mes, 0);
			resultado=
				c.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)+
				" de "+ resultado + " de "+(c.get(c.YEAR));//(c.getTime().getYear());//(c.get(c.YEAR)-1900);
			
		return resultado;
	}
	
	public static void main(String [] args){
		
		System.out.print(NumeroALetra.obtenerUltimoDiaMesTexto(2008,12).toUpperCase());
	}
}
