package com.cetia.sicaco.hibernate;

/**
 * AbstractInvCprCapacidadProductoId entity provides the base persistence
 * definition of the InvCprCapacidadProductoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvCprCapacidadProductoId implements
		java.io.Serializable {

	// Fields

	private InvBodBodegas invBodBodegas = new InvBodBodegas();
	private InvArtArticulo invArtArticulo = new InvArtArticulo();

	// Constructors

	/** default constructor */
	public AbstractInvCprCapacidadProductoId() {
	}

	/** full constructor */
	public AbstractInvCprCapacidadProductoId(InvBodBodegas invBodBodegas,
			InvArtArticulo invArtArticulo) {
		this.invBodBodegas = invBodBodegas;
		this.invArtArticulo = invArtArticulo;
	}

	// Property accessors

	public InvBodBodegas getInvBodBodegas() {
		return this.invBodBodegas;
	}

	public void setInvBodBodegas(InvBodBodegas invBodBodegas) {
		this.invBodBodegas = invBodBodegas;
	}

	public InvArtArticulo getInvArtArticulo() {
		return this.invArtArticulo;
	}

	public void setInvArtArticulo(InvArtArticulo invArtArticulo) {
		this.invArtArticulo = invArtArticulo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractInvCprCapacidadProductoId))
			return false;
		AbstractInvCprCapacidadProductoId castOther = (AbstractInvCprCapacidadProductoId) other;

		return ((this.getInvBodBodegas() == castOther.getInvBodBodegas()) || (this
				.getInvBodBodegas() != null
				&& castOther.getInvBodBodegas() != null && this
				.getInvBodBodegas().equals(castOther.getInvBodBodegas())))
				&& ((this.getInvArtArticulo() == castOther.getInvArtArticulo()) || (this
						.getInvArtArticulo() != null
						&& castOther.getInvArtArticulo() != null && this
						.getInvArtArticulo().equals(
								castOther.getInvArtArticulo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getInvBodBodegas() == null ? 0 : this.getInvBodBodegas()
						.hashCode());
		result = 37
				* result
				+ (getInvArtArticulo() == null ? 0 : this.getInvArtArticulo()
						.hashCode());
		return result;
	}

}