/**
 * 
 */
package com.cetia.sicaco.struts;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Contiene todos los parametros de la aplicacion
 * @author Mauricio Jovel
 *
 */
public class Parametros implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4133942347089353727L;
	
	private HashMap<String, Object> parametros = new HashMap<String, Object>(0);
	
	/**
	 * Guarda la longitud de una contrase&ntilde;a al momento de ser autogenerada
	 */
	public static final String LONGITUD_CONTRASENIA = "par.contrasenia.longitud";

	public HashMap<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(HashMap<String, Object> parametros) {
		this.parametros = parametros;
	}

	public Object put(String key, Object value) {
		return parametros.put(key, value);
	}
	
	public Integer getParametroInteger(String key) {
		return (Integer) parametros.get(key);
	}
	
	public String getParametroString(String key) {
		return (String) parametros.get(key);
	}
	
	public Float getParametroFloat(String key) {
		return (Float) parametros.get(key);
	}
	
	public Double getParametroDouble(String key) {
		return (Double) parametros.get(key);
	}
	
	public Date getParametroDate(String key) {
		return (Date) parametros.get(key);
	}
	
	public Boolean getParametroBoolean(String key) {
		return (Boolean) parametros.get(key);
	}
}
