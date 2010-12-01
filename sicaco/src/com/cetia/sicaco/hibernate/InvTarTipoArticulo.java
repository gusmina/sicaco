package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvTarTipoArticulo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvTarTipoArticulo extends AbstractInvTarTipoArticulo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvTarTipoArticulo() {
	}

	/** minimal constructor */
	public InvTarTipoArticulo(String tarId, String tarNombre,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(tarId, tarNombre, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
	}

	/** full constructor */
	public InvTarTipoArticulo(String tarId, String tarNombre,
			String tarDescripcion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set invArtArticulos,
			Set iucTutTarTprs) {
		super(tarId, tarNombre, tarDescripcion, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion, invArtArticulos,
				iucTutTarTprs);
		// TODO Auto-generated constructor stub
	}

}
