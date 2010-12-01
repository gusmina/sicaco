package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractSecPemPersonaEmergencia entity provides the base persistence
 * definition of the SecPemPersonaEmergencia entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecPemPersonaEmergencia implements
		java.io.Serializable {

	// Fields

	private Integer pemId;
	private SecParParentesco secParParentesco = new SecParParentesco();
	private SecPerPersona secPerPersona;
	private String pemPrimerNombre;
	private String pemSegundoNombre;
	private String pemTercerNombre;
	private String pemPrimerApellido;
	private String pemSegundoApellido;
	private String pemApellidoCasada;
	private String pemTelefono;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractSecPemPersonaEmergencia() {
	}

	/** minimal constructor */
	public AbstractSecPemPersonaEmergencia(Integer pemId,
			String pemPrimerNombre, String pemPrimerApellido,
			String pemTelefono, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.pemId = pemId;
		this.pemPrimerNombre = pemPrimerNombre;
		this.pemPrimerApellido = pemPrimerApellido;
		this.pemTelefono = pemTelefono;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractSecPemPersonaEmergencia(Integer pemId,
			SecParParentesco secParParentesco, SecPerPersona secPerPersona,
			String pemPrimerNombre, String pemSegundoNombre,
			String pemTercerNombre, String pemPrimerApellido,
			String pemSegundoApellido, String pemApellidoCasada,
			String pemTelefono, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.pemId = pemId;
		this.secParParentesco = secParParentesco;
		this.secPerPersona = secPerPersona;
		this.pemPrimerNombre = pemPrimerNombre;
		this.pemSegundoNombre = pemSegundoNombre;
		this.pemTercerNombre = pemTercerNombre;
		this.pemPrimerApellido = pemPrimerApellido;
		this.pemSegundoApellido = pemSegundoApellido;
		this.pemApellidoCasada = pemApellidoCasada;
		this.pemTelefono = pemTelefono;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	// Property accessors

	public Integer getPemId() {
		return this.pemId;
	}

	public void setPemId(Integer pemId) {
		this.pemId = pemId;
	}

	public SecParParentesco getSecParParentesco() {
		return this.secParParentesco;
	}

	public void setSecParParentesco(SecParParentesco secParParentesco) {
		this.secParParentesco = secParParentesco;
	}

	public SecPerPersona getSecPerPersona() {
		return this.secPerPersona;
	}

	public void setSecPerPersona(SecPerPersona secPerPersona) {
		this.secPerPersona = secPerPersona;
	}

	public String getPemPrimerNombre() {
		return this.pemPrimerNombre;
	}

	public void setPemPrimerNombre(String pemPrimerNombre) {
		this.pemPrimerNombre = pemPrimerNombre;
	}

	public String getPemSegundoNombre() {
		return this.pemSegundoNombre;
	}

	public void setPemSegundoNombre(String pemSegundoNombre) {
		this.pemSegundoNombre = pemSegundoNombre;
	}

	public String getPemTercerNombre() {
		return this.pemTercerNombre;
	}

	public void setPemTercerNombre(String pemTercerNombre) {
		this.pemTercerNombre = pemTercerNombre;
	}

	public String getPemPrimerApellido() {
		return this.pemPrimerApellido;
	}

	public void setPemPrimerApellido(String pemPrimerApellido) {
		this.pemPrimerApellido = pemPrimerApellido;
	}

	public String getPemSegundoApellido() {
		return this.pemSegundoApellido;
	}

	public void setPemSegundoApellido(String pemSegundoApellido) {
		this.pemSegundoApellido = pemSegundoApellido;
	}

	public String getPemApellidoCasada() {
		return this.pemApellidoCasada;
	}

	public void setPemApellidoCasada(String pemApellidoCasada) {
		this.pemApellidoCasada = pemApellidoCasada;
	}

	public String getPemTelefono() {
		return this.pemTelefono;
	}

	public void setPemTelefono(String pemTelefono) {
		this.pemTelefono = pemTelefono;
	}

	public Date getAudFechaCreacion() {
		return this.audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
	}

	public String getAudUsuarioCreacion() {
		return this.audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaModificacion() {
		return this.audFechaModificacion;
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		this.audFechaModificacion = audFechaModificacion;
	}

	public String getAudUsuarioModificacion() {
		return this.audUsuarioModificacion;
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	public String getParDescripcion() {
		return secParParentesco.getParDescripcion();
	}

	public Integer getParId() {
		return secParParentesco.getParId();
	}

	public void setParDescripcion(String parDescripcion) {
		secParParentesco.setParDescripcion(parDescripcion);
	}

	public void setParId(Integer parId) {
		secParParentesco.setParId(parId);
	}

}