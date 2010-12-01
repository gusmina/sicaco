package com.cetia.sicaco.hibernate;

/**
 * AbstractInvClaClasificadoId entity provides the base persistence definition
 * of the InvClaClasificadoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvClaClasificadoId implements
		java.io.Serializable {

	// Fields

	private InvProProveedor invProProveedor = new InvProProveedor();
	private InvTclTipoClasificacion invTclTipoClasificacion = new InvTclTipoClasificacion();

	// Constructors

	/** default constructor */
	public AbstractInvClaClasificadoId() {
	}

	/** full constructor */
	public AbstractInvClaClasificadoId(InvProProveedor invProProveedor,
			InvTclTipoClasificacion invTclTipoClasificacion) {
		this.invProProveedor = invProProveedor;
		this.invTclTipoClasificacion = invTclTipoClasificacion;
	}

	// Property accessors

	public InvProProveedor getInvProProveedor() {
		return this.invProProveedor;
	}

	public void setInvProProveedor(InvProProveedor invProProveedor) {
		this.invProProveedor = invProProveedor;
	}

	public InvTclTipoClasificacion getInvTclTipoClasificacion() {
		return this.invTclTipoClasificacion;
	}

	public void setInvTclTipoClasificacion(
			InvTclTipoClasificacion invTclTipoClasificacion) {
		this.invTclTipoClasificacion = invTclTipoClasificacion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractInvClaClasificadoId))
			return false;
		AbstractInvClaClasificadoId castOther = (AbstractInvClaClasificadoId) other;

		return ((this.getInvProProveedor() == castOther.getInvProProveedor()) || (this
				.getInvProProveedor() != null
				&& castOther.getInvProProveedor() != null && this
				.getInvProProveedor().equals(castOther.getInvProProveedor())))
				&& ((this.getInvTclTipoClasificacion() == castOther
						.getInvTclTipoClasificacion()) || (this
						.getInvTclTipoClasificacion() != null
						&& castOther.getInvTclTipoClasificacion() != null && this
						.getInvTclTipoClasificacion().equals(
								castOther.getInvTclTipoClasificacion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getInvProProveedor() == null ? 0 : this.getInvProProveedor()
						.hashCode());
		result = 37
				* result
				+ (getInvTclTipoClasificacion() == null ? 0 : this
						.getInvTclTipoClasificacion().hashCode());
		return result;
	}

}