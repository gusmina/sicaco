/**
 * 
 */
package com.cetia.sicaco.struts;

import java.io.Serializable;
import java.util.Date;


/**
 * @author flozano
 * Objeto UsuarioConectado con todos los datos que este acarrea
 */
public class UsuarioConectado implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8662771796568019734L;
	private String nombreUsuario;
	private Date fecha;
	private String ip;
	private Date ultimoAcceso;
	private int max;
	
	public UsuarioConectado(){
		fecha=new Date();
		ultimoAcceso = new Date();
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getUltimoAcceso() {
		return ultimoAcceso;
	}
	public void setUltimoAcceso(Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
}
