/*
 * Creado el 11-06-2006
 */
package com.mad.utilidades.seguridad;

import java.security.*;

/**
 * 
 * @author Mauricio Jovel
 * @version 1.0
 */

public class Hasher {

	private static MessageDigest digest;
	
	// inicializacion del digest
	static {
		try {
			digest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}

	public Hasher() {
	}

	/**
	 * Regresa una cadena la cual le fue aplicada el algoritmo SHA hash
	 * 
	 * @param in cadena que ser&aacute; encriptada
	 * @return el hash.
	 */
	public static String getHash(String in) {
		byte[] out = in.getBytes();
		digest.update(out);
		byte[] dig = digest.digest();
		
/*		System.out.println("IN: "+in);
		System.out.println("dig: "+ dig.toString());
*/
		return byteArrayToHexString(dig);
		
		
	}

	/**
	 * Convierte un array de byte[] a una cadena formato de cadena legible. Esto
	 * hace que el codigo hexadeimal sea leible.
	 * 
	 * @return el resultado de la conversi&oacute; del array a un formato de cadena.
	 * @param in buffer byte[] buffer que ser&aacute; convertido a una cadena.
	 */

	static private String byteArrayToHexString(byte in[]) {
		byte ch = 0x00;
		int i = 0;
		if (in == null || in.length <= 0)
			return null;
		String pseudo[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"A", "B", "C", "D", "E", "F" };
		StringBuffer out = new StringBuffer(in.length * 2);		
		while (i < in.length) {
			ch = (byte) (in[i] & 0xF0);
			ch = (byte) (ch >>> 4);
			ch = (byte) (ch & 0x0F);
			out.append(pseudo[(int) ch]);
			ch = (byte) (in[i] & 0x0F);
			out.append(pseudo[(int) ch]);
			i++;
		}
		String rslt = new String(out);
		return rslt;
	}
}
