package com.cetia.sicaco.hibernate;

/**
 * AbstractInvNivNivelId entity provides the base persistence definition of the
 * InvNivNivelId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvNivNivelId implements java.io.Serializable {

	// Fields

	private Integer nivFilaId;
	private InvStnEstante invStnEstante;
	private Integer nivColId;

	// Constructors

	/** default constructor */
	public AbstractInvNivNivelId() {
	}

	/** full constructor */
	public AbstractInvNivNivelId(Integer nivFilaId,
			InvStnEstante invStnEstante, Integer nivColId) {
		this.nivFilaId = nivFilaId;
		this.invStnEstante = invStnEstante;
		this.nivColId = nivColId;
	}

	// Property accessors

	public Integer getNivFilaId() {
		return this.nivFilaId;
	}

	public void setNivFilaId(Integer nivFilaId) {
		this.nivFilaId = nivFilaId;
	}

	public InvStnEstante getInvStnEstante() {
		return this.invStnEstante;
	}

	public void setInvStnEstante(InvStnEstante invStnEstante) {
		this.invStnEstante = invStnEstante;
	}

	public Integer getNivColId() {
		return this.nivColId;
	}

	public void setNivColId(Integer nivColId) {
		this.nivColId = nivColId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractInvNivNivelId))
			return false;
		AbstractInvNivNivelId castOther = (AbstractInvNivNivelId) other;

		return ((this.getNivFilaId() == castOther.getNivFilaId()) || (this
				.getNivFilaId() != null
				&& castOther.getNivFilaId() != null && this.getNivFilaId()
				.equals(castOther.getNivFilaId())))
				&& ((this.getInvStnEstante() == castOther.getInvStnEstante()) || (this
						.getInvStnEstante() != null
						&& castOther.getInvStnEstante() != null && this
						.getInvStnEstante()
						.equals(castOther.getInvStnEstante())))
				&& ((this.getNivColId() == castOther.getNivColId()) || (this
						.getNivColId() != null
						&& castOther.getNivColId() != null && this
						.getNivColId().equals(castOther.getNivColId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getNivFilaId() == null ? 0 : this.getNivFilaId().hashCode());
		result = 37
				* result
				+ (getInvStnEstante() == null ? 0 : this.getInvStnEstante()
						.hashCode());
		result = 37 * result
				+ (getNivColId() == null ? 0 : this.getNivColId().hashCode());
		return result;
	}

}