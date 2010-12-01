package com.mad.utilidades.seguridad;

public class PwdAleatorio {
	public static final String[] CARACTERES_PASSWORD = {
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
		"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
		"U", "V", "W", "X", "Y", "Z"
	};
	/*"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
	"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
	"u", "v", "w", "x", "y", "z",*/
	public String getPasswordAleatorio(int longitud){
		String pass = "";
		for(int i = 0; i < longitud; i++)
			pass += CARACTERES_PASSWORD[getNumeroAleatorioEntre(0, CARACTERES_PASSWORD.length-1)];
		return pass;
	}
	
	public int getNumeroAleatorioEntre(int inicio, int fin){
		return (int)((Math.random()*fin)+inicio);
	}
}
