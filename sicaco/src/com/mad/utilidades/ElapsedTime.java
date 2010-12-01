package com.mad.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ElapsedTime es un utilitario utilizado para obtener el per�odo de tiempo,
 * ya sea en d�as o meses que hay entre dos fechas.
 * @author http://www.javaworld.com/javaworld/jw-03-2001/jw-0330-time.html
 *
 */
public class ElapsedTime {
	/**
	 * Saca la cantidad de dias que existen entre dos fechas
	 * @param g1 fecha menor
	 * @param g2 fecha mayor
	 * @return cantidad de dias entre la fecha una y fecha 2
	 */
   public int getDays(GregorianCalendar g1, GregorianCalendar g2) {
      int elapsed = 0;
      GregorianCalendar gc1, gc2;
      if (g2.after(g1)) {
         gc2 = (GregorianCalendar) g2.clone();
         gc1 = (GregorianCalendar) g1.clone();
      }
      else   {
         gc2 = (GregorianCalendar) g1.clone();
         gc1 = (GregorianCalendar) g2.clone();
      }
      gc1.clear(Calendar.MILLISECOND);
      gc1.clear(Calendar.SECOND);
      gc1.clear(Calendar.MINUTE);
      gc1.clear(Calendar.HOUR_OF_DAY);
      gc2.clear(Calendar.MILLISECOND);
      gc2.clear(Calendar.SECOND);
      gc2.clear(Calendar.MINUTE);
      gc2.clear(Calendar.HOUR_OF_DAY);
      
      g1.set(Calendar.MILLISECOND, 0);
	  g1.set(Calendar.SECOND, 0);
	  g1.set(Calendar.MINUTE, 0);
	  g1.set(Calendar.HOUR_OF_DAY, 0);
	  
	  g2.set(Calendar.MILLISECOND, 0);
	  g2.set(Calendar.SECOND, 0);
	  g2.set(Calendar.MINUTE, 0);
	  g2.set(Calendar.HOUR_OF_DAY, 0);
		
      while ( gc1.before(gc2) ) {
         gc1.add(Calendar.DATE, 1);
         elapsed++;
      }
      return elapsed;
   }
   /**
    * Saca la cantidad de meses que existen entre dos fechas
    * @param g1 fecha menor
    * @param g2 fecha mayor
    * @return cantidad de dias entre las dos fechas
    */
   public int getMonths(GregorianCalendar g1, GregorianCalendar g2) {
      int elapsed = 0;
      g1.set(Calendar.MILLISECOND, 0);
	  g1.set(Calendar.SECOND, 0);
	  g1.set(Calendar.MINUTE, 0);
	  g1.set(Calendar.HOUR_OF_DAY, 0);
		
		g2.set(Calendar.MILLISECOND, 0);
		g2.set(Calendar.SECOND, 0);
		g2.set(Calendar.MINUTE, 0);
		g2.set(Calendar.HOUR_OF_DAY, 0);
		
      GregorianCalendar gc1, gc2;
      if (g2.after(g1)) {
         gc2 = (GregorianCalendar) g2.clone();
         gc1 = (GregorianCalendar) g1.clone();
      }
      else   {
         gc2 = (GregorianCalendar) g1.clone();
         gc1 = (GregorianCalendar) g2.clone();
      }
      if(gc2.get(Calendar.MONTH)!=gc1.get(Calendar.MONTH)){
    	  long fecha=gc2.getTimeInMillis() - gc1.getTimeInMillis();
    	  double valor=fecha/(1000*60*60*24*30.0);
    	  elapsed=(int)(Math.ceil(valor)-1);
      }
      else{
    	  elapsed=0;
      }
      
      return elapsed+1;
   }
   
   /**
    * 
    * @param g1 (fecha1)
    * @param g2	(fecha2)
    * @return	Cantidad de anhos existentes entre 2 fechas
    */
   public int getYears(GregorianCalendar g1, GregorianCalendar g2) {
	      int elapsed = 0;
	      
	      GregorianCalendar gc1, gc2;
	      if (g2.after(g1)) {
	         gc2 = (GregorianCalendar) g2.clone();
	         gc1 = (GregorianCalendar) g1.clone();
	      }
	      else   {
	         gc2 = (GregorianCalendar) g1.clone();
	         gc1 = (GregorianCalendar) g2.clone();
	      }
	      gc1.clear(Calendar.MILLISECOND);
	      gc1.clear(Calendar.SECOND);
	      gc1.clear(Calendar.MINUTE);
	      gc1.clear(Calendar.HOUR_OF_DAY);
	      gc2.clear(Calendar.MILLISECOND);
	      gc2.clear(Calendar.SECOND);
	      gc2.clear(Calendar.MINUTE);
	      gc2.clear(Calendar.HOUR_OF_DAY);
	      
	      g1.set(Calendar.MILLISECOND, 0);
		  g1.set(Calendar.SECOND, 0);
		  g1.set(Calendar.MINUTE, 0);
		  g1.set(Calendar.HOUR_OF_DAY, 0);
			
			g2.set(Calendar.MILLISECOND, 0);
			g2.set(Calendar.SECOND, 0);
			g2.set(Calendar.MINUTE, 0);
			g2.set(Calendar.HOUR_OF_DAY, 0);
			
	      while ( gc1.before(gc2) ) {
	          gc1.add(Calendar.YEAR, 1);
	          elapsed++;
	      }
	      return elapsed-1;
	   }
   
