package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * SecSucSucursal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecSucSucursal extends AbstractSecSucSucursal implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecSucSucursal() {
	}

	public SecSucSucursal(Integer sucId, String sucNombre, String sucDireccion,
			String sucEstado, Set secPerPersonas, Set invBodBodegases,
			Set facFenFacturaEncabezados, Set ctrRckRepositorioChequeses) {
		super(sucId, sucNombre, sucDireccion, sucEstado, secPerPersonas,
				invBodBodegases, facFenFacturaEncabezados, ctrRckRepositorioChequeses);
		// TODO Auto-generated constructor stub
	}

	public SecSucSucursal(Integer sucId, String sucNombre, String sucDireccion,
			String sucEstado) {
		super(sucId, sucNombre, sucDireccion, sucEstado);
		// TODO Auto-generated constructor stub
	}



}
