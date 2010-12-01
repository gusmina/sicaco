package com.cetia.sicaco.struts;

/**
 * 
 * @author Carlos Ramirez
 * La vista que de los fiadores que se presenta en el Jmesa es esta
 *
 */
public class FiadorVista {

	private String id;
	private String nombreFiador;
	private String primerApellido;
	private String segundoApellido;
	private String tipoFiador;//asociado o persona externa
	private Float salario;
	private String direccion;
	private String telefono;
	private String lugarTrabajo;
	private String telTrabajo;
	private String jefeInmediato;
	private String pxtEmail;
	private String codigo;
	
	
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getPxtEmail() {
		return pxtEmail;
	}
	public void setPxtEmail(String pxtEmail) {
		this.pxtEmail = pxtEmail;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getLugarTrabajo() {
		return lugarTrabajo;
	}
	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}
	public String getTelTrabajo() {
		return telTrabajo;
	}
	public void setTelTrabajo(String telTrabajo) {
		this.telTrabajo = telTrabajo;
	}
	public String getJefeInmediato() {
		return jefeInmediato;
	}
	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombreFiador() {
		return nombreFiador;
	}
	public void setNombreFiador(String nombreFiador) {
		this.nombreFiador = nombreFiador;
	}
	public String getTipoFiador() {
		return tipoFiador;
	}
	public void setTipoFiador(String tipoFiador) {
		this.tipoFiador = tipoFiador;
	}
	public Float getSalario() {
		return salario;
	}
	public void setSalario(Float salario) {
		this.salario = salario;
	}
	
	
	
}
