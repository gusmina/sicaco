package com.mad.utilidades;

/**
 * 
 * @author Dario Eguizabal
 * @category Utilidad
 * Clase creada para dividir el nombre completo de una persona 
 * en las diferentes partes que lo conforman
 *
 */
public class SplitNombre {
	
	private String primerNombre;
	private String segundoNombre;
	private String tercerNombre;
	private String primerApellido;
	private String segundoApellido;
	private String apellidoCasada;

	public SplitNombre() {
		// TODO Auto-generated constructor stub
	}

	public SplitNombre(String primerNombre, String segundoNombre,
			String tercerNombre, String primerApellido, String segundoApellido,
			String apellidoCasada) {
		super();
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.tercerNombre = tercerNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.apellidoCasada = apellidoCasada;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTercerNombre() {
		return tercerNombre;
	}

	public void setTercerNombre(String tercerNombre) {
		this.tercerNombre = tercerNombre;
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

	public String getApellidoCasada() {
		return apellidoCasada;
	}

	public void setApellidoCasada(String apellidoCasada) {
		this.apellidoCasada = apellidoCasada;
	}

	/**
	 * 
	 * @param nombreCompleto (nombre a dividir)
	 * @return Instancia del objeto SplitNombre (nombre ya dividido)
	 */
	public SplitNombre splitNombreCompleto(String nombreCompleto){
		SplitNombre nombre = new SplitNombre();
		
		//para establecer el valor de los nombres
       String [] nombres = nombreCompleto.split(" ");
       Integer cantidadDeNombres = nombres.length;

       nombre.setPrimerNombre(nombres[0].trim());
       if (nombre.getPrimerNombre().length() > 50){
           nombre.setPrimerNombre(nombre.getPrimerNombre().substring(0,50));
       }                       

       if (cantidadDeNombres >= 3){
    	   nombre.setSegundoNombre(nombres[1]);
       }
       else {
           nombre.setSegundoNombre("");
       }
       if (nombre.getSegundoNombre().length() > 50){
    	   nombre.setSegundoNombre(nombre.getSegundoNombre().substring(0,50));
       }

       nombre.setPrimerApellido("");
       if (cantidadDeNombres >= 3){
    	   nombre.setPrimerApellido(nombres[2].trim());
       }
       if (cantidadDeNombres == 2){
    	   nombre.setPrimerApellido(nombres[2].trim());
       }
       if (nombre.getPrimerApellido().length() > 50){
    	   nombre.setPrimerApellido(nombre.getPrimerApellido().substring(0,50));
       }

       nombre.setSegundoApellido("");
       if (cantidadDeNombres >= 4){
    	   nombre.setSegundoApellido(nombres[3]);
       }
       if (nombre.getSegundoApellido().length() > 50){
    	   nombre.setSegundoApellido(nombre.getSegundoApellido().substring(0, 50));
       }                       

       nombre.setApellidoCasada("");
       int i = nombreCompleto.toUpperCase().indexOf(" DE ");
       if (i>=0){
    	   nombre.setApellidoCasada(nombreCompleto.substring(i));                                
       }
       if (nombre.getApellidoCasada().length() > 50){
    	   nombre.setApellidoCasada(nombre.getApellidoCasada().substring(0,50));
       }
       return nombre;
	}
}
