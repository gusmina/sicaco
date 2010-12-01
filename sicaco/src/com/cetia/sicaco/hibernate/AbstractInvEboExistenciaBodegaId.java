package com.cetia.sicaco.hibernate;

/**
 * AbstractInvEboExistenciaBodegaId entity provides the base persistence
 * definition of the InvEboExistenciaBodegaId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvEboExistenciaBodegaId implements
		java.io.Serializable {

	// Fields

	private InvBodBodegas invBodBodegas = new InvBodBodegas();
	private InvPexProductosExistencia invPexProductosExistencia = new InvPexProductosExistencia();

	// Constructors

	/** default constructor */
	public AbstractInvEboExistenciaBodegaId() {
	}

	/** full constructor */
	public AbstractInvEboExistenciaBodegaId(InvBodBodegas invBodBodegas,
			InvPexProductosExistencia invPexProductosExistencia) {
		this.invBodBodegas = invBodBodegas;
		this.invPexProductosExistencia = invPexProductosExistencia;
	}

	// Property accessors

	public InvBodBodegas getInvBodBodegas() {
		return this.invBodBodegas;
	}

	public void setInvBodBodegas(InvBodBodegas invBodBodegas) {
		this.invBodBodegas = invBodBodegas;
	}

	public InvPexProductosExistencia getInvPexProductosExistencia() {
		return this.invPexProductosExistencia;
	}

	public void setInvPexProductosExistencia(
			InvPexProductosExistencia invPexProductosExistencia) {
		this.invPexProductosExistencia = invPexProductosExistencia;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractInvEboExistenciaBodegaId))
			return false;
		AbstractInvEboExistenciaBodegaId castOther = (AbstractInvEboExistenciaBodegaId) other;

		return ((this.getInvBodBodegas() == castOther.getInvBodBodegas()) || (this
				.getInvBodBodegas() != null
				&& castOther.getInvBodBodegas() != null && this
				.getInvBodBodegas().equals(castOther.getInvBodBodegas())))
				&& ((this.getInvPexProductosExistencia() == castOther
						.getInvPexProductosExistencia()) || (this
						.getInvPexProductosExistencia() != null
						&& castOther.getInvPexProductosExistencia() != null && this
						.getInvPexProductosExistencia().equals(
								castOther.getInvPexProductosExistencia())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getInvBodBodegas() == null ? 0 : this.getInvBodBodegas()
						.hashCode());
		result = 37
				* result
				+ (getInvPexProductosExistencia() == null ? 0 : this
						.getInvPexProductosExistencia().hashCode());
		return result;
	}

}