package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * ConTimTipoImpuesto entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConTimTipoImpuesto extends AbstractConTimTipoImpuesto implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ConTimTipoImpuesto() {
	}

	/** minimal constructor */
	public ConTimTipoImpuesto(String timId, String timNombre) {
		super(timId, timNombre);
	}

	/** full constructor */
	public ConTimTipoImpuesto(String timId, String timNombre,
			String timDescripcion, Set conRimRetencionImpuestos) {
		super(timId, timNombre, timDescripcion, conRimRetencionImpuestos);
	}

}
