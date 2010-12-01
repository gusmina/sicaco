package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaRpeReferenciasPersonales entity provides the base persistence
 * definition of the CtaRpeReferenciasPersonales entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaRpeReferenciasPersonales implements
		java.io.Serializable {

	// Fields

	private Integer rpeId;
	private CtaPrePrestamo ctaPrePrestamo = new CtaPrePrestamo();
	private Integer parId;
	private String rpeApellidos;
	private String rpeNombres;
	private String rpeTelefono;
	private String rpeDireccion;
	private String rpeEstadoValidez;

	// Constructors

	/** default constructor */
	public AbstractCtaRpeReferenciasPersonales() {
	}

	/** minimal constructor */
	public AbstractCtaRpeReferenciasPersonales(Integer rpeId,
			String rpeApellidos, String rpeNombres, String rpeTelefono,
			String rpeDireccion, String rpeEstadoValidez) {
		this.rpeId = rpeId;
		this.rpeApellidos = rpeApellidos;
		this.rpeNombres = rpeNombres;
		this.rpeTelefono = rpeTelefono;
		this.rpeDireccion = rpeDireccion;
		this.rpeEstadoValidez = rpeEstadoValidez;
	}

	/** full constructor */
	public AbstractCtaRpeReferenciasPersonales(Integer rpeId,
			CtaPrePrestamo ctaPrePrestamo, Integer parId, String rpeApellidos,
			String rpeNombres, String rpeTelefono, String rpeDireccion,
			String rpeEstadoValidez) {
		this.rpeId = rpeId;
		this.ctaPrePrestamo = ctaPrePrestamo;
		this.parId = parId;
		this.rpeApellidos = rpeApellidos;
		this.rpeNombres = rpeNombres;
		this.rpeTelefono = rpeTelefono;
		this.rpeDireccion = rpeDireccion;
		this.rpeEstadoValidez = rpeEstadoValidez;
	}

	// Property accessors

	public Integer getRpeId() {
		return this.rpeId;
	}

	public void setRpeId(Integer rpeId) {
		this.rpeId = rpeId;
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return this.ctaPrePrestamo;
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		this.ctaPrePrestamo = ctaPrePrestamo;
	}

	public Integer getParId() {
		return this.parId;
	}

	public void setParId(Integer parId) {
		this.parId = parId;
	}

	public String getRpeApellidos() {
		return this.rpeApellidos;
	}

	public void setRpeApellidos(String rpeApellidos) {
		this.rpeApellidos = rpeApellidos;
	}

	public String getRpeNombres() {
		return this.rpeNombres;
	}

	public void setRpeNombres(String rpeNombres) {
		this.rpeNombres = rpeNombres;
	}

	public String getRpeTelefono() {
		return this.rpeTelefono;
	}

	public void setRpeTelefono(String rpeTelefono) {
		this.rpeTelefono = rpeTelefono;
	}

	public String getRpeDireccion() {
		return this.rpeDireccion;
	}

	public void setRpeDireccion(String rpeDireccion) {
		this.rpeDireccion = rpeDireccion;
	}

	public String getRpeEstadoValidez() {
		return this.rpeEstadoValidez;
	}

	public void setRpeEstadoValidez(String rpeEstadoValidez) {
		this.rpeEstadoValidez = rpeEstadoValidez;
	}

}