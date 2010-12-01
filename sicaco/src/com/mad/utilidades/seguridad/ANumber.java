/**
 * 
 */
package com.mad.utilidades.seguridad;

/**
 * @author Mauricio
 *
 */
public class ANumber {
	
	/**
	 * Genera un n&uacute;mero aletorio utilizando el tiempo
	 * como semilla de la generaci&oacute;n de est&eacute; n&uacute;mero.
	 * @return Cadena de texto de cuatro digitos
	 */
	public static String getAleatoriNumber() {
		String s = "";
		do {
			double numero = Math.random()*100000;
			s = Double.toString(numero).substring(0, 4);
		}while(s.lastIndexOf(".")!=-1);
		
		return s;
	}
	
	public static String getAleatoriName(String name){
		String cad=Hasher.getHash(name);
		cad=getAleatoriNumber()+ cad.substring(0,6);
		return cad;
	}
}
