package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCtrParParametros entity provides the base persistence definition of
 * the CtrParParametros entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrParParametros implements java.io.Serializable {

	// Fields

	private String parNombre;
	private String parDescripcion;
	private Date parValorDate;
	private String parValorString;
	private Double parValorNumber;
	private Byte parValorBoolean;
	private String audUsuarioCreacion;
	private Date audFechaCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractCtrParParametros() {
	}

	/** minimal constructor */
	public AbstractCtrParParametros(String parNombre, String parDescripcion,
			Date parValorDate, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.parNombre = parNombre;
		this.parDescripcion = parDescripcion;
		this.parValorDate = parValorDate;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractCtrParParametros(String parNombre, String parDescripcion,
			Date parValorDate, String parValorString, Double parValorNumber,
			Byte parValorBoolean, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.parNombre = parNombre;
		this.parDescripcion = parDescripcion;
		this.parValorDate = parValorDate;
		this.parValorString = parValorString;
		this.parValorNumber = parValorNumber;
		this.parValorBoolean = parValorBoolean;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	// Property accessors

	public String getParNombre() {
		return this.parNombre;
	}

	public void setParNombre(String parNombre) {
		this.parNombre = parNombre;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

	public Date getParValorDate() {
		return this.parValorDate;
	}

	public void setParValorDate(Date parValorDate) {
		this.parValorDate = parValorDate;
	}

	public String getParValorString() {
		return this.parValorString;
	}

	public void setParValorString(String parValorString) {
		this.parValorString = parValorString;
	}

	public Double getParValorNumber() {
		return this.parValorNumber;
	}

	public void setParValorNumber(Double parValorNumber) {
		this.parValorNumber = parValorNumber;
	}

	public Byte getParValorBoolean() {
		return this.parValorBoolean;
	}

	public void setParValorBoolean(Byte parValorBoolean) {
		this.parValorBoolean = parValorBoolean;
	}

	public String getAudUsuarioCreacion() {
		return this.audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaCreacion() {
		return this.audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
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

}