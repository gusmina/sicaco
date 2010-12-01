package com.mad.utilidades;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 
 * @author Francisco Lozano
 * @category Utilidad
 * Clase que contiene metodos de redondeo
 *
 */
public class Redondeo {
	/**
	 * 
	 * @param number (numero a redondear)
	 * @param num (numero de decimales que tendra el resultado)
	 * @return String del parametro number ya redondeado
	 */
	public static String dRound(double number,int num){
		String pattern = "####.";
		while(num > 0 ){
			pattern = pattern + "#";
			num = num - 1;
		}
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat myFormatter = (DecimalFormat)nf;
        myFormatter.applyPattern(pattern);
        //nf.setMaximumFractionDigits(num);
        String numerof = myFormatter.format(number);
        return numerof;
	}
	
	/**
	 * Redondea un numero flotante
	 * @param Rval (Valor a redondear)
	 * @param Rpl (numero de decimales a redondear)
	 * @return Float del Rval redondeado
	 */
	public static Float roundFloat(float Rval, int Rpl) {
		  float p = (float)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  float tmp = Math.round(Rval);
		  return (float)tmp/p;
	}
	
	/**
	 * Redondea un numero double
	 * @param Rval (Valor a redondear)
	 * @param Rpl (numero de decimales a redondear)
	 * @return Double del Rval redondeado
	 */
	public static Double roundDouble(double Rval, int Rpl) {
		  double p = Math.pow(10,Rpl);
		  Rval = Rval * p;
		  double tmp = Math.round(Rval);
		  return tmp/p;
	}
}
