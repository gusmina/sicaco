package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * FacFusFacturaUso entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class FacFusFacturaUso extends AbstractFacFusFacturaUso implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public FacFusFacturaUso() {
	}

	/** minimal constructor */
	public FacFusFacturaUso(Integer fusId, String fusNombre,
			String fusDescripcion) {
		super(fusId, fusNombre, fusDescripcion);
	}

	public FacFusFacturaUso(Integer fusId, String fusNombre,
			String fusDescripcion, String fusToperacion,
			Set facFenFacturaEncabezados) {
		super(fusId, fusNombre, fusDescripcion, fusToperacion, facFenFacturaEncabezados);
		// TODO Auto-generated constructor stub
	}


}
