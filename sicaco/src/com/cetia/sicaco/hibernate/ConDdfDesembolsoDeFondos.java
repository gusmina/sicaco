package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * ConDdfDesembolsoDeFondos entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConDdfDesembolsoDeFondos extends AbstractConDdfDesembolsoDeFondos
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ConDdfDesembolsoDeFondos() {
	}

	/** full constructor */
	public ConDdfDesembolsoDeFondos(Integer banId, Double prestamos,
			Double ahorros, Double proveedores, String codigoCuenta, Date fecha) {
		super(banId, prestamos, ahorros, proveedores, codigoCuenta, fecha);
	}

}
