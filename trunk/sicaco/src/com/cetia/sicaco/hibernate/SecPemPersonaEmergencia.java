package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * SecPemPersonaEmergencia entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecPemPersonaEmergencia extends AbstractSecPemPersonaEmergencia
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecPemPersonaEmergencia() {
	}

	/** minimal constructor */
	public SecPemPersonaEmergencia(Integer pemId, String pemPrimerNombre,
			String pemPrimerApellido, String pemTelefono,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(pemId, pemPrimerNombre, pemPrimerApellido, pemTelefono,
				audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

	/** full constructor */
	public SecPemPersonaEmergencia(Integer pemId,
			SecParParentesco secParParentesco, SecPerPersona secPerPersona,
			String pemPrimerNombre, String pemSegundoNombre,
			String pemTercerNombre, String pemPrimerApellido,
			String pemSegundoApellido, String pemApellidoCasada,
			String pemTelefono, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(pemId, secParParentesco, secPerPersona, pemPrimerNombre,
				pemSegundoNombre, pemTercerNombre, pemPrimerApellido,
				pemSegundoApellido, pemApellidoCasada, pemTelefono,
				audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

}
