package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvLinLinea entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvLinLinea extends AbstractInvLinLinea implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvLinLinea() {
	}

	public InvLinLinea(Integer linId, String linCodigo, String linNombre,
			String linDescripcion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(linId, linCodigo, linNombre, linDescripcion, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion, audUsuarioModificacion);
		// TODO Auto-generated constructor stub
	}

	public InvLinLinea(Integer linId, String linCodigo, String linNombre,
			String linDescripcion, Double linUtilidad, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set invArtArticulos) {
		super(linId, linCodigo, linNombre, linDescripcion, linUtilidad,
				audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion, invArtArticulos);
		// TODO Auto-generated constructor stub
	}


}
