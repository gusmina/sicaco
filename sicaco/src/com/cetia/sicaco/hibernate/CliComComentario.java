package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CliComComentario entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CliComComentario extends AbstractCliComComentario implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CliComComentario() {
	}

	/** full constructor */
	public CliComComentario(SecIseInicioSesion secIseInicioSesion,
			String comComentario, Date comFechaIngreso) {
		super(secIseInicioSesion, comComentario, comFechaIngreso);
	}

}
