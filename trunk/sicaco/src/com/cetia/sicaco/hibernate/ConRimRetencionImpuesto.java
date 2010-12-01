package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * ConRimRetencionImpuesto entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConRimRetencionImpuesto extends AbstractConRimRetencionImpuesto
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ConRimRetencionImpuesto() {
	}

	/** minimal constructor */
	public ConRimRetencionImpuesto(ConTimTipoImpuesto conTimTipoImpuesto,
			Double rimImpuesto, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(conTimTipoImpuesto, rimImpuesto, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

	/** full constructor */
	public ConRimRetencionImpuesto(ConTimTipoImpuesto conTimTipoImpuesto,
			Double rimImpuesto, Date rimFechaInvalidez, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set facFenFacturaEncabezados) {
		super(conTimTipoImpuesto, rimImpuesto, rimFechaInvalidez,
				audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion, facFenFacturaEncabezados);
	}

}
