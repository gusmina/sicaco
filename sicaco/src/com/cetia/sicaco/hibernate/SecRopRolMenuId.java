package com.cetia.sicaco.hibernate;

/**
 * SecRopRolMenuId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecRopRolMenuId extends AbstractSecRopRolMenuId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecRopRolMenuId() {
	}

	/** full constructor */
	public SecRopRolMenuId(SecRolRoles secRolRoles,
			SecMopMenuOpcion secMopMenuOpcion) {
		super(secRolRoles, secMopMenuOpcion);
	}

}