   /**
    * Devuelve una fecha a partir de una cantidad de dias ingresados
    * @param fechaOriginal fecha del dia de hoy o cualquier fecha de tipo date que se quiera ingresar
    * @param dias cantidad de dias que se le quieren sumar para obtener la nueva fecha
    * @return nueva fecha a partir de los dias agregados a la fecha original
    */
   public static Date obtenerFecha(Date fechaOriginal, int dias){
	   GregorianCalendar actualCalendar = new GregorianCalendar();
	   actualCalendar.setTime(fechaOriginal);
	   GregorianCalendar nuevaFecha = (GregorianCalendar) actualCalendar.clone();
	   nuevaFecha.add(Calendar.DATE, dias);
	   return nuevaFecha.getTime();
   }
   
   /**
    * 
    * @param fechaOriginal (de tipo String)
    * @param meses 
    * @return nueva fecha x meses adelante o atras en el tiempo 
    * Si meses es negativo devuelve hacia atras, si es positivo, devuelve hacia adelante
    */
   public static Date obtenerFechaMeses(String fechaOriginal, int meses){
	   Date fecha = sTDate(fechaOriginal);
	   GregorianCalendar actualCalendar = new GregorianCalendar();
	   actualCalendar.setTime(fecha);
	   GregorianCalendar nuevaFecha = (GregorianCalendar) actualCalendar.clone();
	   nuevaFecha.add(Calendar.MONTH, meses);
	   return nuevaFecha.getTime();
   }
   
   /**
    * 
    * @param fechaOriginal (de tipo Date)
    * @param meses 
    * @return nueva (!)fecha x meses adelante o atras en el tiempo ( meses adelante)  
    * Si meses es negativo devuelve hacia atras, si es positivo, devuelve hacia adelante
    */
   public static Date obtenerFechaMeses(Date fechaOriginal, int meses){
	   GregorianCalendar actualCalendar = new GregorianCalendar();
	   actualCalendar.setTime(fechaOriginal);
	   GregorianCalendar nuevaFecha = (GregorianCalendar) actualCalendar.clone();
	   nuevaFecha.add(Calendar.MONTH, meses);
	   return nuevaFecha.getTime();
   }
   
   /**
    * Convierte String de fecha en una fecha
    * @param fecha en String
    * @return Fecha en Date
    */
   public static Date sTDate(String fecha){
	   String fecha2 = fecha.substring(0, 10);
	   Date fechado = new Date();
	   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	   try{
		   fechado = sdf.parse(fecha);
	   }catch (ParseException e) {
		// TODO: handle exception
	   }
	   return fechado;
   }
   
   /**
    * Convierte fecha en GregorianCalendar
    * @param fecha en formato Date
    * @return fecha en formato Gregorian Calendar
    */
   public static GregorianCalendar dTGC(Date fecha){
	   GregorianCalendar gc = new GregorianCalendar();
	   gc.setTime(fecha);
	   return gc;
   }
   
   /**
    * Compara 2 fechas
    * @param fecha1
    * @param fecha2
    * @return falso si la fecha 1 es mayor que la fecha 2 y verdadero si la fecha 2 es mayor que la 1
    */
   public static Boolean fechaMenor(Date fecha1, Date fecha2){
	   GregorianCalendar gc1 = dTGC(fecha1);
	   GregorianCalendar gc2 = dTGC(fecha2);
	   if(gc1.after(gc2) || gc1.equals(gc2)){
		   return false;
	   }else{
		   return true;
	   }
   }
    
   	/**
   	 * A partir de una fecha x devuelve la fecha en la que debio realizarse
   	 * el ultimo pago
   	 * @param fechaPago
   	 * @return fecha que debio ser el ultimo pago
   	 * @throws ParseException
   	 */
	public static Date obtenUltimaFechaPago(Date fechaPago) throws ParseException {
		Date fechaActual = new Date();
		Date fechaUltimoPago = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int diaPago = Integer.valueOf(sdf.format(fechaPago).substring(8,10));
		int diaHoy = Integer.valueOf(sdf.format(fechaActual).substring(8,10));
		int mesPago = Integer.valueOf(sdf.format(fechaPago).substring(5,7));
		int mesHoy = Integer.valueOf(sdf.format(fechaActual).substring(5,7));
		int anhoPago = Integer.valueOf(sdf.format(fechaPago).substring(0,4));
		int anhoHoy = Integer.valueOf(sdf.format(fechaActual).substring(0,4));
		String sDiaPago = diaPago + "";
		
		if(diaHoy > diaPago){
			StringBuilder ultPago = new StringBuilder();
			String sMesHoy = mesHoy + "";
			ultPago.append(anhoHoy).append("-").append(Format.rellenarIzquierda(sMesHoy, "0", 2));
			ultPago.append("-").append(Format.rellenarIzquierda(sDiaPago, "0", 2));
			fechaUltimoPago = sdf.parse(ultPago.toString());
		}else{
			if(mesHoy == 1){
				mesHoy = 12;
				anhoHoy = anhoHoy - 1;
			}else{
				mesHoy = mesHoy -1;
			}
			StringBuilder ultPago = new StringBuilder();
			String sMesHoy = mesHoy + "";
			ultPago.append(anhoHoy).append("-").append(Format.rellenarIzquierda(sMesHoy, "0", 2));
			ultPago.append("-").append(Format.rellenarIzquierda(sDiaPago, "0", 2));
			fechaUltimoPago = sdf.parse(ultPago.toString());
		}
		return fechaUltimoPago;
	}
}