package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * SecRopRolMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecRopRolMenu extends AbstractSecRopRolMenu implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecRopRolMenu() {
	}

	/** full constructor */
	public SecRopRolMenu(SecRopRolMenuId id, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(id, audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

}
