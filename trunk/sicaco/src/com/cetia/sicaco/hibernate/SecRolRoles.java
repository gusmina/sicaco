package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * SecRolRoles entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecRolRoles extends AbstractSecRolRoles implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecRolRoles() {
	}

	/** minimal constructor */
	public SecRolRoles(String rolNombre, String rolDescripcion,
			String rolTipoSesion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(rolNombre, rolDescripcion, rolTipoSesion, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

	/** full constructor */
	public SecRolRoles(String rolNombre, String rolDescripcion,
			String rolTipoSesion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set secIseInicioSesions,
			Set secRopRolMenus) {
		super(rolNombre, rolDescripcion, rolTipoSesion, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion, secIseInicioSesions, secRopRolMenus);
	}

}
