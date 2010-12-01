package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCliComComentario entity provides the base persistence definition of
 * the CliComComentario entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCliComComentario implements java.io.Serializable {

	// Fields

	private Integer comCodigo;
	private SecIseInicioSesion secIseInicioSesion;
	private String comComentario;
	private Date comFechaIngreso;

	// Constructors

	/** default constructor */
	public AbstractCliComComentario() {
	}

	/** full constructor */
	public AbstractCliComComentario(SecIseInicioSesion secIseInicioSesion,
			String comComentario, Date comFechaIngreso) {
		this.secIseInicioSesion = secIseInicioSesion;
		this.comComentario = comComentario;
		this.comFechaIngreso = comFechaIngreso;
	}

	// Property accessors

	public Integer getComCodigo() {
		return this.comCodigo;
	}

	public void setComCodigo(Integer comCodigo) {
		this.comCodigo = comCodigo;
	}

	public SecIseInicioSesion getSecIseInicioSesion() {
		return this.secIseInicioSesion;
	}

	public void setSecIseInicioSesion(SecIseInicioSesion secIseInicioSesion) {
		this.secIseInicioSesion = secIseInicioSesion;
	}

	public String getComComentario() {
		return this.comComentario;
	}

	public void setComComentario(String comComentario) {
		this.comComentario = comComentario;
	}

	public Date getComFechaIngreso() {
		return this.comFechaIngreso;
	}

	public void setComFechaIngreso(Date comFechaIngreso) {
		this.comFechaIngreso = comFechaIngreso;
	}

}