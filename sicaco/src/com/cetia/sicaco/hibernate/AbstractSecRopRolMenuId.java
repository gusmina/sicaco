package com.cetia.sicaco.hibernate;

/**
 * AbstractSecRopRolMenuId entity provides the base persistence definition of
 * the SecRopRolMenuId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecRopRolMenuId implements java.io.Serializable {

	// Fields

	private SecRolRoles secRolRoles = new SecRolRoles();
	private SecMopMenuOpcion secMopMenuOpcion = new SecMopMenuOpcion();

	// Constructors

	/** default constructor */
	public AbstractSecRopRolMenuId() {
	}

	/** full constructor */
	public AbstractSecRopRolMenuId(SecRolRoles secRolRoles,
			SecMopMenuOpcion secMopMenuOpcion) {
		this.secRolRoles = secRolRoles;
		this.secMopMenuOpcion = secMopMenuOpcion;
	}

	// Property accessors

	public SecRolRoles getSecRolRoles() {
		return this.secRolRoles;
	}

	public void setSecRolRoles(SecRolRoles secRolRoles) {
		this.secRolRoles = secRolRoles;
	}

	public SecMopMenuOpcion getSecMopMenuOpcion() {
		return this.secMopMenuOpcion;
	}

	public void setSecMopMenuOpcion(SecMopMenuOpcion secMopMenuOpcion) {
		this.secMopMenuOpcion = secMopMenuOpcion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractSecRopRolMenuId))
			return false;
		AbstractSecRopRolMenuId castOther = (AbstractSecRopRolMenuId) other;

		return ((this.getSecRolRoles() == castOther.getSecRolRoles()) || (this
				.getSecRolRoles() != null
				&& castOther.getSecRolRoles() != null && this.getSecRolRoles()
				.equals(castOther.getSecRolRoles())))
				&& ((this.getSecMopMenuOpcion() == castOther
						.getSecMopMenuOpcion()) || (this.getSecMopMenuOpcion() != null
						&& castOther.getSecMopMenuOpcion() != null && this
						.getSecMopMenuOpcion().equals(
								castOther.getSecMopMenuOpcion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getSecRolRoles() == null ? 0 : this.getSecRolRoles()
						.hashCode());
		result = 37
				* result
				+ (getSecMopMenuOpcion() == null ? 0 : this
						.getSecMopMenuOpcion().hashCode());
		return result;
	}

}