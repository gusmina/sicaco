package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * SecZonZona entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecZonZona extends AbstractSecZonZona implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecZonZona() {
	}

	public SecZonZona(Integer zonId, String zonCodigo, String zonNombre,
			String zonDescripcion) {
		super(zonId, zonCodigo, zonNombre, zonDescripcion);
		// TODO Auto-generated constructor stub
	}

	public SecZonZona(Integer zonId, String zonCodigo, String zonNombre) {
		super(zonId, zonCodigo, zonNombre);
		// TODO Auto-generated constructor stub
	}

}
